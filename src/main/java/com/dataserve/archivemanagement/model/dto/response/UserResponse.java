package com.dataserve.archivemanagement.model.dto.response;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse extends Response{
    public UserResponse(ResponseInfo responseInfo) {
		super(responseInfo);
	}

	public UserResponse() {
		super();
	}

	@JsonProperty("user")
    private Object response;

	public Object getResponse() {
		return response;
	}

	public void setResponse( Object response) {
		this.response = response;
	}
    
    
}
