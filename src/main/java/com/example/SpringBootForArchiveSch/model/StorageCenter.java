package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

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
    private String isActive;

    @Column(name = "CENTER_TYPE_ID", nullable = false)
    private int centerTypeId;

    @Column(name = "DEPT_ID", nullable = true)
    private int deptId;


    public StorageCenter() {
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
