package learn.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import learn.jdbc.poolplugins.BasePooledObjectFactory;
import learn.jdbc.poolplugins.PooledObject;

//import org.apache.commons.pool2.BasePooledObjectFactory;
//import org.apache.commons.pool2.PooledObject;


public class JdbcConnectionFactory extends BasePooledObjectFactory<Connection> {
	
	private Connection conn;
	
	public JdbcConnectionFactory(DataSource dataSouce){
		try {
			create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection create() throws Exception {
		return conn;
	}

	@Override
	public PooledObject<Connection> wrap(Connection obj) {
		return null;
	}
	

}
