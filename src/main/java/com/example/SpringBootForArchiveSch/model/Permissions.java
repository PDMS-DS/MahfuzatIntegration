package com.example.SpringBootForArchiveSch.model;

import javax.persistence.*;

@Entity
@Table(name = "PERMISSIONS")
public class Permissions {
    private  Long  permissionID;
    private String permissionArName;
    private String permissionEnName;
    private String permissionDescription;
    private  int enabled;

    @ManyToOne()
    @JoinColumn(name="MODULE_ID", referencedColumnName = "MODULE_ID")
    private  Long moduleId;

    @ManyToOne()
    @JoinColumn(name="Action_TYPE_ID", referencedColumnName = "Action_TYPE_ID")
    private  Long actionTypeId;

    public Permissions(){

    }

    public Permissions(Long permissionID, String permissionArName, String permissionEnName, String permissionDescription, int enabled, Long moduleId, Long actionTypeId) {
        this.permissionID = permissionID;
        this.permissionArName = permissionArName;
        this.permissionEnName = permissionEnName;
        this.permissionDescription = permissionDescription;
        this.enabled = enabled;
        this.moduleId = moduleId;
        this.actionTypeId = actionTypeId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Long permissionID) {
        this.permissionID = permissionID;
    }

    @Column(name = "PermissionArName", nullable = true)
    public String getPermissionArName() {
        return permissionArName;
    }

    public void setPermissionArName(String permissionArName) {
        this.permissionArName = permissionArName;
    }

    @Column(name = "PermissionEnName", nullable = true)
    public String getPermissionEnName() {
        return permissionEnName;
    }

    public void setPermissionEnName(String permissionEnName) {
        this.permissionEnName = permissionEnName;
    }

    @Column(name = "PermissionDescription", nullable = true)
    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    @Column(name = "Enabled", nullable = true)
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Column(name = "MODULE_ID", nullable = false)
    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "Action_TYPE_ID", nullable = false)
//    @Column(name = "Action_TYPE_ID", nullable = false)

    @Column(name = "Action_TYPE_ID", nullable = false)
    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "permissionID=" + permissionID +
                ", permissionArName='" + permissionArName + '\'' +
                ", permissionEnName='" + permissionEnName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", enabled=" + enabled +
                ", moduleId=" + moduleId +
                ", actionTypeId=" + actionTypeId +
                '}';
    }
}
