package com.dataserve.archivemanagement.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.Users;
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
	

	private UsersGroupsRepo userGroupRepo;
	

	private GroupsRepo groupRepo;

	@PostMapping("/login")
	public ResponseEntity<UserDTO> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws ResourceNotFoundException  {
		Users user = userRepo.findByUserNameLdap(loginRequest.getUsername());
		if(loginRequest.getPassword().equals("123") && user != null) {
			Optional<UsersGroups> userGroups = userGroupRepo.findById(user.getUserId());
			UserDTO userDTO  = new UserDTO(user);
			if(userGroups.isPresent()) {
				Set<Groups> groups = new HashSet<>();
				UsersGroups u = userGroups.get();
				groups.add(groupRepo.findById(u.getGroupsId()).get());
				userDTO.setGroups(groups);
			}
			
			
			return ResponseEntity.ok().body(userDTO);			
		}
		throw new ResourceNotFoundException("Invalid Login" );
	}

	
}
