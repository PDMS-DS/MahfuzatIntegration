package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.StorageCenter;
import com.example.SpringBootForArchiveSch.repository.StorageCenterTypeRepo;
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
    public List<StorageCenterTypeService> findAll() {
        return null;
    }

    @Override
    public Optional<StorageCenterTypeService> findById(Long theStorageCenterTypeService) {
        return Optional.empty();
    }

    @Override
    public StorageCenterTypeService save(StorageCenterTypeService theStorageCenterTypeService) {
        return null;
    }

    @Override
    public void deleteById(StorageCenterTypeService theStorageCenterTypeService) {

    }
}
