package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class SettingsR1DPage extends PageObject {

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Facility Group Configuration']")
	private WebElementFacade faciltyGroupConfig;

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='UDC Admin Configuration']")
	private WebElementFacade uDCAdminConfig;

	public void clickFaciltyGroupConfig() {
		withAction().click(faciltyGroupConfig).perform();
	}

	public void clickUDCAdminConfig() {
		withAction().moveToElement(uDCAdminConfig).click().build().perform();
	}
}
