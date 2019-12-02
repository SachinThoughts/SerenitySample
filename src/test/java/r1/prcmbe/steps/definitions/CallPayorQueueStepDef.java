package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.CallPayorQueuePage;
import r1.prcmbe.pages.R1ConfigurationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.CallPayorQueueSteps;

public class CallPayorQueueStepDef {

	@Steps
	CallPayorQueueSteps callPayorQueueSteps;

	CommonMethods commonMethods;
	CallPayorQueuePage callPayorQueuePage;
	SettingsPage settingsPage;
	R1ConfigurationPage r1ConfigPage;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;
	String accountNo, noOfAccountsInQueueBefore;
	private static String dbQueryFilename = "CallPayorQueue";
	private int callPayerQueueCount;
	private String removedInvoice;

	@Given("^user is on account page having no payer : \"([^\"]*)\"$")
	public void user_is_on_account_page_having_no_payer(String expDefectClassification) {
		Assert.assertTrue("The defect Classification is not as expected",
				callPayorQueuePage.getDefectClassification().equals(expDefectClassification));
		accountNo = callPayorQueuePage.getAccountNo();
		noOfAccountsInQueueBefore = callPayorQueuePage.getCountOfAccountsInCallPayorQueue();
	}

	@When("^user clicks on Add to Call queue icon$")
	public void user_clicks_on_Add_to_Call_queue_icon() {
		callPayorQueuePage.clickAddtoCallPayorQueueBtn();
	}

	@Then("^The popup to add call to queue should open with title as \"([^\"]*)\"$")
	public void the_popup_to_add_call_to_queue_should_open_with_title_as(String expPopupTitle) {
		Assert.assertTrue("Add Call To Queue popup should open",
				callPayorQueuePage.getCallToQueuePopupTitle().equals(expPopupTitle));
	}

	@When("^user clicks Add without note$")
	public void user_clicks_Add_without_note() {
		callPayorQueuePage.clickAddPayerQueueWithoutNoteBtn();
	}

	@Then("^user should be able to view message for Call Payor queue as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_for_Call_Payor_queue_as(String expMessage) {
		Assert.assertTrue("Expected Info Message not displayed",
				callPayorQueuePage.getInfoMessageText().equals(expMessage));
	}

	@Then("^user should not be able to view account to Call Queue$")
	public void user_should_not_be_able_to_view_account_to_Call_Queue() {
		callPayorQueuePage.closeMsgButton();
		Assert.assertTrue("The count of Accounts in the Call Payor Queue should remain unchanged",
				callPayorQueuePage.getCountOfAccountsInCallPayorQueue().equals(noOfAccountsInQueueBefore));
		callPayorQueuePage.clickToggleCallQueueBtn();
		Assert.assertFalse("User should not be able to view account in Call Payor Queue",
				callPayorQueuePage.getListOfAccountsInCallPayorQueue().contains(accountNo));
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
		callPayorQueuePage.clickToggleCallQueueBtn();
	}

	@When("^user expands the call queue section to view a list of all the added accounts$")
	public void user_expands_the_call_queue_section_to_view_a_list_of_all_the_added_accounts() {
		callPayorQueuePage.clickCallPayerListExpandBtn();
		callPayerQueueCount = callPayorQueuePage.getCallPayerQueueCount();
	}

	@Then("^user should be able to view X button against each account in Call Queue$")
	public void user_should_be_able_to_view_X_button_against_each_account_in_Call_Queue() {
		Assert.assertTrue("Remove button not visible for each account in Call queue",
				callPayorQueuePage.isRemoveCallPayerQueueAccountBtnListVisible());
	}

	@When("^user clicks on remove X button$")
	public void user_clicks_on_remove_X_button() {
		removedInvoice = callPayorQueuePage.getRemovedInvoice();
		callPayorQueuePage.clickRemoveCallPayerQueueAccountBtn();
	}

	@Then("^user should be able to view the deleted account from Call Queue$")
	public void user_should_be_able_to_view_the_deleted_account_from_Call_Queue() {
		Assert.assertTrue("User not able to view the deleted account",
				callPayorQueueSteps.isRemovedAccountFromCallQueueVisible(removedInvoice));
	}

	@Then("^user should be able to view the count of accounts is decreased by (\\d+) in Call Queue$")
	public void user_should_be_able_to_view_the_count_of_accounts_is_decreased_by_in_Call_Queue(int difference) {
		int callPayerQueueCountFinal = callPayerQueueCount - difference;
		Assert.assertTrue("Count of accounts is not decreased by 1 in Call Queue",
				callPayorQueuePage.getCallPayerQueueCount() == callPayerQueueCountFinal);
	}
}