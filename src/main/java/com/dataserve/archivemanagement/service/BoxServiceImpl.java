package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Box;
import com.dataserve.archivemanagement.model.dto.BoxDto;
import com.dataserve.archivemanagement.repository.BoxRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxServiceImpl implements BoxService{


    private final BoxRepo boxRepo;

    @Autowired
    public BoxServiceImpl(BoxRepo boxRepo) {
        this.boxRepo = boxRepo;
    }

    @Override
    public List<Box> findAll() {
        return boxRepo.findAll();
    }

    @Override
    public Optional<Box> findById(Long theId) {
        return boxRepo.findById(theId);
    }

    @Override
    public BoxDto save(Box theBox) {

        boxRepo.save(theBox);
        BoxDto boxdto = new BoxDto();
        boxdto.setCapacity(theBox.getCapacity());
        boxdto.setNameAr(theBox.getNameAr());
        boxdto.setSerial(theBox.getSerial());
        boxdto.setNameEn(theBox.getNameEn());
        boxdto.setBoxId(theBox.getBoxId());
        return boxdto;
    }

    @Override
    public void deleteById(Box theBox) {
        boxRepo.delete(theBox);
    }
}
