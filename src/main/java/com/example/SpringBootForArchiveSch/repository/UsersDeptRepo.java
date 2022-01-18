package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Classifications;
import com.example.SpringBootForArchiveSch.model.UsersDept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDeptRepo extends JpaRepository<UsersDept, Long> {
}
