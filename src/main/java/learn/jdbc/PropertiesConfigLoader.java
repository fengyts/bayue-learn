package learn.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfigLoader {

	private static Properties props;

//	public PropertiesConfigLoader(Properties prop) {
//		if (null == prop) {
//			throw new NullPointerException("配置文件为空");
//		}
//		props = prop;
//	}

//	public PropertiesConfigLoader(String propFilePath) {
//		try {
//			props = new Properties();
//			InputStream is = new FileInputStream(propFilePath);
//			props.load(is);
//			is.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void loadPropertieFile(String propFilePath){
		try {
			props = new Properties();
			InputStream is = new FileInputStream(propFilePath);
			props.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPropertieFile(Properties prop){
		props = prop;
	}

	public static Properties getProperties() {
		if (null == props) {
			throw new NullPointerException("配置文件为空");
		}
		return props;
	}

}
