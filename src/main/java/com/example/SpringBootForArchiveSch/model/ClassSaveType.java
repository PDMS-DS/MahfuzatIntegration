package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class ClassSaveType {

    private int classSaveId;
    private String classSaveArName;
    private String classSaveEnName;

    public ClassSaveType() {
    }

    public ClassSaveType(int classSaveId, String classSaveArName, String classSaveEnName) {
        this.classSaveId = classSaveId;
        this.classSaveArName = classSaveArName;
        this.classSaveEnName = classSaveEnName;
    }

    public int getClassSaveId() {
        return classSaveId;
    }

    public void setClassSaveId(int classSaveId) {
        this.classSaveId = classSaveId;
    }

    public String getClassSaveArName() {
        return classSaveArName;
    }

    public void setClassSaveArName(String classSaveArName) {
        this.classSaveArName = classSaveArName;
    }

    public String getClassSaveEnName() {
        return classSaveEnName;
    }

    public void setClassSaveEnName(String classSaveEnName) {
        this.classSaveEnName = classSaveEnName;
    }

    @Override
    public String toString() {
        return "ClassSaveType{" +
                "classSaveId=" + classSaveId +
                ", classSaveArName='" + classSaveArName + '\'' +
                ", classSaveEnName='" + classSaveEnName + '\'' +
                '}';
    }
}
