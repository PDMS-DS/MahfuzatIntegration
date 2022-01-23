package com.example.SpringBootForArchiveSch.model.dto;

public class StorageCenterDto {
    private Long centerId;
    private String nameAr;
    private String nameEn;
    private boolean isActive;

    public StorageCenterDto() {
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
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
}
