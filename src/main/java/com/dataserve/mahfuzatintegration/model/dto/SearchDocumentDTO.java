package com.dataserve.mahfuzatintegration.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDocumentDTO {
    private String usernameAr;
    private String usernameEn;
    private String docId;
    private String departmentNameAr;
    private String departmentNameEn;
    private String documentTitle;
    private String folderNameAr;
    private String folderNameEn;
    private Long noPages;
    private String username;
    private String departmentName;
    private String folderName;
}

