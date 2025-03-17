package com.dataserve.mahfuzatintegration.repository;



import com.dataserve.mahfuzatintegration.model.Groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepo extends JpaRepository<Groups,Long> {




}
