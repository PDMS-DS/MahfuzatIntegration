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
    private String boxNameEn;
    private String boxNameAr;
    private Long capacity;
    private Date addedOn;
    private Long serial;
    private String boxTypeNameAr;
    private String boxTypeNameEn;
    private String shelfAr;
    private String shelfEn;
    private String lineAr;
    private String lineEn;
    private String inventoryAr;
    private String inventoryEn;
    private String centerAr;
    private String centerEn;
    private String departmentNameAr;
    private String departmentNameEn;
    private String pathEn;
    private String pathAr;



}
