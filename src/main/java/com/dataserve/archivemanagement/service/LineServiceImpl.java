package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Line;
import com.dataserve.archivemanagement.model.dto.LineDto;
import com.dataserve.archivemanagement.repository.LineRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineServiceImpl implements LineService {

    private LineRepo lineRepo;

    @Autowired
    public LineServiceImpl(LineRepo lineRepo) {
        this.lineRepo = lineRepo;
    }


    @Override
    public List<Line> findAll() {
        return lineRepo.findAll();
    }

    @Override
    public Optional<Line> findById(Long theId) {
        return lineRepo.findById(theId);
    }

    @Override
    public LineDto save(Line theLine) {
        lineRepo.save(theLine);
        LineDto lineDto = new LineDto();
        lineDto.setLineId(theLine.getLineId());
        lineDto.setCapacity(theLine.getCapacity());
        lineDto.setNameAr(theLine.getNameAr());
        lineDto.setNameEn(theLine.getNameEn());
        lineDto.setSerial(theLine.getSerial());
        return lineDto;
    }

    @Override
    public void deleteById(Line theLine) {
        lineRepo.delete(theLine);

    }
}
