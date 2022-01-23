package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STORAGE_CENTER")
public class StorageCenter {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CENTER_ID", nullable = false)
    private Long centerId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;

    @Column(name = "IS_ACTIVE", nullable = true)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;

    @Column(name = "CENTER_TYPE_ID", nullable = false , insertable = false , updatable = false)
    private Long centerTypeId;

    @Column(name = "DEPT_ID", nullable = true , insertable = false , updatable = false)
    private Long deptId;

    @JsonManagedReference
    @OneToMany(mappedBy="storageCenter" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Inventory> inventory ;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="DEPT_ID", nullable=true)
    private Departments departments;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="CENTER_TYPE_ID", nullable=true)
    private StorageCenterType storageCenterType;

    public StorageCenter() {
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getCenterTypeId() {
        return centerTypeId;
    }

    public void setCenterTypeId(Long centerTypeId) {
        this.centerTypeId = centerTypeId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Set<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Inventory> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "StorageCenter{" +
                "centerId=" + centerId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", isActive=" + isActive +
                ", centerTypeId=" + centerTypeId +
                ", deptId=" + deptId +
                '}';
    }
}
