package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.GroupPermissions;

import java.util.List;
import java.util.Optional;

public interface GroupPermissionsService {

    public List<GroupPermissions> findAll();

    public Optional<GroupPermissions> findById(Long theId);

    public GroupPermissions save(GroupPermissions theGroupPermissions);

    public void deleteById(GroupPermissions theGroupPermissions);
}
