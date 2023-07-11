package com.dataserve.archivemanagement.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dataserve.archivemanagement.model.dto.CreateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UpdateDocumentDTO;
import com.dataserve.archivemanagement.service.DocumentService;
import com.dataserve.archivemanagement.service.FileNetService;
import com.dataserve.archivemanagement.util.AuditUtil;
import com.dataserve.archivemanagement.util.LogUtil;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;

@RestController
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @PostMapping(value = "/createDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createDocument(@RequestHeader(name = "Authorization") String token, @RequestPart(value = "document") String document, @RequestPart("files") List<MultipartFile> files) {
        return new ResponseEntity<>(documentService.createDocument(token, document, files), HttpStatus.OK);

    }

    @PostMapping(value = "/updateDocument", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateDocument(@RequestHeader(name = "Authorization") String token, @RequestPart(value = "document") @Valid UpdateDocumentDTO document, @RequestPart("files") List<MultipartFile> files) {
        return new ResponseEntity<>(documentService.updateDocument(token, document, files), HttpStatus.OK);
    }


}
