package r1.prcmbe.serenity.steps;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import com.ibm.icu.text.SimpleDateFormat;
import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefaultHandoffPage;

public class DefaultHandoffSteps {

	DefaultHandoffPage defaultHandoffPage;
	CommonMethods commonMethods;
	private static String dbQueryFilename = "DefaultHandoff";
	String firstName, lastName;

	@Step
	public String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

	@Step
	public String getDisplayName(String queryName) throws ClassNotFoundException, SQLException, IOException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilename), CommonMethods.loadProperties("prcmBeUsername")));
		while (DatabaseConn.resultSet.next()) {
			firstName = DatabaseConn.resultSet.getString("FirstName");
			lastName = DatabaseConn.resultSet.getString("LastName");
		}
		String displayName = firstName + " " + lastName;
		return displayName;
	}
}