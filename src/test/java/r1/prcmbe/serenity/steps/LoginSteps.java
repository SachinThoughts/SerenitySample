package r1.prcmbe.serenity.steps;

import java.io.IOException;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.LoginPage;

public class LoginSteps extends PageObject{

	LoginPage loginPage;
	
	@Step
	public void userEntersCredentials(String userName,String password) throws IOException {
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
	}
}
