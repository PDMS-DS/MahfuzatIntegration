package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.*;
import com.dataserve.archivemanagement.model.dto.StorageCenterDto;
import com.dataserve.archivemanagement.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StorageCenterController {

    @Autowired
    private StorageCenterService storageCenterService;

    @Autowired
    private StorageCenterTypeService storageCenterTypeService;

    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("/storageCenter")
    public List<StorageCenter> getAllBox() {
        return storageCenterService.findAll();
    }

    @GetMapping("/storageCenter/{id}")
    public ResponseEntity<StorageCenter> getStorageCenterById(@PathVariable(value = "id") Long storageCenterId)
            throws ResourceNotFoundException {
        StorageCenter storageCenter = storageCenterService.findById(storageCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("storageCenter not found for this id :: " + storageCenterId));
        return ResponseEntity.ok().body(storageCenter);
    }

    @PostMapping("/storageCenter/{centerTypeId}/{deptId}")
    public ResponseEntity<StorageCenterDto> createStorageCenter(@PathVariable(value = "centerTypeId") Long centerTypeId , @PathVariable(value = "deptId") Long deptId
            , @Valid @RequestBody StorageCenter storageCenter) throws ResourceNotFoundException{
        if(centerTypeId != null ) {
            StorageCenterType storageCenterType = storageCenterTypeService.findById(centerTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("StorageCenterType not found for this id :: " + centerTypeId));
            storageCenter.setStorageCenterType(storageCenterType);

        }
        if(deptId != null) {
            Departments departments = departmentsService.findById(deptId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + deptId));
            storageCenter.setDepartments(departments);

        }
        return ResponseEntity.ok().body(storageCenterService.save(storageCenter));
    }


    @PutMapping("/storageCenter/{id}")
    public ResponseEntity<StorageCenterDto> updateStorageCenter(@PathVariable(value = "id") Long StorageCenterId,
                                            @Valid @RequestBody StorageCenter storageCenterDetails) throws ResourceNotFoundException {
        StorageCenter storageCenter = storageCenterService.findById(StorageCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageCenter not found for this id :: " + StorageCenterId));

        storageCenter.setNameAr(storageCenterDetails.getNameAr());
        storageCenter.setNameEn(storageCenterDetails.getNameEn());
        storageCenter.setActive(storageCenterDetails.isActive());


        final StorageCenterDto updatedStorageCenter = storageCenterService.save(storageCenter);
        return ResponseEntity.ok(updatedStorageCenter);
    }

    @DeleteMapping("/storageCenter/{id}")
    public ResponseEntity deleteStorageCenter(@PathVariable(value = "id") Long storageCenterId)
            throws ResourceNotFoundException {
        StorageCenter storageCenter = storageCenterService.findById(storageCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("storageCenter not found for this id :: " + storageCenterId));
        storageCenterService.deleteById(storageCenter);
        return ResponseEntity.noContent().build();
    }
}
