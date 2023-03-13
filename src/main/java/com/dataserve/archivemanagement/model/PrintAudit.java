package com.dataserve.archivemanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRINT_AUDIT")
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






    public PrintAudit() {
    }


    @Override
    public String toString() {
        return "PrintAudit{" +
                "printAuditId=" + printAuditId +
                ", transactionDate=" + transactionDate +
                ", userId=" + userId +
//                ", moduleId=" + moduleId +
                ", actionTypeId=" + actionTypeId +
                ", numberOfPrints=" + numberOfPrints +
                ", reason='" + reason + '\'' +
                ", printedUnitTypeId=" + printedUnitTypeId +
                ", printedUnitId=" + printedUnitId +
                '}';
    }
}
