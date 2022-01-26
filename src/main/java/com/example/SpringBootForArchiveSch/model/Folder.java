package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "FOLDER")
public class Folder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FOLDER_ID", nullable = false)
    private Long folderId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


    @Column(name = "BOX_ID", nullable = true)
    private Long boxId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;


    @Column(name = "ADDED_ON", nullable = true)
    private Date addedOn;

    @Column(name = "CLASSIFICATION_ID", nullable = true , insertable = false , updatable = false)
    private Long classificationId;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;


    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name = "CLASSIFICATION_ID")
    private Classifications classifications;


    public Folder() {
    }


    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
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

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
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

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folderId=" + folderId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", boxId=" + boxId +
                ", capacity=" + capacity +
                ", addedOn=" + addedOn +
                ", classificationId=" + classificationId +
                ", serial=" + serial +
                '}';
    }
}
