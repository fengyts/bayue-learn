package learn.jdbc;


public class GenericDataSourceConfig extends JdbcPoolConfig {
	
	private static PropertiesConfigModel model;

	//	private String dbType = "mysql"; // 默认数据库驱动为mysql
	//	private String driver;
	//	private String url;
	//	private String username;
	//	private String password;
	
	//	public GenericDataSourceConfig(Properties prop) {
	//		String dbType = prop.getProperty("db_type");
	//		if (StringUtils.isNotBlank(dbType)) {
	//			this.dbType = dbType;
	//		}
	//		this.driver = prop.getProperty("db_driver");
	//		this.url = prop.getProperty("db_url");
	//		this.username = prop.getProperty("db_username");
	//		this.password = prop.getProperty("db_password");
	//	}
	
	public GenericDataSourceConfig(PropertiesConfigModel propModel) {
		model = propModel;
	}

	public String getDbType() {
		return model.getDbType();
	}

	public String getDriver() {
		return model.getDbDriver();
	}

	public String getDbUrl() {
		return model.getDbUrl();
	}

	public String getUsername() {
		return model.getUsername();
	}

	public String getPassword() {
		return model.getPassword();
	}

}
