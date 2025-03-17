package com.dataserve.mahfuzatintegration.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassPropertiesDTO {
	private String className;
	private List<GetClassPropertyDTO> properties;

}
