package com.dataserve.mahfuzatintegration.exception;

public class CustomServiceException extends RuntimeException {
    private final Integer errorCode;
    private final String localizedMessage;

    public CustomServiceException(Integer errorCode, String localizedMessage) {
        super(localizedMessage);
        this.errorCode = errorCode;
        this.localizedMessage = localizedMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }
}
