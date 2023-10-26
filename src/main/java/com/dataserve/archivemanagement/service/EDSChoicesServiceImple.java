package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.EDSChoices;
import com.dataserve.archivemanagement.model.dto.EDSChoiceDTO;
import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;
import com.dataserve.archivemanagement.repository.EDSChoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EDSChoicesServiceImple implements EDSChoicesService {
    @Autowired
    private EDSChoicesRepository edsChoicesRepository;

    public Map<String, EDSChoiceListDTO> getClassEDSPropertesBySymbolicName(String classSymbolicName) {
        List<EDSChoices> edsChoicesList = edsChoicesRepository.findByCompositeKeyClassSymbolicName(classSymbolicName);
        EDSChoiceListDTO listDto = null;
        EDSChoiceDTO dto = null;
        Map<String, EDSChoiceListDTO> choiceLists = new HashMap<>();
        if (edsChoicesList != null && !edsChoicesList.isEmpty()) {
            for (EDSChoices edsChoices : edsChoicesList) {
                String propertyName = edsChoices.getCompositeKey().getProperty();
                if (!choiceLists.containsKey(propertyName)) {
                    listDto = new EDSChoiceListDTO();
                    listDto.setPropertyName(edsChoices.getCompositeKey().getProperty());
                    listDto.setChoiceListName(edsChoices.getListDisplayName());
                    listDto.setDependOn(edsChoices.getDepon());
                    listDto.setChoiceList(new ArrayList());
                    choiceLists.put(propertyName, listDto);
                }
                dto = new EDSChoiceDTO();
                dto.setDisplayName(edsChoices.getDisplayName());
                dto.setLang(edsChoices.getCompositeKey().getLang());
                dto.setDependValue(edsChoices.getDepValue());
                dto.setValue(edsChoices.getCompositeKey().getValue());
                choiceLists.get(propertyName).getChoiceList().add(dto);
            }
            return choiceLists;
        } else {
            throw new DataNotFoundException("can not found  class properties");
        }

    }
}
