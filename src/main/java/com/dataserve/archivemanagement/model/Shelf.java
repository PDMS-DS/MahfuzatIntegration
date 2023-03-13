package com.dataserve.archivemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SHELF")
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

    public Shelf() {
    }

    public Shelf(Long shelfId,  String nameAr, String nameEn, int capacity, int serial) {
        this.shelfId = shelfId;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.capacity = capacity;
        this.serial = serial;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }



    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public Set<Box> getBox() {
        return box;
    }

    public void setBox(Set<Box> box) {
        this.box = box;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfId=" + shelfId +
                ", nameAr='" + nameAr + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", capacity=" + capacity +
                ", serial=" + serial +
                '}';
    }
}
