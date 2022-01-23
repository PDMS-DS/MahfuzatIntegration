package com.example.SpringBootForArchiveSch.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOX_Type")
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

    public BoxType() {
    }


    public Long getBoxTypeId() {
        return boxTypeId;
    }

    public void setBoxTypeId(Long boxTypeId) {
        this.boxTypeId = boxTypeId;
    }

    public String getBoxTypeAr() {
        return boxTypeAr;
    }

    public void setBoxTypeAr(String boxTypeAr) {
        this.boxTypeAr = boxTypeAr;
    }

    public String getBoxTypeEn() {
        return boxTypeEn;
    }

    public void setBoxTypeEn(String boxTypeEn) {
        this.boxTypeEn = boxTypeEn;
    }

    public Set<Box> getBox() {
        return box;
    }

    public void setBox(Set<Box> box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "BoxType{" +
                "boxTypeId=" + boxTypeId +
                ", boxTypeAr='" + boxTypeAr + '\'' +
                ", boxTypeEn='" + boxTypeEn + '\'' +
                '}';
    }
}
