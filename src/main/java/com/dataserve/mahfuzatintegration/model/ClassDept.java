package com.dataserve.mahfuzatintegration.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.dataserve.mahfuzatintegration.model.keys.ClassDeptKeys;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLASS_DEPT")
@Getter
@Setter
@NoArgsConstructor
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


   
}
