package com.dataserve.archivemanagement.model.dto.response;

import com.dataserve.archivemanagement.constant.ResponseInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {

    private String responseCode;
    private String responseMessage;
    private String responseMessageAr;


    public Response(ResponseInfo responseInfo) {
        this.responseCode = String.valueOf(responseInfo.getStatusCode());
        this.responseMessage = responseInfo.getMessage();
        this.responseMessageAr = responseInfo.getMessageAr();
    }


}
