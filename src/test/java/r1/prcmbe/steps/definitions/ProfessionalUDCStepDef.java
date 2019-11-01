package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.FacilityGroupConfigurationPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.SettingsR1DPage;
import r1.prcmbe.pages.UniversalDefectConfigurationPage;
import r1.prcmbe.serenity.steps.LoginSteps;

public class ProfessionalUDCStepDef extends PageObject {

	@Steps
	LoginSteps loginStep;

	NavigationPage navPage;
	SettingsPage settingsPage;
	UniversalDefectConfigurationPage uDCPage;
	FacilityGroupConfigurationPage facilityGroupConfigPage;
	SettingsR1DPage settingsR1DPage;
	LoginPage userLoginPage;

	private String selectedDefectType, selectedSOPType, selectedDefectSubCategoryValue, addedSOPAction;

	@Given("^user is on R1 Hub page$")
	public void user_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies 2.0 - 01 Home"));
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
		Assert.assertTrue("User not able to view populated screen to configure at Technical account level",
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
		uDCPage.clickAddNewSopActionBtn();
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
		uDCPage.clickSaveChangesSopActionBtn();
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
}