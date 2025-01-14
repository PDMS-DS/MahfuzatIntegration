package com.dataserve.archivemanagement.controller;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
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
    private ConfigUtil configUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<APIResponseResult<Object>> loginAndGenerateToken(
            @RequestBody @Validated LoginRequest authenticationRequest) {

        // Validate username
        if (authenticationRequest.getUsername() == null || authenticationRequest.getUsername().trim().isEmpty()) {
            throw new CustomServiceException(
                    ArchiveErrorCode.USERNAME_REQUIRED.getCode(),
                    configUtil.getLocalMessage("1001", null)
            );
        }

        // Validate password
        if (authenticationRequest.getPassword() == null || authenticationRequest.getPassword().trim().isEmpty()) {
            throw new CustomServiceException(
                    ArchiveErrorCode.PASSWORD_REQUIRED.getCode(),
                    configUtil.getLocalMessage("1002", null)
            );
        }
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
