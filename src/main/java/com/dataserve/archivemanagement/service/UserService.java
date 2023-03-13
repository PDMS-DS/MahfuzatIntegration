package com.dataserve.archivemanagement.service;



import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Users;

public interface UserService {

    public List<Users> findAll();

    public Optional<Users> findById(Long theId);

    public Users save(Users theUser);

    public void deleteById(Users theUser);

}
