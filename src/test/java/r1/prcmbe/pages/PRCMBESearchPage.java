package r1.prcmbe.pages;

import java.time.Duration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PRCMBESearchPage extends PageObject {

	@FindBy(xpath = "//head/title")
	WebElementFacade searchPageTitle;

	@FindBy(xpath = "//select[@class='form-control ddlSearchCriteria']")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "//input[@placeholder='Invoice #']")
	private WebElementFacade invoiceNumberTxtField;

	@FindBy(xpath = "//input[@placeholder='Visit #']")
	private WebElementFacade visitTxtField;

	@FindBy(xpath = "//input[@placeholder='Medical Records #']")
	private WebElementFacade mRNTxtField;

	@FindBy(xpath = "//input[@placeholder='Claim #']")
	private WebElementFacade claimNumberTxtField;

	@FindBy(xpath = "//div[@class='form-group searchBtnOnLoad']/input[@type='submit']")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElementFacade lastNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElementFacade firstNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='Social Security Number']")
	private WebElementFacade sSNTxtBox;

	@FindBy(xpath = "//*[@id='searchLoader']//div[@class='modal-body']/i")
	private WebElementFacade loadingSpinner;

	@FindBy(id = "showErrorMsg")
	private WebElementFacade errorMsg;

	String titleJs = "return document.querySelector('#Head > title').text";

	public String getsearchPageTitle() {
		return evaluateJavascript(titleJs).toString();
	}

	public String getDefaultSelectedVal() {
		return searchByDropdown.getSelectedVisibleTextValue();
	}

	public void searchBySelectText(String dropdown) {
		searchByDropdown.selectByVisibleText(dropdown);
	}

	public boolean isInvoiceNumberTxtFieldVisible() {
		return invoiceNumberTxtField.isVisible();
	}

	public void enterInvoiceNumber(String visitNumber) {
		invoiceNumberTxtField.type(visitNumber);
	}

	public boolean isVisitTxtFieldVisible() {
		return visitTxtField.isVisible();
	}

	public void enterVisitNumber(String visitNumber) {
		visitTxtField.type(visitNumber);
	}

	public boolean isMRNTxtFieldVisible() {
		return mRNTxtField.isVisible();
	}

	public void enterMRN(String mRN) {
		mRNTxtField.type(mRN);
	}

	public boolean isClaimNumberTxtFieldVisible() {
		return claimNumberTxtField.isVisible();
	}

	public void enterClaimNumber(String claimNumber) {
		claimNumberTxtField.type(claimNumber);
	}

	public boolean islastNameTxtBoxTxtFieldVisible() {
		return lastNameTxtBox.isVisible();
	}

	public void enterlastNameTxtBox(String lastName) {
		lastNameTxtBox.type(lastName);
	}

	public boolean isfirstNameTxtFieldVisible() {
		return firstNameTxtBox.isVisible();
	}

	public void enterfirstName(String firstName) {
		firstNameTxtBox.type(firstName);
	}

	public void clickSubmitBtn() {
		submitBtn.click();
		if (loadingSpinner.isVisible()) {
			loadingSpinner.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
		}
	}

	public String getErrorMsg() {
		return errorMsg.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText();
	}

	public boolean isSSNTxtFieldVisible() {
		return sSNTxtBox.isVisible();
	}

	public void enterSSN(String sSN) {
		sSNTxtBox.type(sSN);
	}

}
