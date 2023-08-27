package com.dataserve.archivemanagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FOLDER")
@Getter
@Setter
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @Column(name = "CLASSIFICATION_ID", nullable = true , insertable = false , updatable = false)
//    private Long classificationId;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;
    @JoinColumn(name = "CENTER_ID")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StorageCenter storageCenter;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CLASSIFICATION_ID")
    private Classifications classifications;


}
