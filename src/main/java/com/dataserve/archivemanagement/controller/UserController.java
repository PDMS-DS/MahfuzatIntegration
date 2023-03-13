package com.dataserve.archivemanagement.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataserve.archivemanagement.exception.ResourceNotFoundException;
import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.UserDTO;
import com.dataserve.archivemanagement.model.Users;
import com.dataserve.archivemanagement.model.UsersGroups;
import com.dataserve.archivemanagement.repository.GroupsRepo;
import com.dataserve.archivemanagement.repository.UsersGroupsRepo;
import com.dataserve.archivemanagement.repository.UsersRepo;
import com.dataserve.archivemanagement.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UsersRepo userRepo;
	
	@Autowired
	private UsersGroupsRepo userGroupRepo;
	
	@Autowired
	private GroupsRepo groupRepo;
	
	
	@GetMapping("/user")
	public List<Users> getAllModule() {
		return userService.findAll();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserId(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Users user = userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/user")
	public ResponseEntity<Users> createUsers(@Valid @RequestBody Users user) {
		return ResponseEntity.ok().body(userService.save(user));
	}

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

	@PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		Users user = userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

		user.setUserArName(userDetails.getUserArName());
		user.setUserEnName(userDetails.getUserEnName());
		user.setUserNameLdap(userDetails.getUserNameLdap());
		user.setLogin(userDetails.isLogin());
		user.setActive(userDetails.isActive());
		final Users updatedUsers = userService.save(user);
		return ResponseEntity.ok(updatedUsers);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Users user = userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
		userService.deleteById(user);
		return ResponseEntity.noContent().build();
	}
}
