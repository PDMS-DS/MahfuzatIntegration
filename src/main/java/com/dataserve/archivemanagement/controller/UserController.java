package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dataserve.archivemanagement.model.LoginRequest;





@RestController
@RequestMapping("/physicalArchive")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> loginAndGenerateToken(@RequestBody @Validated LoginRequest authenticationRequest) {
        return ResponseEntity.ok((service.findByUserName(authenticationRequest)));
    }



}
