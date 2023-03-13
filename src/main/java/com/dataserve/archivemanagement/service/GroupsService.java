package com.dataserve.archivemanagement.service;



import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Groups;

public interface GroupsService {
    public List<Groups> findAll();

    public Optional<Groups> findById(Long theId);

    public Groups save(Groups theGroup);

    public void deleteById(Groups theGroup);
}
