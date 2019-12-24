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

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/child::table")
	private WebElementFacade searchAccountTable;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/table/tbody/tr/td[2]/a")
	private List<WebElementFacade> listOfSearchedInvoiceId;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/table/tbody/tr/td[4]")
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

	@FindBy(xpath = "//select[@class='form-control ddlOperator']")
	private WebElementFacade operator;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/table/tbody/tr/td[2]")
	private List<WebElementFacade> listOfSearchedInvNum;

	@FindBy(id = "msg_info")
	private WebElementFacade errorAlert;

	@FindBy(xpath = "//div[@class = 'alert alert-info']//button")
	private WebElementFacade closeErrorAlert;

	@FindBy(xpath = "//span[@id='lblInvoiceNo']")
	private WebElementFacade invoiceNumber;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/child::table/thead/tr/th")
	private List<WebElementFacade> listOfSrchAccTblHeaders;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/child::table/tbody/tr/td[3]")
	private List<WebElementFacade> listOfSearchedNames;

	@FindBy(id = "lblPatientName")
	private WebElementFacade patientName;

	@FindBy(id = "lblAccountNo")
	private WebElementFacade patientAccountNo;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/table/tbody/tr/td[1]")
	private List<WebElementFacade> listOfSearchedAccNum;

	@FindBy(xpath = "//*[@id='dvAccountSearch' or @class='modal-body']/table/tbody/tr/td[5]")
	private List<WebElementFacade> listOfSearchedMRN;

	@FindBy(id = "lblMRN")
	private WebElementFacade patientMRN;

	@FindBy(id = "lblSSN")
	private WebElementFacade patientSSN;

	String titleJS = "return document.querySelector('#Head > title').text";
	String facilityCodeJS = "return document.querySelector('#dnn_ctr1025_ModuleContent > span > span:nth-child(1)').textContent";

	/**
	 * @return the text title of the search page
	 */
	public String getSearchPageTitle() {
		return evaluateJavascript(titleJS).toString();
	}

	public String getNoAccountsMessage() {
		return noAccountsMessage.getText();
	}

	public void searchBySelectText(String dropdown) {
		searchByDropdown.selectByVisibleText(dropdown);
	}

	public void selectOperatorValue(String operator)  {
		operatorDropdown.selectByVisibleText(operator);
	}

	public void enterInvoiceNumber(String invoiceNumber) {
		invoiceNumberTxtField.type(invoiceNumber);
	}

	/**
	 * click on the submit button
	 */
	public void clickSubmitBtn() {
		submitBtn.click();
		if (loadingSpinner.isVisible()) {
			loadingSpinner.withTimeoutOf(Duration.ofSeconds(80)).waitUntilNotVisible();
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
		int index = 0;
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

	public boolean isToolTipVisible() {
		return toolTip.isVisible();
	}

	/**
	 * @param operatorValue select the operator value
	 */
	public void operatorSelectText(String operatorValue) {
		operator.selectByVisibleText(operatorValue);
	}

	public void clickSearchInvoiceNumber() {
		listOfSearchedInvNum.get(getFacilityIndex()).click();
		if (isErrorMsgVisible()) {
			clickErrorMsg();
		}
	}

	public boolean isErrorMsgVisible() {
		return errorAlert.isVisible();
	}

	public void clickErrorMsg() {
		closeErrorAlert.click();
	}

	/**
	 * @return get list of the Invoice numbers
	 */
	public List<String> getlistOfInvNum() {
		waitForAngularRequestsToFinish();
		List<String> listOfInvNum = new ArrayList<>();
		for (WebElementFacade invoiceNoElement : listOfSearchedInvNum) {
			listOfInvNum.add(invoiceNoElement.getText());
		}
		return listOfInvNum;
	}

	public String getInvoiceNumber() {
		return invoiceNumber.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public void enterLastName(String lastName) {
		lastNameTxtBox.type(lastName);
	}

	public List<String> getListOfSrchAccTblHeaders() {
		List<String> listOfTblHeaders = new ArrayList<>();
		for (WebElementFacade headerName : listOfSrchAccTblHeaders) {
			listOfTblHeaders.add(headerName.getText());
		}
		return listOfTblHeaders;
	}

	public List<String> getListOfSearchedNames() {
		List<String> listOfNames = new ArrayList<>();
		for (WebElementFacade searchedName : listOfSearchedNames) {
			listOfNames.add(searchedName.getText());
		}
		return listOfNames;
	}

	public String getPatientName() {
		return patientName.getText();
	}

	public String getPatientLastName() {
		String[] lastName = patientName.getText().split(",", 0);
		return lastName[0].trim();
	}

	public String getPatientFirstName() {
		String[] firstName = patientName.getText().split(",", 0);
		return firstName[1].trim();
	}

	public List<String> getlistOfSearchedFacility() {
		List<String> listOfFacilities = new ArrayList<>();
		for (WebElementFacade facilityCode : listOfSearchedFacility) {
			listOfFacilities.add(facilityCode.getText());
		}
		return listOfFacilities;
	}

	public String getPatientAccountNo() {
		return patientAccountNo.getText();
	}

	public void clickSearchInvoiceIdOrVisitNumber() {
		int index = getFacilityIndex();
		if (!listOfSearchedInvNum.get(index).getText().equals("N/A"))
			listOfSearchedInvoiceId.get(index).click();
		else
			listOfSearchedAccNum.get(index).click();
	}

	public List<String> getlistOfAccNum() {
		waitForAngularRequestsToFinish();
		List<String> listOfAccNum = new ArrayList<>();
		for (WebElementFacade element : listOfSearchedAccNum) {
			listOfAccNum.add(element.getText());
		}
		return listOfAccNum;
	}

	public String getAccountNumber() {
		return patientAccountNo.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public void invoiceNumberShouldNotVisible() {
		invoiceNumber.shouldNotBeVisible();
	}

	public List<String> getlistOfMRN() {
		waitForAngularRequestsToFinish();
		List<String> listOfMRN = new ArrayList<>();
		for (WebElementFacade mRNNoElement : listOfSearchedMRN) {
			listOfMRN.add(mRNNoElement.getText());
		}
		return listOfMRN;
	}

	public String getMRNText() {
		return patientMRN.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public String getPatientSSN() {
		return patientSSN.getText();
	}

	public String getPatientMRN() {
		return patientMRN.getText();
	}
	
	public void waitForSpinnerToDisappear() {
		loadingSpinner.withTimeoutOf(Duration.ofSeconds(80)).waitUntilNotVisible();
	}
}