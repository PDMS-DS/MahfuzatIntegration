package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dataserve.archivemanagement.model.Box;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
@Repository
public interface BoxRepo extends JpaRepository<Box,Long> {
//    Optional<Box> findByIdAndShelfId(Long boxId, Long shelfId);
    @Query("From Box b where b.serial=?1")
    Optional<Box> findBySerial(Long serial);
}
