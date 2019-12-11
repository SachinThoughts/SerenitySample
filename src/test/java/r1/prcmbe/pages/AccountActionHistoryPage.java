package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountActionHistoryPage extends PageObject{

	@FindBy(xpath="//ul[@id='notesHistory']//li[1]//span/span[2]")
	private List<WebElementFacade> listOfRecentAddedAccountActionHistoryVal;
	
	@FindBy(xpath="//ul[@id='notesHistory']//li[1]//span/span[1]")
	private List<WebElementFacade> listOfRecentAddedAccountActionHistoryLabel;
	
	@FindBy(xpath="//*[@id='accountactionhistory']//a[@class='flex-prev']")
	private WebElementFacade prevArrow;
	
	@FindBy(xpath="//*[@id='accountactionhistory']//a[@class='flex-next']")
	private WebElementFacade nextArrow;
	
	@FindBy(xpath="//*[@class='process-type-history']//span")
	private WebElementFacade defectSubCategoryType;
	
	@FindBy(xpath = "//*[@id='carousel']//li[last()]/div[last()]//a[@class='trigger']/..")
	private WebElementFacade addedBubble;
	
	@FindBy(xpath = "//div[@class='popover-content']//span//span[1]")
	private List<WebElementFacade> listOfEventCircleColumns;
	
	public List<String> getListOfRecentAddedAccountActionHistoryLabel(){
		List<String> listOfRecentAddedAccntLabel=new ArrayList<>();
		for (WebElementFacade recentAddedAccntLabel:listOfRecentAddedAccountActionHistoryLabel) {
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
	
	public void clickPreviousArrow() {
		if(prevArrow.isClickable()) {
			prevArrow.click();
		}
	}
	
	public boolean isPreviousDefectSubCategoryVisible() {
			return defectSubCategoryType.isVisible();
	}
	
	public String getHandoffBubbleColor() {
		withAction().moveToElement(addedBubble).build().perform();
		return addedBubble.getCssValue("background-color");
	}
	
	public void hoverOnAddedBubble() {
		withAction().moveToElement(addedBubble).build().perform();
	}
	
	public List<String> getListOfEventCircleColumns() {
		List<String> listOfEventCircleCols = new ArrayList<>();
		for (WebElementFacade eventCircleCol : listOfEventCircleColumns) {
			listOfEventCircleCols.add(eventCircleCol.getText().trim());
		}
		return listOfEventCircleCols;
	}
}