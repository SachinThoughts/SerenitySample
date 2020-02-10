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
	
	/**
	 * This method clicks on SignInLink
	 */
	public void clickSignInLink() {
		signInLink.click();
	}

	/**
	 * @return UserName TextBox is visible
	 */
	public boolean verifyUsernameTextBox() {
		return username.isVisible();
	}

	/**
	 * This method enters UserName
	 * @param userName is coming from StepDef
	 * @throws IOException
	 */
	public void enterUsername(String userName) throws IOException {
		username.type(userName);
	}

	/**
	 * This method enters Password
	 * @param passwd is coming from StepDef
	 * @throws IOException
	 */
	public void enterPassword(String passwd) throws IOException {
		password.type(passwd);
	}

	/**
	 * This method clicks on Sign-In Button
	 */
	public void loginBtnClick() {
		signInButton.click();
	}

	/**
	 * @return ProceedFurther Link is visible
	 */
	public boolean isProceedLinkVisible() {
	    return proceedFurtherLink.isVisible();	
	}
	
	/**
	 * This method clicks on ProceedFurther Link
	 */
	public void clickOnProceedFurther() {
		proceedFurtherLink.click();
	}
}
