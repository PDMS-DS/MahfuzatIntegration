package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.repository.FolderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService{

    private FolderRepo folderRepo ;

    @Autowired
    public FolderServiceImpl(FolderRepo folderRepo) {
        this.folderRepo = folderRepo;
    }


    @Override
    public List<Folder> findAll() {
        return folderRepo.findAll();
    }

    @Override
    public Optional<Folder> findById(Long theId) {
        return folderRepo.findById(theId);
    }

    @Override
    public FolderDto save(Folder theFolder) {
        theFolder.setAddedOn(new Date());
        folderRepo.save(theFolder);
        FolderDto folderDto = new FolderDto();
        folderDto.setFolderId(theFolder.getFolderId());
        folderDto.setCapacity(theFolder.getCapacity());
        folderDto.setNameAr(theFolder.getNameAr());
        folderDto.setSerial(theFolder.getSerial());
        folderDto.setNameEn(theFolder.getNameEn());
        folderDto.setBoxId(theFolder.getBoxId());
        folderDto.setAddedOn(theFolder.getAddedOn());
        return folderDto;
    }

    @Override
    public void deleteById(Folder theFolder) {
        folderRepo.delete(theFolder);
    }
}
