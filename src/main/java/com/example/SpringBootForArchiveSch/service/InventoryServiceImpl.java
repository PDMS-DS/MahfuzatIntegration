package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Inventory;
import com.example.SpringBootForArchiveSch.model.dto.InventoryDto;
import com.example.SpringBootForArchiveSch.model.dto.LineDto;
import com.example.SpringBootForArchiveSch.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

    private  InventoryRepo inventoryRepo;

    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }


    @Override
    public List<Inventory> findAll() {
        return inventoryRepo.findAll();
    }

    @Override
    public Optional<Inventory> findById(Long theId) {
        return inventoryRepo.findById(theId);
    }

    @Override
    public InventoryDto save(Inventory theInventory) {

        inventoryRepo.save(theInventory);
        InventoryDto lineDto = new InventoryDto();
        lineDto.setInventoryId(theInventory.getInventoryId());
        lineDto.setCapacity(theInventory.getCapacity());
        lineDto.setNameAr(theInventory.getNameAr());
        lineDto.setNameEr(theInventory.getNameEr());
        lineDto.setSerial(theInventory.getSerial());
        return lineDto;
    }

    @Override
    public void deleteById(Inventory theInventory) {
        inventoryRepo.delete(theInventory);
    }
}
