package com.dataserve.mahfuzatintegration.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties props;

	static {
		props = new Properties();
		try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			props.load(is);
		} catch (Exception e) {
			LogUtil.error("Failed to load application.properties", e);
		}		
	}
	
	public static String getFileNetURI() {
		return props.getProperty("content-engine-uri");
	}
	
	public static String getFileNetObjectStoreName() {
		return props.getProperty("content-engine-object-store-name");
	}
	
	public static String getFileNetStanza() {
		return props.getProperty("content-engine-stanza");
	}
	
	public static String getDatabaseServerName() {
		return props.getProperty("database-server-name");
	}
	
	public static int getDatabasePortNumber() {
		return Integer.parseInt(props.getProperty("database-port-number", "1433"));
	}
	
	public static String getDatabaseName() {
		return props.getProperty("database-name");
	}
	
	public static String getDatabaseUsername() {
		return props.getProperty("database-username");
	}
	
	public static String getDatabasePassword() {
		return props.getProperty("database-password");
	}
	
	public static int getDatabaseFetchBatchSize() {
		return Integer.parseInt(props.getProperty("database-fetch-batch-size", "100"));
	}

	public static String getElasticSearchHost() {
		return props.getProperty("elasticsearch-host", "localhost");
	}

	public static int getElasticSearchPort() {
		return Integer.parseInt(props.getProperty("elasticsearch-port", "9200"));
	}
}
