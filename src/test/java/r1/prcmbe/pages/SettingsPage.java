package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class SettingsPage extends PageObject {

    CommonMethods commonMethods;

    @FindBy(id = "dnn_dnnSideNav_ctldnnSideNavt703")
    private WebElementFacade uDCAdminConfig;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Workflow Configuration']")
    private WebElementFacade workflowConfig;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Settings - R1_Decision']")
    private WebElementFacade settingsR1Decision;

    @FindBy(xpath = "//span[not(contains(@style,'hidden'))and text()='IT Tools']")
    private WebElementFacade iTToolsLink;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='FacilitySetting Configuration']")
    private WebElementFacade facilitySettingConfigLink;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Tag Configuration']")
    private WebElementFacade tagConfigLink;

    @FindBy(xpath = "//span[@class='txt'and text()='Cache']")
    private WebElementFacade cacheLink;

    @FindBy(id = "dnn_ctr612_Cache_btnClearAll")
    private WebElementFacade clearAllBtn;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='R1 Configuration']")
    private WebElementFacade r1ConfigurationLink;

    @FindBy(xpath = "//span[(contains(@class,'txt')) and text()='Facility Group Configuration']")
    private WebElementFacade facilityGrpConfigLink;

    @FindBy(xpath = "//span[not(contains(@style,'hidden')) and text()='Action Target Configuration']")
    private WebElementFacade actionTargetConfigLink;

    @FindBy(xpath = "//span[not(contains(@style,'hidden'))and text()='Payor and Plan Config']")
    private WebElementFacade PayorAndPlanConfigLink;

    @FindBy(xpath = "//span[not(contains(@style,'hidden'))and text()='PRCM - Eligibility NPI Configuration']")
    private WebElementFacade eligibilityNPIConfigLink;

    @FindBy(xpath = "//*[@id='sidebar']/div[1]/span[text()='Settings']")
    private WebElementFacade settingsHeader;

    /**
     * Method clicks on R1D Decision on Settings Page
     */
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

    public void clickTagConfigLink() {
        waitForAngularRequestsToFinish();
        withAction().moveToElement(tagConfigLink).click().build().perform();
    }

    public void clickCacheLink() {
        withAction().moveToElement(cacheLink).click().build().perform();
    }

    public void clickClearAllBtn() {
        clearAllBtn.click();
    }

    public void clickUDCAdminConfig() {
        uDCAdminConfig.click();
    }

    public void clickR1D() {
        settingsR1Decision.click();
    }

    public void clickR1ConfigurationLink() {
        waitForAngularRequestsToFinish();
        withAction().moveToElement(r1ConfigurationLink).click().build().perform();
    }

    /**
     * Method clicks on the Facility GRoup Configuration in R1D Decision
     */
    public void clickFacilityGroupConfig() {
        withAction().moveToElement(facilityGrpConfigLink).click().build().perform();
    }

    public void clickActionTargetConfigLink() {
        withAction().moveToElement(actionTargetConfigLink).click().build().perform();
    }

    public void hoverPayorAndPlanConfigLink() {
        withAction().moveToElement(PayorAndPlanConfigLink).build().perform();
    }

    public void clickEligibilityNPIConfigLink() {
        withAction().moveToElement(eligibilityNPIConfigLink).click().build().perform();
    }

    public void checkSettingsHeaderVisibility() {
        settingsHeader.shouldBeVisible();
    }
}

