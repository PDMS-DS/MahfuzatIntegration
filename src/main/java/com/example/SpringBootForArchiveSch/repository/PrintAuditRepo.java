package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.PrintAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintAuditRepo extends JpaRepository<PrintAudit, Long> {
}
