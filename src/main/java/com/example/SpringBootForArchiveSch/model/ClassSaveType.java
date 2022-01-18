package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

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


    public ClassSaveType() {
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
