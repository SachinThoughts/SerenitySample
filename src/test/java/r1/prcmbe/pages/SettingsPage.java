package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class SettingsPage extends PageObject {

	CommonMethods commonMethods;

	@FindBy(id = "dnn_dnnSideNav_ctldnnSideNavt703")
	private WebElementFacade uDCAdminConfig;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/span[2]/span/div[16]/a")
	private WebElementFacade workflowConfig;

	@FindBy(xpath = "//*[@id='sidebar']/div[1]/span[2]/span/div[16]/a")
	private WebElementFacade settingsR1Decision;

	public void hoverSettingsR1Decisions() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", settingsR1Decision);
		evaluateJavascript("arguments[0].click();", settingsR1Decision);
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
}