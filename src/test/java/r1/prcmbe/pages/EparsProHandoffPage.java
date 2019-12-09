package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class EparsProHandoffPage extends PageObject {

	@FindBy(xpath = "//span[@class='subhead mainheader' and contains(text(),'ePARS')]")
	private WebElementFacade eparsTitle;

	@FindBy(id = "ddlSearchCriteriaBy")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "(//a[contains(text(),'Submit')])[1]")
	private WebElementFacade submitBtn;

	@FindBy(id = "validationMessage")
	private WebElementFacade validationMessage;

	@FindBy(id = "searchInputLastName")
	private WebElementFacade lastNameTextBox;

	@FindBy(id = "searchInputInvoice")
	private WebElementFacade invoiceNumberTextBox;

	@FindBy(id = "searchInputVisitNumber")
	private WebElementFacade visitNumberTextBox;

	@FindBy(id = "searchInputSSN")
	private WebElementFacade ssnTextBox;

	@FindBy(id = "searchInputClaimNumber")
	private WebElementFacade claimNumberTextBox;

	@FindBy(id = "searchInputDOS")
	private WebElementFacade dateOfServiceTextBox;

	@FindBy(id = "searchInputDOB")
	private WebElementFacade dateOfBirthTextBox;

	@FindBy(id = "searchInputMRN")
	private WebElementFacade mrnTextBox;

	@FindBy(id = "ddlOperatorBy")
	private WebElementFacade operatorBy;

	public void isEparsPageTitleVisible() {
		eparsTitle.shouldBeVisible();
	}

	public String getSearchByDropdownValue() {
		return searchByDropdown.getSelectedVisibleTextValue().trim();
	}

	public void selectSearchByDropdownValue(String searchByDropdownValue) {
		searchByDropdown.selectByVisibleText(searchByDropdownValue).getSelectedVisibleTextValue();
	}

	public void clickSubmitBtn() {
		evaluateJavascript("arguments[0].click();", submitBtn);
	}

	public String getValidationMessage() {
		return validationMessage.getText().trim();
	}

	public void enterLastNameTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		lastNameTextBox.type(textBoxValue);
	}

	public void enterInvoiceNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		invoiceNumberTextBox.type(textBoxValue);
	}

	public void enterVisitNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		visitNumberTextBox.type(textBoxValue);
	}

	public void enterSSNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		ssnTextBox.type(textBoxValue);
	}

	public void enterClaimNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		claimNumberTextBox.type(textBoxValue);
	}

	public void enterDateOfServiceTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfServiceTextBox.type(textBoxValue);
	}

	public void enterDateOfBirthTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfBirthTextBox.type(textBoxValue);
	}

	public void enterMRNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		mrnTextBox.type(textBoxValue);
	}

	public void selectOperator(String operatorValue) {
		waitForAngularRequestsToFinish();
		operatorBy.selectByVisibleText(operatorValue);
	}
}