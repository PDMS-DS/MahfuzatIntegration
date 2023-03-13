package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Line;

public interface PrintAuditService {

    public List<PrintAuditService> findAll();

    public Optional<PrintAuditService> findById(Long thePrintAuditService);

    public PrintAuditService save(PrintAuditService thePrintAuditService);

    public void deleteById(PrintAuditService thePrintAuditService);
}
