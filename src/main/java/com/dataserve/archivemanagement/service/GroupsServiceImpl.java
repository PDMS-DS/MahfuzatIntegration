package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.repository.GroupsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupsServiceImpl implements GroupsService{
    private final GroupsRepo groupsRepo;

    @Autowired
    public GroupsServiceImpl(GroupsRepo groupsRepo) {
        this.groupsRepo = groupsRepo;
    }

    @Override
    public List<Groups> findAll() {
        return groupsRepo.findAll();
    }

    @Override
    public Optional<Groups> findById(Long theId) {
        return groupsRepo.findById(theId);
    }

    @Override
    public Groups save(Groups theGroup) {
        groupsRepo.save(theGroup);
        return theGroup;
    }

    @Override
    public void deleteById(Groups theGroup) {
        groupsRepo.delete(theGroup);
    }
}
