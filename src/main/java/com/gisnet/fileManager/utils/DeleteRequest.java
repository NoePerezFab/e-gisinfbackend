
package com.gisnet.fileManager.utils;

import com.gisnet.fileManager.domain.FileObject;
import com.gisnet.fileManager.domain.User;
import java.io.Serializable;
import java.util.Date;

public class DeleteRequest implements Serializable{
    private User user;
    private FileObject file;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    public User getUser() {
        return user;
    }

    public FileObject getFile() {
        return file;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFile(FileObject file) {
        this.file = file;
    }
    
}
