package com.dataserve.archivemanagement.repository;

import com.dataserve.archivemanagement.model.DMSPropertiesAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DMSPropertyAuditRepository extends JpaRepository<DMSPropertiesAudit, Long> {


}
