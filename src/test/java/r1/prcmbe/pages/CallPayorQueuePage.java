package r1.prcmbe.pages;

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
	private WebElementFacade expandArrowCallPayorQueue;

	@FindBy(id = "lblAccountNo")
	private WebElementFacade accountNo;

	@FindBy(xpath = "//*[@id='btn_info']/button/span")
	private WebElementFacade msgCloseButton;

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

	public void clickExpandArrowCallPayorQueue() {
		withAction().moveToElement(expandArrowCallPayorQueue).build().perform();
		evaluateJavascript("arguments[0].click();", expandArrowCallPayorQueue);
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
}