package com.dataserve.archivemanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoxDto {
    private Long boxId;
    private String nameAr;
    private String nameEn;
    private Long capacity;
    private Long serial;
    private Date addedOn;
    private Date date;
    private String boxTypeNameAr;
    private Long boxTypeId;
    private String boxTypeNameEn;
    private Long shelfId;
    private String shelfAr;
    private String shelfEn;
    private Long lineId;
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
