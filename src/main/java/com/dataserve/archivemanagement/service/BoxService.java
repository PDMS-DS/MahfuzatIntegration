package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.BoxDto;


import java.util.List;
public interface BoxService {

    public List<Box> findAll();

     Box findById(Long theId);
    
     BoxDto findBySerial(Long serial);




}
