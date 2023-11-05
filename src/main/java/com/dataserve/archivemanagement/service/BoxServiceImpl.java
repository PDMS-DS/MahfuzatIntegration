package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.*;

import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.repository.BoxRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxRepo boxRepo;

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public Box findById(Long id) {
        return boxRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Box Not Found by id value: " + id));
    }

    @Override
    public BoxDto findBySerial(Long serial) {
        Box box = boxRepo.findBySerial(serial).orElseThrow(() ->
                new DataNotFoundException("Box Not Found by serial value: " + serial));
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
            boxDto.setBoxTypeId(boxType.getBoxTypeId());
        }
        if (shelf != null) {
            boxDto.setShelfId(shelf.getShelfId());
            boxDto.setShelfEn(shelf.getNameEn());
            boxDto.setShelfAr(shelf.getNameAr());
            Line line = shelf.getLine();
            if (line != null) {
                boxDto.setLineId(line.getLineId());
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
