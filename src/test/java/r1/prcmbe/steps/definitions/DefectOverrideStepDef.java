package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.DefectOverridePage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class DefectOverrideStepDef {

	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	static String dbFileName = "DefectOverride";
	String settingValueDB, invoiceIDDB;
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	DefectOverridePage defectOverridePage;
	@Steps
	SearchPageSteps searchPageSteps;

	@Given("^user is able to login to sql server and connect to database$")
	public void user_is_able_to_login_to_sql_server_and_connect_to_database() throws IOException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@When("^user runs the to fetch invoicid (.*) query$")
	public void user_runs_the_to_fetch_invoicid_DefectOverride_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
	}

	@Then("^user should be able to view some invoice id$")
	public void user_should_be_able_to_view_some_invoice_id() {
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceIDDB = DatabaseConn.resultSet.getString("InvoiceNumber");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceId is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user runs the query (.*)$")
	public void user_runs_the_query(String queryName) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));

	}

	@Then("^user should be able to view the PRCM Flag \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_PRCM_Flag(String flagValue) {
		try {
			while (DatabaseConn.resultSet.next()) {
				settingValueDB = DatabaseConn.resultSet.getString("SettingValue");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceId is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		Assert.assertTrue(settingValueDB.equals(flagValue));
	}

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user hovers on R(\\d+)_Decision link$")
	public void user_hovers_on_R__Decision_link(int arg1) {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
	}

	@When("^user selects \"([^\"]*)\" from search by dropdown$")
	public void user_selectsr_from_search_by_dropdown(String dropdown) {
		searchPage.searchBySelectText(dropdown);
	}

	@When("^user selects \"([^\"]*)\" operator$")
	public void user_selects_operator(String operator) {
		searchPage.selectOperatorValue(operator);
	}

	@When("^user enters invoice number fetched from database in invoice number textbox$")
	public void user_enters_invoice_number_fetched_from_database_in_invoice_number_textbox() {
		searchPage.enterInvoiceNumber(invoiceIDDB);
	}

	@When("^user clicks on submit button$")
	public void user_clicks_on_submit_button() {
		searchPage.clickSubmitBtn();
	}

	@Then("^user should be able to view the searched account$")
	public void user_should_be_able_to_view_the_searched_account() {
		Assert.assertTrue(searchPageSteps.verifyInvoiceIDWithLikeOperator(invoiceIDDB));
	}

	@When("^user moves the control on right side of the page and see the Defect Workflow section$")
	public void user_moves_the_control_on_right_side_of_the_page_and_see_the_Defect_Workflow_section() {
		defectOverridePage.moveToDefectWorkflowSec();
	}

	@Then("^user should be able to view the Defect workflow section$")
	public void user_should_be_able_to_view_the_Defect_workflow_section() {
		Assert.assertTrue("Defect workflow section is not present", defectOverridePage.isDefectWorkFlowSecPresent());
	}

	@Then("^user should be able to view the progress bar with following steps$")
	public void user_should_be_able_to_view_the_progress_bar_with_following_steps(DataTable progressBarSteps) {
		List<String> listOfprogressBarValues = progressBarSteps.asList(String.class);
		Assert.assertTrue("failed to verify progress bar steps",
				defectOverridePage.getProgressBarSteps().equals(listOfprogressBarValues));
	}

	@Then("^user should be able to view the progesssbar selected as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_progesssbar_selected_as_Confirm(String stepName) {
		Assert.assertTrue("Confirm step is not selected on progress bar",
				defectOverridePage.defaultSelectedStepOnPrgsBar(stepName));
	}

	@Then("^user should be able to view the assigned defect sub category below progress bar$")
	public void user_should_be_able_to_view_the_assigned_defect_sub_category_below_progress_bar() {
		Assert.assertTrue("", defectOverridePage.isAssignedSubCategryPresent());
	}
}