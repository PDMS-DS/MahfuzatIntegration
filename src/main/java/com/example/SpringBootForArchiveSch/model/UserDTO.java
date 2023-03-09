package com.example.SpringBootForArchiveSch.model;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
	
	
	public UserDTO(Users user) {
		
		this.userId = user.getUserId();
		this.userArName = user.getUserArName();
	    this.userEnName = user.getUserEnName();
	    this.userNameLdap = user.getUserNameLdap();
	    this.isLogin = user.isLogin();
	    this.isActive = user.isActive();
	}


    private Long userId;
    private String userArName;
    private String userEnName;

    private String userNameLdap;
    private boolean isLogin;
    private boolean isActive;
    
    private Set<Groups> groups = new HashSet<>();

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

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}

    
    
}
