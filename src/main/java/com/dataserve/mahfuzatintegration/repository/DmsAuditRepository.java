package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.DMSAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmsAuditRepository extends JpaRepository<DMSAudit, Long> {


}
