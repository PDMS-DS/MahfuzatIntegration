package com.example.SpringBootForArchiveSch.service;

import java.util.List;
import java.util.Optional;

public interface StorageCenterTypeService {

    public List<StorageCenterTypeService> findAll();

    public Optional<StorageCenterTypeService> findById(Long theStorageCenterTypeService);

    public StorageCenterTypeService save(StorageCenterTypeService theStorageCenterTypeService);

    public void deleteById(StorageCenterTypeService theStorageCenterTypeService);
}
