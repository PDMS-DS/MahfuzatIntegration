package com.example.SpringBootForArchiveSch.service;
import com.example.SpringBootForArchiveSch.model.BoxType;
import com.example.SpringBootForArchiveSch.repository.BoxTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxTypeServiceImpl implements BoxTypeService {

    private BoxTypeRepo boxTypeRepo ;

    @Autowired
    public BoxTypeServiceImpl(BoxTypeRepo boxTypeRepo) {
        this.boxTypeRepo = boxTypeRepo;
    }

    @Override
    public List<BoxType> findAll() {
        return boxTypeRepo.findAll();
    }

    @Override
    public Optional<BoxType> findById(Long theId) {
        return boxTypeRepo.findById(theId);
    }

    @Override
    public BoxType save(BoxType theBoxType) {
        boxTypeRepo.save(theBoxType);
        return theBoxType;
    }

    @Override
    public void deleteById(BoxType theBoxType) {
        boxTypeRepo.delete(theBoxType);
    }
}
