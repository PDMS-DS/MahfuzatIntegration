package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Classifications;

import java.util.List;
import java.util.Optional;

public interface ClassificationsRepo extends JpaRepository<Classifications, Long> {


}
