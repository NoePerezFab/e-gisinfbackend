
package com.gisnet.fileManager.utils;

import com.gisnet.fileManager.domain.FileObject;
import com.gisnet.fileManager.domain.User;
import java.io.Serializable;

public class UpdateRequest implements Serializable{
    private FileObject file;
    private User user;

    public FileObject getFile() {
        return file;
    }

    public User getUser() {
        return user;
    }

    public void setFile(FileObject file) {
        this.file = file;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
