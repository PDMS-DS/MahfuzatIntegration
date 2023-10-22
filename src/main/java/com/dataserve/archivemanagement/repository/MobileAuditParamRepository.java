package com.dataserve.archivemanagement.repository;


import com.dataserve.archivemanagement.model.MobileAuditParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAuditParamRepository extends JpaRepository<MobileAuditParameter, Long> {


}
