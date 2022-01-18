package com.example.SpringBootForArchiveSch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringBootForArchiveSch.model.StorageCenter;

public interface StorageCenterRepo extends JpaRepository<StorageCenter, Long> {
}
