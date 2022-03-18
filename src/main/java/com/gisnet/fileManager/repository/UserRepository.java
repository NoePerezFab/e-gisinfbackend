
package com.gisnet.fileManager.repository;

import com.gisnet.fileManager.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Scope;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CouchbaseRepository<User, String>{
    Optional<User> findByUsername(String username);
    @Query("#{#n1ql.selectEntity} WHERE  `rol` = 'ROLE_USER' AND type = 'usuario' ")
    List<User> findUsers();
}
