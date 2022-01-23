package com.example.SpringBootForArchiveSch.service;



import com.example.SpringBootForArchiveSch.model.StorageCenter;
import com.example.SpringBootForArchiveSch.model.dto.StorageCenterDto;

import java.util.List;
import java.util.Optional;

public interface StorageCenterService {

    public List<StorageCenter> findAll();

    public Optional<StorageCenter> findById(Long theId);

    public StorageCenterDto save(StorageCenter theStorageCenterService);

    public void deleteById(StorageCenter theStorageCenterService);
}
