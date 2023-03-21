package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SHELF")
@Getter
@Setter
@NoArgsConstructor
public class Shelf {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SHELF_ID", nullable = false)
    private Long shelfId;

    @Column(name = "NAME_AR", nullable = true)
    private String nameAr;

    @Column(name = "NAME_EN", nullable = true)
    private String nameEn;

    @Column(name = "CAPACITY", nullable = true)
    private int capacity;

    @Column(name = "SERIAL", nullable = true)
    private int serial;

    @JsonManagedReference
    @OneToMany(mappedBy="shelf" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Box> box ;

    @JsonBackReference
    @ManyToOne(fetch  = FetchType.EAGER)
    @JoinColumn(name="LINE_ID", nullable=true)
    private Line line;

  
}
