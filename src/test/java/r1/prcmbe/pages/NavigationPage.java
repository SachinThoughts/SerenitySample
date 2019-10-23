package r1.prcmbe.pages;

import java.time.Duration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class NavigationPage extends PageObject {
	final private String himTitleHeader = "Health Information Management";
	private final String userNameJs = "return $(GlobalInputData.UserName).selector";
	private final String userIDJs = "return $(GlobalInputData.UserID).selector";

	@FindBy(xpath = "//span[text()='Health Information Management']")
	private WebElementFacade himLink;

	@FindBy(xpath = "//span[text()='R1 ChartManager']")
	private WebElementFacade chartManagerLink;

	@FindBy(id = "dnn_dnnSideNav_ctldnnSideNavt1792")
	private WebElementFacade externalSearchLink;

	@FindBy(id = "dnn_ctr941_dnnTITLE_titleLabel")
	private WebElementFacade himHomePage;

	@FindBy(xpath = "//a[text()='Settings']")
	private WebElementFacade settings;

	@FindBy(id = "dnn_dnnSideNav_ctldnnSideNavt1789")
	private WebElementFacade workflowDistribution;

	@FindBy(xpath = "//*[@id='dnn_dnnLINKS_lblLinks']/a[text()='Settings']")
	private WebElementFacade footerSettings;

	@FindBy(xpath = "//*[@id='dnn_dnnLINKS_lblLinks']/a[text()='Health Information Management']")
	private WebElementFacade footerHIMLink;

	@FindBy(xpath = "//span[text()='Home']")
	private WebElementFacade homeLink;

	@FindBy(xpath = "//*[@id='dnn_ctr2670_TaskPanel_spanTask']/app-root/div/div/div/div/div/i")
	private WebElementFacade spinner;

	@FindBy(id = "dnn_ctr1025_LocationChooser_hypLoc")
	private WebElementFacade facilityLink;

	@FindBy(id = "dnn_ctr1025_LocationChooser_ddlLocation")
	private WebElementFacade facilityDropdown;
	
	@FindBy(xpath = "//span[text()='Billing & Follow-up' and @class='txt']")
	private WebElementFacade billingAndFollowUpLink;

	public WebElementFacade getChartManagerLink() {
		return chartManagerLink;
	}

	public String getHIMHomePage() {
		return himHomePage.getText();
	}

	public void clickHIMLink() {
		himLink.click();
	}

	public void clickChartManagerLink() {
		chartManagerLink.click();
	}

	public void hoverChartManagerLink() {
		withAction().moveToElement(chartManagerLink).build().perform();
	}

	public void clickExternalSearchLink() {
		externalSearchLink.click();
	}

	public boolean isHIMPageVisible() {
		return himHomePage.getText().equals(himTitleHeader);
	}

	public void clickSettings() {
		settings.click();
	}

	public void clickWorkflowDistribution() {
		workflowDistribution.click();
		waitForAngularRequestsToFinish();
	}

	public void clickFooterSettings() {
		spinner.withTimeoutOf(Duration.ofSeconds(60)).waitUntilNotVisible();
		evaluateJavascript("arguments[0].click();", footerSettings);
	}

	public String fetchUserName() {
		return evaluateJavascript(userNameJs).toString();
	}

	public String fetchUserID() {
		return evaluateJavascript(userIDJs).toString();
	}

	public void clickHIMFooterLink() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", footerHIMLink);
		footerHIMLink.click();
	}

	public void clickHomeLink() {
		homeLink.click();
	}

	public void clickOnFacilityLink() {
		facilityLink.click();
	}

	public void selectFacilityFromDropdown(String facilityName) {
		facilityDropdown.selectByVisibleText(facilityName);
	}

	public void clickOnBillingAndFollowUpLink() {
		billingAndFollowUpLink.click();
	}

}