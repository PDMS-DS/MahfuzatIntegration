package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.StorageCenterType;

import java.util.List;
import java.util.Optional;

public interface StorageCenterTypeService {

    public List<StorageCenterType> findAll();

    public Optional<StorageCenterType> findById(Long theStorageCenterTypeId);

    public StorageCenterType save(StorageCenterType theStorageCenterTypeId);

    public void deleteById(StorageCenterType theStorageCenterTypeId);
}
