package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name = "ACTION_TYPES")
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


    public ActionTypes(){

    }

    public ActionTypes(Long actionTypeId, String actionTypeNameEn, String actionTypeNameAr) {
        this.actionTypeId = actionTypeId;
        this.actionTypeNameEn = actionTypeNameEn;
        this.actionTypeNameAr = actionTypeNameAr;
    }

    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public String getActionTypeNameEn() {
        return actionTypeNameEn;
    }

    public void setActionTypeNameEn(String actionTypeNameEn) {
        this.actionTypeNameEn = actionTypeNameEn;
    }


    public String getActionTypeNameAr() {
        return actionTypeNameAr;
    }

    public void setActionTypeNameAr(String actionTypeNameAr) {
        this.actionTypeNameAr = actionTypeNameAr;
    }

    @JsonManagedReference
    public Set<Permissions> getPermissionsA() {
        return permissionsA;
    }

    public void setPermissionsA(Set<Permissions> permissions) {
        this.permissionsA = permissions;
    }

    @Override
    public String toString() {
        return "ActionTypes{" +
                "actionTypeId=" + actionTypeId +
                ", actionTypeNameEn='" + actionTypeNameEn + '\'' +
                ", actionTypeNameAr='" + actionTypeNameAr + '\'' +
                '}';
    }
}
