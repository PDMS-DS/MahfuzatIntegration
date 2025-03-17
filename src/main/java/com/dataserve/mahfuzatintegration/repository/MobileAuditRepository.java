package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.MobileAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAuditRepository extends JpaRepository<MobileAudit, Long> {


}
