package com.dataserve.archivemanagement.model;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "DMS_FILES")
@Getter
@Setter
@NoArgsConstructor
public class DmsFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "DOCUMENT_ID")
    private String documentId;


    @Column(name = "DOCUMENT_CLASS")
    private String documentClass;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPT_ID")
    private Departments departments;

    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Column(name = "FOLDER_ID")
    private Long folderNo;

    @Column(name = "NO_PAGES", nullable = true)
    private Long noPages;

    @Column(name = "OCR_STATUS")
    private Integer oCRStatus;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "SOURCE_ID")
    private Integer sourceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = true)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE", nullable = true)
    private Date modifiedDate;


}
 