package com.dataserve.archivemanagement.util;

public enum ArchiveErrorCode {

    BUSINESS(1000),
    USERNAME_REQUIRED(1001),
    PASSWORD_REQUIRED(1002),
    USER_NOT_FOUND_IN_LDAP(1003),
    BAD_CREDENTIALS(1004),
    DOCUMENT_NOT_FOUND(1005);







    private final Integer code;

    ArchiveErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
