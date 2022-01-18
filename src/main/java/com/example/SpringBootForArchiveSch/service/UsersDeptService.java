package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.UsersDept;

import java.util.List;
import java.util.Optional;

public interface UsersDeptService {

    public List<UsersDept> findAll();

    public Optional<UsersDept> findById(Long theId);

    public UsersDept save(UsersDept theUsersDept);

    public void deleteById(UsersDept theUsersDept);
}
