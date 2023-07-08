package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.ClassPropertiesDTO;
import com.dataserve.archivemanagement.service.ClassificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physicalArchive")
public class ClassificationController {
    @Autowired
    private ClassificationsService classificationsService;


    @GetMapping("/classifications")
    public ResponseEntity<List<Classifications>> getClassifications(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(classificationsService.listClassifications(token));
    }

    @GetMapping("/classifications/{id}")
    public ResponseEntity<Classifications> getClassificationsId(@PathVariable(value = "id") Long classificationsId)
            throws ResourceNotFoundException {
        Classifications classifications = classificationsService.findById(classificationsId)
                .orElseThrow(() -> new ResourceNotFoundException("classifications not found for this id :: " + classificationsId));
        return ResponseEntity.ok().body(classifications);
    }


    @GetMapping(value = "/getClassProperties/{symbolicName}")
    public ResponseEntity<ClassPropertiesDTO> getClassProperties(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String symbolicName) {
        return new ResponseEntity<>(classificationsService.findClassProperties(symbolicName, token), HttpStatus.OK);
    }


}
