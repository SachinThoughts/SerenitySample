package r1.prcmbe.serenity.steps;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.SearchPage;

public class SearchPageSteps {

	SearchPage searchPage;
	ResultSetMetaData resultSetMetaData;

	@Steps
	LoginSteps loginSteps;

	@Steps
	FinancialInfoSteps financialInfoSteps;

	@Step
	public boolean verifyInvoiceIDWithLikeOperator(String dbInvoiceId) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceID : searchPage.getlistOfInvoiceID()) {
				if (!invoiceID.toLowerCase().contains(dbInvoiceId.toLowerCase())) {
					return false;
				}
			}
			searchPage.clickSearchInvoiceID();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbInvoiceId.contains(searchPage.getInvoiceID().toLowerCase());
	}

	@Step
	public boolean verifyInvoiceNumberWithEqualOperator(String dbInvoiceNum) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceNum : searchPage.getlistOfInvNum()) {
				if (!invoiceNum.equalsIgnoreCase(dbInvoiceNum)) {
					loginSteps.log("The incorrect searched Invoice Number is " + invoiceNum);
					return false;
				}
			}
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbInvoiceNum.equalsIgnoreCase(searchPage.getInvoiceNumber());
	}

	@Step
	public List<String> fetchColumnNamesFromDatabaseResult() throws SQLException {
		List<String> dbColumnNames = new ArrayList<>();
		resultSetMetaData = DatabaseConn.resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			dbColumnNames.add(resultSetMetaData.getColumnLabel(i));
		}
		return dbColumnNames;
	}

	@Step
	public boolean verifyOnlyLastName(String lastName) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String patientName : searchPage.getListOfSearchedNames()) {
				String[] patientLastName = patientName.split(",", 0);
				if (!patientLastName[0].toLowerCase().startsWith(lastName.toLowerCase())) {
					return false;
				}
			}
			return true;
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& lastName.equalsIgnoreCase(searchPage.getPatientLastName());
	}

	@Step
	public boolean verifyOnlyFirstName(String firstName) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String patientName : searchPage.getListOfSearchedNames()) {
				String[] patientFirstName = patientName.split(",", 0);
				if (!patientFirstName[1].trim().toLowerCase().startsWith(firstName.toLowerCase())) {
					return false;
				}
			}
			return true;
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& firstName.equalsIgnoreCase(searchPage.getPatientFirstName());
	}

	@Step
	public boolean verifyNameOnUIWithDatabaseResult(List<String> dblistOfNames) {
		financialInfoSteps.log("List of names from UI:\n" + searchPage.getListOfSearchedNames());
		if (searchPage.isSearchAccTableVisible()) {
			if (!dblistOfNames.containsAll(new ArrayList<>(new HashSet<>(searchPage.getListOfSearchedNames()))))
				return false;
			searchPage.clickSearchInvoiceID();
		}
		String[] patientName = searchPage.getPatientName().split(",", 0);
		String lastName = patientName[0].substring(0, 1) + patientName[0].substring(1).toLowerCase();
		String firstName = patientName[1].substring(1, 2) + patientName[1].substring(2).toLowerCase();

		return searchPage.isPatientAndVisitHeaderVisible() && dblistOfNames.contains(lastName + ", " + firstName);
	}

	@Step
	public boolean verifyPatientFirstAndLastName(String lastName, String firstName) {
		return searchPage.getPatientLastName().toLowerCase().startsWith(lastName.toLowerCase())
				&& searchPage.getPatientFirstName().toLowerCase().startsWith(firstName.toLowerCase());
	}

	@Step
	public boolean verifySearchedNamesWithDatabase(String lastName, String firstName) throws SQLException {
		List<String> listOfDBLastName = new ArrayList<>();
		List<String> listOfDBFirstName = new ArrayList<>();
		boolean fValue = false;
		boolean lValue = false;

		while (DatabaseConn.resultSet.next()) {
			listOfDBLastName.add(DatabaseConn.resultSet.getString("lastname"));
			listOfDBFirstName.add(DatabaseConn.resultSet.getString("firstname"));
		}

		for (String dbLastName : listOfDBLastName) {
			if (!dbLastName.startsWith(lastName)) {
				lValue = false;
				break;
			}
			lValue = true;
		}

		for (String dbFirstName : listOfDBFirstName) {
			if (!dbFirstName.startsWith(firstName)) {
				fValue = false;
				break;
			}
			fValue = true;
		}

		return fValue && lValue;
	}

	@Step
	public boolean verifySearchedSSNWithDatabase(String sSN) throws SQLException {
		List<String> listOfDBSSN = new ArrayList<>();
		boolean sValue = false;

		while (DatabaseConn.resultSet.next()) {
			listOfDBSSN.add(DatabaseConn.resultSet.getString("SSN"));
		}

		for (String dbSSN : listOfDBSSN) {
			if (!dbSSN.contains(sSN)) {
				sValue = false;
				break;
			}
			sValue = true;
		}
		return sValue;
	}

	@Step
	public boolean verifyEncounterId(String dbEncounterID) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String encounterID : searchPage.getlistOfAccNum()) {
				if (!encounterID.contains(dbEncounterID)) {
					financialInfoSteps.log("The incorrect searched Encounter id is " + encounterID);
					return false;
				}
			}
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbEncounterID.equalsIgnoreCase(searchPage.getAccountNumber());
	}

	@Step
	public boolean verifyEncounterIDOnUIWithDatabaseResult(List<String> dblistOfEncounterID) {
		financialInfoSteps.log("List of Encounter ID from UI:\n" + searchPage.getlistOfAccNum());
		if (searchPage.isSearchAccTableVisible()) {
			return (new ArrayList<>(new HashSet<>(searchPage.getlistOfAccNum())).containsAll(dblistOfEncounterID));
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dblistOfEncounterID.contains(searchPage.getAccountNumber());
	}

	@Step
	public boolean verifyInvoiceNumberOnUIWithDatabaseResult(List<String> dblistOfInvoiceNumber) {
		financialInfoSteps.log("List of Invoice Number from UI:\n" + searchPage.getlistOfInvNum());
		if (searchPage.isSearchAccTableVisible()) {
			return dblistOfInvoiceNumber.containsAll(new ArrayList<>(new HashSet<>(searchPage.getlistOfInvNum())));
		}
		return searchPage.isPatientAndVisitHeaderVisible() && dblistOfInvoiceNumber.contains(searchPage.getInvoiceID());
	}

	@Step
	public boolean verifyMRNOnUIWithDatabaseResult(List<String> dblistOfMRN) {
		financialInfoSteps.log("List of MRN from UI:\n" + searchPage.getlistOfMRN());
		if (searchPage.isSearchAccTableVisible()) {
			return dblistOfMRN.containsAll(new ArrayList<>(new HashSet<>(searchPage.getlistOfMRN())));
		}
		return searchPage.isPatientAndVisitHeaderVisible() && dblistOfMRN.contains(searchPage.getMRNText());
	}
}
