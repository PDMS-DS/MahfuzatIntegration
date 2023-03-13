package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.ActionTypes;

public interface ActionTypeService {
    public List<ActionTypes> findAll();

    public Optional<ActionTypes>  findById(Long theId);

    public ActionTypes save(ActionTypes theEmployee);

    public void deleteById(ActionTypes theId);
}
