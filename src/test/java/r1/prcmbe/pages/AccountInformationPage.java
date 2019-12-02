package r1.prcmbe.pages;

import java.time.Duration;
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

	@FindBy(id = "lblBreadcrumb")
	private WebElementFacade defectBreadcrumb;

	@FindBy(xpath = "//fieldset[not(contains(@style,'display: none')) or not(@style)]//a//span[text()='Next']")
	private WebElementFacade defectWorflowNextBtn;

	@FindBy(xpath = "//*[@id='cblActionsRequired']/label")
	private List<WebElementFacade> sOPList;

	@FindBy(xpath = "//*[@id='btnVerifyNextStep']/span[1]")
	private WebElementFacade nextBtn;

	@FindBy(id = "ddlHandoffDirection")
	private WebElementFacade handoffCreateDrpdwn;

	@FindBy(id = "ddlAction")
	private WebElementFacade handoffWhyDrpdwn;

	@FindBy(id = "ddlDisposition")
	private WebElementFacade dispositionDrpdwn;

	@FindBy(id = "txtHandOffNotes")
	private WebElementFacade handoffNotesTxtBox;

	@FindBy(id = "btnSaveHandsOff")
	private WebElementFacade saveHandoffBtn;

	@FindBy(id = "msg_success")
	private WebElementFacade handoffSavedMessage;

	@FindBy(id = "btnShowHistory")
	private WebElementFacade showAccountActionHistoryBtn;

	@FindBy(xpath = "//h3[text()='Account Action History']")
	private WebElementFacade accntActionHistoryHeader;

	@FindBy(xpath = "//label[text()='Hand Off Type']")
	private WebElementFacade handoffTypeLabel;

	@FindBy(xpath = "//label[text()='Create:']")
	private WebElementFacade createLabel;

	@FindBy(xpath = "//label[text()='Note:']")
	private WebElementFacade noteLabel;

	@FindBy(xpath = "//*[@id='handOff']/div/div/div[3]/button")
	private WebElementFacade closeBtnOnHandoffPopup;

	@FindBy(id = "btnSaveHandsOff_nextaccount")
	private WebElementFacade saveAndMoveNxtAccntBtn;

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

	public String getDefectSubcategoryBreadcrumb() {
		String defectLabel = defectBreadcrumb.getText().trim();
		String[] defectSubcategory = defectLabel.split(">>\\s");
		return defectSubcategory[1];
	}

	public void clickDefectWorkflowNextBtn() {
		defectWorflowNextBtn.click();
	}

	public void clickNextBtn() {
		if (nextBtn.isVisible()) {
			nextBtn.withTimeoutOf(Duration.ofSeconds(60)).click();
		}
	}

	public List<String> getSOPList() {
		List<String> sOPNamesList = new ArrayList<>();
		for (WebElementFacade sOP : sOPList) {
			sOPNamesList.add(sOP.getText().trim());
		}
		return sOPNamesList;
	}

	public void clickOnCreateDrpdwn() {
		handoffCreateDrpdwn.click();
	}

	public List<String> getListOfCreateDrpdwnVal() {
		return handoffCreateDrpdwn.getSelectOptions();
	}

	public String getDefaultValueForCreateDrpdwn() {
		return handoffCreateDrpdwn.getSelectedVisibleTextValue();
	}

	public void selectFromCreateDrpdwn(String createValue) {
		handoffCreateDrpdwn.selectByVisibleText(createValue);
	}

	public String getSelectedValueForCreateDrpdwn() {
		return handoffCreateDrpdwn.getSelectedVisibleTextValue();
	}

	public boolean isWhyDrpdwnVisible() {
		return handoffWhyDrpdwn.isVisible();
	}

	public void clickOnWhyDrpdown() {
		handoffWhyDrpdwn.click();
	}

	public List<String> getListOfWhyDrpdwnVal() {
		return handoffWhyDrpdwn.getSelectOptions();
	}

	public void selectFromWhyDrpdwn(String whyValue) {
		handoffWhyDrpdwn.selectByVisibleText(whyValue);
	}

	public boolean isDispositionDrpdwnVisible() {
		return dispositionDrpdwn.isVisible();
	}

	public void enterValueInNoteTxtField(String textValue) {
		handoffNotesTxtBox.type(textValue);
	}

	public void clickOnSaveHandoffBtn() {
		saveHandoffBtn.click();
	}

	public String getHandoffSavedMessage() {
		return handoffSavedMessage.getText();
	}

	public void scrollToAccountActionHistory() {
		evaluateJavascript("arguments[0].scrollIntoView();", accntActionHistoryHeader);
	}

	public void clickOnShowAccountActionBtn() {
		showAccountActionHistoryBtn.click();
	}

	public String getTagNameForNotesTxtBox() {
		return handoffNotesTxtBox.getTagName();
	}

	public boolean isHandoffTypeLabelVisible() {
		return handoffTypeLabel.isVisible();
	}

	public boolean isCreateLabelVisible() {
		return createLabel.isVisible();
	}

	public boolean isNoteLabelVisible() {
		return noteLabel.isVisible();
	}

	public boolean isCloseBtnOnHandoffPopupVisible() {
		return closeBtnOnHandoffPopup.isVisible();
	}

	public boolean isSaveBtnOnHandoffPopupVisible() {
		return saveHandoffBtn.isVisible();
	}

	public boolean isSaveAndMoveToNxtAccntBtnOnHandoffPopupVisible() {
		return saveAndMoveNxtAccntBtn.isVisible();
	}

	public List<String> getListOfDispositionDrpdwnVal() {
		return dispositionDrpdwn.getSelectOptions();
	}

	public void selectFromDispositionDrpdwn(String dispositionVal) {
		dispositionDrpdwn.selectByVisibleText(dispositionVal);
	}
}