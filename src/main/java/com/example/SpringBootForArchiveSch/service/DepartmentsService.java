package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Audit;
import com.example.SpringBootForArchiveSch.model.Departments;

import java.util.List;
import java.util.Optional;

public interface DepartmentsService {

    public List<Departments> findAll();

    public Optional<Departments> findById(Long theId);

    public Departments save(Departments theDepartments);

    public void deleteById(Audit theDepartments);
}
