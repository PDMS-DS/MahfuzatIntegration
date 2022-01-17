package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;
import java.util.Date;


public class Folder {

    private int folderId;
    private String nameAr;
    private String nameEn;
    private int boxId;
    private int capacity;
    private Date addedOn;
    private int classificationId;
    private int serial;

    public Folder() {
    }

    public Folder(int folderId, String nameAr, String nameEn, int boxId, int capacity, Date addedOn, int classificationId, int serial) {
        this.folderId = folderId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.boxId = boxId;
        this.capacity = capacity;
        this.addedOn = addedOn;
        this.classificationId = classificationId;
        this.serial = serial;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folderId=" + folderId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", boxId=" + boxId +
                ", capacity=" + capacity +
                ", addedOn=" + addedOn +
                ", classificationId=" + classificationId +
                ", serial=" + serial +
                '}';
    }
}
