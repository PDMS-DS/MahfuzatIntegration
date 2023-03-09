package com.example.SpringBootForArchiveSch.model;

import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "USERS")
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
    
    public Users() {
    }

    public Users(Long userId, String userArName, String userEnName, String userNameLdap, boolean isLogin, boolean isActive) {
        this.userId = userId;
        this.userArName = userArName;
        this.userEnName = userEnName;
        this.userNameLdap = userNameLdap;
        this.isLogin = isLogin;
        this.isActive = isActive;
    }

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

	
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

   
    public String getUserArName() {
        return userArName;
    }

    public void setUserArName(String userArName) {
        this.userArName = userArName;
    }

    
    public String getUserEnName() {
        return userEnName;
    }

    public void setUserEnName(String userEnName) {
        this.userEnName = userEnName;
    }

    
    public String getUserNameLdap() {
        return userNameLdap;
    }

    public void setUserNameLdap(String userNameLdap) {
        this.userNameLdap = userNameLdap;
    }

   
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userArName='" + userArName + '\'' +
                ", userEnName='" + userEnName + '\'' +
                ", userNameLdap='" + userNameLdap + '\'' +
                ", isLogin=" + isLogin +
                ", isActive=" + isActive +
                '}';
    }
}
