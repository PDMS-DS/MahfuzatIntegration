package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.ClassDept;
import com.dataserve.archivemanagement.repository.ClassDeptRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassDeptServiceImpl implements ClassDeptService {
    @Autowired
    private ClassDeptRepo classDeptRepo;

    @Override
    public List<ClassDept> findAll() {
        return classDeptRepo.listClassDept();
    }

    @Override
    public Optional<ClassDept> findById(Long theId) {
        return Optional.empty();
    }


}
