package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.StorageCenterType;

public interface StorageCenterTypeService {

    public List<StorageCenterType> findAll();

    public Optional<StorageCenterType> findById(Long theStorageCenterTypeId);

    public StorageCenterType save(StorageCenterType theStorageCenterTypeId);

    public void deleteById(StorageCenterType theStorageCenterTypeId);
}
