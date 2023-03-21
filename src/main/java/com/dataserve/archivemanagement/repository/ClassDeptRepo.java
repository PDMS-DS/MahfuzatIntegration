package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataserve.archivemanagement.model.ClassDept;
import com.dataserve.archivemanagement.model.Classifications;

import java.util.List;

public interface ClassDeptRepo extends JpaRepository<ClassDept, Long> {

	 @Query("From ClassDept cd "
		 		+ " left join fetch cd.classifications c"
		 		+ " left join fetch cd.departments d")
		 List<ClassDept> listClassDept();
}
