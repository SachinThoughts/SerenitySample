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

	static String propertyName;

	@Steps
	LoginSteps loginStep;

	@Before(value = "")
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
}