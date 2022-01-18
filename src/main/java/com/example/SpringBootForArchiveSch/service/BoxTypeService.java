package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.BoxType;

import java.util.List;
import java.util.Optional;

public interface BoxTypeService {
    public List<BoxType> findAll();

    public Optional<BoxType> findById(Long theId);

    public BoxType save(BoxType theBoxType);

    public void deleteById(BoxType theBoxType);
}
