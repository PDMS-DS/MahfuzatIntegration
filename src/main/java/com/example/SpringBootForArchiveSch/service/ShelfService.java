package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Shelf;

import java.util.List;
import java.util.Optional;

public interface ShelfService {

    public List<Shelf> findAll();

    public Optional<Shelf> findById(Long theId);

    public Shelf save(Shelf theShelf);

    public void deleteById(Shelf theShelf);
}
