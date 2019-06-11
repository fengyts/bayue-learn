package learn.jdbc;

import java.util.Properties;

public class PropertiesConfigResovler {

	public static PropertiesConfigModel getConfig(String propertieFilePath) {
		try {
			return initConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PropertiesConfigModel getConfig(Properties prop) {
		try {
			return initConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PropertiesConfigModel initConfig() throws Exception {
		final PropertiesConfigModel config = new PropertiesConfigModel();
		config.setDbDriver(PropertieUtil.getValue("db_driver"));
		config.setDbSchema(PropertieUtil.getValue("db_schema"));
		config.setDbType(PropertieUtil.getValue("db_type"));
		config.setUsername(PropertieUtil.getValue("db_username"));
		config.setPassword(PropertieUtil.getValue("db_password"));
		config.setDbUrl(PropertieUtil.getValue("db_url"));

		return config;
	}

}
