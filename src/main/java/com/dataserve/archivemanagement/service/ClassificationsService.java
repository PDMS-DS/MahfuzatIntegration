package com.dataserve.archivemanagement.service;

import java.util.Optional;

import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.response.ClassificationResponse;

public interface ClassificationsService {

    public ClassificationResponse listClassifications();

    public Optional<Classifications> findById(Long theId);

    public Classifications save(Classifications theClassifications);

    public void deleteById(Classifications theClassifications);
}
