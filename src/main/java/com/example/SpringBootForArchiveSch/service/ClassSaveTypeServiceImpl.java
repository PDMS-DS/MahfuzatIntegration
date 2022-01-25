package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.ClassDept;
import com.example.SpringBootForArchiveSch.repository.ClassDeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassSaveTypeServiceImpl implements ClassDeptService{

    private ClassDeptRepo classDeptRepo ;

    @Autowired
    public ClassSaveTypeServiceImpl(ClassDeptRepo classDeptRepo) {
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
