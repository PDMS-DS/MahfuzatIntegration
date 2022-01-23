package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Line;
import com.example.SpringBootForArchiveSch.model.dto.LineDto;

import java.util.List;
import java.util.Optional;

public interface LineService {

    public List<Line> findAll();

    public Optional<Line> findById(Long theId);

    public LineDto save(Line theLine);

    public void deleteById(Line theLine);
}
