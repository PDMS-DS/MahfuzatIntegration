package com.example.SpringBootForArchiveSch.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "INVENTORY_ID", nullable = false)
    private Long inventoryId;


    @Column(name = "NAME_AR", nullable = false)
    private Long nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEr;

    @Column(name = "CAPACITY", nullable = true)
    private long capacity;

    @Column(name = "CENTER_ID", nullable = true)
    private long centerId;

    @Column(name = "SERIAL", nullable = true )
    private long serial;


    public Inventory() {
    }

    public Inventory(int inventoryId, String nameAr, String nameEr, int capacity, int centerId, long serial) {

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
