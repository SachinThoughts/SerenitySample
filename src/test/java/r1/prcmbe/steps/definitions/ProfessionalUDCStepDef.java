package r1.prcmbe.steps.definitions;

import java.io.IOException;

import org.junit.Assert;

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
}