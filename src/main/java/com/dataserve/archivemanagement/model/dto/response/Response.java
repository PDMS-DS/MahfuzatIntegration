package com.dataserve.archivemanagement.model.dto.response;

import com.dataserve.archivemanagement.constant.ResponseInfo;

public class Response {

    private String responseCode;
    private String responseMessage;
    private String responseMessageAr;

    public Response() {
    	
    }
    public Response(ResponseInfo responseInfo) {
        this.responseCode = String.valueOf(responseInfo.getStatusCode());
        this.responseMessage = responseInfo.getMessage();
        this.responseMessageAr = responseInfo.getMessageAr();
    }

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(
			String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(
			String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessageAr() {
		return responseMessageAr;
	}

	public void setResponseMessageAr(
			String responseMessageAr) {
		this.responseMessageAr = responseMessageAr;
	}
    
    
}
