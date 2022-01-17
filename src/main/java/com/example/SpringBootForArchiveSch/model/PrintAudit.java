package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;
import java.util.Date;


public class PrintAudit {

    private long printAuditId;
    private Date transactionDate;
    private int userId;
    private int moduleId;
    private int actionTypeId;
    private int numberOfPrints;
    private String reason;
    private int printedUnitTypeId;
    private long printedUnitId;

    public PrintAudit() {
    }

    public PrintAudit(long printAuditId, Date transactionDate, int userId, int moduleId, int actionTypeId, int numberOfPrints, String reason, int printedUnitTypeId, long printedUnitId) {
        this.printAuditId = printAuditId;
        this.transactionDate = transactionDate;
        this.userId = userId;
        this.moduleId = moduleId;
        this.actionTypeId = actionTypeId;
        this.numberOfPrints = numberOfPrints;
        this.reason = reason;
        this.printedUnitTypeId = printedUnitTypeId;
        this.printedUnitId = printedUnitId;
    }

    public long getPrintAuditId() {
        return printAuditId;
    }

    public void setPrintAuditId(long printAuditId) {
        this.printAuditId = printAuditId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(int actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public int getNumberOfPrints() {
        return numberOfPrints;
    }

    public void setNumberOfPrints(int numberOfPrints) {
        this.numberOfPrints = numberOfPrints;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPrintedUnitTypeId() {
        return printedUnitTypeId;
    }

    public void setPrintedUnitTypeId(int printedUnitTypeId) {
        this.printedUnitTypeId = printedUnitTypeId;
    }

    public long getPrintedUnitId() {
        return printedUnitId;
    }

    public void setPrintedUnitId(long printedUnitId) {
        this.printedUnitId = printedUnitId;
    }

    @Override
    public String toString() {
        return "PrintAudit{" +
                "printAuditId=" + printAuditId +
                ", transactionDate=" + transactionDate +
                ", userId=" + userId +
                ", moduleId=" + moduleId +
                ", actionTypeId=" + actionTypeId +
                ", numberOfPrints=" + numberOfPrints +
                ", reason='" + reason + '\'' +
                ", printedUnitTypeId=" + printedUnitTypeId +
                ", printedUnitId=" + printedUnitId +
                '}';
    }
}
