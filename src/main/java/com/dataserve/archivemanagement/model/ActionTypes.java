package com.dataserve.archivemanagement.model;

import java.util.Date;
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
@Table(name = "ACTION_TYPES")
@Getter
@Setter
@NoArgsConstructor
public class ActionTypes {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ACTION_TYPE_ID", nullable = false)
    private  Long actionTypeId ;

    @Column(name = "ACTION_TYPE_NAME_EN", nullable = true)
    private String actionTypeNameEn;

    @Column(name = "ACTION_TYPE_NAME_AR", nullable = true)
    private String actionTypeNameAr;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL , mappedBy = "actionTypes")
    private Set<Permissions> permissionsA ;

}
