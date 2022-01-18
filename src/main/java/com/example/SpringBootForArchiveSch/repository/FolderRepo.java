package com.example.SpringBootForArchiveSch.repository;

import com.example.SpringBootForArchiveSch.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepo extends JpaRepository<Folder, Long> {



}
