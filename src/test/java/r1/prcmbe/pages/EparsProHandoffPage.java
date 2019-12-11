package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

	@FindBy(xpath = "//div[@class='modal-body tblsearchResults']/table/thead/tr/th")
	private List<WebElementFacade> searchResultsTableHeaders;

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
		return validationMessage.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().getText().trim();
	}

	public void enterLastNameTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		lastNameTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterInvoiceNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		invoiceNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterVisitNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		visitNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterSSNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		ssnTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterClaimNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		claimNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterDateOfServiceTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfServiceTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterDateOfBirthTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfBirthTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void enterMRNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		mrnTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	public void selectOperator(String operatorValue) {
		waitForAngularRequestsToFinish();
		operatorBy.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().selectByVisibleText(operatorValue);
	}

	public List<String> getSearchResultsTableHeaders() {
		waitForAngularRequestsToFinish();
		List<String> listOfSearchResultsTableHeaders = new ArrayList<>();
		for (WebElementFacade searchResultsTableHeader : searchResultsTableHeaders) {
			withAction().moveToElement(searchResultsTableHeader).build().perform();
			if (!searchResultsTableHeader.getText().trim().equals(""))
				listOfSearchResultsTableHeaders.add(searchResultsTableHeader.getText().trim());
		}
		return listOfSearchResultsTableHeaders;
	}
}