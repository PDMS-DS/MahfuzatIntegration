package com.dataserve.mahfuzatintegration.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import com.dataserve.mahfuzatintegration.config.ConfigUtil;
import com.dataserve.mahfuzatintegration.exception.CustomServiceException;
import com.dataserve.mahfuzatintegration.model.AppUsers;
import com.dataserve.mahfuzatintegration.model.dto.ClassPropertiesDTO;
import com.dataserve.mahfuzatintegration.model.dto.EDSChoiceListDTO;
import com.dataserve.mahfuzatintegration.model.dto.GetClassPropertyDTO;
import com.dataserve.mahfuzatintegration.model.dto.UserDTO;
import com.dataserve.mahfuzatintegration.repository.UsersRepo;
import com.dataserve.mahfuzatintegration.security.JwtTokenUtil;
import com.dataserve.mahfuzatintegration.util.ArchiveErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataserve.mahfuzatintegration.model.Classifications;
import com.dataserve.mahfuzatintegration.repository.ClassificationsRepo;

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



