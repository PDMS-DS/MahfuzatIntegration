package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Audit;
import com.example.SpringBootForArchiveSch.model.Departments;
import com.example.SpringBootForArchiveSch.repository.DepartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DepartmentsServiceImpl implements DepartmentsService{

    private DepartmentsRepo departmentsRepo ;

    @Autowired
    public DepartmentsServiceImpl(DepartmentsRepo departmentsRepo) {
        this.departmentsRepo = departmentsRepo;
    }

    @Override
    public List<Departments> findAll() {
        return null;
    }

    @Override
    public Optional<Departments> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Departments save(Departments theDepartments) {
        return null;
    }

    @Override
    public void deleteById(Audit theDepartments) {

    }
}
