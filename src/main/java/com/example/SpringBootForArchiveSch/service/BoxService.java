package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Box;
import com.example.SpringBootForArchiveSch.model.dto.BoxDto;


import java.util.List;
import java.util.Optional;

public interface BoxService {

    public List<Box> findAll();

    public Optional<Box> findById(Long theId);

//    public Optional<Box> findByIdAndShelfId(Long boxId , Long shelfId);

    public BoxDto save(Box theBox);

    public void deleteById(Box theBox);


}
