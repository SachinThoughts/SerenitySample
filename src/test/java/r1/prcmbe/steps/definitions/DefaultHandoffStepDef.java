package r1.prcmbe.steps.definitions;

import cucumber.api.DataTable;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import net.serenitybdd.core.pages.PageObject;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.LoginSteps;
//import r1.decision.serenity.steps.RelatedAccountSteps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefaultHandoffPage;
import r1.prcmbe.pages.NavigationPage;

public class DefaultHandoffStepDef extends PageObject {

	DefaultHandoffPage defaultHandOffPage;
	NavigationPage navPage;
	CommonMethods commonMethods;
	SettingsPage settingsPage;
	LoginSteps loginSteps;
	EnvironmentVariables environmentVariables;

	String workFlowDescription, recipientDesc, actionDescription, followUpDays, dispositionDescription,
			responseDeadline, dispositionCode, dispositionFollowUpDays, dispositionResponseDeadline, dispositionStatus,
			workFlowName, recipientName, actionName;
	private static String dbQueryFilename = "DefaultHandoff";

	@When("^user mouse hover Settings - R1_Decision link and click on Workflow Configuration link$|^user clicks on Work Flow Configuration link under Settings - R1_Decision$")
	public void user_mouse_hover_Settings_R1_Decision_link_and_click_on_workflow_configuration_link() {
		settingsPage.hoverSettingsR1Decisions();
		settingsPage.clickWorkflowConfig();
	}

	@Given("^user having AHtoDecision role is on \"([^\"]*)\" Screen$")
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
	}

	@When("^user clicks on Save Changes button for Add Handoff$")
	public void user_clicks_on_Save_Changes_button_for_Add_Handoff() {
		defaultHandOffPage.clickAddHandOffSaveChangesButton();
	}

	@When("^user runs the query to verify if the handoff is inserted \"([^\"]*)\"  $")
	public void user_runs_the_query_to_verify_if_the_handoff_is_inserted(String query) {
		try {
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					String.format(commonMethods.loadQuery(query, dbQueryFilename), workFlowName));
		} catch (Exception e) {
			Assert.assertTrue(
					"WorkFlowName of the added handoff is not fetched from DB.\nThe Technical Error is:\n" + e, false);
		}
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

	@When("^user enters text in Disposition Code textbox$")
	public void user_enters_text_in_Disposition_Code_textbox() {
		dispositionCode = defaultHandOffPage.enterDispositionCode();
	}

	@When("^user enters text in Disposition Description textbox: \"([^\"]*)\"$")
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
		defaultHandOffPage.selectDispositionStatusDD(dispositionStatusValue);
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

	@Given("^user is on R1 Hub page$")
	public void user_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies 2.0 - 01 Home"));
	}

	@When("^user clicks on Settings link$")
	public void user_clicks_on_Settings_link() {
		navPage.clickSettings();
	}
}
