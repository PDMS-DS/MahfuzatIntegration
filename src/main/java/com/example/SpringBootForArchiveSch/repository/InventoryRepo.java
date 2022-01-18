package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

}
