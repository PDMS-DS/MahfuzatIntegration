package com.dataserve.mahfuzatintegration.model;



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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOX_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class BoxType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BOX_TYPE_ID", nullable = false)
    private Long boxTypeId;

    @Column(name = "BOX_TYPE_AR", nullable = true)
    private String boxTypeAr;

    @Column(name = "BOX_TYPE_EN", nullable = true)
    private String boxTypeEn;


    @OneToMany(mappedBy="boxType" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Box> box ;

    public BoxType(Long boxTypeId, String boxTypeAr, String boxTypeEn) {
        this.boxTypeId = boxTypeId;
        this.boxTypeAr = boxTypeAr;
        this.boxTypeEn = boxTypeEn;
    }

   
}
