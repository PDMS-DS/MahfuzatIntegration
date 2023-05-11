package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.model.dto.response.ClassificationResponse;
import com.dataserve.archivemanagement.repository.ClassificationsRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassificationsServiceImpl implements ClassificationsService{

    private final ClassificationsRepo classificationsRepo ;

    

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
    		// read super user name from config file 
    		// if yes return all claasification else return list according to user department 
    		
    		 List<Classifications> objectList = classificationsRepo.listClassifications();
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
