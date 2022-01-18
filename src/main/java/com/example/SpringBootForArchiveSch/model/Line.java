package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "LINE")
public class Line {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LINE_ID", nullable = false)
    private Long lineId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


    @Column(name = "INVENTORY_ID", nullable = true)
    private Long inventoryId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;



    public Line() {
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
