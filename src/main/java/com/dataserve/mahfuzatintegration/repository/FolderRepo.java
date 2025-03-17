package com.dataserve.mahfuzatintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataserve.mahfuzatintegration.model.Folder;

import java.util.Optional;


public interface FolderRepo extends JpaRepository<Folder, Long> {

	 @Query("From Folder b where b.serial=?1")
	 Optional<Folder> findBySerial(Long serial);

}
