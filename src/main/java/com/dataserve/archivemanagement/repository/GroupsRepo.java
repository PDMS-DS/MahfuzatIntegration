package com.dataserve.archivemanagement.repository;



import com.dataserve.archivemanagement.model.Groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Long> {




}
