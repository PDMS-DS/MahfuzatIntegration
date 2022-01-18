package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.StorageCenterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageCenterTypeRepo extends JpaRepository<StorageCenterType, Long> {
}
