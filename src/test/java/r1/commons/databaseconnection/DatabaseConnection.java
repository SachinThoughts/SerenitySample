package r1.commons.databaseconnection;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import r1.commons.utilities.CommonMethods;

public class DatabaseConnection {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getName());
	private static DatabaseConnection db;
	private String connectionUrl;

	public DatabaseConnection() throws Exception {
		String serverHost = CommonMethods.loadProperties("serverHost");
		String dbName = CommonMethods.loadProperties("dbName");
		String driver = CommonMethods.loadProperties("drivers");
		String path = System.getProperty("java.library.path");
		path = "src\\test\\resources\\drivers" + ";" + path;
		System.setProperty("java.library.path", path);

		try {
			final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
			sysPathsField.setAccessible(true);
			sysPathsField.set(null, null);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		try {
			connectionUrl = "jdbc:sqlserver://" + serverHost + ";databaseName=" + dbName + ";integratedSecurity=true";
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static DatabaseConnection getInstance() throws Exception {
		if (null == db) {
			db = new DatabaseConnection();
		}
		return db;
	}

	public Connection getConnection() throws Exception {
		System.out.println(connectionUrl);
		return DriverManager.getConnection(connectionUrl);
	}

	public void close(final Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("SQL Exception thrown while closing Connection: ", e);
			}
		}
	}

	public void close(final PreparedStatement preparedStatement) {
		if (null != preparedStatement) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOGGER.error("SQL Exception thrown while closing Prepared statement: ", e);
			}
		}
	}

	public void close(final ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.error("SQL Exception thrown while closing Resultset: ", e);
			}
		}
	}
}