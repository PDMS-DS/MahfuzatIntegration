package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Permissions;
import com.dataserve.archivemanagement.repository.PermissionsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionsServiceImpl  implements PermissionsService{
    private final PermissionsRepo permissionsRepo ;

    @Autowired
    public PermissionsServiceImpl( PermissionsRepo thePermissionsRepo) {
        permissionsRepo = thePermissionsRepo;
    }


    @Override
    public List<Permissions> findAll() {
        return permissionsRepo.findAll();
    }

    public Optional<Permissions> findById(Long theId) {
        return permissionsRepo.findById(theId);
    }

    @Override
    public Permissions save(Permissions theActionType) {
        permissionsRepo.save(theActionType);
        return theActionType;
    }

    @Override
    public void deleteById(Permissions theActionType) {
        permissionsRepo.delete(theActionType);
    }
}
