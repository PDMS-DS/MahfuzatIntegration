package com.dataserve.archivemanagement.service;


import java.util.List;
import java.util.Optional;


import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.repository.ClassDeptRepo;
import com.dataserve.archivemanagement.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.response.ClassificationResponse;
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
    private ClassDeptRepo classDeptRepo;

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
    public ClassificationResponse listClassifications() {
        ClassificationResponse response = new ClassificationResponse();
        List<Classifications> objectList = null;


        try {
            String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");
            if (superAdmin.equals("fntadmin")) {
                objectList = classificationsRepo.listClassifications();
            } else {
                Optional<AppUsers> user = usersRepo.findByUserEnName("FileNet");  // user for testing FileNet
                if (!user.isPresent()) {
                    Long deptId = user.get().getDepartment().getDeptId();
                    objectList = classificationsRepo.findByClassDept_Departments_DeptId(deptId);
                }
            }
            if (!objectList.isEmpty()) {
                response.setResponse(objectList);
                response.setResponseCode(String.valueOf(ResponseInfo.SUCCESS.getStatusCode()));
                response.setResponseMessage(ResponseInfo.SUCCESS.getMessage());
                response.setResponseMessageAr(ResponseInfo.SUCCESS.getMessageAr());
            } else {
                response.setResponseCode(String.valueOf(ResponseInfo.NO_DATA_FOUND.getStatusCode()));
                response.setResponseMessage(ResponseInfo.NO_DATA_FOUND.getMessage());
                response.setResponseMessageAr(ResponseInfo.NO_DATA_FOUND.getMessageAr());
            }

        } catch (Exception ex) {
            response.setResponseCode(String.valueOf(ResponseInfo.INTERNAL_SERVER_ERROR.getStatusCode()));
            response.setResponseMessage(ResponseInfo.INTERNAL_SERVER_ERROR.getMessage());
            response.setResponseMessageAr(ResponseInfo.INTERNAL_SERVER_ERROR.getMessageAr());
        }
        return response;
    }


}
