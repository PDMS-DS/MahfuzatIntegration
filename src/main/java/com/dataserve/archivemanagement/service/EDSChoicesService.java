package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;

import java.util.Map;

public interface EDSChoicesService {

    Map<String, EDSChoiceListDTO> getClassEDSPropertesBySymbolicName(String classSymbolicName);
}
