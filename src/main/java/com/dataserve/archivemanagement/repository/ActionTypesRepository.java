package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.ActionTypes;

@Repository
public interface ActionTypesRepository extends JpaRepository<ActionTypes, Long> {
}
