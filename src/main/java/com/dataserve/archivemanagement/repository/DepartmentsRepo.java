package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Departments;

import java.util.List;
import java.util.Optional;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {


}
