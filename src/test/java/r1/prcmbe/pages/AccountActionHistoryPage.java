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
	
}