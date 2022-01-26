package com.example.SpringBootForArchiveSch.model;

import com.example.SpringBootForArchiveSch.model.keys.ClassDeptKeys;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CLASS_DEPT")
public class ClassDept {


    @EmbeddedId
    private ClassDeptKeys classDeptKeys;


    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @MapsId("classificationId")
    @JoinColumn(name = "CLASSIFICATION_ID")
    private Classifications classifications;

//    @Column(name = "CLASSIFICATION_ID", nullable = false)
//    private Long classificationId;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @MapsId("deptId")
    @JoinColumn(name = "DEPT_ID")
    private Departments departments;

//    @Column(name = "DEPT_ID", nullable = false)
//    private Long deptId;


    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @MapsId("classSaveTypeId")
    @JoinColumn(name = "CLASS_SAVE_TYPE")
    private ClassSaveType classSaveType;


//    @Column(name = "CLASS_SAVE_TYPE", nullable = false)
//    private Long classSaveType;


    @Column(name = "SAVE_PERIOD", nullable = true)
    private Long savePeriod;


    public ClassDept() {
    }

    public ClassDeptKeys getClassDeptKeys() {
        return classDeptKeys;
    }

    public void setClassDeptKeys(ClassDeptKeys classDeptKeys) {
        this.classDeptKeys = classDeptKeys;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public ClassSaveType getClassSaveType() {
        return classSaveType;
    }

    public void setClassSaveType(ClassSaveType classSaveType) {
        this.classSaveType = classSaveType;
    }

    public Long getSavePeriod() {
        return savePeriod;
    }

    public void setSavePeriod(Long savePeriod) {
        this.savePeriod = savePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassDept)) return false;
        ClassDept classDept = (ClassDept) o;
        return getClassDeptKeys().equals(classDept.getClassDeptKeys()) && getClassifications().equals(classDept.getClassifications()) && getDepartments().equals(classDept.getDepartments()) && getClassSaveType().equals(classDept.getClassSaveType()) && Objects.equals(getSavePeriod(), classDept.getSavePeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassDeptKeys(), getClassifications(), getDepartments(), getClassSaveType(), getSavePeriod());
    }
}
