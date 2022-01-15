package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.ActionTypes;

import java.util.List;
import java.util.Optional;

public interface ActionTypeService {
    public List<ActionTypes> findAll();

    public Optional<ActionTypes>  findById(Long theId);

    public ActionTypes save(ActionTypes theEmployee);

    public void deleteById(ActionTypes theId);
}
