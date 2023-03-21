package com.dataserve.archivemanagement.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Users;
import com.dataserve.archivemanagement.model.dto.response.UserResponse;
import com.dataserve.archivemanagement.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UsersRepo usersRepo;

  
    @Override
    public UserResponse listUsers() {
    	UserResponse userResponse = new UserResponse();
    	try {
    		 List<Users> users = usersRepo.findAll();
             if (!users.isEmpty()) {
                 userResponse.setResponse(users);
                 userResponse.setResponseCode(String.valueOf(ResponseInfo.SUCCESS.getStatusCode()));
                 userResponse.setResponseMessage(ResponseInfo.SUCCESS.getMessage());
                 userResponse.setResponseMessageAr(ResponseInfo.SUCCESS.getMessageAr());
             } else {
            	 userResponse.setResponseCode(String.valueOf(ResponseInfo.NO_DATA_FOUND.getStatusCode()));
            	 userResponse.setResponseMessage(ResponseInfo.NO_DATA_FOUND.getMessage());
            	 userResponse.setResponseMessageAr(ResponseInfo.NO_DATA_FOUND.getMessageAr());
             }
    	}catch (Exception ex) {
    		userResponse.setResponseCode(String.valueOf(ResponseInfo.INTERNAL_SERVER_ERROR.getStatusCode()));
    		userResponse.setResponseMessage(ResponseInfo.INTERNAL_SERVER_ERROR.getMessage());
    		userResponse.setResponseMessageAr(ResponseInfo.INTERNAL_SERVER_ERROR.getMessageAr());
        }
    	return userResponse;
    }

   
}
