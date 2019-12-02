package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import r1.prcmbe.serenity.steps.DefectOverrideSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class DefectOverrideStepDef {

	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	DefectOverridePage defectOverridePage;

	static String dbFileName = "DefectOverride";
	String dbSettingValue, dbInvoiceId, selectedDefectypeValue, selectedDefectSubCatValue, dbDefectAccountHistorykey;
	int dbDefectTypeId, dbDefectSubCategoryId;
	List<String> listOfSOPActionsOnTriage = new ArrayList<>();
	List<String> listOfSOPActionsOnAction = new ArrayList<>();

	Boolean isRecordPresent;

	@Steps
	SearchPageSteps searchPageSteps;

	@Steps
	DefectOverrideSteps defectOverrideStep;

	@Given("^user is able to login to sql server and connect to database$|^user login to SQL Server and connect to facility database$")
	public void user_is_able_to_login_to_sql_server_and_connect_to_database() throws IOException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@Then("^user should be able to view some invoice id$")
	public void user_should_be_able_to_view_some_invoice_id() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceId = DatabaseConn.resultSet.getString("InvoiceNumber");
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
				dbSettingValue = DatabaseConn.resultSet.getString("SettingValue");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceId is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		Assert.assertTrue(dbSettingValue.equals(flagValue));
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
		searchPage.enterInvoiceNumber(dbInvoiceId);
	}

	@When("^user clicks on submit button$")
	public void user_clicks_on_submit_button() {
		searchPage.clickSubmitBtn();
	}

	@Then("^user should be able to view the searched account$")
	public void user_should_be_able_to_view_the_searched_account() {
		Assert.assertTrue("failed to view searched account",
				searchPageSteps.verifyInvoiceIDWithLikeOperator(dbInvoiceId));
	}

	@When("^user moves the control on right side of the page and see the Defect Workflow section$")
	public void user_moves_the_control_on_right_side_of_the_page_and_see_the_Defect_Workflow_section() {
		defectOverridePage.moveToDefectWorkflowSec();
	}

	@Then("^user should be able to view the Defect workflow section$")
	public void user_should_be_able_to_view_the_Defect_workflow_section() {
		Assert.assertTrue("Defect workflow section is not present", defectOverridePage.isDefectWorkFlowSecVisible());
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
				defectOverridePage.verifyDefaultSelectedStepOnProgressBar(stepName));
	}

	@Then("^user should be able to view the assigned defect sub category below progress bar$")
	public void user_should_be_able_to_view_the_assigned_defect_sub_category_below_progress_bar() {
		Assert.assertTrue("failed to view assigned defect", defectOverridePage.isAssignedSubCategryVisible());
	}

	@When("^user selects No radio button to Override Subcategory$")
	public void user_selects_No_radio_button_to_Override_Subcategory() {
		defectOverridePage.selectNoRadioBtnOnOverrideSubCat();
	}

	@When("^user selects any value from DefectType dropdown and other Than \"([^\"]*)\" value$")
	public void user_selects_any_value_from_DefectType_dropdown(String defaultDrpdwnValue) {
		selectedDefectypeValue = defectOverridePage.selectAndGetTextDefectType(defaultDrpdwnValue);
	}

	@When("^user selects any value from Defectsubcategory dropdown and other Than \"([^\"]*)\" value$")
	public void user_selects_any_value_from_Defectsubcategory_dropdown(String defaultDrpdwnValue) {
		selectedDefectSubCatValue = defectOverridePage.selectAndGetTextDefectSubCategory(defaultDrpdwnValue);
	}

	@When("^user clicks on Save button$")
	public void user_clicks_on_Save_button() {
		defectOverridePage.clickOnSaveBtn();
	}

	@When("^user refreshes a page$")
	public void user_refreshes_a_page() {
		defectOverridePage.refreshesAPage();
	}

	@Then("^user should be able to view changed sub category below current defect under Defect workflow section$")
	public void user_should_be_able_to_view_changed_sub_category_below_current_defect_under_Defect_workflow_section() {
		Assert.assertTrue("Failed to view changed sub category in below current defect",
				defectOverridePage.getDefectSubCategoryUnderCurrentDefect().equals(selectedDefectSubCatValue));
	}

	@Then("^user runs the with DefectType query (.*)$")
	public void user_runs_the_with_DefectType_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), selectedDefectypeValue));
	}

	@Then("^user runs with DefectTypeID the query (.*)$")
	public void user_runs_with_DefectTypeID_the_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbDefectTypeId));
	}

	@Then("^user should be able to view all active defect Type$")
	public void user_should_be_able_to_view_all_active_defect_Type() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbDefectTypeId = DatabaseConn.resultSet.getInt("DefectTypeID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("DefectTypeID is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view All Defect Subcategory for that Selected Defect Type$")
	public void user_should_be_able_to_view_All_Defect_Subcategory_for_that_Selected_Defect_Type() throws SQLException {
		Assert.assertTrue("failed to view All selected defect type",
				defectOverrideStep.verifySelectedDefectSubCategoryWithDB(selectedDefectSubCatValue));
	}

	@When("^user moves to Defect Classification Section$")
	public void user_moves_to_Defect_Classification_Section() {
		defectOverridePage.moveToDefectClassificationSec();
	}

	@Then("^user should be able to view the updated defect category in Defect Classification section$")
	public void user_should_be_able_to_view_the_updated_defect_category_in_Defect_Classification_section() {
		Assert.assertTrue("Failed to update defect category in defect classification",
				defectOverridePage.getDefectCategoryUnderDefectClassSec().contains(selectedDefectSubCatValue));
	}

	@Then("^user fetches defect subCategory from data base$")
	public void user_fetches_defect_subCategory_from_data_base() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbDefectSubCategoryId = DatabaseConn.resultSet.getInt("DefectSubCategoryID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("DefectSubCategoryID is not fetched from DB.\nThe Technical Error is:\n" + exception,
					false);
		}
	}

	@When("^user clicks on Next button$")
	public void user_clicks_on_Next_button() {
		defectOverridePage.clickOnNextButton();
	}

	@Then("^user should be able to view \"([^\"]*)\" section open and placeholder Triage$")
	public void user_should_be_able_to_view_section_open_and_placeholder_Triage(String expectedSectionValue) {
		Assert.assertTrue("failed to navigate verify all steps taken tab",
				defectOverridePage.getTriageSectionHeaderText().contains(expectedSectionValue));
	}

	@Then("^user should be able to view below fields$")
	public void user_should_be_able_to_view_below_fields(DataTable buttonList) {
		List<String> listOfButtonValues = buttonList.asList(String.class);
		Assert.assertTrue("failed to verify buttons on Defect workflow",
				defectOverridePage.getButtonValuesOnSection().equals(listOfButtonValues));
	}

	@When("^user clicks on Previous button$")
	public void user_clicks_on_Previous_button() {
		defectOverridePage.clickOnPrevBtn();
	}

	@Then("^user should be able to view navigation to Override Defect Category section$")
	public void user_should_be_able_to_view_navigation_to_Override_Defect_Category_section() {
		Assert.assertTrue("failed to navigate override defect category section",
				defectOverridePage.checkOverrideDefectCatSec());
	}

	@Then("^user should be able to view the \"([^\"]*)\" section$")
	public void user_should_be_able_to_view_the_section(String expectedSectionValue) {
		Assert.assertTrue("failed to navigate taken Section",
				defectOverridePage.getTriageSectionHeaderText().contains(expectedSectionValue));
	}

	@When("^user runs the to fetch SOP actions of  isrequired is zero query (.*)$")
	public void user_runs_the_to_fetch_SOP_actions_of_isrequired_is_zero_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbDefectSubCategoryId));
	}

	@Then("^user should be able to view SOP actions  having IsRequired=0$")
	public void user_should_be_able_to_view_SOP_actions_having_IsRequired() {
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfSOPActionsOnTriage.add(DatabaseConn.resultSet.getString("ActionName"));

			}
		} catch (SQLException exception) {
			Assert.assertTrue("ActionName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view Required Sops actions in verify All steps taken$")
	public void user_should_be_able_to_view_Required_Sops_actions_in_verify_All_steps_taken() {
		Assert.assertTrue("Failed to view Sop Actions On verify All Step taken",
				listOfSOPActionsOnTriage.equals(defectOverridePage.getSOPActionsOnTriagePage()));
	}

	@Then("^user should be able to view optional \"([^\"]*)\"$")
	public void user_should_be_able_to_view_optional(String expectedSectionValue) {
		Assert.assertTrue("failed to navigate taken Section",
				defectOverridePage.getActionSectionHeaderText().contains(expectedSectionValue));
	}

	@When("^user runs the to fetch SOP actions of isrequired is one  (.*)$")
	public void user_runs_the_to_fetch_SOP_actions_of_isrequired_is_one(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbDefectSubCategoryId));
	}

	@Then("^user should be able to view  SOP actions having Isrequired=1$")
	public void user_should_be_able_to_view_SOP_actions_having_Isrequired() {
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfSOPActionsOnAction.add(DatabaseConn.resultSet.getString("ActionName"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("ActionName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view Required Sops actions in Step taken section$")
	public void user_should_be_able_to_view_Required_Sops_actions_in_Step_taken_section() {
		Assert.assertTrue("Failed to view required Sop Actions On Step taken",
				listOfSOPActionsOnAction.equals(defectOverridePage.getSOPActionsOnActionPage()));
	}

	@Then("^user runs with DefectTypID and DefectSubCategoryDesc the query (.*)$")
	public void user_runs_with_DefectTypID_and_DefectSubCategoryDesc_the_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String
				.format(commonMethods.loadQuery(queryName, dbFileName), dbDefectTypeId, selectedDefectSubCatValue));
	}

	@Then("^user clicks on Next button on TriagePage$")
	public void user_clicks_on_Next_button_on_TriagePage() {
		defectOverridePage.clickOnNextButtonOnTriagePage();
	}

	@When("^user run the query to fetch invoice Id \"([^\"]*)\"$")
	public void user_run_the_query_to_fetch_invoice_Id(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceId = DatabaseConn.resultSet.getString("invoiceid");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("Data is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user run the query to fetch defect account history \"([^\"]*)\"$")
	public void user_run_the_query_to_fetch_defect_account_history(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbInvoiceId));
	}

	@Then("^user should be able to view the record get inserted to Defect History Table after overriding$")
	public void user_should_be_able_to_view_the_record_get_inserted_to_Defect_History_Table_after_overriding()
			throws SQLException {
		isRecordPresent = false;
		while (DatabaseConn.resultSet.next()) {
			isRecordPresent = true;
			dbDefectAccountHistorykey = DatabaseConn.resultSet.getString("DefectAccountHistorykey");
		}
		Assert.assertTrue("failed to get inserted to Defect History Table after overriding", isRecordPresent);
	}

	@When("^user run the query \"([^\"]*)\" to check new record$")
	public void user_run_the_query_to_check_new_record(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbInvoiceId));
	}

	@Then("^user should be able to view new record get inserted with appropriate data$")
	public void user_should_be_able_to_view_new_record_get_inserted_with_appropriate_data() throws SQLException {
		isRecordPresent = false;
		while (DatabaseConn.resultSet.next()) {
			isRecordPresent = true;
		}
		Assert.assertTrue("failed to check new record", isRecordPresent);
	}

	@When("^user run the query \"([^\"]*)\" to check Defect Account Attribute Table$")
	public void user_run_the_query_to_check_Defect_Account_Attribute_Table(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbDefectAccountHistorykey));
	}

	@Then("^user should be able to view override entry in Defect Account Attribute Table$")
	public void user_should_be_able_to_view_override_entry_in_Defect_Account_Attribute_Table() throws SQLException {
		isRecordPresent = false;
		while (DatabaseConn.resultSet.next()) { 
			isRecordPresent = true;
		}
		Assert.assertTrue("failed to override entry in Defect Account Attribute Table", isRecordPresent);
	}
}