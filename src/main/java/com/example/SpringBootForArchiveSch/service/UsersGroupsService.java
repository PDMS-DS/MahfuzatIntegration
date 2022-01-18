package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.UsersGroups;

import java.util.List;
import java.util.Optional;

public interface UsersGroupsService {

    public List<UsersGroups> findAll();

    public Optional<UsersGroups> findById(Long theId);

    public UsersGroups save(UsersGroups theUsersGroups);

    public void deleteById(UsersGroups theUsersGroups);
}
