package com.dataserve.archivemanagement.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.dto.ClassPropertiesDTO;
import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;
import com.dataserve.archivemanagement.model.dto.GetClassPropertyDTO;
import com.dataserve.archivemanagement.model.dto.UserDTO;
import com.dataserve.archivemanagement.repository.UsersRepo;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.repository.ClassificationsRepo;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassificationsServiceImpl implements ClassificationsService {
    @Autowired
    private ClassificationsRepo classificationsRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private EDSChoicesService edsChoicesService;
    @Autowired
    private FileNetService fnService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final ConfigUtil configUtil;


    @Override
    public Optional<Classifications> findById(Long theId) {
        return classificationsRepo.findById(theId);
    }

    @Override
    public Classifications save(Classifications theClassifications) {
        classificationsRepo.save(theClassifications);
        return theClassifications;
    }

    @Override
    public void deleteById(Classifications theClassifications) {
        classificationsRepo.delete(theClassifications);
    }

    @Override
    public List<Classifications> listClassifications(String token) {

        UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
        List<Classifications> objectList = null;
        String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");
        if (loginUser.getUserNameLdap().equals(superAdmin)) {
            return classificationsRepo.listClassifications();
        } else {
            AppUsers user = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(

                    () -> new DataNotFoundException("User: " + loginUser.getUserNameLdap() + "Not Found"));// user for testing FileNet
            Long deptId = user.getDepartment().getDeptId();
            return classificationsRepo.findByClassDept_Departments_DeptId(deptId);
        }
    }

    public ClassPropertiesDTO findClassProperties(String symbolicName, String token) {

        ClassPropertiesDTO fnProps = fnService.getClassPropertiesById(symbolicName, token);
        if (fnProps == null || fnProps.getProperties().size() == 0) {
            throw new DataNotFoundException("Class not found");
        }
        Map<String, EDSChoiceListDTO> edsProps = edsChoicesService.getClassEDSPropertesBySymbolicName(fnProps.getClassName());
        if (edsProps.size() != 0) {
            for (GetClassPropertyDTO prop : fnProps.getProperties()) {
                if (edsProps.containsKey(prop.getSymbolicName())) {
                    EDSChoiceListDTO listDto = edsProps.get(prop.getSymbolicName());
                    prop.setEdsChoiceListName(listDto.getChoiceListName());
                    prop.setEdsChoiceListValues(listDto.getChoiceList());
                }
            }
        }
        return fnProps;

    }
}



