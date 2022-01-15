package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.ActionTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionTypesRepository extends JpaRepository<ActionTypes, Long> {
}
