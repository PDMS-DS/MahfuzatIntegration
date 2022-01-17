package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;
import java.util.Date;


public class Audit {

    private Long auditId;
    private Date transactionDate;
    private int userId;
    private int moduleId;
    private int actionTypeId;

    public Audit() {
    }

    public Audit(Long auditId, Date transactionDate, int userId, int moduleId, int actionTypeId) {
        this.auditId = auditId;
        this.transactionDate = transactionDate;
        this.userId = userId;
        this.moduleId = moduleId;
        this.actionTypeId = actionTypeId;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
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

    @Override
    public String toString() {
        return "Audit{" +
                "auditId=" + auditId +
                ", transactionDate=" + transactionDate +
                ", userId=" + userId +
                ", moduleId=" + moduleId +
                ", actionTypeId=" + actionTypeId +
                '}';
    }
}