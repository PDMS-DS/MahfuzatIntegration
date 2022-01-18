package com.example.SpringBootForArchiveSch.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

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





    public Departments() {
    }


}
