package com.dataserve.archivemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DMS_AUDIT")
@Getter
@Setter
@NoArgsConstructor
public class DMSAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID", nullable = false)
    private Long auditId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "OPERATION_ID")
    private Integer operationId;
    @Column(name = "DOCUMENT_CLASS")
    private String documentClass;
    @Column(name = "DOCUMENT_ID")
    private String documentId;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "COPIED_TO_ELASTIC")
    private Integer copiedToElastic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
    private Date createDate;


}
