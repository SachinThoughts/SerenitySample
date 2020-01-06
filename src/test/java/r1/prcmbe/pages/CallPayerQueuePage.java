package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CallPayerQueuePage extends PageObject {

	@FindBy(id = "btnAddtoCallPayerQueue")
	private WebElementFacade addToQueueIcon;

	@FindBy(id = "addToCallQueueLabel")
	private WebElementFacade addToCallQueuePopup;

	@FindBy(id = "btnAddPayerQueueWithoutNote")
	private WebElementFacade addPayerQueueWithoutNoteBtn;

	@FindBy(id = "msg_info")
	private WebElementFacade infoMessage;

	@FindBy(id = "callQueAmnt")
	private WebElementFacade callQueAmnt;

	@FindBy(id = "lblBreadcrumb")
	private WebElementFacade defectClassification;

	@FindBy(xpath = "//*[contains(@id,'tbodySELF')]/div/a[1]/span[@class='InvoiceNo']")
	private List<WebElementFacade> listOfAccountsInCallPayorQueue;

	@FindBy(xpath = "//*[@id='divCallQueue']/div[@class='call-queue-heading']/a[@class='toggle']/i")
	private WebElementFacade toggleCallQueueBtn;

	@FindBy(id = "lblAccountNo")
	private WebElementFacade accountNo;

	@FindBy(xpath = "//*[@id='btn_info']/button/span")
	private WebElementFacade msgCloseButton;

	@FindBy(xpath = "//*[@id='SELFPayers']/div")
	private List<WebElementFacade> callPayerQueueList;

	@FindBy(xpath = "//*[@id='CPAccountsList']/div/ul[contains(@style,'none')]")
	private WebElementFacade callPayerListHidden;

	@FindBy(xpath = "//*[@id='SELF']/i")
	private WebElementFacade expandCallPayerListBtn;

	@FindBy(xpath = "//*[@class='remove-queue']/i")
	private List<WebElementFacade> removeCallPayerQueueAccountBtnList;

	@FindBy(id = "ddlApprovalType")
	private WebElementFacade category;

	@FindBy(id = "ddlApprovalCategory")
	private WebElementFacade writeOffType;

	@FindBy(id = "txtWriteOffAmount")
	private WebElementFacade writeOffAmount;

	@FindBy(id = "txWriteOffNotes")
	private WebElementFacade writeOffNotes;

	@FindBy(id = "btnSaveWriteOff")
	private WebElementFacade saveWriteOffBtn;

	@FindBy(id = "ddlTcode")
	private WebElementFacade tCodeToUse;

	@FindBy(xpath = "//table[@id='gvWriteOff']/tbody/tr[2]/td[1]")
	private WebElementFacade createdWriteOffAmount;

	@FindBy(xpath = "//table[@id='gvWriteOff']/tbody/tr[2]/td[3]")
	private WebElementFacade createdTCode;

	@FindBy(id = "btnSaveTransactionResponse")
	private WebElementFacade approvalResponseSubmitBtn;

	@FindBy(id = "rdoRejected")
	private WebElementFacade denyRadioBtn;

	@FindBy(id = "gvWriteOff")
	private WebElementFacade approvalRequestTbl;

	@FindBy(xpath = "//*[@class='callPayerList']/descendant::span[@class='InvoiceNo']")
	private WebElementFacade invoiceNumberCPQ;

	@FindBy(id = "btnOK")
	private WebElementFacade appReviewSaveBtn;

	@FindBy(xpath = "//*[@id='gvWriteOffReview']/tbody/tr[2]/td[2]")
	private WebElementFacade reviewerName;

	@FindBy(xpath = "//*[@id='gvWriteOffReview']/tbody/tr[2]/td[4]")
	private WebElementFacade reviewStatus;

	@FindBy(id = "rdoApproved")
	private WebElementFacade approveRadioBtn;

	@FindBy(xpath = "//*[@id='recentlyWorkedLabel']/following::div[@id='dvRecentWorkList']/table")
	private WebElementFacade recentlyWorkedAccPopup;

	@FindBy(xpath = "//*[@id='dvRecentWorkList']/table/tbody/tr/td[4]/a")
	private List<WebElementFacade> listOfRecentlyWorkedInvNum;

	@FindBy(id = "txtAddCallPayerQueueNote")
	private WebElementFacade noteTxtBoxCPQ;

	@FindBy(id = "btnAddPayerQueueWithNote")
	private WebElementFacade addPayerQueueWithNoteBtn;

	@FindBy(id = "writeOffLabel")
	private WebElementFacade approvalPopup;

	@FindBy(xpath = "//*[@id='writeOff']/div/div/div[1]/button/span[text()='Close']")
	private WebElementFacade approvalPopupCloseBtn;

	/**
	 * Clicks on Add to Call Payer Queue button
	 */
	public void clickAddtoCallPayorQueueBtn() {
		withAction().moveToElement(addToQueueIcon).build().perform();
		evaluateJavascript("arguments[0].click();", addToQueueIcon);
	}

	/**
	 * Captures Call to queue Pop up title
	 * 
	 * @return String This returns Call to queue Pop up title
	 */
	public String getCallToQueuePopupTitle() {
		return addToCallQueuePopup.getText().trim();
	}

	/**
	 * Clicks on Without note button
	 */
	public void clickAddPayerQueueWithoutNoteBtn() {
		evaluateJavascript("arguments[0].click();", addPayerQueueWithoutNoteBtn);
	}

	/**
	 * Captures alert message
	 * 
	 * @return String This returns alert message
	 */
	public String getInfoMessageText() {
		return infoMessage.getText().trim();
	}

	/**
	 * Captures Call queue amount
	 * 
	 * @return String This returns Call queue amount
	 */
	public String getCallQueAmnt() {
		return callQueAmnt.getText().trim();
	}

	/**
	 * Captures Defect classification info
	 * 
	 * @return String This returns Defect classification info
	 */
	public String getDefectClassification() {
		return defectClassification.getText().trim();
	}

	/**
	 * Captures Accounts in Call Payer Queue
	 * 
	 * @return List This returns list of defect classification info
	 */
	public List<String> getListOfAccountsInCallPayorQueue() {
		List<String> listOfAcctInCallPayorQueue = new ArrayList<>();
		for (WebElementFacade acctInCallPayorQueue : listOfAccountsInCallPayorQueue) {
			listOfAcctInCallPayorQueue.add(acctInCallPayorQueue.getText().trim());
		}
		return listOfAcctInCallPayorQueue;
	}

	/**
	 * Clicks on Toggle Call Queue button
	 */
	public void clickToggleCallQueueBtn() {
		withAction().moveToElement(toggleCallQueueBtn).build().perform();
		evaluateJavascript("arguments[0].click();", toggleCallQueueBtn);
	}

	/**
	 * Captures count of accounts in call queue
	 * 
	 * @return String This returns Count of Accounts in Call Queue
	 */
	public String getCountOfAccountsInCallPayorQueue() {
		return callQueAmnt.getText().trim();
	}

	/**
	 * Captures account number
	 * 
	 * @return String This returns Account number
	 */
	public String getAccountNo() {
		return accountNo.getText().trim();
	}

	/**
	 * Clicks on Close message button
	 */
	public void closeMsgButton() {
		evaluateJavascript("arguments[0].click();", msgCloseButton);
	}

	/**
	 * Captures call payer queue count
	 * 
	 * @return int This returns Call Payer queue count
	 */
	public int getCallPayerQueueCount() {
		return callPayerQueueList.size();
	}

	/**
	 * Clicks on Call Payer Queue Expand button
	 */
	public void clickCallPayerListExpandBtn() {
		if (getCallPayerQueueCount() > 0 && callPayerListHidden.isVisible()) {
			evaluateJavascript("arguments[0].click();", expandCallPayerListBtn);
		} else {
			clickAddtoCallPayorQueueBtn();
			clickAddPayerQueueWithoutNoteBtn();
		}
	}

	/**
	 * Clicks on Remove Call Payer Queue button
	 */
	public void clickRemoveCallPayerQueueAccountBtn() {
		if (getCallPayerQueueCount() > 0) {
			evaluateJavascript("arguments[0].click();", removeCallPayerQueueAccountBtnList.get(0));
		}
	}

	/**
	 * Checks List of Remove Call Payer Queue Account button visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isRemoveCallPayerQueueAccountBtnListVisible() {
		infoMessage.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible();
		boolean removeBtnVisibility = false;
		for (WebElementFacade removeBtn : removeCallPayerQueueAccountBtnList) {
			if (removeBtn.isVisible()) {
				removeBtnVisibility = true;
			} else {
				removeBtnVisibility = false;
			}
		}
		return removeBtnVisibility;
	}

	/**
	 * Captures removed invoice number from call payer queue
	 * 
	 * @return String This returns invoice number
	 */
	public String getRemovedInvoice() {
		String[] invoice = listOfAccountsInCallPayorQueue.get(0).getText().trim().split("-\\s");
		return invoice[1];
	}

	/**
	 * Checks Call Payer Queue Invoice visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isCallPayerQueueInvoiceVisible() {
		infoMessage.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible();
		clickToggleCallQueueBtn();
		return listOfAccountsInCallPayorQueue.get(0).isVisible();
	}

	/**
	 * Selects value from category drop down
	 * 
	 * @param categoryText This parameter is used to pass Category value
	 */
	public void categorySelectByText(String categoryText) {
		category.selectByVisibleText(categoryText);
	}

	/**
	 * Selects value from WriteOff Type drop down
	 * 
	 * @param writeOffTypeText This parameter is used to pass WriteOff Type value
	 */
	public void writeOffTypeSelectByText(String writeOffTypeText) {
		writeOffType.selectByVisibleText(writeOffTypeText);
	}

	/**
	 * Enters WriteOff amount in textbox
	 * 
	 * @param amount This parameter is used to pass WriteOff amount
	 */
	public void enterWriteOffAmount(String amount) {
		writeOffAmount.type(amount);
	}

	/**
	 * Enters values in writeoff notes textbox
	 * 
	 * @param notes This parameter is used to pass WriteOff notes value
	 */
	public void enterWriteOffNotes(String notes) {
		writeOffNotes.type(notes);
	}

	/**
	 * Clicks on Save Write Off button
	 */
	public void clickSaveWriteOffBtn() {
		saveWriteOffBtn.click();
	}

	/**
	 * Selects TCode value from drop down
	 * 
	 * @param tCode This parameter is used to pass TCode value
	 */
	public void tCodeSelectByText(String tCode) {
		tCodeToUse.selectByVisibleText(tCode);
	}

	/**
	 * Captures created WriteOff amount
	 * 
	 * @return String This returns WriteOff amount
	 */
	public String getCreatedWriteOffAmount() {
		return createdWriteOffAmount.getText();
	}

	/**
	 * Captures TCode value
	 * 
	 * @return String This returns TCode value
	 */
	public String getCreatedTCode() {
		return createdTCode.getText();
	}

	/**
	 * Clicks on Approval Response Submit button
	 */
	public void clickApprovalResponseSubmitBtn() {
		approvalResponseSubmitBtn.click();
	}

	/**
	 * Clicks on Deny Radio button
	 */
	public void clickDenyRadioBtn() {
		denyRadioBtn.click();
	}

	/**
	 * Hovers over Approval Request table
	 */
	public void moveToApprovalRequestTbl() {
		withAction().moveToElement(approvalRequestTbl).build().perform();
	}

	/**
	 * Captures invoice number from Call Payer Queue
	 * 
	 * @return String This returns Invoice number
	 */
	public String getInvoiceNumberCPQ() {
		return invoiceNumberCPQ.getText();
	}

	/**
	 * Checks Invoice Number CPQ visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isInvoiceNumberCPQVisible() {
		return invoiceNumberCPQ.isVisible();
	}

	/**
	 * Clicks on Approve Review Save button
	 */
	public void clickAppReviewSaveBtn() {
		appReviewSaveBtn.click();
	}

	/**
	 * Captures reviewer name
	 * 
	 * @return String This returns reviewer name
	 */
	public String getReviewerName() {
		return reviewerName.getText();
	}

	/**
	 * Captures review status
	 * 
	 * @return String This returns Review Status
	 */
	public String getReviewStatus() {
		return reviewStatus.getText();
	}

	/**
	 * Clicks on Approve Radio button
	 */
	public void clickApproveRadioBtn() {
		approveRadioBtn.click();
	}

	/**
	 * Checks Add to Call Payer Queue button visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isAddToCallPayerQueueBtnDisabled() {
		return (boolean) evaluateJavascript("return document.querySelector('#btnAddtoCallPayerQueue').disabled");
	}

	/**
	 * Checks Recently Worked Account pop up visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isRecentlyWorkedAccPopupVisible() {
		return recentlyWorkedAccPopup.isVisible();
	}

	/**
	 * Captures list of recently worked invoice number
	 * 
	 * @return List This returns list of invoice number
	 */
	public List<String> getListOfRecentlyWorkedInvNum() {
		List<String> listOfInvNum = new ArrayList<>();
		for (WebElementFacade recentlyWorkedInvNum : listOfRecentlyWorkedInvNum) {
			listOfInvNum.add(recentlyWorkedInvNum.getText());
		}
		return listOfInvNum;
	}

	/**
	 * Checks Call Queue Pop Up visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isCallQueuePopupVisible() {
		return addToCallQueuePopup.isVisible();
	}

	/**
	 * Enters values in notes textbox
	 * 
	 * @param noteText This parameter is used to pass note value
	 */
	public void enterNoteTxtBoxCPQ(String noteText) {
		noteTxtBoxCPQ.type(noteText);
	}

	/**
	 * Clicks on Add to Call Payer Queue With Note button
	 */
	public void clickAddCPQWithNoteBtn() {
		addPayerQueueWithNoteBtn.click();
	}

	/**
	 * Captures list of invoice in call payer queue
	 * 
	 * @return List This returns list of invoices
	 */
	public List<String> getCallPayerQueueInvoiceList() {
		List<String> cPQInvoiceNumberList = new ArrayList<>();
		for (WebElementFacade invoiceNumber : listOfAccountsInCallPayorQueue) {
			cPQInvoiceNumberList.add(invoiceNumber.getText());
		}
		return cPQInvoiceNumberList;
	}

	/**
	 * Checks Approval Pop up visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isApprovalPopupVisisble() {
		return approvalPopup.isVisible();
	}

	/**
	 * Checks Category visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isCategoryVisible() {
		return category.isVisible();
	}

	/**
	 * Checks Write Off Type visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isWriteOffTypeVisible() {
		return writeOffType.isVisible();
	}

	/**
	 * Checks T Code To Use Type visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isTCodeToUseTypeVisible() {
		return tCodeToUse.isVisible();
	}

	/**
	 * Checks Write Off amount visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isWriteOffAmountVisible() {
		return writeOffAmount.isVisible();
	}

	/**
	 * Checks Write Off notes visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isWriteOffNotesVisible() {
		return writeOffNotes.isVisible();
	}

	/**
	 * Checks Approval Pop up close button visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isApprovalPopupCloseBtnVisible() {
		return approvalPopupCloseBtn.isVisible();
	}

	/**
	 * Checks Save Write Off button visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isSaveWriteOffBtnVisible() {
		return saveWriteOffBtn.isVisible();
	}
}