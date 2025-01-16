package com.dataserve.archivemanagement.service;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.exception.DataNotFoundException;
import com.dataserve.archivemanagement.exception.DataRequiredException;
import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.*;
import com.dataserve.archivemanagement.model.dto.*;
import com.dataserve.archivemanagement.repository.*;
import com.dataserve.archivemanagement.security.JwtTokenUtil;
import com.dataserve.archivemanagement.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.PropertyNames;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

import com.filenet.api.collection.DocumentSet;
import com.filenet.api.core.Factory;
import com.filenet.api.property.FilterElement;
import com.filenet.api.property.PropertyFilter;
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
    private DepartmentsRepo departmentsRepo;
    @Autowired
    private ClassificationsService classificationsService;
    @Autowired
    private EDSChoicesService edsChoicesService;
    @Autowired
    ConfigUtil configUtil;


    @Override
    public String createDocument(String token, String document, List<MultipartFile> files) {
        try {
            CreateDocumentDTO documentDTO = new ObjectMapper().readValue(document, CreateDocumentDTO.class);

            if (validationBeforeSaveDocument(documentDTO)) {
                Folder folder = folderRepo.findBySerial(Long.valueOf(documentDTO.getFolderNo()))
                        .orElseThrow(() -> new CustomServiceException(
                                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(),
                                configUtil.getLocalMessage("1009", null)
                        ));

                if (documentDTO.getSaveType().equals(SaveType.UPLOAD_FILE) && documentDTO.getNumOfPages() == null) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.NUMBER_OF_PAGES_REQUIRED.getCode(),
                            configUtil.getLocalMessage("1006", null)
                    );
                }
                List<File> filesList = new ArrayList<>();
                files.stream().forEach(f -> {
                    try {
                        byte[] bytes = f.getBytes();
                        File file = Files.write(Paths.get(tempFilePath + File.separator + f.getOriginalFilename()), bytes).toFile();
                        filesList.add(file);
                    } catch (IOException e) {
                        throw new CustomServiceException(
                                ArchiveErrorCode.FAILED_TO_RECEIVE_FILES.getCode(),
                                configUtil.getLocalMessage("1007", null)
                        );
                    }
                });

                if (files.size() != filesList.size()) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.FAILED_TO_RECEIVE_FILES.getCode(),
                            configUtil.getLocalMessage("1007", null)
                    );
                }

                UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
                Document newDocument = fnService.createDocument(
                        loginUser.getUserNameLdap(), loginUser.getPassword(), documentDTO, filesList
                );

                String result = newDocument.get_Id().toString();
                LogUtil.info("Document'" + newDocument.get_Id() + "' has been created through integration");

                JSONObject params = new JSONObject(document);
                params.put("files", filesList);
                AuditUtil audit = new AuditUtil("/createDocument", loginUser.getUserNameLdap(), params, result);
                audit.run();

                String documentId = result.substring(1, result.length() - 1);

                AppUsers existingUser = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap())
                        .orElseThrow(() -> new CustomServiceException(
                                ArchiveErrorCode.USER_NOT_FOUND_IN_LDAP.getCode(),
                                configUtil.getLocalMessage("1003", null)
                        ));

                DmsFiles dmsFiles = addDMSFilesOnDataBase(documentDTO, documentId, existingUser, folder.getFolderId());
                if (dmsFiles != null) {
                    DMSAudit dmsAudit = addDMSAuditOnDataBase(documentDTO, documentId, existingUser.getUserId(), dmsFiles.getFileId());
                    saveDMSPropertyAudit(documentDTO.getProperties(), dmsAudit.getAuditId());
                }
                return documentId;
            }

        } catch (CustomServiceException e) {
            throw e;
        } catch (DataRequiredException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new CustomServiceException(
                    ArchiveErrorCode.FAILED_TO_CREATE_DOCUMENT.getCode(),
                    configUtil.getLocalMessage("1010", null)
            );
        }
        return null;
    }

    @Override
    public String createDocumentBase64(String token, CreateDocumentDTO document) {
        try {

            if (document.getProperties() == null || document.getProperties().isEmpty()) {
                throw new CustomServiceException(
                        ArchiveErrorCode.PROPERTIES_REQUIRED.getCode(),
                        configUtil.getLocalMessage("1026", null) // Localized message for "Properties are required"
                );
            }
            // Validate the document before saving
            if (validationBeforeSaveDocument(document)) {
                Folder folder = folderRepo.findBySerial(Long.valueOf(document.getFolderNo()))
                        .orElseThrow(() -> new CustomServiceException(
                                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(), // Use FOLDER_NOT_FOUND (1006)
                                configUtil.getLocalMessage("1009", null)    // Folder not found message
                        ));

                UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);

                Document newDocument = fnService.createDocument(
                        loginUser.getUserNameLdap(),
                        loginUser.getPassword(),
                        document
                );

                String result = newDocument.get_Id().toString();
                LogUtil.info("Document '" + newDocument.get_Id() + "' has been created through integration");

                JSONObject params = new JSONObject(document);
                AuditUtil audit = new AuditUtil("/createDocument", loginUser.getUserNameLdap(), params, result);
                audit.run();

                // Save document information in the database
                String documentId = result.substring(1, result.length() - 1);

                AppUsers existingUser = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap())
                        .orElseThrow(() -> new CustomServiceException(
                                ArchiveErrorCode.USER_NOT_FOUND_IN_LDAP.getCode(), // Use USER_NOT_FOUND_IN_LDAP (1003)
                                configUtil.getLocalMessage("1003", null)          // User not found in LDAP
                        ));

                DmsFiles dmsFiles = addDMSFilesOnDataBase(document, documentId, existingUser, folder.getFolderId());
                if (dmsFiles != null) {
                    DMSAudit dmsAudit = addDMSAuditOnDataBase(document, documentId, existingUser.getUserId(), dmsFiles.getFileId());
                    saveDMSPropertyAudit(document.getProperties(), dmsAudit.getAuditId());
                }
                return documentId;
            }

        }  catch (CustomServiceException e) {
            throw e;
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
            throw new CustomServiceException(
                    ArchiveErrorCode.FOLDER_NUMBER_REQUIRED.getCode(), // New code for folder number required
                    configUtil.getLocalMessage("1012", null)          // Localized message
            );
        }

        if (document.getSaveType().equals(SaveType.UPLOAD_FILE) && document.getNumOfPages() == null) {
            throw new CustomServiceException(
                    ArchiveErrorCode.NUMBER_OF_PAGES_REQUIRED.getCode(), // New code for number of pages required
                    configUtil.getLocalMessage("1006", null)            // Localized message
            );
        }

        Folder folder = folderRepo.findBySerial(Long.valueOf(document.getFolderNo()))
                .orElseThrow(() -> new CustomServiceException(
                        ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(), // New code for folder not found
                        configUtil.getLocalMessage("1012", null)    // Localized message
                ));

        Long sumOfFiles = dmsFilesRepository.countByFolderNo(folder.getFolderId());
        if (sumOfFiles >= folder.getCapacity() && folder.getCapacity() != 0) {
            throw new CustomServiceException(
                    ArchiveErrorCode.FOLDER_CAPACITY_FULL.getCode(), // New code for folder capacity full
                    configUtil.getLocalMessage("1013", null)        // Localized message
            );
        }

        StorageCenter storageCenter = folder.getStorageCenter();
        if (storageCenter == null) {
            throw new CustomServiceException(
                    ArchiveErrorCode.STORAGE_CENTER_NOT_FOUND.getCode(), // New code for storage center not found
                    configUtil.getLocalMessage("1014", null)            // Localized message
            );
        }

        StorageCenterType storageCenterType = storageCenter.getStorageCenterType();
        if (storageCenterType == null) {
            throw new CustomServiceException(
                    ArchiveErrorCode.INVALID_STORAGE_CENTER_TYPE.getCode(), // New code for invalid storage center type
                    configUtil.getLocalMessage("1015", null)               // Localized message
            );
        }

        Classifications classifications = folder.getClassifications();
        if (classifications != null) {
            if (storageCenterType.getStorageCenterTypeId() != 3
                    && !storageCenterType.getStorageCenterTypeId().equals(classifications.getSaveType())) {
                String savedType = saveType(classifications.getSaveType());
                throw new CustomServiceException(
                        ArchiveErrorCode.STORAGE_TYPE_MISMATCH.getCode(), // New code for storage type mismatch
                        configUtil.getLocalMessage("1017", null)         // Localized message
                );
            }
        } else {
            throw new CustomServiceException(
                    ArchiveErrorCode.CLASSIFICATION_INVALID.getCode(), // New code for invalid classification
                    configUtil.getLocalMessage("1016", null)           // Localized message
            );
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
                        LogUtil.error("Failed to receive attachments", e);
                    }
                });

                if (files.size() != filesList.size()) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.FAILED_TO_RECEIVE_FILES.getCode(), // Error for file receipt failure
                            configUtil.getLocalMessage("1007", null)           // Localized message
                    );
                }
            }

            Document resultDocument = null;
            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            if (document.getProperties() != null && document.getProperties().size() > 0) {
                resultDocument = fnService.updateDocumentProperties(
                        loginUser.getUserNameLdap(), loginUser.getPassword(), document);
            }
            if (filesList != null && filesList.size() > 0) {
                resultDocument = fnService.updateDocumentContents(
                        loginUser.getUserNameLdap(), loginUser.getPassword(), document.getGuid(), filesList);
            }

            if (resultDocument == null) {
                throw new CustomServiceException(
                        ArchiveErrorCode.NOTHING_TO_UPDATE.getCode(), // New error code for nothing to update
                        configUtil.getLocalMessage("1019", null)     // Localized message
                );
            }

            String result = resultDocument.get_Id().toString();
            LogUtil.info("Document '" + result + "' has been updated through integration");

            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(document);
            JSONObject params = new JSONObject(str);
            params.put("files", filesList);
            AuditUtil audit = new AuditUtil("/updateDocument", loginUser.getUserNameLdap(), params, result);
            audit.run();
            return result;
        } catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            LogUtil.error("Failed to update document", e);
            throw new CustomServiceException(
                    ArchiveErrorCode.FAILED_TO_UPDATE_DOCUMENT.getCode(), // New error code for update failure
                    configUtil.getLocalMessage("1020", null)             // Localized message
            );
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


    public List<SearchDocumentDTO> searchInPropertiesAndContent(String token, String documentClass, String searchValue) {
        try (FileNetConnection fileNetConnectionUtil = new FileNetConnection()) {
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fileNetConnectionUtil.connect(user.getUserNameLdap(), user.getPassword());
            List<GetClassPropertyDTO> classProperties = getClassProperties(documentClass, token);
            String query = null;
//            if (searchContent == null)
//                query = buildFiledQueryAndWhereCondition(classProperties, documentName, propertyName);
//            else {
            query = buildContentQueryAndWhereCondition(classProperties, documentClass, searchValue);
//            }
            SearchSQL sql = new SearchSQL();
            sql.setQueryString(query);
            SearchScope searchScope = new SearchScope(os);
            DocumentSet documents = (DocumentSet) searchScope.fetchObjects(sql, Integer.valueOf(50), null, Boolean.valueOf(true));
            return getSearchDocumentResult(documents.iterator());
        } catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<SearchDocumentDTO> getSearchDocumentResult(Iterator iterator) {
        List<SearchDocumentDTO> searchDocumentDTOS = new ArrayList<>();
        while (iterator.hasNext()) {
            Document doc = (Document) iterator.next();
            System.out.println(doc.get_Id());
            String documentId = doc.get_Id().toString();
            String docId = documentId.substring(1, documentId.length() - 1);
            SearchDocumentDTO searchDocumentDTO = findDocumentDataById(docId);
            if (searchDocumentDTO != null)
                searchDocumentDTOS.add(searchDocumentDTO);
        }
        return searchDocumentDTOS;
    }

    private SearchDocumentDTO findDocumentDataById(String docId) {
        DmsFiles dmsFiles = dmsFilesRepository.findByDocumentId(docId);
        SearchDocumentDTO searchDocument = null;
        AppUsers user = null;
        if (dmsFiles != null) {
            searchDocument = new SearchDocumentDTO();
            // Retrieve user details associated with the document
             user = usersRepo.findById(dmsFiles.getUserId()).orElseThrow(() ->
                    new CustomServiceException(
                            ArchiveErrorCode.USER_NOT_FOUND.getCode(), // Use USER_NOT_FOUND (1009)
                            configUtil.getLocalMessage("1008", null)  // Localized message for "User not found"
                    )
            );
            searchDocument.setUsernameEn(user.getUserEnName());
            searchDocument.setUsernameAr(user.getUserArName());
            searchDocument.setDocId(dmsFiles.getDocumentId());
            searchDocument.setNoPages(dmsFiles.getNoPages());
            searchDocument.setDocumentTitle(dmsFiles.getDocumentName());
            Departments department = dmsFiles.getDepartments();
            if (department != null) {
                searchDocument.setDepartmentNameAr(department.getDeptArName());
                searchDocument.setDepartmentNameEn(department.getDeptEnName());
            }
            Optional<Folder> folder = folderRepo.findById(dmsFiles.getFileId());
            if (folder.isPresent()) {
                searchDocument.setFolderNameEn(folder.get().getNameEn());
                searchDocument.setFolderNameAr(folder.get().getNameAr());
            }
            return searchDocument;
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

    private String buildContentQueryAndWhereCondition(List<GetClassPropertyDTO> classProperties, String documentClass, String searchValue) {
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
        return "SELECT Id FROM " + documentClass + " T LEFT JOIN ContentSearch cs ON T.This = cs.QueriedObject " + "WHERE CONTAINS(*," + "'" + searchValue + "'" + ")" + " OR " + stringBuilder;
    }

    public List<GetClassPropertyDTO> getClassProperties(String documentName, String token) {
        // Retrieve class properties
        ClassPropertiesDTO classProperties = classificationsService.findClassProperties(documentName, token);
        if (classProperties != null) {
            List<GetClassPropertyDTO> properties = classProperties.getProperties();
            if (properties != null && !properties.isEmpty()) {
                return properties;
            }
        }

        // Throw exception if class properties are not found
        throw new CustomServiceException(
                ArchiveErrorCode.CLASSIFICATION_PROPERTIES_NOT_FOUND.getCode(), // New error code for missing class properties
                configUtil.getLocalMessage("1022", null)  // Localized message for "Class does not have properties"
        );
    }



    public FileContentDto getFileContent(String token, String docId) {
        StringBuilder stringBuilder1 = new StringBuilder();
        try (FileNetConnection fileNetConnectionUtil = new FileNetConnection()) {
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fileNetConnectionUtil.connect(user.getUserNameLdap(), user.getPassword());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            stringBuilder.append(docId);
            stringBuilder.append("}");

            FileContentDto fileContentDto = new FileContentDto();
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_SIZE, null));
            pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null));
            Document doc = Factory.Document.fetchInstance(os, stringBuilder.toString(), pf);
            if (doc == null) {
                throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(),configUtil.getLocalMessage("1005", null));
            }
            String base64 = "";
            // Get content elements and iterate list.
            ContentElementList docContentList = doc.get_ContentElements();
            Iterator iter = docContentList.iterator();
            while (iter.hasNext()) {
                ContentTransfer ct = (ContentTransfer) iter.next();

                fileContentDto.setContentType(ct.get_ContentType());
                fileContentDto.setFileName(ct.get_RetrievalName());
                fileContentDto.setContentSize(ct.get_ContentSize());

                // Get and print the content of the element.
                InputStream stream = ct.accessContentStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[(int) Math.round(ct.get_ContentSize())];
                int n = 0;
                while (-1 != (n = stream.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                stream.close();
                byte[] response = out.toByteArray();
                stream.close();
                base64 = Base64.getEncoder().encodeToString(response);
                fileContentDto.setBase64(base64);
                return fileContentDto;
            }
        } catch (CustomServiceException e) {
            throw e;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new ServiceException(ioe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return null;
    }


    public ClassPropertiesDTO findFileProperties(String docId, String token) {
        try{
        UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
        DmsFiles dmsFile = dmsFilesRepository.findByDocumentId(docId);

        if (dmsFile == null) {
            throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(),configUtil.getLocalMessage("1005", null));
        }

        Document document =fnService.getDocumentByDocId(loginUser.getUserNameLdap(), loginUser.getPassword(),docId);
        if (document == null) {
            throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(),configUtil.getLocalMessage("1005", null));
        }
        ClassPropertiesDTO fnProps = fnService.getDocumentPropertiesById(dmsFile.getDocumentClass(),document, token);
        if (fnProps == null || fnProps.getProperties().isEmpty()) {
            throw new CustomServiceException(
                    ArchiveErrorCode.CLASSIFICATION_PROPERTIES_NOT_FOUND.getCode(), // Error code for missing properties
                    configUtil.getLocalMessage("1022", null)                        // Localized message for no properties
            );
        }

        // Enrich properties with EDS choices
        Map<String, EDSChoiceListDTO> edsProps = edsChoicesService.getClassEDSPropertesBySymbolicName(fnProps.getClassName());
        if (!edsProps.isEmpty()) {
            for (GetClassPropertyDTO prop : fnProps.getProperties()) {
                if (edsProps.containsKey(prop.getSymbolicName())) {
                    EDSChoiceListDTO listDto = edsProps.get(prop.getSymbolicName());
                    if (listDto.getDependOn() != null) {
                        prop.setDependOn(listDto.getDependOn());
                    }

                    // Match choice value to set displayName
                    if (listDto.getChoiceList() != null) {
                        for (EDSChoiceDTO choice : listDto.getChoiceList()) {
                            if (choice.getValue().equals(prop.getValue()) && "ar".equals(choice.getLang())) {
                                prop.setValue(choice.getDisplayName());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return fnProps;
    } catch (CustomServiceException e) {
        throw e;
    } catch (Exception e) {
        throw new ServiceException(e.getMessage(),e);
    }

    }
}
