package com.dataserve.archivemanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.response.FolderResponse;
import com.dataserve.archivemanagement.service.FolderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physicalArchive")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @GetMapping("/folder")
    public List<Folder> getAllFolders() {
        return folderService.findAll();
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<FolderResponse> getFolderById(@PathVariable(value = "id") Long folderId)
          {
        return ResponseEntity.ok(folderService.findById(folderId));
    }

    @GetMapping("/folder/serial/{serial}")
    public ResponseEntity<FolderResponse> getFolderBySerial(@PathVariable(value = "serial") Long serial) {
        return ResponseEntity.ok(folderService.findBySerial(serial));
    }

}
