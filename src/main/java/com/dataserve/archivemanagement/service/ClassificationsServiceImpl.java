package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.Users;
import com.dataserve.archivemanagement.model.dto.response.ClassificationResponse;
import com.dataserve.archivemanagement.model.dto.response.UserResponse;
import com.dataserve.archivemanagement.repository.ClassificationsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationsServiceImpl implements ClassificationsService{

    private ClassificationsRepo classificationsRepo ;

    @Autowired
    public ClassificationsServiceImpl(ClassificationsRepo classificationsRepo) {
        this.classificationsRepo = classificationsRepo;
    }

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
    	try {
    		 List<Classifications> objectList = classificationsRepo.findAll();
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
    	}catch (Exception ex) {
    		response.setResponseCode(String.valueOf(ResponseInfo.INTERNAL_SERVER_ERROR.getStatusCode()));
    		response.setResponseMessage(ResponseInfo.INTERNAL_SERVER_ERROR.getMessage());
    		response.setResponseMessageAr(ResponseInfo.INTERNAL_SERVER_ERROR.getMessageAr());
        }
    	return response;
    }
}
