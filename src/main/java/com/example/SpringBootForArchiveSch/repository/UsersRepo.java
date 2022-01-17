package com.example.SpringBootForArchiveSch.repository;


import com.example.SpringBootForArchiveSch.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
}
