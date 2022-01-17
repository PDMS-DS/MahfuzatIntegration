package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class Departments {

    private String deptArName;
    private String deptEnName;
    private boolean enabled;
    private int deptCode;
    private int deptId;

    public Departments() {
    }

    public Departments(String deptArName, String deptEnName, boolean enabled, int deptCode, int deptId) {
        this.deptArName = deptArName;
        this.deptEnName = deptEnName;
        this.enabled = enabled;
        this.deptCode = deptCode;
        this.deptId = deptId;
    }

    public String getDeptArName() {
        return deptArName;
    }

    public void setDeptArName(String deptArName) {
        this.deptArName = deptArName;
    }

    public String getDeptEnName() {
        return deptEnName;
    }

    public void setDeptEnName(String deptEnName) {
        this.deptEnName = deptEnName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "deptArName='" + deptArName + '\'' +
                ", deptEnName='" + deptEnName + '\'' +
                ", enabled=" + enabled +
                ", deptCode=" + deptCode +
                ", deptId=" + deptId +
                '}';
    }
}
