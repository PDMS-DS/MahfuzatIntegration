package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.config.ConfigUtil;
import com.dataserve.mahfuzatintegration.exception.CustomServiceException;
import com.dataserve.mahfuzatintegration.model.Folder;
import com.dataserve.mahfuzatintegration.model.dto.BoxDto;
import com.dataserve.mahfuzatintegration.model.dto.FolderDto;
import com.dataserve.mahfuzatintegration.repository.FolderRepo;
import com.dataserve.mahfuzatintegration.util.ArchiveErrorCode;
import com.dataserve.mahfuzatintegration.util.LocalizationUtil;
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
                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(),
                configUtil.getLocalMessage("1009", null)
        ));
    }

    @Override
    public FolderDto findBySerial(Long serial) {
        Folder folder = folderRepo.findBySerial(serial).orElseThrow(() ->
                new CustomServiceException(
                        ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(),
                        configUtil.getLocalMessage("1009", null)
                ));
        return mapFolderToDto(folder);
    }

    private FolderDto mapFolderToDto(Folder folder) {
        FolderDto folderDto = new FolderDto();
        folderDto.setFolderId(folder.getFolderId());
        folderDto.setNameAr(folder.getNameAr());
        folderDto.setNameEn(folder.getNameEn());
        folderDto.setName(LocalizationUtil.getLocalizedValue(folder.getNameAr(), folder.getNameEn()));
        folderDto.setCapacity(folder.getCapacity());
        folderDto.setAddedOn(folder.getAddedOn());
        folderDto.setSerial(folder.getSerial());

        BoxDto box = BoxService.findBySerial(folder.getSerial());
        if (box != null) {
            folderDto.setBoxNameAr(box.getNameAr());
            folderDto.setBoxNameEn(box.getNameEn());
            folderDto.setBoxName(LocalizationUtil.getLocalizedValue(box.getNameAr(), box.getNameEn()));
            folderDto.setBoxTypeNameAr(box.getBoxTypeNameAr());
            folderDto.setBoxTypeNameEn(box.getBoxTypeNameEn());
            folderDto.setBoxTypeName(LocalizationUtil.getLocalizedValue(box.getBoxTypeNameAr(), box.getBoxTypeNameEn()));
            folderDto.setShelfAr(box.getShelfAr());
            folderDto.setShelfEn(box.getShelfEn());
            folderDto.setShelf(LocalizationUtil.getLocalizedValue(box.getShelfAr(), box.getShelfEn()));
            folderDto.setLineAr(box.getLineAr());
            folderDto.setLineEn(box.getLineEn());
            folderDto.setLine(LocalizationUtil.getLocalizedValue(box.getLineAr(), box.getLineEn()));
            folderDto.setInventoryAr(box.getInventoryAr());
            folderDto.setInventoryEn(box.getInventoryEn());
            folderDto.setInventory(LocalizationUtil.getLocalizedValue(box.getInventoryAr(), box.getInventoryEn()));
            folderDto.setCenterAr(box.getCenterAr());
            folderDto.setCenterEn(box.getCenterEn());
            folderDto.setCenter(LocalizationUtil.getLocalizedValue(box.getCenterAr(), box.getCenterEn()));
            folderDto.setDepartmentNameAr(box.getDepartmentNameAr());
            folderDto.setDepartmentNameEn(box.getDepartmentNameEn());
            folderDto.setDepartmentName(LocalizationUtil.getLocalizedValue(box.getDepartmentNameAr(), box.getDepartmentNameEn()));
            folderDto.setPathAr(box.getPathAr());
            folderDto.setPathEn(box.getPathEn());
        }
        return folderDto;
    }


}
