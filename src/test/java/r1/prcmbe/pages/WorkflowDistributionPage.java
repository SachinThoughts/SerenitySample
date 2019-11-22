package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class WorkflowDistributionPage extends PageObject {

	@FindBy(xpath = "//h3[contains(text(),'Defect Management Inventory ')]")
	private WebElementFacade workflowDistributionTitle;

	@FindBy(xpath = "//a[@class='FacilityInventoryTab']")
	private WebElementFacade facilityInventoryTab;

	@FindBy(xpath = "(//div[@class='filters-payer'])[1]//label")
	private List<WebElementFacade> listOfFiltersUnderAccInvtryTab;

	@FindBy(xpath = "//*[@id='facility-inventory']/div/div/div/h4/a[contains(text(),'Hide')]")
	private WebElementFacade hideLinkOnFacilityInvtryTab;

	@FindBy(xpath = "//*[@id='facility-inventory']/div/div/div/h4/a[contains(text(),'Show')]")
	private WebElementFacade showLinkOnFacilityInvtryTab;

	public boolean isWorkflowDistributionTitleVisible() {
		return workflowDistributionTitle.isVisible();
	}

	public void clickOnFacilityInvtryTab() {
		waitForAngularRequestsToFinish();
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	public List<String> getFiltersValueUnderAccInventory() {
		List<String> filtersUnderAccInventory = new ArrayList<String>();
		for (WebElementFacade filter : listOfFiltersUnderAccInvtryTab) {
			filtersUnderAccInventory.add(filter.getText());
		}
		return filtersUnderAccInventory;
	}

	public boolean isHideLinkVisibleInFacilityInvtryTab() {
		return hideLinkOnFacilityInvtryTab.isVisible();
	}

	public void clickOnHideLinkOnFacilityInvtryTab() {
		hideLinkOnFacilityInvtryTab.click();
	}

	public boolean isShowLinkVisibleInFacilityInvtryTab() {
		return showLinkOnFacilityInvtryTab.isVisible();
	}

	public void clickOnShowLinkOnFacilityInvtryTab() {
		showLinkOnFacilityInvtryTab.click();
	}

	public String isProfessionalRadioBtnSelected() {
		return evaluateJavascript("return document.querySelector('#inventoryFiltersProfessional').checked").toString();
	}

	public boolean isFiltersUnderAccInvtryVisible() {
		return showLinkOnFacilityInvtryTab.isVisible();
	}
}
