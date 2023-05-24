package com.dataserve.archivemanagement.service;


import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.dto.TokenResponse;

import java.util.List;

public interface UserService {

    List<AppUsers> listUsers();

     TokenResponse findByUserName(LoginRequest loginRequest);


}
