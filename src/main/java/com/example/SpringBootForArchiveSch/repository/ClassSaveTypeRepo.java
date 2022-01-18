package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.ClassSaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassSaveTypeRepo extends JpaRepository<ClassSaveType, Long> {

}
