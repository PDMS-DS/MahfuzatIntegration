package com.dataserve.archivemanagement.model.dto;

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
	private List<EDSChoiceDTO> choiceList;

}
