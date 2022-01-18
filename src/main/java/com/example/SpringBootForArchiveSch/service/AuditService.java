package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Audit;

import java.util.List;
import java.util.Optional;

public interface AuditService {
    public List<Audit> findAll();

    public Optional<Audit> findById(Long theId);

    public Audit save(Audit theAudit);

    public void deleteById(Audit theAudit);
}
