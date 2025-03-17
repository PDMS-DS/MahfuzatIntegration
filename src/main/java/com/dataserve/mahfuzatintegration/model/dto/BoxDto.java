package com.dataserve.mahfuzatintegration.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoxDto {
    private Long boxId;
    private String nameAr;
    private String nameEn;
    private String name;
    private Long capacity;
    private Long serial;
    private Date addedOn;
    private Date date;
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