
package com.gisnet.fileManager.domain;

import com.gisnet.fileManager.utils.Metadato;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Scope("entidades")
@Collection("folder")
@Document
public class Folder implements Serializable{
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String path;
    @Field
    private List<FileObject> fileList;
    @Field
    private List<Folder> folderList;
    @Field
    private String type;
    @Field
    private String fatherPath;
    @Field
    private List<Metadato> metadatos;

    public Folder(){
        this.type = "folder";
        
    }

    public void setMetadatos(List<Metadato> metadatos) {
        this.metadatos = metadatos;
    }
    
    public List<Metadato> getMetadatos() {
        return metadatos;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setFatherPath(String fatherPath) {
        this.fatherPath = fatherPath;
    }
    
    public String getType() {
        return type;
    }

    public String getFatherPath() {
        return fatherPath;
    }
    
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public List<FileObject> getFileList() {
        return fileList;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setId(){
        this.id = this.type + "::" + this.path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileList(List<FileObject> fileList) {
        this.fileList = fileList;
    }

    public void setFolderList(List<Folder> folderList) {
        this.folderList = folderList;
    }

    
    
}
