package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CallPayorQueuePage extends PageObject {

	@FindBy(xpath = "//*[@id='btnAddtoCallPayerQueue']/span/i[2]")
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

	@FindBy(xpath = "//*[@id='tbodySELFSELFPAY']/div/a[1]/span[1]")
	private List<WebElementFacade> listOfAccountsInCallPayorQueue;

	@FindBy(xpath = "//*[@id='divCallQueue']/div[1]/a[4]/i")
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

	@FindBy(xpath = "//li[@id='SELFPayers']/div/div/a[2]/i")
	private List<WebElementFacade> removeCallPayerQueueAccountBtnList;

	@FindBy(xpath = "//*[@id='SELFPayers']/div/div/a[1]/span[1]")
	private List<WebElementFacade> callPayerQueueInvoiceList;

	public void clickAddtoCallPayorQueueBtn() {
		withAction().moveToElement(addToQueueIcon).build().perform();
		evaluateJavascript("arguments[0].click();", addToQueueIcon);
	}

	public String getCallToQueuePopupTitle() {
		return addToCallQueuePopup.getText().trim();
	}

	public void clickAddPayerQueueWithoutNoteBtn() {
		evaluateJavascript("arguments[0].click();", addPayerQueueWithoutNoteBtn);
	}

	public String getInfoMessageText() {
		return infoMessage.getText().trim();
	}

	public String getCallQueAmnt() {
		return callQueAmnt.getText().trim();
	}

	public String getDefectClassification() {
		return defectClassification.getText().trim();
	}

	public List<String> getListOfAccountsInCallPayorQueue() {
		List<String> listOfAcctInCallPayorQueue = new ArrayList<>();
		for (WebElementFacade acctInCallPayorQueue : listOfAccountsInCallPayorQueue) {
			listOfAcctInCallPayorQueue.add(acctInCallPayorQueue.getText().trim());
		}
		return listOfAcctInCallPayorQueue;
	}

	public void clickToggleCallQueueBtn() {
		withAction().moveToElement(toggleCallQueueBtn).build().perform();
		evaluateJavascript("arguments[0].click();", toggleCallQueueBtn);
	}

	public String getCountOfAccountsInCallPayorQueue() {
		return callQueAmnt.getText().trim();
	}

	public String getAccountNo() {
		return accountNo.getText().trim();
	}

	public void closeMsgButton() {
		evaluateJavascript("arguments[0].click();", msgCloseButton);
	}

	public int getCallPayerQueueCount() {
		return callPayerQueueList.size();
	}

	public void clickCallPayerListExpandBtn() {
		if (getCallPayerQueueCount() > 0 && callPayerListHidden.isVisible()) {
			evaluateJavascript("arguments[0].click();", expandCallPayerListBtn);
		} else {
			clickAddtoCallPayorQueueBtn();
			clickAddPayerQueueWithoutNoteBtn();
		}
	}

	public void clickRemoveCallPayerQueueAccountBtn() {
		if (getCallPayerQueueCount() > 0) {
			evaluateJavascript("arguments[0].click();", removeCallPayerQueueAccountBtnList.get(0));
		}
	}

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

	public String getRemovedInvoice() {
		String[] invoice = callPayerQueueInvoiceList.get(0).getText().trim().split("-\\s");
		return invoice[1];
	}

	public boolean isCallPayerQueueInvoiceVisible() {
		infoMessage.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible();
		clickToggleCallQueueBtn();
		return callPayerQueueInvoiceList.get(0).isVisible();
	}
}