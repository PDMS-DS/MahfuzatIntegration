package com.dataserve.archivemanagement.model.dto.response;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FolderResponse extends Response{

	public FolderResponse() {
		super();
	}

	public FolderResponse(ResponseInfo responseInfo) {
		super(responseInfo);
	}
	
	
	@JsonProperty("folder")
	 private Object response;


	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	
}
