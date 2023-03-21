package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.response.BoxResponse;
import com.dataserve.archivemanagement.repository.BoxRepo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService{


    private final BoxRepo boxRepo;

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public BoxResponse findById(Long theId) {
    	BoxResponse  response = new BoxResponse();
    	try {
    		Optional<Box> data = boxRepo.findById(theId);
        	
        	if(data.isPresent()) {
        		response.setResponse(data);
                response.setResponseCode(String.valueOf(ResponseInfo.SUCCESS.getStatusCode()));
                response.setResponseMessage(ResponseInfo.SUCCESS.getMessage());
                response.setResponseMessageAr(ResponseInfo.SUCCESS.getMessageAr());
        	}else {
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
    
    @Override
    public BoxResponse findBySerial(Long serial) {
    	BoxResponse  response = new BoxResponse();
    	try {
    		Optional<Box> data = boxRepo.findBySerial(serial);
    		if (data.isPresent()) {
        		response.setResponse(data);
                response.setResponseCode(String.valueOf(ResponseInfo.SUCCESS.getStatusCode()));
                response.setResponseMessage(ResponseInfo.SUCCESS.getMessage());
                response.setResponseMessageAr(ResponseInfo.SUCCESS.getMessageAr());
        	}else {
        		response.setResponseCode(String.valueOf(ResponseInfo.NO_DATA_FOUND.getStatusCode()));
           	 	response.setResponseMessage(ResponseInfo.NO_DATA_FOUND.getMessage());
           	 	response.setResponseMessageAr(ResponseInfo.NO_DATA_FOUND.getMessageAr());
        	}
    	}catch (Exception ex) {
    		ex.printStackTrace();
    		response.setResponseCode(String.valueOf(ResponseInfo.INTERNAL_SERVER_ERROR.getStatusCode()));
    		response.setResponseMessage(ResponseInfo.INTERNAL_SERVER_ERROR.getMessage());
    		response.setResponseMessageAr(ResponseInfo.INTERNAL_SERVER_ERROR.getMessageAr());
        }
    	return response;
    	
    }


    
}
