//
package com.gisnet.fileManager.repository;

import com.gisnet.fileManager.domain.FileObject;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;

import org.springframework.stereotype.Repository;



@Repository
@Scope("documentos")
public interface FileObjectRepository extends CouchbaseRepository<FileObject, String> {
    List<FileObject> findByPath(String path);
    @Query("#{#n1ql.selectEntity} WHERE  `path` = '#{[0]}' AND type = 'document' ")
    List<FileObject> findNoDeleted(String path);
    void deleteById(String id);
}
