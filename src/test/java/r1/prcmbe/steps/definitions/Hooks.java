package r1.prcmbe.steps.definitions;

import java.io.IOException;

import cucumber.api.java.Before;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.serenity.steps.LoginSteps;

public class Hooks extends PageObject {

	LoginPage userLoginPage;
	AccountInformationPage accInfoPage;
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
}