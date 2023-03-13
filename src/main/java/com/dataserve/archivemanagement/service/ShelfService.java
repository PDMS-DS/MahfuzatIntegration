package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Shelf;

public interface ShelfService {

    public List<Shelf> findAll();

    public Optional<Shelf> findById(Long theId);

    public Shelf save(Shelf theShelf);

    public void deleteById(Shelf theShelf);
}
