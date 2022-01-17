package com.example.SpringBootForArchiveSch.repository;


import com.example.SpringBootForArchiveSch.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Long> {

}
