package com.example.SpringBootForArchiveSch.service;



import com.example.SpringBootForArchiveSch.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<Users> findAll();

    public Optional<Users> findById(Long theId);

    public Users save(Users theUser);

    public void deleteById(Users theUser);

}
