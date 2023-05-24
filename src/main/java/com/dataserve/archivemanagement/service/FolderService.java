package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Folder;

import java.util.List;

public interface FolderService {

    List<Folder> findAll();

    Folder findById(Long theId);

    Folder findBySerial(Long serial);

}
