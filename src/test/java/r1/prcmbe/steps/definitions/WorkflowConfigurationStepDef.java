package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
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
import r1.prcmbe.pages.DefaultHandoffPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.WorkflowConfigurationPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.LoginSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;
import r1.prcmbe.serenity.steps.WorkflowConfigurationSteps;

public class WorkflowConfigurationStepDef extends PageObject {

	private final String BLUECOLORRGBCODE = "rgba(61, 100, 168, 1)";
	NavigationPage navPage;
	SettingsPage settingsPage;
	WorkflowConfigurationPage workflowConfigPage;
	DefaultHandoffPage defaultHandOffPage;
	CommonMethods commonMethods;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;
	AccountActionHistoryPage accActionHistoryPage;

	@Steps
	FinancialInfoSteps financialInfoSteps;
	@Steps
	WorkflowConfigurationSteps workflowConfigSteps;
	@Steps
	LoginSteps loginSteps;
	@Steps
	SearchPageSteps searchPageSteps;

	String dbFileName = "WorkFlowConfiguration", dbHandOffName, dbRecipientName, defaultRecipientName,
			recipientNameOtherThanDefault, dispositionNotes, workflowName, respondDeadline, updatedBy, updatedDate,
			successMsg, recipientName, recipientDesc, createdBy, createdDate, nextDispositionByDropdownValue,
			dispositionStatusByDropdownValue, dispositionCodeFromTextBox, updatedRecipientDesc,
			respondDeadlineOnEditDispositionPopUp, actionName, invoiceNumber, notesLabel, encounterId;

	List<String> listDBDispositionCode = new ArrayList<String>();
	int dbWorkFlowTypeId, dbWorkflowSubTypeId;
	List<String> actionIDList = new ArrayList<>();
	List<String> dispositionNamesList = new ArrayList<>();
	List<String> invoiceNumberList = new ArrayList<>();

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

	@When("^user should be able to view grid with columns headers$")
	public void user_should_be_able_to_view_grid_with_columns_headers(DataTable expectedColumnHeaders) {
		List<String> recipientColumnLabels = expectedColumnHeaders.asList(String.class);
		Assert.assertTrue(" User is not able to view column headers",
				workflowConfigPage.getSopHeaderList().containsAll(recipientColumnLabels));
	}

	@When("^user should be able to view Edit icon button adjacent to Recipient and Radio button checked against first Recipient$")
	public void user_should_be_able_to_view_edit_icon_button_adjacent_to_recipient_and_radio_button_checked_against_first_recipient() {
		Assert.assertTrue("Edit Icon is not visible against Recipient Name ",
				workflowConfigPage.isEditIconOnRecipientTabVisible());
		Assert.assertTrue("Default radio button is not checked against first Recipient",
				workflowConfigPage.isFirstRecipientBtnSelected().equalsIgnoreCase("true"));
	}

	@When("^user should be able to view Details link button for respective Recipient$")
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

	@When("^user clicks on Continue button on Recipient tab and verify Action Name exists$")
	public void user_clicks_on_continue_button_on_recipient_tab_and_verify_action_name_exists() {
		workflowConfigPage.clickOnContinueRecipientTabHavingActionNames();
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

	@When("^user can see Save changes button on the Disposition popup$")
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
		dispositionNotes = workflowConfigPage.enterAndGetDispositionNotes();
	}

	@Then("^user should no longer be able to view Add New Disposition pop-up window$")
	public void user_should_no_longer_be_able_to_view_Add_New_Disposition_pop_up_window() {
		Assert.assertFalse("Add New Disposition Popup is visible ",
				workflowConfigPage.isAddNewDispositionPopupVisible());
	}

	@When("^user clicks on Details link button adjacent to newly created Disposition Name$")
	public void user_clicks_on_Details_link_button_adjacent_to_newly_created_Disposition_Name() {
		workflowConfigPage.clickOnNewlyDispositionDetailsLink();
	}

	@When("^user runs the Add Disposition Detail query \"([^\"]*)\"$")
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
				workflowConfigSteps.formatDbDateFieldWithDateTime(createdDate)
						.equals(workflowConfigPage.getCreatedDateFieldValue()));
		Assert.assertTrue(
				"CreatedBy from the Database " + createdBy + " does not match with the UI"
						+ workflowConfigPage.getCreatedByFieldValue(),
				createdBy.equals(workflowConfigPage.getCreatedByFieldValue()));
	}

	@When("^user clicks on Close icon at top corner of the right hand side$")
	public void user_clicks_on_close_icon_at_top_corner_of_the_right_hand_side() {
		workflowConfigPage.clickOnCloseBtnOnDispositionPopup();
	}

	@When("^user clicks on Edit link button adjacent to associated Disposition Type$")
	public void user_clicks_on_Edit_link_button_adjacent_to_associated_Disposition_Type() {
		workflowConfigPage.clickOnEditLinkOnDispositionGrid();
	}

	@Then("^user should be able to view Edit Disposition pop up window with controls$")
	public void user_should_be_able_to_view_Edit_Disposition_pop_up_window_with_controls(DataTable popupControls) {
		List<String> dispositionPopupLabels = popupControls.asList(String.class);
		Assert.assertTrue("User is not able to see Edit Disposition pop up window with controls",
				workflowConfigPage.getListOfLabelsOnDispositionPopup().containsAll(dispositionPopupLabels));
	}

	@Then("^user should be able to view pre-populated values in all controls$")
	public void user_should_be_able_to_view_pre_populated_values_in_all_controls() {
		List<Object> listOfVal = workflowConfigPage.verifyEditDispositionPopupPrePopulated();
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue(
				"Controls not prepopoulated on Edit Disposition popup\n" + listOfVal.subList(0, listOfVal.size() - 1),
				val);
	}

	@Then("^Edit New Disposition window should be closed$")
	public void edit_new_disposition_window_should_be_closed() {
		Assert.assertFalse("Edit New Disposition Popup is visible ",
				workflowConfigPage.isAddNewDispositionPopupVisible());
	}

	@When("^user copies the Disposition code by clicking and dragging the mouse through entire text$")
	public void user_copies_the_disposition_code_by_clicking_and_dragging_the_mouse_through_entire_text() {
		dispositionCodeFromTextBox = workflowConfigPage.getDispositionCodeFromTextBox();
	}

	@When("^user clicks on Action Type tab again$")
	public void user_clicks_on_action_type_tab_again() {
		workflowConfigPage.clickOnActionType();
	}

	@When("^user Chooses some other action other than the one chosen above$")
	public void user_chooses_some_other_action_other_than_the_one_chosen_above() {
		workflowConfigPage.clickOnAnyActionTypeRadioBtn();
	}

	@Then("^user should be able to view validation message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_validation_message_something(String errorMessage) {
		Assert.assertTrue("User is not able to see validation message ",
				workflowConfigPage.getDispositionErrorMsgOnDuplicateCode().contains(errorMessage));
	}

	@When("^user clicks on Edit button against any listed disposition type$")
	public void user_clicks_on_edit_button_against_any_listed_disposition_type() {
		workflowConfigPage.clickOnEditLinkOnDispositionGrid();
	}

	@When("^user copies the same disposition code fetched in above step belonging to some different action$")
	public void user_copies_the_same_disposition_code_fetched_in_above_step_belonging_to_some_different_action() {
		workflowConfigPage.enterPreviousDispositionCode(dispositionCodeFromTextBox);
	}

	@When("^user updates the Disposition Code as unique Alphanumeric value other than those fetched by running query \"([^\"]*)\"$")
	public void user_updates_the_disposition_code_as_unique_alphanumeric_value_other_than_those_fetched_by_running_query_something(
			String queryName) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				listDBDispositionCode.add(DatabaseConn.resultSet.getString("code"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("Code is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		int dispositionCodeCounter = CommonMethods.getRandom(listDBDispositionCode.size());
		String dispositionCodeNumber = listDBDispositionCode.get(dispositionCodeCounter);
		workflowConfigPage
				.enterPreviousDispositionCode(dispositionCodeNumber.concat(RandomStringUtils.randomAlphabetic(3)));
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

	@When("^user run the query to fetch recipient name (.*)$")
	public void user_run_the_query_to_fetch_recipient_name(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbRecipientName = DatabaseConn.resultSet.getString("Name");
			}

		} catch (SQLException exception) {
			Assert.assertTrue("RecepientName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user clicks on radio button against any fetched Recipient$")
	public void user_clicks_on_radio_button_against_any_fetched_Recipient() {
		workflowConfigPage.clickSpecificRecipientRadioBtn(dbRecipientName);
	}

	@Then("^user should be able to view Add New Action pop up window with controls$")
	public void user_should_be_able_to_view_Add_New_Action_pop_up_window_with_controls(DataTable controls) {
		List<String> expectedActionPopUpControls = controls.asList(String.class);
		List<Object> listOfVal = workflowConfigPage.verifyAddActionPopupControlsVisible(expectedActionPopUpControls);
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Controls not visible on Add Action popup\n" + listOfVal.subList(0, listOfVal.size() - 1),
				val);
	}

	@When("^user clicks on Required checkbox$")
	public void user_clicks_on_Required_checkbox() {
		workflowConfigPage.clickRequiredCheckBoxOnActionPopup();
	}

	@Then("^user should no longer be able to view Add New Action pop-up window$")
	public void user_should_no_longer_be_able_to_view_Add_New_Action_pop_up_window() {
		Assert.assertFalse("Add action popup is not closed", workflowConfigPage.isAddActionPopupVisible());
	}

	@When("^user clicks on Details link button adjacent to newly created Action Name$")
	public void user_clicks_on_Details_link_button_adjacent_to_newly_created_Action_Name() {
		workflowConfigPage.clickSpecificDetailsLinkOnActionTab(DefaultHandoffStepDef.actionName);
	}

	@When("^user run the query to fetch Action Details (.*)$")
	public void user_run_the_query_to_fetch_Action_Details(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
	}

	@Then("^user should be able to view same value in details columns on UI as in SQL result$")
	public void user_should_be_able_to_view_same_value_in_details_columns_on_UI_as_in_SQL_result()
			throws SQLException, ParseException {
		while (DatabaseConn.resultSet.next()) {
			String firstName = DatabaseConn.resultSet.getString("FirstName");
			String lastName = DatabaseConn.resultSet.getString("LastName");
			createdBy = firstName.concat(" " + lastName);
			createdDate = DatabaseConn.resultSet.getString("CreatedDate");
			updatedDate = (String) DatabaseConn.resultSet.getObject("UpdatedDate");
			updatedBy = (String) DatabaseConn.resultSet.getObject("UpdatedBy");
			if (updatedDate == null) {
				updatedDate = " ";
			}
			if (updatedBy == null) {
				updatedBy = "";
			}
		}
		Assert.assertTrue("Created date does not match", workflowConfigPage.getActionCreatedDate()
				.equals(workflowConfigSteps.formatDbDateFieldWithDateTime(createdDate)));
		Assert.assertTrue("Created by does not match", workflowConfigPage.getActionCreatedBy().equals(createdBy));
		Assert.assertTrue("Updated date does not match", workflowConfigPage.getActionUpdatedDate().equals(updatedDate));
		Assert.assertTrue("Updated by does not match", workflowConfigPage.getActionUpdatedBy().equals(updatedBy));
	}

	@Then("^user should able to view info message on action popup \"([^\"]*)\"$")
	public void user_should_able_to_view_info_message_on_action_popup(String expectedMessage) {
		Assert.assertTrue("Action popup Error Message does not match ",
				workflowConfigPage.getErrorMsgOnActionPopup().contains(expectedMessage));
	}

	@Then("^user should be able to view newly created Action in Choose Action Type grid$")
	public void user_should_able_to_view_newly_created_Action_in_Choose_Action_Type_grid() {
		Assert.assertTrue("New Action Name not as per the value entered by user",
				workflowConfigPage.isNewlyAddedActionVisibleInGrid(DefaultHandoffStepDef.actionName));
	}

	@Then("^user should be able to navigate to Disposition type page$")
	public void user_should_be_able_to_navigate_to_Disposition_type_page() {
		workflowConfigPage.isDispositionTabVisible();
	}

	@Then("^user should be able to view Disposition Type tab selected highlighted in blue color$")
	public void user_should_be_able_to_view_Disposition_Type_tab_selected_highlighted_in_blue_color() {
		Assert.assertTrue("Disposition tab color is not Blue",
				workflowConfigPage.getDispositionTabColor().equals(BLUECOLORRGBCODE));
	}

	@Then("^user should be able to view Choose a Disposition Type grid with buttons underneath$")
	public void user_should_be_able_to_view_Choose_a_Disposition_Type_grid_with_buttons_underneath(
			DataTable dispositionButtons) {
		List<String> dispositionBtnText = dispositionButtons.asList(String.class);
		Assert.assertTrue("Buttons on Disposition type grid not displayed",
				workflowConfigPage.getDispositionButtonText().equals(dispositionBtnText));
	}

	@Then("^user should be able to view Save Configuration button disabled$")
	public void user_should_be_able_to_view_Save_Configuration_button_disabled() {
		Assert.assertTrue("Save configuration button is not disabled",
				workflowConfigPage.isSaveConfigBtnOnDispositionTabDisabled());
	}

	@Then("^user should be able to view Edit link button adjacent to associated Disposition Type$")
	public void user_should_be_able_to_view_Edit_link_button_adjacent_to_associated_Disposition_Type() {
		Assert.assertTrue("Edit link button is not displayed adjacent to each disposition type",
				workflowConfigPage.getDispositionNameCount() == workflowConfigPage.getDispositionEditLinksCount());
	}

	@Then("^user should be able to view Details button for particular Disposition Type$")
	public void user_should_be_able_to_view_Details_button_for_particular_Disposition_Type() {
		Assert.assertTrue("Details link button is not displayed adjacent to each disposition type",
				workflowConfigPage.getDispositionNameCount() == workflowConfigPage.getDispositionDetailsLinkCount());
	}

	@Then("^user should be able to view Reorder link button against each Disposition Type$")
	public void user_should_be_able_to_view_Reorder_link_button_against_each_Disposition_Type() {
		Assert.assertTrue("Details link button is not displayed adjacent to each disposition type",
				workflowConfigPage.getDispositionNameCount() == workflowConfigPage.getDispositionReorderLinksCount());
	}

	@When("^user clicks on Details link button adjacent to any Disposition Type$")
	public void user_clicks_on_Details_link_button_adjacent_to_any_Disposition_Type() {
		workflowConfigPage.clickFirstDispositionDetailsLink();
	}

	@Then("^user should be able to view detailed columns$")
	public void user_should_be_able_to_view_detailed_columns(DataTable columnNames) {
		List<String> detailsColumns = columnNames.asList(String.class);
		Assert.assertTrue(
				"Expected Details columns not visible \n Actual"
						+ workflowConfigPage.getDispositionDetailsColumnNamesList() + "Expected " + detailsColumns,
				workflowConfigPage.getDispositionDetailsColumnNamesList().equals(detailsColumns));
	}

	@When("^user clicks on Details link again$")
	public void user_clicks_on_Details_link_again() {
		workflowConfigPage.clickExpandedDetailsLinkOnDispositionTab();
	}

	@Then("^expanded grid for selected Disposition Type gets collapsed$")
	public void expanded_grid_for_selected_Disposition_Type_gets_collapsed() {
		Assert.assertTrue("Expanded details section is not collapsed",
				workflowConfigPage.isDispositionDetailsCollapsed());
	}

	@Then("^user should no longer be able to view the associated fields$")
	public void user_should_no_longer_be_able_to_view_the_associated_fields() {
		Assert.assertFalse("Details section should not be displayed",
				workflowConfigPage.isDispositionDetailsSectionVisible());
	}

	@Then("^user should be able to view disposition grid with columns headers$")
	public void user_should_be_able_to_view_disposition_grid_with_columns_headers(DataTable expectedColumnHeaders) {
		List<String> dispositionGridColumnLabels = expectedColumnHeaders.asList(String.class);
		Assert.assertTrue(" User is not able to view column headers",
				workflowConfigPage.getDispositionGridHeaderList().containsAll(dispositionGridColumnLabels));
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

	@When("^user clicks on any of the field Textboxes, Dropdowns or Textarea on Edit Disposition PopUp$")
	public void user_clicks_on_any_of_the_field_Textboxes_Dropdowns_or_Textarea_on_Edit_Disposition_PopUp() {
		workflowConfigPage.clickRespondDeadlineOnEditDispositionTypePopup();
	}

	@When("^user updates the existing information in either of these fields on Edit Disposition PopUp$")
	public void user_updates_the_existing_information_in_either_of_these_fields_on_Edit_Disposition_PopUp() {
		respondDeadlineOnEditDispositionPopUp = workflowConfigPage
				.enterAndGetRandomValueRespondDeadlineForEditDispositionTypePopup();
	}

	@Then("^user should be able to view updated values related to Edit Disposition Type in Choose a Disposition Type grid$")
	public void user_should_be_able_to_view_updated_values_related_to_Edit_Disposition_Type_in_Choose_a_Disposition_Type_grid() {
		Assert.assertTrue("The updated time limit value is not matching in Disposition Grid",
				workflowConfigPage.getMappedDispositionTimeLimitValueOnDispositionTypeGrid()
						.equals(respondDeadlineOnEditDispositionPopUp));
	}

	@When("^user clicks on Details link button adjacent to updated Disposition Type$")
	public void user_clicks_on_Details_link_button_adjacent_to_updated_Disposition_Type() {
		workflowConfigPage.clickOnDetailsLinkOnDispositionTab();
	}

	@When("^user run the query to fetch edit disposition Detail (.+)$")
	public void user_run_the_query_to_fetch_edit_disposition_detail(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				String firstName = DatabaseConn.resultSet.getString("FirstName");
				String lastName = DatabaseConn.resultSet.getString("LastName");
				updatedBy = firstName.concat(" " + lastName);
				updatedDate = DatabaseConn.resultSet.getString("UpdatedDate");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("RecepientName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@Then("^user should be able to view same value in Updated Date and Updated By columns on UI as in SQL result$")
	public void user_should_be_able_to_view_same_value_in_Updated_Date_and_Updated_By_columns_on_UI_as_in_SQL_result()
			throws ParseException {
		Assert.assertTrue("Updated date does not match with DB", workflowConfigSteps
				.formatDbDateFieldWithDateTime(updatedDate).equals(workflowConfigPage.getUpdatedDateFieldValue()));
		Assert.assertTrue("Updated by does not match with DB",
				updatedBy.contains(workflowConfigPage.getUpdatedByFieldValue()));
	}

	@When("^user clicks on Edit link button adjacent to particular Action Type$")
	public void user_clicks_on_edit_link_button_adjacent_to_particular_action_type() {
		workflowConfigPage.clickFirstEditLinkOnActionTypeTab();
	}

	@Then("^user should be able to able to navigate to Action Type tab$")
	public void user_should_be_able_to_able_to_navigate_to_action_type_tab() {
		Assert.assertTrue("User is not able to navigate to Action type", workflowConfigPage.isActionTabVisible());
	}

	@When("^user should be able to view Action Type tab selected highlighted in blue color$")
	public void user_should_be_able_to_view_action_type_tab_selected_highlighted_in_blue_color() {
		Assert.assertTrue("Handoff tab color is not Blue",
				workflowConfigPage.getActionTypeTabColor().equals(BLUECOLORRGBCODE));
	}

	@When("^user should able to view Workflow Summary label with selected Action Type appended$")
	public void user_should_able_to_view_workflow_summary_label_with_selected_action_type_appended() {
		String actionNameSelected = workflowConfigPage.getSelectedActionTypeName();
		Assert.assertTrue("Action Typ tab: ActionName not displayed in the crumb",
				workflowConfigPage.getActionTextBreadcrumb().contains(actionNameSelected));
	}

	@When("^user should be able to view \\+Add Action button$")
	public void user_should_be_able_to_view_add_action_button() {
		Assert.assertTrue("+Add new Action Button is not visible ", defaultHandOffPage.isAddNewActionBtnVisisble());
	}

	@When("^user should be able to view Continue > Action button$")
	public void user_should_be_able_to_view_continue_action_button() {
		Assert.assertTrue(" Continue button on Action Type is not visible ",
				workflowConfigPage.isContinueBtnOnActionTypeVisible());
	}

	@When("^user should able to view grid with columns headers$")
	public void user_should_able_to_view_grid_with_columns_headers(DataTable popupControls) {
		List<String> actionTypeHeaders = popupControls.asList(String.class);
		Assert.assertTrue("User is not able to see headers on Action type  with controls",
				workflowConfigPage.getActionTypeHeaders().containsAll(actionTypeHeaders));
	}

	@When("^user should be able to view Edit link button adjacent to associated Action Type$")
	public void user_should_be_able_to_view_edit_link_button_adjacent_to_associated_action_type() {
		Assert.assertTrue("User is not able to view Edit link ", workflowConfigPage.isEditLinkOnActionTypeVisible());
	}

	@When("^user should be able to view Radio button adjacent to Action Type for selecting any Action$")
	public void user_should_be_able_to_view_radio_button_adjacent_to_action_type_for_selecting_any_action() {
		Assert.assertTrue("Radio button is not visible ", workflowConfigPage.isRadioBtnOnActionTypeVisible());
	}

	@When("^user should be able to view Details link for particular Action Type$")
	public void user_should_be_able_to_view_details_link_for_particular_action_type() {
		Assert.assertTrue("Details link not visible ", workflowConfigPage.isDetailLinkOnActionTypeVisible());
	}

	@When("^user should be able to view Reorder link button against each Action Type$")
	public void user_should_be_able_to_view_reorder_link_button_against_each_action_type() {
		Assert.assertTrue("Reorder link is not visible ", workflowConfigPage.isReorderOnActionTypeVisible());
	}

	@When("^user copies the Action Name by clicking and dragging the mouse through entire text$")
	public void user_copies_the_action_name_by_clicking_and_dragging_the_mouse_through_entire_text() {
		actionName = defaultHandOffPage.getActionNameFromTextBox();
	}

	@When("^user clicks on close button on Action popup$")
	public void user_clicks_on_close_button_on_action_popup() {
		workflowConfigPage.clickOnCloseBtnOnActionPopup();
	}

	@When("^user enters same action name copied in previous step in Action Name textbox$")
	public void user_enters_same_action_name_copied_in_previous_step_in_Action_Name_textbox() {
		defaultHandOffPage.enterCopiedActionName(actionName);
	}

	@When("^user clicks on Hand off tab$")
	public void user_clicks_on_Hand_off_tab() {
		workflowConfigPage.clickHandoffTab();
	}

	@When("^user selects any Hand off other than selected above$")
	public void user_selects_any_Hand_off_other_than_selected_above() {
		workflowConfigPage.clickOnRandomHandoffType();
	}

	@Then("^user should be able to view error message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_error_message_something(String actionNameErrorMsg) {
		Assert.assertTrue("ExpectedAction Name Error message is not displayed",
				workflowConfigPage.getErrMsgOnDuplicateActionName().contains(actionNameErrorMsg));
	}

	@When("^user run the query by passing selected Handoff name (.*)$")
	public void user_run_the_query_by_passing_selected_Handoff_name(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbHandOffName));
	}

	@When("^user run the query by passing selected Recipient name (.*)$")
	public void user_run_the_query_by_passing_selected_Recipient_name(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbRecipientName));
	}

	@Then("^user should be able to fetch the Workflowtypeid and SubTypeID for respective Handoff type and associated Recipient$")
	public void user_should_be_able_to_fetch_the_Workflowtypeid_and_SubTypeID_for_respective_Handoff_type_and_associated_Recipient() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbWorkFlowTypeId = DatabaseConn.resultSet.getInt("WorkFlowTypeID");
				dbWorkflowSubTypeId = DatabaseConn.resultSet.getInt("SubTypeID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("WorkFlowTypeId and WorkFlowSubTypeId is not fetched from DB.\nThe Technical Error is:\n"
					+ exception, false);
		}
	}

	@When("^user run the query by passing fetched Workflowtypeid and SubTypeID (.*)$")
	public void user_run_the_query_by_passing_fetched_Workflowtypeid_and_SubTypeID(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbWorkFlowTypeId, dbWorkflowSubTypeId));
	}

	@When("^user run the query by passing Workflowtypeid and SubTypeID to fetch ActionID (.*)$")
	public void user_run_the_query_by_passing_Workflowtypeid_and_SubTypeID_to_fetch_ActionID(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbWorkFlowTypeId, dbWorkflowSubTypeId));
	}

	@Then("^user should be able to view Number of actions in db as reflected in UI$")
	public void user_should_be_able_to_view_Number_of_actions_in_db_as_reflected_in_UI() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			actionIDList.add(DatabaseConn.resultSet.getString("ActionId"));
		}
		Assert.assertTrue("The number of actions in DB and UI does not match",
				actionIDList.size() == workflowConfigPage.getActionNamesCount());
	}

	@When("^user run the query by passing fetched ActionId (.*)$")
	public void user_run_the_query_by_passing_fetched_ActionId(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), actionIDList.get(0)));
	}

	@Then("^user should be able to view same action in db as reflected on UI$")
	public void user_should_be_able_to_view_same_action_in_db_as_reflected_on_UI() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			actionName = DatabaseConn.resultSet.getString("Name");
		}
		Assert.assertTrue("The action name in DB and UI does not match",
				workflowConfigPage.getListOfActionNames().contains(actionName));
	}

	@When("^user selects any actions from Choose Action Type grid$")
	public void user_selects_any_actions_from_Choose_Action_Type_grid() {
		workflowConfigPage.clickSpecificRadioBtnOnActionTab(actionName);
	}

	@When("^user run the query by passing ActionId of selected Action (.*)$")
	public void user_run_the_query_by_passing_ActionId_of_selected_Action(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), actionIDList.get(0)));
		while (DatabaseConn.resultSet.next()) {
			dispositionNamesList.add(DatabaseConn.resultSet.getString("Disposition"));
		}
	}

	@Then("^user should be able to view same disposition type in DB as as reflected on UI$")
	public void user_should_be_able_to_view_same_disposition_type_in_DB_as_as_reflected_on_UI() {
		Assert.assertTrue("The disposition does not match with DB and UI",
				dispositionNamesList.equals(workflowConfigPage.getListOfDispositionNames()));
	}

	@When("^user selects newly added Handoff in Handoff Type$")
	public void user_selects_newly_added_Handoff_in_Handoff_Type() {
		workflowConfigPage.selectNewHandOffType(DefaultHandoffStepDef.workFlowName);
	}

	@When("^user selects newly added value from Create dropdown$")
	public void user_selects_newly_added_value_from_Create_dropdown() {
		workflowConfigPage.selectCreateDrpDwn(DefaultHandoffStepDef.recipientDesc);
	}

	@When("^user selects newly added value from Why dropdown$")
	public void user_selects_newly_added_value_from_Why_dropdown() {
		workflowConfigPage.selectWhyDrpDwn(DefaultHandoffStepDef.actionName);
	}

	@When("^user selects newly added value from Disposition dropdown$")
	public void user_selects_newly_added_value_from_Disposition_dropdown() {
		workflowConfigPage.selectDispostionDrpDwn(DefaultHandoffStepDef.dispositionDescription);
	}

	@When("^user clears cache in application and login again$")
	public void user_clears_cache_in_application_and_login_again() throws IOException {
		workflowConfigSteps.clearCacheAndLogin();
	}

	@Then("^user should be able to view newly added Handoff Type and associated recipients, Actions and dispositions on R(\\d+) Decision screen$")
	public void user_should_be_able_to_view_newly_added_Handoff_Type_and_associated_recipients_Actions_and_dispositions_on_R_Decision_screen(
			int arg) {
		List<String> handOffValues = new ArrayList<>();
		handOffValues.add(DefaultHandoffStepDef.workFlowName);
		handOffValues.add(DefaultHandoffStepDef.recipientDesc);
		handOffValues.add(DefaultHandoffStepDef.actionName);
		handOffValues.add(DefaultHandoffStepDef.dispositionDescription);
		Assert.assertTrue("Newly added Handoff types are not displayed on R1 decision screen",
				workflowConfigPage.getNewHandOffValuesAdded().equals(handOffValues));
	}

	@When("^user run the query and fetch encounterId \"([^\"]*)\"$")
	public void user_run_the_query_and_fetch_encounterid_something(String queryName)
			throws ClassNotFoundException, SQLException, Exception {

		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumberList.add(DatabaseConn.resultSet.getString("Invoicenumber"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("Invoice Number is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}

		int counter = CommonMethods.getRandom(invoiceNumberList.size());
		invoiceNumber = invoiceNumberList.get(counter);
	}

	@When("^user enters encounterId in visit number textfield$")
	public void user_enters_encounterid_in_visit_number_textfield() {
		loginSteps.log("Fetched Invoice Number from Database is " + invoiceNumber);
		searchPage.enterInvoiceNumber(invoiceNumber);
	}

	@When("^user clicks radio button against \"([^\"]*)\" Handoff$")
	public void user_clicks_radio_button_against_Handoff(String handOffType) {
		workflowConfigPage.clickArSupervisorRadioBtn();
	}

	@When("^user clicks on radio button against \"([^\"]*)\" Recipient$")
	public void user_clicks_on_radio_button_against_Recipient(String recipientName) {
		workflowConfigPage.clickSpecificRecipientRadioBtn(recipientName);
	}

	@When("^user clicks on radio button against \"([^\"]*)\" Action Type$")
	public void user_clicks_on_radio_button_against_Action_Type(String actionType) {
		workflowConfigPage.clickSpecificActionTypeRadioBtn(actionType);
	}

	@When("^user clicks on Edit link against \"([^\"]*)\" Disposition Type$")
	public void user_clicks_on_Edit_link_against_Disposition_Type(String dispositionType) {
		workflowConfigPage.clickSpecificEditDispositionTypeBtn(dispositionType);
	}

	@Then("^user should able to view Edit New Disposition pop up window$")
	public void user_should_able_to_view_Edit_New_Disposition_pop_up_window() {
		Assert.assertTrue("Edit disposition popup is not visible", workflowConfigPage.isEditDispositionPopupVisible());
	}

	@When("^user clicks on Save Changes button on Disposition popup$")
	public void user_clicks_on_Save_Changes_button_on_Disposition_popup() {
		workflowConfigPage.clickSaveChangesBtnOnDispositionPopUp();
	}

	@Then("^user should be able to view the same Predefined Note entered previously$")
	public void user_should_be_able_to_view_the_same_Predefined_Note_entered_previously() {
		Assert.assertTrue("Predefined notes not same as previously entered",
				dispositionNotes.equals(workflowConfigPage.getDispositionNotes()));
	}

	@When("^user clicks on Close icon at Top right hand corner of the screen$")
	public void user_clicks_on_Close_icon_at_Top_right_hand_corner_of_the_screen() {
		workflowConfigPage.clickDispositionPopupClose();
	}

	@Then("^Edit New Disposition pop-up window should be closed$")
	public void edit_New_Disposition_pop_up_window_should_be_closed() {
		Assert.assertFalse("Edit disposition popup is not closed", workflowConfigPage.isEditDispositionPopupVisible());
	}

	@Then("^user should be able to view message on Disposition page \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_on_Disposition_page(String successMessage) {
		Assert.assertTrue("Success message is not displayed",
				workflowConfigPage.getSuccessMessage().equals(successMessage));
	}

	@When("^user clicks on radio button adjacent to associated Recipient fetched from above query$")
	public void user_clicks_on_radio_button_adjacent_to_associated_Recipient_fetched_from_above_query() {
		workflowConfigPage.clickOnRadioBtnAgnstFetchedRecipient(dbRecipientName);
	}

	@When("^user clicks on Edit link against \"([^\"]*)\" Action Type$")
	public void user_clicks_on_Edit_link_against_Action_Type(String actionType) {
		workflowConfigPage.clickSpecificActionTypeEditLink(actionType);
	}

	@Then("user should able to view Edit Action pop up window with controls$")
	public void user_should_able_to_view_Edit_Action_pop_up_window_with_controls(DataTable popupControls) {
		List<String> listOfFields = popupControls.asList(String.class);
		List<Object> listOfVal = workflowConfigPage.verifyEditActionPopupControlsVisible(listOfFields);
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Controls not visible on Edit Action popup\n" + listOfVal.subList(0, listOfVal.size() - 1),
				val);
	}

	@Then("^user should be able to view prepopulated values in all controls of edit action popup$")
	public void user_should_be_able_to_view_prepopulated_values_in_all_controls_of_edit_action_popup() {
		List<Object> listOfVal = workflowConfigPage.verifyEditActionPopupPrePopulated();
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue(
				"Controls not prepopoulated on Edit Action popup\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}

	@When("^user clicks on any of the field Textboxes or Dropdowns$")
	public void user_clicks_on_any_of_the_field_Textboxes_or_Dropdowns() {
		workflowConfigPage.clickRespondDeadline();
	}

	@When("^user updates the existing information in either of these fields$")
	public void user_updates_the_existing_information_in_either_of_these_fields() {
		respondDeadline = workflowConfigPage.enterAndGetRandomValueRespondDeadline();
	}

	@When("^user clicks on Save Changes button$")
	public void user_clicks_on_Save_Changes_button() {
		workflowConfigPage.clickSaveChangesBtnEditActionPopup();
		successMsg = workflowConfigPage.getSuccessMessage();
	}

	@Then("^user should be able to view Action saved success message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_Action_saved_success_message(String successMessage) {
		Assert.assertTrue("Success message displayed is not as expected", successMsg.equals(successMessage));
	}

	@Then("^user should no longer be able to view Edit Action pop-up window$")
	public void user_should_no_longer_be_able_to_view_Edit_Action_pop_up_window() {
		Assert.assertFalse("Edit Action popup window not closed", workflowConfigPage.isEditActionPopupVisible());
	}

	@Then("^user should be able to view updated values related to \"([^\"]*)\" in edit Action in Choose Action grid$")
	public void user_should_be_able_to_view_updated_values_related_to_in_edit_Action_in_Choose_Action_grid(
			String actionType) {
		Assert.assertTrue("The updated time limit value is not matching in Action Grid",
				workflowConfigPage.getSpecificTimeLimitValueInActionTypeGrid(actionType).equals(respondDeadline));
	}

	@When("^user clicks on Details link against \"([^\"]*)\" Action Type$")
	public void user_clicks_on_Details_link_against_Action_Type(String actionType) {
		workflowConfigPage.clickSpecificActionTypeDetailsLink(actionType);
	}

	@When("^user runs the query to fetch recepient (.*)$")
	public void user_runs_the_query_to_fetch_recepient__WFConfig_ReorderAction(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbRecipientName = DatabaseConn.resultSet.getString("Name");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("RecepientName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user runs the query to fetch Action details (.*)$")
	public void user_runs_the_query_to_fetch_Action_details(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				updatedBy = DatabaseConn.resultSet.getString("DisplayName");
				updatedDate = DatabaseConn.resultSet.getString("LastModifiedOnDate");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("RecepientName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user selects \"([^\"]*)\" handoff from Handoff Types dropdown$")
	public void user_selects_handoff_from_Handoff_Types_dropdown(String handOffType) {
		accInfoPage.selectHandOffType(handOffType);
	}

	@When("^user selects \"([^\"]*)\" from Create dropdown$")
	public void user_selects_from_Create_dropdown(String value) {
		accInfoPage.selectValueFromCreateDrpdwn(value);
	}

	@Then("^Handoff Account modal pop-up window should be closed$")
	public void handoff_Account_modal_pop_up_window_should_be_closed() {
		Assert.assertFalse("Handofff modal pop-up is visible", accInfoPage.isHandOffPopupVisible());
	}

	@When("^user navigates to Account Action History section$")
	public void user_navigates_to_Account_Action_History_section() {
		accInfoPage.scrollToAccountActionHistory();
	}

	@When("^user clicks on Show Account Action History Notes button$")
	public void user_clicks_on_Show_Account_Action_History_Notes_button() {
		accInfoPage.clickOnShowAccountActionBtn();
	}

	@When("^user should be able to view the Predefined Note saved at the top under Handoff action related grid$")
	public void user_should_be_able_to_view_the_Predefined_Note_saved_at_the_top_under_Handoff_action_related_grid() {
		accActionHistoryPage.isNotesLabelVisible();
		notesLabel = accActionHistoryPage.getNotesLabel();
	}

	@When("^user mouse hovers event circle H under event circle in blue color for latest Handoff Action on Horizontal timeline$")
	public void user_mouse_hovers_event_circle_H_under_event_circle_in_blue_color_for_latest_Handoff_Action_on_Horizontal_timeline() {
		accActionHistoryPage.hoverOverLatestBubble();
	}

	@Then("^user should be able to view the Predefined Note on mouse hovering H event circle highlighted in blue for latest Handoff Action on Horlization timeline$")
	public void user_should_be_able_to_view_the_Predefined_Note_on_mouse_hovering_H_event_circle_highlighted_in_blue_for_latest_Handoff_Action_on_Horlization_timeline() {
		System.out.println(notesLabel);
		System.out.println(accActionHistoryPage.getPopoverTitle());
		Assert.assertTrue("Not able to view predefined note on mouse hovering H event circle",
				accActionHistoryPage.getPopoverTitle().contains(notesLabel));
	}

	@When("^user run the query \"([^\"]*)\" and fetch encounterId$")
	public void user_run_the_query_and_fetch_encounterId(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				encounterId = DatabaseConn.resultSet.getString("EncounterID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("Encounter ID is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters encounter id$")
	public void user_enter_encounter_id() {
		searchPage.enterVisitNumber(encounterId);
	}

	@Then("^user should be able to view R1 Decision Account screen$")
	public void user_should_be_able_to_view_R_Decision_Account_screen() {
		searchPageSteps.verifyEncounterId(encounterId);
		Assert.assertTrue("User not able to view R1 Decision Account screen", accInfoPage.isInvoiceNumberVisible());
	}

}