package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class UniversalDefectConfigurationPage extends PageObject {

	@FindBy(xpath = "//h3[contains(text(),'Universal Defect Configuration')]")
	private WebElementFacade uDCTitle;

	@FindBy(xpath = "//*[@id='anchorahtodecisiontab']/a")
	private WebElementFacade decisionConfigTab;

	@FindBy(xpath = "//*[@id='anchorahtodecisiontab']/a")
	private WebElementFacade pRCMDecisionConfig;

	@FindBy(xpath = "//*[@id='R1Dh2id']")
	private WebElementFacade decisionConfigHeader;

	public boolean checkUDCTitleVisibility() {
		return uDCTitle.isVisible();
	}

	public boolean decisionConfigTabIsVisible() {
		return decisionConfigTab.isVisible();
	}

	public boolean pRCMDecisionConfigIsVisible() {
		return pRCMDecisionConfig.isVisible();
	}

	public void clickOnDecisionConfigTab() {
		decisionConfigTab.click();
	}

	public boolean decisionConfigHeaderIsVisible() {
		return decisionConfigHeader.isVisible();
	}

	public void clickOnPRCMDecisionConfig() {
		pRCMDecisionConfig.click();
	}
}
