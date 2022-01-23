package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;

import com.example.SpringBootForArchiveSch.model.Inventory;
import com.example.SpringBootForArchiveSch.model.Line;
import com.example.SpringBootForArchiveSch.model.dto.LineDto;
import com.example.SpringBootForArchiveSch.service.InventoryService;
import com.example.SpringBootForArchiveSch.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class LineController {

    @Autowired
    private LineService lineService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/line")
    public List<Line> getAllLine() {
        return lineService.findAll();
    }

    @GetMapping("/line/{id}")
    public ResponseEntity<Line> getLineById(@PathVariable(value = "id") Long lineId)
            throws ResourceNotFoundException {
        Line line = lineService.findById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found for this id :: " + lineId));
        return ResponseEntity.ok().body(line);
    }

    @PostMapping("/line/{inventoryId}")
    public ResponseEntity<LineDto> createLine(@PathVariable(value = "inventoryId") Long inventoryId
            , @Valid @RequestBody Line line) throws ResourceNotFoundException{
        if(inventoryId != null ) {
            Inventory inventory = inventoryService.findById(inventoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("inventory not found for this id :: " + inventoryId));
            line.setInventory(inventory);

        }
        return ResponseEntity.ok().body(lineService.save(line));
    }


    @PutMapping("/line/{id}")
    public ResponseEntity<LineDto> updateLine(@PathVariable(value = "id") Long lineId,
                                            @Valid @RequestBody Line lineDetails) throws ResourceNotFoundException {
        Line line = lineService.findById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found for this id :: " + lineId));

        line.setNameAr(lineDetails.getNameAr());
        line.setNameEn(lineDetails.getNameEn());
        line.setCapacity(lineDetails.getCapacity());
        line.setSerial(lineDetails.getSerial());
        final LineDto updatedLine = lineService.save(line);
        return ResponseEntity.ok(updatedLine);
    }

    @DeleteMapping("/line/{id}")
    public ResponseEntity deleteLine(@PathVariable(value = "id") Long lineId)
            throws ResourceNotFoundException {
        Line line = lineService.findById(lineId)
                .orElseThrow(() -> new ResourceNotFoundException("line not found for this id :: " + lineId));
        lineService.deleteById(line);
        return ResponseEntity.noContent().build();
    }
}
