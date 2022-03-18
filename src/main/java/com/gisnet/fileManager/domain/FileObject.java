
package com.gisnet.fileManager.domain;

import com.gisnet.fileManager.utils.Deleted;
import com.gisnet.fileManager.utils.Metadato;
import com.gisnet.fileManager.utils.UpdatedBy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;


@Document
public class FileObject implements Serializable{
    @Id
    private String id;
    @Field
    private Date documentDate;
    @Field
    private String documentName;
    @Field
    private String documentText;
    @Field
    private String path;
    @Field
    private String serverPath;
    @Field
    private String type;
    @Field
    private Deleted deleted;
    @Field
    private User uploadBy;
    @Field
    private String documentType;
    @Field
    private int version;
    @Field
    private String documentSystemName;
    @Field
    private List<UpdatedBy> updatedBy;
    @Field
    private List<Metadato> metadatos;

    public List<Metadato> getMetadatos() {
        return metadatos;
    }

    public void setMetadatos(List<Metadato> metadatos) {
        this.metadatos = metadatos;
    }
    
    public String getDocumentSystemName() {
        return documentSystemName;
    }

    public List<UpdatedBy> getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(List<UpdatedBy> updatedBy) {
        this.updatedBy = updatedBy;
    }
    

    public void setDocumentSystemName(String documentSystemName) {
        this.documentSystemName = documentSystemName;
    }
    
    
    public Deleted getDeleted() {
        return deleted;
    }

    public User getUploadBy() {
        return uploadBy;
    }

    public String getDocumentType() {
        return documentType;
    }

    public int getVersion() {
        return version;
    }

    public void setUploadBy(User uploadBy) {
        this.uploadBy = uploadBy;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeleted(Deleted deleted) {
        this.deleted = deleted;
    }
    
    public FileObject(){
        this.type = "document";
        
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
 
    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
    

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }
    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    
    public void setId(){
        this.id = this.type + "::" + this.documentName;
    }
    
    
    
    
}
