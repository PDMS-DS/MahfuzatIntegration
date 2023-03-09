//package com.example.SpringBootForArchiveSch.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.util.Set;
//
////@Entity
////@Table(name = "CLASSSAVETYPE")
//public class ClassSaveType {
//
////    @Id
////    @GeneratedValue(strategy=GenerationType.AUTO)
////    @Column(name = "CALSS_SAVE_ID", nullable = false)
//    private Long calssSaveId;
//
////    @Column(name = "CLASSSAVE_AR_NAME", nullable = true)
//    private String classSaveArName;
//
////    @Column(name = "CLASSSAVE_EN_NAME", nullable = true)
//    private String classSaveEnName;
//
//
////    @JsonIgnore
////    @OneToMany(mappedBy="classSaveType" , fetch = FetchType.LAZY,
////            cascade = CascadeType.ALL)
//    private Set<ClassDept> classDept ;
//
//    public ClassSaveType() {
//    }
//
//    public ClassSaveType(Long calssSaveId, String classSaveArName, String classSaveEnName, Set<ClassDept> classDept) {
//        this.calssSaveId = calssSaveId;
//        this.classSaveArName = classSaveArName;
//        this.classSaveEnName = classSaveEnName;
//        this.classDept = classDept;
//    }
//
//    public Long getCalssSaveId() {
//        return calssSaveId;
//    }
//
//    public void setCalssSaveId(Long calssSaveId) {
//        this.calssSaveId = calssSaveId;
//    }
//
//    public String getClassSaveArName() {
//        return classSaveArName;
//    }
//
//    public void setClassSaveArName(String classSaveArName) {
//        this.classSaveArName = classSaveArName;
//    }
//
//    public String getClassSaveEnName() {
//        return classSaveEnName;
//    }
//
//    public void setClassSaveEnName(String classSaveEnName) {
//        this.classSaveEnName = classSaveEnName;
//    }
//
//    public Set<ClassDept> getClassDept() {
//        return classDept;
//    }
//
//    public void setClassDept(Set<ClassDept> classDept) {
//        this.classDept = classDept;
//    }
//}
