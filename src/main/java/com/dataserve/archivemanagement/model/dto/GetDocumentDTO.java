package com.dataserve.archivemanagement.model.dto;

import java.util.Map;

public class GetDocumentDTO {
	private Map<String, String> properties;
	private Map<String, byte[]> contents;
	
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> propsMap) {
		this.properties = propsMap;
	}
	public Map<String, byte[]> getContents() {
		return contents;
	}
	public void setContents(Map<String, byte[]> contents) {
		this.contents = contents;
	}
}
