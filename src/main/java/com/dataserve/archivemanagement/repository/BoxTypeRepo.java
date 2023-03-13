package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.BoxType;

import java.util.List;
import java.util.Optional;

public interface BoxTypeRepo extends JpaRepository<BoxType, Long> {


}
