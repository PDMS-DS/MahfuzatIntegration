package com.dataserve.archivemanagement.util;

public enum ArchiveErrorCode {

    BUSINESS(1000),
    USERNAME_REQUIRED(1001),
    PASSWORD_REQUIRED(1002),
    USER_NOT_FOUND_IN_LDAP(1003),
    BAD_CREDENTIALS(1004),
    DOCUMENT_NOT_FOUND(1005),
    NUMBER_OF_PAGES_REQUIRED(1006), // Number of pages required
    FAILED_TO_RECEIVE_FILES(1007), // Failed to receive files
    USER_NOT_FOUND(1008),
    FOLDER_NOT_FOUND(1009), // Folder not found
    FAILED_TO_CREATE_DOCUMENT(1010), // Error code for failed document creation
    FOLDER_NUMBER_REQUIRED(1012),      // Folder number is required// Number of pages required
    FOLDER_CAPACITY_FULL(1013),       // Folder capacity is full
    STORAGE_CENTER_NOT_FOUND(1014),   // Storage center not found
    INVALID_STORAGE_CENTER_TYPE(1015),// Invalid storage center type
    CLASSIFICATION_INVALID(1016),     // Invalid classification
    STORAGE_TYPE_MISMATCH(1017),      // Storage type mismatch
    NOTHING_TO_UPDATE(1019),           // Nothing to update
    FAILED_TO_UPDATE_DOCUMENT(1020),   // Failed to update the document
    CLASSIFICATION_PROPERTIES_NOT_FOUND(1022), // New error code for missing class properties
    PROPERTIES_REQUIRED(1026); // Properties are required







    private final Integer code;

    ArchiveErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
