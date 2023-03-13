package com.dataserve.archivemanagement.service;



import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Permissions;

public interface PermissionsService {
    public List<Permissions> findAll();

    public Optional<Permissions> findById(Long theId);

    public Permissions save(Permissions theEmployee);

    public void deleteById(Permissions theId);
}
