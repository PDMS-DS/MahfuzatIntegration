package com.dataserve.archivemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Groups;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Long> {

}
