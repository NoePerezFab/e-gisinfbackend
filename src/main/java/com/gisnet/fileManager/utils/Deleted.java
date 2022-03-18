
package com.gisnet.fileManager.utils;

import com.gisnet.fileManager.domain.User;
import java.io.Serializable;
import java.util.Date;


public class Deleted implements Serializable{
    private User usuario;
    private Date fecha;

    public User getUsuario() {
        return usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
