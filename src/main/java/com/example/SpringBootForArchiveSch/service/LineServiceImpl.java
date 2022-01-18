package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Line;
import com.example.SpringBootForArchiveSch.repository.LineRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LineServiceImpl implements LineService {

    private LineRepo lineRepo;

    @Autowired
    public LineServiceImpl(LineRepo lineRepo) {
        this.lineRepo = lineRepo;
    }


    @Override
    public List<Line> findAll() {
        return null;
    }

    @Override
    public Optional<Line> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Line save(Line theLine) {
        return null;
    }

    @Override
    public void deleteById(Line theLine) {

    }
}
