package com.dataserve.archivemanagement.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.AppUsers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long userId;
	private String userArName;
	private String userEnName;

	private String userNameLdap;
	private boolean isLogin;
	private boolean isActive;

	private Set<Groups> groups = new HashSet<>();

	public UserDTO(AppUsers user) {
		this.userId = user.getUserId();
		this.userArName = user.getUserArName();
		this.userEnName = user.getUserEnName();
		this.userNameLdap = user.getUserNameLdap();
		this.isLogin = user.isLogin();
		this.isActive = user.isActive();
	}

}
