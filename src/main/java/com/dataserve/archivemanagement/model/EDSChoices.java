package com.dataserve.archivemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EDS_CHOICES")
@Getter
@Setter
public class EDSChoices {
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "ID", nullable = false)
//    private Long id;
    @Column(name = "PROPERTY")
    private String property;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "OBJECTTYPE")
    private String classSymbolicName;
    @Column(name = "LISTDISPNAME")
    private String listDisplayName;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "DISPNAME")
    private String displayName;
    @Column(name = "DEPON")
    private String  depon;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "DEPVALUE")
    private String depValue;
    @Column(name = "orderN")
    private int orderNo;




}
