package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Line;
import com.dataserve.archivemanagement.model.dto.LineDto;

import java.util.List;
import java.util.Optional;

public interface LineService {

    public List<Line> findAll();

    public Optional<Line> findById(Long theId);

    public LineDto save(Line theLine);

    public void deleteById(Line theLine);
}
