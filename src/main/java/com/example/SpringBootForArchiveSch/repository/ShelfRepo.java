package com.example.SpringBootForArchiveSch.repository;


import com.example.SpringBootForArchiveSch.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepo extends JpaRepository<Shelf,Long> {
}
