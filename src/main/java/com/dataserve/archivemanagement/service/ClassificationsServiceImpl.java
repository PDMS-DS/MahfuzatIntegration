package com.dataserve.archivemanagement.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.exception.DataRequiredException;
import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.dto.ClassPropertiesDTO;
import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;
import com.dataserve.archivemanagement.model.dto.GetClassPropertyDTO;
import com.dataserve.archivemanagement.model.dto.UserDTO;
import com.dataserve.archivemanagement.repository.UsersRepo;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
import com.dataserve.archivemanagement.util.LogUtil;
import lombok.RequiredArgsConstructor;
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
//    public List<Classifications> listClassifications(String token) {
//        try {
//            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
//            List<Classifications> objectList = null;
//            String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");
//            if (loginUser.getUserNameLdap().equals(superAdmin)) {
//                List<Classifications> b = classificationsRepo.listClassifications();
//                return b;
//            } else {
//                AppUsers user = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(
//                        () -> new CustomServiceException(
//                                ArchiveErrorCode.USER_NOT_FOUND.getCode(),
//                                configUtil.getLocalMessage("1008", null)
//                        )
//                );
//                Long deptId = user.getDepartment().getDeptId();
//                List<Classifications> b = classificationsRepo.listClassifications();
//                return b;
//
//            }
//        }catch (CustomServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new CustomServiceException(
//                    ArchiveErrorCode.BUSINESS.getCode(),
//                    e.getLocalizedMessage()
//            );
//        }
//    }
//    public List<Classifications> listClassifications(String token) {
//        try {
//            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
//            String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");
//
//            if (loginUser.getUserNameLdap().equals(superAdmin)) {
//                return classificationsRepo.listClassifications();
//            } else {
//                AppUsers user = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(
//                        () -> new CustomServiceException(
//                                ArchiveErrorCode.USER_NOT_FOUND.getCode(),
//                                configUtil.getLocalMessage("1008", null)
//                        )
//                );
//                Long deptId = user.getDepartment().getDeptId();
//                return classificationsRepo.findByClassDept_Departments_DeptId(deptId);
//
//            }
//        } catch (CustomServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new CustomServiceException(
//                    ArchiveErrorCode.BUSINESS.getCode(),
//                    e.getLocalizedMessage()
//            );
//        }
//    }


    public List<Classifications> listClassifications(String token) {
        try {
            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");

            List<Classifications> classifications;

            if (loginUser.getUserNameLdap().equals(superAdmin)) {
                classifications = classificationsRepo.listClassifications();
            } else {
                AppUsers user = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(
                        () -> new CustomServiceException(
                                ArchiveErrorCode.USER_NOT_FOUND.getCode(),
                                configUtil.getLocalMessage("1008", null)
                        )
                );

                Long deptId = user.getDepartment().getDeptId();
                classifications = classificationsRepo.findByClassDept_Departments_DeptId(deptId);
            }

            // ✅ Filter classifications to keep only unique symbolicNames

            return classifications.stream()
                    .collect(Collectors.toMap(Classifications::getSympolicName, c -> c, (existing, replacement) -> existing))
                    .values()
                    .stream()
                    .collect(Collectors.toList());

        } catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }


    public ClassPropertiesDTO findClassProperties(String symbolicName, String token) {
        try {
            ClassPropertiesDTO fnProps = fnService.getClassPropertiesById(symbolicName, token);

            if (fnProps == null || fnProps.getProperties().isEmpty()) {
                throw new CustomServiceException(
                        ArchiveErrorCode.CLASSIFICATION_PROPERTIES_NOT_FOUND.getCode(),
                        configUtil.getLocalMessage("1022", null) // Localized "Class does not have properties" message
                );
            }
            Map<String, EDSChoiceListDTO> edsProps = edsChoicesService.getClassEDSPropertesBySymbolicName(fnProps.getClassName());
            if (edsProps.size() != 0) {
                for (GetClassPropertyDTO prop : fnProps.getProperties()) {
                    if (edsProps.containsKey(prop.getSymbolicName())) {
                        EDSChoiceListDTO listDto = edsProps.get(prop.getSymbolicName());
                        if (listDto.getDependOn() != null)
                            prop.setDependOn(listDto.getDependOn());
                        prop.setEdsChoiceListName(listDto.getChoiceListName());
                        prop.setEdsChoiceListValues(listDto.getChoiceList());
                    }
                }
            }

            return fnProps;
        }catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }
}



