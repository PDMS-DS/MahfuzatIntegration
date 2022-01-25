package com.example.SpringBootForArchiveSch.service;

import com.example.SpringBootForArchiveSch.model.Departments;
import com.example.SpringBootForArchiveSch.model.dto.DepartmentsDto;
import com.example.SpringBootForArchiveSch.repository.DepartmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentsServiceImpl implements DepartmentsService{

    private DepartmentsRepo departmentsRepo ;

    @Autowired
    public DepartmentsServiceImpl(DepartmentsRepo departmentsRepo) {
        this.departmentsRepo = departmentsRepo;
    }

    @Override
    public List<Departments> findAll() {
        return departmentsRepo.findAll();
    }

    @Override
    public Optional<Departments> findById(Long theId) {
        return departmentsRepo.findById(theId);
    }

    @Override
    public DepartmentsDto save(Departments theDepartments) {

        departmentsRepo.save(theDepartments);
        DepartmentsDto departmentsDto = new DepartmentsDto();
        departmentsDto.setDeptArName(theDepartments.getDeptArName());
        departmentsDto.setDeptCode(theDepartments.getDeptCode());
        departmentsDto.setDeptId(theDepartments.getDeptId());
        departmentsDto.setDeptEnName(theDepartments.getDeptEnName());
        departmentsDto.setEnabled(theDepartments.isEnabled());
        return departmentsDto;
    }

    @Override
    public void deleteById(Departments theDepartments) {
        departmentsRepo.delete(theDepartments);
    }
}
