package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountActionHistoryPage extends PageObject {

	@FindBy(xpath = "//ul[@id='notesHistory']//li[1]//span/span[2]")
	private List<WebElementFacade> listOfRecentAddedAccountActionHistoryVal;

	@FindBy(xpath = "//ul[@id='notesHistory']//li[1]//span/span[1]")
	private List<WebElementFacade> listOfRecentAddedAccountActionHistoryLabel;

	@FindBy(xpath = "//button[@id='btnShowHistory']/span[text()='Show Account Action History Notes']")
	private WebElementFacade showAccHistoryBtn;

	@FindBy(id = "accountactionhistory")
	private WebElementFacade accActionHistorySection;

	@FindBy(id = "lblNoHistory")
	private WebElementFacade noAccActionHistoryMsgLbl;

	@FindBy(xpath = "//*[@id='notesHistory']/li[1]/div/div/div[1]/span")
	private WebElementFacade notesLabel;

	@FindBy(xpath = "//*[@id='carousel']//li[last()]/div[last()]//a[@class='trigger']/..")
	private WebElementFacade latestBubble;

	@FindBy(xpath = "//*[@class='popover fade top in']/div[2]/ul/li/div/div/div[1]/span")
	private WebElementFacade popoverTitle;
	
	@FindBy(xpath = "//*[@id='accountactionhistory']//i[@class='fa toggle fa-chevron-right']")
	private WebElementFacade expandIcon;

	public List<String> getListOfRecentAddedAccountActionHistoryLabel() {
		List<String> listOfRecentAddedAccntLabel = new ArrayList<>();
		for (WebElementFacade recentAddedAccntLabel : listOfRecentAddedAccountActionHistoryLabel) {
			listOfRecentAddedAccntLabel.add(recentAddedAccntLabel.getText());
		}
		return listOfRecentAddedAccntLabel;
	}

	public String getRecentAddedAccountActionHistoryValue(int positionOfElement) {
		return listOfRecentAddedAccountActionHistoryVal.get(positionOfElement).getText();
	}

	public boolean isRecentAddedAccountActionHistoryLabelVisible(int labelPosition) {
		return listOfRecentAddedAccountActionHistoryLabel.get(labelPosition).isVisible();
	}

	public void ifVisibleClickShowAccHistoryBtn() {
		if (showAccHistoryBtn.isVisible()) {
			evaluateJavascript("arguments[0].click();", showAccHistoryBtn);
		}
	}

	/**
	 * scrolls to Account Action History section
	 */
	public void scrollToAccActionHistorySection() {
		withAction().moveToElement(accActionHistorySection).build().perform();
	}

	/**
	 * @return No Account Action History message
	 */
	public String getNoAccActionHistoryMsg() {
		return noAccActionHistoryMsgLbl.getText();
	}

	public boolean isNotesLabelVisible() {
		return notesLabel.isVisible();
	}

	public String getNotesLabel() {
		return notesLabel.getText().trim();
	}

	public void hoverOverLatestBubble() {
		evaluateJavascript("arguments[0].scrollIntoView();", latestBubble);
		withAction().moveToElement(latestBubble).build().perform();
	}

	public boolean getPopoverTitle() {
		return popoverTitle.isVisible();
	}
	
	/**
	 * This method clicks on expand icon of Account action history incase it is collapsed 
	 */
	public void expandAccountActionHistorySection() {
		if(expandIcon.isVisible()) {
			expandIcon.click();
		}
	}
}