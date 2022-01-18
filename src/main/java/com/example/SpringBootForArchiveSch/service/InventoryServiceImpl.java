package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Inventory;
import com.example.SpringBootForArchiveSch.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InventoryServiceImpl implements InventoryService{

    private  InventoryRepo inventoryRepo;

    @Autowired
    public InventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }


    @Override
    public List<Inventory> findAll() {
        return null;
    }

    @Override
    public Optional<Inventory> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Inventory save(Inventory theGroupInventory) {
        return null;
    }

    @Override
    public void deleteById(Inventory theGroupInventory) {

    }
}
