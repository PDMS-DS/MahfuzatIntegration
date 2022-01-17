package com.example.SpringBootForArchiveSch.service;



import com.example.SpringBootForArchiveSch.model.Permissions;

import java.util.List;
import java.util.Optional;

public interface PermissionsService {
    public List<Permissions> findAll();

    public Optional<Permissions> findById(Long theId);

    public Permissions save(Permissions theEmployee);

    public void deleteById(Permissions theId);
}
