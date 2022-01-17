package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class ClassDept {

    private int classificationId;
    private int deptId;
    private int savePeriod;
    private int classSaveType;

    public ClassDept() {
    }

    public ClassDept(int classificationId, int deptId, int savePeriod, int classSaveType) {
        this.classificationId = classificationId;
        this.deptId = deptId;
        this.savePeriod = savePeriod;
        this.classSaveType = classSaveType;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getSavePeriod() {
        return savePeriod;
    }

    public void setSavePeriod(int savePeriod) {
        this.savePeriod = savePeriod;
    }

    public int getClassSaveType() {
        return classSaveType;
    }

    public void setClassSaveType(int classSaveType) {
        this.classSaveType = classSaveType;
    }

    @Override
    public String toString() {
        return "ClassDept{" +
                "classificationId=" + classificationId +
                ", deptId=" + deptId +
                ", savePeriod=" + savePeriod +
                ", classSaveType=" + classSaveType +
                '}';
    }
}
