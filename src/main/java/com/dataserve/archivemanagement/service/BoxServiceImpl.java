package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.model.Box;

import com.dataserve.archivemanagement.repository.BoxRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxRepo boxRepo;

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public Box findById(Long id) {
        return boxRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Box Not Found by id value: " + id));
    }

    @Override
    public Box findBySerial(Long serial) {
        return boxRepo.findBySerial(serial).orElseThrow(() ->
                new DataNotFoundException("Box Not Found by serial value: " + serial));
    }

}
