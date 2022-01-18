package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.StorageCenter;

import com.example.SpringBootForArchiveSch.repository.StorageCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StorageCenterServiceImpl implements StorageCenterService{

    private final StorageCenterRepo storageCenterRepo;

    @Autowired
    public StorageCenterServiceImpl(StorageCenterRepo storageCenterRepo) {
        this.storageCenterRepo = storageCenterRepo;
    }

    @Override
    public List<StorageCenter> findAll() {
        return storageCenterRepo.findAll();
    }

    @Override
    public Optional<StorageCenter> findById(Long theId) {
        return storageCenterRepo.findById(theId);
    }

    @Override
    public StorageCenter save(StorageCenter theStorage) {
        storageCenterRepo.save(theStorage);
        return theStorage;
    }

    @Override
    public void deleteById(StorageCenter theStorage) {
        storageCenterRepo.delete(theStorage);
    }
}
