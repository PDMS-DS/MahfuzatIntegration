package com.dataserve.archivemanagement.model;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class Users {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "UserArname", nullable = true)
    private String userArName;
    
    @Column(name = "UserEnName", nullable = true)
    private String userEnName;

    @Column(name = "UsernameLDAP", nullable = true)
    private String userNameLdap;
    
    @Column(name = "IsLogin", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isLogin;
    
    @Column(name = "IsActive", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;
    
   
//    
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.ALL
//            })
//    @JoinTable(
//            name = "USER_DEPARTMENT",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "DEPARTMENT_ID"))
//    Set<Departments> depts = new HashSet<>();

    
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.ALL
//            })
//    @JoinTable(
//            name = "USER_GROUPS",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
//    Set<Groups> groups = new HashSet<>();
// 
  

//	public Set<Departments> getDepts() {
//		return depts;
//	}
//
//	public void setDepts(Set<Departments> depts) {
//		this.depts = depts;
//	}
//
//	public Set<Groups> getGroups() {
//		return groups;
//	}
//
//	public void setGroups(Set<Groups> groups) {
//		this.groups = groups;
//	}

	
    
}
