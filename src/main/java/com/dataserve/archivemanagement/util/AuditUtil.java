package com.dataserve.archivemanagement.util;
import com.dataserve.archivemanagement.model.dto.AuditDTO;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.json.JSONException;


public class AuditUtil implements Runnable {
	private String api;
	private String username;
	private String result;
	private Map<String, String> params;

	public AuditUtil(String api, String username, JSONObject object, String result) {
		this.api = api;
		this.username = username;
		this.result = result;
		params = new HashMap<>();		
		for (Iterator<String> keys = object.keys(); keys.hasNext();) {
			String key = keys.next();
			try {
				this.params.put(key, object.getString(key));
			} catch (JSONException e) {
				LogUtil.error("Error reading attribute '" + key + "'", e);
			}
		}
	}

	@Override
	public void run() {
		long auditId = 0;
		Date timestamp = new Date(System.currentTimeMillis());
		AuditDTO dto = new AuditDTO();
		dto.setUsername(username);
		dto.setApi(api);
		dto.setTimestamp(timestamp);
		dto.setParams(params);
		dto.setResult(result);
		
		try (DatabaseConnection con = new DatabaseConnection()) {
			auditId = con.addIntegrationAuditLog(dto);
		} catch (Exception e) {
			LogUtil.error("Failed to log audit {api: " + api + ", username: " + username + ", params: " + params.toString() + "} into database", e);
		}
		
		try (ElasticsearchConnection con = new ElasticsearchConnection()) {
			con.audit(auditId, dto);
		} catch (Exception e) {
			LogUtil.error("Failed to log audit {api: " + api + ", username: " + username + ", params: " + params.toString() + "} into kibana", e);
		}
	}

}
