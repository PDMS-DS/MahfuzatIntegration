package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    //    String createDocument(String token, CreateDocumentDTO document, List<MultipartFile> files);
    String createDocument(String token, String document, List<MultipartFile> files);

    public String updateDocument(String token, UpdateDocumentDTO document, List<MultipartFile> files);

    String createDocumentBase64(String token, CreateDocumentDTO document);

    List<SearchDocumentDTO> searchInPropertiesAndContent(String token, String documentClass, String searchValue, String language);
    FileContentDto getFileContent(String token, String docId);

    ClassPropertiesDTO findFileProperties(String docId, String token);



}
