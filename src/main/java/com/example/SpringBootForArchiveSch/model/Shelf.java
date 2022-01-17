package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SHELF")
public class Shelf {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SHELF_ID", nullable = false)
    private Long shelfId;

    @Column(name = "LINE_ID", nullable = true)
    private int lineId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;

    @Column(name = "CAPACITY", nullable = true)
    private int capacity;

    @Column(name = "SERIAL", nullable = true)
    private int serial;

    @JsonManagedReference
    @OneToMany(mappedBy="shelf" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Box> box ;

    public Shelf() {
    }

    public Shelf(Long shelfId, int lineId, String nameAr, String nameEn, int capacity, int serial) {
        this.shelfId = shelfId;
        this.lineId = lineId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.capacity = capacity;
        this.serial = serial;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
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

    public Set<Box> getBox() {
        return box;
    }

    public void setBox(Set<Box> box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfId=" + shelfId +
                ", lineId=" + lineId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
