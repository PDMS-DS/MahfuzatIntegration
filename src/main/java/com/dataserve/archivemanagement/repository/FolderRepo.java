package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataserve.archivemanagement.model.Folder;

import java.util.List;


public interface FolderRepo extends JpaRepository<Folder, Long> {

	 @Query("From Folder b where b.serial=?1")
	 List<Folder> findBySerial(Long serial);

}
