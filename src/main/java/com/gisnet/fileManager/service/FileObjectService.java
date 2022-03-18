
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.FileObject;
import java.util.List;
import java.util.Optional;


public interface FileObjectService {
    
    Optional<FileObject> findOne(String id);
    List<FileObject> findAll();
    FileObject create(FileObject fileObject);
    FileObject update(FileObject fileObject);
   
}
