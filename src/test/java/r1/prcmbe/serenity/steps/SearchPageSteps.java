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

	/**
	 * This method is used to compare the searched Invoice Number with the search
	 * results displayed using like operator. If there are multiple records in
	 * search result then this Method will check Invoice number of all the records.
	 * If single record searched then directly the Invoice number of that record is
	 * checked
	 * 
	 * @param invoiceNum
	 *            the invoice number which is searched
	 * @return the value 'True' if invoice number is matched else return 'False'
	 */
	@Step
	public boolean verifyInvoiceNumberWithLikeOperator(String invoiceNum) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceNumFromList : searchPage.getlistOfInvNum()) {
				if (!invoiceNumFromList.toLowerCase().contains(invoiceNum.toLowerCase())) {
					return false;
				}
			}
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& invoiceNum.contains(searchPage.getInvoiceNumber().toLowerCase());
	}

	/**
	 * This method is used to compare the searched Invoice Number with the search
	 * results displayed using equal operator. If there are multiple records in
	 * search result then this Method will check Invoice number of all the records.
	 * If single record searched then directly the Invoice number of that record is
	 * checked
	 * 
	 * @param invoiceNum
	 *            the invoice number which is searched
	 * @return the value 'True' if invoice number is matched else return 'False'
	 */
	@Step
	public boolean verifyInvoiceNumberWithEqualOperator(String invoiceNum) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceNumFromList : searchPage.getlistOfInvNum()) {
				if (!invoiceNumFromList.equalsIgnoreCase(invoiceNum)) {
					loginSteps.log("The incorrect searched Invoice Number is " + invoiceNumFromList);
					return false;
				}
			}
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& invoiceNum.equalsIgnoreCase(searchPage.getInvoiceNumber());
	}

	/**
	 * This method is used to get names of all the columns from the database result
	 * set.
	 * 
	 * @return list of all the column names
	 * @throws SQLException
	 */
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

	/**
	 * This method is used to compare the searched last name with the search results
	 * displayed. If there are multiple records in search result then this Method
	 * will check last name of all the records. If single record searched then
	 * directly the last name is checked. The Name displayed on UI consist of both
	 * First and Last name, hence Split method is used to get only last name
	 * 
	 * @param lastName
	 *            the Last Name which is searched
	 * @return the value 'True' if last name is matched else return 'False'
	 */
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

	/**
	 * This method is used to compare the searched first name with the search
	 * results displayed. If there are multiple records in search result then this
	 * Method will check first name of all the records. If single record searched
	 * then directly the first name is checked. The Name displayed on UI consist of
	 * both First and Last name, hence Split method is used to get only first name
	 * 
	 * @param firstName
	 *            the First Name which is searched
	 * @return the value 'True' if first name is matched else return 'False'
	 */
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

	/**
	 * This method is used to compare all the names fetched from data base with the
	 * names displayed in multiple search results on UI
	 * 
	 * @param dblistOfNames
	 *            the list of names from database result set
	 * @return the value 'True' if both First and Last name is matched else will
	 *         return 'False'
	 */
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

	/**
	 * This method is used to compare first name and last name on UI starting with
	 * the searched first and last name
	 * 
	 * @param lastName
	 *            the Last Name which is searched
	 * @param firstName
	 *            the First Name which is searched
	 * @return the value 'True' if both First and Last name is matched else will
	 *         return 'False'
	 */
	@Step
	public boolean verifyPatientFirstAndLastName(String lastName, String firstName) {
		return searchPage.getPatientLastName().toLowerCase().startsWith(lastName.toLowerCase())
				&& searchPage.getPatientFirstName().toLowerCase().startsWith(firstName.toLowerCase());
	}

	/**
	 * This method is used to compare the single character searched in first and
	 * last name with the results displayed in database
	 * 
	 * @param lastName
	 *            the Last Name which is searched
	 * @param firstName
	 *            the First Name which is searched
	 * @return the value 'True' if both First and Last name is matched else will
	 *         return 'False'
	 * @throws SQLException
	 */
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

	/**
	 * This method is used to compare the searched SSN with all the SSN from the
	 * database
	 * 
	 * @param sSN
	 *            the SSN which is searched
	 * @return the value 'True' if the searched SSN is part of all the SSN from
	 *         Database else return 'False'
	 * @throws SQLException
	 */
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

	/**
	 * This method is used to compare the searched encounter id/account number with
	 * the search results displayed. If there are multiple records in search result
	 * then this Method will check encounter id of all the records and then click on
	 * a record and again check its encounter id. If single record searched then
	 * directly the encounter id is checked
	 * 
	 * @param encounterID
	 *            EncounterID which is searched on UI
	 * @return the value 'True' if the searched encounter id is part of all the
	 *         encounter ids from Database else return 'False'
	 */
	@Step
	public boolean verifyEncounterId(String encounterID) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String eIDFromList : searchPage.getlistOfAccNum()) {
				if (!eIDFromList.contains(encounterID)) {
					financialInfoSteps.log("The incorrect searched Encounter id is " + eIDFromList);
					return false;
				}
			}
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& encounterID.equalsIgnoreCase(searchPage.getPatientAccountNo());
	}

	/**
	 * This method is used to compare the searched encounter id with the search
	 * results displayed. If there are multiple records in search result then this
	 * Method will check encounter id of all the records. If single record searched
	 * then directly the encounter id of that record is checked
	 * 
	 * @param dblistOfEncounterID
	 *            the list of encounter ids fetched from database
	 * @return the value 'True' if the searched encounter id is part of all the
	 *         encounter ids from Database else return 'False'
	 */
	@Step
	public boolean verifyEncounterIdOnUIWithDatabaseResult(List<String> dblistOfEncounterID) {
		financialInfoSteps.log("List of Encounter ID from UI:\n" + searchPage.getlistOfAccNum());
		if (searchPage.isSearchAccTableVisible()) {
			return dblistOfEncounterID.containsAll(new ArrayList<>(new HashSet<>(searchPage.getlistOfAccNum())));
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dblistOfEncounterID.contains(searchPage.getPatientAccountNo());
	}

	/**
	 * This method is used to compare the searched invoice number with the search
	 * results displayed. If there are multiple records in search result then this
	 * Method will check invoice numbers of all the records. If single record
	 * searched then directly the invoice number of that record is checked
	 * 
	 * @param dblistOfInvoiceNumber
	 *            the list of invoice numbers fetched from database
	 * @return the value 'True' if the searched invoice number is part of all the
	 *         invoice numbers from Database else return 'False'
	 */
	@Step
	public boolean verifyInvoiceNumberOnUIWithDatabaseResult(List<String> dblistOfInvoiceNumber) {
		financialInfoSteps.log("List of Invoice Number from UI:\n" + searchPage.getlistOfInvNum());
		if (searchPage.isSearchAccTableVisible()) {
			return dblistOfInvoiceNumber.containsAll(new ArrayList<>(new HashSet<>(searchPage.getlistOfInvNum())));
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dblistOfInvoiceNumber.contains(searchPage.getInvoiceNumber());
	}

	/**
	 * This method is used to compare the searched MRN with the search results
	 * displayed. If there are multiple records in search result then this method
	 * will check MRNs of all the records. If single record searched then directly
	 * the MRN of that record is checked
	 * 
	 * @param dblistOfMRN
	 *            the list of MRNs fetched from Database
	 * @return the value 'True' if the searched MRN is part of all the MRNs from
	 *         Database else return 'False'
	 */
	@Step
	public boolean verifyMRNOnUIWithDatabaseResult(List<String> dblistOfMRN) {
		financialInfoSteps.log("List of MRN from UI:\n" + searchPage.getlistOfMRN());
		if (searchPage.isSearchAccTableVisible()) {
			return dblistOfMRN.containsAll(new ArrayList<>(new HashSet<>(searchPage.getlistOfMRN())));
		}
		return searchPage.isPatientAndVisitHeaderVisible() && dblistOfMRN.contains(searchPage.getMRNText());
	}
}
