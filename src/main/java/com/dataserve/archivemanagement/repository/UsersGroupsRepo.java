package com.dataserve.archivemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.UsersGroups;

public interface UsersGroupsRepo extends JpaRepository<UsersGroups, Long> {
}
