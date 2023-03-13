package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.constant.ResponseInfo;
import com.dataserve.archivemanagement.model.Folder;
import com.dataserve.archivemanagement.model.dto.FolderDto;
import com.dataserve.archivemanagement.model.dto.response.FolderResponse;
import com.dataserve.archivemanagement.repository.FolderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService{

    private FolderRepo folderRepo ;

    @Autowired
    public FolderServiceImpl(FolderRepo folderRepo) {
        this.folderRepo = folderRepo;
    }


    @Override
    public List<Folder> findAll() {
        return folderRepo.findAll();
    }

    @Override
    public FolderResponse findById(Long theId) {
    	FolderResponse  response = new FolderResponse();
    	try {
    		Optional<Folder> data = folderRepo.findById(theId);
        	
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
    public FolderResponse findBySerial(Long serial) {
    	FolderResponse  response = new FolderResponse();
    	try {
    		List<Folder> dataList = folderRepo.findBySerial(serial);
    		if (!dataList.isEmpty()) {
        		response.setResponse(dataList.get(0));
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

    @Override
    public FolderDto save(Folder theFolder) {
        theFolder.setAddedOn(new Date());
        folderRepo.save(theFolder);
        FolderDto folderDto = new FolderDto();
        folderDto.setFolderId(theFolder.getFolderId());
        folderDto.setCapacity(theFolder.getCapacity());
        folderDto.setNameAr(theFolder.getNameAr());
        folderDto.setSerial(theFolder.getSerial());
        folderDto.setNameEn(theFolder.getNameEn());
        folderDto.setBoxId(theFolder.getBoxId());
        folderDto.setAddedOn(theFolder.getAddedOn());
        return folderDto;
    }

    @Override
    public void deleteById(Folder theFolder) {
        folderRepo.delete(theFolder);
    }
}
