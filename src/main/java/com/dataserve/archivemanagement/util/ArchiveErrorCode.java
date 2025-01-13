package com.dataserve.archivemanagement.util;

public enum ArchiveErrorCode {
    DATA_NOT_FOUND(2002);
    private final Integer code;

    ArchiveErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
