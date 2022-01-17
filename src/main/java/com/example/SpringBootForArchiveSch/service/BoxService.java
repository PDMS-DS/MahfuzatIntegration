package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Box;


import java.util.List;
import java.util.Optional;

public interface BoxService {

    public List<Box> findAll();

    public Optional<Box> findById(Long theId);

//    public Optional<Box> findByIdAndShelfId(Long boxId , Long shelfId);

    public Box save(Box theBox);

    public void deleteById(Box theBox);


}
