package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Inventory;
import com.example.SpringBootForArchiveSch.model.dto.InventoryDto;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    public List<Inventory> findAll();

    public Optional<Inventory> findById(Long theId);

    public InventoryDto save(Inventory theInventory);

    public void deleteById(Inventory theInventory);
}
