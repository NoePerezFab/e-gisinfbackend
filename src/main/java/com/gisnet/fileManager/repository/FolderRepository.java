
package com.gisnet.fileManager.repository;

import com.gisnet.fileManager.domain.Folder;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FolderRepository extends CouchbaseRepository<Folder, String>{
    
}
