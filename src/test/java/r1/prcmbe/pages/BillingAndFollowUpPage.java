package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class BillingAndFollowUpPage extends PageObject {

	@FindBy(xpath = "//span[text()='R1_Decision' and @class='txt']")
	private WebElementFacade r1DecisionLink;

	@FindBy(xpath = "//span[text()='Search' and @class='txt']")
	private WebElementFacade searchLink;

	@FindBy(xpath = "//span[text()='ePARS-Pro' and @class='txt']")
	private WebElementFacade eparsProLink;

	@FindBy(xpath = "//span[text()='Mass Update' and @class='txt']")
	private WebElementFacade massUpdateLink;

	public void clickOnR1DecisionLink() {
		withAction().moveToElement(r1DecisionLink).click().build().perform();
	}

	public void hoverOnR1DecisionLink() {
		withAction().moveToElement(r1DecisionLink).build().perform();
	}

	public void clickSearchLink() {
		withAction().moveToElement(searchLink).click().build().perform();
	}

	public void clickEparsProLink() {
		withAction().moveToElement(eparsProLink).click().build().perform();
	}

	public void clickMassUpdateLink() {
		withAction().moveToElement(massUpdateLink).click().build().perform();
	}
}
