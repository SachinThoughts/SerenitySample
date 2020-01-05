package r1.prcmbe.serenity.steps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;

public class DefectOverrideSteps {

	/**
	 * This method verify Selected Defect SubCategory Value with Database
	 * @return boolean Value based on verification
	 * @throws SQLException
	 */
	@Step
	public boolean verifySelectedDefectSubCategoryWithDB(String selectedSubCatValue) throws SQLException {
		List<String> listOfDefectSubCategoryInDB = new ArrayList<>();
		while (DatabaseConn.resultSet.next()) {
			listOfDefectSubCategoryInDB.add(DatabaseConn.resultSet.getString("DefectSubCategoryDesc"));
		}
		return listOfDefectSubCategoryInDB.contains(selectedSubCatValue);
	}
}
