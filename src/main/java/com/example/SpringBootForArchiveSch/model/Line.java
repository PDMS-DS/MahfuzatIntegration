package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LINE")
public class Line {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LINE_ID", nullable = false)
    private Long lineId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


    @Column(name = "INVENTORY_ID", nullable = true , updatable = false , insertable = false)
    private Long inventoryId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;

    @JsonManagedReference
    @OneToMany(mappedBy="line" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Shelf> shelf ;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="INVENTORY_ID", nullable=true)
    private Inventory inventory;

    public Line() {
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
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

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Set<Shelf> getShelf() {
        return shelf;
    }

    public void setShelf(Set<Shelf> shelf) {
        this.shelf = shelf;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineId=" + lineId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", inventoryId=" + inventoryId +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
