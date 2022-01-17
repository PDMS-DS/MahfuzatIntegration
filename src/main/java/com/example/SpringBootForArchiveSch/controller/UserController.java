package com.example.SpringBootForArchiveSch.controller;

import com.example.SpringBootForArchiveSch.exception.ResourceNotFoundException;
import com.example.SpringBootForArchiveSch.model.Users;
import com.example.SpringBootForArchiveSch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<Users> getAllModule() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserId(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        Users user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public ResponseEntity<Users> createUsers(@Valid @RequestBody Users user) {
        return ResponseEntity.ok().body(userService.save(user));
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
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        Users user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        userService.deleteById(user);
        return ResponseEntity.noContent().build();
    }
}
