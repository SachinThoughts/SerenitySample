package r1.prcmbe.steps.definitions;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.PatientInformationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.PatientInformationSteps;

public class PatientInformationStepDef extends PageObject {
	PatientInformationPage patientInformationPage;
	CommonMethods commonMethods;
	SearchPage searchPage;

	@Steps
	FinancialInfoSteps financialInfoSteps;
	@Steps
	PatientInformationSteps patientInformationSteps;

	List<String> listOfHeadersOnUI = new ArrayList<>();
	List<String> listOfDataHeadersInDB = new ArrayList<>();
	String dbQueryFilename = "PatientInformation", dbInvoiceNumber;

	@Then("^user should be able to view the Patient and Facility Info section on R1D Page$")
	public void user_should_be_able_to_view_the_Patient_and_Facility_Info_section_on_R1D_Page() {
		Assert.assertTrue("User is not able to view Patient and Facility Info section in R1D",
				patientInformationPage.isPatientAndFacilityInfoSectionVisible());
	}

	@Then("^user should be able to view the following tabs$")
	public void user_should_be_able_to_view_the_folllowing_tabs_Patient_Visit_Details(DataTable expectedTabHeaders) {
		List<String> expectedListOfGridColumns = expectedTabHeaders.asList(String.class);
		listOfHeadersOnUI = patientInformationPage.getlistOfPatientAndFacilityInfoTab();
		Assert.assertTrue("User is not able to view following Test case" + expectedTabHeaders,
				listOfHeadersOnUI.containsAll(expectedListOfGridColumns));
	}

	@Then("^user should be able to view Patient & Visit Details tab as selected by default$")
	public void user_should_be_able_to_view_Patient_Visit_Details_tab_as_selected_by_default() {
		Assert.assertTrue("User is not able to view Patient and Visit details tab as selected by default",
				patientInformationPage.isPatientAndVisitTabVisible());
	}

	@When("^User clicks on drilldown icon of Patient & Facility Info panel$")
	public void user_clicks_on_drilldown_icon_of_Patient_Facility_Info_panel() {
		patientInformationPage.clickDrillDownBtn();
	}

	@Then("^user should be able to view Patient Information grid as collapsed$")
	public void user_should_be_able_to_view_Patient_Information_grid_as_collapsed() {
		Assert.assertTrue("User is not able to view Patient Information grid as collapsed",
				patientInformationPage.isPatientAndVisitCollapsedSecVisible());
	}

	@When("^user run the query \"([^\"]*)\" and Fetch Invoice number to verify patient info$")
	public void user_run_the_query_and_Fetch_Invoice_number_to_verify_patient_info(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("Invoicenumber");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Invoice number is not fetched from DB.\nThe Technical Error is:\n" + sQLException,
					false);
		}
		financialInfoSteps.log("Fetched Invoice Number from Database is " + dbInvoiceNumber);
	}

	@When("^user enters the query result in Invoice Number search textbox to verify patient info$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_to_verify_patient_info() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@When("^User clicks on tab (.*) in Patient & Facility Info panel$")
	public void user_clicks_on_Patient_Address_tab_in_Patient_Facility_Info_panel(String tabName) {
		if (tabName.trim().equals("Patient Address tab")) {
			patientInformationPage.clickOnPatientAddressTab();
		}
		else if (tabName.trim().equals("Facility Details tab")) {
			patientInformationPage.clickOnFacilityDetailsTab();
		}
		else if (tabName.trim().equals("Patient & Visit Details tab")) {
			patientInformationPage.clickOnPatientAndVisitDetailsTab();
		}
		else {
			Assert.assertTrue("No Tab is visible on page",false);
		}
	}

	@Then("^user should be able to view the following header fields in all four tabs$")
	public void user_should_be_able_to_view_the_following_header_fields_in_all_four_tabs(DataTable expectedHeaders) {
		List<String> expectedListOfHeaders = expectedHeaders.asList(String.class);
		listOfHeadersOnUI = patientInformationPage.getlistOfPatientDataHeaders();
		Assert.assertTrue("Expected headers are not visdible on Page",
				expectedListOfHeaders.containsAll(listOfHeadersOnUI));
	}

	@Then("^user should be able to view same data in header fields as in SQL result$")
	public void user_should_be_able_to_view_same_data_in_header_fields_as_in_SQL_result() {
		Assert.assertTrue("User is not able to view data header fields in sql result",
				listOfHeadersOnUI.containsAll(listOfDataHeadersInDB));
	}

	@When("^User clicks on Facility Details tab in Patient & Facility Info panel$")
	public void user_clicks_on_Facility_Details_tab_in_Patient_Facility_Info_panel() {
		patientInformationPage.clickOnPatientAndFacilityInfoPanel();
	}

	@When("^user runs the patient info query \"([^\"]*)\" and fetch the headers$")
	public void user_runs_the_patient_info_query_and_fetch_the_headers(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbInvoiceNumber));
		try {
			while (DatabaseConn.resultSet.next()) {
				ResultSetMetaData md = DatabaseConn.resultSet.getMetaData();
				int col = md.getColumnCount();
				for (int i = 1; i <= col; i++) {
					listOfDataHeadersInDB.add(md.getColumnName(i));
				}
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Table Headers doesn't fetch from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		financialInfoSteps.log("Fetched Table Headers from Database is " + listOfDataHeadersInDB);
	}

	@When("^User clicks on Facility Details tab$")
	public void user_clicks_on_Facility_Details_tab() {
		patientInformationPage.clickOnFacilityDetailsTab();
	}

	@Then("^User should be able to view the following feilds on Facility Details tab$")
	public void user_should_be_able_to_view_the_following_feilds_on_Facility_Details_tab(DataTable expectedHeaders) {
		List<String> expectedListOfHeaders = expectedHeaders.asList(String.class);
		listOfHeadersOnUI = patientInformationPage.getlistOfFacilityInfoSectionLables();
		Assert.assertTrue("Expected headers are not visible on Page",
				listOfHeadersOnUI.containsAll(expectedListOfHeaders));
	}

	@Then("^User should be able to view the same data in columns as in SQL result $")
	public void user_should_be_able_to_view_the_same_data_in_columns_as_in_SQL_result() {
		Assert.assertTrue("User is not able to view data header fields in sql result",
				listOfHeadersOnUI.containsAll(listOfDataHeadersInDB));
	}

	@When("^user runs the patient info query to fetch facility data \"([^\"]*)\" query$")
	public void user_runs_the_patient_info_query_to_fetch_facility_data_query(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbInvoiceNumber));
		List<Object> listOfVal = patientInformationSteps.verifyFacilityDetailsWithDb();
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue(
				"Following facility details values does not match\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}
}
