package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.service.ClassificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/physicalArchive")
public class ClassificationController {
    @Autowired
    private ClassificationsService classificationsService;

    @GetMapping("/classifications")
    public ResponseEntity<List<Classifications>> getClassifications() {
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
