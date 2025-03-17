package com.dataserve.mahfuzatintegration.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EDSChoiceListDTO {
	private String propertyName;
	private String choiceListName;
	private String dependOn;
	private List<EDSChoiceDTO> choiceList;

}
