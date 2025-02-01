package com.dataserve.archivemanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FolderDto {
    private Long folderId;
    private String nameAr;
    private String nameEn;
    private String name;
    private Long capacity;
    private Date addedOn;
    private Long serial;

    private String boxNameAr;
    private String boxNameEn;
    private String boxName;

    private String boxTypeNameAr;
    private String boxTypeNameEn;
    private String boxTypeName;

    private String shelfAr;
    private String shelfEn;
    private String shelf;

    private String lineAr;
    private String lineEn;
    private String line;

    private String inventoryAr;
    private String inventoryEn;
    private String inventory;

    private String centerAr;
    private String centerEn;
    private String center;

    private String departmentNameAr;
    private String departmentNameEn;
    private String departmentName;

    private String pathEn;
    private String pathAr;
}