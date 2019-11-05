package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SearchPage extends PageObject {

	@FindBy(css = "#userMsg > span")
	private WebElementFacade noAccountsMessage;

	@FindBy(xpath = "//select[@class='form-control ddlSearchCriteria']")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "//select[@class='form-control ddlOperator']")
	private WebElementFacade operatorDropdown;

	@FindBy(xpath = "//input[@placeholder='Invoice #']")
	private WebElementFacade invoiceNumberTxtField;

	@FindBy(xpath = "//div[@class='form-group searchBtnOnLoad']/input[@type='submit']")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//*[@id='searchLoader']//div[@class='modal-body']/i")
	private WebElementFacade loadingSpinner;

	@FindBy(xpath = "//*[@id='dvAccountSearch']/child::table")
	private WebElementFacade searchAccountTable;

	@FindBy(xpath = "//*[@id='dvAccountSearch']/table/tbody/tr/td[2]/a")
	private List<WebElementFacade> listOfSearchedInvoiceId;

	@FindBy(xpath = "//*[@id='dvAccountSearch']/table/tbody/tr/td[4]")
	private List<WebElementFacade> listOfSearchedFacility;

	@FindBy(xpath = "//div[@id='visit']/h4")
	private WebElementFacade patientAndVisit;

	@FindBy(xpath = "//*[@id='lblInvoiceNo']")
	private WebElementFacade invoiceID;

	@FindBy(xpath = "//head/title")
	WebElementFacade searchPageTitle;

	@FindBy(xpath = "//input[@placeholder='Visit #']")
	private WebElementFacade visitTxtField;

	@FindBy(xpath = "//input[@placeholder='Medical Records #']")
	private WebElementFacade mRNTxtField;

	@FindBy(xpath = "//input[@placeholder='Claim #']")
	private WebElementFacade claimNumberTxtField;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElementFacade lastNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElementFacade firstNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='Social Security Number']")
	private WebElementFacade sSNTxtBox;

	@FindBy(id = "showErrorMsg")
	private WebElementFacade errorMsg;

	@FindBy(xpath = "//div[@class='tooltip top in']")
	private WebElementFacade toolTip;

	String titleJS = "return document.querySelector('#Head > title').text";
	String facilityCodeJS = "document.querySelector('#dnn_ctr1025_ModuleContent > span > span:nth-child(1)').textContent";

	public String getSearchPageTitle() {
		return evaluateJavascript(titleJS).toString();
	}

	public String getNoAccountsMessage() {
		return noAccountsMessage.getText();
	}

	public void searchBySelectText(String dropdown) {
		searchByDropdown.selectByVisibleText(dropdown);
	}

	public void selectOperatorValue(String operator) {
		operatorDropdown.selectByVisibleText(operator);
	}

	public void enterInvoiceNumber(String invoiceNumber) {
		invoiceNumberTxtField.type(invoiceNumber);
	}

	public void clickSubmitBtn() {
		submitBtn.click();
		if (loadingSpinner.isVisible()) {
			loadingSpinner.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
		}
	}

	public boolean isSearchAccTableVisible() {
		waitForAngularRequestsToFinish();
		return searchAccountTable.isVisible();
	}

	public List<String> getlistOfInvoiceID() {
		List<String> listOfInvoiceID = new ArrayList<>();
		for (WebElementFacade element : listOfSearchedInvoiceId) {
			listOfInvoiceID.add(element.getText());
		}
		return listOfInvoiceID;
	}

	public int getFacilityIndex() {
		/** Returns index of matched facility code **/
		String facilityCode = getFacilityCodeText();
		int index = 999;
		int size = listOfSearchedFacility.size();
		for (int i = 0; i < size; i++) {
			if (listOfSearchedFacility.get(i).getText().equals(facilityCode)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void clickSearchInvoiceID() {
		listOfSearchedInvoiceId.get(getFacilityIndex()).click();
	}

	public String getFacilityCodeText() {
		return evaluateJavascript(facilityCodeJS).toString();
	}

	public boolean isPatientAndVisitHeaderVisible() {
		/** return searched account's details is visible on the page or not **/
		waitForAngularRequestsToFinish();
		return patientAndVisit.isVisible();
	}

	public String getInvoiceID() {
		return invoiceID.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public String getDefaultSelectedVal() {
		return searchByDropdown.getSelectedVisibleTextValue();
	}

	public boolean isInvoiceNumberTxtFieldVisible() {
		return invoiceNumberTxtField.isVisible();
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

	public boolean isLastNameTxtFieldVisible() {
		return lastNameTxtBox.isVisible();
	}

	public void enterLastNameTxtBox(String lastName) {
		lastNameTxtBox.type(lastName);
	}

	public boolean isFirstNameTxtFieldVisible() {
		return firstNameTxtBox.isVisible();
	}

	public void enterFirstName(String firstName) {
		firstNameTxtBox.type(firstName);
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

	public String getToolTipText() {
		return toolTip.getText();
	}

	public boolean isSubmitBtnEnabled() {
		return submitBtn.isCurrentlyEnabled();
	}
}