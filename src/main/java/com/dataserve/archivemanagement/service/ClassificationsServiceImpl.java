package com.dataserve.archivemanagement.service;


import java.util.List;
import java.util.Optional;


import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.repository.UsersRepo;
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
    public List<Classifications> listClassifications() {
        List<Classifications> objectList = null;
        String userName = "";
        String superAdmin = configUtil.fetchProperties("SUPER_USER_NAME");
        if (superAdmin.equals("fntadmin")) {
            return classificationsRepo.listClassifications();
        } else {
            AppUsers user = usersRepo.findByUserEnName("FileNet").orElseThrow(

                    () -> new DataNotFoundException("User: " + userName + "Not Found"));// user for testing FileNet
            Long deptId = user.getDepartment().getDeptId();
            return classificationsRepo.findByClassDept_Departments_DeptId(deptId);
        }
    }
}



