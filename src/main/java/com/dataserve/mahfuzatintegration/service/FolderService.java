package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.model.Folder;
import com.dataserve.mahfuzatintegration.model.dto.FolderDto;

import java.util.List;

public interface FolderService {

    List<Folder> findAll();

    Folder findById(Long theId);

    FolderDto findBySerial(Long serial);

}
