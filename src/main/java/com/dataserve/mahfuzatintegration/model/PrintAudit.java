package com.dataserve.mahfuzatintegration.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "PRINT_AUDIT")
@Getter
@Setter
@NoArgsConstructor
public class PrintAudit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRINT_AUDIT_ID", nullable = true)
    private Long printAuditId;

    @Column(name = "TRANSACTION_DATE", nullable = true)
    private Date transactionDate;

    @Column(name = "USER_ID", nullable = true)
    private String userId;


//    @Column(name = "MODULE_ID", nullable = true)
//    private Long moduleId;

    @Column(name = "ACTION_TYPE_ID", nullable = true)
    private Long actionTypeId;

    @Column(name = "NUMBER_OF_PRINTS", nullable = true)
    private Long numberOfPrints;

    @Column(name = "REPRINT_REASON", nullable = false)
    private Long reason;

    @Column(name = "PRINTED_UNIT_TYPE_ID", nullable = true)
    private Long printedUnitTypeId;

    @Column(name = "PRINTED_UNIT_ID", nullable = true)
    private Long printedUnitId;
}
