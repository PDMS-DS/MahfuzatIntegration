package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.ClassDept;

import java.util.List;
import java.util.Optional;

public interface ClassDeptService {

    public List<ClassDept> findAll();

    public Optional<ClassDept> findById(Long theId);

    public ClassDept save(ClassDept theClassDept);

    public void deleteById(ClassDept theClassDept);
}
