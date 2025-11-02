package com.dataserve.mahfuzatintegration.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.dataserve.mahfuzatintegration.config.ConfigUtil;
import com.dataserve.mahfuzatintegration.exception.CustomServiceException;
import com.dataserve.mahfuzatintegration.util.ArchiveErrorCode;
import com.dataserve.mahfuzatintegration.util.LocalizationUtil;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.apiimpl.property.PropertyDateTimeImpl;
import com.filenet.apiimpl.property.PropertyFloat64Impl;
import com.filenet.apiimpl.property.PropertyInteger32Impl;
import com.filenet.apiimpl.property.PropertyStringImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dataserve.mahfuzatintegration.model.dto.ClassPropertiesDTO;
import com.dataserve.mahfuzatintegration.model.dto.CreateDocumentDTO;
import com.dataserve.mahfuzatintegration.model.dto.CustomDocument;
import com.dataserve.mahfuzatintegration.model.dto.GetClassPropertyDTO;
import com.dataserve.mahfuzatintegration.model.dto.GetDocumentDTO;
import com.dataserve.mahfuzatintegration.model.dto.PropertyDTO;
import com.dataserve.mahfuzatintegration.model.dto.UpdateDocumentDTO;
import com.dataserve.mahfuzatintegration.model.dto.UserDTO;
import com.dataserve.mahfuzatintegration.security.JwtTokenUtil;
import com.dataserve.mahfuzatintegration.util.FileNetConnection;
import com.filenet.api.admin.ClassDefinition;
import com.filenet.api.admin.LocalizedString;
import com.filenet.api.admin.PropertyDefinition;
import com.filenet.api.admin.PropertyTemplate;
import com.filenet.api.collection.BooleanList;
import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.DateTimeList;
import com.filenet.api.collection.Float64List;
import com.filenet.api.collection.Integer32List;
import com.filenet.api.collection.LocalizedStringList;
import com.filenet.api.collection.PropertyDefinitionList;
import com.filenet.api.collection.StringList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.ClassNames;
import com.filenet.api.constants.FilteredPropertyType;
import com.filenet.api.constants.PropertyNames;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.property.FilterElement;
import com.filenet.api.property.Properties;
import com.filenet.api.property.Property;
import com.filenet.api.property.PropertyFilter;
import com.filenet.api.util.Id;

@Service
public class FileNetService {

    @Value("${class-execluded-properties-list}")
    String classExcludedPropertiesList;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ConfigUtil configUtil;

    public boolean checkAuthentication(String username, String password) {
        try (FileNetConnection fnUtil = new FileNetConnection()) {
            fnUtil.connect(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ClassPropertiesDTO getClassPropertiesById(String symbolicName, String token) throws CustomServiceException {
        try (FileNetConnection fnUtil = new FileNetConnection()) {
            ClassPropertiesDTO results = new ClassPropertiesDTO();
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fnUtil.connect(user.getUserNameLdap(), user.getPassword());
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
            results.setClassName(symbolicName);
         // Fetch selected class definition from the server
            ClassDefinition objClassDef = Factory.ClassDefinition.fetchInstance(os, symbolicName, pf);
         // Get PropertyDefinitions property from the property cache
         	PropertyDefinitionList objPropDefs = objClassDef.get_PropertyDefinitions();
            
            results.setProperties(getClassPropertiesList( objPropDefs));
            
            return results;
        }
        catch (EngineRuntimeException e) {
            String exceptionCode = e.getAsErrorStack().getExceptionCode().getKey();
            if ("E_BAD_CLASSID".equals(exceptionCode)) {
                throw new CustomServiceException(
                        ArchiveErrorCode.CLASS_NOT_FOUND.getCode(), // Use proper error code
                        configUtil.getLocalMessage("1033", new String[]{symbolicName}) // More specific error message
                );
            }

        }
        catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }

        return null;
    }


    public ClassPropertiesDTO getDocumentPropertiesById(String symbolicName,Document document, String token) throws CustomServiceException {
        try (FileNetConnection fnUtil = new FileNetConnection()) {
            ClassPropertiesDTO results = new ClassPropertiesDTO();
            UserDTO user = jwtTokenUtil.getUsernameAndPasswordFromToken(token);
            ObjectStore os = fnUtil.connect(user.getUserNameLdap(), user.getPassword());
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
            results.setClassName(symbolicName);
            // Fetch selected class definition from the server
            ClassDefinition objClassDef = Factory.ClassDefinition.fetchInstance(os, symbolicName, pf);
            // Get PropertyDefinitions property from the property cache
            PropertyDefinitionList objPropDefs = objClassDef.get_PropertyDefinitions();

            results.setProperties(getDocumentPropertiesValuesList( objPropDefs,document));

            return results;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    public GetDocumentDTO getDocumentByGUID(String username, String password, String GUID) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
            pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null));
            Document doc = Factory.Document.fetchInstance(con.connect(username, password), new Id(GUID), pf);

            Map<String, byte[]> contents = getDocumentContentsMap(doc);
            Map<String, String> propsMap = getDocumentPropertiesMap(doc);

            GetDocumentDTO document = new GetDocumentDTO();
            document.setContents(contents);
            document.setProperties(propsMap);
            return document;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    public Document getDocumentByDocId(String username, String password, String GUID) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
            pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null));
            Document doc = Factory.Document.fetchInstance(con.connect(username, password), new Id(GUID), pf);
            return doc;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );        }
    }

    public Document createDocument(String username, String password, CreateDocumentDTO dto, List<File> files) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            ObjectStore objectStore = con.connect(username, password);
            Document document = Factory.Document.createInstance(objectStore, dto.getDocumentClassName());
            Properties docProps = document.getProperties();
            for (PropertyDTO propDto : dto.getProperties()) {
                String propertyDataType = getPropertyDtoDataType(objectStore,dto.getDocumentClassName(),  propDto);
                if (propertyDataType == null) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.UNSUPPORTED_DATA_TYPE.getCode(), // Define a new error code for unsupported data types
                            configUtil.getLocalMessage("1029", new String[]{propDto.getDataType()}) // Localized message with dynamic data type
                    );
                }

                setPropertyValue(docProps, propDto.getSymbolicName(), propDto.getPropertyValue(), propertyDataType);
            }
//            document.getProperties().putValue("DocumentTitle", dto.getDocumentTitle());
            document.set_ContentElements(getContentElements(files));
            document.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
            document.save(RefreshMode.REFRESH);
            return document;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    public Document createDocument(String username, String password, CreateDocumentDTO dto) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            ObjectStore objectStore = con.connect(username, password);
            Document document = Factory.Document.createInstance(objectStore, dto.getDocumentClassName());
            Properties docProps = document.getProperties();
            List<PropertyDTO> propDtoListWithDataType = new ArrayList<PropertyDTO>();
            for (PropertyDTO propDto : dto.getProperties()) {
                String propertyDataType = getPropertyDtoDataType(objectStore,dto.getDocumentClassName(), propDto);
            	propDto.setDataType(propertyDataType);
                if (propertyDataType == null) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.UNSUPPORTED_DATA_TYPE.getCode(), // Error code for unsupported data types
                            configUtil.getLocalMessage("1029", new String[]{propDto.getDataType()}) // Localized message with dynamic data type
                    );
                }
            	propDtoListWithDataType.add(propDto);
            	
                setPropertyValue(docProps, propDto.getSymbolicName(), propDto.getPropertyValue(), propertyDataType);
            }
            dto.setProperties(propDtoListWithDataType);
//            document.getProperties().putValue("DocumentTitle", dto.getDocumentTitle());
            document.set_ContentElements(getContentElementsBase64(dto.getUploadDocumentList()));
            document.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
            document.save(RefreshMode.REFRESH);
            return document;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    public Document updateDocumentProperties(String username, String password, UpdateDocumentDTO dto) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            ObjectStore objectStore = con.connect(username, password);
            PropertyFilter pf = new PropertyFilter();
            pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
            Document doc = Factory.Document.fetchInstance(objectStore, new Id(dto.getGuid()), pf);
            setDocumentProperties(doc, dto.getProperties());
            doc.save(RefreshMode.REFRESH);
            return doc;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    public Document updateDocumentContents(String username, String password, String guid, List<File> filesList) throws CustomServiceException {
        try (FileNetConnection con = new FileNetConnection()) {
            ObjectStore os = con.connect(username, password);
            Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id(guid));

            // Check out the Document object and save it.
            doc.checkout(com.filenet.api.constants.ReservationType.EXCLUSIVE, null, doc.getClassName(), doc.getProperties());
            doc.save(RefreshMode.REFRESH);

            // Get the Reservation object from the Document object.
            Document reservation = (Document) doc.get_Reservation();

            @SuppressWarnings("deprecation") ContentElementList contentList = Factory.ContentTransfer.createList();
            for (File file : filesList) {
                ContentTransfer ctObject = Factory.ContentTransfer.createInstance();
                FileInputStream fileIS = new FileInputStream(file.getAbsolutePath());
                ctObject.setCaptureSource(fileIS);
                contentList.add(ctObject);
            }

            reservation.set_ContentElements(contentList);
            reservation.save(RefreshMode.REFRESH);

            reservation.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
            reservation.save(RefreshMode.REFRESH);
            return reservation;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    private void setDocumentProperties(Document doc, Map<String, String> propsMap) throws CustomServiceException {
        try {
            Properties props = doc.getProperties();
            for (Entry<String, String> entry : propsMap.entrySet()) {
                String name = entry.getKey();
                Property p = props.get(name);
                if (p == null) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.PROPERTY_NOT_FOUND.getCode(), // Error code for missing properties
                            configUtil.getLocalMessage("1030", new String[]{name}) // Localized message with dynamic property name
                    );
                }
                String className = p.getClass().getSimpleName();
                setPropertyValue(props, entry.getKey(), entry.getValue(), className);
            }
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    private void setPropertyValue(Properties props, String name, String value, String className) throws Exception {
    	if(value != null  && !value.equalsIgnoreCase("")) {
            List<String> values = Arrays.asList(value.replace("[", "").replace("]", "").split(","));
            switch (className) {
                case "PropertyBooleanImpl":
                    props.putValue(name, Boolean.parseBoolean(value));
                    break;
                case "PropertyStringImpl":
                    props.putValue(name, value);
                    break;
                case "PropertyInteger32Impl":
                    props.putValue(name, Integer.parseInt(value));
                    break;
                case "PropertyFloat64Impl":
                    props.putValue(name, Double.parseDouble(value));
                    break;
                case "PropertyDateTimeImpl":
                    props.putValue(name, new SimpleDateFormat("yyyy-MM-dd").parse((String) value));
                    break;
                case "PropertyBooleanListImpl":
                    BooleanList booleanList = Factory.BooleanList.createList();
                    booleanList.addAll(values.stream().map(Boolean::parseBoolean).collect(Collectors.toList()));
                    props.putValue(name, booleanList);
                    break;
                case "PropertyInteger32ListImpl":
                    Integer32List intList = Factory.Integer32List.createList();
                    intList.addAll(values.stream().map(Integer::parseInt).collect(Collectors.toList()));
                    props.putValue(name, intList);
                    break;
                case "PropertyStringListImpl":
                    StringList strList = Factory.StringList.createList();
                    strList.addAll(values);
                    props.putValue(name, strList);
                    break;
                case "PropertyFloat64ListImpl":
                    Float64List floatList = Factory.Float64List.createList();
                    floatList.addAll(values.stream().map(Double::parseDouble).collect(Collectors.toList()));
                    props.putValue(name, floatList);
                    break;
                case "PropertyDateTimeListImpl":
                    DateTimeList dateList = Factory.DateTimeList.createList();
                    for (String strDate : values) {
                        dateList.add(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
                    }
                    props.putValue(name, dateList);
                    break;
                default:
                    throw new CustomServiceException(
                            ArchiveErrorCode.UNSUPPORTED_PROPERTY_DATATYPE.getCode(), // Error code for unsupported property data type
                            configUtil.getLocalMessage("1031", new String[]{name, value, className}) // Localized message with dynamic placeholders
                    );
            }
    	}

    }

    private String getPropertyValue(Property prop) throws CustomServiceException {
        try {
            String className = prop.getClass().getSimpleName();
            switch (className) {
                case "PropertyBooleanImpl":
                    Boolean b = prop.getBooleanValue();
                    return (b == null ? null : b.toString());
                case "PropertyStringImpl":
                    return prop.getStringValue();
                case "PropertyInteger32Impl":
                    Integer i = prop.getInteger32Value();
                    return (i == null ? null : i.toString());
                case "PropertyFloat64Impl":
                    Double f = prop.getFloat64Value();
                    return (f == null ? null : f.toString());
                case "PropertyDateTimeImpl":
                    Date d = prop.getDateTimeValue();
                    return (d == null ? null : d.toString());
                case "PropertyBooleanListImpl":
                    return getStringValue(prop.getBooleanListValue());
                case "PropertyInteger32ListImpl":
                    return getStringValue(prop.getInteger32ListValue());
                case "PropertyStringListImpl":
                    return getStringValue(prop.getStringListValue());
                case "PropertyFloat64ListImpl":
                    return getStringValue(prop.getFloat64ListValue());
                case "PropertyDateTimeListImpl":
                    return getStringValue(prop.getDateTimeListValue());
                default:
                    return null;
            }
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    private <T> String getStringValue(List<T> list) throws Exception {
        if (list == null) {
            return null;
        }

        if (list.size() == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T t : list) {
            sb.append(String.valueOf(t) + ", ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(), "]");
        return sb.toString();
    }

    private Map<String, String> getDocumentPropertiesMap(Document doc) throws CustomServiceException {
        Map<String, String> map = new HashMap<>();
        try {
            Properties props = doc.getProperties();
            for (Iterator<Property> itr = props.iterator(); itr.hasNext(); ) {
                Property p = itr.next();
                String value = getPropertyValue(p);
                if (value != null) {
                    map.put(p.getPropertyName(), getPropertyValue(p));
                }
            }
            return map;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    private List<File> getDocumentContents(Document doc) throws CustomServiceException {
        // Get content elements and iterate list.
        ContentElementList docContentList = doc.get_ContentElements();
        Iterator iter = docContentList.iterator();
        List<File> attachments = new ArrayList<File>();
        while (iter.hasNext()) {
            ContentTransfer ct = (ContentTransfer) iter.next();

            // Get the content of the element.
            byte[] buffer;
            File file = new File(ct.get_RetrievalName());
            try (InputStream is = ct.accessContentStream(); OutputStream os = new FileOutputStream(file)) {
                buffer = new byte[is.available()];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                attachments.add(file);
            } catch (IOException e) {
                throw new CustomServiceException(
                        ArchiveErrorCode.BUSINESS.getCode(),
                        e.getLocalizedMessage()
                );
            }
        }
        return attachments;
    }

    private Map<String, byte[]> getDocumentContentsMap(Document doc) throws CustomServiceException {
        Map<String, byte[]> map = new HashMap<>();
        try {
            ContentElementList docContentList = doc.get_ContentElements();
            Iterator iter = docContentList.iterator();
            while (iter.hasNext()) {
                ContentTransfer ct = (ContentTransfer) iter.next();
                String fileName = ct.get_RetrievalName();

                // Get the content of the element.
                try (InputStream is = ct.accessContentStream()) {
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream(is.available());
                    byte[] output = new byte[16384];
                    int bytesRead;
                    while ((bytesRead = is.read(output, 0, output.length)) != -1) {
                        buffer.write(output, 0, bytesRead);
                    }
                    map.put(fileName, output);
                } catch (IOException e) {
                    throw new CustomServiceException(
                            ArchiveErrorCode.BUSINESS.getCode(),
                            e.getLocalizedMessage()
                    );
                }
            }
            return map;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }

    private ContentElementList getContentElements(List<File> files) throws CustomServiceException {
        ContentElementList contentElementList = Factory.ContentElement.createList();
        for (File file : files) {
            try {
                ContentTransfer content = Factory.ContentTransfer.createInstance();
                content.set_RetrievalName(file.getName());
                content.setCaptureSource(new FileInputStream(file));
                contentElementList.add(content);
            } catch (Exception e) {
                String message = "Error adding content to element list" + (file == null ? "" : " for file '" + file.getName() + "'");
                throw new CustomServiceException(
                        ArchiveErrorCode.BUSINESS.getCode(),
                        e.getLocalizedMessage()
                );
            }
        }
        return contentElementList;
    }

    private ContentElementList getContentElementsBase64(List<CustomDocument> documents) throws CustomServiceException {
        ContentElementList contentElementList = Factory.ContentElement.createList();
        for (CustomDocument file : documents) {
            try {
                ContentTransfer content = Factory.ContentTransfer.createInstance();
                String contentType = getMimeTypeFromPath(file.getFileExt());
                content.set_RetrievalName(file.getFileName());
                if (contentType == null || contentType.length() == 0) contentType = "text/plain";
                content.set_ContentType(contentType);
                byte[] contentbytes = null;
                contentbytes = Base64.decodeBase64(file.getSource());
                ByteArrayInputStream is = new ByteArrayInputStream(contentbytes);
                content.setCaptureSource(is);
                contentElementList.add(content);
            } catch (Exception e) {
                String message = "Error adding content to element list" + (file == null ? "" : " for file '" + file.getFileName() + "'");
                throw new CustomServiceException(
                        ArchiveErrorCode.BUSINESS.getCode(),
                        e.getLocalizedMessage()
                );
            }
        }
        return contentElementList;
    }

    private List<GetClassPropertyDTO> getClassPropertiesList(PropertyDefinitionList objPropDefs) throws CustomServiceException {
        try {
            List<GetClassPropertyDTO> propsList = new ArrayList<>();
            Iterator iter = objPropDefs.iterator();
            PropertyDefinition objPropDef;

            while (iter.hasNext()) {
                objPropDef = (PropertyDefinition) iter.next();
                String symbolicName = objPropDef.get_SymbolicName();

                if (!objPropDef.get_IsSystemOwned() && !objPropDef.get_IsHidden() && !classExcludedPropertiesList.contains(symbolicName)) {
                    GetClassPropertyDTO prop = new GetClassPropertyDTO();
                    prop.setSymbolicName(symbolicName);
                    prop.setDataType(objPropDef.get_DataType().toString());

                    if ("DocumentTitle".equals(symbolicName)) {
                        prop.setRequired(true);
                    } else {
                        prop.setRequired(objPropDef.get_IsValueRequired());
                    }

                    prop.setDesc(objPropDef.get_DisplayName());
                    PropertyTemplate propTemp = objPropDef.get_PropertyTemplate();
                    LocalizedStringList localizedStringList = propTemp.get_DisplayNames();
                    LocalizedString localizedString;
                    for (int i = 0; i < localizedStringList.size(); i++) {
                        localizedString = (LocalizedString) localizedStringList.get(i);
                        if(localizedString != null && !localizedString.equals("")) {
                            if(localizedString.get_LocaleName().contains("ar".toLowerCase())){
                                prop.setDescAr(localizedString.get_LocalizedText());
                            }
                            if(localizedString.get_LocaleName().contains("en".toLowerCase())){
                                prop.setDescEn(localizedString.get_LocalizedText());
                            }
                        }
                    }
                    if (objPropDef.get_Cardinality().toString().equalsIgnoreCase("LIST")){
                        prop.setMultiValue(true);
                    }
                    prop.setDesc(LocalizationUtil.getLocalizedValue(prop.getDescAr(), prop.getDescEn()));
                    propsList.add(prop);
                }
            }
            return propsList;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
            );
        }
    }


    private List<GetClassPropertyDTO> getDocumentPropertiesValuesList(PropertyDefinitionList objPropDefs,Document document) throws CustomServiceException {
        try {
            Properties props = document.getProperties();
            List<GetClassPropertyDTO> propsList = new ArrayList<GetClassPropertyDTO>();
            Iterator iter = objPropDefs.iterator();
            PropertyDefinition objPropDef = null;
            while (iter.hasNext()) {
                objPropDef = (PropertyDefinition) iter.next();
                String symbolicName = objPropDef.get_SymbolicName();
                if (props !=null && props.isPropertyPresent(symbolicName)) {
                    if (!objPropDef.get_IsSystemOwned() && !objPropDef.get_IsHidden() & !classExcludedPropertiesList.contains(symbolicName)) {
                        GetClassPropertyDTO prop = new GetClassPropertyDTO();
                        prop.setSymbolicName(symbolicName);
                        prop.setDataType(objPropDef.get_DataType().toString());
                        prop.setRequired(objPropDef.get_IsValueRequired());
//                        prop.setDesc(objPropDef.get_DisplayName());

                        // Extract value based on data type
                        String value = "";
                        System.out.println("objPropDef.get_DataType().toString() ***  " + objPropDef.get_DataType().toString());
                        switch (objPropDef.get_DataType().toString()) {
                            case "STRING":
                                if (props.get(symbolicName) instanceof PropertyStringImpl) {
                                    value = props.getStringValue(symbolicName) != null ? props.getStringValue(symbolicName).toString() : "";
                                }
                                break;
                            case "INTEGER":
                            case "LONG":
                                if (props.get(symbolicName) instanceof PropertyInteger32Impl) {
                                    value = props.getInteger32Value(symbolicName) != null ? Integer.toString(props.getInteger32Value(symbolicName)) : "";
                                }
                                break;
                            case "FLOAT":
                                if (props.get(symbolicName) instanceof PropertyFloat64Impl) {
                                    value = props.getFloat64Value(symbolicName) != null ? Double.toString(props.getFloat64Value(symbolicName)) : "";
                                }
                                break;
                            case "DATE":
                                if (props.get(symbolicName) instanceof PropertyDateTimeImpl) {
                                    java.util.Date dateValue = props.getDateTimeValue(symbolicName);


                                    if (dateValue != null) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                        value = dateFormat.format(dateValue);

                                    } else {
                                        value = "";
                                    }
                                }
                                break;
                        }

                        prop.setValue(value);

                        PropertyTemplate propTemp = objPropDef.get_PropertyTemplate();
                        LocalizedStringList localizedStringList = propTemp.get_DisplayNames();
                        LocalizedString localizedString;
                        for (int i = 0; i < localizedStringList.size(); i++) {
                            localizedString = (LocalizedString) localizedStringList.get(i);
                            if (localizedString != null && !localizedString.equals("")) {
                                if (localizedString.get_LocaleName().contains("ar".toLowerCase())) {
                                    prop.setDescAr(localizedString.get_LocalizedText());
                                }
                                if (localizedString.get_LocaleName().contains("en".toLowerCase())) {
                                    prop.setDescEn(localizedString.get_LocalizedText());
                                }
                            }
                        }
                        prop.setDesc(LocalizationUtil.getLocalizedValue(prop.getDescAr(), prop.getDescEn()));

                        propsList.add(prop);
                    }
                }

            }
            return propsList;
        } catch (Exception e) {
            throw new CustomServiceException(
                    ArchiveErrorCode.BUSINESS.getCode(),
                    e.getLocalizedMessage()
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


    private String getPropertyDtoDataType(ObjectStore objectStore, String documentClassName,  PropertyDTO propDto) throws Exception {  
    	PropertyDefinition  propertyDefinition = fetchPropertyDefinitionByName(objectStore , documentClassName,  propDto.getSymbolicName());
    	if(propertyDefinition !=null && propDto.getPropertyValue() != null  && !propDto.getPropertyValue().equalsIgnoreCase("")) {
    	       if (!propertyDefinition.get_Cardinality().toString().equalsIgnoreCase("SINGLE")) {
    	            switch (propertyDefinition.get_DataType().toString().toUpperCase()) {
    	                case "BOOLEAN":
    	                    return "PropertyBooleanListImpl";
    	                case "STRING":
    	                    return "PropertyStringListImpl";
    	                case "DATE":
    	                    return "PropertyDateTimeListImpl";
    	                case "LONG":
    	                    return "PropertyInteger32ListImpl";
    	                case "DOUBLE":
    	                    return "PropertyFloat64ListImpl";
    	                case "FLOAT64":
    	                    return "PropertyFloat64ListImpl";
    	            }
    	        } else {
    	            switch (propertyDefinition.get_DataType().toString().toUpperCase()) {
    	                case "BOOLEAN":
    	                    return "PropertyBooleanImpl";
    	                case "STRING":
    	                    return "PropertyStringImpl";
    	                case "DATE":
    	                    return "PropertyDateTimeImpl";
    	                case "LONG":
    	                    return "PropertyInteger32Impl";
    	                case "DOUBLE":
    	                    return "PropertyFloat64Impl";
    	                case "FLOAT64":
    	                    return "PropertyFloat64Impl";
    	            }
    	        }
    	}
 

        return null;
    }
    




        // Helper method to fetch a property definition by name under a Document Class
        private  PropertyDefinition fetchPropertyDefinitionByName(ObjectStore objectStore,  String  documentClassName, String propertyName)  throws Exception{
        	 PropertyFilter pf = new PropertyFilter();
             pf.addIncludeType(0, null, Boolean.TRUE, FilteredPropertyType.ANY, null);
          // Fetch selected class definition from the server
             ClassDefinition objClassDef = Factory.ClassDefinition.fetchInstance(objectStore, documentClassName, pf);
          // Get PropertyDefinitions property from the property cache
          	PropertyDefinitionList objPropDefs = objClassDef.get_PropertyDefinitions();
            Iterator iter = objPropDefs.iterator();
        	PropertyDefinition objPropDef = null;
            while (iter.hasNext()) {
            	objPropDef = (PropertyDefinition) iter.next();
            	String symbolicName = objPropDef.get_SymbolicName();

                if (!objPropDef.get_IsSystemOwned() && !objPropDef.get_IsHidden() & !classExcludedPropertiesList.contains(symbolicName) 
                		&& objPropDef.get_SymbolicName().equals(propertyName) ) {
                	return objPropDef;
                }
            }
            return null;
        }
}
