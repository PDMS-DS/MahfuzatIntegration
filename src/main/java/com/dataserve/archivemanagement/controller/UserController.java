package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dataserve.archivemanagement.model.LoginRequest;





@RestController
@RequestMapping("/physicalArchive")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> loginAndGenerateToken(@RequestBody @Validated LoginRequest authenticationRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);


        return ResponseEntity.ok((service.findByUserName(authenticationRequest)));
    }



}
