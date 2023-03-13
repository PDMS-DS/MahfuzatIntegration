package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Audit;

public interface AuditService {
    public List<Audit> findAll();

    public Optional<Audit> findById(Long theId);

    public Audit save(Audit theAudit);

    public void deleteById(Audit theAudit);
}
