package com.dataserve.archivemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataserve.archivemanagement.model.Classifications;


public interface ClassificationsRepo extends JpaRepository<Classifications, Long> {

	 @Query("From Classifications b "
	 		+ " left join fetch b.folder ")
	 List<Classifications> listClassifications();

}
