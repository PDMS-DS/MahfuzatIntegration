package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Line;

import java.util.List;
import java.util.Optional;

public interface PrintAuditService {

    public List<PrintAuditService> findAll();

    public Optional<PrintAuditService> findById(Long thePrintAuditService);

    public PrintAuditService save(PrintAuditService thePrintAuditService);

    public void deleteById(PrintAuditService thePrintAuditService);
}
