package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.PrintAudit;

public interface PrintAuditRepo extends JpaRepository<PrintAudit, Long> {
}
