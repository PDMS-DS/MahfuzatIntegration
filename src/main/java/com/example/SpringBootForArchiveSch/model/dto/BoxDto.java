package com.example.SpringBootForArchiveSch.model.dto;

public class BoxDto {
    private Long boxId;
    private String nameAr;
    private String nameEn;
    private Long capacity;
    private int serial;

    public BoxDto() {
    }

    public BoxDto(String nameAr, String nameEn, Long capacity, int serial) {
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.capacity = capacity;
        this.serial = serial;
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

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    @Override
    public String toString() {
        return "BoxDto{" +
                "boxId=" + boxId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
