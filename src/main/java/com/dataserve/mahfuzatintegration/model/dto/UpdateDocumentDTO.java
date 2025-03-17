package com.dataserve.mahfuzatintegration.model.dto;

import java.util.Map;

public class UpdateDocumentDTO {
	private String guid;
	private Map<String, String> properties;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}
