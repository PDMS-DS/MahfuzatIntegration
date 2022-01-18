package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.GroupPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupPermissionsRepo extends JpaRepository<GroupPermissions, Long> {


}
