package com.dataserve.archivemanagement.model.dto;

import com.dataserve.archivemanagement.util.SaveType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateDocumentDTO {
    @NotNull(message = "Document properties cannot be null")
    private List<PropertyDTO> properties;
    private String creator;
    private String documentClassName;
    @JsonProperty("DocumentTitle")
    private String documentTitle;
    private Integer folderNo;
    private SaveType saveType;
    private Integer numOfPages;
    private Integer isOriginal;


}
