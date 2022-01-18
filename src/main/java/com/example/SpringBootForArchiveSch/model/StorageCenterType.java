package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "STORAGE_CENTER_TYPE")
public class StorageCenterType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "STORAGE_CENTER_TYPE_ID", nullable = false)
    private Long storageCenterTypeId;

    @Column(name = "TYPE_AR", nullable = true)
    private String typeAr;

    @Column(name = "TYPE_EN", nullable = true)
    private String typeEn;




    public StorageCenterType() {
    }


    @Override
    public String toString() {
        return "StorageCenterType{" +
                "storageCenterTypeId=" + storageCenterTypeId +
                ", typeAr='" + typeAr + '\'' +
                ", typeEn='" + typeEn + '\'' +
                '}';
    }


}
