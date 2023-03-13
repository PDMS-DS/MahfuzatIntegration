package com.dataserve.archivemanagement.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassificationResponse extends Response{

	
	public ClassificationResponse(Object response) {
		super();
		this.response = response;
	}

	public ClassificationResponse() {
		super();
	}

	@JsonProperty("classification")
	 private Object response;

		public Object getResponse() {
			return response;
		}

		public void setResponse( Object response) {
			this.response = response;
		}
}
