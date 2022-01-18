package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.GroupPermissions;
import com.example.SpringBootForArchiveSch.repository.GroupPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GroupPermissionsServiceImpl implements GroupPermissionsService{

    private GroupPermissionsRepo groupPermissionsRepo ;

    @Autowired
    public GroupPermissionsServiceImpl(GroupPermissionsRepo groupPermissionsRepo) {
        this.groupPermissionsRepo = groupPermissionsRepo;
    }

    @Override
    public List<GroupPermissions> findAll() {
        return null;
    }

    @Override
    public Optional<GroupPermissions> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public GroupPermissions save(GroupPermissions theGroupPermissions) {
        return null;
    }

    @Override
    public void deleteById(GroupPermissions theGroupPermissions) {

    }
}
