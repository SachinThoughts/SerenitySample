package r1.prcmbe.steps.definitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.DefaultHandoffSteps;
import r1.prcmbe.serenity.steps.LoginSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.DefaultHandoffPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;

public class DefaultHandoffStepDef {

	DefaultHandoffPage defaultHandOffPage;
	NavigationPage navPage;
	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;

	@Steps
	SearchPageSteps searchPageSteps;
	@Steps
	LoginSteps loginSteps;
	@Steps
	SettingsPage settingsPage;
	@Steps
	DefaultHandoffSteps defaultHandoffSteps;

	String workFlowDescription, actionDescription, followUpDays, responseDeadline, dispositionCode,
			dispositionFollowUpDays, dispositionResponseDeadline, recipientName, searchText, dbId = "",
			facilitySettingValue, invoiceNumber, dbFacilitySettingValue, createVal, whyVal, dispositionVal, handoffType;
	static String dispositionStatus, actionName, dispositionDescription, recipientDesc, workFlowName;

	private static String dbQueryFilename = "DefaultHandoff";
	private final String BUBBLECOLOR = "rgba(61, 100, 168, 1)";

	@When("^click on Workflow Configuration link$")
	public void click_on_workflow_configuration_link() {
		settingsPage.clickWorkflowConfig();
	}

	@Given("^PRCM user is on \"([^\"]*)\" Screen$")
	public void user_having_AHtoDecision_role_is_on_Screen(String expectedTitle) {
		Assert.assertTrue("User is not on the expected Workflow screen",
				defaultHandOffPage.getTextDefaultHandOffPageTitle().equals(expectedTitle));
	}

	@Then("^user should be able to view \\+Add Handoff button on Handoff screen grid$")
	public void user_should_be_able_to_view_Add_Handoff_button_on_Handoff_screen_grid() {
		Assert.assertTrue("Add Handoff button is not visible", defaultHandOffPage.verifyVisibilityOfAddHandOffButton());
	}

	@When("^user clicks on \\+Add Handoff button$")
	public void user_clicks_on_Add_Handoff_button() {
		defaultHandOffPage.clickAddHandOffButton();
	}

	@Then("^user should be able to view popup window with following fields$")
	public void user_should_be_able_to_view_popup_window_with_following_fields(DataTable datatable) {
		List<String> expectedListOfAddHandoffElements = datatable.asList(String.class);
		Assert.assertTrue("Workflow Name field is not available",
				expectedListOfAddHandoffElements.get(0).equals(defaultHandOffPage.getTextAddHandOffWorkFlowNameLabel())
						&& defaultHandOffPage.checkVisibilityOfWorkFlowNameTextBox());
		Assert.assertTrue("Workflow Description field is not available",
				expectedListOfAddHandoffElements.get(1)
						.equals(defaultHandOffPage.getTextAddHandOffWorkFlowDescriptionLabel())
						&& defaultHandOffPage.checkVisibilityOfWorkFlowDescriptionTextBox());
		Assert.assertTrue("Worklist field is not available",
				expectedListOfAddHandoffElements.get(2).equals(defaultHandOffPage.getTextAddHandOffWorkList())
						&& defaultHandOffPage.checkVisibilityOfWorkListDD());
		Assert.assertTrue("AH Module Code field is not available",
				expectedListOfAddHandoffElements.get(3).equals(defaultHandOffPage.getTextAddHandOffAHModuleCode())
						&& defaultHandOffPage.checkVisibilityOfAHModuleCodeTextBox());
		Assert.assertTrue("Visible To Group field is not available",
				expectedListOfAddHandoffElements.get(4)
						.equals(defaultHandOffPage.getTextAddHandOffVisibleToGroupLabel())
						&& defaultHandOffPage.checkVisibilityOfVisibleToGroupDD());
		Assert.assertTrue("Activate Handoff field is not available",
				expectedListOfAddHandoffElements.get(5)
						.equals(defaultHandOffPage.getTextAddHandOffActivateHandOffLabel())
						&& defaultHandOffPage.checkVisibilityOfActivateHandOffCheckBox());
		Assert.assertTrue("Close button is not available",
				expectedListOfAddHandoffElements.get(6).equals(defaultHandOffPage.getTextAddHandOffCloseButton())
						&& defaultHandOffPage.checkVisibilityOfAddHandOffCloseButton());
		Assert.assertTrue("Save Changes button is not available",
				expectedListOfAddHandoffElements.get(7).equals(defaultHandOffPage.getTextAddHandOffSaveChangesButton())
						&& defaultHandOffPage.checkVisibilityOfAddHandOffSaveChangesButton());
	}

	@When("^user enters value in Workflow Name$")
	public void user_enters_value_in_Workflow_Name() {
		workFlowName = defaultHandOffPage.enterWorkFlowName();
	}

	@When("^user enters value in Workflow Description textbox: \"([^\"]*)\"$")
	public void user_enters_value_in_Workflow_Description_textbox(String workFlowDescription) {
		defaultHandOffPage.enterWorkFlowDescription(workFlowDescription);
	}

	@When("^user selects AHtoDecisionHandoff value from Worklist dropdown$")
	public void user_selects_AHtoDecisionHandoff_value_from_Worklist_dropdown() {
		defaultHandOffPage.selectWorkListDD();
	}

	@When("^user enters value in AH Module Code textbox: \"([^\"]*)\"$")
	public void user_enters_value_in_AH_Module_Code_textbox(String ahModuleCode) {
		defaultHandOffPage.enterAHModuleCode(ahModuleCode);
	}

	@When("^user selects any value from Visible to Group dropdown for Add Handoff$")
	public void user_selects_any_value_from_Visible_to_Group_dropdown_for_Add_Handoff() {
		defaultHandOffPage.selectVisibleToGroup();
		defaultHandOffPage.clickVisibleToGrpAHtoDecisionChkBox();
	}

	@When("^user clicks on Save Changes button for Add Handoff$")
	public void user_clicks_on_Save_Changes_button_for_Add_Handoff() {
		defaultHandOffPage.clickAddHandOffSaveChangesButton();
	}

	@When("^Default Handoff user runs the query to verify if the handoff is inserted \"([^\"]*)\"  $")
	public void user_runs_the_query_to_verify_if_the_handoff_is_inserted(String query)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename), workFlowName));
	}

	@Then("^user should be able to view newly inserted Handoff type in SQL result$")
	public void user_should_be_able_to_view_newly_inserted_Handoff_type_in_SQL_result() throws SQLException {
		String dbWorkFlowName = null;
		while (DatabaseConn.resultSet.next()) {
			dbWorkFlowName = DatabaseConn.resultSet.getString("Name");
		}
		loginSteps.log("The inserted handoff is visible in the database results with WorkFlow Name: " + dbWorkFlowName);
	}

	@Then("^user should be able to view the appropriate success message: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_handoff_success_message(String expectedSuccessMessage) {
		Assert.assertTrue("Expected handoff success message is not displayed",
				defaultHandOffPage.getTextSuccessMessage().contains(expectedSuccessMessage));
	}

	@When("^user selects radio button corresponding to the newly created Handoff and user clicks on Continue button$")
	public void user_selects_radio_button_corresponding_to_the_newly_created_Handoff_and_user_clicks_on_Continue_button() {
		defaultHandOffPage.selectNewlyAddedHandOff();
		defaultHandOffPage.clickStepOneContinueBtn();
	}

	@Then("^user should be able to navigate to Recipient tab and user should be able to view the message \"([^\"]*)\" under Choose Recipient Grid$")
	public void user_should_be_able_to_navigate_to_Recipient_tab_and_user_should_be_able_to_view_the_message_under_Choose_Recipient_Grid(
			String expMsg) {
		Assert.assertTrue("The expected message should be displayed when there are not added Recipients",
				defaultHandOffPage.getTextNoRecipientMsg().equals(expMsg));
	}

	@When("^user clicks on \\+Add Recipient button$")
	public void user_clicks_on_Add_Recipient_button() {
		defaultHandOffPage.clickAddRecipientButton();
	}

	@When("^user enters text in Recipient Name textbox$")
	public void user_enters_text_in_Recipient_Name_textbox() {
		recipientName = defaultHandOffPage.enterRecipientNameTextBox();
	}

	@When("^user enters text in Recipient Description textbox: \"([^\"]*)\"$")
	public void user_enters_text_in_Recipient_Description_textbox(String recipientDescription) {
		recipientDesc = defaultHandOffPage.enterRecipientDescriptionTextBox(recipientDescription);
	}

	@When("^user clicks on Add Recipient button on the popup$")
	public void user_clicks_on_Add_Recipient_button_on_the_popup() {
		defaultHandOffPage.clickSaveRecipientButton();
	}

	@Then("^user should be able to view newly created Recipient in Choose Recipient grid with correct data in columns$")
	public void user_should_be_able_to_view_newly_created_Recipient_in_Choose_Recipient_grid_with_correct_data_in_columns() {
		Assert.assertTrue("Recipient Name not displayed in the table as added",
				recipientName.equals(defaultHandOffPage.getTextSavedRecipientName()));
		Assert.assertTrue("Recipient Description not displayed in the table as added",
				recipientDesc.equals(defaultHandOffPage.getTextSavedRecipientDescription()));
	}

	@Then("^user should be able to view newly created Recipient name in Workflow Summary breadcrumb just after Handoff type \\(For eg\\. Handoff Type >> Recipient name\\)$")
	public void user_should_be_able_to_view_newly_created_Recipient_name_in_Workflow_Summary_breadcrumb_just_after_Handoff_type_For_eg_Handoff_Type_Recipient_name() {
		Assert.assertTrue("Recipient tab: Recipient Name not displayed in the crumb",
				defaultHandOffPage.getTextBreadcrumb().contains(recipientName));
		Assert.assertTrue("Recipient tab: WorkFlow Name not displayed in the crumb",
				defaultHandOffPage.getTextBreadcrumb().contains(workFlowName));
	}

	@Then("^user should be able to view the info message \"([^\"]*)\" under Choose Action Type grid$")
	public void user_should_be_able_to_view_the_info_message_under_Choose_Action_Type_grid(String expMsg) {
		Assert.assertTrue("The expected message should be displayed when there are not added actions",
				defaultHandOffPage.getTextworkFlowTypeActionsMessage().equals(expMsg));
	}

	@Then("^user should be able to navigate to Action Type tab by clicking on Continue button$")
	public void user_should_be_able_to_navigate_to_Action_Type_tab_by_clicking_on_Continue_button() {
		defaultHandOffPage.clickStepTwoContinueBtn();
	}

	@When("^user clicks on \\+Add New Action button$")
	public void user_clicks_on_Add_New_Action_button() {
		defaultHandOffPage.clickAddNewActionButton();
	}

	@When("^user enters text in Action Name textbox$")
	public void user_enters_text_in_Action_Name_textbox() {
		actionName = defaultHandOffPage.enterActionNameTextBox();
	}

	@When("^for Actions user enters text: \"([^\"]*)\" in Action Description textbox$")
	public void user_enters_text_in_Action_Description_textbox(String actionDesc) {
		actionDescription = defaultHandOffPage.enterActionDescriptionTextBox(actionDesc);
	}

	@When("^for Actions user selects \"([^\"]*)\" option from Next Action By dropdown$")
	public void user_selects_option_from_Next_Action_By_dropdown(String nextActionDDValue) {
		defaultHandOffPage.selectNextActionByDD(nextActionDDValue);
	}

	@When("^for Actions user enters \"([^\"]*)\" in Follow Up Days textbox$")
	public void user_enters_in_Follow_Up_Days_textbox(String followupDaysValue) {
		followUpDays = followupDaysValue;
		defaultHandOffPage.enterFollowUpDaysTextBox(followupDaysValue);
	}

	@When("^for Actions user enters: \"([^\"]*)\" in Follow Respond Deadline textbox$")
	public void user_enters_in_Follow_Respond_Deadline_textbox(String followRespondDeadlineValue) {
		responseDeadline = followRespondDeadlineValue;
		defaultHandOffPage.enterRespondDeadlineTextBox(followRespondDeadlineValue);
	}

	@When("^for Actions user selects: \"([^\"]*)\" option from Action Status dropdown$")
	public void user_selects_option_from_Action_Status_dropdown(String actionStatusDDvalue) {
		defaultHandOffPage.selectActionStatusDD(actionStatusDDvalue);
	}

	@When("^user clicks on Save Changes button on action popup$")
	public void user_clicks_on_Save_Changes_button_on_action_popup() {
		defaultHandOffPage.clickAddNewActionSaveChangesButton();
	}

	@When("^user clicks on Save Changes button on disposition popup$")
	public void user_clicks_on_Save_Changes_button_on_disposition_popup() {
		defaultHandOffPage.clickAddNewDispositionSaveChangesButton();
	}

	@Then("^user should be able to view newly created Action in Choose Action Type grid with correct data in the columns$")
	public void user_should_be_able_to_view_newly_created_Action_in_Choose_Action_Type_grid_with_correct_data_in_the_columns() {
		Assert.assertTrue("Saved Action Name not as per the value entered by user",
				defaultHandOffPage.getTextSavedActionName().equals(actionName));
		Assert.assertTrue("Saved Followupdays not as per the value entered by user",
				defaultHandOffPage.getTextSavedFollowUpDays().equals(followUpDays));
		Assert.assertTrue("Saved Time Line Days not as per the value entered by user",
				defaultHandOffPage.getTextSavedTimelineDays().equals(responseDeadline));
	}

	@Then("^user should be able to view newly created Action Name updated in Workflow Summary breadcrumb just after Handoff type and Recipient Name \\(For eg\\. Handoff Type >> Recipient name >> Action Name\\)$")
	public void user_should_be_able_to_view_newly_created_Action_Name_updated_in_Workflow_Summary_breadcrumb_just_after_Handoff_type_and_Recipient_Name_For_eg_Handoff_Type_Recipient_name_Action_Name() {
		Assert.assertTrue("Action tab: Recipient Name not displayed in the crumb",
				defaultHandOffPage.getTextBreadcrumb().contains(recipientName));
		Assert.assertTrue("Action tab: WorkFlow Name Name not displayed in the crumb",
				defaultHandOffPage.getTextBreadcrumb().contains(workFlowName));
		Assert.assertTrue("Action tab: Action Name not displayed in the crumb",
				defaultHandOffPage.getTextBreadcrumb().contains(actionName));
	}

	@When("^user clicks on Continue button to move to Desposition tab and user should be able to view  the message \"([^\"]*)\" under Choose Disposition Type grid$")
	public void user_clicks_on_Continue_button_to_move_to_Desposition_tab_and_user_should_be_able_to_view_the_message_under_Choose_Disposition_Type_grid(
			String msg) {
		defaultHandOffPage.clickStepThreeContinueBtn();
		Assert.assertTrue("The expected message should be displayed when there are no added dispositions",
				defaultHandOffPage.getTextWorkFlowTypeDispositionMessage().equals(msg));
	}

	@When("^user clicks on \\+Add New Disposition button$")
	public void user_clicks_on_Add_New_Disposition_button() {
		defaultHandOffPage.clickAddNewDispositionButton();
	}

	@When("^user enters text in Disposition Code textbox$")
	public void user_enters_text_in_Disposition_Code_textbox() {
		dispositionCode = defaultHandOffPage.enterDispositionCode();
	}

	@When("^user enters text in Disposition Description textbox: \"([^\"]*)\"$")
	public void user_enters_text_in_Disposition_Description_textbox(String dispositionDescriptionValue) {
		dispositionDescription = dispositionDescriptionValue;
		defaultHandOffPage.enterDispositionNameTextBox(dispositionDescriptionValue);
	}

	@When("^user selects any option from Next Desposition By dropdown$")
	public void user_selects_any_option_from_Next_Desposition_By_dropdown() {
		defaultHandOffPage.selectNextDispositionByDD();
	}

	@When("^For disposition user enters \"([^\"]*)\" in Follow Up Days textbox$")
	public void for_disposition_user_enters_in_Follow_Up_Days_textbox(String followupDaysValue) {
		dispositionFollowUpDays = followupDaysValue;
		defaultHandOffPage.enterDispositionFollowUpDaysTextBox(followupDaysValue);
	}

	@When("^For disposition user enters: \"([^\"]*)\" in Follow Respond Deadline textbox$")
	public void for_disposition_user_enters_in_Follow_Respond_Deadline_textbox(String followRespondDeadlineValue) {
		dispositionResponseDeadline = followRespondDeadlineValue;
		defaultHandOffPage.enterDispositionRespondDeadlineTextBox(followRespondDeadlineValue);
	}

	@When("^For disposition user selects \"([^\"]*)\" option from Desposition Status dropdown$")
	public void for_disposition_user_selects_option_from_Desposition_Status_dropdown(String dispositionStatusValue) {
		dispositionStatus = dispositionStatusValue;
		defaultHandOffPage.selectDispositionStatusDropdown(dispositionStatusValue);
	}

	@When("^user enters text: \"([^\"]*)\" in Predefined Note text area$")
	public void user_enters_text_in_Predefined_Note_text_area(String predefinedNotesValue) {
		defaultHandOffPage.enterPreDefinedNotes(predefinedNotesValue);
	}

	@Then("^user should be able to view the newly created Disposition in Choose Disposition Type grid with correct data in the columns$")
	public void user_should_be_able_to_view_the_newly_created_Disposition_in_Choose_Disposition_Type_grid_with_correct_data_in_the_columns() {
		Assert.assertTrue("Disposition name displayed in the table does not match with one selected",
				defaultHandOffPage.getTextSavedDispositionName().equals(dispositionDescription));
		Assert.assertTrue("Disposition followup days displayed in the table does not match with one selected",
				defaultHandOffPage.getTextSavedDispositionFollowUpDays().equals(dispositionFollowUpDays));
		Assert.assertTrue("Disposition time limit displayed in the table does not match with one selected",
				defaultHandOffPage.getTextSavedDispositionTimeLimit().equals(dispositionResponseDeadline));
		Assert.assertTrue("Disposition Status displayed in the table does not match with one selected",
				defaultHandOffPage.getTextSavedDispositionStatus().equals(dispositionStatus));
	}

	@Given("^user login to SQL server and connect to \"([^\"]*)\" database$")
	public void user_login_to_SQL_server_and_connect_to_database(String database) throws IOException {
		DatabaseConn.serverName = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("bindURL");
		DatabaseConn.databaseName = database;
	}

	@When("^user mouse hover on IT Tools link$")
	public void user_mouse_On_IT_Tools_links() {
		settingsPage.hoverITToolsLink();
	}

	@When("^user click on FacilitySetting Configuration link$")
	public void user_click_on_FacilitySetting_Configuration_link() {
		settingsPage.clickFacilitySettingConfigLink();
	}

	@Then("^user having AHtoDecision Admin role is on \"([^\"]*)\" Screen$")
	public void user_having_AHtoDecision_Admin_role_is_on_Screen(String expectedTitle) {
		Assert.assertTrue("User is not on the " + expectedTitle + " screen",
				defaultHandOffPage.getFacilitySettingConfigTitle().equals(expectedTitle));
	}

	@When("^user clicks on Settings link from footer$")
	public void user_clicks_on_Settings_link_from_footer() {
		navPage.clickFooterSettings();
	}

	@When("^user selects \"([^\"]*)\" option from Search dropdown$")
	public void user_selects_option_from_Search_dropdown(String searchValue) {
		defaultHandOffPage.selectTextSearchDrpdwn(searchValue);
	}

	@When("^user enters any facility code in the Search textbox$")
	public void user_enters_any_facility_code_in_the_Search_textbox() throws IOException {
		defaultHandOffPage.enterSearchTxtBox(CommonMethods.loadProperties("facility").substring(0, 4));
	}

	@When("^user clicks on Search Data icon$")
	public void user_clicks_on_Search_Data_icon() {
		defaultHandOffPage.clickSearchBtn();
	}

	@Then("^user should be able to view the row for searched facility in Locations grid$")
	public void user_should_be_able_to_view_the_row_for_searched_facility_in_Locations_grid() throws IOException {
		Assert.assertTrue("incorrect facility displayed in the grid", CommonMethods.loadProperties("facility")
				.substring(0, 4).equals(defaultHandOffPage.getSearchFacilityCode()));
	}

	@When("^user clicks on View link of searched facility$")
	public void user_clicks_on_View_link_of_searched_facility() {
		defaultHandOffPage.clickSearchFacilityViewLink();
	}

	@Then("^user should be able to view Facility Setting grid for searched facility$")
	public void user_should_be_able_to_view_Facility_Setting_grid_for_searched_facility() {
		defaultHandOffPage.isSearchFacilitySettingGridVisible();
	}

	@When("^user selects \"([^\"]*)\" option from facility setting Search dropdown$")
	public void user_selects_option_from_facility_setting_Search_dropdown(String searchValue) {
		defaultHandOffPage.selectTextFSSearchDrpdwn(searchValue);
	}

	@When("^user enters \"([^\"]*)\" in the Search textbox$")
	public void user_enters_in_the_Search_textbox(String value) {
		searchText = value;
		defaultHandOffPage.enterFSSearchTxtBox(value);
	}

	@When("^user clicks on facility setting Search Data icon$")
	public void user_clicks_on_facility_setting_Search_Data_icon() {
		defaultHandOffPage.clickFSSearchBtn();
	}

	@Then("^user should be able to view the row for searched Setting Name in Facility Settings grid$")
	public void user_should_be_able_to_view_the_row_for_searched_Setting_Name_in_Facility_Settings_grid() {
		Assert.assertTrue(searchText + " is not displayed in the grid",
				searchText.equals(defaultHandOffPage.getSearchFacilitySettingText()));
	}

	@When("^user clicks on Edit icon of searched facility setting$")
	public void user_clicks_on_Edit_icon_of_searched_facility_setting() {
		defaultHandOffPage.clickSearchFSEditIcon();
	}

	@Then("^user should be able to view Facility Setting Details pop up$")
	public void user_should_be_able_to_view_Facility_Setting_Details_pop_up() {
		Assert.assertTrue("Facility Setting Details pop up is not visible",
				defaultHandOffPage.isFSDetailsPopupVisible());
	}

	@When("^user runs the \"([^\"]*)\" query to fetch ID$")
	public void user_runs_the_query_to_fetch_ID(String query) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename), workFlowName));
	}

	@Then("^user should be able to fetch the ProcessID$|^user should be able to fetch the WorkflowTypeID$")
	public void user_should_be_able_to_fetch_the_ProcessID() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbId = DatabaseConn.resultSet.getString(1);
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("ID is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("ID is empty for the executed query", !dbId.isEmpty());
		loginSteps.log("Fetched ID from database is " + dbId);
	}

	@When("^user enters comma followed by fetched ProcessID in Setting Value text area of Facility Settings Detail pop up$|^user enters comma followed by fetched WorkflowTypeID in Setting Value text area of Facility Settings Detail pop up$")
	public void user_enters_comma_followed_by_fetched_ProcessID_in_Setting_Value_text_area_of_Facility_Settings_Detail_pop_up() {
		defaultHandOffPage.updateSettingValueTxtArea("," + dbId);
		facilitySettingValue = defaultHandOffPage.getSettingValueTxtArea();
	}

	@When("^user clicks on Update Facility Setting button$")
	public void user_clicks_on_Update_Facility_Setting_button() {
		defaultHandOffPage.clickUpdateSettingValueBtn();
	}

	@Then("^user should be able to view Facility Settings Details pop up as closed$")
	public void user_should_be_able_to_view_Facility_Settings_Details_pop_up_as_closed() {
		Assert.assertFalse("Facility Setting Details pop up is not closed",
				defaultHandOffPage.isFSDetailsPopupVisible());
	}

	@When("^user runs the \"([^\"]*)\" query for default handoff$")
	public void user_runs_the_query_query_for_default_handoff(String query) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(query, dbQueryFilename));
	}

	@Then("^user should be able to view added ProcessID in SQL result$|^user should be able to view added WorkflowTypeID in SQL result$")
	public void user_should_be_able_to_view_added_ProcessID_in_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbFacilitySettingValue = DatabaseConn.resultSet.getString("SettingValue");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("SettingValue is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("SettingValue is not added in database", facilitySettingValue.equals(dbFacilitySettingValue));
	}

	@When("^user selects existing added handoff$")
	public void user_selects_existing_added_handoff() {
		workFlowName = defaultHandOffPage.getSecondLastAHtoDecisionAdminWorkflow();
	}

	@When("^user clicks on Billing & Follow-up link from footer$")
	public void user_clicks_on_Billing_Follow_up_link_from_footer() {
		navPage.clickFooterBillingFollowUpLink();
	}

	@When("^user selects \"([^\"]*)\" option from Operator dropdown$")
	public void user_selects_option_from_Operator_dropdown(String operatorValue) {
		searchPage.operatorSelectText(operatorValue);
	}

	@When("^user enters the SQL result in Visit Number Search textbox$")
	public void user_enters_the_SQL_result_in_Visit_Number_Search_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber = DatabaseConn.resultSet.getString("InvoiceNumber");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Invoice Number is not fetched from DB.\nThe Technical Error is:\n" + sQLException,
					false);
		}
		loginSteps.log("Fetched Invoice Number from Database is " + invoiceNumber);
		searchPage.enterInvoiceNumber(invoiceNumber);
	}

	@Then("^user should be able to view R1 Decision Account page$")
	public void user_should_be_able_to_view_R1_Decision_Account_page() {
		Assert.assertTrue("Incorrect or no R1 Decision Account page is opened",
				searchPageSteps.verifyInvoiceNumberWithEqualOperator(invoiceNumber));
	}

	@When("^user clicks on Handoff Type dropdown$")
	public void user_clicks_on_Handoff_Type_dropdown() {
		accInfoPage.clickHandOffTypeDrpDown();
	}

	@Then("^user should be able to view the newly added handoff in Handoff Type dropdown$")
	public void user_should_be_able_to_view_the_newly_added_handoff_in_Handoff_Type_dropdown() {
		Assert.assertTrue("Newly added handoff is not visible in the Handoff Type dropdown",
				accInfoPage.getHandOffTypeDrpDownValues().contains(workFlowName));
	}

	@When("^user clicks on Handoff link button$|^user clicks on Handoff button$")
	public void user_clicks_on_Handoff_link_button() {
		accInfoPage.clickHandOffBtn();
	}

	@When("^user selects (.*) from Handoff Type dropdown$")
	public void user_selects_from_Handoff_Type_dropdown(String drpVal) {
		handoffType = defaultHandOffPage.selectHandoffType(drpVal);
	}

	@When("^user selects any value from Create dropdown$")
	public void user_selects_any_value_from_Create_dropdown() {
		createVal = defaultHandOffPage.selectAnyOptionFromCreateDrpdwn();
	}

	@When("^user selects any value from Why dropdown$")
	public void user_selects_any_value_from_Why_dropdown() {
		whyVal = defaultHandOffPage.selectAnyOptionFromWhyDrpdwn();
	}

	@When("^user selects any value from Disposition dropdown$")
	public void user_selects_any_value_from_Disposition_dropdown() {
		if (!(defaultHandOffPage.selectAnyOptionFromDispositionDrpdwn() == null)) {
			dispositionVal = defaultHandOffPage.selectAnyOptionFromDispositionDrpdwn();
		}
	}

	@When("^user enters any \"([^\"]*)\" in Notes text area$")
	public void user_enters_any_in_Notes_text_area(String note) {
		defaultHandOffPage.enterNote(note);
	}

	@Then("^user should be able to view H under event circle in blue color for newly added Handoff type on Horizontal timeline$")
	public void user_should_be_able_to_view_under_event_circle_in_blue_color_for_newly_added_Handoff_type_on_Horizontal_timeline() {
		defaultHandOffPage.expandAccountActionHistory();
		Assert.assertTrue("User is not able to view blue bubble with H text",
				defaultHandOffPage.getHandoffBubbleColor().equals(BUBBLECOLOR));
	}

	@When("^user hovers the event circle for newly added Handoff type$")
	public void user_hovers_the_event_circle_for_newly_added_Handoff_type() {
		defaultHandOffPage.hoverOnAddedBubble();
	}

	@Then("^user should be able to view the following columns$")
	public void user_should_be_able_to_view_the_following_columns(DataTable expectedHeaders) {
		List<String> expectedListOfEventCircleCols = expectedHeaders.asList(String.class);
		Assert.assertTrue("The column headers in the popup text are not as expected.",
				defaultHandOffPage.getListOfEventCircleColumns().equals(expectedListOfEventCircleCols));
	}

	@When("^user clicks on Save button on the handoff popup$")
	public void user_clicks_on_Save_button_on_the_handoff_popup() {
		defaultHandOffPage.clickHandoffSaveBtn();
	}

	@Then("^user should be able to view Handoff type value as newly added handoff type$")
	public void user_should_be_able_to_view_Handoff_type_value_as_newly_added_handoff_type() {
		Assert.assertTrue("User is not able to view handoff type in the handoff bubble popup",
				defaultHandOffPage.getListOfPopupValues().contains(handoffType));
	}

	@Then("^user should be able to view Added column value as system current date: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Added_column_value_as_system_current_date(String query)
			throws ClassNotFoundException, SQLException, Exception {
		Assert.assertTrue("Created date does not matched with system date in the handoff bubble popup",
				defaultHandOffPage.getCreatedDate()
						.equals(defaultHandoffSteps.getHandoffCreatedDate(query, invoiceNumber)));
	}

	@Then("^user should be able to view Followup column value as system current date: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Followup_column_value_as_system_current_date(String query)
			throws ClassNotFoundException, SQLException, Exception {
		Assert.assertTrue(
				"Followup date is not as expected in the handoff bubble popup. Actual: "
						+ defaultHandOffPage.getFollowupDate() + " Expected: "
						+ defaultHandoffSteps.getHandoffFollowupDate(query, invoiceNumber),
				defaultHandOffPage.getFollowupDate()
						.equals(defaultHandoffSteps.getHandoffFollowupDate(query, invoiceNumber)));
	}

	@Then("^user should be able to view Created column value as Logged in username and userid: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Created_column_value_as_Logged_in_username_and_userid(String queryName)
			throws ClassNotFoundException, SQLException, IOException, Exception {
		Assert.assertTrue("User is not able to view handoff type in the handoff bubble popup",
				defaultHandOffPage.getListOfPopupValues().contains(defaultHandoffSteps.getDisplayName(queryName)));
	}

	@Then("^user should be able to view Action with the correct selected data$")
	public void user_should_be_able_to_view_Action_with_the_correct_selected_data() {
		Assert.assertTrue("User is not able to view Action with correct selected data in the handoff bubble popup",
				defaultHandOffPage.getListOfPopupValues().contains(whyVal));
	}

	@Then("^user should be able to view Disposition with the correct selected data$")
	public void user_should_be_able_to_view_Disposition_with_the_correct_selected_data() {
		Assert.assertTrue("User is not able to view Action with correct selected data in the handoff bubble popup",
				defaultHandOffPage.getListOfPopupValues().contains(dispositionVal));
	}

	@When("^click on Show Account Action History Notes button$")
	public void click_on_Show_Account_Action_History_Notes_button() {
		defaultHandOffPage.clickAccountActionHistoryNotesBtn();
	}

	@Then("^user should be able to view the following columns in Account Action History$")
	public void user_should_be_able_to_view_the_following_columns_in_Account_Action_History(DataTable expectedHeaders) {
		List<String> expectedListOfEventCircleCols = expectedHeaders.asList(String.class);
		Assert.assertTrue("The column headers in the popup text are not as expected in Account Action History.",
				defaultHandOffPage.getAccountActionHistoryColumns().equals(expectedListOfEventCircleCols));
	}

	@Then("^user should be able to view Handoff type value as newly added handoff type in Account Action History$")
	public void user_should_be_able_to_view_Handoff_type_value_as_newly_added_handoff_type_in_Account_Action_History() {
		Assert.assertTrue("User is not able to view expected handoff type in Account Action History",
				defaultHandOffPage.getAccountActionHistoryValues().contains(handoffType));
	}

	@Then("^user should be able to view Action with the correct selected data in Account Action History$")
	public void user_should_be_able_to_view_Action_with_the_correct_selected_data_in_Account_Action_History() {
		Assert.assertTrue("User is not able to view Action with correct selected data in Account Action History",
				defaultHandOffPage.getAccountActionHistoryValues().contains(whyVal));
	}

	@Then("^user should be able to view Disposition with the correct selected data in Account Action History$")
	public void user_should_be_able_to_view_Disposition_with_the_correct_selected_data_in_Account_Action_History() {
		Assert.assertTrue("User is not able to view Action with correct selected data in Account Action History",
				defaultHandOffPage.getAccountActionHistoryValues().contains(dispositionVal));
	}

	@Then("^user should be able to view Added column value as system current date in Account Action History: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Added_column_value_as_system_current_date_in_Account_Action_History(
			String query) throws ClassNotFoundException, SQLException, Exception {
		Assert.assertTrue("Created date does not matched with system date in Account Action History",
				defaultHandOffPage.getAccountActionHistoryAddedDate()
						.equals(defaultHandoffSteps.getHandoffCreatedDate(query, invoiceNumber)));
	}

	@Then("^user should be able to view Created column value as Logged in username and userid in Account Action History: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Created_column_value_as_Logged_in_username_and_userid_in_Account_Action_History(
			String queryName) throws ClassNotFoundException, SQLException, IOException, Exception {
		Assert.assertTrue("User is not able to view handoff type in Account Action History", defaultHandOffPage
				.getAccountActionHistoryValues().contains(defaultHandoffSteps.getDisplayName(queryName)));
	}

	@Then("^user should be able to view Followup column value as system current date in Account Action History: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Followup_column_value_as_system_current_date_in_Account_Action_History(
			String query) throws ClassNotFoundException, SQLException, Exception {
		Assert.assertTrue(
				"Followup date is not as expected in Account Action History. Actual: "
						+ defaultHandOffPage.getAccountActionHistoryFollowupDate() + " Expected: "
						+ defaultHandoffSteps.getHandoffFollowupDate(query, invoiceNumber),
				defaultHandOffPage.getAccountActionHistoryFollowupDate()
						.equals(defaultHandoffSteps.getHandoffFollowupDate(query, invoiceNumber)));
	}

	@Then("^user should be able to view the appropriate handoff success message: \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_appropriate_handoff_success_message(String expectedSuccessMessage) {
		Assert.assertTrue("Expected handoff success message is not displayed",
				defaultHandOffPage.getTextHandoffSuccessMessage().contains(expectedSuccessMessage));
	}
}
