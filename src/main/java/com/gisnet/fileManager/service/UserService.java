
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.User;
import com.gisnet.fileManager.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
   
    Optional<User> findOne(String id);
    List<User> finadAll();
    User create(User user);
    User update(User user);
    Optional<User> findByUsername(User user);
}
