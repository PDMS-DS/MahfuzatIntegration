package com.example.SpringBootForArchiveSch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "GROUPS")
public class Groups {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

    @Column(name = "GROUPARNAME", nullable = true)
    private String groupArName;

    @Column(name = "GROUPENNAME", nullable = true)
    private String groupEnName;

    @Column(name = "GROUPLDAP", nullable = true)
    private String groupLdap;

    @Column(name = "ENABLED", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @Column(name = "ACTIVE", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(
            name = "GROUP_PERMISSIONS",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
    Set<Permissions> permissionsG = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.ALL
//            },mappedBy = "groups")
//    Set<Users> users = new HashSet<>();
//
//    public Set<Users> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<Users> users) {
//		this.users = users;
//	}

	public Groups() {
    }

    public Groups(Long groupId, String groupArName, String groupEnName, String groupLdap, boolean enabled, boolean active) {
        this.groupId = groupId;
        this.groupArName = groupArName;
        this.groupEnName = groupEnName;
        this.groupLdap = groupLdap;
        this.enabled = enabled;
        this.active = active;
    }




    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }


    public String getGroupArName() {
        return groupArName;
    }

    public void setGroupArName(String groupArName) {
        this.groupArName = groupArName;
    }


    public String getGroupEnName() {
        return groupEnName;
    }

    public void setGroupEnName(String groupEnName) {
        this.groupEnName = groupEnName;
    }


    public String getGroupLdap() {
        return groupLdap;
    }

    public void setGroupLdap(String groupLdap) {
        this.groupLdap = groupLdap;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Set<Permissions> getPermissionsG() {
        return permissionsG;
    }

    public void setPermissionsG(Set<Permissions> permissions) {
        this.permissionsG = permissions;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", groupArName='" + groupArName + '\'' +
                ", groupEnName='" + groupEnName + '\'' +
                ", groupLdap='" + groupLdap + '\'' +
                ", enabled=" + enabled +
                ", active=" + active +
                '}';
    }
}
