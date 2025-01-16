package com.dataserve.archivemanagement.controller;

import java.util.List;

import com.dataserve.archivemanagement.exception.APIResponseResult;
import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.service.FolderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FolderController {

    @Autowired
    private FolderService folderService;

    @GetMapping("/folder")
    public ResponseEntity<APIResponseResult<List<Folder>>> getAllFolders() {
        List<Folder> result = folderService.findAll();
        APIResponseResult<List<Folder>> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "All folders retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<APIResponseResult<Folder>> getFolderById(@PathVariable(value = "id") Long folderId) {
        Folder result = folderService.findById(folderId);
        APIResponseResult<Folder> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Folder retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/folder/serial/{serial}")
    public ResponseEntity<APIResponseResult<FolderDto>> getFolderBySerial(@PathVariable(value = "serial") Long serial) {
        FolderDto result = folderService.findBySerial(serial);
        APIResponseResult<FolderDto> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Folder with serial retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }
}
