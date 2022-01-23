package com.example.SpringBootForArchiveSch.controller;


import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.Line;
import com.example.SpringBootForArchiveSch.model.Shelf;
import com.example.SpringBootForArchiveSch.service.LineService;
import com.example.SpringBootForArchiveSch.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ShelfController {



    @Autowired
    private ShelfService shelfService;

    @Autowired
    private LineService lineService;

    @GetMapping("/shelf")
    public List<Shelf> getAllShelves() {
        return shelfService.findAll();
    }

    @GetMapping("/shelf/{id}")
    public ResponseEntity<Shelf> getShelfById(@PathVariable(value = "id") Long shelfId)
            throws ResourceNotFoundException {
        Shelf shelf = shelfService.findById(shelfId)
                .orElseThrow(() -> new ResourceNotFoundException("shelf not found for this id :: " + shelfId));
        return ResponseEntity.ok().body(shelf);
    }

    @PostMapping("/shelf/{lineId}")
    public ResponseEntity<Shelf> createShelf(@PathVariable(value = "lineId") Long lineId , @Valid @RequestBody Shelf shelf) throws ResourceNotFoundException {
        if(lineId != null ) {
            Line line = lineService.findById(lineId)
                    .orElseThrow(() -> new ResourceNotFoundException("Line not found for this id :: " + lineId));
            shelf.setLine(line);

        }
        return ResponseEntity.ok().body(shelfService.save(shelf));
    }


    @PutMapping("/shelf/{id}")
    public ResponseEntity<Shelf> updateShelf(@PathVariable(value = "id") Long shelfId,
                                                         @Valid @RequestBody Shelf shelfDetails) throws ResourceNotFoundException {
        Shelf shelf = shelfService.findById(shelfId)
                .orElseThrow(() -> new ResourceNotFoundException("Shelf not found for this id :: " + shelfId));
        shelf.setNameAr(shelfDetails.getNameAr());
        shelf.setNameEn(shelfDetails.getNameEn());
        shelf.setCapacity(shelfDetails.getCapacity());
        shelf.setSerial(shelfDetails.getSerial());
        final Shelf updatedShelf = shelfService.save(shelf);
        return ResponseEntity.ok(updatedShelf);
    }

    @DeleteMapping("/shelf/{id}")
    public ResponseEntity deleteShelf(@PathVariable(value = "id") Long shelfId)
            throws ResourceNotFoundException {
        Shelf shelf = shelfService.findById(shelfId)
                .orElseThrow(() -> new ResourceNotFoundException("Shelf not found for this id :: " + shelfId));
        shelfService.deleteById(shelf);
        return ResponseEntity.noContent().build();
    }
}
