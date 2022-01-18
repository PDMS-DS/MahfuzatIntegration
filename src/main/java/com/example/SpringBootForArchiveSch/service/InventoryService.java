package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    public List<Inventory> findAll();

    public Optional<Inventory> findById(Long theId);

    public Inventory save(Inventory theGroupInventory);

    public void deleteById(Inventory theGroupInventory);
}
