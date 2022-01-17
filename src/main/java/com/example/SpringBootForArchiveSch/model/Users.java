package com.example.SpringBootForArchiveSch.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {


    private Long userId;
    private String userArName;
    private String userEnName;
    private String userNameLdap;
    private boolean isLogin;
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

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "UserArname", nullable = true)
    public String getUserArName() {
        return userArName;
    }

    public void setUserArName(String userArName) {
        this.userArName = userArName;
    }

    @Column(name = "UserEnName", nullable = true)
    public String getUserEnName() {
        return userEnName;
    }

    public void setUserEnName(String userEnName) {
        this.userEnName = userEnName;
    }

    @Column(name = "UsernameLDAP", nullable = true)
    public String getUserNameLdap() {
        return userNameLdap;
    }

    public void setUserNameLdap(String userNameLdap) {
        this.userNameLdap = userNameLdap;
    }

    @Column(name = "IsLogin", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Column(name = "IsActive", nullable = true )
    @Type(type = "org.hibernate.type.NumericBooleanType")
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
