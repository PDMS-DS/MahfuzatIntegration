package com.dataserve.archivemanagement.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.dto.response.UserResponse;
import com.dataserve.archivemanagement.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UsersRepo usersRepo;


    @Override
    public UserResponse listUsers() {
        UserResponse userResponse = new UserResponse();
        try {
            List<AppUsers> users = usersRepo.findAll();
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
        } catch (Exception ex) {
            userResponse.setResponseCode(String.valueOf(ResponseInfo.INTERNAL_SERVER_ERROR.getStatusCode()));
            userResponse.setResponseMessage(ResponseInfo.INTERNAL_SERVER_ERROR.getMessage());
            userResponse.setResponseMessageAr(ResponseInfo.INTERNAL_SERVER_ERROR.getMessageAr());
        }
        return userResponse;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUsers> existingUser = usersRepo.findByUserEnName(username);
        // replace it with file net api response
        String fileNetPassword = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
        if (!existingUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return new User(existingUser.get().getUserEnName(), fileNetPassword,

                    //getAuthorities replace it
                    new ArrayList<>());
        }

    }

    //    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        List<GrantedAuthority> authorities
//                = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//
//        return authorities;
//    }



}
