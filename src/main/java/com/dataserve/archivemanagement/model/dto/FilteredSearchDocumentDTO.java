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
public class FilteredSearchDocumentDTO {
    private String username;
    private String docId;
    private String departmentName;
    private String documentTitle;
    private String folderName;
    private Long noPages;
}

