package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.repository.FolderRepo;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private BoxService BoxService;

    @Autowired
    private ConfigUtil configUtil;

    @Override
    public List<Folder> findAll() {
        return folderRepo.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepo.findById(id).orElseThrow(() -> new CustomServiceException(
                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(), // Error code for folder not found
                configUtil.getLocalMessage("1009", null)    // Localized message
        ));
    }

    @Override
    public FolderDto findBySerial(Long serial) {
        Folder folder = folderRepo.findBySerial(serial).orElseThrow(() -> new CustomServiceException(
                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(), // Error code for folder not found
                configUtil.getLocalMessage("1009", null)    // Localized message
        ));

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
