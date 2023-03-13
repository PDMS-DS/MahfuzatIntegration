package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Permissions;

@Repository
public interface PermissionsRepo extends JpaRepository<Permissions,Long> {
}
