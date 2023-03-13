package com.dataserve.archivemanagement.controller;


import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.BoxType;
import com.dataserve.archivemanagement.model.Shelf;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.response.BoxResponse;
import com.dataserve.archivemanagement.service.BoxService;
import com.dataserve.archivemanagement.service.BoxTypeService;
import com.dataserve.archivemanagement.service.ShelfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/physicalArchive")
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
    public ResponseEntity<BoxResponse> getBoxById(@PathVariable(value = "id") Long boxId) {
        return ResponseEntity.ok(boxService.findById(boxId));
    }
    
    @GetMapping("/box/serial/{serial}")
    public ResponseEntity<BoxResponse> getBoxBySerial(@PathVariable(value = "serial") Long serial) {
        return ResponseEntity.ok(boxService.findBySerial(serial));
    }
    
}
