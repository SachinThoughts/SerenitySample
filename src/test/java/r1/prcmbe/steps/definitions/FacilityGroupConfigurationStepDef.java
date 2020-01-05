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
import r1.prcmbe.pages.FacilityGroupConfigurationPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.WorkflowDistributionPage;
import r1.prcmbe.serenity.steps.FacilityGroupConfigSteps;

public class FacilityGroupConfigurationStepDef extends PageObject {
	SettingsPage settingsPage;
	FacilityGroupConfigurationPage facilityGrpConfigPage;
	CommonMethods commonMethods;
	NavigationPage navigationPage;
	WorkflowDistributionPage workflowDistributionPage;

	@Steps
	FacilityGroupConfigSteps facilityGrpConfigSteps;

	private static String dbQueryFilename = "FacilityGrpConfig";
	static String checkedFacilityGrpName;

	String facilityGroupNameFromUI = null;
	String prcmEnabledFlag, pRCMEnabledFacilityCode, nonPrcmFacilityGroupNameFromUI;

	@When("^user mouse hovers on Settings-R(\\d+)_Decision link$")
	public void user_mouse_hovers_on_Settings_R__Decision_link(int arg1) {
		settingsPage.clickOnSettingsR1Decisions();
	}

	@When("^user clicks on Facility Group Configuration link$")
	public void user_clicks_on_Facility_Group_Configuration_link() {
		settingsPage.clickFacilityGroupConfig();
	}

	@Given("^user is on Facility Group Configuration screen$")
	public void user_is_on_Facility_Group_Configuration_screen() {
		facilityGrpConfigPage.isFacilityGrpConfigHeaderVisible();
	}

	@When("^user runs the \"([^\"]*)\" query$")
	public void user_runs_the_something_query(String queryName) throws Exception {
		{
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					commonMethods.loadQuery(queryName, dbQueryFilename));
		}
	}

	@When("^user runs the facility group query\"([^\"]*)\"$")
	public void user_runs_the_facility_group_querysomething(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), facilityGroupNameFromUI));
	}

	@Then("^user should be able to view newly added column name as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_newly_added_column_name_as_something(String addedCoumnName) {
		Assert.assertTrue("Newly added Column is not displayed in DB ",
				facilityGrpConfigSteps.isNewAddedColumnVisibleInDB(addedCoumnName));
	}

	@Then("^user should be able to view header name as Facility Group Configuration$")
	public void user_should_be_able_to_view_header_name_as_facility_group_configuration() {
		Assert.assertTrue("Header name as Facility Group Configuration",
				facilityGrpConfigPage.isHeaderNameOnFacilityGrpConfigVisble());
	}

	@Then("^user should be able to view PRCM flag should be enabled having value as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_prcm_flag_should_be_enabled_having_value_as_something(String numberValue)
			throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			prcmEnabledFlag = DatabaseConn.resultSet.getString("IsPRCMEnabled");
		}
		Assert.assertTrue("PRCM flag is not enabled and having value as 1 ", prcmEnabledFlag.equals(numberValue));
	}

	@Then("^user should be able to view data in facility group column (.+)$")
	public void user_should_be_able_to_view_data_in_facility_group_column(String expectedFacilityGroupName) {
		facilityGroupNameFromUI = expectedFacilityGroupName;
		Assert.assertTrue(" Expected Facility Group is not Present ",
				facilityGrpConfigPage.isFacilityGroupNamePresent(expectedFacilityGroupName));
	}

	@Then("^user should be able to view data in facilities (.+)$")
	public void user_should_be_able_to_view_data_in_facilities(String facilities) {
		Assert.assertTrue(" Expected Facility  is not Present ",
				facilityGrpConfigPage.isExpectedFacilityPresent(facilities));
	}

	@Then("^user should be able to view column$")
	public void user_should_be_able_to_view_column(DataTable columnHeaders) {
		List<String> columnHeaderInFacilityGrpConfig = columnHeaders.asList(String.class);
		Assert.assertTrue("User is not able to see column headers",
				facilityGrpConfigPage.getTableGridHeaders().equals(columnHeaderInFacilityGrpConfig));
	}

	@Then("^user should be able to view Add New Facility Group button in top right and bottom right corner$")
	public void user_should_be_able_to_view_add_new_facility_group_button_in_top_right_and_bottom_right_corner() {
		Assert.assertTrue("User is not able to view Add New Facility Group button in top right and bottom right corner",
				facilityGrpConfigPage.areAddFAcilityBtnPresents());
	}

	@Then("^user should be able to view the Edit Link button$")
	public void user_should_be_able_to_view_the_edit_link_button() {
		Assert.assertTrue("EditButton is not visisble on the Page ", facilityGrpConfigPage.isEditBtnPresent());
	}

	@When("^user clicks on Add New Facility Group button$")
	public void user_clicks_on_add_new_facility_group_button() {
		pRCMEnabledFacilityCode = facilityGrpConfigPage.getPrcmEnabledFacilityCodes().get(1);
		facilityGrpConfigPage.clickOnAddFacilityBtn();
	}

	@Then("^user should be able to view new pop up window on clicking$")
	public void user_should_be_able_to_view_new_pop_up_window_on_clicking() {
		Assert.assertTrue(" Add new Facility Group is not visible ",
				facilityGrpConfigPage.isAddNewFacilityGroupPopupVisible());
	}

	@Then("^user should be able to view controls Facility$")
	public void user_should_be_able_to_view_controls_facility(DataTable pageControls) {
		List<String> pageControlsOnPopup = pageControls.asList(String.class);
		Assert.assertTrue("Controls are not seen",
				facilityGrpConfigPage.getAllPageControls().containsAll(pageControlsOnPopup));
	}

	@When("^user search facilities in the textbox present with label search facilities, example: \"([^\"]*)\"$")
	public void user_search_facilities_in_the_textbox_present_with_label_search_facilities_example_something(
			String facilityCode) {
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(facilityCode);
	}

	@Then("^user should be able to view \\+Add button as disabled$")
	public void user_should_be_able_to_view_add_button_as_disabled() {
		Assert.assertFalse("+Add button is not disabled ", facilityGrpConfigPage.isAddBtnOnAddNewPopupEnabled());

	}

	@Then("^user should be able to view \\+Add button should get enabled$")
	public void user_should_be_able_to_view_add_button_should_get_enabled() {
		Assert.assertTrue("+Add button is not enabled ", facilityGrpConfigPage.isAddBtnOnAddNewPopupEnabled());
	}

	@When("^user clicks on edit button$")
	public void user_clicks_on_edit_button() {
		facilityGrpConfigPage.clickOnEditBtn();
	}

	@Then("^user should be able to view edit button for each facility group present in facility group column$")
	public void user_should_be_able_to_view_edit_button_for_each_facility_group_present_in_facility_group_column() {
		Assert.assertTrue("Edit Button are not displayed against each facility group ",
				facilityGrpConfigPage.isEditBtnsDisplayedAgainstEachFacilityGrp());
	}

	@Then("^user should be able to view edit window popup should be display$")
	public void user_should_be_able_to_view_edit_window_popup_should_be_display() {
		Assert.assertTrue("Edit window is not displayed ", facilityGrpConfigPage.isEditFacilityModalWindowVisisble());
	}

	@Then("^user should be able to view physician in scope checkbox$")
	public void user_should_be_able_to_view_physician_in_scope_checkbox() {
		Assert.assertTrue(" Physician checkbox is not visible ", facilityGrpConfigPage.isPhysicianCheckboxVisisble());
	}

	@When("^user can check or uncheck physician in scope checkbox$")
	public void user_can_check_or_uncheck_physician_in_scope_checkbox() {
		facilityGrpConfigPage.clickOnPhysicianCheckbox();
	}

	@When("^user clicks on any edit button and clicks physician checkbox to enable it$")
	public void user_clicks_on_any_edit_button_and_clicks_physician_checkbox_to_enable_it() {
		facilityGrpConfigPage.clickOnEditBtnWithNoPhysicianChkboxChecked();
		facilityGroupNameFromUI = facilityGrpConfigPage.getFacilityGrpNameWithPhysicianChecked();
	}

	@When("^user clicks on edit button of the (.+)$")
	public void user_clicks_on_edit_button_of_the(String facilityGroupName) {
		checkedFacilityGrpName = facilityGroupName;
		facilityGrpConfigPage.clickOnFacilityGrpEditBtn(checkedFacilityGrpName);
	}

	@Then("^user should able to enable the checkbox for existing facility group$")
	public void user_should_able_to_enable_the_checkbox_for_existing_facility_group() {
		Assert.assertTrue("Physician checkbox is not checked", facilityGrpConfigPage.isPhysicianCheckboxEnabled());
	}

	@When("^user clicks and enable the physician scope checkbox$")
	public void user_clicks_and_enable_the_physician_scope_checkbox() {
		facilityGrpConfigPage.clickOnPhysicianCheckbox();
	}

	@When("^clicks on save button$")
	public void clicks_on_save_button() {
		facilityGrpConfigPage.clickOnSaveBtn();
	}

	@When("^user clicks on billing & follow\\-up from the footer$")
	public void user_clicks_on_billing_followup_from_the_footer() {
		navigationPage.clickFooterBillingFollowUpLink();
	}

	@Then("^user should be able to view workflow distribution screen$")
	public void user_should_be_able_to_view_workflow_distribution_screen() {
		Assert.assertTrue("User is not able to see Workflow Distribution screen",
				workflowDistributionPage.isWorkflowDistributionPageVisible());
	}

	@Then("^user should be able to view that facility group in the dropdown facility group list$")
	public void user_should_be_able_to_view_that_facility_group_in_the_dropdown_facility_group_list() {
		Assert.assertTrue("User is not able to see the facility group name in the drop down ", workflowDistributionPage
				.isFacilityGrpNamePresentInTheDropdown(WorkflowDistributionStepDef.pRCMEnabledFacilityGrpName));
	}

	@Then("^user should be able to view payer inventory filter for prcm enable facility group$")
	public void user_should_be_able_to_view_payer_inventory_filter_for_prcm_enable_facility_group() {
		Assert.assertTrue("user is not able to see Filter", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^user enters the Facility Group Name as \"([^\"]*)\"$")
	public void user_enters_the_facility_group_name_as_something(String facilityGrpName) {
		facilityGrpConfigPage.enterFacilityGrpNameInTxtBox(facilityGrpName);
		facilityGroupNameFromUI = facilityGrpConfigPage.getEnteredFacilityGroupName();
	}

	@When("^user selects facilities such that the Facility group has atleast one PRCM enabled Facility$")
	public void user_selects_facilities_such_that_the_facility_group_has_atleast_one_prcm_enabled_facility() {
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(pRCMEnabledFacilityCode);
		facilityGrpConfigPage.clickOnFacilityCodeFromSearchDropdown(pRCMEnabledFacilityCode);
		facilityGrpConfigPage.clickOnAddBtnOnPopup();
	}

	@Then("^user should be able to view that the IsPRCMEnabled checkbox is automatically checked$")
	public void user_should_be_able_to_view_that_the_isprcmenabled_checkbox_is_automatically_checked() {
		Assert.assertTrue("Physician Checkbox is not automatically checked ",
				facilityGrpConfigPage.isPhysicianCheckboxEnabled());
	}

	@When("^user selects PRCM enabled facility group from Facility Group dropdown$")
	public void user_selects_prcm_enabled_facility_group_from_facility_group_dropdown() {
		workflowDistributionPage.selectFacilityGroup(facilityGroupNameFromUI);
	}

	@Then("^user should be able to view the WFD screen should be displayed in PRCM view$")
	public void user_should_be_able_to_view_the_wfd_screen_should_be_displayed_in_prcm_view() {
		Assert.assertTrue("user is able to see PRCM view", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^atleast one facility group (.+) is enable in list of all facility group$")
	public void atleast_one_facility_group_is_enable_in_list_of_all_facility_group(String facilityGroupName) {
		facilityGrpConfigPage.clickOnFacilityGrpEditBtn(facilityGroupName);
		facilityGrpConfigPage.isPhysicianCheckboxEnabled();
		facilityGrpConfigPage.clickOnCloseBtn();
	}

	@Then("^user should able to view atleast one facility group \"([^\"]*)\" in facility group configuration screen$")
	public void user_should_able_to_view_atleast_one_facility_group_something_in_facility_group_configuration_screen(
			String expectedFacilityGrpName) {
		Assert.assertTrue(
				"User is not able to see the facility group name in the Facility Group Configuration screen  ",
				facilityGrpConfigPage.isFacilityGroupNamePresent(expectedFacilityGrpName));
	}

	@When("^user clicks to select any value from facility dropdown$")
	public void user_clicks_to_select_any_value_from_facility_dropdown() {
		workflowDistributionPage.clickAnyFacilityFromFacilityDropdown();
	}

	@When("^user should able to select \"([^\"]*)\" from facility dropdown$")
	public void user_should_able_to_select_something_from_facility_dropdown(String facility) {
		workflowDistributionPage.selectFacilityFromDropdown(facility);
	}

	@Then("^user should able to view payer inventory filter for professional and technical accounts$")
	public void user_should_able_to_view_payer_inventory_filter_for_professional_and_technical_accounts() {
		Assert.assertTrue("user is not able to see PRCM view", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^user selects Facilities \"([^\"]*)\" such no Selected Facility is PRCM enabled$")
	public void user_selects_facilities_something_such_no_selected_facility_is_prcm_enabled(String facilityCode) {
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(facilityCode);
		facilityGrpConfigPage.clickOnFacilityCodeFromSearchDropdown(facilityCode);
		facilityGrpConfigPage.clickOnAddBtnOnPopup();
	}

	@Then("^user should be able to view that the IsPRCMEnabled checkbox is automatically unchecked$")
	public void user_should_be_able_to_view_that_the_isprcmenabled_checkbox_is_automatically_unchecked() {
		Assert.assertFalse("Physician Checkbox is automatically checked ",
				facilityGrpConfigPage.isPhysicianCheckboxEnabled());
	}

	@When("^user clicks on facility group dropdown list$")
	public void user_clicks_on_facility_group_dropdown_list() {
		workflowDistributionPage.selectFacilityGroup(facilityGroupNameFromUI);
		workflowDistributionPage.clickAnyFacilityFromFacilityDropdown();
	}

	@Then("^user should be able to view the WFD screen should be displayed in non\\-PRCM view$")
	public void user_should_be_able_to_view_the_wfd_screen_should_be_displayed_in_nonprcm_view() {
		Assert.assertFalse("user is able to see PRCM view", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^user enters the Facility Group Name as \"([^\"]*)\" having no PRCM enabled facilities$")
	public void user_enters_the_facility_group_name_as_something_having_no_prcm_enabled_facilities(
			String facilityGrpNameNoPrcmEnabled) {
		facilityGrpConfigPage.enterFacilityGrpNameInTxtBox(facilityGrpNameNoPrcmEnabled);
		nonPrcmFacilityGroupNameFromUI = facilityGrpConfigPage.getEnteredFacilityGroupName();
	}

	@Then("^user should be able to view 2 facility group Test1 and Test2 having one facility \"([^\"]*)\" common on both$")
	public void user_should_be_able_to_view_2_facility_group_test1_and_test2_having_one_facility_something_common_on_both(
			String commonFacilityCode) {
		Assert.assertTrue("Facility group 1 does not contains common facility", facilityGrpConfigPage
				.checkFacilityGrpContainsCommonFacilityCode(facilityGroupNameFromUI, commonFacilityCode));
		Assert.assertTrue("Facility group 2  does not contains common facility", facilityGrpConfigPage
				.checkFacilityGrpContainsCommonFacilityCode(nonPrcmFacilityGroupNameFromUI, commonFacilityCode));

	}

	@Then("^user should be able to enable that check box$")
	public void user_should_be_able_to_enable_that_check_box() {
		Assert.assertTrue("User is not able to enable the checkbox ",
				facilityGrpConfigPage.isPhysicianCheckboxEnabled());
	}

	@Then("^user  should be able to view Test2 facility group should be non prcm facility group$")
	public void user_should_be_able_to_view_test2_facility_group_should_be_non_prcm_facility_group() {
		Assert.assertFalse(" Test2 is not is not non prcm enabled facility ",
				facilityGrpConfigPage.isPhysicianCheckboxEnabled());
	}

	@Then("^user  should be able to view PRCM enable view$")
	public void user_should_be_able_to_view_prcm_enable_view() {
		Assert.assertTrue("user is not able to see PRCM enable view",
				workflowDistributionPage.isFilterSectionPresent());
	}

	@Then("^user should be able to view R1D enable view$")
	public void user_should_be_able_to_view_r1d_enable_view() {
		Assert.assertFalse("user is able to see PRCM view", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^user enters facility as \"([^\"]*)\"$")
	public void user_enters_facility_as_something(String facilityCode) {
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(facilityCode);
		facilityGrpConfigPage.clickOnFacilityCodeFromSearchDropdown(facilityCode);
		facilityGrpConfigPage.clickOnAddBtnOnPopup();
	}

	@When("^user enters facility as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_facility_as_something_and_something(String facilityCodeOne, String facilityCodeTwo) {
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(facilityCodeOne);
		facilityGrpConfigPage.clickOnFacilityCodeFromSearchDropdown(facilityCodeOne);
		facilityGrpConfigPage.clickOnAddBtnOnPopup();
		facilityGrpConfigPage.enterFacilityCodeInTxtBox(facilityCodeTwo);
		facilityGrpConfigPage.clickOnFacilityCodeFromSearchDropdown(facilityCodeTwo);
		facilityGrpConfigPage.clickOnAddBtnOnPopup();
	}

	@When("^user clicks on edit button for Test1 facility group$")
	public void user_clicks_on_edit_button_for_test1_facility_group() {
		facilityGrpConfigPage.clickOnFacilityGrpEditBtn(facilityGroupNameFromUI);
	}

	@When("^user enable prcm checkbox that is physician in scope checkbox$")
	public void user_enable_prcm_checkbox_that_is_physician_in_scope_checkbox() {
		facilityGrpConfigPage.clickOnPhysicianCheckbox();
	}

	@When("^user clicks on edit button for Test2 facility group$")
	public void user_clicks_on_edit_button_for_test2_facility_group() {
		facilityGrpConfigPage.clickOnFacilityGrpEditBtn(nonPrcmFacilityGroupNameFromUI);
	}

	@When("^user selects Test1 from facility group dropdown$")
	public void user_selects_test1_from_facility_group_dropdown() {
		workflowDistributionPage.selectFacilityGroup(facilityGroupNameFromUI);

	}

	@When("^user selects common \"([^\"]*)\" facility from facility dropdown which is common in both Test1 and Test2$")
	public void user_selects_common_something_facility_from_facility_dropdown_which_is_common_in_both_test1_and_test2(
			String commonFacilityCode) {
		workflowDistributionPage.selectFacilityFromDropdown(commonFacilityCode);
	}

	@Then("^payer inventory filter should be display$")
	public void payer_inventory_filter_should_be_display() {
		Assert.assertTrue("user is not able to see Payer Inventory filter",
				workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^user selects Test2 from facility group dropdown$")
	public void user_selects_test2_from_facility_group_dropdown() {
		workflowDistributionPage.selectFacilityGroup(nonPrcmFacilityGroupNameFromUI);
	}

	@Then("^payer inventory filter for technical and professional should not be display$")
	public void payer_inventory_filter_for_technical_and_professional_should_not_be_display() {
		Assert.assertFalse("user is able to see PRCM view", workflowDistributionPage.isFilterSectionPresent());
	}

	@When("^clicks on close button$")
	public void clicks_on_close_button() {
		facilityGrpConfigPage.clickOnCloseBtn();
	}
}