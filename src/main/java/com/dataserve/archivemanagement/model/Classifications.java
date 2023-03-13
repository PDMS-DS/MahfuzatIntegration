package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLASSIFICTIONS")
public class Classifications {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CLASSIFICATION_ID", nullable = false)
    private Long classificationId;

    @Column(name = "CLASS_AR_NAME", nullable = true)
    private String classArName;

    @Column(name = "CLASS_EN_NAME", nullable = true)
    private String classEnName;


    @Column(name = "SYMPOLIC_NAME", nullable = true)
    private String sympolicName;

    @Column(name = "PARENT_ID", nullable = true)
    private Long parentID;

    @Column(name = "CLASS_CODE", nullable = true)
    private String classCode;

    @JsonIgnore
    @OneToMany(mappedBy="classifications" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ClassDept> classDept ;

    @JsonManagedReference
    @OneToMany(mappedBy="classifications" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Folder> folder  ;

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassArName() {
        return classArName;
    }

    public void setClassArName(String classArName) {
        this.classArName = classArName;
    }

    public String getClassEnName() {
        return classEnName;
    }

    public void setClassEnName(String classEnName) {
        this.classEnName = classEnName;
    }

    public String getSympolicName() {
        return sympolicName;
    }

    public void setSympolicName(String sympolicName) {
        this.sympolicName = sympolicName;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Set<ClassDept> getClassDept() {
        return classDept;
    }

    public void setClassDept(Set<ClassDept> classDept) {
        this.classDept = classDept;
    }

    public Classifications() {
    }









    @Override
    public String toString() {
        return "Classifications{" +
                "classificationId=" + classificationId +
                ", classArName='" + classArName + '\'' +
                ", classEnName='" + classEnName + '\'' +
                ", sympolicName='" + sympolicName + '\'' +
                ", parentID=" + parentID +
                ", classCode='" + classCode + '\'' +
                '}';
    }
}
