package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Classifications;

public interface ClassificationsService {

     List<Classifications> listClassifications();

     Optional<Classifications> findById(Long theId);

     Classifications save(Classifications theClassifications);

     void deleteById(Classifications theClassifications);
}
