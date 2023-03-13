package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Classifications;

public interface ClassificationsService {

    public List<Classifications> findAll();

    public Optional<Classifications> findById(Long theId);

    public Classifications save(Classifications theClassifications);

    public void deleteById(Classifications theClassifications);
}
