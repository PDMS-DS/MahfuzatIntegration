package com.dataserve.archivemanagement.repository;

import com.dataserve.archivemanagement.model.MobileAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAuditRepository extends JpaRepository<MobileAudit, Long> {


}
