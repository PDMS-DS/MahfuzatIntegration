package com.dataserve.archivemanagement.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.dataserve.archivemanagement.security.TokenResponse;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.UsersGroups;
import com.dataserve.archivemanagement.model.dto.UserDTO;
import com.dataserve.archivemanagement.repository.GroupsRepo;
import com.dataserve.archivemanagement.repository.UsersGroupsRepo;
import com.dataserve.archivemanagement.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/physicalArchive")
@RequiredArgsConstructor
public class UserController {

    private UsersRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userService;


    private UsersGroupsRepo userGroupRepo;


    private GroupsRepo groupRepo;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws ResourceNotFoundException {
        AppUsers user = userRepo.findByUserNameLdap(loginRequest.getUsername());
        if (loginRequest.getPassword().equals("123") && user != null) {
            Optional<UsersGroups> userGroups = userGroupRepo.findById(user.getUserId());
            UserDTO userDTO = new UserDTO(user);
            if (userGroups.isPresent()) {
                Set<Groups> groups = new HashSet<>();
                UsersGroups u = userGroups.get();
                groups.add(groupRepo.findById(u.getGroupsId()).get());
                userDTO.setGroups(groups);
            }


            return ResponseEntity.ok().body(userDTO);
        }
        throw new ResourceNotFoundException("Invalid Login");
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> loginAndGenerateToken(@RequestBody @Validated LoginRequest authenticationRequest)
            throws Exception {

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }


}
