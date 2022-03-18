
package com.gisnet.fileManager.utils;

import com.gisnet.fileManager.domain.FileObject;
import com.gisnet.fileManager.domain.User;
import java.io.Serializable;

public class UploadRequest implements Serializable{
    private User user;
    private FileObject file;

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
