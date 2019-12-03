package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.prcmbe.serenity.steps.LoginSteps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.R1ConfigurationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.CallPayerQueueSteps;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;

public class CallPayerQueueStepDef {

	@Steps
	CallPayerQueueSteps callPayerQueueSteps;
	@Steps
	FinancialInfoSteps financialInfoStep;
	@Steps
	LoginSteps loginStep;

	CommonMethods commonMethods;
	CallPayerQueuePage callPayerQueuePage;
	SettingsPage settingsPage;
	R1ConfigurationPage r1ConfigPage;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;
	NavigationPage navigationPage;
	LoginPage loginPage;

	String accountNo, noOfAccountsInQueueBefore, dbInvoiceNumber, amount, tCode;
	private static String dbQueryFilename = "CallPayerQueue";
	private int callPayerQueueCount;
	private String removedInvoice, handOffType, succesMessageHandOff;

	@Given("^user is on account page having no payer : \"([^\"]*)\"$")
	public void user_is_on_account_page_having_no_payer(String expDefectClassification) {
		Assert.assertTrue("The defect Classification is not as expected",
				callPayerQueuePage.getDefectClassification().equals(expDefectClassification));
		accountNo = callPayerQueuePage.getAccountNo();
		noOfAccountsInQueueBefore = callPayerQueuePage.getCountOfAccountsInCallPayorQueue();
	}

	@When("^user clicks on Add to Call queue icon$|^user clicks on Add to queue button in Call Payer Queue Section$")
	public void user_clicks_on_Add_to_Call_queue_icon() {
		callPayerQueuePage.clickAddtoCallPayorQueueBtn();
	}

	@Then("^The popup to add call to queue should open with title as \"([^\"]*)\"$")
	public void the_popup_to_add_call_to_queue_should_open_with_title_as(String expPopupTitle) {
		Assert.assertTrue("Add Call To Queue popup should open",
				callPayerQueuePage.getCallToQueuePopupTitle().equals(expPopupTitle));
	}

	@When("^user clicks Add without note$")
	public void user_clicks_Add_without_note() {
		callPayerQueuePage.clickAddPayerQueueWithoutNoteBtn();
	}

	@Then("^user should be able to view message for Call Payor queue as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_for_Call_Payor_queue_as(String expMessage) {
		Assert.assertTrue("Expected Info Message not displayed",
				callPayerQueuePage.getInfoMessageText().equals(expMessage));
	}

	@Then("^user should not be able to view account to Call Queue$")
	public void user_should_not_be_able_to_view_account_to_Call_Queue() {
		callPayerQueuePage.closeMsgButton();
		Assert.assertTrue("The count of Accounts in the Call Payor Queue should remain unchanged",
				callPayerQueuePage.getCountOfAccountsInCallPayorQueue().equals(noOfAccountsInQueueBefore));
		callPayerQueuePage.clickToggleCallQueueBtn();
		Assert.assertFalse("User should not be able to view account in Call Payor Queue",
				callPayerQueuePage.getListOfAccountsInCallPayorQueue().contains(accountNo));
	}

	@When("^Call Payor Queue user run the query to fetch an Unclassified account \"([^\"]*)\"$")
	public void call_Payor_Queue_user_run_the_query_to_fetch_an_Unclassified_account(String query) {
		try {
			String finalQuery = commonMethods.loadQuery(query, dbQueryFilename);
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			Assert.assertTrue("unable to execute query" + e, false);
		}
	}

	@When("^user hovers IT Tools link$")
	public void user_hovers_IT_Tools_link() {
		settingsPage.hoverITToolsLink();
	}

	@When("^user clicks on R1 Configuration$")
	public void user_clicks_on_R1_Configuration() {
		settingsPage.clickR1ConfigurationLink();
	}

	@Given("^user is on R1 Configuration Setting Page$")
	public void user_is_on_R1_Configuration_Setting_Page() {
		r1ConfigPage.isR1ConfigurationTitleVisible();
	}

	@When("^user selects \"([^\"]*)\" from search dropdown$")
	public void user_selects_from_search_dropdown(String value) {
		r1ConfigPage.selectValueFromSearchDrpdwn(value);
	}

	@When("^user enters \"([^\"]*)\" in textbox$")
	public void user_enters_in_textbox(String settingName) {
		r1ConfigPage.enterValueInSearchTextbox(settingName);
	}

	@When("^user clicks on search button$")
	public void user_clicks_on_search_button() {
		r1ConfigPage.clickSearchBtn();
	}

	@When("^user clicks on edit button to update \"([^\"]*)\" Value as per configuration requirement$")
	public void user_clicks_on_edit_button_to_update_Value_as_per_configuration_requirement(String settingName) {
		r1ConfigPage.clickEditBtn();
	}

	@When("^user enters the setting value (.*) in setting value textbox$")
	public void user_enters_the_setting_value_in_setting_value_textbox(String settingValue) {
		r1ConfigPage.enterValueInSettingValueTextbox(settingValue);
	}

	@When("^user clicks on Update Appsetting button$")
	public void user_clicks_on_Update_Appsetting_button() {
		r1ConfigPage.clickUpdateAppSettingBtn();
	}

	@Then("^user should be able to view the Application setting list screen$")
	public void user_should_be_able_to_view_the_Application_setting_list_screen() {
		Assert.assertTrue("Application Settings List screen is not visible",
				r1ConfigPage.isApplicationSettingsListVisible());
	}

	@Then("^user should be able to view the setting \"([^\"]*)\" in acceretive configuration page$")
	public void user_should_be_able_to_view_the_setting_in_acceretive_configuration_page(String settingName) {
		Assert.assertTrue("Setting Name not visible in acceretive configuration page",
				settingName.equals(r1ConfigPage.getSettingNameValue()));
	}

	@Then("^user should be able to view the updated value of setting (.*)$")
	public void user_should_be_able_to_view_the_updated_value_of_setting(String settingValue) {
		Assert.assertTrue("Updated value of setting is not visible",
				settingValue.equals(r1ConfigPage.getSettingValue()));
	}

	@When("^user selects \"([^\"]*)\" option$")
	public void user_selects_option(String operator) {
		searchPage.selectOperatorValue(operator);
	}

	@When("^user clicks on Toggle Call Queue button$")
	public void user_clicks_on_Toggle_Call_Queue_button() {
		callPayerQueuePage.clickToggleCallQueueBtn();
	}

	@When("^user expands the call queue section to view a list of all the added accounts$")
	public void user_expands_the_call_queue_section_to_view_a_list_of_all_the_added_accounts() {
		callPayerQueuePage.clickCallPayerListExpandBtn();
		callPayerQueueCount = callPayerQueuePage.getCallPayerQueueCount();
	}

	@Then("^user should be able to view X button against each account in Call Queue$")
	public void user_should_be_able_to_view_X_button_against_each_account_in_Call_Queue() {
		Assert.assertTrue("Remove button not visible for each account in Call queue",
				callPayerQueuePage.isRemoveCallPayerQueueAccountBtnListVisible());
	}

	@When("^user clicks on remove X button$")
	public void user_clicks_on_remove_X_button() {
		removedInvoice = callPayerQueuePage.getRemovedInvoice();
		callPayerQueuePage.clickRemoveCallPayerQueueAccountBtn();
	}

	@Then("^user should be able to view the deleted account from Call Queue$")
	public void user_should_be_able_to_view_the_deleted_account_from_Call_Queue() {
		Assert.assertTrue("User not able to view the deleted account",
				callPayerQueueSteps.isRemovedAccountFromCallQueueVisible(removedInvoice));
	}

	@Then("^user should be able to view the count of accounts is decreased by (\\d+) in Call Queue$")
	public void user_should_be_able_to_view_the_count_of_accounts_is_decreased_by_in_Call_Queue(int difference) {
		int callPayerQueueCountFinal = callPayerQueueCount - difference;
		Assert.assertTrue("Count of accounts is not decreased by 1 in Call Queue",
				callPayerQueuePage.getCallPayerQueueCount() == callPayerQueueCountFinal);
	}

	@When("^user clicks on Add to Queue button$")
	public void user_clicks_on_Add_to_Queue_button() {
		callPayerQueuePage.clickAddtoCallPayorQueueBtn();
		callPayerQueuePage.clickAddPayerQueueWithoutNoteBtn();
	}

	@Then("^user should be able to view the account added to Call Queue$")
	public void user_should_be_able_to_view_the_account_added_to_Call_Queue() {
		Assert.assertTrue("User not able to view the account added to Call queue",
				callPayerQueuePage.isCallPayerQueueInvoiceVisible());
	}

	@When("^user selects any value from Handoff Types dropdown (.*)$")
	public void user_selects_any_value_from_Handoff_Types_dropdown(String value) {
		handOffType = value;
		accInfoPage.selectHandOffType(value);
	}

	@When("^user selects any value from Create dropdown (.*)$")
	public void user_selects_any_value_from_Create_dropdown(String value) {
		accInfoPage.selectValueFromCreateDrpdwn(value);
	}

	@When("^user selects \"([^\"]*)\" from Why dropdown$")
	public void user_selects_from_Why_dropdown(String value) {
		accInfoPage.selectValueFromWhyDrpdwn(value);
	}

	@When("^user selects \"([^\"]*)\" from Disposition dropdown$")
	public void user_selects_from_Disposition_dropdown(String value) {
		accInfoPage.selectValueFromDispositionDrpdwn(value);
	}

	@When("^user enters any value in Note Text Area \"([^\"]*)\"$")
	public void user_enters_any_value_in_Note_Text_Area(String value) {
		accInfoPage.enterValueInNotesTextbox(value);
	}

	@When("^user clicks on Save button on handoff pop up$")
	public void user_clicks_on_Save_button_on_handoff_pop_up() {
		accInfoPage.clickSaveBtn();
		succesMessageHandOff = accInfoPage.getSuccessMsg();
	}

	@Then("^user should be able to view the \"([^\"]*)\" message$")
	public void user_should_be_able_to_view_the_message(String message) {
		Assert.assertTrue("User not able to view hand off success message", message.equals(succesMessageHandOff));
	}

	@Then("^user should be able to view the saved Action in Action History$")
	public void user_should_be_able_to_view_the_saved_Action_in_Action_History() {
		Assert.assertTrue("User not able to view saved action in Action History",
				handOffType.equals(accInfoPage.accountActionHistoryHandOff()));
	}

	@Then("^user should be able to view the deleted account from the Call Queue on navigating to the Next Account$")
	public void user_should_be_able_to_view_the_deleted_account_from_the_Call_Queue_on_navigating_to_the_Next_Account() {
		Assert.assertTrue("User not able to view deleted account from the call queue",
				accInfoPage.isInvoiceNumberVisible());
	}

	@Given("^user is on account page$")
	public void user_is_on_account_page() {
		Assert.assertTrue("Searched account page is not displayed",
				accInfoPage.getInvoiceNumber().equals(dbInvoiceNumber));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch account for writeoff$")
	public void user_runs_the_query_to_fetch_account_for_writeoff(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@When("^user fetch the InvoiceNumber from database$")
	public void user_fetch_the_InvoiceNumber_from_DB() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			}
			financialInfoStep.log("invoiceNumber in DB" + dbInvoiceNumber);
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters Invoice number$")
	public void user_enter_Invoice_Number() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@When("^user clicks on Approvals link$")
	public void user_clicks_on_Approvals_link() {
		accInfoPage.clickApprovalWriteOffLink();
	}

	@When("^user selects \"([^\"]*)\" option from Category dropdown$")
	public void user_selects_option_from_Category_dropdown(String drpdwnVal) {
		callPayerQueuePage.categorySelectByText(drpdwnVal);
	}

	@When("^user selects \"([^\"]*)\" from Write Off Type dropdown$")
	public void user_selects_from_Write_Off_Type_dropdown(String drpdwnVal) {
		callPayerQueuePage.writeOffTypeSelectByText(drpdwnVal);
	}

	@When("^user selects \"([^\"]*)\" from T-Code to Use dropdown$")
	public void user_selects_from_tcode_to_Use_dropdown(String drpdwnVal) {
		tCode = drpdwnVal;
		callPayerQueuePage.tCodeSelectByText(tCode);
	}

	@When("^user enters amount \"([^\"]*)\" in Write off Amount textbox$")
	public void user_enters_amount_in_Write_Off_Amount_textbox(String amount) {
		this.amount = amount;
		callPayerQueuePage.enterWriteOffAmount(this.amount);
	}

	@When("^user enters \"([^\"]*)\" in Notes textbox$")
	public void user_enters_in_Notes_textbox(String notes) {
		callPayerQueuePage.enterWriteOffNotes(notes);
	}

	@When("^user clicks on write off Save button$")
	public void user_clicks_on_write_off_Save_button() {
		callPayerQueuePage.clickSaveWriteOffBtn();
		searchPage.waitForSpinnerToDisappear();
	}

	@Then("^user should be able to view write-off request on account$")
	public void user_should_be_able_to_view_write_off_request_on_account() {
		Assert.assertTrue("incorrect or no write off request present on the account",
				amount.equals(callPayerQueuePage.getCreatedWriteOffAmount())
						&& tCode.contains(callPayerQueuePage.getCreatedTCode()));
	}

	@When("^user scrolls down to the Write Off section$")
	public void user_scrolls_down_to_the_Write_Off_section() {
		callPayerQueuePage.closeMsgButton();
		callPayerQueuePage.moveToApprovalRequestTbl();
	}

	@When("^user clicks on radiobutton Deny$")
	public void user_clicks_on_radiobutton_Deny() {
		callPayerQueuePage.clickDenyRadioBtn();
	}

	@When("^user clicks on write off response Submit button$")
	public void user_clicks_on_write_off_response_Submit_button() {
		callPayerQueuePage.clickApprovalResponseSubmitBtn();
	}

	@When("^user clicks on review save button$")
	public void user_clicks_on_review_save_button() {
		callPayerQueuePage.clickAppReviewSaveBtn();
	}

	@Then("^user should be able to view the account to users CPQ$")
	public void user_should_be_able_to_view_the_account_to_users_CPQ() {
		callPayerQueuePage.clickToggleCallQueueBtn();
		Assert.assertTrue("Account not visible in Call Payer Queue",
				callPayerQueuePage.getInvoiceNumberCPQ().contains(dbInvoiceNumber));
	}

	@When("^user logins to the application with R1D Approval Role$")
	public void user_logins_to_the_application_with_R1D_Approval_Role() throws IOException {
		loginStep.userEntersCredentials(CommonMethods.loadProperties("R1DApproverUserName"),
				CommonMethods.loadProperties("R1DApproverPassword"));
		Hooks.propertyName = "R1DApproverUserName";
		loginPage.loginBtnClick();
		if (loginPage.isProceedLinkVisible()) {
			loginPage.clickOnProceedFurther();
		}
	}

	@When("^user logins to the application with BSO_Followup Role$")
	public void user_logins_to_the_application_with_BSO_Followup_Role() throws IOException {
		loginStep.userEntersCredentials(CommonMethods.loadProperties("BSOFollowUpUserName"),
				CommonMethods.loadProperties("BSOFollowUpPassword"));
		Hooks.propertyName = "BSOFollowUpUserName";
		loginPage.loginBtnClick();
		if (loginPage.isProceedLinkVisible()) {
			loginPage.clickOnProceedFurther();
		}
	}

	@Then("^user should be able to view the request \"([^\"]*)\" status$")
	public void user_should_be_able_to_view_the_request_Denied(String status) {
		callPayerQueuePage.closeMsgButton();
		Assert.assertTrue("rejected status not visible", status.equals(callPayerQueuePage.getReviewStatus()));
	}

	@Then("^user should be able to view the account dropped from CPQ$|^user should not be able to view the account in users CPQ$")
	public void user_should_be_able_to_view_the_account_dropped_from_CPQ() {
		callPayerQueuePage.clickToggleCallQueueBtn();
		Assert.assertFalse("Account is still visible in Call Payer Queue",
				callPayerQueuePage.isInvoiceNumberCPQVisible()
						&& callPayerQueuePage.getInvoiceNumberCPQ().contains(dbInvoiceNumber));
	}

	@When("^user clicks on radiobutton Approve$")
	public void user_clicks_on_radiobutton_Approve() {
		callPayerQueuePage.clickApproveRadioBtn();
	}
}