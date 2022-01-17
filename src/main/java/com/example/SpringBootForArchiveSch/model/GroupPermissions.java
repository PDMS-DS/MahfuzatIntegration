package com.example.SpringBootForArchiveSch.model;



//@Entity
//@Table(name = "GROUP_PERMISSIONS")
public class GroupPermissions  {

    private int groupId;
    private int permissionId;

    public GroupPermissions() {
    }

    public GroupPermissions(int groupId, int permissionId) {
        this.groupId = groupId;
        this.permissionId = permissionId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "GroupPermissions{" +
                "groupId=" + groupId +
                ", permissionId=" + permissionId +
                '}';
    }
}
