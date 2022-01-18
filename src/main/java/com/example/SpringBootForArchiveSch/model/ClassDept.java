package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "CLASS_DEPT")
public class ClassDept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CLASSIFICATION_ID", nullable = false)
    private Long classificationId;

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DEPT_ID", nullable = false)
    private Long deptId;

    @Column(name = "SAVE_PERIOD", nullable = true)
    private Long savePeriod;

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CLASS_SAVE_TYPE", nullable = false)
    private Long classSaveType;



    public ClassDept() {
    }


    @Override
    public String toString() {
        return "ClassDept{" +
                "classificationId=" + classificationId +
                ", deptId=" + deptId +
                ", savePeriod=" + savePeriod +
                ", classSaveType=" + classSaveType +
                '}';
    }
}
