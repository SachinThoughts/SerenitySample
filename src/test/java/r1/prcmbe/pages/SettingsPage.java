package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class SettingsPage extends PageObject {

	CommonMethods commonMethods;

	@FindBy(xpath = "//span[text()='Workflow Configuration']")
	private WebElementFacade workflowConfig;

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Settings - R1_Decision']")
	private WebElementFacade settingsR1Decision;

	public void clickOnSettingsR1Decisions() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(settingsR1Decision).click().build().perform();
	}

	public void clickWorkflowConfig() {
		workflowConfig.click();
	}
}