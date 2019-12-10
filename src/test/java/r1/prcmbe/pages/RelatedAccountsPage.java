package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class RelatedAccountsPage extends PageObject {

	@FindBy(id = "relatedInstances")
	private WebElementFacade relatedAccountsBtn;

	@FindBy(id = "relatedLabel")
	private WebElementFacade relatedAccountsPopupLbl;

	@FindBy(xpath = "//button[text()='Search']")
	private WebElementFacade searchBtn;

	@FindBy(xpath = "//*[@id='AccountFirst']/a")
	private List<WebElementFacade> firstBtnList;
	
	@FindBy(xpath = "//*[@id='AccountPrevious']/a")
	private List<WebElementFacade> previousBtnList;
	
	@FindBy(xpath = "//*[@id='AccountPageNext']/a")
	private List<WebElementFacade> nextBtnList;
	
	@FindBy(xpath = "//*[@id='AccountPageLast']/a")
	private List<WebElementFacade> lastBtnList;
	
	@FindBy(xpath = "//*[@class='jp-current']")
	private WebElementFacade defaultPage;
	
	@FindBy(xpath = "//*[@id='tbRelatedAccount']//th")
	private List<WebElementFacade> relatedAccountPopupHeaderList;
	
	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[1]")
	private List<WebElementFacade> relatedVisitsList;
	
	@FindBy(xpath = "(//*[@id='AccountPageNext' and @class='disabled']/a)[1]")
	private WebElementFacade nextBtnDisabled;
	
	@FindBy(xpath = "(//*[@id='AccountPageNext']/a)[1]")
	private WebElementFacade nextBtn;
	
	@FindBy(xpath = "//*[@id='loadingDiv']/i")
	private WebElementFacade paginationSpinner;

	public void clickRelatedAccountsBtn() {
		relatedAccountsBtn.click();
	}

	public String getRelatedAccPopupLabelTxt() {
		return relatedAccountsPopupLbl.getText();
	}

	public boolean isSearchBtnVisible() {
		return searchBtn.isVisible();
	}

	public boolean isFirstBtnVisible() {
		if (firstBtnList.size() == 2) {
			return true;
		}
		return false;
	}
	
	public boolean isPreviousBtnVisible() {
		if(previousBtnList.size()==2) {
			return true;
		}
		return false;
	}
	
	public boolean isNextBtnVisible() {
		if(nextBtnList.size()==2) {
			return true;
		}
		return false;
	}
	
	public boolean isLastBtnVisible() {
		if(lastBtnList.size()==2) {
			return true;
		}
		return false;
	}
	
	public String getDefaultSelectedPage() {
		return defaultPage.getText();
	}
	
	public List<String> getRelatedAcctPopUpHeaderList(){
		List<String> headerList=new ArrayList<>();
		for(WebElementFacade header:relatedAccountPopupHeaderList) {
			headerList.add(header.getText());
		}
		return headerList;
	}
	
	public int getRelatedAccountCount() {
		return relatedVisitsList.size();
	}
	
	public List<String> getAllVisitNumbers(){
		List<String> visitNumbers=new ArrayList<>();
		boolean flag=false;
		String attribute;
		do {
			for(WebElementFacade visit: relatedVisitsList) {
				visitNumbers.add(visit.getText().trim());
			}
			System.out.println(visitNumbers);
			flag=nextBtnDisabled.isVisible();
			System.out.println("flag "+flag);
			//attribute=nextBtn.getAttribute("class");
			if(flag==false) {
				nextBtn.click();
				paginationSpinner.waitUntilNotVisible();
			}
		}while(flag==false);
			return visitNumbers;
	}
	
	/*public List<String> compareList(List<String> dbValues, List<String> uivaluesl){
		List<String> uncommonNumber=new ArrayList<>();
		
	}*/
}
