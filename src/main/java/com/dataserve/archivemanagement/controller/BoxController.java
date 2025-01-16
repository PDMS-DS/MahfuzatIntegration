package com.dataserve.archivemanagement.controller;

import java.util.List;

import com.dataserve.archivemanagement.exception.APIResponseResult;
import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.service.BoxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BoxController {

    @Autowired
    private BoxService boxService;

    @GetMapping("/box")
    public ResponseEntity<APIResponseResult<List<Box>>> getAllBox() {
        List<Box> result = boxService.findAll();
        APIResponseResult<List<Box>> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "All boxes retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/box/{id}")
    public ResponseEntity<APIResponseResult<Box>> getBoxById(@PathVariable(value = "id") Long boxId) {
        Box result = boxService.findById(boxId);
        APIResponseResult<Box> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Box retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/box/serial/{serial}")
    public ResponseEntity<APIResponseResult<BoxDto>> getBoxBySerial(@PathVariable(value = "serial") Long serial) {
        BoxDto result = boxService.findBySerial(serial);
        APIResponseResult<BoxDto> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Box with serial retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }
}
