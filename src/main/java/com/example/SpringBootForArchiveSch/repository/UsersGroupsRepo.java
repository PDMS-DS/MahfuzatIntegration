package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Classifications;
import com.example.SpringBootForArchiveSch.model.UsersGroups;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersGroupsRepo extends JpaRepository<UsersGroups, Long> {
}
