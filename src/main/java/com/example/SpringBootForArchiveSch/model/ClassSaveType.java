package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLASSSAVETYPE")
public class ClassSaveType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CALSS_SAVE_ID", nullable = false)
    private Long classSaveId;

    @Column(name = "CLASSSAVE_AR_NAME", nullable = true)
    private String classSaveArName;

    @Column(name = "CLASSSAVE_EN_NAME", nullable = true)
    private String classSaveEnName;

    @JsonManagedReference
    @OneToMany(mappedBy="classSaveType" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ClassDept> classDept ;

    public ClassSaveType() {
    }

    public Long getClassSaveId() {
        return classSaveId;
    }

    public void setClassSaveId(Long classSaveId) {
        this.classSaveId = classSaveId;
    }

    public String getClassSaveArName() {
        return classSaveArName;
    }

    public void setClassSaveArName(String classSaveArName) {
        this.classSaveArName = classSaveArName;
    }

    public String getClassSaveEnName() {
        return classSaveEnName;
    }

    public void setClassSaveEnName(String classSaveEnName) {
        this.classSaveEnName = classSaveEnName;
    }

    public Set<ClassDept> getClassDept() {
        return classDept;
    }

    public void setClassDept(Set<ClassDept> classDept) {
        this.classDept = classDept;
    }

    @Override
    public String toString() {
        return "ClassSaveType{" +
                "classSaveId=" + classSaveId +
                ", classSaveArName='" + classSaveArName + '\'' +
                ", classSaveEnName='" + classSaveEnName + '\'' +
                '}';
    }
}
