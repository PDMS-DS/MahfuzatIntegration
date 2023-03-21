package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.model.dto.response.FolderResponse;

import java.util.List;

public interface FolderService {

    public List<Folder> findAll();

    public FolderResponse findById(Long theId);
    
    public FolderResponse findBySerial(Long serial);

}
