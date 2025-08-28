package com.dataserve.mahfuzatintegration.model.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreateItemDTO {
    private String documentId; // archivedDocumentId
    private String integrationDocumentId; // source doc id from caller
    private Long integrationSystemId; // caller system id
}
