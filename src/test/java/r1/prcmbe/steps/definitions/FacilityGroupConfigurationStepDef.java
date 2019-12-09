package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.FacilityGroupConfigurationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.FacilityGroupConfigSteps;

public class FacilityGroupConfigurationStepDef {
	SettingsPage settingsPage;
	FacilityGroupConfigurationPage facilityGrpConfigPage;
	CommonMethods commonMethods;

	@Steps
	FacilityGroupConfigSteps facilityGrpConfigSteps;

	private static String dbQueryFilename = "FacilityGrpConfig";

	String facilityGroupNameFromUI = null;
	String prcmEnabledFlag;

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
    public void user_clicks_on_any_edit_button_and_clicks_physician_checkbox_to_enable_it(){
		facilityGrpConfigPage.clickOnEditBtnWithNoPhysicianChkboxChecked();
		facilityGroupNameFromUI = facilityGrpConfigPage.getFacilityGrpNameWithPhysicianChecked();
    }
	 @When("^user clicks on edit button of the (.+)$")
	    public void user_clicks_on_edit_button_of_the(String facilitygroupname)  {
		 facilityGrpConfigPage.clickOnFacilityGrpEditBtn(facilitygroupname);
		 
	    }

	    @Then("^user should able to enable the checkbox for existing facility group$")
	    public void user_should_able_to_enable_the_checkbox_for_existing_facility_group()  {
	    	facilityGrpConfigPage.isPhysicianCheckboxEnabled();
	    }

	    @When("^user clicks and enable the physician scope checkbox$")
	    public void user_clicks_and_enable_the_physician_scope_checkbox()  {
	    	facilityGrpConfigPage.clickOnPhysicianCheckbox();
	    }

	    @When("^clicks on save button$")
	    public void clicks_on_save_button()  {
	    	facilityGrpConfigPage.clickOnSaveBtn();
	    }


}