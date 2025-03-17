package com.dataserve.mahfuzatintegration.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STORAGE_CENTER_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class StorageCenterType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "STORAGE_CENTER_TYPE_ID", nullable = false)
    private Long storageCenterTypeId;

    @Column(name = "TYPE_AR", nullable = true)
    private String typeAr;

    @Column(name = "TYPE_EN", nullable = true)
    private String typeEn;

    @JsonManagedReference
    @OneToMany(mappedBy="storageCenterType" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<StorageCenter> storageCenter ;
}
