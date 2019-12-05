package r1.prcmbe.serenity.steps;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Assert;

import r1.commons.databaseconnection.DatabaseConn;

public class FacilityGroupConfigSteps {

	public boolean IsNewAddedColumnVisibleInDB(String newColumnName) {
		try {
			ResultSetMetaData rsMetaData = DatabaseConn.resultSet.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i < numberOfColumns; i++) {
				if (rsMetaData.getColumnName(i).equals(newColumnName)) {
					return true;
				}
			}

		} catch (SQLException exception) {
			Assert.assertTrue("IsPRCMenabled column name is not displayed.\nThe Technical Error is:\n" + exception,
					false);
		}
		return false;
	}
}
