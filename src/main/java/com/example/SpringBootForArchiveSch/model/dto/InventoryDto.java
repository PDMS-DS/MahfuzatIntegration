package com.example.SpringBootForArchiveSch.model.dto;

public class InventoryDto {

    private Long inventoryId;
    private String nameAr;
    private String nameEr;
    private long capacity;
    private long serial;

    public InventoryDto() {
    }

    public InventoryDto(Long inventoryId, String nameAr, String nameEr, long capacity, long serial) {
        this.inventoryId = inventoryId;
        this.nameAr = nameAr;
        this.nameEr = nameEr;
        this.capacity = capacity;
        this.serial = serial;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
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

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }
}
