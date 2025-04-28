package com.dataserve.mahfuzatintegration.model.dto;

import com.dataserve.mahfuzatintegration.util.SaveType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateDocumentDTO {
    @NotNull(message = "Document properties cannot be null")
    private List<PropertyDTO> properties;
    private String documentClassName;
    private Integer numOfPages;
    @NotNull(message = "integrationDocumentId  cannot be null")
    private String integrationDocumentId;
    @NotNull(message = "integrationSystemId  cannot be null")
    private Long integrationSystemId;
    private List<CustomDocument> uploadDocumentList;

}
