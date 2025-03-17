package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.model.dto.EDSChoiceListDTO;

import java.util.Map;

public interface EDSChoicesService {

    Map<String, EDSChoiceListDTO> getClassEDSPropertesBySymbolicName(String classSymbolicName);
}
