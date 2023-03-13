package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.ClassDept;
import com.dataserve.archivemanagement.repository.ClassDeptRepo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClassDeptServiceImpl implements ClassDeptService{

    private ClassDeptRepo classDeptRepo ;

    @Autowired
    public ClassDeptServiceImpl(ClassDeptRepo classDeptRepo) {
        this.classDeptRepo = classDeptRepo;
    }


    @Override
    public List<ClassDept> findAll() {
        return null;
    }

    @Override
    public Optional<ClassDept> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public ClassDept save(ClassDept theClassDept) {
        return null;
    }

    @Override
    public void deleteById(ClassDept theClassDept) {

    }
}
