package com.dataserve.mahfuzatintegration.util;

import com.dataserve.mahfuzatintegration.exception.ConnectionException;
import com.dataserve.mahfuzatintegration.model.dto.AuditDTO;
import com.dataserve.mahfuzatintegration.model.dto.ClassDTO;
import com.dataserve.mahfuzatintegration.model.dto.EDSChoiceDTO;
import com.dataserve.mahfuzatintegration.model.dto.EDSChoiceListDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseConnection implements AutoCloseable {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public DatabaseConnection() throws ConnectionException {
//		try {
            // Load Driver CLass.
//            Class.forName("oracle.jdbc.driver.OracleDriver");
			// Load Driver CLass.
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Build connection String
//            String url = String.format("jdbc:oracle:thin:@%s:%d:%s", ConfigManager.getDatabaseServerName(), ConfigManager.getDatabasePortNumber(), ConfigManager.getDatabaseName());
//			String url = String.format("jdbc:sqlserver:thin:@%s:%d:%s", ConfigManager.getDatabaseServerName(), ConfigManager.getDatabasePortNumber(), ConfigManager.getDatabaseName());

			// Get connection
////			con = DriverManager.getConnection(url, ConfigManager.getDatabaseUsername(), ConfigManager.getDatabasePassword());
//		} catch (Exception e) {
//			throw new ConnectionException("Error getting connection to database", e);
//		}
	}

	@Override
	public void close() {
		close(ps);
		close(rs);
		close(con);
	}

	private void close(AutoCloseable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				LogUtil.warn("Resource was not closed successfully", e);
			}
		}
	}

	public List<ClassDTO> getSubClassesByParentId(Integer parentId) throws ConnectionException {
		List<ClassDTO> classes = new ArrayList<>();
		try {
			String sql = "SELECT CLASSIFICATION_ID, CLASS_AR_NAME, CLASS_EN_NAME, SYMPOLIC_NAME, CLASS_CODE FROM CLASSIFICTIONS WHERE is_fn_added = 1 AND PARENT_ID = ?";
			if (parentId == null) {
				sql = sql.replace("= ?", "IS NULL");
			}

			ps = con.prepareStatement(sql);
			if (parentId != null) {
				ps.setInt(1, parentId);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				ClassDTO c = new ClassDTO();
				c.setId(rs.getInt("CLASSIFICATION_ID"));
				c.setCode(rs.getString("CLASS_CODE"));
				c.setSymbolicName(rs.getString("SYMPOLIC_NAME"));
				c.setNameAr(rs.getNString("CLASS_AR_NAME"));
				c.setNameEn(rs.getString("CLASS_EN_NAME"));
				c.setParentId(parentId);
				classes.add(c);
			}
			return classes;
		} catch (SQLException e) {
			throw new ConnectionException("Failed to get subclasses of class " + parentId, e);
		}
	}

	public Map<String, EDSChoiceListDTO> getEDSChoiceListsByClassName(String classSymbolicName) throws ConnectionException {
		Map<String, EDSChoiceListDTO> choiceLists = new HashMap<>();
		try {
			ps = con.prepareStatement("SELECT PROPERTY, LISTDISPNAME, LANG, DISPNAME, VALUE FROM EDS_CHOICES WHERE OBJECTTYPE = ?");
			ps.setString(1, classSymbolicName);
			rs = ps.executeQuery();
			while (rs.next()) {
				String propertyName = rs.getString("PROPERTY");
				if (!choiceLists.containsKey(propertyName)) {
					EDSChoiceListDTO listDto = new EDSChoiceListDTO();
					listDto.setPropertyName(rs.getNString("PROPERTY"));
					listDto.setChoiceListName(rs.getNString("LISTDISPNAME"));
					listDto.setChoiceList(new ArrayList<EDSChoiceDTO>());
					choiceLists.put(propertyName, listDto);
				}

				EDSChoiceDTO dto = new EDSChoiceDTO();
				dto.setDisplayName(rs.getString("DISPNAME"));
				dto.setLang(rs.getString("LANG"));
				dto.setValue(rs.getString("VALUE"));

				choiceLists.get(propertyName).getChoiceList().add(dto);
			}
			return choiceLists;
		} catch (Exception e) {
			throw new ConnectionException("Failed to get records from EDS_CHOICES table", e);
		}
	}

	public List<String> getClassesByLdapUserId(String ldapUserId) throws ConnectionException {
		List<String> classNames = new ArrayList<>();
		StringBuilder sql = new StringBuilder(400);
		sql.append("SELECT CLASSES.SYMPOLIC_NAME CLASS_NAME ");
		sql.append("FROM USERS USERS ");
		sql.append("JOIN DEPARTMENTS DEPTS ON (USERS.DEPARTMENT_ID = DEPTS.DEPT_ID) ");
		sql.append("JOIN CLASS_DEPT CLASS_DEPT ON (DEPTS.DEPT_ID = CLASS_DEPT.DEPT_ID) ");
		sql.append("JOIN CLASSIFICTIONS CLASSES ON (CLASS_DEPT.CLASSIFICATION_ID = CLASSES.CLASSIFICATION_ID) ");
		sql.append("WHERE USERS.UsernameLDAP = ?");
		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, ldapUserId);
			rs = ps.executeQuery();
			while (rs.next()) {
				classNames.add(rs.getString("CLASS_NAME"));
			}
			return classNames;
		} catch (Exception e) {
			throw new ConnectionException("Failed to fetch classifications for user " + ldapUserId, e);
		}
	}

	public long addIntegrationAuditLog(AuditDTO dto) throws ConnectionException {
		try {
			ps = con.prepareStatement("INSERT INTO INTEGRATION_AUDIT(USER_NAME, TRANSACTION_DATE, API, RESULT) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getUsername());
			ps.setDate(2, dto.getTimestamp());
			ps.setString(3, dto.getApi());
			ps.setNString(4, dto.getResult());
			ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            long auditId = 0;
        	if (rs.next()) {
        		auditId = rs.getInt(1);
        	}

        	if (auditId != 0) {
        		ps = con.prepareStatement("INSERT INTO INTEGRATION_AUDIT_PARAMETERS (AUDIT_ID, PARAMETER_NAME, PARAMETER_VALUE) VALUES (?, ?, ?)");
        		Map<String, String> params = dto.getParams();
        		for (String key : params.keySet()) {
        			addIntegrationAuditParamatersLog(auditId, key, params.get(key), ps);
        		}
        	}

        	return auditId;
		} catch (Exception e) {
			throw new ConnectionException("Failed to intsert logs into database", e);
		}

	}

	private void addIntegrationAuditParamatersLog(long auditId, String paramName, String paramValue, PreparedStatement ps) {
		try {
			ps.setLong(1, auditId);
    		ps.setString(2, paramName);
    		ps.setNString(3, paramValue);
    		ps.executeUpdate();
		} catch (Exception e) {
			LogUtil.error("Failed to insert parameter for AUDIT_ID " + auditId + ", parameter name: " + paramName + ", parameter value: " + paramValue, e);
		}
	}

}
