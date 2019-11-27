package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.FacilityGroupConfigurationPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.SettingsR1DPage;
import r1.prcmbe.pages.UniversalDefectConfigurationPage;
import r1.prcmbe.serenity.steps.LoginSteps;
import r1.prcmbe.serenity.steps.ProfessionalUDCSteps;

public class ProfessionalUDCStepDef extends PageObject {

	@Steps
	LoginSteps loginStep;

	@Steps
	ProfessionalUDCSteps proUDCSteps;

	NavigationPage navPage;
	SettingsPage settingsPage;
	UniversalDefectConfigurationPage uDCPage;
	FacilityGroupConfigurationPage facilityGroupConfigPage;
	SettingsR1DPage settingsR1DPage;
	LoginPage userLoginPage;
	AccountInformationPage accInfoPage;
	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;

	private String selectedDefectType, selectedSOPType, selectedDefectSubCategoryValue, addedSOPAction,
			randomDefectTypeName, randomDefectSubCategory, defectSubcategory;

	List<String> defectTypeList;
	List<String> defectSubCategoryList;
	List<String> dbSOPList;
	List<String> sOPList;

	private static String dbFileName = "ProfessionalUDC";

	private int defectSubcategoryId;

	@Given("^user is on R1 Hub page$")
	public void user_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies 2.0 - 15 Home"));
	}

	@When("^user clicks on setting link$")
	public void user_clicks_on_setting_link() {
		navPage.clickFooterSettings();
	}

	@When("^user clicks on Settings-R1_Decision$")
	public void user_clicks_on_Settings_R1_Decision() {
		settingsPage.clickOnSettingsR1Decisions();
	}

	@When("^user clicks on facility group configuration screen$")
	public void user_clicks_on_facility_group_configuration_screen() {
		settingsR1DPage.clickFaciltyGroupConfig();
	}

	@Then("^user should be able to view one prcm enable facility group$")
	public void user_should_be_able_to_view_one_prcm_enable_facility_group() {
		Assert.assertTrue("PRCM_enabled_facility_group not visible", facilityGroupConfigPage.getPRCMFacility() != null);
	}

	@When("^user clicks on UDC Admin configuration$")
	public void user_clicks_on_UDC_Admin_configuration() {
		settingsR1DPage.clickUDCAdminConfig();
	}

	@Given("^user is on UDC Admin configuration page$")
	public void user_is_on_UDC_Admin_configuration_page() {
		Assert.assertTrue("UDC page not visible", uDCPage.checkUDCTitleVisibility());
	}

	@Then("^user should able to view both application Tabs for technical as well as professional$")
	public void user_should_able_to_view_both_application_Tabs_for_technical_as_well_as_professional() {
		Assert.assertTrue("Both application Tabs for technical as well as professional on UDC page is not visible",
				uDCPage.decisionConfigTabIsVisible() && uDCPage.pRCMDecisionConfigIsVisible());
	}

	@When("^user clicks on R1Decision Config Tab$")
	public void user_clicks_on_R1Decision_Config_Tab() {
		uDCPage.clickOnDecisionConfigTab();
	}

	@Then("^user should be able to view populated screen to configure at Technical account level$")
	public void user_should_be_able_to_view_populated_screen_to_configure_at_Technical_account_level() {
		Assert.assertTrue("User not able to view populated screen to configure at Technical account level",
				uDCPage.innerNavConfigHeaderIsVisible());
	}

	@When("^user clicks on PRCM R1Decision Config$")
	public void user_clicks_on_PRCM_R1Decision_Config() {
		uDCPage.clickOnPRCMDecisionConfig();
	}

	@Then("^user should be able to view populated screen to configure at professional account invoice$")
	public void user_should_be_able_to_view_populated_screen_to_configure_at_professional_account_invoice() {
		Assert.assertTrue("User not able to view populated screen to configure at Professional account level",
				uDCPage.innerNavConfigHeaderIsVisible());
	}

	@Then("^user should be able to view R1 Decision UDC screen for technical only$")
	public void user_should_be_able_to_view_R1_Decision_UDC_screen_for_technical_only() {
		Assert.assertTrue("User not be able to view R1 Decision UDC screen for technical",
				uDCPage.decisionConfigTabIsVisible());
	}

	@When("^user logout from the application$")
	public void user_logout_from_the_application() {
		uDCPage.clickOnLogout();
	}

	@When("^user login with the user \"([^\"]*)\" who have atleast one facility which is prcm enable$")
	public void user_login_with_the_user_who_have_atleast_one_facility_which_is_prcm_enable(String username)
			throws IOException {
		loginStep.userEntersCredentials(CommonMethods.loadProperties(username),
				CommonMethods.loadProperties("nonPRCMBePassword"));
		Hooks.propertyName = username;
	}

	@When("^user clicks on Login button$")
	public void user_clicks_on_Login_button() {
		userLoginPage.loginBtnClick();
		if (userLoginPage.isProceedLinkVisible()) {
			userLoginPage.clickOnProceedFurther();
		}
	}

	@Given("^user having AHtoDecision Admin role is on Universal Defect Configuration  page$")
	public void user_having_AHtoDecision_Admin_role_is_on_Universal_Defect_Configuration_page() {
		Assert.assertTrue("UDC page not visible", uDCPage.checkUDCTitleVisibility());
	}

	@When("^user select the radio button against any defect type$")
	public void user_select_the_radio_button_against_any_defect_type() {
		selectedDefectType = uDCPage.selectAndGetRandomDefectType();
	}

	@When("^user clicks on the Continue button on defect type page$")
	public void user_clicks_on_the_Continue_button_on_defect_type_page() {
		uDCPage.clickContinueBtnOnDefectType();
		uDCPage.addDefectSubCategoryIfNotExist();
	}

	@When("^user select the radio button corresponding to a defect subcategory$")
	public void user_select_the_radio_button_corresponding_to_a_defect_subcategory() {
		selectedDefectSubCategoryValue = uDCPage.selectAndGetRandomDefectSubcategory();
	}

	@When("^user clicks on the Continue button on defect sub category page$")
	public void user_clicks_on_the_Continue_button_on_defect_sub_category_page() {
		uDCPage.clickContinueBtnDefectSubCategory();
		selectedSOPType = uDCPage.selectAndGetRandomSOPType();
	}

	@Then("^user should be able to view the selected Defect Type Defect SubCategory and default SOP Action in breadcrumb$")
	public void user_should_be_able_to_view_the_selected_Defect_Type_Defect_SubCategory_and_default_SOP_Action_in_breadcrumb() {
		Assert.assertTrue("Application is not displaying Defect type ,Defect subcategory and Sop action in breadcrumb",
				uDCPage.getListOfBreadCrumbVal().contains(selectedDefectType)
						&& uDCPage.getListOfBreadCrumbVal().contains(selectedDefectSubCategoryValue)
						&& uDCPage.getListOfBreadCrumbVal().contains(selectedSOPType));
	}

	@Then("^user should be able to view Choose a SOP Action grid$")
	public void user_should_be_able_to_view_Choose_a_SOP_Action_grid() {
		Assert.assertTrue("SOP Grid not visible ", uDCPage.isChooseSOPGridVisible());
	}

	@When("^user clicks on the Continue button on SOP type page")
	public void user_clicks_on_Continue_button_on_SOP_type_page() {
		uDCPage.clickContinueBtnOnSOP();
	}

	@When("^user clicks on the Add New SOP Actions button on SOP Actions screen$")
	public void user_clicks_on_the_Add_New_SOP_Actions_button_on_SOP_Actions_screen() {
		uDCPage.clickAddNewSOPActionBtn();
	}

	@When("^user enters all the mandatory fields$")
	public void user_enters_all_the_mandatory_fields(DataTable dataTable) {
		List<String> sOPActionData = dataTable.asList(String.class);
		addedSOPAction = sOPActionData.get(0);
		uDCPage.enterActionName(addedSOPAction);
		uDCPage.enterActionDescription(sOPActionData.get(1));
		uDCPage.selectNextActionByText(sOPActionData.get(2));
		uDCPage.enterFollowUpDays(sOPActionData.get(3));
		uDCPage.enterRespondDeadline(sOPActionData.get(4));
		uDCPage.selectActionStatusByText(sOPActionData.get(5));
	}

	@When("^user clicks on Save Changes SOP Actions button$")
	public void user_clicks_on_Save_Changes_SOP_Actions_button() {
		uDCPage.clickSaveChangesSOPActionBtn();
	}

	@Then("^user should be able to view message as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_as(String message) {
		Assert.assertTrue("'" + message + "' message is not displayed after adding SOP Actions",
				message.equals(uDCPage.getSOPSuccessMessage()));
	}

	@Then("^Add SOP Action pop-up should disappear$")
	public void add_SOP_Action_pop_up_should_disappear() {
		Assert.assertFalse("SOP Action Popup is still visible", uDCPage.checkSOPActionPopupDisappeared());
	}

	@Then("^user should be able to view added SOP Action on SOP Actions screen$")
	public void user_should_be_able_to_view_added_SOP_Action_on_SOP_Actions_screen() {
		Assert.assertTrue("Added SOP Action not visible on screen",
				uDCPage.getListOfSOPActions().contains(addedSOPAction));
	}

	@Then("^user having prcm enable facility should be able to view PRCM R1Decision Config as pre-selected$")
	public void user_having_prcm_enable_facility_should_be_able_to_view_PRCM_R1Decision_Config_as_pre_selected() {
		Assert.assertTrue("PRCM R1Decision Config is not pre-selected", uDCPage.innerNavConfigHeaderIsVisible());
	}

	@Then("^user should be able to open that application name$")
	public void user_should_be_able_to_open_that_application_name() {
		Assert.assertTrue("User not able to view populated screen to configure at Professional account level",
				uDCPage.innerNavConfigHeaderIsVisible());
	}

	@When("^user clicks on Add defect type button$")
	public void user_clicks_on_Add_defect_type_button() {
		defectTypeList = uDCPage.getListOfDefectTypes();
		uDCPage.clickAddDefectTypeBtn();
	}

	@Then("^user should able to view popup window for new defect$")
	public void user_should_able_to_view_popup_window_for_new_defect() {
		Assert.assertTrue("Defect Type Modal Pop Up not visible", uDCPage.checkDefectTypeModalPopUpVisibility());
	}

	@When("^user fills all the fields (.+) and clicks active checkbox$")
	public void user_fills_all_the_fields_and_clicks_active_checkbox(String defectName) {
		randomDefectTypeName = proUDCSteps.getDefectTypeValue(defectName, defectTypeList);
		uDCPage.enterDefectName(randomDefectTypeName);
		uDCPage.clickActiveCheckbox();
	}

	@When("^user clicks on Add Defect Type button on modal popup$")
	public void user_clicks_on_Add_Defect_Type_button_on_modal_popup() {
		uDCPage.clickModalAddDefectTypeBtn();
	}

	@Then("^user should be able to view success message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_success_message(String successMsg) {
		Assert.assertTrue("Defect Type saved success message not displayed",
				successMsg.equals(uDCPage.getDefectTypeSuccessMsg()));
	}

	@Then("^user should be able to view newly added defect at professional level$")
	public void user_should_be_able_to_view_newly_added_defect_at_professional_level() {
		Assert.assertTrue("User not able to view newly added defect type with Active checkbox",
				randomDefectTypeName.equals(uDCPage.getNewlyAddedDefectType()));
	}

	@When("^user clicks on Continue button on defect type page$")
	public void user_clicks_on_Continue_button_on_defect_type_page() {
		uDCPage.clickContinueBtnOnDefectType();
	}

	@When("^user clicks on add defect subcategory$")
	public void user_clicks_on_add_defect_subcategory() {
		defectSubCategoryList = uDCPage.getListOfDefectSubCategory();
		uDCPage.clickAddDefectSubCategoryBtn();
	}

	@Then("^user should be able to add any new (.+) defectsubcategory and clicks active checkbox$")
	public void user_should_be_able_to_add_any_new_defectsubcategory_and_clicks_active_checkbox(
			String defectSubcategoryName) {
		randomDefectSubCategory = proUDCSteps.getDefectSubTypeValue(defectSubcategoryName, defectSubCategoryList);
		uDCPage.enterDefectSubCategoryName(randomDefectSubCategory);
		uDCPage.clickDefectSubCategoryActiveCheckbox();
	}

	@When("^user clicks on Add Defect Sub Category button on modal popup$")
	public void user_clicks_on_Add_Defect_Sub_Category_button_on_modal_popup() {
		uDCPage.clickAddDefectSubCategoryPopUpBtn();
	}

	@When("^user runs the \"([^\"]*)\" query to fetch applicationid$")
	public void user_runs_the_query_to_fetch_applicationid(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
	}

	@Then("^user should be able to view that for all defects which belongs to professional applicationid should be (\\d+)$")
	public void user_should_be_able_to_view_that_for_all_defects_which_belongs_to_professional_applicationid_should_be(
			int applicationId) throws Exception {
		while (DatabaseConn.resultSet.next()) {
			Assert.assertTrue("Application ID fetched from DB is not equal to 3",
					applicationId == DatabaseConn.resultSet.getInt("ApplicationID"));
		}
	}

	@When("^user runs the \"([^\"]*)\" query to fetch application name and application ID$")
	public void user_runs_the_query_to_fetch_application_name_and_application_ID(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
	}

	@Then("^user should be able to view Application name (.+) and Application ID$")
	public void user_should_be_able_to_view_Application_name_and_Application_ID(String applicationName)
			throws Exception {
		List<String> application = new ArrayList<String>();
		while (DatabaseConn.resultSet.next()) {
			application.add(DatabaseConn.resultSet.getString("ApplicationName"));
		}
		Assert.assertTrue(applicationName + " does not exists in database", application.contains(applicationName));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch defect subcategory ID$")
	public void user_runs_the_query_to_fetch_defect_subcategory_ID(String queryName) throws Exception {
		String query = commonMethods.loadQuery(queryName, dbFileName);
		query = String.format(query, defectSubcategory);
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, query);
	}

	@Then("^user should be able to view defectsubcategoryid$")
	public void user_should_be_able_to_view_defectsubcategoryid() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			defectSubcategoryId = DatabaseConn.resultSet.getInt("DefectSubCategoryID");
		}
	}

	@When("^user runs the \"([^\"]*)\" query to fetch skills id$")
	public void user_runs_the_query_to_fetch_skills_id(String queryName) throws Exception {
		String query = commonMethods.loadQuery(queryName, dbFileName);
		query = String.format(query, defectSubcategoryId);
		System.out.println(query);
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, query);
	}

	@Then("^user should be able to view Skillid for all Major payer for defectsubcategoryid$")
	public void user_should_be_able_to_view_Skillid_for_all_Major_payer_for_defectsubcategoryid() throws SQLException {
		List<Integer> skillsId = new ArrayList<Integer>();
		while (DatabaseConn.resultSet.next()) {
			skillsId.add(DatabaseConn.resultSet.getInt("SkillID"));
		}
		Assert.assertTrue("Skill Id's not fetched from database", !skillsId.isEmpty());
	}

	@Then("^user should able to open that application name$")
	public void user_should_able_to_open_that_application_name() {
		Assert.assertTrue("User not able to view populated screen to configure at Technical account level",
				uDCPage.innerNavConfigHeaderIsVisible());
	}

	@Then("^user should be able to view newly added defect at technical level$")
	public void user_should_be_able_to_view_newly_added_defect_at_technical_level() {
		Assert.assertTrue("User not able to view newly added defect type with active checkbox at technical level",
				randomDefectTypeName.equals(uDCPage.getNewlyAddedDefectType()));
	}

	@Then("^user should be able to view that for all defects which belongs to technical applicationid should be (\\d+)$")
	public void user_should_be_able_to_view_that_for_all_defects_which_belongs_to_technical_applicationid_should_be(
			int applicationId) throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			Assert.assertTrue("Application ID fetched from DB is not equal to 1",
					applicationId == DatabaseConn.resultSet.getInt("ApplicationID"));
		}
	}

	@When("^user clicks on next button in defect workflow section until user reaches SOPs$")
	public void user_clicks_on_next_button_in_defect_workflow_section_until_user_reaches_SOPs() {
		defectSubcategory = accInfoPage.getDefectSubcategoryBreadcrumb();
		accInfoPage.clickDefectWorkflowNextBtn();
		accInfoPage.clickNextBtn();
	}

	@Then("^user should be able to view the SOP action$")
	public void user_should_be_able_to_view_the_SOP_action() {
		sOPList = accInfoPage.getSOPList();
	}

	@When("^user runs the \"([^\"]*)\" query to fetch the list of SOPs$")
	public void user_runs_the_query_to_fetch_the_list_of_SOPs(String queryName) throws Exception {
		String query = commonMethods.loadQuery(queryName, dbFileName);
		query = String.format(query, defectSubcategoryId);
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, query);
	}

	@Then("^user should be able view SOP list for the passed defectsubcategory$")
	public void user_should_be_able_view_SOP_list_for_the_passed_defectsubcategory() throws SQLException {
		dbSOPList = new ArrayList<String>();
		while (DatabaseConn.resultSet.next()) {
			dbSOPList.add(DatabaseConn.resultSet.getString(2));
		}
	}

	@Then("^user should be able to view Same SOPs Step in DB and UI$")
	public void user_should_be_able_to_view_Same_SOPs_Step_in_DB_and_UI() {
		Assert.assertTrue("UI and DB SOPs does not match", dbSOPList.containsAll(sOPList));
	}

	@Given("^user login to SQL server and connect to facility database$")
	public void user_login_to_SQL_server_and_connect_to_facility_database() throws IOException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@Then("^user should be able to view Add Defect Type pop-up disappear$")
	public void user_should_be_able_to_view_Add_Defect_Type_pop_up_disappear() {
		Assert.assertFalse("Add defect sub-category pop up is visible", uDCPage.checkSuccessMsgPopupVisibility());
	}

	@Then("^user should be able to view Add Defect Sub Category pop-up disappear$")
	public void user_should_be_able_to_view_Add_Defect_Sub_Category_pop_up_disappear() {
		Assert.assertFalse("Add defect sub-category pop up is visible", uDCPage.checkSuccessMsgPopupVisibility());
	}

	@When("^user clicks on Defect Type tab$")
	public void user_clicks_on_Defect_Type_tab() {
		uDCPage.clickOnDefectTypeTab();
	}

	@When("^user clicks on edit button on any existing defect$")
	public void user_clicks_on_edit_button_on_any_existing_defect() {
		defectTypeList = uDCPage.getListOfDefectTypes();
		uDCPage.clickEditLink();
	}
}