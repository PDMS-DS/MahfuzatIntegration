package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.repository.FolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private BoxService BoxService;


    @Override
    public List<Folder> findAll() {
        return folderRepo.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepo.findById(id).orElseThrow(() -> new DataNotFoundException("Folder Not Found By id value: " + id));
    }

    @Override
    public FolderDto findBySerial(Long serial) {
        Folder folder = folderRepo.findBySerial(serial).orElseThrow(() -> new DataNotFoundException("Folder Not found By serial value: " + serial));
        BoxDto box = BoxService.findBySerial(serial);
        FolderDto folderDto = new FolderDto();
        folderDto.setFolderId(folder.getFolderId());
        folderDto.setNameAr(folder.getNameAr());
        folderDto.setNameEn(folder.getNameEn());
        folderDto.setBoxNameAr(box.getNameAr());
        folderDto.setBoxNameEn(box.getNameEn());
        folderDto.setCapacity(folder.getCapacity());
        folderDto.setAddedOn(folder.getAddedOn());
        folderDto.setSerial(folder.getSerial());
        folderDto.setBoxTypeNameAr(box.getBoxTypeNameAr());
        folderDto.setBoxTypeNameEn(box.getBoxTypeNameEn());
        folderDto.setDepartmentNameAr(box.getDepartmentNameAr());
        folderDto.setDepartmentNameEn(box.getDepartmentNameEn());
        folderDto.setLineAr(box.getLineAr());
        folderDto.setLineEn(box.getLineEn());
        folderDto.setShelfAr(box.getShelfAr());
        folderDto.setShelfEn(box.getShelfEn());
        folderDto.setInventoryAr(box.getInventoryAr());
        folderDto.setInventoryEn(box.getInventoryEn());
        folderDto.setCenterAr(box.getCenterAr());
        folderDto.setCenterEn(box.getCenterEn());
        folderDto.setPathAr(box.getPathAr());
        folderDto.setPathEn(box.getPathEn());
        return folderDto;

    }


}
