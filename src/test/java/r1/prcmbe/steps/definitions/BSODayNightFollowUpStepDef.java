package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountActionHistoryPage;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.WorkflowConfigurationPage;
import r1.prcmbe.serenity.steps.BSODayNightHandoffSteps;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;

public class BSODayNightFollowUpStepDef extends PageObject {

	AccountInformationPage accInfoPage;
	CommonMethods commonMethods;
	WorkflowConfigurationPage workflowConfigPage;
	NavigationPage navigationPage;
	AccountActionHistoryPage acntActionHistoryPage;

	@Steps
	BSODayNightHandoffSteps bSODayNightHandoffSteps;
	@Steps
	FinancialInfoSteps financialInfoSteps;

	String expectedCreateDrpdwnValue, expectedWhyDrpdownValue, expectedHandoffType, junkTestData = "Testing",
			actualValidationMessage, actualDispositionName, dbHandoffedInvoiceId;
	private static String dbQueryFilename = "BSODayNightFollowUp";

	@When("^user executes the query to fetch InvoiceNumber (.*)$")
	public void user_executes_the_query_to_fetch_InvoiceNumber(String query) {
		try {
			String finalQuery = commonMethods.loadQuery(query, dbQueryFilename);
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			Assert.assertTrue("unable to execute query" + e, false);
		}
	}

	@When("^user clicks on handOff button$")
	public void user_clicks_on_handOff_button() {
		accInfoPage.clickHandOffBtn();
	}

	@Then("^user should be able to view Hand-off pop-up window$")
	public void user_should_be_able_to_view_Hand_off_pop_up_window() {
		Assert.assertTrue("HandOff Popup is not visible", accInfoPage.isHandOffPopUpVisible());
	}

	@When("^user selects \"([^\"]*)\" from hand off type dropdown$")
	public void user_selects_from_hand_off_type_dropdown(String value) {
		accInfoPage.selectHandOffType(value);
	}

	@Then("^user should be able to select Hand Off Type as \"([^\"]*)\"$")
	public void user_should_be_able_to_select_Hand_Off_Type_as(String handOffType) {
		Assert.assertTrue("Expected HandOff type is not selected",
				accInfoPage.getSelectedHandOffTypeValue().equals(handOffType));
	}

	@Given("^user is on setting page$")
	public void user_is_on_setting_page() {
		Assert.assertTrue(getDriver().getTitle().contains("Settings"));
	}

	@When("^user selects \"([^\"]*)\" radio button and clicks on continue button$")
	public void user_selects_radio_button_and_clicks_on_continue_button(String handoffName) {
		workflowConfigPage.clickOnHandoffTypeRadioBtn(handoffName);
		workflowConfigPage.clickOnContinueBtnOnHandoffTab();
	}

	@When("^user selects \"([^\"]*)\" radio button and clicks on continue button to move Action Type tab$")
	public void user_selects_radio_button_and_clicks_on_continue_button_to_move_Action_Type_tab(String recipientName) {
		workflowConfigPage.clickOnRecipientRadioBtn(recipientName);
		workflowConfigPage.clickOnContinueBtnOnRecipientTab();
	}

	@Then("^user should be able to view name as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_name_as(String actionName) {
		Assert.assertTrue(
				"User is able to view different Name as  " + workflowConfigPage.getActionNameOnActionTypeTab(),
				actionName.equals(workflowConfigPage.getActionNameOnActionTypeTab()));
	}

	@Then("^user should be able to view follow-up date as (\\d+) and Time limit as (\\d+)$")
	public void user_should_be_able_to_view_follow_up_date_as_and_Time_limit_as(int followUpDate, int timeLimit) {
		Assert.assertTrue(
				"User is able to see different value for FollowUp Date :"
						+ workflowConfigPage.getFollowUpDayOnActionTypeTab(),
				followUpDate == Integer.parseInt(workflowConfigPage.getFollowUpDayOnActionTypeTab()));
		Assert.assertTrue(
				"User is able to see different value for Time Limit :"
						+ workflowConfigPage.getTimeLimitOnActionTypeTab(),
				timeLimit == Integer.parseInt(workflowConfigPage.getTimeLimitOnActionTypeTab()));
		workflowConfigPage.clickContinueBtnOnActionTypeTab();
		actualDispositionName = workflowConfigPage.getFirstDispositionName();
	}

	@When("^user clicks on Hand-off link$")
	public void user_clicks_on_Hand_off_link() {
		accInfoPage.clickHandOffBtn();
	}

	@When("^user select \"([^\"]*)\" as Handoff type from Handoff type dropdown$")
	public void user_select_as_Handoff_type_from_Handoff_type_dropdown(String handoffType) {
		expectedHandoffType = handoffType;
		accInfoPage.selectHandOffType(handoffType);
	}

	@Then("^user should be able to view$")
	public void user_should_be_able_to_view(DataTable dataTable) {
		List<Object> listOfVal = bSODayNightHandoffSteps.verifyMultipleAttributeOnHandoffPopup(dataTable);
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Following attribute not available\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}

	@Then("^user should be able to view note as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_note_as(String textArea) {
		Assert.assertTrue("User is not able view note as TextArea",
				accInfoPage.getTagNameForNotesTxtBox().equalsIgnoreCase(textArea));
	}

	@When("^user clicks on Create Dropdown menu$")
	public void user_clicks_on_Create_Dropdown_menu() {
		accInfoPage.clickOnCreateDrpdwn();
	}

	@Then("^user should be able to view below dropdown value$")
	public void user_should_be_able_to_view_below_dropdown_value(DataTable dataTableVal) {
		List<String> expectedCreateDrpdwnVal = dataTableVal.asList(String.class);
		Assert.assertTrue("User is not to view DropDown Values for Create Field",
				accInfoPage.getListOfCreateDrpdwnVal().equals(expectedCreateDrpdwnVal));
	}

	@Then("^\"([^\"]*)\" value should be display by default$")
	public void value_should_be_display_by_default(String defaultValue) {
		String actualDefaultValue = accInfoPage.getDefaultValueForCreateDrpdwn();
		Assert.assertTrue("Default Value is different :  " + actualDefaultValue,
				defaultValue.equals(actualDefaultValue));
	}

	@When("^user clicks \"([^\"]*)\" in Create dropdown$")
	public void user_clicks_in_Create_dropdown(String createValue) {
		expectedCreateDrpdwnValue = createValue;
		accInfoPage.selectFromCreateDrpdwn(createValue);
	}

	@Then("^user should be able to select \"([^\"]*)\" in Create Dropdown$")
	public void user_should_be_able_to_select_in_Create_Dropdown(String expectedcreateValue) {
		String actualSelectedValue = accInfoPage.getSelectedValueForCreateDrpdwn();
		Assert.assertTrue(
				"User is able to select different value in Create Drpdown :Selected Value " + actualSelectedValue,
				expectedcreateValue.equals(actualSelectedValue));
	}

	@When("^user clicks any value other then Directions in create dropdown$")
	public void user_clicks_any_value_other_then_Directions_in_create_dropdown() {
		accInfoPage.selectFromCreateDrpdwn(expectedCreateDrpdwnValue);
	}

	@Then("^user should be able to view Why dropdown$")
	public void user_should_be_able_to_view_Why_dropdown() {
		Assert.assertTrue("User is not able to view Why Dropdown", accInfoPage.isWhyDrpdwnVisible());
	}

	@When("^user clicks on Why Dropdown menu$")
	public void user_clicks_on_Why_Dropdown_menu() {
		accInfoPage.clickOnWhyDrpdown();
	}

	@Then("^user should be able to view \"([^\"]*)\"$")
	public void user_should_be_able_to_view(String whyValue) {
		expectedWhyDrpdownValue = whyValue;
		Assert.assertTrue("User is not able to view " + whyValue,
				accInfoPage.getListOfWhyDrpdwnVal().contains(whyValue));
	}

	@When("^user selects any other value then action in why dropdown$")
	public void user_selects_any_other_value_then_action_in_why_dropdown() {
		accInfoPage.selectFromWhyDrpdwn(expectedWhyDrpdownValue);
	}

	@Then("^user should be able to view Disposition Dropdown$")
	public void user_should_be_able_to_view_Disposition_Dropdown() {
		Assert.assertTrue("User is not able to view Disposition Dropdown", accInfoPage.isDispositionDrpdwnVisible());
	}

	@Then("^user should be able to provided the disposition is active for that action in Workflow Configuration screen$")
	public void user_should_be_able_to_provided_the_disposition_is_active_for_that_action_in_Workflow_Configuration_screen() {
		Assert.assertTrue("User is not able to provide the disposition is active for that action",
				accInfoPage.getListOfDispositionDrpdwnVal().contains(actualDispositionName));
		accInfoPage.selectFromDispositionDrpdwn(actualDispositionName);
	}

	@When("^user enters any value in Note$")
	public void user_enters_any_value_in_Note() {
		accInfoPage.enterValueInNoteTxtField(junkTestData);
	}

	@When("^user clicks on Save button on HandOff$")
	public void user_clicks_on_Save_button_on_HandOff() {
		accInfoPage.clickOnSaveHandoffBtn();
		actualValidationMessage = accInfoPage.getHandoffSavedMessage();
	}

	@Then("^user should be able to view the validation message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_validation_message(String expectedMessage) {
		Assert.assertTrue(
				"User is not able to view expected validation message : Actual Message " + actualValidationMessage,
				actualValidationMessage.equals(actualValidationMessage));
	}

	@When("^user scrolls to account action history section$")
	public void user_scrolls_to_account_action_history_section() {
		accInfoPage.scrollToAccountActionHistory();
	}

	@When("^user clicks on Show Account Action History Notes$")
	public void user_clicks_on_Show_Account_Action_History_Notes() {
		accInfoPage.clickOnShowAccountActionBtn();
	}

	@Then("^user should be able to view following details with saved value $")
	public void user_should_be_able_to_view_following_details_with_saved_value(DataTable dataTable) {
		List<Object> listOfVal = bSODayNightHandoffSteps.verifyRecentlyHandoffSavedValue(dataTable, expectedHandoffType,
				expectedWhyDrpdownValue, actualDispositionName);
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Following attribute not available\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}

	@Then("^user should be able to view Added appeared as system date and Created user appeared as handoff taken user$")
	public void user_should_be_able_to_view_Added_and_appeared_as_system_date_and_Created_user_appeared_as_handoff_taken_user() {
		Assert.assertTrue("Created date does not matched with system date", acntActionHistoryPage
				.getRecentAddedAccountActionHistoryValue(3).equals(bSODayNightHandoffSteps.getCurrentDate()));
		Assert.assertTrue("Created User Appeared different From Handoff Taken User ",
				bSODayNightHandoffSteps.verifySystemUserMappedWithCreatedUser());
	}

	@When("^user clicks on Billing & Follow-up Footer link$")
	public void user_clicks_on_Billing_Follow_up_Footer_link() {
		navigationPage.clickOnBillingAndFollowUpFooterLink();
	}

	@When("^user runs the \"([^\"]*)\" query to fetch the handoffed Invoice ID$")
	public void user_runs_the_query_to_fetch_the_handoffed_Invoice_ID(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbHandoffedInvoiceId = DatabaseConn.resultSet.getString("invoiceid");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("InvoiceId are not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		financialInfoSteps.log("Handoffed invoice Id of names from DB:\n" + dbHandoffedInvoiceId);
	}

	@Then("^user should be able to view invoiceid as sql result$")
	public void user_should_be_able_to_view_invoiceid_as_sql_result() {
		Assert.assertTrue(
				"User is not able to view invoiceid as sql results . Invoice Id from Db :" + dbHandoffedInvoiceId,
				!dbHandoffedInvoiceId.isEmpty());
	}

	@When("^user runs the \"([^\"]*)\" query to fetch result Set$")
	public void user_runs_the_query_to_fetch_result_Set(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbHandoffedInvoiceId));
	}

	@Then("^user should be able to view hand off action as sql result$")
	public void user_should_be_able_to_view_hand_off_action_as_sql_result() throws SQLException {
		Assert.assertTrue("User is not able to view hand off action as sql result . " + dbHandoffedInvoiceId,
				DatabaseConn.resultSet.next());
	}
}