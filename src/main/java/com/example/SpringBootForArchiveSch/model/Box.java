package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BOX")
public class Box {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BOX_ID", nullable = false)
    private Long boxId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


//    @Column(name = "BOX_TYPE_ID", nullable = true)
//    private int boxTypeId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ADDED_ON", nullable = true)
    private Date addedOn;

    @Column(name = "SERIAL", nullable = true)
    private int serial;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE", nullable = true)
    private Date date;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="SHELF_ID", nullable=true)
    private Shelf shelf;

    @JsonIgnore
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="BOX_TYPE_ID", nullable=true)
    private BoxType boxType;

    public Box() {
    }

    public Box(Long boxId, String nameAr, String nameEn,  Long capacity, Date addedOn, int serial, Date date) {
        this.boxId = boxId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.capacity = capacity;
        this.addedOn = addedOn;
        this.serial = serial;
        this.date = date;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
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

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxId=" + boxId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", capacity=" + capacity +
                ", addedOn=" + addedOn +
                ", serial=" + serial +
                ", date=" + date +
                '}';
    }
}
