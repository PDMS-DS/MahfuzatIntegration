package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.APIResponseResult;
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
    public ResponseEntity<APIResponseResult<List<Classifications>>> getClassifications(
            @RequestHeader(name = "Authorization") String token) {
        List<Classifications> result = classificationsService.listClassifications(token);
        APIResponseResult<List<Classifications>> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Classifications retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/classifications/{id}")
    public ResponseEntity<APIResponseResult<Classifications>> getClassificationsId(
            @PathVariable(value = "id") Long classificationsId) throws ResourceNotFoundException {
        Classifications classifications = classificationsService.findById(classificationsId)
                .orElseThrow(() -> new ResourceNotFoundException("Classifications not found for this id :: " + classificationsId));
        APIResponseResult<Classifications> response = new APIResponseResult<>(
                classifications,
                HttpStatus.OK.value(),
                "Classification retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getClassProperties/{symbolicName}")
    public ResponseEntity<APIResponseResult<ClassPropertiesDTO>> getClassProperties(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable String symbolicName) {
        ClassPropertiesDTO result = classificationsService.findClassProperties(symbolicName, token);
        APIResponseResult<ClassPropertiesDTO> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Class properties retrieved successfully"
        );
        return ResponseEntity.ok(response);
    }
}
