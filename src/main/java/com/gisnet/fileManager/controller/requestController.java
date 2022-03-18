
package com.gisnet.fileManager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.fileManager.domain.ErrorHandler;
import com.gisnet.fileManager.domain.FileObject;
import com.gisnet.fileManager.domain.Folder;
import com.gisnet.fileManager.domain.Permisos;
import com.gisnet.fileManager.domain.User;
import com.gisnet.fileManager.service.FileObjectRepositoryService;
import com.gisnet.fileManager.service.FolderRepositoryService;
import com.gisnet.fileManager.service.UserRepositoryService;
import com.gisnet.fileManager.utils.DeleteRequest;
import com.gisnet.fileManager.utils.Deleted;
import com.gisnet.fileManager.utils.UpdateRequest;
import com.gisnet.fileManager.utils.UpdatedBy;
import com.gisnet.fileManager.utils.UploadRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@Controller
@CrossOrigin(origins = { "http://192.168.254.186:3000" }, allowedHeaders = "*", allowCredentials = "true")
@RestController

public class requestController {
    private static String UNAUTORIZED = "{\"status\": 401,\n" +
"    \"error\": \"Unautorized\",}";
    private static Folder rootFolder;
    @Autowired
    private FileObjectRepositoryService fileService;
    @Autowired
    private UserRepositoryService userService;
    @Autowired
    private Environment environment;
    @Autowired
    private FolderRepositoryService folderService;
    
    private  String rootPath = "/home/e-GISdoc";
    private ObjectMapper mapper = new ObjectMapper();
    
    
   
    @GetMapping("/getusers")
    public String getUsers(HttpSession sesion) throws JsonProcessingException{
            return mapper.writeValueAsString(userService.findUsers());
        
    }
    @PostMapping("/index")
    public String getIndex(@RequestBody User user) throws IOException{
             File rootPathFolder = new File(rootPath);
             user.setId();
             user = userService.findOne(user.getId()).get();
             List<Folder> list  = folderService.findAll();
        if(list.isEmpty()){
            Folder f = new Folder();
            f.setPath(this.rootPath);
            f.setId();
            f.setName(rootPathFolder.getName());
            return mapper.writeValueAsString(folderService.create(f));
        }else{
            Folder f = new Folder();
            f.setPath(rootPath);
            f.setId();
            f = folderService.findOne(f.getId()).get();
            if(user.getRol().compareTo("ROLE_ADMIN")==0){
                return mapper.writeValueAsString(f);
            }
            list = f.getFolderList();
            if(list == null || list.size() == 0){
                return mapper.writeValueAsString(f);
            }
            List<Permisos> permisos = user.getPermisos();
            List<Folder> permisosCopy = new ArrayList<>(list);
            for(Folder fol : permisosCopy){
                if(!checkPermisos(permisos, fol)){
                    list.remove(fol);
                }
            }
            
            f.setFolderList(list);
            
            return mapper.writeValueAsString(f);
        }
            
        
        
       // return mapper.writeValueAsString(list.get(0));
    }
    
    @PostMapping(value="/upfile",produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public String createFile(@RequestBody UploadRequest uploadr) throws JsonProcessingException{
        FileObject fileObject = uploadr.getFile();
        User user = uploadr.getUser();
        user.setId();
        System.out.println(user.getId());
        user = userService.findOne(user.getId()).get();
        user.setPassword(""); 
        fileObject.setUploadBy(user);
        String host = "http://192.168.200.216:8084/fileManager-0.0.1-SNAPSHOT/files";
        System.out.println(fileObject.getPath());
        String path[] = fileObject.getPath().split("\\\\");
        for(int i=1; i<path.length;i++){
            host = host + "/" + path[i];
        }
        fileObject.setServerPath(host+fileObject.getPath());
        fileObject.setId();
        if(fileService.findOne(fileObject.getId()).isPresent()){
            return mapper.writeValueAsString(new ErrorHandler(200, "Ya existe un archivo con ese nombre"));
        }
        return mapper.writeValueAsString(fileService.create(fileObject));
    }
    @PostMapping(value="/getfiles",produces="application/json")
    public String getFilesByPath(@RequestBody FileObject fileObject) throws JsonProcessingException{
        
        String path = fileObject.getPath();
        String pathSplit[] = path.split("\\\\");
        String pathConverted = "";
        for(String s : pathSplit){
            pathConverted = pathConverted + s + "\\\\";
        }
        pathConverted = pathConverted.substring(0, pathConverted.length()-2);
        
        return mapper.writeValueAsString(fileService.findNoDeleted(pathConverted));
    }
    @PostMapping(value="/getfile",produces="application/json")
    public String getFile(@RequestBody FileObject fileObject) throws JsonProcessingException{
        FileObject fileResponse =fileService.findOne(fileObject.getId()).get();
        return mapper.writeValueAsString(fileResponse);
    }
    @PostMapping("/savefile")
    public String uploadFile(@RequestParam("file") MultipartFile file,
    @RequestParam("path") String path) throws IOException{
        File convfile = new File(path+"/"+file.getOriginalFilename());
        file.transferTo(convfile);
 
        return(path);
    }
    @PostMapping("/updatefile")
    public String updateFile(@RequestBody UpdateRequest update) throws JsonProcessingException{
        FileObject file = update.getFile();
        int version = file.getVersion();
        file.setId();
        file = fileService.findOne(file.getId()).get();
        List<UpdatedBy> lista;
        if(file.getUpdatedBy() != null ){
            lista = file.getUpdatedBy();
        }else{
            lista = new ArrayList<>();
        }
        lista.add(new UpdatedBy(update.getUser(),version));
        file.setVersion(version);
        file.setUpdatedBy(lista);
        file.setDocumentSystemName(update.getFile().getDocumentSystemName());
        return mapper.writeValueAsString(fileService.update(file));
    }
    @PostMapping("/deletefile")
    public String deleteFile(@RequestBody DeleteRequest delete){
        FileObject file = fileService.findOne(delete.getFile().getId()).get();
        Deleted del = new Deleted();
        del.setFecha(delete.getFecha());
        del.setUsuario(delete.getUser());
        file.setDeleted(del);
        fileService.update(file);
        return "Ok";
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) throws JsonProcessingException{
        user.setId();
        if(!userService.findOne(user.getId()).isPresent()){
        if(user.getRol().compareTo("ROLE_USER") == 0 ){
            List<Folder> list = folderService.findAll();
            for(Folder f : list){
                Permisos permiso = new Permisos();
                permiso.setPath(f.getPath());
                permiso.setCarga(false);
                permiso.setDescarga(false);
                permiso.setEdicion(false);
                permiso.setVer(false);
                user.addPermiso(permiso);
            }
        }
        return mapper.writeValueAsString(userService.create(user));
        }else{
            return mapper.writeValueAsString(new ErrorHandler(200, "Ya existe un usuario con ese nombre de usuario") );
        }
        
    }
    @PostMapping("/addfolder")
    public String addFolder(@RequestBody Folder folder) throws JsonProcessingException{
        boolean added = false;
        folder.setPath(folder.getFatherPath()+"/"+folder.getName());
        folder.setId();
        folder = folderService.create(folder);
        Folder father = new Folder();
        File systemFolder = new File(folder.getPath());
        System.err.println(systemFolder.getPath());
        systemFolder.mkdir();
        System.err.println(systemFolder.mkdir());
        while(folder.getFatherPath() != null){
            added = false;
          father.setPath(folder.getFatherPath());
            father.setId();
            father = folderService.findOne(father.getId()).get();
            List<Folder> list = father.getFolderList();
            if(list == null){
                list = new ArrayList<Folder>();
            }
            for(Folder f : list ){
                
                if(f.getId().compareTo(folder.getId()) == 0){
                    list.remove(f);
                    list.add(folder);
                    added = true;
                    break;
                }
            }
            if(!added){
                list.add(folder);
            }
            
            father.setFolderList(list);
            folderService.update(father);
            System.out.println(folder.getId());
            folder = folderService.findOne(father.getId()).get();
            System.out.println(folder.getId());
        }
        List<User> users = userService.finadAll();
        for(User u : users){
            if(u.getRol().compareTo("ROLE_ADMIN") != 0){
                List<Permisos> permisos = u.getPermisos();
                Permisos permiso = new Permisos();
                permiso.setPath(folder.getPath());
                permiso.setCarga(false);
                permiso.setDescarga(false);
                permiso.setEdicion(false);
                permiso.setVer(false);
                permisos.add(permiso);
                u.setPermisos(permisos);
                userService.update(u);
            }
            
            
        }
        return mapper.writeValueAsString(folder);
    }
    @GetMapping("/pruebapermisos")
    public String pruebaPermisos() throws JsonProcessingException{
        List<User> users = userService.finadAll();
        for(User u : users){
            if(u.getRol().compareTo("ROLE_ADMIN") != 0){
                List<Folder> list = folderService.findAll();
                 u.setPermisos(new ArrayList<>());
            for(Folder f : list){
               
                Permisos permiso = new Permisos();
                permiso.setPath(f.getPath());
                permiso.setCarga(false);
                permiso.setDescarga(false);
                permiso.setEdicion(false);
                permiso.setVer(false);
                u.addPermiso(permiso);
            }
            userService.update(u);
               
            }
            
            
        }
        return mapper.writeValueAsString(users);
        
    }
    @PostMapping("/getfolder")
    public String getFolder(@RequestBody Folder folder) throws JsonProcessingException{
        folder.setId();
        System.err.println(folder.getId());
        return mapper.writeValueAsString(folderService.findOne(folder.getId()).get());
    }
    
    @PostMapping("/updatepermisos")
    public String updatePermisos(@RequestBody User user) throws JsonProcessingException{
      List<Permisos> permisos = user.getPermisos();
      user = userService.findByUsername(user).get();
      user.setPermisos(permisos);
      userService.update(user);
      return(mapper.writeValueAsString(new ErrorHandler(200, "Ok")));
    }
    
    public static void checkfolder(Folder folder,FolderRepositoryService repo){
        List<Folder> lista = folder.getFolderList();
        
       if(!lista.isEmpty()){
           for(Folder f : lista){
               f.setId();
               checkfolder(f,repo);
           }
       }else{
           Optional<Folder> f = repo.findOne(folder.getId());
        if(!f.isPresent()){
            repo.create(folder);
        }
       }
    }
    public static boolean checkPermisos(List<Permisos> permisos, Folder folder){
        boolean flag = false;
        for(Permisos p : permisos){
            if(p.getPath().compareTo(folder.getPath()) == 0){
                if(p.getVer()){
                    flag = true;
                }
            }
        }
        return flag;
    }
   
}
