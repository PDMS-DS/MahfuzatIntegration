package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataRequiredException;
import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.dto.CreateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UpdateDocumentDTO;
import com.dataserve.archivemanagement.model.dto.UserDTO;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.util.AuditUtil;
import com.dataserve.archivemanagement.util.LogUtil;
import com.dataserve.archivemanagement.util.SaveType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filenet.api.core.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("${temp-files-path}")
    private String tempFilePath;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private FileNetService fnService;

    @Override
    public String createDocument(String token, String document, List<MultipartFile> files) {
        try {

            CreateDocumentDTO documentDTO = new ObjectMapper().readValue(document, CreateDocumentDTO.class);

            if (documentDTO.getSaveType().equals(SaveType.SCAN_FILE) && documentDTO.getFolderNo() == null) {
                throw new DataRequiredException("Folder Number is Required ");
            }
            if (documentDTO.getSaveType().equals(SaveType.UPLOAD_FILE) && documentDTO.getNumOfPages() == null) {
                throw new DataRequiredException("Number Of Pages is Required ");
            }


            List<File> filesList = new ArrayList<>();
            files.stream().forEach(f -> {
                try {
                    byte[] bytes = f.getBytes();
                    File file = Files.write(Paths.get(tempFilePath + File.separator + f.getOriginalFilename()), bytes).toFile();
                    filesList.add(file);
                } catch (IOException e) {
                    LogUtil.error("Failed to recieve attachments", e);
                }
            });
            if (files.size() != filesList.size()) {
                throw new DataRequiredException("Failed to recieve attached files");
            }
            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            Document newDocument = fnService.createDocument(loginUser.getUserNameLdap(), loginUser.getPassword(), documentDTO, filesList);

            String result = newDocument.get_Id().toString();

            LogUtil.info("Document'" + newDocument.get_Id() + "' has been created throw integration");
            ObjectMapper mapper = new ObjectMapper();
//            String str = mapper.writeValueAsString(document);
            JSONObject params = new JSONObject(document);

            params.put("files", filesList);
            AuditUtil audit = new AuditUtil("/createDocument", loginUser.getUserNameLdap(), params, result);
            audit.run();
            return result;

        } catch (DataRequiredException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new ServiceException("Failed to create document", e);
        }
    }


    public String updateDocument(String token, UpdateDocumentDTO document, List<MultipartFile> files) {

        try {
            List<File> filesList = new ArrayList<>();
            if (files != null && files.size() > 0) {
                files.stream().forEach(f -> {
                    try {
                        byte[] bytes = f.getBytes();
                        File file = Files.write(Paths.get(tempFilePath + File.separator + f.getOriginalFilename()), bytes).toFile();
                        filesList.add(file);
                    } catch (IOException e) {
                        LogUtil.error("Failed to recieve attachments", e);
                    }
                });

                if (files.size() != filesList.size()) {
                    throw new DataRequiredException("Failed to recieve attached files");
                }
            }

            Document resultDocument = null;
            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            if (document.getProperties() != null && document.getProperties().size() > 0) {
                resultDocument = fnService.updateDocumentProperties(loginUser.getUserNameLdap(), loginUser.getPassword(), document);
            }
            if (filesList != null && filesList.size() > 0) {
                resultDocument = fnService.updateDocumentContents(loginUser.getUserNameLdap(), loginUser.getPassword(), document.getGuid(), filesList);
            }

            if (resultDocument == null) {
                throw new DataRequiredException("Nothing sent to update");
            }

            String result = resultDocument.get_Id().toString();
            LogUtil.info("Document '" + result + "' has been updated throw integration");

            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(document);
            JSONObject params = new JSONObject(str);
            params.put("files", filesList);
            AuditUtil audit = new AuditUtil("/updateDocument", loginUser.getUserNameLdap(), params, result);
            audit.run();
            return result;
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new ServiceException("Failed to update document", e);
        }
    }
}
