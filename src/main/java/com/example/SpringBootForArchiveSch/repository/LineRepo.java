package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineRepo extends JpaRepository<Line, Long> {


}
