package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Module;
import com.dataserve.archivemanagement.service.ModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/module")
    public List<Module> getAllModule() {
        return moduleService.findAll();
    }

    @GetMapping("/module/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable(value = "id") Long moduleId)
            throws ResourceNotFoundException {
        Module actionTypes = moduleService.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :: " + moduleId));
        return ResponseEntity.ok().body(actionTypes);
    }

    @PostMapping("/module")
    public ResponseEntity<Module> createModule(@Valid @RequestBody Module module) {
        return ResponseEntity.ok().body(moduleService.save(module));
    }


    @PutMapping("/module/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable(value = "id") Long moduleId,
                                                         @Valid @RequestBody Module moduleDetails) throws ResourceNotFoundException {
        Module module = moduleService.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :: " + moduleId));

        module.setModuleNameAr(moduleDetails.getModuleNameAr());
        module.setModuleNameEn(moduleDetails.getModuleNameEn());
        final Module updatedModule = moduleService.save(module);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/module/{id}")
    public ResponseEntity deleteModule(@PathVariable(value = "id") Long moduleId)
            throws ResourceNotFoundException {
        Module module = moduleService.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :: " + moduleId));
        moduleService.deleteById(module);
        return ResponseEntity.noContent().build();
    }
}
