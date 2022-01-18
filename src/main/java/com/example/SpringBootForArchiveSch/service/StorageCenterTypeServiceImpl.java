package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.StorageCenter;
import com.example.SpringBootForArchiveSch.repository.StorageCenterTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StorageCenterTypeServiceImpl implements StorageCenterService{

    private StorageCenterTypeRepo storageCenterTypeRepo ;

    @Autowired
    public StorageCenterTypeServiceImpl(StorageCenterTypeRepo storageCenterTypeRepo) {
        this.storageCenterTypeRepo = storageCenterTypeRepo;
    }

    @Override
    public List<StorageCenter> findAll() {
        return null;
    }

    @Override
    public Optional<StorageCenter> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public StorageCenter save(StorageCenter theStorageCenterService) {
        return null;
    }

    @Override
    public void deleteById(StorageCenter theStorageCenterService) {

    }
}
