package com.dataserve.archivemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
	Users findByUserNameLdap(String userName);
	
}
