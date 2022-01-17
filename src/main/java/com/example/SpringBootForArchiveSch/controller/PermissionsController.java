package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.ActionTypes;

import com.example.SpringBootForArchiveSch.model.Permissions;
import com.example.SpringBootForArchiveSch.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;

    @GetMapping("/permissions")
    public List<Permissions> getAllPermissions() {
        return permissionsService.findAll();
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permissions> getPermissionsById(@PathVariable(value = "id") Long actionTypeId)
            throws ResourceNotFoundException {
        Permissions actionTypes = permissionsService.findById(actionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("permission not found for this id :: " + actionTypeId));
        return ResponseEntity.ok().body(actionTypes);
    }

    @PostMapping("/permissions")
    public ResponseEntity<Permissions> createPermissions(@Valid @RequestBody Permissions actionTypes) {
        return ResponseEntity.ok().body(permissionsService.save(actionTypes));
    }


    @PutMapping("/permissions/{id}")
    public ResponseEntity<Permissions> updatePermissions(@PathVariable(value = "id") Long permissionId,
                                                         @Valid @RequestBody Permissions permissionsDetails) throws ResourceNotFoundException {
        Permissions permissions = permissionsService.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("permission not found for this id :: " + permissionId));

        permissions.setPermissionArName(permissionsDetails.getPermissionArName());
        permissions.setPermissionEnName(permissionsDetails.getPermissionEnName());
        permissions.setPermissionDescription(permissionsDetails.getPermissionDescription());
        permissions.setEnabled(permissionsDetails.isEnabled());
//        permissions.setModuleId(permissionsDetails.getModuleId());
//        permissions.setActionTypeId(permissionsDetails.getActionTypeId());
        final Permissions updatedPermission = permissionsService.save(permissions);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity deletePermissions(@PathVariable(value = "id") Long actionTypeId)
            throws ResourceNotFoundException {
        Permissions actionType = permissionsService.findById(actionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("permission not found for this id :: " + actionTypeId));
        permissionsService.deleteById(actionType);
        return ResponseEntity.noContent().build();
    }
}
