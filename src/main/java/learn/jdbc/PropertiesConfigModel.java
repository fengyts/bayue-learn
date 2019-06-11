package learn.jdbc;

import ng.bayue.util.StringUtils;

public class PropertiesConfigModel {

	private String dbType = "mysql";
	private String dbDriver;
	private String dbSchema;
	private String dbUrl;
	private String username;
	private String password;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		if (StringUtils.isNotBlank(dbType)) {
			this.dbType = dbType;
		}
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
