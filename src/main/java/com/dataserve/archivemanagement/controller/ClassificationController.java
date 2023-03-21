package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.response.ClassificationResponse;
import com.dataserve.archivemanagement.service.BoxService;
import com.dataserve.archivemanagement.service.ClassificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/physicalArchive")
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationsService classificationsService;

    @GetMapping("/classifications")
    public ResponseEntity<ClassificationResponse> getClassifications() {
        return ResponseEntity.ok(classificationsService.listClassifications());
    }

    @GetMapping("/classifications/{id}")
    public ResponseEntity<Classifications> getClassificationsId(@PathVariable(value = "id") Long classificationsId)
            throws ResourceNotFoundException {
        Classifications classifications = classificationsService.findById(classificationsId)
                .orElseThrow(() -> new ResourceNotFoundException("classifications not found for this id :: " + classificationsId));
        return ResponseEntity.ok().body(classifications);
    }

    @PostMapping("/classifications")
    public ResponseEntity<Classifications> createClassifications(@Valid @RequestBody Classifications classifications) {
        return ResponseEntity.ok().body(classificationsService.save(classifications));
    }


    @PutMapping("/classifications/{id}")
    public ResponseEntity<Classifications> updateClassifications(@PathVariable(value = "id") Long classificationsId,
                                                 @Valid @RequestBody Classifications classificationsDetails) throws ResourceNotFoundException {
        Classifications classifications = classificationsService.findById(classificationsId)
                .orElseThrow(() -> new ResourceNotFoundException("classifications not found for this id :: " + classificationsId));
        classifications.setClassCode(classificationsDetails.getClassCode());
        classifications.setClassArName(classificationsDetails.getClassArName());
        classifications.setClassEnName(classificationsDetails.getClassEnName());
        classifications.setParentID(classificationsDetails.getParentID());
        final Classifications updatedClassifications = classificationsService.save(classifications);
        return ResponseEntity.ok(updatedClassifications);
    }

    @DeleteMapping("/classifications/{id}")
    public ResponseEntity deleteClassifications(@PathVariable(value = "id") Long classificationsId)
            throws ResourceNotFoundException {
        Classifications classifications = classificationsService.findById(classificationsId)
                .orElseThrow(() -> new ResourceNotFoundException("classifications not found for this id :: " + classificationsId));
        classificationsService.deleteById(classifications);
        return ResponseEntity.noContent().build();
    }
}
