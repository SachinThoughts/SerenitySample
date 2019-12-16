package r1.prcmbe.steps.definitions;

import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.FacilityGroupConfigurationPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.LoginSteps;

public class Hooks extends PageObject {

	LoginPage userLoginPage;
	AccountInformationPage accInfoPage;
	FacilityGroupConfigurationPage facilityGrpConfigPage;
	NavigationPage navPage;
	SettingsPage settingsPage;
	static public String propertyName;

	@Steps
	LoginSteps loginStep;

	@Before(value = "@AHtoDecisionAdmin or @PRCMUser or @ARSupervisor")
	public void prcmBeUser() throws IOException {
		open();
		if (accInfoPage.checkLogoutVisible() && propertyName != "prcmBeUsername") {
			accInfoPage.logOut();
			open();
		}
		if (userLoginPage.verifyUsernameTextBox()) {
			String accountuser = CommonMethods.loadProperties("prcmBeUsername");
			String passwd = CommonMethods.loadProperties("prcmBePassword");
			loginStep.userEntersCredentials(accountuser, passwd);
			userLoginPage.loginBtnClick();
			if (userLoginPage.isProceedLinkVisible()) {
				userLoginPage.clickOnProceedFurther();
			}
			propertyName = "prcmBeUsername";
		}
	}

	@Before(value = "@NonPRCMUser")
	public void nonPRCMBeUser() throws IOException {
		open();
		if (accInfoPage.checkLogoutVisible() && propertyName != "nonPRCMBeUsername") {
			accInfoPage.logOut();
			open();
		}
		if (userLoginPage.verifyUsernameTextBox()) {
			String accountuser = CommonMethods.loadProperties("nonPRCMBeUsername");
			String passwd = CommonMethods.loadProperties("nonPRCMBePassword");
			loginStep.userEntersCredentials(accountuser, passwd);
			userLoginPage.loginBtnClick();
			if (userLoginPage.isProceedLinkVisible()) {
				userLoginPage.clickOnProceedFurther();
			}
			propertyName = "nonPRCMBeUsername";
		}
	}

	@Before(value = "@BSOFollowUpUser")
	public void prcmBSOFollowUpUser() throws IOException {
		open();
		if (accInfoPage.checkLogoutVisible() && propertyName != "BSOFollowUpUserName") {
			accInfoPage.logOut();
			open();
		}
		if (userLoginPage.verifyUsernameTextBox()) {
			String accountuser = CommonMethods.loadProperties("BSOFollowUpUserName");
			String passwd = CommonMethods.loadProperties("BSOFollowUpPassword");
			loginStep.userEntersCredentials(accountuser, passwd);
			userLoginPage.loginBtnClick();
			if (userLoginPage.isProceedLinkVisible()) {
				userLoginPage.clickOnProceedFurther();
			}
			propertyName = "BSOFollowUpUserName";
		}
	}

	@Before(value = "@R1DApproval")
	public void prcmR1DApprovalUser() throws IOException {
		open();
		if (accInfoPage.checkLogoutVisible() && propertyName != "R1DApproverUserName") {
			accInfoPage.logOut();
			open();
		}
		if (userLoginPage.verifyUsernameTextBox()) {
			String accountuser = CommonMethods.loadProperties("R1DApproverUserName");
			String passwd = CommonMethods.loadProperties("R1DApproverPassword");
			loginStep.userEntersCredentials(accountuser, passwd);
			userLoginPage.loginBtnClick();
			if (userLoginPage.isProceedLinkVisible()) {
				userLoginPage.clickOnProceedFurther();
			}
			propertyName = "R1DApproverUserName";
		}
	}

	@Before(value = "@PRCMQueueUser")
	public void prcmBeQueueUser() throws IOException {
		open();
		if (accInfoPage.checkLogoutVisible() && propertyName != "prcmBeQueueUsername") {
			accInfoPage.logOut();
			open();
		}
		if (userLoginPage.verifyUsernameTextBox()) {
			String accountuser = CommonMethods.loadProperties("prcmBeQueueUsername");
			String passwd = CommonMethods.loadProperties("prcmBeQueuePassword");
			loginStep.userEntersCredentials(accountuser, passwd);
			userLoginPage.loginBtnClick();
			if (userLoginPage.isProceedLinkVisible()) {
				userLoginPage.clickOnProceedFurther();
			}
			propertyName = "prcmBeQueueUsername";
		}
	}

	@After(value = "@391164")
	public void uncheckPrcmFacilityGrp() {
		navPage.clickFooterSettings();
		settingsPage.clickOnSettingsR1Decisions();
		settingsPage.clickFacilityGroupConfig();
		facilityGrpConfigPage.clickOnFacilityGrpEditBtn(FacilityGroupConfigurationStepDef.checkedFacilityGrpName);
		facilityGrpConfigPage.clickOnPhysicianCheckbox();
		facilityGrpConfigPage.clickOnSaveBtn();
	}
}