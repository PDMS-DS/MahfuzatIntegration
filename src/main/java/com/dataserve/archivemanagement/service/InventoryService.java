package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Inventory;
import com.dataserve.archivemanagement.model.dto.InventoryDto;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    public List<Inventory> findAll();

    public Optional<Inventory> findById(Long theId);

    public InventoryDto save(Inventory theInventory);

    public void deleteById(Inventory theInventory);
}
