package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.StorageCenterType;

public interface StorageCenterTypeRepo extends JpaRepository<StorageCenterType, Long> {
}
