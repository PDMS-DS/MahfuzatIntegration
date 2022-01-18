package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

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
