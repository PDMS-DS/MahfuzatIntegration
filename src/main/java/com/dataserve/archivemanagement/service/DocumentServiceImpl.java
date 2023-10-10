package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.exception.DataRequiredException;
import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.*;
import com.dataserve.archivemanagement.model.dto.*;
import com.dataserve.archivemanagement.repository.*;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.util.AuditUtil;
import com.dataserve.archivemanagement.util.FileNetConnection;
import com.dataserve.archivemanagement.util.LogUtil;
import com.dataserve.archivemanagement.util.SaveType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filenet.api.core.Document;

import java.util.Iterator;

import com.filenet.api.collection.DocumentSet;
import com.filenet.api.query.SearchScope;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.core.ObjectStore;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("${temp-files-path}")
    private String tempFilePath;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private FileNetService fnService;
    @Autowired
    private DmsFilesRepository dmsFilesRepository;
    @Autowired
    private DmsAuditRepository dmsAuditRepository;
    @Autowired
    private DMSPropertyAuditRepository dmsPropertyAuditRepository;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private ClassificationsService classificationsService;

    @Override
    public String createDocument(String token, String document, List<MultipartFile> files) {
        try {
            CreateDocumentDTO documentDTO = new ObjectMapper().readValue(document, CreateDocumentDTO.class);

            if (validationBeforeSaveDocument(documentDTO)) {
                Folder folder = folderRepo.findBySerial(Long.valueOf(documentDTO.getFolderNo())).orElseThrow(() -> new DataNotFoundException("Folder Not Found With serial No: " + documentDTO.getFolderNo()));

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
                // save Document info on DB

                String documentId = result.substring(1, result.length() - 1);

                AppUsers existingUser = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(() -> new RuntimeException("User Not Found"));


                DmsFiles dmsFiles = addDMSFilesOnDataBase(documentDTO, documentId, existingUser, folder.getFolderId());
                if (dmsFiles != null) {
                    DMSAudit dmsAudit = addDMSAuditOnDataBase(documentDTO, documentId, existingUser.getUserId(), dmsFiles.getFileId());
                    saveDMSPropertyAudit(documentDTO.getProperties(), dmsAudit.getAuditId());
                }
                return documentId;
            }

        } catch (DataRequiredException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new ServiceException("Failed to create document", e);
        }
        return null;
    }

    @Override
    public String createDocumentBase64(String token, CreateDocumentDTO document) {
        try {
            if (validationBeforeSaveDocument(document)) {
                Folder folder = folderRepo.findBySerial(Long.valueOf(document.getFolderNo())).orElseThrow(() -> new DataNotFoundException("Folder Not Found With serial No: " + document.getFolderNo()));
                UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
                Document newDocument = fnService.createDocument(loginUser.getUserNameLdap(), loginUser.getPassword(), document);

                String result = newDocument.get_Id().toString();

                LogUtil.info("Document'" + newDocument.get_Id() + "' has been created throw integration");
                JSONObject params = new JSONObject(document);
                AuditUtil audit = new AuditUtil("/createDocument", loginUser.getUserNameLdap(), params, result);
                audit.run();
                // save Document info on DB

                String documentId = result.substring(1, result.length() - 1);

                AppUsers existingUser = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap()).orElseThrow(() -> new RuntimeException("User Not Found"));

                DmsFiles dmsFiles = addDMSFilesOnDataBase(document, documentId, existingUser, folder.getFolderId());
                if (dmsFiles != null) {
                    DMSAudit dmsAudit = addDMSAuditOnDataBase(document, documentId, existingUser.getUserId(), dmsFiles.getFileId());
                    saveDMSPropertyAudit(document.getProperties(), dmsAudit.getAuditId());
                }
                return documentId;
            }

        } catch (DataRequiredException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new ServiceException(e.getMessage());
        }
        return null;
    }

    boolean validationBeforeSaveDocument(CreateDocumentDTO document) {
        if (document.getSaveType().equals(SaveType.SCAN_FILE) && document.getFolderNo() == null) {
            throw new DataRequiredException("Folder Number is Required ");
        }
        if (document.getSaveType().equals(SaveType.UPLOAD_FILE) && document.getNumOfPages() == null) {
            throw new DataRequiredException("Number Of Pages is Required ");
        }
        Folder folder = folderRepo.findBySerial(Long.valueOf(document.getFolderNo())).orElseThrow(() -> new DataNotFoundException("Folder Not Found With serial No: " + document.getFolderNo()));
        Long sumOfFiles = dmsFilesRepository.countByFolderNo(folder.getFolderId());
        if (sumOfFiles >= folder.getCapacity() && folder.getCapacity() != 0) {
            throw new ServiceException("Folder Capacity is Full");
        }
        StorageCenter storageCenter = folder.getStorageCenter();
        if (storageCenter == null) throw new DataNotFoundException("Storage Center not valid");
        StorageCenterType storageCenterType = storageCenter.getStorageCenterType();
        if (storageCenterType == null) throw new DataNotFoundException("Storage Center Type not valid");
        Classifications classifications = folder.getClassifications();
        String saveType = "";
        if (classifications != null) {
            if (storageCenterType.getStorageCenterTypeId() != 3 && !storageCenterType.getStorageCenterTypeId().equals(classifications.getSaveType())) {
                String savedType = saveType(classifications.getSaveType());
                StringBuilder message = new StringBuilder(100);
                message.append("Folder is associated to a Storage Center with save type '");
                message.append(storageCenterType.getTypeEn());
                message.append("' that doesn't match with classification save type '");
                if (saveType != null) message.append(savedType);
                message.append("'");
                throw new ServiceException(message.toString());
            }
        } else {
            throw new DataNotFoundException("Classification not valid");
        }
        return true;
    }

    public Boolean saveDMSPropertyAudit(List<PropertyDTO> properties, Long dmsAuditId) {
        if (properties != null && properties.size() > 0) {
            List<DMSPropertiesAudit> dmsPropertiesAudits = properties.stream().map(propertyDTO -> new DMSPropertiesAudit(propertyDTO.getSymbolicName(), propertyDTO.getPropertyValue(), new DMSAudit(dmsAuditId))).collect(Collectors.toList());
            dmsPropertyAuditRepository.saveAll(dmsPropertiesAudits);
        }
        return true;
    }

    private String saveType(Long type) {
        if (type == 1) {
            return "Permanent";
        } else if (type == 2) {
            return "Temporal";
        } else if (type == 3) {
            return "All";
        }
        return null;
    }

    public DmsFiles addDMSFilesOnDataBase(CreateDocumentDTO documentDTO, String documentId, AppUsers existingUser, Long folderId) {

        DmsFiles dmsFiles = new DmsFiles();
        dmsFiles.setDocumentId(documentId);
        dmsFiles.setDocumentClass(documentDTO.getDocumentClassName());
        dmsFiles.setDocumentName(documentDTO.getDocumentTitle());
        dmsFiles.setFolderNo(Long.valueOf(folderId));
        dmsFiles.setNoPages(Long.valueOf(documentDTO.getNumOfPages()));
        dmsFiles.setOCRStatus(0);
        dmsFiles.setUserId(existingUser.getUserId());
        dmsFiles.setSourceId(1);
        dmsFiles.setDepartments(existingUser.getDepartment());
        dmsFiles.setCreateDate(new Date());
        dmsFiles.setModifiedDate(new Date());
        return dmsFilesRepository.save(dmsFiles);

    }

    public DMSAudit addDMSAuditOnDataBase(CreateDocumentDTO documentDTO, String documentId, Long userId, Long fileId) {

        DMSAudit dmsAudit = new DMSAudit();
        dmsAudit.setDocumentId(documentId);
        dmsAudit.setUserId(userId);
        dmsAudit.setDocumentClass(documentDTO.getDocumentClassName());
        dmsAudit.setFileId(fileId);
        dmsAudit.setOperationId(7);
        dmsAudit.setCopiedToElastic(0);
        dmsAudit.setCreateDate(new Date());
        return dmsAuditRepository.save(dmsAudit);

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


    public String getMimeTypeFromPath(String filePath) {
        filePath = filePath.toLowerCase();
        if (filePath.endsWith("gif")) {
            return "image/gif";
        } else if (filePath.endsWith("jpg") || filePath.endsWith("jpeg") || filePath.endsWith("jp")) {
            return "image/jpeg";
        } else if (filePath.endsWith("png")) {
            return "image/png";
        } else if (filePath.endsWith("bmp")) {
            return "image/bmp";
        } else if (filePath.endsWith("tif") || filePath.endsWith("tiff")) {
            return "image/tiff";
        } else if (filePath.endsWith("pdf")) {
            return "application/pdf";
        } else if (filePath.endsWith("mda")) {
            return "application/vnd.ibm.modcap";
        } else if (filePath.endsWith("afp")) {
            return "application/afp";
        } else if (filePath.endsWith("txt") || filePath.endsWith("tmp")) {
            return "text/plain";
        } else if (filePath.endsWith("htm") || filePath.endsWith("html")) {
            return "text/html";
        } else if (filePath.endsWith("rtf")) {
            return "text/richtext";
        } else if (filePath.endsWith("log")) {
            return "text/plain";
        } else if (filePath.endsWith("rtf")) {
            return "text/richtext";
        } else if (filePath.endsWith("ppt") || filePath.endsWith("pptx") || filePath.endsWith("pot") || filePath.endsWith("ppa") || filePath.endsWith("pps") || filePath.endsWith("pwz")) {
            return "application/vnd.ms-powerpoint";
        } else if (filePath.endsWith("xls") || filePath.endsWith("xlt") || filePath.endsWith("xlsx") || filePath.endsWith("xlm") || filePath.endsWith("xld") || filePath.endsWith("xla") || filePath.endsWith("xlc") || filePath.endsWith("xlw") || filePath.endsWith("xll")) {
            return "application/vnd.ms-excel";
        } else if (filePath.endsWith("asf") || filePath.endsWith("asx")) {
            return "video/x-ms-asf";
        } else if (filePath.endsWith("wma")) {
            return "audio/x-ms-wma";
        } else if (filePath.endsWith("wax")) {
            return "audio/x-ms-wax";
        } else if (filePath.endsWith("wmv")) {
            return "audio/x-ms-wmv";
        } else if (filePath.endsWith("wvx")) {
            return "video/x-ms-wvx";
        } else if (filePath.endsWith("wm")) {
            return "video/x-ms-wm";
        } else if (filePath.endsWith("wmx")) {
            return "video/x-ms-wmx";
        } else if (filePath.endsWith("wmz")) {
            return "application/x-ms-wmz";
        } else if (filePath.endsWith("wmd")) {
            return "application/x-ms-wmd";
        } else if (filePath.endsWith("doc") || filePath.endsWith("dot") || filePath.endsWith("docx") || filePath.endsWith("rtf")) {
            return "application/msword";
        } else {
            return "application/octet-stream";
        }
    }


    public List<String> searchInPropertiesAndContent(String token, String documentName, String propertyName, String searchContent) {
        try (FileNetConnection fileNetConnectionUtil = new FileNetConnection()) {
            List<String> list = new ArrayList<>();
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fileNetConnectionUtil.connect(user.getUserNameLdap(), user.getPassword());
            List<GetClassPropertyDTO> classProperties = getClassProperties(documentName, token);
            String query = null;
            if (searchContent == null)
                query = buildFiledQueryAndWhereCondition(classProperties, documentName, propertyName);
            else {
                query = buildContentQueryAndWhereCondition(classProperties, documentName, propertyName, searchContent);
            }
            SearchSQL sql = new SearchSQL();
            sql.setQueryString(query);
            SearchScope searchScope = new SearchScope(os);
            DocumentSet documents = (DocumentSet) searchScope.fetchObjects(sql, Integer.valueOf(50), null, Boolean.valueOf(true));
            Iterator iterator = documents.iterator();
            while (iterator.hasNext()) {
                Document doc = (Document) iterator.next();
                System.out.println(doc.get_Id());
                String documentId = doc.get_Id().toString();
                list.add(documentId.substring(1, documentId.length() - 1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String buildFiledQueryAndWhereCondition(List<GetClassPropertyDTO> classProperties, String documentName, String searchValue) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (GetClassPropertyDTO propertyDTO : classProperties) {
            if (propertyDTO.getDataType().equals("STRING")) {
                if (index > 0) stringBuilder.append(" OR ");
                stringBuilder.append("[");
                stringBuilder.append(propertyDTO.getSymbolicName());
                stringBuilder.append("]");
                stringBuilder.append(" like ");
                stringBuilder.append("'%");
                stringBuilder.append(searchValue);
                stringBuilder.append("%'");
            }
            index++;
        }
        return "SELECT [Id] FROM [" + documentName + "] WHERE " + stringBuilder;
    }

    private String buildContentQueryAndWhereCondition(List<GetClassPropertyDTO> classProperties, String documentName, String searchValue, String searchContent) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (GetClassPropertyDTO propertyDTO : classProperties) {
            if (propertyDTO.getDataType().equals("STRING")) {
                if (index > 0) stringBuilder.append(" OR ");
                stringBuilder.append("[");
                stringBuilder.append(propertyDTO.getSymbolicName());
                stringBuilder.append("]");
                stringBuilder.append(" like ");
                stringBuilder.append("'%");
                stringBuilder.append(searchValue);
                stringBuilder.append("%'");
            }
            index++;
        }
        return "SELECT Id FROM " + documentName + " T LEFT JOIN ContentSearch cs ON T.This = cs.QueriedObject " + "WHERE CONTAINS(*," + "'" + searchContent + "'" + ")" + " OR " + stringBuilder;
    }

    public List<GetClassPropertyDTO> getClassProperties(String documentName, String token) {

        ClassPropertiesDTO classProperties = classificationsService.findClassProperties(documentName, token);
        if (classProperties != null) {
            List<GetClassPropertyDTO> properties = classProperties.getProperties();
            if (properties != null && !properties.isEmpty()) {
                return properties;
            }
        } else {
            throw new ServiceException(" Class is not has Properties");
        }
        return null;
    }


}
