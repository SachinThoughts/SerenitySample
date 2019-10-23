package r1.prcmbe.pages;

import java.io.IOException;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {

	@FindBy(xpath = "//*[@id='nav-link-yourAccount']/span[2]")
	WebElementFacade signInLink;

	@FindBy(id = "dnn_ctr1029_Login_Login_DNN_txtUsername")
	WebElementFacade username;

	@FindBy(id = "dnn_ctr1029_Login_Login_DNN_txtPassword")
	WebElementFacade password;

	@FindBy(id = "dnn_ctr1029_Login_Login_DNN_cmdLogin")
	WebElementFacade signInButton;

	@FindBy(xpath="//a[text()='Proceed Anyway (Change password later)']")
	private WebElementFacade proceedFurtherLink;
	
	public void clickSignInLink() {
		signInLink.click();
	}

	public boolean verifyUsernameTextBox() {
		return username.isVisible();
	}

	public void enterUsername(String userName) throws IOException {
		username.type(userName);
	}

	public void enterPassword(String passwd) throws IOException {
		password.type(passwd);
	}

	public void loginBtnClick() {
		signInButton.click();
	}

	public boolean isProceedLinkVisible() {
	    return proceedFurtherLink.isVisible();	
	}
	
	public void clickOnProceedFurther() {
		proceedFurtherLink.click();
	}
}
