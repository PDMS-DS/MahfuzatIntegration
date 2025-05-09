package com.dataserve.mahfuzatintegration.model.dto.response;

import com.dataserve.mahfuzatintegration.constant.ResponseInfo;

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
