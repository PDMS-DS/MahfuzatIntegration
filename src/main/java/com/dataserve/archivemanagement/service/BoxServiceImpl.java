package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.model.*;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.repository.BoxRepo;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxRepo boxRepo;

    @Autowired
    private ConfigUtil configUtil;

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public Box findById(Long id) {
        return boxRepo.findById(id)
                .orElseThrow(() -> new CustomServiceException(
                        ArchiveErrorCode.BOX_NOT_FOUND_BY_ID.getCode(), // Error code for box not found by ID
                        configUtil.getLocalMessage("1027", new String[]{id.toString()}) // Localized message
                ));
    }

    @Override
    public BoxDto findBySerial(Long serial) {
        Box box = boxRepo.findBySerial(serial).orElseThrow(() ->
                new CustomServiceException(
                        ArchiveErrorCode.BOX_NOT_FOUND_BY_SERIAL.getCode(), // Error code for box not found by serial
                        configUtil.getLocalMessage("1028", new String[]{serial.toString()}) // Localized message
                ));
        BoxType boxType = box.getBoxType();
        BoxDto boxDto = new BoxDto();
        Shelf shelf = box.getShelf();
        boxDto.setBoxId(box.getBoxId());
        boxDto.setCapacity(box.getCapacity());
        boxDto.setAddedOn(box.getAddedOn());
        boxDto.setDate(box.getDate());
        boxDto.setNameAr(box.getNameAr());
        boxDto.setNameEn(box.getNameEn());
        boxDto.setPathAr(box.getPathAr());
        boxDto.setPathEn(box.getPathEn());
        if (boxType != null) {
            boxDto.setBoxTypeNameAr(boxType.getBoxTypeAr());
            boxDto.setBoxTypeNameEn(boxType.getBoxTypeEn());
        }
        if (shelf != null) {
            boxDto.setShelfEn(shelf.getNameEn());
            boxDto.setShelfAr(shelf.getNameAr());
            Line line = shelf.getLine();
            if (line != null) {
                boxDto.setLineAr(line.getNameAr());
                boxDto.setLineEn(line.getNameEn());
                Inventory inventory = line.getInventory();
                if (inventory != null) {
                    boxDto.setInventoryAr(inventory.getNameAr());
                    boxDto.setInventoryEn(inventory.getNameEr());
                    StorageCenter storageCenter = inventory.getStorageCenter();
                    if (storageCenter != null) {
                        boxDto.setCenterAr(storageCenter.getNameAr());
                        boxDto.setCenterEn(storageCenter.getNameEn());
                        Departments departments = storageCenter.getDepartments();
                        if (departments != null) {
                            boxDto.setDepartmentNameEn(departments.getDeptEnName());
                            boxDto.setDepartmentNameAr(departments.getDeptArName());
                        }
                    }
                }
            }
        }

        return boxDto;
    }
}
