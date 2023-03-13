package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.BoxType;

public interface BoxTypeService {
    public List<BoxType> findAll();

    public Optional<BoxType> findById(Long theId);

    public BoxType save(BoxType theBoxType);

    public void deleteById(BoxType theBoxType);
}
