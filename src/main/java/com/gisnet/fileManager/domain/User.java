
package com.gisnet.fileManager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.Scope;

@Scope("entidades")
@Collection("user")
@Document
public class User implements Serializable{
    @Id 

    private String id;
    @Field
    private String type;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private String name;
    @Field
    private String lastname; 
    @Field
    private String rol;
    @Field
    private List<Permisos> permisos;
    private String token;

    public List<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }
    
    
    
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
    public String getRol() {
        return rol;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    
    public String getToken() {
        return token;
    }
    
    
    public User(){
        this.type = "usuario";
    }
    public void setId(){
        this.id = this.type + "::" + this.username;
    }
    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void addPermiso(Permisos permiso){
        if(this.permisos == null ){
            this.permisos = new ArrayList<>();
        }
        this.permisos.add(permiso);
    }
    
}
