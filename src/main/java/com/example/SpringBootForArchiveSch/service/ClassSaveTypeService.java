package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.ClassSaveType;

import java.util.List;
import java.util.Optional;

public interface ClassSaveTypeService {

    public List<ClassSaveType> findAll();

    public Optional<ClassSaveType> findById(Long theId);

    public ClassSaveType save(ClassSaveType theClassSaveType);

    public void deleteById(ClassSaveType theClassSaveType);
}
