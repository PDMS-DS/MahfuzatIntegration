package com.dataserve.archivemanagement.service;



import java.util.List;
import java.util.Optional;

import com.dataserve.archivemanagement.model.Module;

public interface ModuleService {
    public List<Module> findAll();

    public Optional<Module> findById(Long theId);

    public Module save(Module theEmployee);

    public void deleteById(Module theId);
}
