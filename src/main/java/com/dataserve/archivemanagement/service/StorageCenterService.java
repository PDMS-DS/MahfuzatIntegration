package com.dataserve.archivemanagement.service;



import com.dataserve.archivemanagement.model.StorageCenter;
import com.dataserve.archivemanagement.model.dto.StorageCenterDto;

import java.util.List;
import java.util.Optional;

public interface StorageCenterService {

    public List<StorageCenter> findAll();

    public Optional<StorageCenter> findById(Long theId);

    public StorageCenterDto save(StorageCenter theStorageCenterService);

    public void deleteById(StorageCenter theStorageCenterService);
}
