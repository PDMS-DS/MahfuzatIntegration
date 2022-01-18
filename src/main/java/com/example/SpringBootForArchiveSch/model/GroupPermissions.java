package com.example.SpringBootForArchiveSch.model;


import javax.persistence.*;

@Entity
@Table(name = "GROUP_PERMISSIONS")
public class GroupPermissions  {

    public GroupPermissions() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PERMISSION_ID", nullable = false)
    private Long permissionId;

    @Override
    public String toString() {
        return "GroupPermissions{" +
                "groupId=" + groupId +
                ", permissionId=" + permissionId +
                '}';
    }
}
