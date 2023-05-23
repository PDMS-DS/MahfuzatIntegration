package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Box;


import java.util.List;
public interface BoxService {

    public List<Box> findAll();

     Box findById(Long theId);
    
     Box findBySerial(Long serial);




}
