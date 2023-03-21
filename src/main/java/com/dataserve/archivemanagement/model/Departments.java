package com.dataserve.archivemanagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEPARTMENTS")
@Getter
@Setter
@NoArgsConstructor
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
    private String deptCode;

    @JsonManagedReference
    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<StorageCenter> storageCenter ;

    @JsonManagedReference
    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<ClassDept> classDept ;

    @JsonManagedReference
    @OneToMany(mappedBy="departments" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<DmsFiles> dmsFiles  ;
    

	
    
    
}
