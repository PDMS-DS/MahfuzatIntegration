package com.dataserve.archivemanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDocumentDTO {
    private String usernameAr;
    private String usernameEn;
    private String docId;
    private String documentNameAr;
    private String documentNameEn;
    private String departmentName;
    private String folderNameAr;
    private String folderNameEn;
    private Long noPages;

}
