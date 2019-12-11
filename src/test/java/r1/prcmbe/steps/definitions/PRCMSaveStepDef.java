package r1.prcmbe.steps.definitions;

import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefectWorkflowPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class PRCMSaveStepDef {

	DefectWorkflowPage defectWorkflowPage;
	CommonMethods commonMethods;
	SearchPage searchPage;

	private String tabColorOnDefectWrkflwSection = "rgba(61, 100, 168, 1)";

	@Steps
	SearchPageSteps searchPageSteps;
	@Steps
	FinancialInfoSteps financialInfoStep;

	String dbInvoiceNumber;
	private static String dbQueryFilename = "AccountActionHistory";

	@When("^user clicks on Next button under Defect workflow section$")
	public void user_clicks_on_Next_button_under_Defect_workflow_section() {
		defectWorkflowPage.clickOnNextButton();
	}

	@When("^user clicks on Next button after selecting any step$")
	public void user_clicks_on_Next_button_after_selecting_any_step() {
		defectWorkflowPage.clickSOPActionOnTriagePage();
		defectWorkflowPage.clickOnNextButtonOnTriagePage();
	}

	@Then("^user should be able to view \"([^\"]*)\" section$")
	public void user_should_be_able_to_view_Step_s_taken_section(String expectedSectionValue) {
		Assert.assertTrue("failed to navigate taken Section",
				defectWorkflowPage.getActionSectionHeaderText().contains(expectedSectionValue));
	}

	@Then("^user should be able to view the bar move to Action$")
	public void user_should_be_able_to_view_the_bar_move_to_Action() {
		Assert.assertTrue("User is not able to view the bar moved to Action",
				defectWorkflowPage.getActionTabColourVal().trim().equals(tabColorOnDefectWrkflwSection));
	}

	@When("^user selects checkbox from Verify All Steps Taken checkboxes$")
	public void user_selects_checkbox_from_Verify_All_Steps_Taken_checkboxes() {
		defectWorkflowPage.clickSOPActionOnTriagePage();
	}

	@When("^user selects checkbox from Steps Taken checkboxes$")
	public void user_selects_checkbox_from_Steps_Taken_checkboxes() {
		defectWorkflowPage.clickSOPActionOnActionPage();
	}

	@Then("^user should be able to view the message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_message(String expectedMsg) {
		String actualSuccessMsg = defectWorkflowPage.getSuccessMessage();
		Assert.assertTrue("User is not able to view the expected message . Actual Message :" + actualSuccessMsg,
				actualSuccessMsg.equals(expectedMsg));
	}

	@When("^user clicks on A2D Save button$")
	public void user_clicks_on_A2D_Save_button() {
		defectWorkflowPage.clickOnA2DSaveButton();
	}

	@When("^user runs the \"([^\"]*)\" query to fetch Invoice Number$")
	public void user_runs_the_query_to_fetch_Invoice_Number(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("invoiceNumber");
			}
			financialInfoStep.log("invoiceNumber in DB" + dbInvoiceNumber);
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters Invoice Number fetched from database in invoice number textbox$")
	public void user_enters_Invoice_Number_fetched_from_database_in_invoice_number_textbox() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@Then("^user is able to view the Account Page$|^user is on Account Page$")
	public void user_is_able_to_view_the_Account_Page() {
		Assert.assertTrue("User is not navigated to the R1D account page for Searched Invoice Number",
				searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber));
	}
}