package com.dataserve.archivemanagement.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.response.BoxResponse;
import com.dataserve.archivemanagement.service.BoxService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physicalArchive")
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;


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
