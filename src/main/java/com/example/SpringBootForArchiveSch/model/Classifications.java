package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class Classifications {

    private int classificationId;
    private String classArName;
    private String classEnName;
    private String sympolicName;

    private long parentID;
    private String classCode;


    public Classifications() {
    }

    public Classifications(int classificationId, String classArName, String classEnName, String sympolicName, long parentID, String classCode) {
        this.classificationId = classificationId;
        this.classArName = classArName;
        this.classEnName = classEnName;
        this.sympolicName = sympolicName;
        this.parentID = parentID;
        this.classCode = classCode;
    }


    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassArName() {
        return classArName;
    }

    public void setClassArName(String classArName) {
        this.classArName = classArName;
    }

    public String getClassEnName() {
        return classEnName;
    }

    public void setClassEnName(String classEnName) {
        this.classEnName = classEnName;
    }

    public String getSympolicName() {
        return sympolicName;
    }

    public void setSympolicName(String sympolicName) {
        this.sympolicName = sympolicName;
    }

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return "Classifications{" +
                "classificationId=" + classificationId +
                ", classArName='" + classArName + '\'' +
                ", classEnName='" + classEnName + '\'' +
                ", sympolicName='" + sympolicName + '\'' +
                ", parentID=" + parentID +
                ", classCode='" + classCode + '\'' +
                '}';
    }
}
