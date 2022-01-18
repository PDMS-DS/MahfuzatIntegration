package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;
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

    @Column(name = "CLASSIFICATION_ID", nullable = true)
    private Long classificationId;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;





    public Folder() {
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
