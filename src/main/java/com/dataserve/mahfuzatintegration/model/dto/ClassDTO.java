package com.dataserve.mahfuzatintegration.model.dto;

import java.util.List;

public class ClassDTO {
	private String symbolicName;
	private String nameAr;
	private String nameEn;
	private String code;
	private Integer id;
	private Integer parentId;
	private List<ClassDTO> subclasses;
	
	public String getSymbolicName() {
		return symbolicName;
	}
	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}

	public String getNameAr() {
		return nameAr;
	}
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<ClassDTO> getSubclasses() {
		return subclasses;
	}
	public void setSubclasses(List<ClassDTO> subclasses) {
		this.subclasses = subclasses;
	}
	
	
}
