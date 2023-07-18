package com.dataserve.archivemanagement.repository;

import com.dataserve.archivemanagement.model.Audit;
import com.dataserve.archivemanagement.model.DMSAudit;
import com.dataserve.archivemanagement.model.DmsFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmsAuditRepository extends JpaRepository<DMSAudit, Long> {


}
