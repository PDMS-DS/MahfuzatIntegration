package com.dataserve.mahfuzatintegration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DMS_INTEGRATION_FILES")
@Getter
@Setter
@NoArgsConstructor
public class DmsIntegrationFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTEGRATION_FILE_ID")
    private Long integrationFileId;

    @Column(name = "INTEGRATION_DOCUMENT_ID")
    private String integrationDocumentId;

    @Column(name = "INTEGRATION_DOCUMENT_NAME")
    private String integrationDocumentName;

    @Column(name = "INTEGRATION_SYS_ID")
    private Long integrationSystemId;

    @Column(name = "INTEGRATION_FILE_NO_PAGES")
    private Integer integrationFileNoPages;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "ARCHIVED_DOCUMENT_ID")
    private String archivedDocumentId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ARCHIVED_DATE")
    private Date archivedDate;

    @Column(name = "ARCHIVED_DOCUMENT_STATUS")
    private Integer archivedDocumentStatus;

    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "INTEGRATION_CLASSIFICATION_ID")
    private Long integrationClassificationId;

    @Column(name = "INTEGRATION_FILE_EXT", length = 20)
    private String integrationFileExt;

    @Column(name = "INTEGRATION_PROPERTIES_JSON")
    private String integrationPropertiesJson;
}
