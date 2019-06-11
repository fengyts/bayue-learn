package testjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import learn.jdbc.JdbcPool;
import learn.jdbc.PropertiesConfigLoader;

public class DbDataRecover {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;

	private static JdbcPool jdbcPool;

	static {
		Properties prop = initProp();
		jdbcPool = new JdbcPool(prop);
	}

	private static Properties initProp() {
		Properties prop = new Properties();
		final String url = "jdbc:mysql://47.94.199.26:3306/ygbi1?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&useSSL=false";
		final String user = "chadmin";
		final String password = "8-#xgC%p(t85X";
		final String db_driver = "com.mysql.jdbc.Driver";
		final String db_schema = "ygbi1";
		prop.setProperty("db_url", url);
		prop.setProperty("db_username", user);
		prop.setProperty("db_password", password);
		prop.setProperty("db_schema", db_schema);
		prop.setProperty("db_driver", db_driver);
		PropertiesConfigLoader propLoader = new PropertiesConfigLoader();
		propLoader.loadPropertieFile(prop);
		return prop;
	}

	public static void recoverFileDb() {
		try {
			final String basePath = "C:\\Users\\lenovopc\\Desktop\\ygbi\\";
			final String recoverFile = basePath + "recover\\";
			final String srcFile = basePath + "databackup\\ygbiproperty_fee_bill_insertdata.sql";
			File file = new File(srcFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			int totalCount = 0;
			int fileNo = 1; // new file number
			final int size = 10000;
			final String insertStatement = "INSERT INTO";
			final String fileStart = "INSERT INTO `property_fee_bill` (`id`, `house_id`, `house_name`, `building_id`, `building_name`, `community_id`, `community_name`, `date`, `amount`, `is_paid`, `gmt_pay`, `pay_info`, `is_deleted`, `gmt_create`, `gmt_modified`) VALUES \r\n";
			String newFile = recoverFile + "1.sql";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
			bw.append(fileStart);
			while (null != (line = br.readLine())) {
				if (line.isEmpty() || line.trim().startsWith(insertStatement)) {
					continue;
				}
				if (line.endsWith(";")) {
					line = line.substring(0, line.length() - 1) + ",";
				}
				int n = totalCount / size + 1;
				if (n > fileNo) {
					fileNo = n;
					newFile = recoverFile + fileNo + ".sql";
					bw.flush();
					bw.close();
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));
					bw.append(fileStart);
				} else {
					if ((totalCount + 1) % size == 0) { // file end with by symbol ';'
						line = line.substring(0, line.length() - 1) + ";";
					}
				}
				totalCount++;
				bw.append(line).append("\r\n");
			}

			System.out.println(totalCount);
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getConnection() throws SQLException {
		conn = jdbcPool.getConnection();
	}

	private void execute(String sql) {
		try {
			getConnection();
			//			pstat = conn.prepareStatement(sql);
			//			pstat.execute();
			//			pstat.executeBatch();

			stat = conn.createStatement();
			stat.execute(sql);
			//			stat.executeLargeUpdate(sql);
			//			stat.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn)
					conn.close();
				if (null != pstat)
					pstat.close();
				if (null != stat)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String parseSql(File file) {
		final StringBuilder sql = new StringBuilder();
		System.out.println("start handle sql file: " + file.getName());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = null;
			while (null != (line = br.readLine())) {
				sql.append(line);
			}
			//			System.out.println("success handle sql file: " + f.getName()); 
			return sql.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void doRecover(String recoverFile) {
		File file = new File(recoverFile);
		File[] sqlFiles = file.listFiles();
		for (File f : sqlFiles) {
			String sql = parseSql(f);
			ThreadPool.getThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					execute(sql);
				}
			});
		}
	}

	static final class ThreadPool {
		private static final int THREAD_SIZE = 8;
		private static volatile ExecutorService threadFixedPool;

		public static ExecutorService getThreadPool() {
			if (null == threadFixedPool) {
				synchronized (ThreadPool.class) {
					if (null == threadFixedPool) {
						threadFixedPool = Executors.newFixedThreadPool(THREAD_SIZE);
					}
				}
			}
			return threadFixedPool;
		}
	}

	public static void main(String[] args) {
		try {
			//			recoverFileDb();

			final String basePath = "C:\\Users\\lenovopc\\Desktop\\ygbi\\";
			final String recoverFile = basePath + "recover\\";
			DbDataRecover recover = new DbDataRecover();

			recover.doRecover(recoverFile);

			//			final String specialF = recoverFile + "10.sql";
			//			String sql = recover.parseSql(new File(specialF));
			//			recover.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
