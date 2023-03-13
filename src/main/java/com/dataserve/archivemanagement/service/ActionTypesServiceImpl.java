package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.ActionTypes;
import com.dataserve.archivemanagement.repository.ActionTypesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionTypesServiceImpl implements ActionTypeService{

    private ActionTypesRepository actionTypesRepository ;

    @Autowired
    public ActionTypesServiceImpl( ActionTypesRepository theActionTypesRepository) {
        actionTypesRepository = theActionTypesRepository;
    }


    @Override
    public List<ActionTypes> findAll() {
        return actionTypesRepository.findAll();
    }

    public Optional<ActionTypes> findById(Long theId) {
        return actionTypesRepository.findById(theId);
    }

    @Override
    public ActionTypes save(ActionTypes theActionType) {
        actionTypesRepository.save(theActionType);
        return theActionType;
    }

    @Override
    public void deleteById(ActionTypes theActionType) {
        actionTypesRepository.delete(theActionType);
    }
}
