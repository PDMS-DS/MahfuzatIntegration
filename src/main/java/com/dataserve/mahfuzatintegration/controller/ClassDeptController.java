package com.dataserve.mahfuzatintegration.controller;

import java.util.List;

import com.dataserve.mahfuzatintegration.exception.APIResponseResult;
import com.dataserve.mahfuzatintegration.model.ClassDept;
import com.dataserve.mahfuzatintegration.service.ClassDeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClassDeptController {

    @Autowired
    private ClassDeptService classDeptService;

    @GetMapping("/classDept")
    public ResponseEntity<APIResponseResult<List<ClassDept>>> getAllDepartments() {
        List<ClassDept> result = classDeptService.findAll();
        APIResponseResult<List<ClassDept>> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "All departments retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }
}
