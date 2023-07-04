package com.dataserve.archivemanagement.model.dto;

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
	private boolean isRequired;
	private String choicListName;
	private List<String> choicListValues;
	private String edsChoiceListName;
	private List<EDSChoiceDTO> edsChoiceListValues;

}
