package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class Line {

    private int lineId;
    private String nameAr;
    private String nameEn;
    private int inventoryId;
    private int capacity;
    private int serial;

    public Line() {
    }

    public Line(int lineId, String nameAr, String nameEn, int inventoryId, int capacity, int serial) {
        this.lineId = lineId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.inventoryId = inventoryId;
        this.capacity = capacity;
        this.serial = serial;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
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

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineId=" + lineId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", inventoryId=" + inventoryId +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
