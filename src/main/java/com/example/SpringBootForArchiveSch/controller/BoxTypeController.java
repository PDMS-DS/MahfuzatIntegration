package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.BoxType;
import com.example.SpringBootForArchiveSch.service.BoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoxTypeController {

    @Autowired
    private BoxTypeService boxTypeService;

    @GetMapping("/boxType")
    public List<BoxType> getAllShelves() {
        return boxTypeService.findAll();
    }

    @GetMapping("/boxType/{id}")
    public ResponseEntity<BoxType> getBoxTypeId(@PathVariable(value = "id") Long boxTypeId)
            throws ResourceNotFoundException {
        BoxType boxType = boxTypeService.findById(boxTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("boxType not found for this id :: " + boxTypeId));
        return ResponseEntity.ok().body(boxType);
    }

    @PostMapping("/boxType")
    public ResponseEntity<BoxType> createBoxType(@Valid @RequestBody BoxType boxType) {
        return ResponseEntity.ok().body(boxTypeService.save(boxType));
    }


    @PutMapping("/boxType/{id}")
    public ResponseEntity<BoxType> updateBoxType(@PathVariable(value = "id") Long boxTypeId,
                                             @Valid @RequestBody BoxType boxTypeDetails) throws ResourceNotFoundException {
        BoxType boxType = boxTypeService.findById(boxTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("boxType not found for this id :: " + boxTypeId));
        boxType.setBoxTypeAr(boxTypeDetails.getBoxTypeAr());
        boxType.setBoxTypeEn(boxTypeDetails.getBoxTypeEn());
        final BoxType updatedBoxType = boxTypeService.save(boxType);
        return ResponseEntity.ok(updatedBoxType);
    }

    @DeleteMapping("/boxType/{id}")
    public ResponseEntity deleteBoxType(@PathVariable(value = "id") Long boxTypeId)
            throws ResourceNotFoundException {
        BoxType boxType = boxTypeService.findById(boxTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("boxType not found for this id :: " + boxTypeId));
        boxTypeService.deleteById(boxType);
        return ResponseEntity.noContent().build();
    }
}
