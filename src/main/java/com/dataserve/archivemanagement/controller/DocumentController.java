package com.dataserve.archivemanagement.controller;


import java.util.List;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.APIResponseResult;
import com.dataserve.archivemanagement.model.dto.CreateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UpdateDocumentDTO;
import com.dataserve.archivemanagement.service.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/physicalArchive")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    ConfigUtil configUtil;

    @PostMapping(value = "/createDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponseResult<Object>> createDocument(
            @RequestHeader(name = "Authorization") String token,
            @RequestPart(value = "document") String document,
            @RequestPart("files") List<MultipartFile> files) {
        Object result = documentService.createDocument(token, document, files);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), configUtil.getLocalMessage("1023", null));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/createDocumentBase64", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<APIResponseResult<Object>> createDocumentBase64(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody CreateDocumentDTO document) {
        Object result = documentService.createDocumentBase64(token, document);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), configUtil.getLocalMessage("1024", null));
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/updateDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<APIResponseResult<Object>> updateDocument(
            @RequestHeader(name = "Authorization") String token,
            @RequestPart(value = "document") @Valid UpdateDocumentDTO document,
            @RequestPart("files") List<MultipartFile> files) {
        Object result = documentService.updateDocument(token, document, files);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), configUtil.getLocalMessage("1025", null));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<APIResponseResult<Object>> searchOnDocument(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam String documentClass,
            @RequestParam String searchValue) {
        Object result = documentService.searchInPropertiesAndContent(token, documentClass, searchValue);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), "Search completed successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fileContent/{docId}")
    public ResponseEntity<APIResponseResult<Object>> getFileContent(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable("docId") String docId) {
        Object result = documentService.getFileContent(token, docId);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), "File content retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fileProperties/{docId}")
    public ResponseEntity<APIResponseResult<Object>> getFileProperties(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable("docId") String docId) {
        Object result = documentService.findFileProperties(docId, token);
        APIResponseResult<Object> response = new APIResponseResult<>(result, HttpStatus.OK.value(), "File properties retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
