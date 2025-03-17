package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.EDSChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EDSChoicesRepository extends JpaRepository<EDSChoices, String> {



    List<EDSChoices> findByCompositeKeyClassSymbolicName(String symbolicName);

}
