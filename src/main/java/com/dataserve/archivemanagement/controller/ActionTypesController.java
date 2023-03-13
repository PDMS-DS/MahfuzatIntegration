package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.ActionTypes;
import com.dataserve.archivemanagement.service.ActionTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/physicalArchive")
public class ActionTypesController {
    @Autowired
    private ActionTypeService actionTypesService;

    @GetMapping("/actionTypes")
    public List<ActionTypes> getAllActionTypes() {
        return actionTypesService.findAll();
    }

    @GetMapping("/actionTypes/{id}")
    public ResponseEntity<ActionTypes> getActionTypesById(@PathVariable(value = "id") Long actionTypeId)
            throws ResourceNotFoundException {
        ActionTypes actionTypes = actionTypesService.findById(actionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ActionType not found for this id :: " + actionTypeId));
        return ResponseEntity.ok().body(actionTypes);
    }

    @PostMapping("/actionTypes")
    public ResponseEntity<ActionTypes> createActionTypes(@Valid @RequestBody ActionTypes actionTypes) {
        return ResponseEntity.ok().body(actionTypesService.save(actionTypes));
    }


    @PutMapping("/actionTypes/{id}")
    public ResponseEntity<ActionTypes> updateActionTypes(@PathVariable(value = "id") Long actionTypeId,
                                                   @Valid @RequestBody ActionTypes actionTypesDetails) throws ResourceNotFoundException {
        ActionTypes actionType = actionTypesService.findById(actionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ActionType not found for this id :: " + actionTypeId));

        actionType.setActionTypeNameAr(actionTypesDetails.getActionTypeNameAr());
        actionType.setActionTypeNameEn(actionTypesDetails.getActionTypeNameEn());
        final ActionTypes updatedActionTypes = actionTypesService.save(actionType);
        return ResponseEntity.ok(updatedActionTypes);
    }

    @DeleteMapping("/actionTypes/{id}")
    public ResponseEntity deleteActionType(@PathVariable(value = "id") Long actionTypeId)
            throws ResourceNotFoundException {
        ActionTypes actionType = actionTypesService.findById(actionTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ActionType not found for this id :: " + actionTypeId));
        actionTypesService.deleteById(actionType);
        return ResponseEntity.noContent().build();
    }
}
