package com.dataserve.mahfuzatintegration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STORAGE_CENTER")
@Getter
@Setter
@NoArgsConstructor
public class StorageCenter {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CENTER_ID", nullable = false)
    private Long centerId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;

    @Column(name = "IS_ACTIVE", nullable = true)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;

    @Column(name = "CENTER_TYPE_ID", nullable = false , insertable = false , updatable = false)
    private Long centerTypeId;

    @Column(name = "DEPT_ID", nullable = true , insertable = false , updatable = false)
    private Long deptId;


    @OneToMany(mappedBy="storageCenter" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Inventory> inventory ;

    @JsonIgnore
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="DEPT_ID", nullable=true)
    private Departments departments;

    @JsonIgnore
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="CENTER_TYPE_ID", nullable=true)
    private StorageCenterType storageCenterType;


}
