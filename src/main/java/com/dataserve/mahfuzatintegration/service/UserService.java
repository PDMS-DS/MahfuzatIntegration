package com.dataserve.mahfuzatintegration.service;


import com.dataserve.mahfuzatintegration.model.AppUsers;
import com.dataserve.mahfuzatintegration.model.LoginRequest;
import com.dataserve.mahfuzatintegration.model.dto.TokenResponse;

import java.util.List;

public interface UserService {

    List<AppUsers> listUsers();

     TokenResponse findByUserName(LoginRequest loginRequest);



}
