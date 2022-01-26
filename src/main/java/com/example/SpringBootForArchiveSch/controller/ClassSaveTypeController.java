package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.ClassSaveType;
import com.example.SpringBootForArchiveSch.service.ClassSaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClassSaveTypeController {


    @Autowired
    private ClassSaveTypeService classSaveTypeService;

    @GetMapping("/classSaveType")
    public List<ClassSaveType> getAllShelves() {
        return classSaveTypeService.findAll();
    }

    @GetMapping("/classSaveType/{id}")
    public ResponseEntity<ClassSaveType> getClassSaveType(@PathVariable(value = "id") Long classSaveTypeId)
            throws ResourceNotFoundException {
        ClassSaveType classSaveType = classSaveTypeService.findById(classSaveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("classSaveType not found for this id :: " + classSaveTypeId));
        return ResponseEntity.ok().body(classSaveType);
    }

    @PostMapping("/classSaveType")
    public ResponseEntity<ClassSaveType> createClassSaveType(@Valid @RequestBody ClassSaveType classSaveType) {
        return ResponseEntity.ok().body(classSaveTypeService.save(classSaveType));
    }


    @PutMapping("/classSaveType/{id}")
    public ResponseEntity<ClassSaveType> updateClassSaveType(@PathVariable(value = "id") Long classSaveTypeId,
                                                 @Valid @RequestBody ClassSaveType classSaveTypeDetails) throws ResourceNotFoundException {
        ClassSaveType classSaveType = classSaveTypeService.findById(classSaveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("classSaveType not found for this id :: " + classSaveTypeId));
        classSaveType.setClassSaveArName(classSaveTypeDetails.getClassSaveArName());
        classSaveType.setClassSaveEnName(classSaveTypeDetails.getClassSaveEnName());
        final ClassSaveType updatedClassSaveType = classSaveTypeService.save(classSaveType);
        return ResponseEntity.ok(updatedClassSaveType);
    }

    @DeleteMapping("/classSaveType/{id}")
    public ResponseEntity deleteClassSaveType(@PathVariable(value = "id") Long classSaveTypeId)
            throws ResourceNotFoundException {
        ClassSaveType classSaveType = classSaveTypeService.findById(classSaveTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ClassSaveType not found for this id :: " + classSaveTypeId));
        classSaveTypeService.deleteById(classSaveType);
        return ResponseEntity.noContent().build();
    }
}
