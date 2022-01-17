package com.example.SpringBootForArchiveSch.service;



import com.example.SpringBootForArchiveSch.model.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {
    public List<Module> findAll();

    public Optional<Module> findById(Long theId);

    public Module save(Module theEmployee);

    public void deleteById(Module theId);
}
