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
		return searchPage.isPatientAndVisitHeaderVisible() && dblistOfNames.contains(searchPage.getPatientName());
	}
	@Step
	public boolean verifyEncounterId(String dbEncounterID) {
		if (searchPage.isSearchAccTableVisible()) {
			System.out.println(searchPage.getlistOfAccNum());
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

}
