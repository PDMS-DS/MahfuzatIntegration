package com.dataserve.archivemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataserve.archivemanagement.model.Shelf;

@Repository
public interface ShelfRepo extends JpaRepository<Shelf,Long> {
}
