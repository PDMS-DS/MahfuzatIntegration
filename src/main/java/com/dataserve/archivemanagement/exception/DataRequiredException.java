package com.dataserve.archivemanagement.exception;

public class DataRequiredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataRequiredException(String message){
        super(message);
    }
}
