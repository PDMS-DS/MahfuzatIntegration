package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERMISSIONS")
public class Permissions {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PermissionID")
    private  Long  permissionID;

    @Column(name = "PermissionArName", nullable = true)
    private String permissionArName;

    @Column(name = "PermissionEnName", nullable = true)
    private String permissionEnName;

    @Column(name = "PermissionDescription", nullable = true)
    private String permissionDescription;

    @Column(name = "Enabled", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private  boolean  enabled;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODULE_ID")
    private Module module;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Action_TYPE_ID")
    private ActionTypes actionTypes;




    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },mappedBy = "permissionsG")
    Set<Groups> groups;


    public Permissions(Long permissionID, String permissionArName, String permissionEnName, String permissionDescription, boolean enabled, Module module, ActionTypes actionTypes) {
        this.permissionID = permissionID;
        this.permissionArName = permissionArName;
        this.permissionEnName = permissionEnName;
        this.permissionDescription = permissionDescription;
        this.enabled = enabled;
        this.module = module;
        this.actionTypes = actionTypes;
    }

    public Permissions(){

    }




    public Long getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Long permissionID) {
        this.permissionID = permissionID;
    }


    public String getPermissionArName() {
        return permissionArName;
    }

    public void setPermissionArName(String permissionArName) {
        this.permissionArName = permissionArName;
    }


    public String getPermissionEnName() {
        return permissionEnName;
    }

    public void setPermissionEnName(String permissionEnName) {
        this.permissionEnName = permissionEnName;
    }


    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }

    @JsonBackReference
    public Set<Groups> getGroups() {
        return groups;
    }


    public void setActionTypes(ActionTypes actionTypes) {
        this.actionTypes = actionTypes;
    }

    @JsonBackReference
    public ActionTypes getActionTypes() {
        return actionTypes;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @JsonBackReference
    public Module getModule() {
        return module;
    }




    @Override
    public String toString() {
        return "Permissions{" +
                "permissionID=" + permissionID +
                ", permissionArName='" + permissionArName + '\'' +
                ", permissionEnName='" + permissionEnName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
