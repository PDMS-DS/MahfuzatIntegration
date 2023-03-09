//package com.example.SpringBootForArchiveSch.service;
//
//import com.example.SpringBootForArchiveSch.model.ClassDept;
//import com.example.SpringBootForArchiveSch.model.ClassSaveType;
//import com.example.SpringBootForArchiveSch.repository.ClassDeptRepo;
//import com.example.SpringBootForArchiveSch.repository.ClassSaveTypeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ClassSaveTypeServiceImpl implements ClassSaveTypeService{
//
//    private ClassSaveTypeRepo classSaveTypeRepo ;
//
//    @Autowired
//    public ClassSaveTypeServiceImpl(ClassSaveTypeRepo classSaveTypeRepo) {
//        this.classSaveTypeRepo = classSaveTypeRepo;
//    }
//
//    @Override
//    public List<ClassSaveType> findAll() {
//        return classSaveTypeRepo.findAll();
//    }
//
//    @Override
//    public Optional<ClassSaveType> findById(Long theId) {
//        return classSaveTypeRepo.findById(theId);
//    }
//
//    @Override
//    public ClassSaveType save(ClassSaveType theClassSaveType) {
//        classSaveTypeRepo.save(theClassSaveType);
//        return theClassSaveType;
//    }
//
//    @Override
//    public void deleteById(ClassSaveType theClassSaveType) {
//        classSaveTypeRepo.delete(theClassSaveType);
//    }
//}
