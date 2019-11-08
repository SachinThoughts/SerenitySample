package r1.prcmbe.pages;

import java.util.*;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountInformationPage extends PageObject {

	@FindBy(xpath = "//span[text()='Related Accounts']")
	private WebElementFacade relatedAccountBtn;

	@FindBy(xpath = "//*[@id='related']//*[@class='modal-body']//table/thead/tr/th")
	private List<WebElementFacade> relatedAcctSubheader;

	@FindBy(xpath = "//*[@id='related']//*[@class='modal-body']//table/tbody/tr[1]/td[1]")
	private WebElementFacade relatedAcctVisit;

	@FindBy(xpath = "//*[contains(@class,'patient-data patient-header')]/li[4]//span[2]")
	private WebElementFacade accountNumber;

	@FindBy(xpath = "//*[@id='related']//*[@class='modal-body']//table/tbody")
	private WebElementFacade relatedAcctTable;

	@FindBy(xpath = "//*[@id='related']//*[@class='modal-body']//table/tbody/tr/td//a")
	private List<WebElementFacade> listOfVisitNumbersOnRelatedAccntPopUp;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tbody/tr/td[3]")
	private List<WebElementFacade> listOfFacilityCodeOnRelatedAccntPopUp;

	@FindBy(xpath = "//*[@id='related']//div[@class='modal-content']")
	private WebElementFacade relatedAccountPoup;

	@FindBy(id = "lblPatientInfoFacilityCode")
	private WebElementFacade facilityCodeValue;

	@FindBy(xpath = "//*[@id='related']//*[@class='modal-body']//table/tbody/tr")
	private List<WebElementFacade> listOfVisits;

	@FindBy(id = "lblAccountNo")
	private WebElementFacade accountNoValue;

	@FindBy(id = "dnn_dnnLOGIN_loginLink")
	WebElementFacade logOut;

	@FindBy(xpath = "//a[@href='#accountDocs']//h4")
	private WebElementFacade documentLink;

	@FindBy(id = "lblInvoiceNo")
	private WebElementFacade invoiceNumber;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tbody/tr/td[2]//a")
	private List<WebElementFacade> listOfInvoiceNumbersOnRelatedAccntPopUp;

	@FindBy(id = "lnkHandOff")
	private WebElementFacade addHandOffBtn;

	@FindBy(id = "handOff")
	private WebElementFacade handOffPopUp;

	@FindBy(id = "ddlHandOffType")
	private WebElementFacade handOffTypeDrpDwn;

	@FindBy(id = "ddlHandOffType")
	private WebElementFacade handOffTypeDrpDown;

	public String getAccountNumber() {
		waitForAngularRequestsToFinish();
		return accountNumber.getText().trim();
	}

	public boolean isRelatedAccntPopUpVisible() {
		return relatedAccountPoup.isVisible();
	}

	public int getSizeOfRelatedAccntVisitNo() {
		return listOfVisitNumbersOnRelatedAccntPopUp.size();
	}

	public void clicksRelatedAccountButton() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", relatedAccountBtn);
		evaluateJavascript("arguments[0].click();", relatedAccountBtn);
	}

	public String getFacilityCodeValue() {
		waitForAngularRequestsToFinish();
		return facilityCodeValue.getText();
	}

	public String getAccountNoValue() {
		return accountNoValue.getText();
	}

	public int getSizeOfRelatedAccntFacilityCode() {
		return listOfFacilityCodeOnRelatedAccntPopUp.size();
	}

	public String clickOnRelatedAccntBasedOnFacilityAndFetchVisitNo(String facilityCode) {
		int size = listOfFacilityCodeOnRelatedAccntPopUp.size();
		String visitNo = "";
		for (int i = 0; i < size; i++) {
			if (listOfFacilityCodeOnRelatedAccntPopUp.get(i).getText().equals(facilityCode)) {
				visitNo = clickOnVisitNoOnRelatedAccntPopUpAndGetVisitNo(i);
				break;
			}
		}
		return visitNo;
	}

	public String clickOnVisitNoOnRelatedAccntPopUpAndGetVisitNo(int index) {
		String visitNo = listOfVisitNumbersOnRelatedAccntPopUp.get(index).getText();
		listOfVisitNumbersOnRelatedAccntPopUp.get(index).click();
		return visitNo;
	}

	public boolean checkLogoutVisible() {
		return logOut.isVisible();
	}

	public void logOut() {
		logOut.click();
	}

	public void clickOnDocumentLink() {
		documentLink.click();
	}

	public String getInvoiceNumber() {
		waitForAngularRequestsToFinish();
		return invoiceNumber.getText().trim();
	}

	public int getSizeOfRelatedAccntInvoiceNo() {
		return listOfVisitNumbersOnRelatedAccntPopUp.size();
	}

	public String clickRelatedAccountBasedOnFacilityCodeAndFetchInvoiceNo(String facilityCode) {
		int size = listOfFacilityCodeOnRelatedAccntPopUp.size();
		String visitNo = "";
		for (int i = 0; i < size; i++) {
			if (listOfFacilityCodeOnRelatedAccntPopUp.get(i).getText().equals(facilityCode)) {
				visitNo = clickOnVisitNoOnRelatedAccntPopUpAndGetVisitNo(i);
				break;
			}
		}
		return visitNo;
	}

	public String clickOnInvoiceNoOnRelatedAccntPopUpAndGetInvoiceNo(int index) {
		String visitNo = listOfInvoiceNumbersOnRelatedAccntPopUp.get(index).getText();
		listOfInvoiceNumbersOnRelatedAccntPopUp.get(index).click();
		return visitNo;
	}

	public void clickHandOffBtn() {
		addHandOffBtn.click();
	}

	public boolean isHandOffPopUpVisible() {
		return handOffPopUp.isVisible();
	}

	public void selectHandOffType(String handOffType) {
		handOffTypeDrpDwn.selectByVisibleText(handOffType);
	}

	public String getSelectedHandOffTypeValue() {
		return handOffTypeDrpDwn.getSelectedVisibleTextValue();
	}

	public void clickHandOffTypeDrpDown() {
		evaluateJavascript("arguments[0].click()", handOffTypeDrpDown);
	}

	public List<String> getHandOffTypeDrpDownValues() {
		return handOffTypeDrpDown.getSelectOptions();
	}
}