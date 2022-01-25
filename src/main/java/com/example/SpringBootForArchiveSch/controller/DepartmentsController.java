package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.Departments;
import com.example.SpringBootForArchiveSch.model.dto.DepartmentsDto;
import com.example.SpringBootForArchiveSch.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentsController {

    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("/department")
    public List<Departments> getAllDepartments() {
        return departmentsService.findAll();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Departments> getDepartmentsId(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        Departments department = departmentsService.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));
        return ResponseEntity.ok().body(department);
    }

    @PostMapping("/department")
    public ResponseEntity<DepartmentsDto> createBDepartment(@Valid @RequestBody Departments departments) {
        return ResponseEntity.ok().body(departmentsService.save(departments));
    }


    @PutMapping("/department/{id}")
    public ResponseEntity<DepartmentsDto> updateDepartment(@PathVariable(value = "id") Long departmentsId,
                                                 @Valid @RequestBody Departments departmentsDetails) throws ResourceNotFoundException {
        Departments departments = departmentsService.findById(departmentsId)
                .orElseThrow(() -> new ResourceNotFoundException("department not found for this id :: " + departmentsId));
        departments.setDeptCode(departmentsDetails.getDeptCode());
        departments.setDeptArName(departmentsDetails.getDeptArName());
        departments.setDeptEnName(departmentsDetails.getDeptEnName());
        departments.setEnabled(departmentsDetails.isEnabled());
        final DepartmentsDto updatedDepartment = departmentsService.save(departments);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity deleteBoxType(@PathVariable(value = "id") Long departmentsId)
            throws ResourceNotFoundException {
        Departments departments = departmentsService.findById(departmentsId)
                .orElseThrow(() -> new ResourceNotFoundException("department not found for this id :: " + departmentsId));
        departmentsService.deleteById(departments);
        return ResponseEntity.noContent().build();
    }
}
