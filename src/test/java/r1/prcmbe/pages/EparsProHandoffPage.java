package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class EparsProHandoffPage extends PageObject {

	@FindBy(xpath = "//span[@class='subhead mainheader']")
	private WebElementFacade eparsTitle;

	@FindBy(id = "ddlSearchCriteriaBy")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "//*[@id='btnSubmitTopSearch']/a")
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

	@FindBy(id = "searchInputFirstName")
	private WebElementFacade firstNameTextBox;

	/**
	 * The method checks if the user is on Epars page and the Epars page title is visible
	 */
	public void isEparsPageTitleVisible() {
		eparsTitle.shouldBeVisible();
	}

	/** 
	 * @return the value selected for Search By
	 */
	public String getSearchByDropdownValue() {
		return searchByDropdown.getSelectedVisibleTextValue().trim();
	}

	/** 
	 * @param searchByDropdownValue : The value to be selected in 'Search By' dropdown
	 */
	public void selectSearchByDropdownValue(String searchByDropdownValue) {
		searchByDropdown.selectByVisibleText(searchByDropdownValue).getSelectedVisibleTextValue();
	}

	/**
	 * Click on the 'Submit' button on the top
	 */
	public void clickSubmitBtn() {
		evaluateJavascript("arguments[0].click();", submitBtn);
	}

	/** 
	 * @return the validation message that is displayed after clicking on the submit button
	 */
	public String getValidationMessage() {
		return validationMessage.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().getText().trim();
	}

	/** 
	 * @param textBoxValue : The Value to be entered in Last Name Textbox
	 */
	public void enterLastNameTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		lastNameTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue: The Invoice Number to be entered
	 */
	public void enterInvoiceNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		invoiceNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue : The Visit Number to be entered
	 */
	public void enterVisitNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		visitNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue : The Social Security Number to be entered
	 */
	public void enterSSNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		ssnTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue: The Claim Number to be entered
	 */
	public void enterClaimNumberTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		claimNumberTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue : The Date of Service to be entered
	 */
	public void enterDateOfServiceTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfServiceTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue : The Date of Birth to be entered
	 */
	public void enterDateOfBirthTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		dateOfBirthTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param textBoxValue : The Medical Record Number to be entered
	 */
	public void enterMRNTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		mrnTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}

	/**
	 * @param operatorValue : The Operator '=' or 'Like' to be selected
	 */
	public void selectOperator(String operatorValue) {
		waitForAngularRequestsToFinish();
		operatorBy.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().selectByVisibleText(operatorValue);
	}

	/** 
	 * @return : The Search Results Table headers are returned
	 */
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

	/**
	 * @param textBoxValue : The First Name to be entered
	 */
	public void enterFirstNameTextBox(String textBoxValue) {
		waitForAngularRequestsToFinish();
		firstNameTextBox.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().type(textBoxValue);
	}
}