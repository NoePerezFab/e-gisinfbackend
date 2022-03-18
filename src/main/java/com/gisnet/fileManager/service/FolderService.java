
package com.gisnet.fileManager.service;

import com.gisnet.fileManager.domain.Folder;
import java.util.List;
import java.util.Optional;

public interface FolderService {
    Optional<Folder>findOne(String id);
    List<Folder>findAll();
    Folder create(Folder folder);
    Folder update(Folder folder);
}
