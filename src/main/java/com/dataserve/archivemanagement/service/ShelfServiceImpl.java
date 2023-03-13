package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.Shelf;
import com.dataserve.archivemanagement.repository.ShelfRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService{

    private final ShelfRepo shelfRepo;

    @Autowired
    public ShelfServiceImpl(ShelfRepo shelfRepo) {
        this.shelfRepo = shelfRepo;
    }

    @Override
    public List<Shelf> findAll() {
        return shelfRepo.findAll();
    }

    @Override
    public Optional<Shelf> findById(Long theId) {
        return shelfRepo.findById(theId);
    }

    @Override
    public Shelf save(Shelf theShelf) {
        shelfRepo.save(theShelf);
        return theShelf;
    }

    @Override
    public void deleteById(Shelf theShelf) {
        shelfRepo.delete(theShelf);
    }
}
