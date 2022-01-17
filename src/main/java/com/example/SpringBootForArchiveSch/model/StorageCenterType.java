package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;


public class StorageCenterType {

    private int storageCenterTypeId;
    private String typeAr;
    private String typeEn;

    public StorageCenterType() {
    }

    public StorageCenterType(int storageCenterTypeId, String typeAr, String typeEn) {
        this.storageCenterTypeId = storageCenterTypeId;
        this.typeAr = typeAr;
        this.typeEn = typeEn;
    }

    public int getStorageCenterTypeId() {
        return storageCenterTypeId;
    }

    public void setStorageCenterTypeId(int storageCenterTypeId) {
        this.storageCenterTypeId = storageCenterTypeId;
    }

    public String getTypeAr() {
        return typeAr;
    }

    public void setTypeAr(String typeAr) {
        this.typeAr = typeAr;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public void setTypeEn(String typeEn) {
        this.typeEn = typeEn;
    }

    @Override
    public String toString() {
        return "StorageCenterType{" +
                "storageCenterTypeId=" + storageCenterTypeId +
                ", typeAr='" + typeAr + '\'' +
                ", typeEn='" + typeEn + '\'' +
                '}';
    }


}
