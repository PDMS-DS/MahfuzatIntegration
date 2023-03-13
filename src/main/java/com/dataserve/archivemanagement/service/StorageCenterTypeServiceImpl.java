package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.StorageCenter;
import com.dataserve.archivemanagement.model.StorageCenterType;
import com.dataserve.archivemanagement.repository.StorageCenterTypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCenterTypeServiceImpl implements StorageCenterTypeService{

    private StorageCenterTypeRepo storageCenterTypeRepo ;

    @Autowired
    public StorageCenterTypeServiceImpl(StorageCenterTypeRepo storageCenterTypeRepo) {
        this.storageCenterTypeRepo = storageCenterTypeRepo;
    }

    @Override
    public List<StorageCenterType> findAll() {
        return storageCenterTypeRepo.findAll();
    }

    @Override
    public Optional<StorageCenterType> findById(Long theStorageCenterTypeId) {
        return storageCenterTypeRepo.findById(theStorageCenterTypeId);
    }

    @Override
    public StorageCenterType save(StorageCenterType theStorageCenterTypeId) {
        storageCenterTypeRepo.save(theStorageCenterTypeId);
        return theStorageCenterTypeId;
    }

    @Override
    public void deleteById(StorageCenterType theStorageCenterTypeId) {
        storageCenterTypeRepo.delete(theStorageCenterTypeId);
    }
}
