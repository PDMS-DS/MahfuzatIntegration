package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.*;
import com.example.SpringBootForArchiveSch.model.dto.BoxDto;
import com.example.SpringBootForArchiveSch.model.dto.FolderDto;
import com.example.SpringBootForArchiveSch.service.ClassificationsService;
import com.example.SpringBootForArchiveSch.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private ClassificationsService classificationsService;

    @GetMapping("/folder")
    public List<Folder> getAllFolders() {
        return folderService.findAll();
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable(value = "id") Long folderId)
            throws ResourceNotFoundException {
        Folder folder = folderService.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found for this id :: " + folderId));
        return ResponseEntity.ok().body(folder);
    }

    @PostMapping("/folder/{classificationId}")
    public ResponseEntity<FolderDto> createFolder(@PathVariable(value = "classificationId") Long classificationId
            , @Valid @RequestBody Folder folder) throws ResourceNotFoundException{
        if(classificationId != null ) {
            Classifications classifications = classificationsService.findById(classificationId)
                    .orElseThrow(() -> new ResourceNotFoundException("classifications not found for this id :: " + classificationId));
            folder.setClassifications(classifications);

        }
        return ResponseEntity.ok().body(folderService.save(folder));
    }


    @PutMapping("/folder/{id}")
    public ResponseEntity<FolderDto> updateFolder(@PathVariable(value = "id") Long folderId,
                                            @Valid @RequestBody Folder folderDetails) throws ResourceNotFoundException {
        Folder folder = folderService.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found for this id :: " + folderId));

        folder.setNameAr(folderDetails.getNameAr());
        folder.setNameEn(folderDetails.getNameEn());
        folder.setCapacity(folderDetails.getCapacity());
        folder.setSerial(folderDetails.getSerial());
        folder.setBoxId(folderDetails.getBoxId());

        final FolderDto updatedFolder = folderService.save(folder);
        return ResponseEntity.ok(updatedFolder);
    }

    @DeleteMapping("/folder/{id}")
    public ResponseEntity deleteActionType(@PathVariable(value = "id") Long folderId)
            throws ResourceNotFoundException {
        Folder folder = folderService.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("folder not found for this id :: " + folderId));
        folderService.deleteById(folder);
        return ResponseEntity.noContent().build();
    }

}
