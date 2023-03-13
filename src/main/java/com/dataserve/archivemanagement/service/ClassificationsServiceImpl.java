package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Classifications;
import com.dataserve.archivemanagement.repository.ClassificationsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationsServiceImpl implements ClassificationsService{

    private ClassificationsRepo classificationsRepo ;

    @Autowired
    public ClassificationsServiceImpl(ClassificationsRepo classificationsRepo) {
        this.classificationsRepo = classificationsRepo;
    }


    @Override
    public List<Classifications> findAll() {
        return classificationsRepo.findAll();
    }

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
}
