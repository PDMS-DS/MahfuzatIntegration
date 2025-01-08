package com.dataserve.archivemanagement.controller;


import java.util.List;

import com.dataserve.archivemanagement.model.dto.CreateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UpdateDocumentDTO;
import com.dataserve.archivemanagement.service.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;

@RestController
@RequestMapping("/physicalArchive")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/createDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createDocument(@RequestHeader(name = "Authorization") String token, @RequestPart(value = "document") String document, @RequestPart("files") List<MultipartFile> files) {
        return new ResponseEntity<>(documentService.createDocument(token, document, files), HttpStatus.OK);
    }
    @PostMapping(value = "/createDocumentBase64", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createDocumentBase64(@RequestHeader(name = "Authorization") String token, @RequestBody CreateDocumentDTO document) {
        return new ResponseEntity<>(documentService.createDocumentBase64(token, document), HttpStatus.OK);

    }

    @PostMapping(value = "/updateDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateDocument(@RequestHeader(name = "Authorization") String token, @RequestPart(value = "document") @Valid UpdateDocumentDTO document, @RequestPart("files") List<MultipartFile> files) {
        return new ResponseEntity<>(documentService.updateDocument(token, document, files), HttpStatus.OK);
    }
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchOnDocument(@RequestHeader(name = "Authorization") String token, @RequestParam String documentClass,
                                              @RequestParam String searchValue) {
        return new ResponseEntity<>(documentService.searchInPropertiesAndContent(token, documentClass, searchValue), HttpStatus.OK);
    }

    @GetMapping(value = "/fileContent/{docId}")
    public ResponseEntity<?> getFileContent(@RequestHeader(name = "Authorization") String token, @PathVariable("docId") String docId) {
        return new ResponseEntity<>(documentService.getFileContent(token,docId), HttpStatus.OK);
    }

    @GetMapping(value = "/fileProperties/{docId}")
    public ResponseEntity<?> getFileProperties(@RequestHeader(name = "Authorization") String token, @PathVariable("docId") String docId) {
        return new ResponseEntity<>(documentService.findFileProperties(docId,token), HttpStatus.OK);
    }

}
