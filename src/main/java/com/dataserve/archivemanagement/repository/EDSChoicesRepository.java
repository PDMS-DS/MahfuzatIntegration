package com.dataserve.archivemanagement.repository;

import com.dataserve.archivemanagement.model.EDSChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDSChoicesRepository extends JpaRepository<EDSChoices, String> {

    List<EDSChoices> findByClassSymbolicName(String objectType);

}
