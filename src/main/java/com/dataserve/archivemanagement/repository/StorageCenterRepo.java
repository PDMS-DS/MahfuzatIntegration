package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.StorageCenter;

public interface StorageCenterRepo extends JpaRepository<StorageCenter, Long> {
}
