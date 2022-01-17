package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class StorageCenter {

    private int centerId;
    private String nameAr;
    private String nameEn;
    private boolean isActive;
    private int centerTypeId;
    private int deptId;

    public StorageCenter() {
    }

    public StorageCenter(int centerId, String nameAr, String nameEn, boolean isActive, int centerTypeId, int deptId) {
        this.centerId = centerId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.isActive = isActive;
        this.centerTypeId = centerTypeId;
        this.deptId = deptId;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCenterTypeId() {
        return centerTypeId;
    }

    public void setCenterTypeId(int centerTypeId) {
        this.centerTypeId = centerTypeId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "StorageCenter{" +
                "centerId=" + centerId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", isActive=" + isActive +
                ", centerTypeId=" + centerTypeId +
                ", deptId=" + deptId +
                '}';
    }
}
