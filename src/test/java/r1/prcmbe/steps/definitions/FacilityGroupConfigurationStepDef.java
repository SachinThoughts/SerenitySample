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

	String facilityGroupNameFromUI;
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
		Assert.assertTrue("failed to navigate facility Group Confifg Screen",
				facilityGrpConfigPage.isFacilityGrpConfigHeaderVisible());
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
				facilityGrpConfigSteps.IsNewAddedColumnVisibleInDB(addedCoumnName));
	}

	@Then("^user should be able to view header name as Facility Group Configuration$")
	public void user_should_be_able_to_view_header_name_as_facility_group_configuration() {
		Assert.assertTrue("Header name as Facility Group Configuration",
				facilityGrpConfigPage.isFacilityGrpConfigHeaderVisible());
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
				facilityGrpConfigPage.IsPRCMFacilityGroupNamePresent(expectedFacilityGroupName));
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
				facilityGrpConfigPage.AreAddFAcilityBtnPresents());
	}

	@Then("^user should be able to view the Edit Link button$")
	public void user_should_be_able_to_view_the_edit_link_button() {
		Assert.assertTrue("EditButton is not visisble on the Page ", facilityGrpConfigPage.isEditBtnPresent());
	}

}
