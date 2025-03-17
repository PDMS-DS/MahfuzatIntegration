package com.dataserve.mahfuzatintegration.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUDIT")
@Getter
@Setter
@NoArgsConstructor

public class Audit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID", nullable = false)
    private Long auditId;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private String transactionDate;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "MODULE_ID", nullable = false)
    private String moduleId;

    @Column(name = "ACTION_TYPE_ID", nullable = false)
    private String actionTypeId;


    

}