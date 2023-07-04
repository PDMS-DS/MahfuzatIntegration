package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.ClassPropertiesDTO;

public interface ClassificationsService {

     List<Classifications> listClassifications(String token);

     Optional<Classifications> findById(Long theId);

     Classifications save(Classifications theClassifications);

     void deleteById(Classifications theClassifications);
     ClassPropertiesDTO findClassProperties(String symbolicName, String token);
}
