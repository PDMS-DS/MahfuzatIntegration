package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.repository.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepo folderRepo;


    @Override
    public List<Folder> findAll() {
        return folderRepo.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Folder Not Found By id value: " + id));
    }

    @Override
    public Folder findBySerial(Long serial) {
        return folderRepo.findBySerial(serial).orElseThrow(() -> new DataNotFoundException("Folder Not found By serial value: " + serial));
    }


}
