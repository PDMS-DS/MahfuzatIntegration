package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.BoxType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoxTypeRepo extends JpaRepository<BoxType, Long> {


}
