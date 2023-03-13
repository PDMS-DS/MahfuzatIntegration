package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.FolderDto;

import java.util.List;
import java.util.Optional;

public interface FolderService {

    public List<Folder> findAll();

    public Optional<Folder> findById(Long theId);

    public FolderDto save(Folder theFolder);

    public void deleteById(Folder theFolder);
}
