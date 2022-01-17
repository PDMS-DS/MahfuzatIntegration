package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class Inventory {

    private int inventoryId;
    private String nameAr;
    private String nameEr;
    private int capacity;
    private int centerId;
    private long serial;

    public Inventory() {
    }

    public Inventory(int inventoryId, String nameAr, String nameEr, int capacity, int centerId, long serial) {
        this.inventoryId = inventoryId;
        this.nameAr = nameAr;
        this.nameEr = nameEr;
        this.capacity = capacity;
        this.centerId = centerId;
        this.serial = serial;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEr() {
        return nameEr;
    }

    public void setNameEr(String nameEr) {
        this.nameEr = nameEr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEr='" + nameEr + '\'' +
                ", capacity=" + capacity +
                ", centerId=" + centerId +
                ", serial=" + serial +
                '}';
    }
}
