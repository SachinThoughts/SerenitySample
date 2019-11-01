package r1.prcmbe.pages;

import java.time.Duration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SearchPage extends PageObject {

	@FindBy(css = "#userMsg > span")
	private WebElementFacade noAccountsMessage;
	
	@FindBy(xpath = "//select[@class='form-control ddlSearchCriteria']")
	private WebElementFacade searchByDropdown;

	@FindBy(xpath = "//input[@placeholder='Invoice #']")
	private WebElementFacade invoiceNumberTxtField;

	@FindBy(xpath = "//div[@class='form-group searchBtnOnLoad']/input[@type='submit']")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//*[@id='searchLoader']//div[@class='modal-body']/i")
	private WebElementFacade loadingSpinner;

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

	public void enterInvoiceNumber(String visitNumber) {
		invoiceNumberTxtField.type(visitNumber);
	}

	public void clickSubmitBtn() {
		submitBtn.click();
		if (loadingSpinner.isVisible()) {
			loadingSpinner.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
		}
	}
}
