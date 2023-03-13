package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.UsersGroups;

public interface UsersGroupsService {

    public List<UsersGroups> findAll();

    public Optional<UsersGroups> findById(Long theId);

    public UsersGroups save(UsersGroups theUsersGroups);

    public void deleteById(UsersGroups theUsersGroups);
}
