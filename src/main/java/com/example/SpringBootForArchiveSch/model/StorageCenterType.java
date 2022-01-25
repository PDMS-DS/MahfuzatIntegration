package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

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

    @JsonManagedReference
    @OneToMany(mappedBy="storageCenterType" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<StorageCenter> storageCenter ;



    public StorageCenterType() {
    }

    public Long getStorageCenterTypeId() {
        return storageCenterTypeId;
    }

    public void setStorageCenterTypeId(Long storageCenterTypeId) {
        this.storageCenterTypeId = storageCenterTypeId;
    }

    public String getTypeAr() {
        return typeAr;
    }

    public void setTypeAr(String typeAr) {
        this.typeAr = typeAr;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public void setTypeEn(String typeEn) {
        this.typeEn = typeEn;
    }

    public Set<StorageCenter> getStorageCenter() {
        return storageCenter;
    }

    public void setStorageCenter(Set<StorageCenter> storageCenter) {
        this.storageCenter = storageCenter;
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
