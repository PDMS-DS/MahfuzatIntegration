package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "INVENTORY_ID", nullable = false)
    private Long inventoryId;


    @Column(name = "NAME_AR", nullable = false)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEr;

    @Column(name = "CAPACITY", nullable = true)
    private long capacity;

    @Column(name = "CENTER_ID", nullable = true , insertable = false , updatable = false)
    private long centerId;

    @Column(name = "SERIAL", nullable = true )
    private long serial;

    @JsonManagedReference
    @OneToMany(mappedBy="inventory" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Line> line ;

    @JsonIgnore
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="CENTER_ID", nullable=true)
    private StorageCenter storageCenter;

    public Inventory() {
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEr() {
        return nameEr;
    }

    public void setNameEr(String nameEr) {
        this.nameEr = nameEr;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public Set<Line> getLine() {
        return line;
    }

    public void setLine(Set<Line> line) {
        this.line = line;
    }

    public StorageCenter getStorageCenter() {
        return storageCenter;
    }

    public void setStorageCenter(StorageCenter storageCenter) {
        this.storageCenter = storageCenter;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEr='" + nameEr + '\'' +
                ", capacity=" + capacity +
                ", centerId=" + centerId +
                ", serial=" + serial +
                '}';
    }
}
