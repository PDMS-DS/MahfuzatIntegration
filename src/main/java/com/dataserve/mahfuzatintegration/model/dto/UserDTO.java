package com.dataserve.mahfuzatintegration.model.dto;

import com.dataserve.mahfuzatintegration.model.AppUsers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long userId;
    private String userArName;
    private String userEnName;
    private String password;

    private String userNameLdap;
    private boolean isLogin;
    private boolean isActive;




    public UserDTO(AppUsers user) {
        this.userId = user.getUserId();
        this.userArName = user.getUserArName();
        this.userEnName = user.getUserEnName();
        this.userNameLdap = user.getUserNameLdap();
        this.isLogin = user.isLogin();
        this.isActive = user.isActive();
    }

}
