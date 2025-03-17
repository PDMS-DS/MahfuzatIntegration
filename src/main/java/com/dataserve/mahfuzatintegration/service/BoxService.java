package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.model.Box;
import com.dataserve.mahfuzatintegration.model.dto.BoxDto;


import java.util.List;
public interface BoxService {

    public List<Box> findAll();

     Box findById(Long theId);
    
     BoxDto findBySerial(Long serial);




}
