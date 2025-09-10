package com.dataserve.mahfuzatintegration.service;

import com.dataserve.mahfuzatintegration.config.ConfigUtil;
import com.dataserve.mahfuzatintegration.exception.CustomServiceException;
import com.dataserve.mahfuzatintegration.exception.DataRequiredException;
import com.dataserve.mahfuzatintegration.exception.ServiceException;
import com.dataserve.mahfuzatintegration.model.*;
import com.dataserve.mahfuzatintegration.model.dto.*;
import com.dataserve.mahfuzatintegration.repository.*;
import com.dataserve.mahfuzatintegration.security.JwtTokenUtil;
import com.dataserve.mahfuzatintegration.util.*;
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
    private DmsIntegrationFilesRepository dmsIntegrationFilesRepository;
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

    @Autowired
    private ClassificationsRepo classificationsRepo;

    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;


    @Override
    public String createDocument(String token, String document, List<MultipartFile> files) {
        try {
//            CreateDocumentDTO documentDTO = new ObjectMapper().readValue(document, CreateDocumentDTO.class);
//
//            if (validationBeforeSaveDocument(documentDTO)) {
//                Folder folder = folderRepo.findBySerial(Long.valueOf(documentDTO.getFolderNo()))
//                        .orElseThrow(() -> new CustomServiceException(
//                                ArchiveErrorCode.FOLDER_NOT_FOUND.getCode(),
//                                configUtil.getLocalMessage("1009", null)
//                        ));
//
//                if (documentDTO.getSaveType().equals(SaveType.UPLOAD_FILE) && documentDTO.getNumOfPages() == null) {
//                    throw new CustomServiceException(
//                            ArchiveErrorCode.NUMBER_OF_PAGES_REQUIRED.getCode(),
//                            configUtil.getLocalMessage("1006", null)
//                    );
//                }
//                List<File> filesList = new ArrayList<>();
//                files.stream().forEach(f -> {
//                    try {
//                        byte[] bytes = f.getBytes();
//                        File file = Files.write(Paths.get(tempFilePath + File.separator + f.getOriginalFilename()), bytes).toFile();
//                        filesList.add(file);
//                    } catch (IOException e) {
//                        throw new CustomServiceException(
//                                ArchiveErrorCode.FAILED_TO_RECEIVE_FILES.getCode(),
//                                configUtil.getLocalMessage("1007", null)
//                        );
//                    }
//                });
//
//                if (files.size() != filesList.size()) {
//                    throw new CustomServiceException(
//                            ArchiveErrorCode.FAILED_TO_RECEIVE_FILES.getCode(),
//                            configUtil.getLocalMessage("1007", null)
//                    );
//                }
//
//                UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
//                Document newDocument = fnService.createDocument(
//                        loginUser.getUserNameLdap(), loginUser.getPassword(), documentDTO, filesList
//                );
//
//                String result = newDocument.get_Id().toString();
//                LogUtil.info("Document'" + newDocument.get_Id() + "' has been created through integration");
//
//                JSONObject params = new JSONObject(document);
//                params.put("files", filesList);
//                AuditUtil audit = new AuditUtil("/createDocument", loginUser.getUserNameLdap(), params, result);
//                audit.run();
//
//                String documentId = result.substring(1, result.length() - 1);
//
//                AppUsers existingUser = usersRepo.findByUserNameLdap(loginUser.getUserNameLdap())
//                        .orElseThrow(() -> new CustomServiceException(
//                                ArchiveErrorCode.USER_NOT_FOUND_IN_LDAP.getCode(),
//                                configUtil.getLocalMessage("1003", null)
//                        ));
//
//                DmsFiles dmsFiles = addDMSFilesOnDataBase(documentDTO, documentId, existingUser, folder.getFolderId());
//                if (dmsFiles != null) {
//                    DMSAudit dmsAudit = addDMSAuditOnDataBase(documentDTO, documentId, existingUser.getUserId(), dmsFiles.getFileId());
//                    saveDMSPropertyAudit(documentDTO.getProperties(), dmsAudit.getAuditId());
//                }
//                return documentId;
//            }

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
            // Call the validateProperties method to perform additional validation
            String documentTitle = validateProperties(
                    document.getProperties(),
                    document.getIntegrationDocumentId(),
                    document.getIntegrationSystemId()
            );

            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);

            Document newDocument = fnService.createDocument(
                    loginUser.getUserNameLdap(),
                    loginUser.getPassword(),
                    document
            );

            String result = newDocument.get_Id().toString();
            LogUtil.info("Document '" + newDocument.get_Id() + "' has been created through integration");


            // Save document information in the database
            String documentId = result.substring(1, result.length() - 1);

            DmsIntegrationFiles dmsFiles = addDmsIntegrationFiles(document, documentId, documentTitle);
            return dmsFiles.getArchivedDocumentId();

        } catch (CustomServiceException e) {
            throw e;
        } catch (DataRequiredException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            LogUtil.error("Failed to create document", e);
            throw new ServiceException(e.getMessage());
        }
    }
    public DmsIntegrationFiles addDmsIntegrationFiles(CreateDocumentDTO documentDTO, String documentId, String documentTitle) {
        DmsIntegrationFiles dmsFiles = new DmsIntegrationFiles();
        dmsFiles.setIntegrationDocumentId(documentDTO.getIntegrationDocumentId());
        dmsFiles.setIntegrationDocumentName(documentTitle);
        dmsFiles.setIntegrationFileNoPages(documentDTO.getNumOfPages() != null ? documentDTO.getNumOfPages() : null);
        dmsFiles.setArchivedDocumentStatus(IntegrationFileStatus.NEW.getId());
        dmsFiles.setTransactionId(getRowCountPlusOne());
        dmsFiles.setArchivedDocumentId(documentId);
        dmsFiles.setIntegrationSystemId(documentDTO.getIntegrationSystemId()); // Assuming you have this data in the User
        dmsFiles.setCreatedDate(new Date());
        return dmsIntegrationFilesRepository.save(dmsFiles);
    }
    @Override
    public Map<String, Object> createDocumentBase64(String token, List<CreateDocumentDTO> documents) {
        Map<String, Object> resultMap = new HashMap<>();
        List<DocumentCreateItemDTO> items = new ArrayList<>();
        List<String> documentIds = new ArrayList<>(); // optional: keep for backward compatibility

        try {
            Long transactionId = getRowCountPlusOne();

            for (CreateDocumentDTO document : documents) {
                String documentTitle = validateProperties(
                        document.getProperties(),
                        document.getIntegrationDocumentId(),
                        document.getIntegrationSystemId()
                );

                UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);

                Document newDocument = fnService.createDocument(
                        loginUser.getUserNameLdap(),
                        loginUser.getPassword(),
                        document
                );

                String rawId = newDocument.get_Id().toString();
                LogUtil.info("Document '" + rawId + "' has been created through integration");

                String archivedDocumentId = rawId.substring(1, rawId.length() - 1);

                DmsIntegrationFiles dmsFiles = addDmsIntegrationFiles(document, archivedDocumentId, documentTitle, transactionId);

                items.add(new DocumentCreateItemDTO(
                        dmsFiles.getArchivedDocumentId(),
                        dmsFiles.getIntegrationDocumentId(),
                        dmsFiles.getIntegrationSystemId()
                ));

                documentIds.add(dmsFiles.getArchivedDocumentId());
            }

            resultMap.put("transactionId", transactionId);
            resultMap.put("documents", items);
            return resultMap;

        } catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            LogUtil.error("Failed to create documents", e);
            throw new ServiceException(e.getMessage());
        }
    }


//    public DmsIntegrationFiles addDmsIntegrationFiles(CreateDocumentDTO documentDTO, String documentId, String documentTitle, Long transactionId) {
//        DmsIntegrationFiles dmsFiles = new DmsIntegrationFiles();
//        dmsFiles.setIntegrationDocumentId(documentDTO.getIntegrationDocumentId());
//        dmsFiles.setIntegrationDocumentName(documentTitle);
//        dmsFiles.setIntegrationFileNoPages(documentDTO.getNumOfPages() != null ? documentDTO.getNumOfPages() : null);
//        dmsFiles.setArchivedDocumentStatus(IntegrationFileStatus.NEW.getId());
//        dmsFiles.setTransactionId(transactionId); // ✅ Same transactionId for all
//        dmsFiles.setArchivedDocumentId(documentId);
//        dmsFiles.setIntegrationSystemId(documentDTO.getIntegrationSystemId());
//        dmsFiles.setCreatedDate(new Date());
//        return dmsIntegrationFilesRepository.save(dmsFiles);
//    }

    public DmsIntegrationFiles addDmsIntegrationFiles(CreateDocumentDTO documentDTO,
                                                      String documentId,
                                                      String documentTitle,
                                                      Long transactionId) {
        DmsIntegrationFiles dmsFiles = new DmsIntegrationFiles();
        dmsFiles.setIntegrationDocumentId(documentDTO.getIntegrationDocumentId());
        dmsFiles.setIntegrationDocumentName(documentTitle);
        dmsFiles.setIntegrationFileNoPages(documentDTO.getNumOfPages() != null ? documentDTO.getNumOfPages() : null);
        dmsFiles.setArchivedDocumentStatus(IntegrationFileStatus.NEW.getId());
        dmsFiles.setTransactionId(transactionId);
        dmsFiles.setArchivedDocumentId(documentId);
        dmsFiles.setIntegrationSystemId(documentDTO.getIntegrationSystemId());
        dmsFiles.setCreatedDate(new Date());

        // ✅ 1) INTEGRATION_PROPERTIES_JSON (serialize properties list as JSON)
        try {
            String propsJson = (documentDTO.getProperties() == null)
                    ? "[]"
                    : objectMapper.writeValueAsString(documentDTO.getProperties());
            dmsFiles.setIntegrationPropertiesJson(propsJson);
        } catch (Exception ex) {
            // fallback: store a minimal error marker to avoid null
            dmsFiles.setIntegrationPropertiesJson("[]");
        }

        // ✅ 2) INTEGRATION_FILE_EXT (take first file ext from uploadDocumentList)
        String fileExt = null;
        if (documentDTO.getUploadDocumentList() != null && !documentDTO.getUploadDocumentList().isEmpty()) {
            CustomDocument first = documentDTO.getUploadDocumentList().get(0);
            if (first != null && first.getFileExt() != null && !first.getFileExt().isEmpty()) {
                fileExt = first.getFileExt();
            }
        }
        dmsFiles.setIntegrationFileExt(fileExt);

        // ✅ 3) INTEGRATION_CLASSIFICATION_ID (lookup by documentClassName)
        Long classificationId = null;
        if (documentDTO.getDocumentClassName() != null && !documentDTO.getDocumentClassName().isEmpty()) {
            // NOTE: ensure DB column is SYMBOLIC_NAME (or adjust the repo/native query to exact spelling)
            classificationId = classificationsRepo.findIdBySymbolicName(documentDTO.getDocumentClassName());
        }
        dmsFiles.setIntegrationClassificationId(classificationId);

        return dmsIntegrationFilesRepository.save(dmsFiles);
    }


    public Long getRowCountPlusOne() {
        Long rowCount = dmsIntegrationFilesRepository.countRows();
        return rowCount + 1; // Add 1 to the count
    }


    public String validateProperties(List<PropertyDTO> properties, String integrationDocumentId, Long integrationSystemId) {
        // 1. Validate that integrationDocumentId and integrationSystemId are not null
        if (integrationDocumentId == null || integrationDocumentId.isEmpty()) {
            throw new CustomServiceException(
                    ArchiveErrorCode.INTEGRATION_DOCUMENT_ID_REQUIRED.getCode(),
                    configUtil.getLocalMessage("1035", null) // Localized message for "Integration Document ID is required"
            );
        }

        if (integrationSystemId == null) {
            throw new CustomServiceException(
                    ArchiveErrorCode.INTEGRATION_SYSTEM_ID_REQUIRED.getCode(),
                    configUtil.getLocalMessage("1036", null) // Localized message for "Integration System ID is required"
            );
        }

        // 2. Check if the "DocumentTitle" property exists
        Optional<PropertyDTO> documentTitleProperty = properties.stream()
                .filter(property -> "DocumentTitle".equals(property.getSymbolicName()))
                .findFirst();

        if (!documentTitleProperty.isPresent()) {
            // If "DocumentTitle" is not found, throw an exception with a localized message
            throw new CustomServiceException(
                    ArchiveErrorCode.DOCUMENT_TITLE_REQUIRED.getCode(),
                    configUtil.getLocalMessage("1034", null) // Localized message for "Document title is required"
            );
        }

        // Return the property value for "DocumentTitle"
        return documentTitleProperty.get().getPropertyValue();
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


//    public List<SearchDocumentDTO> searchInPropertiesAndContent(String token, String documentClass, String searchValue) {
//        try (FileNetConnection fileNetConnectionUtil = new FileNetConnection()) {
//            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
//            ObjectStore os = fileNetConnectionUtil.connect(user.getUserNameLdap(), user.getPassword());
//            List<GetClassPropertyDTO> classProperties = getClassProperties(documentClass, token);
//            String query = null;
////            if (searchContent == null)
////                query = buildFiledQueryAndWhereCondition(classProperties, documentName, propertyName);
////            else {
//            query = buildContentQueryAndWhereCondition(classProperties, documentClass, searchValue);
////            }
//            SearchSQL sql = new SearchSQL();
//            sql.setQueryString(query);
//            SearchScope searchScope = new SearchScope(os);
//            DocumentSet documents = (DocumentSet) searchScope.fetchObjects(sql, Integer.valueOf(50), null, Boolean.valueOf(true));
//            return getSearchDocumentResult(documents.iterator());
//        } catch (CustomServiceException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new ServiceException(e.getMessage(),e);
//        }
//    }

    public List<SearchDocumentDTO> searchInPropertiesAndContent(String token, String documentClass, String searchValue, String language) {
        try (FileNetConnection fileNetConnectionUtil = new FileNetConnection()) {
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fileNetConnectionUtil.connect(user.getUserNameLdap(), user.getPassword());

            List<GetClassPropertyDTO> classProperties = getClassProperties(documentClass, token);
            String query = buildContentQueryAndWhereCondition(classProperties, documentClass, searchValue);

            SearchSQL sql = new SearchSQL();
            sql.setQueryString(query);
            SearchScope searchScope = new SearchScope(os);
            DocumentSet documents = (DocumentSet) searchScope.fetchObjects(sql, 50, null, true);

            List<SearchDocumentDTO> results = getSearchDocumentResult(documents.iterator());
            results.forEach(dto -> addLocalizedFields(dto, language));

            return results;
        } catch (CustomServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void addLocalizedFields(SearchDocumentDTO dto, String language) {
        if ("ar".equalsIgnoreCase(language)) {
            dto.setUsername(dto.getUsernameAr());
            dto.setDepartmentName(dto.getDepartmentNameAr());
            dto.setFolderName(dto.getFolderNameAr());
        } else { // Default to English
            dto.setUsername(dto.getUsernameEn());
            dto.setDepartmentName(dto.getDepartmentNameEn());
            dto.setFolderName(dto.getFolderNameEn());
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
                throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(), configUtil.getLocalMessage("1005", null));
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
        try {
            UserDTO loginUser = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            DmsFiles dmsFile = dmsFilesRepository.findByDocumentId(docId);

            if (dmsFile == null) {
                throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(), configUtil.getLocalMessage("1005", null));
            }

            Document document = fnService.getDocumentByDocId(loginUser.getUserNameLdap(), loginUser.getPassword(), docId);
            if (document == null) {
                throw new CustomServiceException(ArchiveErrorCode.DOCUMENT_NOT_FOUND.getCode(), configUtil.getLocalMessage("1005", null));
            }
            ClassPropertiesDTO fnProps = fnService.getDocumentPropertiesById(dmsFile.getDocumentClass(), document, token);
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
            throw new ServiceException(e.getMessage(), e);
        }

    }
}
