package com.dataserve.archivemanagement.service;

import java.util.List;
import java.util.Map;

import com.dataserve.archivemanagement.exception.ServiceException;
import com.dataserve.archivemanagement.model.dto.ClassDTO;
import com.dataserve.archivemanagement.model.dto.EDSChoiceListDTO;
import com.dataserve.archivemanagement.util.DatabaseConnection;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	public List<ClassDTO> getAllDocumentClasses() throws ServiceException {
		try (DatabaseConnection dbUtil = new DatabaseConnection()) {
			List<ClassDTO> classes = dbUtil.getSubClassesByParentId(null);
			for (ClassDTO c : classes) {
				loadSubclasses(dbUtil, c);
			}
	        return classes;
		} catch (Exception e) {
			throw new ServiceException("Failed to get all classifications", e);
		}
	}

	private void loadSubclasses(DatabaseConnection db, ClassDTO c) throws Exception {
		try {
			List<ClassDTO> classes = db.getSubClassesByParentId(c.getId());
			c.setSubclasses(classes);
			for (ClassDTO sub : classes) {
				loadSubclasses(db, sub);
			}
		} catch (Exception e) {
			throw new Exception("Failed to load subclasses for class with id " + c.getId().toString());
		}
	}

	public Map<String, EDSChoiceListDTO> getClassEDSPropertesBySymbolicName(String classSymbolicName) throws ServiceException {
		try (DatabaseConnection con = new DatabaseConnection()) {
			return con.getEDSChoiceListsByClassName(classSymbolicName);
		} catch (Exception e) {
			throw new ServiceException("Failed to get EDS Properties for class '" + classSymbolicName + "'", e);
		}
	}

	public List<String> getUserClasses(String ldapUserId) throws ServiceException {
		try (DatabaseConnection con = new DatabaseConnection()) {
			return con.getClassesByLdapUserId(ldapUserId);
		} catch (Exception e) {
			throw new ServiceException("Failed to get classes for user " + ldapUserId, e);
		}
	}
	
	
}
