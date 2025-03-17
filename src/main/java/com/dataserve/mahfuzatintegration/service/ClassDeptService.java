package com.dataserve.mahfuzatintegration.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.mahfuzatintegration.model.ClassDept;

public interface ClassDeptService {

    List<ClassDept> findAll();

    Optional<ClassDept> findById(Long theId);


}
