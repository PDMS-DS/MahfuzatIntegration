package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Box;

import java.util.Optional;

@Repository
public interface BoxRepo extends JpaRepository<Box,Long> {
//    Optional<Box> findByIdAndShelfId(Long boxId, Long shelfId);

}
