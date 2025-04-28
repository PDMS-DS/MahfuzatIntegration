package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.DmsIntegrationFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DmsIntegrationFilesRepository extends JpaRepository<DmsIntegrationFiles, Long> {

    Long countByTransactionId(Long transactionId);

    DmsIntegrationFiles findByIntegrationDocumentId(String integrationDocumentId);

    @Query("SELECT COUNT(d) FROM DmsIntegrationFiles d")
    Long countRows();

}
