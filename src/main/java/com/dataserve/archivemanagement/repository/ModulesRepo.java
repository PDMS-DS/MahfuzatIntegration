package com.dataserve.archivemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Module;

@Repository
public interface ModulesRepo extends JpaRepository<Module,Long> {

}
