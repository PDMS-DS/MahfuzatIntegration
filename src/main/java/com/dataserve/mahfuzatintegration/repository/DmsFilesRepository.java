package com.dataserve.mahfuzatintegration.repository;

import com.dataserve.mahfuzatintegration.model.DmsFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DmsFilesRepository extends JpaRepository<DmsFiles, Long> {

    Long countByFolderNo(Long folderId);

    DmsFiles findByDocumentId(String documentId);


}
