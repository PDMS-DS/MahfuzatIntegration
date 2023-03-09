package com.example.SpringBootForArchiveSch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootForArchiveSch.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
	Users findByUserNameLdap(String userName);
	
}
