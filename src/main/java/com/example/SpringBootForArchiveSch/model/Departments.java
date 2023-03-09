package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.HashSet;
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
    private String deptCode;

    @JsonManagedReference
    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<StorageCenter> storageCenter ;

    @JsonManagedReference
    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ClassDept> classDept ;

//    @ManyToMany(mappedBy = "depts")
//    Set<Users> users = new HashSet<>();
//
//    public Set<Users> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<Users> users) {
//		this.users = users;
//	}

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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Set<StorageCenter> getStorageCenter() {
        return storageCenter;
    }

    public void setStorageCenter(Set<StorageCenter> storageCenter) {
        this.storageCenter = storageCenter;
    }

    public Set<ClassDept> getClassDept() {
        return classDept;
    }

    public void setClassDept(Set<ClassDept> classDept) {
        this.classDept = classDept;
    }
}
