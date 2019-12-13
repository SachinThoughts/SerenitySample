package r1.prcmbe.steps.definitions;

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

public class PatientInformationStepDef extends PageObject {
	PatientInformationPage patientInformationPage;
	CommonMethods commonMethods;
	SearchPage searchPage;
	
	@Steps
	FinancialInfoSteps financialInfoSteps;

	List<String> listOfHeadersOnUI = new ArrayList<>();
	String dbQueryFilename = "PatientInformation", dbInvoiceNumber;

	@Then("^user should be able to view the Patient and Facility Info section on R1D Page$")
	public void user_should_be_able_to_view_the_Patient_and_Facility_Info_section_on_R1D_Page() {
		Assert.assertTrue("User is not able to view Patient and Facility Info section in R1D",
				patientInformationPage.isPatientAndFacilityInfoSectionVisible());
	}

	@Then("^user should be able to view the following tabs$")
	public void user_should_be_able_to_view_the_folllowing_tabs_Patient_Visit_Details(DataTable expectedTabHeaders) {
		List<String> expectedListOfGridColumns = expectedTabHeaders.asList(String.class);
		listOfHeadersOnUI = patientInformationPage.getlistOfPatientInfoHeaders();
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
}
