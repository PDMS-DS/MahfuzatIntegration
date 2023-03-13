package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Line;


public interface LineRepo extends JpaRepository<Line, Long> {


}
