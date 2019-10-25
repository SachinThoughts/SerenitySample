package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class SettingsPage extends PageObject {

	CommonMethods commonMethods;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/span[2]/span/div[43]/table/tbody/tr[1]/td[1]/a/span[text()='UDC Admin Configuration']")
	private WebElementFacade uDCAdminConfig;

	@FindBy(xpath = "//span[text()='Workflow Configuration']")
	private WebElementFacade workflowConfig;

	@FindBy(xpath = "//span[text()='Settings - R1_Decision']")
	private WebElementFacade settingsR1Decision;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/span[2]/span/div[44]/table/tbody/tr[3]/td[2]/span[text()='Facility Group Configuration']")
	private WebElementFacade faciltyGroupConfig;

	public void hoverSettingsR1Decisions() {
		commonMethods.hoverOverElement(settingsR1Decision);
	}

	public void clickUDCAdminConfig() {
		uDCAdminConfig.click();
	}

	public void clickR1D() {
		settingsR1Decision.click();
	}

	public void clickWorkflowConfig() {
		workflowConfig.click();
	}

	public void clickFaciltyGroupConfig() {
		faciltyGroupConfig.click();
	}
}