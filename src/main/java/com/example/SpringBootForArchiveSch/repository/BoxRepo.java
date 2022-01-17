package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoxRepo extends JpaRepository<Box,Long> {
//    Optional<Box> findByIdAndShelfId(Long boxId, Long shelfId);

}
