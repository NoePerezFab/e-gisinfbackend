
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.User;
import com.gisnet.fileManager.repository.UserRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService implements UserService{
    @Autowired
    private UserRepository repo;

    @Override
    public Optional<User> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public List<User> finadAll() {
        List<User> lista = new ArrayList<>();
        Iterator<User> it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add(it.next());
        }
        return lista;
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public User update(User user) {
        return repo.save(user);
    }

    @Override
    public Optional<User> findByUsername(User user) {
        return repo.findByUsername(user.getUsername());
    }
    public List<User> findUsers(){
        return (repo.findUsers());
    }
}
