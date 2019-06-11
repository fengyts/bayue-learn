package learn.jdbc;

import java.util.Properties;

public class PropertieUtil {

	private static Properties props = PropertiesConfigLoader.getProperties();

	//	public PropertieUtil(String filePath) throws Exception {
	//		props = initProp(filePath);
	//	}
	//
	//	public PropertieUtil(Properties prop) {
	//		props = prop;
	//	}
	//
	//	private Properties initProp(String filePath) throws Exception {
	//		File file = new File(filePath);
	//		return initProp(file);
	//	}
	//
	//	private Properties initProp(File propertieFile) throws Exception {
	//		Properties prop = new Properties();
	//		InputStream inStream = new FileInputStream(propertieFile);
	//		prop.load(inStream);
	//		inStream.close();
	//		return prop;
	//	}

	public static String getValue(String key) {
		if (null == props)
			throw new NullPointerException("配置文件为空");
		return props.getProperty(key);
	}

}
