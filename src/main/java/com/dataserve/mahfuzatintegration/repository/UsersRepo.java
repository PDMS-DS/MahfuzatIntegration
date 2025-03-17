package com.dataserve.mahfuzatintegration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.mahfuzatintegration.model.AppUsers;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<AppUsers, Long> {



    Optional<AppUsers> findByUserNameLdap(String userNameLdap);


}
