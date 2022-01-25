package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.BoxType;
import com.example.SpringBootForArchiveSch.model.StorageCenterType;
import com.example.SpringBootForArchiveSch.service.BoxTypeService;
import com.example.SpringBootForArchiveSch.service.StorageCenterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StorageCenterTypeController {

    @Autowired
    private StorageCenterTypeService storageCenterTypeService;

    @GetMapping("/storageCenterTypeService")
    public List<StorageCenterType> getAllStorageCenterType() {
        return storageCenterTypeService.findAll();
    }

    @GetMapping("/storageCenterTypeService/{id}")
    public ResponseEntity<StorageCenterType> getStorageCenterTypeById(@PathVariable(value = "id") Long storageCenterTypeId)
            throws ResourceNotFoundException {
        StorageCenterType storageCenterType = storageCenterTypeService.findById(storageCenterTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageCenterType not found for this id :: " + storageCenterTypeId));
        return ResponseEntity.ok().body(storageCenterType);
    }

    @PostMapping("/storageCenterTypeService")
    public ResponseEntity<StorageCenterType> createStorageCenterType(@Valid @RequestBody StorageCenterType storageCenterType) {
        return ResponseEntity.ok().body(storageCenterTypeService.save(storageCenterType));
    }


    @PutMapping("/storageCenterTypeService/{id}")
    public ResponseEntity<StorageCenterType> updateStorageCenterType(@PathVariable(value = "id") Long storageCenterTypeId,
                                                 @Valid @RequestBody StorageCenterType storageCenterTypeDetails) throws ResourceNotFoundException {
        StorageCenterType storageCenterType = storageCenterTypeService.findById(storageCenterTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageCenterType not found for this id :: " + storageCenterTypeId));
        storageCenterType.setTypeAr(storageCenterTypeDetails.getTypeAr());
        storageCenterType.setTypeEn(storageCenterTypeDetails.getTypeEn());
        final StorageCenterType updatedStorageCenterType = storageCenterTypeService.save(storageCenterType);
        return ResponseEntity.ok(updatedStorageCenterType);
    }

    @DeleteMapping("/storageCenterTypeService/{id}")
    public ResponseEntity deleteStorageCenterType(@PathVariable(value = "id") Long storageCenterTypeId)
            throws ResourceNotFoundException {
        StorageCenterType storageCenterType = storageCenterTypeService.findById(storageCenterTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageCenterType not found for this id :: " + storageCenterTypeId));
        storageCenterTypeService.deleteById(storageCenterType);
        return ResponseEntity.noContent().build();
    }
}
