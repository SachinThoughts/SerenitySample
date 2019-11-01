package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SearchPage extends PageObject {

	@FindBy(css = "#userMsg > span")
	private WebElementFacade noAccountsMessage;

	@FindBy(xpath = "//select[@class='form-control ddlSearchCriteria']")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "//select[@class='form-control ddlOperator']")
	private WebElementFacade operator;

	@FindBy(xpath = "//input[@placeholder='Medical Records #']")
	private WebElementFacade mRNTxtField;

	String titleJS = "return document.querySelector('#Head > title').text";

	public String getSearchPageTitle() {
		return evaluateJavascript(titleJS).toString();
	}

	public String getNoAccountsMessage() {
		return noAccountsMessage.getText();
	}

	public void searchBySelectText(String dropdown) {
		searchByDropdown.selectByVisibleText(dropdown);
	}

	public void operatorSelectText(String operatorValue) {
		operator.selectByVisibleText(operatorValue);
	}

	public void enterMRN(String mRN) {
		mRNTxtField.type(mRN);
	}
}
