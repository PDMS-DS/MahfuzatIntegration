package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Audit;
import com.example.SpringBootForArchiveSch.repository.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditServiceImpl implements AuditService{

    private AuditRepo auditRepo ;

    @Autowired
    public AuditServiceImpl( AuditRepo theAuditRepo) {
        this.auditRepo = theAuditRepo;
    }

    @Override
    public List<Audit> findAll() {
        return null;
    }

    @Override
    public Optional<Audit> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Audit save(Audit theAudit) {
        return null;
    }

    @Override
    public void deleteById(Audit theAudit) {

    }
}
