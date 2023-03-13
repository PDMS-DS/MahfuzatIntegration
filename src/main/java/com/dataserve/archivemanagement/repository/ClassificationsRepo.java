package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Classifications;


public interface ClassificationsRepo extends JpaRepository<Classifications, Long> {


}
