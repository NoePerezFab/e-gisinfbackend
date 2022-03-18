
package com.gisnet.fileManager.domain;
public class ErrorHandler {
    private int status;
    private String error;
    public ErrorHandler(int status, String error){
        this.status = status;
        this.error = error;
    }
    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
}
