package com.dataserve.mahfuzatintegration.model.dto;

public class EDSChoiceDTO {
	private String lang;
	private String displayName;

	private String value;
	private String dependValue;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getDependValue() {
		return dependValue;
	}

	public void setDependValue(String dependValue) {
		this.dependValue = dependValue;
	}

}
