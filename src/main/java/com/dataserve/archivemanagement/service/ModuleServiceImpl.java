package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Module;
import com.dataserve.archivemanagement.repository.ModulesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService{
    private final ModulesRepo moduleRepo;

    @Autowired
    public ModuleServiceImpl(ModulesRepo moduleRepo) {
        this.moduleRepo = moduleRepo;
    }

    @Override
    public List<Module> findAll() {
        return moduleRepo.findAll();
    }

    @Override
    public Optional<Module> findById(Long theId) {
        return moduleRepo.findById(theId);
    }

    @Override
    public Module save(Module theModule) {
        moduleRepo.save(theModule);
        return theModule;
    }

    @Override
    public void deleteById(Module theModule) {
        moduleRepo.delete(theModule);
    }
}
