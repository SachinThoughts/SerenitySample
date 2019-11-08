package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class SettingsPage extends PageObject {

	CommonMethods commonMethods;

	@FindBy(id = "dnn_dnnSideNav_ctldnnSideNavt703")
	private WebElementFacade uDCAdminConfig;

	public void clickUDCAdminConfig() {
		uDCAdminConfig.click();
	}

	public void clickR1D() {
		settingsR1Decision.click();
	}

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Workflow Configuration']")
	private WebElementFacade workflowConfig;

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Settings - R1_Decision']")
	private WebElementFacade settingsR1Decision;

	@FindBy(xpath = "//span[not(contains(@style,'hidden'))and text()='IT Tools']")
	private WebElementFacade iTToolsLink;

	@FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='FacilitySetting Configuration']")
	private WebElementFacade facilitySettingConfigLink;

	public void clickOnSettingsR1Decisions() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(settingsR1Decision).click().build().perform();
	}

	public void clickWorkflowConfig() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(workflowConfig).click().build().perform();
	}

	public void hoverITToolsLink() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(iTToolsLink).click().build().perform();
	}

	public void clickFacilitySettingConfigLink() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(facilitySettingConfigLink).click().build().perform();
	}
}