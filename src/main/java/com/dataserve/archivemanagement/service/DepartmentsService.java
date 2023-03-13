package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Audit;
import com.dataserve.archivemanagement.model.Departments;
import com.dataserve.archivemanagement.model.dto.DepartmentsDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentsService {

    public List<Departments> findAll();

    public Optional<Departments> findById(Long theId);

    public DepartmentsDto save(Departments theDepartments);

    public void deleteById(Departments theDepartments);
}
