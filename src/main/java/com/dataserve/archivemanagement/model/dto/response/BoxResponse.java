package com.dataserve.archivemanagement.model.dto.response;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BoxResponse extends Response{

	public BoxResponse() {
		super();
	}

	public BoxResponse(ResponseInfo responseInfo) {
		super(responseInfo);
	}
	
	
	@JsonProperty("box")
	 private Object response;


	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	
}
