package r1.commons.databaseconnection;

import java.sql.*;

public final class QueryExecutor {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	DatabaseConnection databaseConnection;

	public ResultSet executeQuery(String query) throws Exception {
		databaseConnection = DatabaseConnection.getInstance();
		connection = databaseConnection.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void closeConnections() {
		databaseConnection.close(resultSet);
		databaseConnection.close(preparedStatement);
		databaseConnection.close(connection);
	}
}
