package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.ClassDept;

public interface ClassDeptService {

    public List<ClassDept> findAll();

    public Optional<ClassDept> findById(Long theId);


}
