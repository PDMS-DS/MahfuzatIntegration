package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Users;
import com.example.SpringBootForArchiveSch.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UsersRepo usersRepo;

    @Autowired
    public UserServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }

    @Override
    public Optional<Users> findById(Long theId) {
        return usersRepo.findById(theId);
    }

    @Override
    public Users save(Users theUser) {
        usersRepo.save(theUser);
        return theUser;
    }

    @Override
    public void deleteById(Users theUser) {
        usersRepo.delete(theUser);
    }
}
