package com.example.SpringBootForArchiveSch.service;
import com.example.SpringBootForArchiveSch.model.Box;
import com.example.SpringBootForArchiveSch.repository.BoxRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BoxTypeServiceImpl implements BoxService{

    private BoxRepo boxRepo ;

    @Autowired
    public BoxTypeServiceImpl(BoxRepo boxRepo) {
        this.boxRepo = boxRepo;
    }

    @Override
    public List<Box> findAll() {
        return null;
    }

    @Override
    public Optional<Box> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Box save(Box theBox) {
        return null;
    }

    @Override
    public void deleteById(Box theBox) {

    }
}
