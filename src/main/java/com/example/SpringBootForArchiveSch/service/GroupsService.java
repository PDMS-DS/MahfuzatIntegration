package com.example.SpringBootForArchiveSch.service;



import com.example.SpringBootForArchiveSch.model.Groups;

import java.util.List;
import java.util.Optional;

public interface GroupsService {
    public List<Groups> findAll();

    public Optional<Groups> findById(Long theId);

    public Groups save(Groups theGroup);

    public void deleteById(Groups theGroup);
}
