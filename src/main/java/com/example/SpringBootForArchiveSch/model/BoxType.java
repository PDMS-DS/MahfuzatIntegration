package com.example.SpringBootForArchiveSch.model;


import javax.persistence.*;

@Entity
@Table(name = "BOX_Type")

public class BoxType {

    private int boxTypeId;
    private String boxTypeAr;
    private String boxTypeEn;

    public BoxType() {
    }

    public BoxType(int boxTypeId, String boxTypeAr, String boxTypeEn) {
        this.boxTypeId = boxTypeId;
        this.boxTypeAr = boxTypeAr;
        this.boxTypeEn = boxTypeEn;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(int boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public String getBoxTypeAr() {
        return boxTypeAr;
    }

    public void setBoxTypeAr(String boxTypeAr) {
        this.boxTypeAr = boxTypeAr;
    }

    public String getBoxTypeEn() {
        return boxTypeEn;
    }

    public void setBoxTypeEn(String boxTypeEn) {
        this.boxTypeEn = boxTypeEn;
    }

    @Override
    public String toString() {
        return "BoxType{" +
                "boxTypeId=" + boxTypeId +
                ", boxTypeAr='" + boxTypeAr + '\'' +
                ", boxTypeEn='" + boxTypeEn + '\'' +
                '}';
    }
}
