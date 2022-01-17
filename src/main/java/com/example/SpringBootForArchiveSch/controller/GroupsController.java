package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.ActionTypes;
import com.example.SpringBootForArchiveSch.model.Groups;
import com.example.SpringBootForArchiveSch.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GroupsController {

    @Autowired
    private GroupsService groupService;

    @GetMapping("/group")
    public List<Groups> getAllGroup() {
        return groupService.findAll();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<Groups> getGroupById(@PathVariable(value = "id") Long groupId)
            throws ResourceNotFoundException {
        Groups groups = groupService.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("group not found for this id :: " + groupId));
        return ResponseEntity.ok().body(groups);
    }

    @PostMapping("/group")
    public ResponseEntity<Groups> createGroup(@Valid @RequestBody Groups groups) {
        return ResponseEntity.ok().body(groupService.save(groups));
    }


    @PutMapping("/group/{id}")
    public ResponseEntity<Groups> updateGroup(@PathVariable(value = "id") Long groupId,
                                                         @Valid @RequestBody Groups groupsDetails) throws ResourceNotFoundException {
        Groups group = groupService.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("group not found for this id :: " + groupId));

        group.setGroupArName(groupsDetails.getGroupArName());
        group.setGroupEnName(groupsDetails.getGroupEnName());
        group.setGroupLdap(groupsDetails.getGroupLdap());
        group.setEnabled(groupsDetails.isEnabled());
        group.setActive(groupsDetails.isActive());
        final Groups updatedGroup = groupService.save(group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity deleteGroup(@PathVariable(value = "id") Long groupId)
            throws ResourceNotFoundException {
        Groups group = groupService.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("group not found for this id :: " + groupId));
        groupService.deleteById(group);
        return ResponseEntity.noContent().build();
    }
}
