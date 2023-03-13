package com.dataserve.archivemanagement.model.dto;

public class LineDto {

    private Long lineId;
    private String nameAr;
    private String nameEn;
    private Long capacity;
    private Long serial;

    public LineDto() {
    }

    public LineDto(Long lineId, String nameAr, String nameEn, Long capacity, Long serial) {
        this.lineId = lineId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.capacity = capacity;
        this.serial = serial;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
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

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "LineDto{" +
                "lineId=" + lineId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
