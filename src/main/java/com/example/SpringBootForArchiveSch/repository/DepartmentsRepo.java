package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {


}
