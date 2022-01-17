package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Box;
import com.example.SpringBootForArchiveSch.repository.BoxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxServiceImpl implements BoxService{


    private final BoxRepo boxRepo;

    @Autowired
    public BoxServiceImpl(BoxRepo boxRepo) {
        this.boxRepo = boxRepo;
    }

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public Optional<Box> findById(Long theId) {
        return boxRepo.findById(theId);
    }

    @Override
    public Box save(Box theBox) {
        boxRepo.save(theBox);
        return theBox;
    }

    @Override
    public void deleteById(Box theBox) {
        boxRepo.delete(theBox);
    }
}
