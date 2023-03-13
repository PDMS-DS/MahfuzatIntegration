package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.model.dto.response.BoxResponse;

import java.util.List;
import java.util.Optional;

public interface BoxService {

    public List<Box> findAll();

    public BoxResponse findById(Long theId);
    
    public BoxResponse findBySerial(Long serial);

//    public Optional<Box> findByIdAndShelfId(Long boxId , Long shelfId);

    public BoxDto save(Box theBox);

    public void deleteById(Box theBox);


}
