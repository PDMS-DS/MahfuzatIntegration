package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Map;

import com.dataserve.archivemanagement.config.ConfigUtil;
import com.dataserve.archivemanagement.exception.CustomServiceException;
import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.dto.ClassDTO;
import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;
import com.dataserve.archivemanagement.util.ArchiveErrorCode;
import com.dataserve.archivemanagement.util.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	@Autowired
	private ConfigUtil configUtil;
	public List<ClassDTO> getAllDocumentClasses() throws CustomServiceException {
		try (DatabaseConnection dbUtil = new DatabaseConnection()) {
			List<ClassDTO> classes = dbUtil.getSubClassesByParentId(null);
			for (ClassDTO c : classes) {
				loadSubclasses(dbUtil, c);
			}
	        return classes;
		} catch (Exception e) {
			throw new CustomServiceException(
					ArchiveErrorCode.BUSINESS.getCode(),
					e.getLocalizedMessage()
			);
		}
	}

	private void loadSubclasses(DatabaseConnection db, ClassDTO c) throws CustomServiceException {
		try {
			List<ClassDTO> classes = db.getSubClassesByParentId(c.getId());
			c.setSubclasses(classes);
			for (ClassDTO sub : classes) {
				loadSubclasses(db, sub);
			}
		}catch (Exception e) {
			throw new CustomServiceException(
					ArchiveErrorCode.FAILED_TO_LOAD_SUBCLASSES.getCode(),
					configUtil.getLocalMessage("1032", new String[]{c.getId().toString()})
			);
		}

	}

	public Map<String, EDSChoiceListDTO> getClassEDSPropertesBySymbolicName(String classSymbolicName) throws CustomServiceException {
		try (DatabaseConnection con = new DatabaseConnection()) {
			return con.getEDSChoiceListsByClassName(classSymbolicName);
		} catch (Exception e) {
			throw new CustomServiceException(
					ArchiveErrorCode.BUSINESS.getCode(),
					e.getLocalizedMessage()
			);
		}
	}

	public List<String> getUserClasses(String ldapUserId) throws CustomServiceException {
		try (DatabaseConnection con = new DatabaseConnection()) {
			return con.getClassesByLdapUserId(ldapUserId);
		} catch (Exception e) {
			throw new CustomServiceException(
					ArchiveErrorCode.BUSINESS.getCode(),
					e.getLocalizedMessage()
			);
		}
	}
	
	
}
