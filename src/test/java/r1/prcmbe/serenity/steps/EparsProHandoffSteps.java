package r1.prcmbe.serenity.steps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.EparsProHandoffPage;

public class EparsProHandoffSteps {
	EparsProHandoffPage eparsProHandoffPage;
	CommonMethods commonMethods;
	private static String dbQueryFilename = "EparsProHandoff";
	List<String> listOfSearchValues = new ArrayList<>();

	@Step
	public void enterOperatorAndSearchByTextBox(String operatorValue, String textBoxValue) {
		switch (eparsProHandoffPage.getSearchByDropdownValue()) {
		case "Visit Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterVisitNumberTextBox(textBoxValue);
			break;

		case "Invoice Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterInvoiceNumberTextBox(textBoxValue);
			break;

		case "Social Security Number":
			eparsProHandoffPage.enterSSNTextBox(textBoxValue);
			break;

		case "Last/First Name":
			eparsProHandoffPage.enterLastNameTextBox(textBoxValue);
			break;

		case "Medical Records Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterMRNTextBox(textBoxValue);
			break;

		case "Claim Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterClaimNumberTextBox(textBoxValue);
			break;

		case "DOB":
			eparsProHandoffPage.enterDateOfBirthTextBox(textBoxValue);
			break;

		case "Date of Service":
			eparsProHandoffPage.enterDateOfServiceTextBox(textBoxValue);
			break;
		}
	}

	@Step
	public boolean verifyValidationMessages(List<String> listOfValidationMsgs, String expValidationMsg) {
		for (String validationMsg : listOfValidationMsgs) {
			if (!validationMsg.equals(expValidationMsg))
				return false;
		}
		return true;
	}

	@Step
	public void enterSearchByValue(List<String> searchByValues) {
		switch (eparsProHandoffPage.getSearchByDropdownValue()) {
		case "Visit Number":
			eparsProHandoffPage.enterVisitNumberTextBox(searchByValues.get(0));
			break;

		case "Invoice Number":
			eparsProHandoffPage.enterInvoiceNumberTextBox(searchByValues.get(0));
			break;

		case "Social Security Number":
			eparsProHandoffPage.enterSSNTextBox(searchByValues.get(0));
			break;

		case "Last/First Name":
			eparsProHandoffPage.enterLastNameTextBox(searchByValues.get(0));
			eparsProHandoffPage.enterFirstNameTextBox(searchByValues.get(1));
			break;

		case "Medical Records Number":
			eparsProHandoffPage.enterMRNTextBox(searchByValues.get(0));
			break;

		case "Claim Number":
			eparsProHandoffPage.enterClaimNumberTextBox(searchByValues.get(0));
			break;

		case "DOB":
			eparsProHandoffPage.enterDateOfBirthTextBox(searchByValues.get(0));
			break;

		case "Date of Service":
			eparsProHandoffPage.enterDateOfServiceTextBox(searchByValues.get(0));
			break;
		}
	}

	@Step
	public List<String> getDBSearchByValues(String searchBy, String query)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename)));
		listOfSearchValues.clear();
		switch (searchBy) {
		case "Visit Number":
			while (DatabaseConn.resultSet.next()) {
				listOfSearchValues.add(DatabaseConn.resultSet.getString("EncounterID"));
			}
			break;

		case "Invoice Number":
			while (DatabaseConn.resultSet.next()) {
				listOfSearchValues.add(DatabaseConn.resultSet.getString("Invoicenumber"));
			}
			break;

		case "SSN":
			while (DatabaseConn.resultSet.next()) {
				listOfSearchValues.add(DatabaseConn.resultSet.getString("SSN"));
			}
			break;
		}
		return listOfSearchValues;
	}
}