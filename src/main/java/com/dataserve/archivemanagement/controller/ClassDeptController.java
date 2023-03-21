package com.dataserve.archivemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.model.ClassDept;
import com.dataserve.archivemanagement.service.BoxService;
import com.dataserve.archivemanagement.service.ClassDeptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physicalArchive")
@RequiredArgsConstructor
public class ClassDeptController {

	private final ClassDeptService classDeptService  ;
	
	
	@GetMapping("/classDept")
    public List<ClassDept> getAllDepartments() {
        return classDeptService.findAll();
    }

}
