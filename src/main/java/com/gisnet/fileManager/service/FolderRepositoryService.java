
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.Folder;
import com.gisnet.fileManager.repository.FolderRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderRepositoryService implements FolderService{
    @Autowired
    private FolderRepository repo;
    @Override
    public Optional<Folder> findOne(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Folder> findAll() {
        List<Folder> lista = new ArrayList<Folder>();
        Iterator it = repo.findAll().iterator();
        while(it.hasNext()){
            lista.add((Folder)it.next());
        }
        return lista;
    }

    @Override
    public Folder create(Folder folder) {
         return repo.save(folder);
    }

    @Override
    public Folder update(Folder folder) {
       return repo.save(folder);
    }
    
}
