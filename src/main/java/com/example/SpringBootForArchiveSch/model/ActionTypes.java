package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name = "ACTION_TYPES")
public class ActionTypes {

    private  Long actionTypeId ;
    private String actionTypeNameEn;
    private String actionTypeNameAr;

    public ActionTypes(){

    }

    public ActionTypes(Long actionTypeId, String actionTypeNameEn, String actionTypeNameAr) {
        this.actionTypeId = actionTypeId;
        this.actionTypeNameEn = actionTypeNameEn;
        this.actionTypeNameAr = actionTypeNameAr;
    }



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ACTION_TYPE_ID", nullable = false)
    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }


    private Set<Permissions> permissions ;


    @Column(name = "ACTION_TYPE_NAME_EN", nullable = true)
    public String getActionTypeNameEn() {
        return actionTypeNameEn;
    }

    public void setActionTypeNameEn(String actionTypeNameEn) {
        this.actionTypeNameEn = actionTypeNameEn;
    }

    @Column(name = "ACTION_TYPE_NAME_AR", nullable = true)
    public String getActionTypeNameAr() {
        return actionTypeNameAr;
    }

    public void setActionTypeNameAr(String actionTypeNameAr) {
        this.actionTypeNameAr = actionTypeNameAr;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Action_TYPE_ID")
    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
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
