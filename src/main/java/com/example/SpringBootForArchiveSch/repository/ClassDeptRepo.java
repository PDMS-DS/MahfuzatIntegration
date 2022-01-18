package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.ClassDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassDeptRepo extends JpaRepository<ClassDept, Long> {


}
