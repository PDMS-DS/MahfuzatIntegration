package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.DMSPropertiesAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DMSPropertyAuditRepository extends JpaRepository<DMSPropertiesAudit, Long> {


}
