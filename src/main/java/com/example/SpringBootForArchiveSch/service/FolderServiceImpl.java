package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Folder;
import com.example.SpringBootForArchiveSch.repository.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FolderServiceImpl implements FolderService{

    private FolderRepo folderRepo ;

    @Autowired
    public FolderServiceImpl(FolderRepo folderRepo) {
        this.folderRepo = folderRepo;
    }


    @Override
    public List<Folder> findAll() {
        return null;
    }

    @Override
    public Optional<Folder> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Folder save(Folder theFolder) {
        return null;
    }

    @Override
    public void deleteById(Folder theFolder) {

    }
}
