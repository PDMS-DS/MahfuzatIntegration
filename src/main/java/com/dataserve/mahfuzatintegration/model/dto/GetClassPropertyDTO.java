package com.dataserve.mahfuzatintegration.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetClassPropertyDTO {
    private String symbolicName;
    private String dataType;
    private String value;
    private boolean isMultiValue;
    private boolean isRequired;
    private String choicListName;
    private List<String> choicListValues;
    private String dependOn;
    private String edsChoiceListName;
    private List<EDSChoiceDTO> edsChoiceListValues;
    private String desc;
    private String descAr;
    private String descEn;

}
