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

	/** The method selects the operator '=' or 'Like' and enters the Search value depending on the SearchBy value selected
	 * @param operatorValue: The value to be entered for operator: = or Like
	 * @param textBoxValue: The value entered for search depending upon the SearchBy value selected
	 */
	@Step
	public void enterOperatorAndSearchTextBox(String operatorValue, String textBoxValue) {
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

	/** the method compares the list of all validation messages displayed against the list of expected validation messages and returns true/false
	 * @param listOfValidationMsgs: List of all validation messages displayed as per the SearchBy value selected
	 * @param expValidationMsg: List of expected validation messages that should be displayed as per the SearchBy value selected
	 * @return: true or false after comparing if the actual validation messages match the expected validation messages
	 */
	@Step
	public boolean verifyValidationMessages(List<String> listOfValidationMsgs, String expValidationMsg) {
		for (String validationMsg : listOfValidationMsgs) {
			if (!validationMsg.equals(expValidationMsg))
				return false;
		}
		return true;
	}

	/** The method enters the Search values in the textboxes depending on the SearchBy dropdown value selected
	 * @param searchValues: the Search values in the textboxes depending on the SearchBy dropdown value selected
	 */
	@Step
	public void enterSearchValue(List<String> searchValues) {
		switch (eparsProHandoffPage.getSearchByDropdownValue()) {
		case "Visit Number":
			eparsProHandoffPage.enterVisitNumberTextBox(searchValues.get(0));
			break;

		case "Invoice Number":
			eparsProHandoffPage.enterInvoiceNumberTextBox(searchValues.get(0));
			break;

		case "Social Security Number":
			eparsProHandoffPage.enterSSNTextBox(searchValues.get(0));
			break;

		case "Last/First Name":
			eparsProHandoffPage.enterLastNameTextBox(searchValues.get(0));
			eparsProHandoffPage.enterFirstNameTextBox(searchValues.get(1));
			break;

		case "Medical Records Number":
			eparsProHandoffPage.enterMRNTextBox(searchValues.get(0));
			break;

		case "Claim Number":
			eparsProHandoffPage.enterClaimNumberTextBox(searchValues.get(0));
			break;

		case "DOB":
			eparsProHandoffPage.enterDateOfBirthTextBox(searchValues.get(0));
			break;

		case "Date of Service":
			eparsProHandoffPage.enterDateOfServiceTextBox(searchValues.get(0));
			break;
		}
	}

	/**
	 * The method fetches the database values for Search depending upon the SearchBy value selected and returns them
	 * @param searchBy: The SearchBy Value selected
	 * @param query: The DataBase query to fetch the Search value depending upon the SearchBy value selected. example: Invoice# for Invoice Number selected in SearchBy
	 * @return: the fetched Search Values from the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws Exception
	 */
	@Step
	public List<String> getDBSearchValues(String searchBy, String query)
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