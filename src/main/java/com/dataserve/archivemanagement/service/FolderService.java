package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.FolderDto;

import java.util.List;

public interface FolderService {

    List<Folder> findAll();

    Folder findById(Long theId);

    FolderDto findBySerial(Long serial);

}
