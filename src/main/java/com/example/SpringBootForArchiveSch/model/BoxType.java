package com.example.SpringBootForArchiveSch.model;


import javax.persistence.*;

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



    public BoxType() {
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
