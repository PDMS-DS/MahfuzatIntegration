package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.ClassDept;
import com.dataserve.archivemanagement.repository.ClassDeptRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassDeptServiceImpl implements ClassDeptService{

    private final ClassDeptRepo classDeptRepo ;

    @Override
    public List<ClassDept> findAll() {
        return classDeptRepo.listClassDept();
    }

    @Override
    public Optional<ClassDept> findById(Long theId) {
        return Optional.empty();
    }

    
}
