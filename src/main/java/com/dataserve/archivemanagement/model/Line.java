package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LINE")
@Getter
@Setter
@NoArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LINE_ID", nullable = false)
    private Long lineId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;


    @Column(name = "INVENTORY_ID", nullable = true , updatable = false , insertable = false)
    private Long inventoryId;

    @Column(name = "CAPACITY", nullable = true)
    private Long capacity;

    @Column(name = "SERIAL", nullable = true)
    private Long serial;

    @JsonManagedReference
    @OneToMany(mappedBy="line" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Shelf> shelf ;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="INVENTORY_ID", nullable=true)
    private Inventory inventory;

   
}
