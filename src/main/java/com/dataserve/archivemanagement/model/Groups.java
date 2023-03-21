package com.dataserve.archivemanagement.model;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "GROUPS")
@Getter
@Setter
@NoArgsConstructor
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

	
}
