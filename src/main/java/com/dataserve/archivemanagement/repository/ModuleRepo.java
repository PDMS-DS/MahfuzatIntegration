package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleRepo extends JpaRepository<Module, Long> {


}
