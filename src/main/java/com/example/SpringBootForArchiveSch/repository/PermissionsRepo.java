package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepo extends JpaRepository<Permissions,Long> {
}
