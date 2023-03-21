package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BOX")
@Getter
@Setter
@NoArgsConstructor

public class Box {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BOX_ID", nullable = false)
    private Long boxId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


    @Column(name = "BOX_TYPE_ID", nullable = true , insertable = false , updatable = false)
    private Long boxTypeId;

    @Column(name = "SHELF_ID", nullable = true , insertable = false , updatable = false)
    private Long shelfId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ADDED_ON", nullable = true)
    private Date addedOn;

    @Column(name = "SERIAL", nullable = false)
    private Long serial;

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

   

    public String getPathAr(){
        String departmentName  = null;
        String storageCenterName  = null;
        String inventoryName  = null;
        String shelfName  = null;
        String lineName  = null;
        String path = null;
        if(shelf != null){
             shelfName = shelf.getNameAr();
            if(shelf.getLine() != null) {
                Line line = shelf.getLine();
                lineName =  line.getNameAr();
                if(line.getInventory() != null){
                    Inventory inventory = line.getInventory();
                    inventoryName = inventory.getNameAr();
                    if(inventory.getStorageCenter() != null){
                        StorageCenter storageCenter = inventory.getStorageCenter();
                        storageCenterName =  storageCenter.getNameAr();
                        if(storageCenter.getDepartments() != null){
                            Departments departments = storageCenter.getDepartments();
                            departmentName = departments.getDeptArName();
                        }
                    }
                }
            }
        }

        if(departmentName != null){
            path = departmentName.concat("\\").concat(storageCenterName).concat("\\").concat(inventoryName).concat("\\").concat(lineName).concat("\\").concat(shelfName).concat("\\").concat(this.getNameAr())  ;
            return path;
        }
        else if(storageCenterName != null){
            path = storageCenterName.concat("\\").concat(inventoryName).concat("\\").concat(lineName).concat("\\").concat(shelfName).concat("\\").concat(this.getNameAr()) ;;
            return path;
        }
        else if(inventoryName != null){
            path = inventoryName.concat("\\").concat(lineName).concat("\\").concat(shelfName).concat("\\").concat(this.getNameAr()) ;
            return path;
        }
        else if(lineName != null){
            path = lineName.concat("\\").concat(shelfName).concat("\\").concat(this.getNameAr()) ;
            return path;
        }
        else if(shelfName != null){
            path = shelfName.concat("\\").concat(this.getNameAr()) ;
            return path;
        }
        return path;
    }

    public String getPathEn(){
        String departmentName  = null;
        String storageCenterName  = null;
        String inventoryName  = null;
        String shelfName  = null;
        String lineName  = null;
        String path = null;
        if(shelf != null){
            shelfName = shelf.getNameEn();
            if(shelf.getLine() != null) {
                Line line = shelf.getLine();
                lineName =  line.getNameEn();
                if(line.getInventory() != null){
                    Inventory inventory = line.getInventory();
                    inventoryName = inventory.getNameEr();
                    if(inventory.getStorageCenter() != null){
                        StorageCenter storageCenter = inventory.getStorageCenter();
                        storageCenterName =  storageCenter.getNameEn();
                        if(storageCenter.getDepartments() != null){
                            Departments departments = storageCenter.getDepartments();
                            departmentName = departments.getDeptEnName();
                        }
                    }
                }
            }
        }

        if(departmentName != null){
            path = departmentName.concat("/").concat(storageCenterName).concat("/").concat(inventoryName).concat("/").concat(lineName).concat("/").concat(shelfName).concat("/").concat(this.getNameEn())  ;
            return path;
        }
        else if(storageCenterName != null){
            path = storageCenterName.concat("/").concat(inventoryName).concat("/").concat(lineName).concat("/").concat(shelfName).concat("/").concat(this.getNameEn()) ;;
            return path;
        }
        else if(inventoryName != null){
            path = inventoryName.concat("/").concat(lineName).concat("/").concat(shelfName).concat("/").concat(this.getNameEn()) ;
            return path;
        }
        else if(lineName != null){
            path = lineName.concat("/").concat(shelfName).concat("/").concat(this.getNameEn()) ;
            return path;
        }
        else if(shelfName != null){
            path = shelfName.concat("/").concat(this.getNameEn()) ;
            return path;
        }
        return path;
    }

   
}
