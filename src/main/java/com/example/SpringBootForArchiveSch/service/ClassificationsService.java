package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Classifications;

import java.util.List;
import java.util.Optional;

public interface ClassificationsService {

    public List<Classifications> findAll();

    public Optional<Classifications> findById(Long theId);

    public Classifications save(Classifications theClassifications);

    public void deleteById(Classifications theClassifications);
}
