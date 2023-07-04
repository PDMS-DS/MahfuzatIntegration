package com.dataserve.archivemanagement.model.dto;

import java.util.List;
import java.util.Map;

public class CreateDocumentDTO {
	private List<PropertyDTO> properties;
    private String creator;
    private String documentClassName;
    
	public String getDocumentClassName() {
		return documentClassName;
	}
	public void setDocumentClassName(String documentClassName) {
		this.documentClassName = documentClassName;
	}
	public List<PropertyDTO> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyDTO> properties) {
		this.properties = properties;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
}
