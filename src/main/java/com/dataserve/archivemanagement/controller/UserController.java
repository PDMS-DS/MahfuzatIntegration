package com.dataserve.archivemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.exception.APIResponseResult;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.service.UserService;

@RestController
@RequestMapping("/physicalArchive")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<APIResponseResult<Object>> loginAndGenerateToken(
            @RequestBody @Validated LoginRequest authenticationRequest) {

        // Perform authentication (currently commented out but retained for reference)
        /*
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        */

        // Get user details or token from the service layer
        Object result = service.findByUserName(authenticationRequest);

        // Wrap the result in APIResponseResult
        APIResponseResult<Object> response = new APIResponseResult<>(
                result,
                HttpStatus.OK.value(),
                "Authentication successful"
        );
        return ResponseEntity.ok(response);
    }
}
