package r1.prcmbe.serenity.steps;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefaultHandoffPage;

public class DefaultHandoffSteps {

	DefaultHandoffPage defaultHandoffPage;
	CommonMethods commonMethods;
	private static String dbQueryFilename = "DefaultHandoff";
	String firstName, lastName, createdDate, followupDate;
	DateFormat outputFormat, inputFormat;

	/**
	 * @param query to get created Date
	 * @param invoiceNumber the invoice number of the account for which created date is needed
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	@Step
	public String getHandoffCreatedDate(String query, String invoiceNumber)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename), invoiceNumber));
		while (DatabaseConn.resultSet.next()) {
			createdDate = DatabaseConn.resultSet.getString("UpdatedDateTime");
		}
		outputFormat = new SimpleDateFormat("d MMMM yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(createdDate);
		return outputFormat.format(date);
	}

	/**
	 * @param query to get followup date
	 * @param invoiceNumber: the invoice number of the account for which followup date is needed
	 * @return expected followup date
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	@Step
	public String getHandoffFollowupDate(String query, String invoiceNumber)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename), invoiceNumber));
		while (DatabaseConn.resultSet.next()) {
			followupDate = DatabaseConn.resultSet.getString("NextFollowUpDate");
		}
		outputFormat = new SimpleDateFormat("d MMMM yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(followupDate);
		return outputFormat.format(date);
	}

	/**
	 * @param queryName: query to get display name
	 * @return the display name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws Exception
	 */
	@Step
	public String getDisplayName(String queryName) throws ClassNotFoundException, SQLException, IOException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilename), CommonMethods.loadProperties("prcmBeUsername")));
		while (DatabaseConn.resultSet.next()) {
			firstName = DatabaseConn.resultSet.getString("FirstName");
			lastName = DatabaseConn.resultSet.getString("LastName");
		}
		return firstName + " " + lastName;
	}
}