package com.dataserve.archivemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.dataserve.archivemanagement.model.UsersGroups;

import java.util.List;
import java.util.Optional;

public class UsersGroupsServiceImpl implements UsersGroupsService{

    private UsersGroupsService usersGroupService;

    @Autowired
    public UsersGroupsServiceImpl(UsersGroupsService usersGroupService) {
        this.usersGroupService = usersGroupService;
    }

    @Override
    public List<UsersGroups> findAll() {
        return null;
    }

    @Override
    public Optional<UsersGroups> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public UsersGroups save(UsersGroups theUsersGroups) {
        return null;
    }

    @Override
    public void deleteById(UsersGroups theUsersGroups) {

    }
}
