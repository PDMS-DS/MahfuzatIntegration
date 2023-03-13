package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.StorageCenter;
import com.dataserve.archivemanagement.model.dto.StorageCenterDto;
import com.dataserve.archivemanagement.repository.StorageCenterRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public StorageCenterDto save(StorageCenter theStorage) {
        storageCenterRepo.save(theStorage);
        StorageCenterDto storageCenterDto = new StorageCenterDto();
        storageCenterDto.setCenterId(theStorage.getCenterId());
        storageCenterDto.setNameEn(theStorage.getNameEn());
        storageCenterDto.setNameAr(theStorage.getNameAr());
        storageCenterDto.setActive(theStorage.isActive());
        return storageCenterDto;
    }

    @Override
    public void deleteById(StorageCenter theStorage) {
        storageCenterRepo.delete(theStorage);
    }
}
