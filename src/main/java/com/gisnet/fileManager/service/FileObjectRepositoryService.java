
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.FileObject;
import com.gisnet.fileManager.repository.FileObjectRepository;
import com.gisnet.fileManager.service.FileObjectService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FileObjectRepositoryService")
public class FileObjectRepositoryService implements FileObjectService{
    
    @Autowired
    private FileObjectRepository repo;
    
    @Override
    public Optional<FileObject> findOne(String id) {
        
        return repo.findById(id);
    }

    @Override
    public List<FileObject> findAll() {
        List<FileObject> listFile = new ArrayList<>();
        Iterator<FileObject> it = repo.findAll().iterator();
        while(it.hasNext()){
            listFile.add(it.next());
        }
        return listFile;
    }

    @Override
    public FileObject create(FileObject fileObject) {
        return repo.save(fileObject);
    }

    @Override
    public FileObject update(FileObject fileObject) {
        return repo.save(fileObject);
    }
    
    
    public List<FileObject> findByPath(String path){
        return repo.findByPath(path);
    }
    public List<FileObject> findNoDeleted(String path){
        return repo.findNoDeleted(path);
    }
    public void deleteById(String id){
        FileObject file = repo.findById(id).get();
        
    }
    
   
}
