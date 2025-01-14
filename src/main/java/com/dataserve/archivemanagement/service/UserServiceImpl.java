package com.dataserve.archivemanagement.service;


import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.ConnectionException;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.Groups;
import com.dataserve.archivemanagement.model.LoginRequest;
import com.dataserve.archivemanagement.model.Permissions;
import com.dataserve.archivemanagement.model.dto.TokenResponse;
import com.dataserve.archivemanagement.repository.PermissionRepo;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
import com.dataserve.archivemanagement.util.FileNetConnection;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.exception.EngineRuntimeException;
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
    private String password;
    @Autowired
    private ConfigUtil configUtil;

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

//        String fileNetPassword = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
//        AppUsers existingUser = usersRepo.findByUserNameLdap(username).orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
        // replace it with file net api response
        // file net connection manager
        return new User(username, getPassword(),
                //getAuthorities replace it
                new ArrayList<>());
    }


    public TokenResponse findByUserName(LoginRequest loginRequest) {
        setPassword(loginRequest.getPassword());
        // Fetch the user from LDAP
        AppUsers existingUser = usersRepo.findByUserNameLdap(loginRequest.getUsername())
                .orElseThrow(() -> new CustomServiceException(
                        ArchiveErrorCode.USER_NOT_FOUND_IN_LDAP.getCode(),
                        configUtil.getLocalMessage("1003", null) // Localized message for user not found in LDAP
                ));


        TokenResponse tokenResponse = new TokenResponse();
        FileNetConnection fileNetConnection = new FileNetConnection();
        try {
           fileNetConnection.connect(loginRequest.getUsername(), loginRequest.getPassword());
            tokenResponse.setJwtToken(jwtTokenUtil.generateToken(loadUserByUsername(existingUser.getUserNameLdap()), loginRequest.getPassword()));
            if (existingUser.getGroups() != null && !existingUser.getGroups().isEmpty()) {
                tokenResponse.setPermissions(getUserPermissions(existingUser.getGroups()));
            }
            return tokenResponse;
        } catch (ConnectionException e) {
            // Handle EngineRuntimeException specifically
            if (e.getCause() instanceof EngineRuntimeException) {
                EngineRuntimeException engineException = (EngineRuntimeException) e.getCause();
                String exceptionCode = engineException.getAsErrorStack().getExceptionCode().getKey();
                if ("E_NOT_AUTHENTICATED".equals(exceptionCode)) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.BAD_CREDENTIALS.getCode(),
                            configUtil.getLocalMessage("1004", null) // Localized message for invalid credentials
                    );
                }
            }
            throw new RuntimeException(e);
        }
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
        Set<String> permissionList = null;
        Set<Map<String, Set<String>>> mapOfPermissions = new HashSet<>();
        Map<String, Set<String>> map = null;
        for (Map.Entry<String, List<Permissions>> entry : stringListMap.entrySet()) {
            map = new HashMap<>();
            permissionList = new HashSet<>();
            List<Permissions> permissionsList = entry.getValue();
            Set<String> finalPermissions = permissionList;
            permissionsList.forEach(permissions ->
                    finalPermissions.add(Arrays.stream(permissions.getPermissionEnName()
                            .toUpperCase().split(" ")).findFirst().get()));
            map.put(entry.getKey().toUpperCase(), finalPermissions);
            mapOfPermissions.add(map);
        }
        return mapOfPermissions;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

