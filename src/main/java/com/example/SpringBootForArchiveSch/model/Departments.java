package com.example.SpringBootForArchiveSch.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENTS")
public class Departments {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DEPT_ID", nullable = false)
    private Long deptId;

    @Column(name = "DEPT_AR_NAME", nullable = true)
    private String deptArName;

    @Column(name = "DEPT_EN_NAME", nullable = true)
    private String deptEnName;

    @Column(name = "ENABLED", nullable = true)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @Column(name = "DEPT_CODE", nullable = true)
    private Long deptCode;


    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<StorageCenter> storageCenter ;


    public Departments() {
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptArName() {
        return deptArName;
    }

    public void setDeptArName(String deptArName) {
        this.deptArName = deptArName;
    }

    public String getDeptEnName() {
        return deptEnName;
    }

    public void setDeptEnName(String deptEnName) {
        this.deptEnName = deptEnName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Long deptCode) {
        this.deptCode = deptCode;
    }

    public Set<StorageCenter> getStorageCenter() {
        return storageCenter;
    }

    public void setStorageCenter(Set<StorageCenter> storageCenter) {
        this.storageCenter = storageCenter;
    }
}
