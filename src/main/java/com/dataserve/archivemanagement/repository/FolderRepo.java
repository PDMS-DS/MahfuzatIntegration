package com.dataserve.archivemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataserve.archivemanagement.model.Folder;

import java.util.List;
import java.util.Optional;

public interface FolderRepo extends JpaRepository<Folder, Long> {



}
