package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class BillingAndFollowUpPage extends PageObject {

	@FindBy(xpath = "//span[text()='R1_Decision' and @class='txt']")
	private WebElementFacade r1DecisionLink;

	public void clickOnR1DecisionLink() {
		withAction().moveToElement(r1DecisionLink).click().build().perform();
	}

	public void hoverOnR1DecisionLink() {
		withAction().moveToElement(r1DecisionLink).build().perform();
	}
}
