package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class BillingAndFollowUpPage extends PageObject {

	@FindBy(xpath = "//span[text()='R1_Decision' and @class='txt']")
	private WebElementFacade r1DecisionLink;

	private String searchLink = "document.querySelector(\"#sidebar > div.side_nav > span:nth-child(4) > span > div:nth-child(18) > table > tbody > tr.mi.mi0-0.id688.first > td:nth-child(2) > a\").click()";

	public void clickOnR1DecisionLink() {
		r1DecisionLink.click();
	}

	public void hoverOnR1DecisionLink() {
		withAction().moveToElement(r1DecisionLink).build().perform();
	}

	public void clickSearchLink() {
		evaluateJavascript(searchLink);
	}
}
