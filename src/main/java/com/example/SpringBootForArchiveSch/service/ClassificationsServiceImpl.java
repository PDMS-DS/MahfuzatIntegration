package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Classifications;
import com.example.SpringBootForArchiveSch.repository.ClassificationsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClassificationsServiceImpl implements ClassificationsService{

    private ClassificationsRepo classificationsRepo ;

    @Autowired
    public ClassificationsServiceImpl(ClassificationsRepo classificationsRepo) {
        this.classificationsRepo = classificationsRepo;
    }


    @Override
    public List<Classifications> findAll() {
        return null;
    }

    @Override
    public Optional<Classifications> findById(Long theId) {
        return Optional.empty();
    }

    @Override
    public Classifications save(Classifications theClassifications) {
        return null;
    }

    @Override
    public void deleteById(Classifications theClassifications) {

    }
}
