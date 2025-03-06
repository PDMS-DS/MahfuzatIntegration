package com.dataserve.archivemanagement.repository;

import java.util.List;

import com.dataserve.archivemanagement.model.ClassDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataserve.archivemanagement.model.Classifications;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepo extends JpaRepository<Classifications, Long> {

    @Query("From Classifications b "
            + " left join fetch b.folder  WHERE b.isFnAdded = 1 ")
    List<Classifications> listClassifications();

//    List<Classifications> findByClassDept_Departments_DeptId(Long deptId);

    @Query("SELECT c FROM Classifications c "
            + "JOIN c.classDept cd "
            + "WHERE cd.departments.deptId = :deptId "
            + "AND c.isFnAdded = 1")
    List<Classifications> findByClassDept_Departments_DeptId(@Param("deptId") Long deptId);
}
