package com.dataserve.archivemanagement.repository;


import com.dataserve.archivemanagement.model.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepo extends JpaRepository<Permissions, Long> {

    Set<Permissions> findByGroupsGroupIdIn(List<Long> ids);
    Set<Permissions> findByGroupsGroupIdInAndModuleModuleNameEnContaining(List<Long> ids, String moduleNameEn);

}
