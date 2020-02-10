package r1.prcmbe.pages;

import java.time.Duration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class NavigationPage extends PageObject {
	final private String himTitleHeader = "Health Information Management";

	private final String userNameJS = "return $(GlobalInputData.UserName).selector";
	private final String userIDJS = "return $(GlobalInputData.UserID).selector";

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

	@FindBy(xpath = "//*[@class= 'icn']//*[contains(@href,'Workflow-Distribution')]")
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

	@FindBy(xpath = "//*[@id='dnn_dnnLINKS_lblLinks']/a[text()='Billing & Follow-up']")
	private WebElementFacade footerBillingFollowUpLink;

	@FindBy(id = "dnn_dnnUSER_registerLink")
	private WebElementFacade userRegisterLink;
	
	@FindBy(xpath = "(//span[@class='TitleHeader'])[last()-1]")
	private WebElementFacade hubPageTitleHeader;

	public WebElementFacade getChartManagerLink() {
		return chartManagerLink;
	}

	/**
	 * @return himHomePage text
	 */
	public String getHIMHomePage() {
		return himHomePage.getText();
	}

	/**
	 * This method clicks on HIM Link
	 */
	public void clickHIMLink() {
		himLink.click();
	}

	/**
	 * This method clicks on ChartManager Link
	 */
	public void clickChartManagerLink() {
		chartManagerLink.click();
	}

	/**
	 * This method hovers on ChartManager Link
	 */
	public void hoverChartManagerLink() {
		withAction().moveToElement(chartManagerLink).build().perform();
	}

	/**
	 * This method clicks on ExternalSearch Link
	 */
	public void clickExternalSearchLink() {
		externalSearchLink.click();
	}

	/**
	 * @return whether HIMHomePage title is visible
	 */
	public boolean isHIMPageVisible() {
		return himHomePage.getText().equals(himTitleHeader);
	}

	/**
	 * This method clicks on settings page Link
	 */
	public void clickSettings() {
		settings.click();
	}

	/**
	 * This method clicks on WorkflowDistribution Link
	 */
	public void clickWorkflowDistribution() {
		evaluateJavascript("arguments[0].click();", workflowDistribution);
		waitForAngularRequestsToFinish();
	}

	/**
	 * click on the Settings link present in the footer
	 */
	public void clickFooterSettings() {
		spinner.withTimeoutOf(Duration.ofSeconds(60)).waitUntilNotVisible();
		evaluateJavascript("arguments[0].click();", footerSettings);
	}

	/**
	 * @return UserName in String
	 */
	public String fetchUserName() {
		return evaluateJavascript(userNameJS).toString();
	}

	/**
	 * @return UserID in String
	 */
	public String fetchUserID() {
		return evaluateJavascript(userIDJS).toString();
	}

	/**
	 * This method clicks on HIMFooter Link
	 */
	public void clickHIMFooterLink() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", footerHIMLink);
		footerHIMLink.click();
	}

	/**
	 * This method clicks on Home Link
	 */
	public void clickHomeLink() {
		homeLink.click();
	}

	/**
	 * This method clicks on Facility Link
	 */
	public void clickOnFacilityLink() {
		facilityLink.click();
	}

	/**
	 * This method select Facility From Facility DropDown
	 * @param facilityName
	 */
	public void selectFacilityFromDropdown(String facilityName) {
		facilityDropdown.selectByVisibleText(facilityName);
	}

	/**
	 * This method clicks on Billing And FollowUp Link
	 */
	public void clickOnBillingAndFollowUpLink() {
		withAction().moveToElement(billingAndFollowUpLink).click().build().perform();
	}

	/**
	 * click on the Billing FollowupUp link present in the footer
	 */
	public void clickFooterBillingFollowUpLink() {
		evaluateJavascript("arguments[0].click();", footerBillingFollowUpLink);
	}

	/**
	 * This method fetches User Login Name
	 * @return user Login Name
	 */
	public String getUserLoginName() {
		return userRegisterLink.getText();
	}
	
	/**
	 * @return visibility of Hub page title header
	 */
	public boolean isHubPageTitleHeaderVisible() {
		return hubPageTitleHeader.isVisible();
	}
}