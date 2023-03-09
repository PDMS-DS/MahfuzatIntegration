package com.example.SpringBootForArchiveSch.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.SpringBootForArchiveSch.model.keys.ClassDeptKeys;
import com.fasterxml.jackson.annotation.JsonBackReference;

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


    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @MapsId("deptId")
    @JoinColumn(name = "DEPT_ID")
    private Departments departments;




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
        return getClassDeptKeys().equals(classDept.getClassDeptKeys()) && getClassifications().equals(classDept.getClassifications()) && getDepartments().equals(classDept.getDepartments())  && Objects.equals(getSavePeriod(), classDept.getSavePeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassDeptKeys(), getClassifications(), getDepartments(),  getSavePeriod());
    }
}
