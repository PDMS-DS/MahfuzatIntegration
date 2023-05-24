package com.dataserve.archivemanagement.service;


import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.Permissions;
import com.dataserve.archivemanagement.model.dto.TokenResponse;
import com.dataserve.archivemanagement.repository.PermissionRepo;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.repository.UsersRepo;

import lombok.RequiredArgsConstructor;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private PermissionRepo permissionRepo;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${platform.module.permission}")
    private String modulePermission;


    @Override
    public List<AppUsers> listUsers() {
        List<AppUsers> users = usersRepo.findAll();
        if (users.isEmpty())
            throw new DataNotFoundException("Users Not Found ");
        return users;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String fileNetPassword = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
        AppUsers existingUser = usersRepo.findByUserEnName(username).orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
        // replace it with file net api response
        return new User(existingUser.getUserEnName(), fileNetPassword,
                //getAuthorities replace it
                new ArrayList<>());
    }


    public TokenResponse findByUserName(LoginRequest loginRequest) {
        AppUsers existingUser = usersRepo.findByUserEnName(loginRequest.getUsername()).orElseThrow(() -> new DataNotFoundException("user not found"));
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setJwtToken(jwtTokenUtil.generateToken(loadUserByUsername(loginRequest.getUsername())));
        if (existingUser.getGroups() != null && !existingUser.getGroups().isEmpty()) {
            tokenResponse.setPermissions(getUserPermissions(existingUser.getGroups()));
        }
        return tokenResponse;

    }

    public Set<Map<String, Set<String>>> getUserPermissions(Set<Groups> groupList) {
        List<Long> longList = new ArrayList<>();
        Set<Map<String, Set<String>>> userPermissions = null;
        groupList.forEach(groups -> longList.add(groups.getGroupId()));
        Set<Permissions> mobilePermissionsList = permissionRepo.findByGroupsGroupIdInAndModuleModuleNameEnContaining(longList, modulePermission);
        if (mobilePermissionsList != null && !mobilePermissionsList.isEmpty()) {
            userPermissions = getPermissionGroupedByModulesDistinct(mobilePermissionsList);
        }
        return userPermissions;
    }

    public Set<Map<String, Set<String>>> getPermissionGroupedByModulesDistinct(Set<Permissions> permissionsSet) {
        Map<String, List<Permissions>> stringListMap = permissionsSet.stream().collect(groupingBy(permissions -> permissions.getModule().getModuleNameEn()));
        Set<String> finalPermissions = new HashSet<>();
        Set<Map<String, Set<String>>> mapOfPermissions = new HashSet<>();

        for (Map.Entry<String, List<Permissions>> entry : stringListMap.entrySet()) {
            Map<String, Set<String>> map = new HashMap<>();
            List<Permissions> permissionsList = entry.getValue();
            permissionsList.forEach(permissions ->
                    finalPermissions.add(Arrays.stream(permissions.getPermissionEnName()
                            .toUpperCase().split(" ")).findFirst().get()));
            map.put(entry.getKey().toUpperCase(), finalPermissions);
            mapOfPermissions.add(map);
        }
        return mapOfPermissions;

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
//
}

