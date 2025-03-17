package com.dataserve.mahfuzatintegration.repository;


import com.dataserve.mahfuzatintegration.model.MobileAuditParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAuditParamRepository extends JpaRepository<MobileAuditParameter, Long> {


}
