package com.dataserve.archivemanagement.service;



import java.util.Optional;

import com.dataserve.archivemanagement.model.Users;
import com.dataserve.archivemanagement.model.dto.response.UserResponse;

public interface UserService {

    public UserResponse listUsers();

    public Optional<Users> findById(Long theId);

    public Users save(Users theUser);

    public void deleteById(Users theUser);

}
