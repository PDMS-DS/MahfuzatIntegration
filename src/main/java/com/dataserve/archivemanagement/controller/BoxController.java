package com.dataserve.archivemanagement.controller;


import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.BoxType;
import com.dataserve.archivemanagement.model.Shelf;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.service.BoxService;
import com.dataserve.archivemanagement.service.BoxTypeService;
import com.dataserve.archivemanagement.service.ShelfService;

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

    @Autowired
    private BoxTypeService boxTypeService;

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

    @PostMapping("/box/{shelfId}/{boxTypeId}")
    public ResponseEntity<BoxDto> createBox(@PathVariable(value = "shelfId") Long shelfId , @PathVariable(value = "boxTypeId") Long boxTypeId
            , @Valid @RequestBody Box box) throws ResourceNotFoundException{
        if(boxTypeId != null ) {
            BoxType boxType = boxTypeService.findById(boxTypeId)
                    .orElseThrow(() -> new ResourceNotFoundException("boxTypeId not found for this id :: " + boxTypeId));
            box.setBoxType(boxType);

        }
        if(shelfId != null) {
            Shelf shelf = shelfService.findById(shelfId)
                    .orElseThrow(() -> new ResourceNotFoundException("Shelf not found for this id :: " + shelfId));
            box.setShelf(shelf);

        }
        return ResponseEntity.ok().body(boxService.save(box));
    }


    @PutMapping("/box/{id}")
    public ResponseEntity<BoxDto> updateBox(@PathVariable(value = "id") Long boxId,
                                                         @Valid @RequestBody Box boxDetails) throws ResourceNotFoundException {
        Box box = boxService.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Box not found for this id :: " + boxId));

        box.setNameAr(boxDetails.getNameAr());
        box.setNameEn(boxDetails.getNameEn());
        box.setCapacity(boxDetails.getCapacity());
        box.setSerial(boxDetails.getSerial());
        box.setDate(boxDetails.getDate());

        final BoxDto updatedBox = boxService.save(box);
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
