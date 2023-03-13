package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Inventory;
import com.dataserve.archivemanagement.model.StorageCenter;
import com.dataserve.archivemanagement.model.dto.InventoryDto;
import com.dataserve.archivemanagement.service.InventoryService;
import com.dataserve.archivemanagement.service.StorageCenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/physicalArchive")
public class InventoryController {

    @Autowired
    private StorageCenterService StorageCenterService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return inventoryService.findAll();
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable(value = "id") Long inventoryId)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryService.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " + inventoryId));
        return ResponseEntity.ok().body(inventory);
    }

    @PostMapping("/inventory/{storageCenterId}")
    public ResponseEntity<InventoryDto> createInventory(@PathVariable(value = "storageCenterId") Long storageCenterId
            , @Valid @RequestBody Inventory inventory) throws ResourceNotFoundException{
        if(storageCenterId != null ) {
            StorageCenter storageCenter = StorageCenterService.findById(storageCenterId)
                    .orElseThrow(() -> new ResourceNotFoundException("storageCenter not found for this id :: " + storageCenterId));
            inventory.setStorageCenter(storageCenter);

        }
        return ResponseEntity.ok().body(inventoryService.save(inventory));
    }


    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryDto> updateLine(@PathVariable(value = "id") Long lineId,
                                              @Valid @RequestBody Inventory inventoryDetails) throws ResourceNotFoundException {
        Inventory inventory = inventoryService.findById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " + lineId));

        inventory.setNameAr(inventoryDetails.getNameAr());
        inventory.setNameEr(inventoryDetails.getNameEr());
        inventory.setCapacity(inventoryDetails.getCapacity());
        inventory.setSerial(inventoryDetails.getSerial());
        final InventoryDto updatedInventoryDto = inventoryService.save(inventory);
        return ResponseEntity.ok(updatedInventoryDto);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity deleteInventory(@PathVariable(value = "id") Long inventoryId)
            throws ResourceNotFoundException {
        Inventory inventory = inventoryService.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " + inventoryId));
        inventoryService.deleteById(inventory);
        return ResponseEntity.noContent().build();
    }
}
