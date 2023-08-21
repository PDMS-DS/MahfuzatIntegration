package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.model.dto.CreateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UpdateDocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    //    String createDocument(String token, CreateDocumentDTO document, List<MultipartFile> files);
    String createDocument(String token, String document, List<MultipartFile> files);

    public String updateDocument(String token, UpdateDocumentDTO document, List<MultipartFile> files);

    String createDocumentBase64(String token, CreateDocumentDTO document);
}
