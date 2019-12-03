package r1.prcmbe.serenity.steps;

import java.io.IOException;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.steps.definitions.Hooks;

public class LoginSteps extends PageObject {

	LoginPage loginPage;

	@Step
	public void userEntersCredentials(String userName, String password) throws IOException {
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
	}

	@Step
	public void log(String message) {

	}

	@Step
	public void roleLogin(String usernameProperty, String passwordProperty) throws IOException {
		userEntersCredentials(CommonMethods.loadProperties(usernameProperty),
				CommonMethods.loadProperties(passwordProperty));
		Hooks.propertyName = passwordProperty;
		loginPage.loginBtnClick();
		if (loginPage.isProceedLinkVisible()) {
			loginPage.clickOnProceedFurther();
		}
	}
}
