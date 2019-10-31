package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SearchPage extends PageObject {

	@FindBy(css = "#userMsg > span")
	private WebElementFacade noAccountsMessage;

	String titleJs = "return document.querySelector('#Head > title').text";

	public String getSearchPageTitle() {
		return evaluateJavascript(titleJs).toString();
	}

	public String getNoAccountsMessage() {
		return noAccountsMessage.getText();
	}
}
