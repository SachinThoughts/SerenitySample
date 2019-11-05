package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.DefaultHandoffPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.WorkflowConfigurationPage;

public class WorkflowConfigurationStepDef extends PageObject {

	private final String BLUECOLORRGBCODE = "rgba(61, 100, 168, 1)";
	NavigationPage navPage;
	SettingsPage settingsPage;
	WorkflowConfigurationPage workflowConfigPage;
	DefaultHandoffPage defaultHandOffPage;
	CommonMethods commonMethods;
	
	String dbFileName = "WorkFlowConfiguration", dbHandOffName, dbRecipientName, defaultRecipientName,
			recipientNameOtherThanDefault, dispositionNotes, workflowName, respondDeadline, updatedBy, updatedDate,
			successMsg, recipientName, recipientDesc, createdBy, createdDate;
	
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
    public void user_run_the_query_to_fetch_handoff_id(String query1) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(query1, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbWorkFlowTypeId = DatabaseConn.resultSet.getInt("WorkflowTypeID");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("WorkflowTypeID is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		} 
        
    }

    @When("^user run the query to fetch hand-off name (.+)$")
    public void user_run_the_query_to_fetch_handoff_name(String query2) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(query2, dbFileName));
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

	@When("^user clicks on continue button$")
	public void user_clicks_on_continue_button() {
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
	@When("^user clicks on Details link button$")
	public void user_clicks_on_details_link_button() {
		workflowConfigPage.clickOnDetailsOnRecipientTab();
	}
	@Then("^user should be able to view detailed columns$")
	public void user_should_be_able_to_view_detailed_columns(DataTable expectedColumHeaders) {
		List<String> recipientColumnLabels = expectedColumHeaders.asList(String.class);
		Assert.assertTrue("User is not able to see column headers",
				workflowConfigPage.getDetailColumnHeadersRecipientTab().equals(recipientColumnLabels));
	}
}