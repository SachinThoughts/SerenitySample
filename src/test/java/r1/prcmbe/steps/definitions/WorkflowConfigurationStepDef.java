package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefaultHandoffPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.WorkflowConfigurationPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.WorkflowConfigurationSteps;

public class WorkflowConfigurationStepDef extends PageObject {

	private final String BLUECOLORRGBCODE = "rgba(61, 100, 168, 1)";
	NavigationPage navPage;
	SettingsPage settingsPage;
	WorkflowConfigurationPage workflowConfigPage;
	DefaultHandoffPage defaultHandOffPage;
	CommonMethods commonMethods;

	@Steps
	FinancialInfoSteps financialInfoSteps;
	@Steps
	WorkflowConfigurationSteps workflowConfigSteps;

	String dbFileName = "WorkFlowConfiguration", dbHandOffName, dbRecipientName, defaultRecipientName,
			recipientNameOtherThanDefault, dispositionNotes, workflowName, respondDeadline, updatedBy, updatedDate,
			successMsg, recipientName, recipientDesc, createdBy, createdDate, nextDispositionByDropdownValue,
			dispositionStatusByDropdownValue, updatedRecipientDesc;

	int dbWorkFlowTypeId;

	@Given("^user having AHtoDecision Admin role is on R1 Hub page$")
	public void user_having_AHtoDecision_Admin_role_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies"));
	}

	@When("^user clicks on Workflow Configuration link$")
	public void user_clicks_on_Workflow_Configuration_link() {
		settingsPage.clickWorkflowConfig();
	}

	@Given("^user having AHtoDecision Admin role is on workflow configuration home page$")
	public void user_having_AHtoDecision_Admin_role_is_on_workflow_configuration_home_page() {
		Assert.assertTrue("Workflow Config page is not visible", workflowConfigPage.isWorkflowTitleVisible());
	}

	@Then("^user should be able to view Hand off tab selected by default$")
	public void user_should_be_able_to_view_Hand_off_tab_selected_by_default() {
		Assert.assertTrue("Handoff tab is not visible", workflowConfigPage.isHandoffTabVisible());
	}

	@Then("^the hand off tab is highlighted in blue color$")
	public void the_hand_off_tab_is_highlighted_in_blue_color() {
		Assert.assertTrue("Handoff tab color is not Blue",
				workflowConfigPage.getHandoffTabColor().equals(BLUECOLORRGBCODE));
	}

	@Then("^user should be able to view tabs$")
	public void user_should_be_able_to_view_tabs(DataTable tabNames) {
		List<String> tabText = tabNames.asList(String.class);
		Assert.assertTrue("The workflow tabs doesnt match", workflowConfigPage.getWorkflowTabs().equals(tabText));
	}

	@Then("^user should be able to view Workflow Summary label with selected Handoff type value$")
	public void user_should_be_able_to_view_Workflow_Summary_label_with_selected_Handoff_type_value() {
		Assert.assertTrue("The workflow summary label doesnt match with selected handoff label",
				workflowConfigPage.getWorkflowSummaryLabel().equals(workflowConfigPage.getDefaultHandoffName()));
	}

	@Then("^user should be able to view \\+Add Handoff button$")
	public void user_should_be_able_to_view_Add_Handoff_button() {
		Assert.assertTrue("Add handoff button is not visible", workflowConfigPage.isAddHandoffBtnVisible());
	}

	@Then("^user should be able to view Continue > button ?$")
	public void user_should_be_able_to_view_Continue_button() {
		Assert.assertTrue("Continue button is not visible", workflowConfigPage.isStepFirstContinueBtnVisible());
	}

	@Then("^user should be able to view grid with column headers$")
	public void user_should_be_able_to_view_grid_with_column_headers(DataTable workflowHeaders) {
		List<String> workflowHeaderNames = workflowHeaders.asList(String.class);
		Assert.assertTrue("Workflow headers doesnt match",
				workflowConfigPage.getWorkflowGridHeaders().equals(workflowHeaderNames));
	}

	@Then("^user should be able to view Edit link button$")
	public void user_should_be_able_to_view_Edit_link_button() {
		Assert.assertTrue("Edit handoff button is not visible", workflowConfigPage.isHandoffEditButtonVisible());
	}

	@Then("^user should able to view Radio button checked against first handoff type$")
	public void user_should_able_to_view_Radio_button_checked_against_first_handoff_type() {
		Assert.assertTrue("Default radio button is not checked against first handoff",
				workflowConfigPage.isDefaultRadioBtnSelected().equalsIgnoreCase("true"));
	}

	@When("^user clicks on Edit link button against particular hand off type in Choose Handoff grid$")
	public void user_clicks_on_Edit_link_button_against_particular_hand_off_type_in_Choose_Handoff_grid() {
		workflowConfigPage.clickOnRandomEditLink();
	}

	@Then("^user should be able to view Edit Handoff pop up window with labels|user should be able to view Add Handoff pop-up window with controls$$")
	public void user_should_be_able_to_view_Edit_Handoff_pop_up_window_with_labels(DataTable workFlowHeadersOfPopup) {
		List<String> workflowHeaderNames = workFlowHeadersOfPopup.asList(String.class);
		Assert.assertTrue("Labels do not match in the Edit Popup",
				workflowConfigPage.getLabelsOnEditPopup().equals(workflowHeaderNames));
	}

	@Then("^user should able to view following controls$")
	public void user_should_able_to_view_following_controls(DataTable editPopupControls) {
		List<String> controlsOnEditPopup = editPopupControls.asList(String.class);
		Assert.assertTrue(" Controls do not match in the Edit popup",
				workflowConfigPage.getControlsOnEditPopup().equals(controlsOnEditPopup));
	}

	@Then("^user should be able to view prepopulated values in all controls$")
	public void user_should_be_able_to_view_prepopulated_values_in_all_controls() {
		Assert.assertTrue(" Values are not populated in the controls",
				workflowConfigPage.areValuesPopulatedInTheControls());
	}

	@When("^user clicks on Close button$")
	public void user_clicks_on_Close_button() {
		workflowConfigPage.clickOnCloseBtnOnEditPopup();
	}

	@Then("^Edit Handoff pop up window should get closed with no data saved$")
	public void edit_Handoff_pop_up_window_should_get_closed_with_no_data_saved() {
		Assert.assertFalse(" Edit pop up field is not closed ",
				workflowConfigPage.isEditPopupVisible() && workflowConfigPage.isSuccessMsgVisible());
	}

	@When("^user updates value in any of the fields$")
	public void user_updates_value_in_any_of_the_fields() {
		workflowConfigPage.editWorkflowDescription();
	}

	@When("^user clicks on Save changes button$")
	public void user_clicks_on_Save_changes_button() {
		workflowConfigPage.clickOnSaveBtn();
	}

	@Then("^user should be able to view handoff message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_handoff_message(String successMessage) {
		Assert.assertTrue("User is not able to see the message ",
				workflowConfigPage.getSuccessMsgOnSave().equals(successMessage));
	}

	@Then("^user should no longer be able to view Edit Handoff popup window$")
	public void user_should_no_longer_be_able_to_view_Edit_Handoff_popup_window() {
		Assert.assertFalse("User can still see the Edit popup ", workflowConfigPage.isEditPopupVisible());
	}

	@Then("^user should be able to view Updated values related to handoff type in Choose Handoff grid$")
	public void user_should_be_able_to_view_Updated_values_related_to_handoff_type_in_Choose_Handoff_grid() {
		Assert.assertTrue(" updated values are not visible", workflowConfigPage.isNewlyEditHandoffVisible());
	}

	@When("^user enters value in Workflow Name on popup window$")
	public void user_enters_value_in_Workflow_Name_on_popup_window() {
		workflowName = defaultHandOffPage.enterWorkFlowName();
	}

	@Then("^user should no longer be able to view Add Handoff pop-up window$")
	public void user_should_no_longer_be_able_to_view_Add_Handoff_pop_up_window() {
		Assert.assertFalse("Add Handoff popup window is visible ", workflowConfigPage.isAddHandOffLabelVisible());
	}

	@Then("^user should be able to view newly added handoff in the Choose Handoff grid$")
	public void user_should_be_able_to_view_newly_added_handoff_in_the_Choose_Handoff_grid() {
		Assert.assertTrue("Newly added handoff is not visible ",
				workflowConfigPage.isNewlyAddedHandOffVisible(workflowName.trim()));
	}

	@When("^user run the query to fetch hand-off id (.+)$")
	public void user_run_the_query_to_fetch_handoff_id(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbWorkFlowTypeId = DatabaseConn.resultSet.getInt("WorkflowTypeID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("WorkflowTypeID is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user run the query to fetch hand-off name (.+)$")
	public void user_run_the_query_to_fetch_handoff_name(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
	}

	@When("^user fetches any Handoff Type from DB$")
	public void user_fetches_any_Handoff_Type_from_DB() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbHandOffName = DatabaseConn.resultSet.getString("Name");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("HandOffName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user clicks on Radio button against any fetched Handoff Type in Choose Handoff grid$")
	public void user_clicks_on_Radio_button_against_any_fetched_Handoff_Type_in_Choose_Handoff_grid() {
		workflowConfigPage.clickOnRadioBtnAgnstFetchedHandOff(dbHandOffName);
	}

	@When("^user clicks on continue button on Handoff tab$")
	public void user_clicks_on_continue_button_on_Handoff_tab() {
		workflowConfigPage.clickOnContinueBtnOnHandoffTab();
	}

	@Then("^user should be able to navigate to Recipient tab$")
	public void user_should_be_able_to_navigate_to_recipient_tab() {
		Assert.assertTrue("User is not navigated to Recipient Tab", workflowConfigPage.isRecipientPageVisible());
	}

	@Then("^user should be able to view Recipient tab highlighted in blue color$")
	public void user_should_be_able_to_view_recipient_tab_highlighted_in_blue_color() {
		Assert.assertTrue("Recipient tab color is not Blue",
				workflowConfigPage.getRecipientTabColour().equals(BLUECOLORRGBCODE));
	}

	@Then("^user should be able to view Workflow Summary label with selected Recipient appended after Handoff type value$")
	public void user_should_be_able_to_view_workflow_summary_label_with_selected_recipient_appended_after_handoff_type_value() {
		Assert.assertTrue("Recipeint Name is not appended after Handoff",
				workflowConfigPage.isRecipientAppendInBreadcrumbInRecipientTab(dbHandOffName,
						workflowConfigPage.getDefaultSelectedRecipientName()));
	}

	@Then("^user should be able to view \\+Add Recipient and Continue > button$")
	public void user_should_be_able_to_view_Add_Recipient_and_Continue_button() {
		Assert.assertTrue("User is not able to view +Add Recipient and Continue button ",
				workflowConfigPage.isContinueAndAddRecipientOnRecipientTabVisible());
	}

	@Then("^user should able to view Choose Recipient Label$")
	public void user_should_able_to_view_Choose_Recipient_Label() {
		Assert.assertTrue("User is not able to view Choose Recipient Label; ",
				workflowConfigPage.isChooseRecipientVisible());
	}

	@And("^user should be able to view grid with columns headers$")
	public void user_should_be_able_to_view_grid_with_columns_headers(DataTable expectedColumnHeaders) {
		List<String> recipientColumnLabels = expectedColumnHeaders.asList(String.class);
		Assert.assertTrue(" User is not able to view column headers",
				workflowConfigPage.getSopHeaderList().containsAll(recipientColumnLabels));
	}

	@And("^user should be able to view Edit icon button adjacent to Recipient and Radio button checked against first Recipient$")
	public void user_should_be_able_to_view_edit_icon_button_adjacent_to_recipient_and_radio_button_checked_against_first_recipient() {
		Assert.assertTrue("Edit Icon is not visible against Recipient Name ",
				workflowConfigPage.isEditIconOnRecipientTabVisible());
		Assert.assertTrue("Default radio button is not checked against first Recipient",
				workflowConfigPage.isFirstRecipientBtnSelected().equalsIgnoreCase("true"));
	}

	@And("^user should be able to view Details link button for respective Recipient$")
	public void user_should_be_able_to_view_details_link_button_for_respective_recipient() {
		Assert.assertTrue("User is not able to view Details link ",
				workflowConfigPage.isDetailsIconOnRecipientVisible());
	}

	@When("^user clicks on Details link button on Recipient Tab$")
	public void user_clicks_on_details_link_button_on_Recipient_Tab() {
		workflowConfigPage.clickOnDetailsOnRecipientTab();
	}

	@Then("^user should be able to view detailed columns on Recipient Tab$")
	public void user_should_be_able_to_view_detailed_columns_on_Recipient_Tab(DataTable expectedColumHeaders) {
		List<String> recipientColumnLabels = expectedColumHeaders.asList(String.class);
		Assert.assertTrue("User is not able to see column headers",
				workflowConfigPage.getDetailColumnHeadersRecipientTab().equals(recipientColumnLabels));
	}

	@When("^user verifies that radio button is selected against the Recipient$")
	public void user_verifies_that_radio_button_is_selected_against_the_Recipient() {
		Assert.assertTrue("Default radio button is not checked against first Recipient",
				workflowConfigPage.isFirstRecipientBtnSelected().equalsIgnoreCase("true"));
	}

	@When("^user clicks on Continue button on Recipient tab$")
	public void user_clicks_on_Continue_button_on_Recipient_tab() {
		workflowConfigPage.clickOnContinueBtnOnRecipientTab();
	}

	@When("^user verifies that radio button is selected to associated Action Type$")
	public void user_verifies_that_radio_button_is_selected_to_associated_Action_Type() {
		Assert.assertTrue("Default radio button is not checked against first Recipient",
				workflowConfigPage.isFirstRadioBtnActionTabSelected().equalsIgnoreCase("true"));
	}

	@When("^user clicks on Continue button on Action type Tab$")
	public void user_clicks_on_Continue_button_on_Action_type_Tab() {
		workflowConfigPage.clickContinueBtnOnActionTypeTab();
	}

	@Then("^user should be able to view Add New Disposition pop up with controls$")
	public void user_should_be_able_to_view_Add_New_Disposition_pop_up_with_controls(DataTable expectedLabelHeaders) {
		List<String> dispositionPopupLabels = expectedLabelHeaders.asList(String.class);
		Assert.assertTrue("User is not able to see column headers",
				workflowConfigPage.getListOfLabelsOnDispositionPopup().containsAll(dispositionPopupLabels));
	}

	@And("^user can see Save changes button on the Disposition popup$")
	public void user_can_see_save_changes_button_on_the_disposition_popup() {
		Assert.assertTrue("Save Button is not visisble on the DispositionPopup",
				workflowConfigPage.isSaveBtnOnDispositionPopupVisible());
	}

	@When("^user clicks on Save Changes button without entering any text$")
	public void user_clicks_on_Save_Changes_button_without_entering_any_text() {
		workflowConfigPage.clickSaveChangesBtn();
	}

	@When("^user enters alphanumeric text in Disposition Code textbox$")
	public void user_enters_alphanumeric_text_in_Disposition_Code_textbox() {
		workflowConfigPage.enterTextInDispositionCodeTextBox();
	}

	@When("^user clicks on Save Changes button on Disposition pop up$")
	public void user_clicks_on_Save_Changes_button_on_Disposition_pop_up() {
		workflowConfigPage.clickSaveChangesBtn();
	}

	@Then("^user should able to view info message \"([^\"]*)\"$")
	public void user_should_able_to_view_info_message(String expectedDispositionNameMessage) {
		Assert.assertTrue("Disposition Name Error Message does not match ",
				workflowConfigPage.getErrorMsgOnDispositionPopup().contains(expectedDispositionNameMessage));
	}

	@When("^user select \"([^\"]*)\" value from Next Disposition By drop down, other than --Select one-- option$")
	public void user_select_something_value_from_next_disposition_by_drop_down_other_than_select_one_option(
			String expectedDrpDownValue) {
		nextDispositionByDropdownValue = expectedDrpDownValue;
		workflowConfigPage.selectNextDispositionFromDropdown(expectedDrpDownValue);
	}

	@Then("^user should be able to view selected value in Next Disposition By drop down$")
	public void user_should_be_able_to_view_selected_value_in_Next_Disposition_By_drop_down() {
		Assert.assertTrue(" User is not able to view the Dropdown selected values ",
				workflowConfigPage.isSelectedValueInNextDispositionByVisible(nextDispositionByDropdownValue));
	}

	@Then("^user should be able to view selected value in Disposition Status drop down$")
	public void user_should_be_able_to_view_selected_value_in_Disposition_Status_drop_down() {
		Assert.assertTrue("User is not able to view Selected Value in Disposition Status drop down ",
				defaultHandOffPage.isSelectedValueInDispositionStatusVisible(DefaultHandoffStepDef.dispositionStatus));
	}

	@Then("^user enters notes under Predefined Note textarea$")
	public void user_enters_notes_under_Predefined_Note_textarea() {
		workflowConfigPage.enterAndGetDispositionNotes();
	}

	@Then("^user should no longer be able to view Add New Disposition pop-up window$")
	public void user_should_no_longer_be_able_to_view_Add_New_Disposition_pop_up_window() {
		Assert.assertFalse("Add New Disposition Popup is visible ",
				workflowConfigPage.isAddNewDispositionPopupVisible());
	}

	@When("^user clicks on Details link button adjacent to newly created Disposition Name$")
	public void user_clicks_on_Details_link_button_adjacent_to_newly_created_Disposition_Name() {
		workflowConfigPage.clickOnDispositionDetailsLink();
	}

	@And("^user runs the Add Disposition Detail query \"([^\"]*)\"$")
	public void user_runs_the_add_disposition_detail_query_something(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				String firstName = DatabaseConn.resultSet.getString("FirstName");
				String lastName = DatabaseConn.resultSet.getString("LastName");
				createdBy = firstName.concat(" " + lastName);
				createdDate = DatabaseConn.resultSet.getString("CreatedDate");
			}

		} catch (SQLException exception) {
			Assert.assertTrue("RecepientName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view same value in Created Date and CreatedBy columns on UI as in SQL result$")
	public void user_should_be_able_to_view_same_value_in_Created_Date_and_CreatedBy_columns_on_UI_as_in_SQL_result()
			throws ParseException {
		Assert.assertTrue(
				"Created Date from Database " + financialInfoSteps.formatDbDateFieldWithDateTime(createdDate)
						+ " does not match with the UI " + workflowConfigPage.getCreatedDateFieldValue(),
				financialInfoSteps.formatDbDateFieldWithDateTime(createdDate)
						.equals(workflowConfigPage.getCreatedDateFieldValue()));
		Assert.assertTrue(
				"CreatedBy from the Database " + createdBy + " does not match with the UI"
						+ workflowConfigPage.getCreatedByFieldValue(),
				createdBy.equals(workflowConfigPage.getCreatedByFieldValue()));
	}

	@When("^user clicks on \\+Add Recipient button under choose recipient$")
	public void user_clicks_on_Add_Recipient_button_under_choose_recipient() {
		workflowConfigPage.clickAddRecipientButton();
	}

	@Then("^user should be able to view Add Recipient pop up with controls$")
	public void user_should_be_able_to_view_Add_Recipient_pop_up_with_controls(DataTable recipientControls) {
		List<String> recipientControlHeaders = recipientControls.asList(String.class);
		Assert.assertTrue("Labels do not match on the Recipient popup",
				workflowConfigPage.getListOfAddRecipientLabels().equals(recipientControlHeaders));
	}

	@Then("^user should able to view following options on Recipient popup$")
	public void user_should_able_to_view_following_options_on_Recipient_popup(DataTable recipientOptions) {
		List<String> recipientControlButtons = recipientOptions.asList(String.class);
		Assert.assertTrue("Options do not match on the Recipient Popup",
				workflowConfigPage.getListOfAddRecipientButtons().equals(recipientControlButtons));
	}

	@When("^user clicks on Close button on Add Recipient popup$")
	public void user_clicks_on_Close_button_on_Add_Recipient_popup() {
		workflowConfigPage.clickCloseButtonOnAddRecipient();
	}

	@When("^user enters text in Recipient Name textbox on Recipient popup$")
	public void user_enters_text_in_Recipient_Name_textbox_on_Recipient_popup() {
		recipientName = defaultHandOffPage.enterRecipientNameTextBox();
	}

	@When("^user enters text in Recipient Description textbox like \"([^\"]*)\"$")
	public void user_enters_text_in_Recipient_Description_textbox_like(String recipientDescription) {
		recipientDesc = defaultHandOffPage.enterRecipientDescriptionTextBox(recipientDescription);
	}

	@Then("^user should be able to view newly created Recipient in Choose Recipient grid with correct data$")
	public void user_should_be_able_to_view_newly_created_Recipient_in_Choose_Recipient_grid_with_correct_data() {
		Assert.assertTrue("Recipient Name not displayed in the table as added",
				workflowConfigPage.isAddedRecipientNameVisible(recipientName));
		Assert.assertTrue("Recipient Description not displayed in the table as added",
				workflowConfigPage.isAddedRecipientDescVisible(recipientDesc));
	}

	@Then("^user should be able to view newly created Recipient name in Workflow Summary breadcrumb just after Handoff type$")
	public void user_should_be_able_to_view_newly_created_Recipient_name_in_Workflow_Summary_breadcrumb_just_after_Handoff_type() {
		workflowConfigPage.clickOnRadioBtnAgnstFetchedRecipient(recipientName);
		Assert.assertTrue("Recipient tab: Recipient Name not displayed in the breadcrumb",
				defaultHandOffPage.getTextBreadcrumb().contains(recipientName));
	}

	@When("^user clicks on Details link button adjacent to newly created Recipient$")
	public void user_clicks_on_Details_link_button_adjacent_to_newly_created_Recipient() {
		workflowConfigPage.clickOnDetailsOfSpecificRecipient(recipientName);
	}

	@When("^user executes the query to fetch added recipient (.*)$")
	public void user_executes_the_query_to_fetch_added_recipient(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), recipientName));
		try {
			while (DatabaseConn.resultSet.next()) {
				createdBy = DatabaseConn.resultSet.getString("DisplayName");
				createdDate = DatabaseConn.resultSet.getString("CreatedDate");
			}
		} catch (SQLException exception) {
			Assert.assertTrue(
					"Created By or Created date is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view same value in following columns on Recepient Tab as in SQL result$")
	public void user_should_be_able_to_view_same_value_in_following_columns_on_Recepient_Tab_as_in_SQL_result()
			throws ParseException {
		Assert.assertTrue("Created by of Recipient does not match with DB",
				createdBy.contains(workflowConfigPage.getCreatedByRecipientText()));
		Assert.assertTrue("Created date of Recipient does not match with DB", workflowConfigSteps
				.formatDbDateFieldWithDateTime(createdDate).equals(workflowConfigPage.getCreatedDateRecipientText()));
	}

	@When("^user clicks on Continue button on HandOff Tab$")
	public void user_clicks_on_Continue_button_on_HandOff_Tab() {
		workflowConfigPage.clickOnContinueBtnOnHandoffTab();
	}

	@When("^user clicks on Edit link button against any recipient$")
	public void user_clicks_on_Edit_link_button_against_any_recipient() {
		workflowConfigPage.clickFirstEditIconOnRecipientTab();
	}

	@Then("^user should be able to view Edit Recipient pop up with controls$")
	public void user_should_be_able_to_view_Edit_Recipient_pop_up_with_controls(DataTable recipientControls) {
		List<String> recipientControlHeaders = recipientControls.asList(String.class);
		Assert.assertTrue("Labels do not match on the Edit Recipient popup",
				workflowConfigPage.getListOfEditRecipientLabels().equals(recipientControlHeaders));
	}

	@Then("^user should able to view following button on Edit Recipient popup \"([^\"]*)\"$")
	public void user_should_able_to_view_following_button_on_Edit_Recipient_popup(String saveRecipientBtn) {
		Assert.assertTrue("Save Recipient button doesnt appear on Recipient popup",
				workflowConfigPage.getSaveRecipientButtonText().equals(saveRecipientBtn));
	}

	@Then("^user should be able to view prepopulated values in all controls under Edit Recipient popup$")
	public void user_should_be_able_to_view_prepopulated_values_in_all_controls_under_Edit_Recipient_popup() {
		Assert.assertTrue("Recipient Name textbox is empty",
				workflowConfigPage.verifyEditRecipientPrePopulatedFields());
	}

	@When("^user clicks on Recipient Name or Recipient Description and updates the existing information$")
	public void user_clicks_on_Recipient_Name_or_Recipient_Description_and_updates_the_existing_information() {
		updatedRecipientDesc = workflowConfigPage.enterAndGetRandomRecipientDescText();
	}

	@When("^user clicks on Save Recipient button$")
	public void user_clicks_on_Save_Recipient_button() {
		workflowConfigPage.clickSaveRecipientButton();
	}

	@Then("^user should be able to view updated values related to edited recipient in Choose Recipient grid$")
	public void user_should_be_able_to_view_updated_values_related_to_edited_recipient_in_Choose_Recipient_grid() {
		Assert.assertTrue("Recipient description is not updated",
				workflowConfigPage.getFirstRecipientDesc().equals(updatedRecipientDesc.trim()));
	}

	@When("^user clicks on Details link button adjacent to updated Recipient$")
	public void user_clicks_on_Details_link_button_adjacent_to_updated_Recipient() {
		workflowConfigPage.clickFirstRecipientDetailsLink();
	}

	@When("^user executes the query to fetch edited recipient information (.+)$")
	public void user_executes_the_query_to_fetch_edited_recipient_information(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), updatedRecipientDesc));
		try {
			while (DatabaseConn.resultSet.next()) {
				updatedBy = DatabaseConn.resultSet.getString("DisplayName");
				updatedDate = DatabaseConn.resultSet.getString("UpdatedDate");
			}			
		} catch (SQLException exception) {
			Assert.assertTrue(
					"Updated By or Updated Date is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view Updated By and Updated Date details of Edited Recipient on UI as in SQL result$")
	public void user_should_be_able_to_view_Updated_By_and_Updated_Date_details_of_Edited_Recipient_on_UI_as_in_SQL_result()
			throws ParseException {
		Assert.assertTrue("Updated date for recipient does not match with DB", workflowConfigSteps
				.formatDbDateFieldWithDateTime(updatedDate).equals(workflowConfigPage.getUpdatedDateFieldValue()));
		Assert.assertTrue("Updated by for recipient does not match with DB",
				updatedBy.contains(workflowConfigPage.getUpdatedByFieldValue()));
	}
}