package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.ClassDept;

public interface ClassDeptService {

    List<ClassDept> findAll();

    Optional<ClassDept> findById(Long theId);


}
