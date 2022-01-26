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


    @Column(name = "BOX_TYPE_ID", nullable = true , insertable = false , updatable = false)
    private Long boxTypeId;

    @Column(name = "SHELF_ID", nullable = true , insertable = false , updatable = false)
    private Long shelfId;

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

    public Long getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(Long boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

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
