package com.example.SpringBootForArchiveSch.controller;


import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.Box;
import com.example.SpringBootForArchiveSch.model.Shelf;
import com.example.SpringBootForArchiveSch.service.BoxService;
import com.example.SpringBootForArchiveSch.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoxController {

    @Autowired
    private BoxService boxService;

    @Autowired
    private ShelfService shelfService;

    @GetMapping("/box")
    public List<Box> getAllBox() {
        return boxService.findAll();
    }

    @GetMapping("/box/{id}")
    public ResponseEntity<Box> getBoxById(@PathVariable(value = "id") Long boxId)
            throws ResourceNotFoundException {
        Box box = boxService.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("ActionType not found for this id :: " + boxId));
        return ResponseEntity.ok().body(box);
    }

    @PostMapping("/box/{shelfId}")
    public ResponseEntity<Box> createBox(@PathVariable(value = "shelfId") Long shelfId
            ,@Valid @RequestBody Box box) throws ResourceNotFoundException{
        Shelf shelf = shelfService.findById(shelfId)
                .orElseThrow(() -> new ResourceNotFoundException("Shelf not found for this id :: " + shelfId));;
        box.setShelf(shelf);
        return ResponseEntity.ok().body(boxService.save(box));
    }


    @PutMapping("/box/{id}")
    public ResponseEntity<Box> updateBox(@PathVariable(value = "id") Long boxId,
                                                         @Valid @RequestBody Box boxDetails) throws ResourceNotFoundException {
        Box box = boxService.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Box not found for this id :: " + boxId));

        box.setNameAr(boxDetails.getNameAr());
        box.setNameEn(boxDetails.getNameEn());
//        box.setShelf(boxDetails.getShelf().getShelfId());
        box.setBoxTypeId(boxDetails.getBoxTypeId());
        box.setCapacity(boxDetails.getCapacity());
        box.setSerial(boxDetails.getSerial());
        box.setDate(boxDetails.getDate());

        final Box updatedBox = boxService.save(box);
        return ResponseEntity.ok(updatedBox);
    }

    @DeleteMapping("/box/{id}")
    public ResponseEntity deleteActionType(@PathVariable(value = "id") Long boxId)
            throws ResourceNotFoundException {
        Box box = boxService.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("box not found for this id :: " + boxId));
        boxService.deleteById(box);
        return ResponseEntity.noContent().build();
    }
}
