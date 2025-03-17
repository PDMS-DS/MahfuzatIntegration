package com.dataserve.mahfuzatintegration.model;

import com.dataserve.mahfuzatintegration.util.LocalizationUtil;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FOLDER")
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "folderId", "nameAr", "nameEn", "folderName", "boxId", "capacity", "addedOn", "serial"})
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOLDER_ID", nullable = false)
    private Long folderId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;

    @Transient
    @JsonProperty("folderName")
    public String getFolderName() {
        return LocalizationUtil.getLocalizedValue(this.nameAr, this.nameEn);
    }

    @Column(name = "BOX_ID", nullable = true)
    private Long boxId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Column(name = "ADDED_ON", nullable = true)
    private Date addedOn;

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
