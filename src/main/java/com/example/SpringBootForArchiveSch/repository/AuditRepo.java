package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditRepo extends JpaRepository<Audit, Long> {

}
