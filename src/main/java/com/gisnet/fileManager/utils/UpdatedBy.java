
package com.gisnet.fileManager.utils;

import com.gisnet.fileManager.domain.User;
import java.io.Serializable;

public class UpdatedBy implements Serializable{
    private User user;
    private int version;

    public UpdatedBy(User user, int version) {
        this.user = user;
        this.version = version;
    }
    
    public User getUser() {
        return user;
    }

    public int getVersion() {
        return version;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    
}
