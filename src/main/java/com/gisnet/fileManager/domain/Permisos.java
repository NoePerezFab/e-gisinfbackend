
package com.gisnet.fileManager.domain;

import java.io.Serializable;
import java.util.List;


public class Permisos implements Serializable{
    private String path;
    private boolean ver;
    private boolean carga;
    private boolean descarga;
    private boolean edicion;

   
    
    
    
    
    public String getPath() {
        return path;
    }

    public boolean getVer() {
        return ver;
    }

    public boolean getCarga() {
        return carga;
    }

    public boolean getDescarga() {
        return descarga;
    }

    public boolean getEdicion() {
        return edicion;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public void setCarga(boolean carga) {
        this.carga = carga;
    }

    public void setDescarga(boolean descarga) {
        this.descarga = descarga;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }
 
    
}
