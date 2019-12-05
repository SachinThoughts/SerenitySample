package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class WorkflowDistributionPage extends PageObject {

	@FindBy(xpath = "//h3[contains(text(),'Defect Management Inventory ')]")
	private WebElementFacade workflowDistributionTitle;

	@FindBy(xpath = "//ul[@id = 'R1DWFDTabsLink']//li/a")
	private List<WebElementFacade> workflowDistributionTabs;

	@FindBy(id = "BreadCrumb")
	private WebElementFacade breadCrumb;

	@FindBy(id = "PatientInventoryTab")
	private WebElementFacade patientInventoryTab;

	@FindBy(className = "FacilityInventoryTab")
	private WebElementFacade facilityInventoryTab;

	@FindBy(className = "TeamsTab")
	private WebElementFacade teamsTab;

	@FindBy(className = "RepsTab")
	private WebElementFacade repsTab;

	@FindBy(xpath = "//*[@id='filterOptions']//*[contains(text(),'Note')]")
	private WebElementFacade notesSection;

	@FindBy(xpath = "(//*[@id='filterOptions']//span)[2]")
	private WebElementFacade noteMsg;

	@FindBy(xpath = "//label[@for='inventoryFiltersAll']")
	private WebElementFacade selectAllRadioBtn;

	@FindBy(xpath = "//ul[@id='inventoryHeaderView']/li")
	private List<WebElementFacade> listOfFacilityInventoryHeaders;

	@FindBy(xpath = "(//a[@class='toggle-inventory'])[1]")
	private WebElementFacade firstFacilityGroup;

	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/h4[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfFacilityGroupSections;

	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[1]/li[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfFacilityGroupSubSectionsForUnassigned;

	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[4]/li[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfFacilityGroupSubSectionsForAssigned;

	@FindBy(xpath = "//label[@for='inventoryFiltersPatient']")
	private WebElementFacade patientRadioBtn;

	@FindBy(xpath = "//label[@for='inventoryFiltersPayer']")
	private WebElementFacade payerRadioBtn;

	@FindBy(xpath = "//label[@for='payerInventoryType-0']")
	private WebElementFacade allRadioBtn;

	@FindBy(xpath = "//label[@for='payerInventoryType-01']")
	private WebElementFacade professionalRadioBtn;

	@FindBy(xpath = "(//div[@class='filters-payer'])[1]//label")
	private List<WebElementFacade> listOfFiltersUnderAccInvtryTab;

	@FindBy(xpath = "//*[@id='facility-inventory']/div/div/div/h4/a[contains(text(),'Hide')]")
	private WebElementFacade hideLinkOnFacilityInvtryTab;

	@FindBy(xpath = "//*[@id='facility-inventory']/div/div/div/h4/a[contains(text(),'Show')]")
	private WebElementFacade showLinkOnFacilityInvtryTab;

	@FindBy(xpath = "//label[@for='payerInventoryType-02']")
	private WebElementFacade technicalRadioBtn;

	@FindBy(id = "ddlfacilityGroupFilters")
	private WebElementFacade facilityGrpDrpDwn;
	
	@FindBy(xpath = "//*[@id='teamFilters']/div/label")
	private List<WebElementFacade> listOfFiltersUnderRepsTab;

	@FindBy(xpath = "//*[@id='user-inventory']/div[2]/div[1]/div/div/div[2]/h4/a[contains(text(),'Hide')]")
	private WebElementFacade hideLinkOnRepsTab;	
	
	@FindBy(xpath = "//*[@id='user-inventory']/div[2]/div[1]//div[@class='filters-search']/h4")
	private WebElementFacade searchLabelOnRepsTab;	
	
	@FindBy(id = "txtSearch")
	private WebElementFacade searchTxtBoxOnRepsTab;	
	
	@FindBy(xpath = "//*[@id='user-inventory']/div[2]/div[1]/div/div//input[@class='btn btnPrimary']")
	private List<WebElementFacade> listOfButtonsOnRepsTab;
	
	@FindBy(xpath = "//*[@id='user-inventory']/div[2]/div[1]/div/div/div[2]/h4/a[contains(text(),'Show')]")
	private WebElementFacade showLinkOnRepsTab;
	
	public void isWorkflowDistributionTitleVisible() {
		workflowDistributionTitle.shouldBeVisible();
	}

	public List<String> getWorkflowDistributionTabsHeaders() {
		List<String> wrkflwTabs = new ArrayList<>();
		for (WebElementFacade tabName : workflowDistributionTabs) {
			wrkflwTabs.add(tabName.getText());
		}
		return wrkflwTabs;
	}

	public boolean isWorkflowDistributionTabsVisible(List<String> expectedTabs) {
		return getWorkflowDistributionTabsHeaders().containsAll(expectedTabs);
	}

	public String getBreadCrumbText() {
		return breadCrumb.getText();
	}

	public void clickPatientInventoryTab() {
		evaluateJavascript("arguments[0].click();", patientInventoryTab);
	}

	public void clickFacilityInventoryTab() {
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	public void clickTeamsTab() {
		evaluateJavascript("arguments[0].click();", teamsTab);
	}

	public void clickRepsTab() {
		evaluateJavascript("arguments[0].click();", repsTab);
	}

	public boolean isNoteMsgVisible() {
		return notesSection.isVisible();
	}

	public String getNoteMsgText() {
		return noteMsg.getText().trim();
	}

	public void clickselectAllRadioBtn() {
		evaluateJavascript("arguments[0].click();", selectAllRadioBtn);
	}

	public List<String> getListOfFacilityInventoryHeaders() {
		List<String> listOfFacilityHeaderText = new ArrayList<>();
		for (WebElementFacade facilityInventoryHeader : listOfFacilityInventoryHeaders) {
			listOfFacilityHeaderText.add(facilityInventoryHeader.getText().replace("\n", " ").trim());
		}
		return listOfFacilityHeaderText;
	}

	public void clickFirstFacilityGroup() {
		evaluateJavascript("arguments[0].click();", firstFacilityGroup);
	}

	public List<String> getFacilityGrpSections() {
		List<String> listOfTxtValOfFacilityGrpSections = new ArrayList<>();
		for (WebElementFacade facilityGroupSections : listOfFacilityGroupSections) {
			withAction().moveToElement(facilityGroupSections).build().perform();
			listOfTxtValOfFacilityGrpSections.add(facilityGroupSections.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSections;
	}

	public List<String> getListOfFacilityGroupSubSectionsForUnassigned() {
		List<String> listOfTxtValOfFacilityGrpSubSectionsForUnassigned = new ArrayList<>();
		for (WebElementFacade facilityGroupSubSectionsForUnassigned : listOfFacilityGroupSubSectionsForUnassigned) {
			listOfTxtValOfFacilityGrpSubSectionsForUnassigned
					.add(facilityGroupSubSectionsForUnassigned.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSubSectionsForUnassigned;
	}

	public List<String> getListOfFacilityGroupSubSectionsForAssigned() {
		List<String> listOfTxtValOfFacilityGrpSubSectionsForAssigned = new ArrayList<>();
		for (WebElementFacade facilityGroupSubSectionsForAssigned : listOfFacilityGroupSubSectionsForAssigned) {
			withAction().moveToElement(facilityGroupSubSectionsForAssigned).build().perform();
			listOfTxtValOfFacilityGrpSubSectionsForAssigned.add(facilityGroupSubSectionsForAssigned.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSubSectionsForAssigned;
	}

	public void clickPatientRadioBtn() {
		evaluateJavascript("arguments[0].click();", patientRadioBtn);
	}

	public void clickPayerRadioBtn() {
		evaluateJavascript("arguments[0].click();", payerRadioBtn);
	}

	public boolean isFirstFacilityGroupVisible() {
		return firstFacilityGroup.isVisible();
	}

	public boolean isAllRadioBtnVisible() {
		return allRadioBtn.isVisible();
	}

	public boolean isProfessionalRadioBtnVisible() {
		return professionalRadioBtn.isVisible();
	}

	public boolean isTechnicalRadioBtnVisible() {
		return technicalRadioBtn.isVisible();
	}

	public void clickOnFacilityInvtryTab() {
		waitForAngularRequestsToFinish();
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	public List<String> getFiltersValueUnderAccInventory() {
		List<String> filtersUnderAccInventory = new ArrayList<>();
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

	public void selectFacilityGroup(String facilityGrpName) {
		facilityGrpDrpDwn.selectByVisibleText(facilityGrpName);
	}

	public List<String> getFiltersNameUnderRepsTab() {
		List<String> filtersUnderRepsTab = new ArrayList<>();
		for (WebElementFacade filters : listOfFiltersUnderRepsTab) {
			filtersUnderRepsTab.add(filters.getText());
		}
		return filtersUnderRepsTab;	
	}

	public boolean isHideLinkVisibleInRepsTab() {
		return	hideLinkOnRepsTab.isVisible();
	}

	public void isSearchLabelOnRepsTabVisible() {
		searchLabelOnRepsTab.isVisible();
	}

	public String getSearchBoxWaterMarkTextOnRepsTab() {
		return searchTxtBoxOnRepsTab.getAttribute("placeholder");
	}

	public List<String> getButtonsValueUnderRepsTab() {
		List<String> buttonsUnderRepsTab = new ArrayList<>();
		for (WebElementFacade button : listOfButtonsOnRepsTab) {
			buttonsUnderRepsTab.add(button.getAttribute("value"));
		}
		return buttonsUnderRepsTab;	 
	}

	public void clickOnHideLinkOnRepsTab() {
		hideLinkOnRepsTab.click();
	}

	public boolean isShowLinkVisibleInRepsTab() {
     return showLinkOnRepsTab.isVisible();
	}

	public void clickOnShowLinkOnRepsTab() {
		showLinkOnRepsTab.click();
	}

	public boolean isListOfFiltersVisible() {
		return listOfFiltersUnderRepsTab.isEmpty();
	}
}