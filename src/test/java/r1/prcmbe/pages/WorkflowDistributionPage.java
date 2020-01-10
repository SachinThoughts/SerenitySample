package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class WorkflowDistributionPage extends PageObject {

	Boolean isNotPresent;

	@FindBy(xpath = "//h3[text()='Defect Management Inventory ']")
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

	@FindBy(xpath = "//*[@id='filterOptions']//*[text()='Note']")
	private WebElementFacade notesSection;

	@FindBy(xpath = "(//*[@id='filterOptions']//span)[2]")
	private WebElementFacade noteMsg;

	@FindBy(xpath = "//label[@for='inventoryFiltersAll']")
	private WebElementFacade selectAllRadioBtn;

	@FindBy(xpath = "//ul[@id='inventoryHeaderView']/li")
	private List<WebElementFacade> listOfFacilityInventoryHeaders;

	@FindBy(xpath = "(//a[@class='toggle-inventory'])[1]")
	private WebElementFacade firstFacilityGroup;

	@FindBy(xpath = "//*[@id='inventoryDetail0']//h4[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfGroupSections;

	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[1]/li[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfGroupSubSectionsForUnassigned;

	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[contains(@class,'sop-header')][2]/li[not(contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfGroupSubSectionsForAssigned;

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

	@FindBy(xpath = "//*[@id='facility-inventory']//h4/a")
	private WebElementFacade hideLinkOnFacilityInvtryTab;

	@FindBy(xpath = "//*[@id='facility-inventory']//h4/a")
	private WebElementFacade showLinkOnFacilityInvtryTab;

	@FindBy(xpath = "//label[@for='payerInventoryType-02']")
	private WebElementFacade technicalRadioBtn;

	@FindBy(id = "ddlfacilityGroupFilters")
	private WebElementFacade facilityGrpDrpDwn;

	@FindBy(xpath = "//*[@id='teamFilters']/div/label")
	private List<WebElementFacade> listOfFiltersUnderRepsTab;

	@FindBy(xpath = "//*[@id='user-inventory']/div[2]/div[1]/div/div/div[2]/h4/a[text()='Hide']")
	private WebElementFacade hideLinkOnRepsTab;

	@FindBy(xpath = "//*[@id='user-inventory']//div[@class='filters-search']/h4")
	private WebElementFacade searchLabelOnRepsTab;

	@FindBy(id = "txtSearch")
	private WebElementFacade searchTxtBoxOnRepsTab;

	@FindBy(xpath = "//*[@id='user-inventory']//input[@class='btn btnPrimary']")
	private List<WebElementFacade> listOfButtonsOnRepsTab;

	@FindBy(xpath = "//*[@id='user-inventory']//h4/a[text()='Show']")
	private WebElementFacade showLinkOnRepsTab;

	@FindBy(xpath = "//*[@id='divPayerFilter']//div[@class='well']")
	private WebElementFacade filterSection;

	@FindBy(xpath = "(//a[@class='toggle-inventory'])[1]/i")
	private WebElementFacade firstDrildwnBtnOnPayerInvtryTab;

	@FindBy(id = "ddlFacilityFilters")
	private WebElementFacade facilityDropdown;

	@FindBy(xpath = "//label[@for='payerInventoryType-02']")
	private WebElementFacade technicalRadioBtnOnPayorInvtryTab;

	/**
	 * Clicking on any facility from facility drop-down
	 */
	public void clickAnyFacilityFromFacilityDropdown() {
		List<String> listOfAllFacilities;
		listOfAllFacilities = facilityDropdown.getSelectOptions();
		int size = listOfAllFacilities.size();
		int randomNumber = ThreadLocalRandom.current().nextInt(1, size);
		facilityDropdown.selectByIndex(randomNumber);
	}

	/**
	 * @param facilityName coming from feature file Select facility from facility
	 *                     Drop-down
	 */
	public void selectFacilityFromDropdown(String facilityName) {
		facilityDropdown.selectByVisibleText(facilityName);
	}

	/**
	 * Checks if Workflow Distribution title is visible
	 */
	public void isWorkflowDistributionTitleVisible() {
		workflowDistributionTitle.shouldBeVisible();
	}

	/**
	 * @return List of workflow Distribution tab headers
	 */
	public List<String> getWorkflowDistributionTabsHeaders() {
		List<String> wrkflwTabs = new ArrayList<>();
		for (WebElementFacade tabName : workflowDistributionTabs) {
			wrkflwTabs.add(tabName.getText());
		}
		return wrkflwTabs;
	}

	/**
	 * @param expectedTabs fetched from Datatable
	 * @return true, if all the expected tabs are present else return false.
	 */
	public boolean isWorkflowDistributionTabsVisible(List<String> expectedTabs) {
		return getWorkflowDistributionTabsHeaders().containsAll(expectedTabs);
	}

	/**
	 * @return Bread crumb text displayed on workflow distribution page
	 */
	public String getBreadCrumbText() {
		return breadCrumb.getText();
	}

	/**
	 * Clicks on Patient Inventory tab
	 */
	public void clickPatientInventoryTab() {
		evaluateJavascript("arguments[0].click();", patientInventoryTab);
	}

	/**
	 * Clicks on Facility Inventory tab
	 */
	public void clickFacilityInventoryTab() {
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	/**
	 * Clicks on Teams tab
	 */
	public void clickTeamsTab() {
		evaluateJavascript("arguments[0].click();", teamsTab);
	}

	/**
	 * Clicks on Reps Tab
	 */
	public void clickRepsTab() {
		evaluateJavascript("arguments[0].click();", repsTab);
	}

	/**
	 * @return True: Note section is visible, else False
	 */
	public boolean isNoteMsgVisible() {
		return notesSection.isVisible();
	}

	/**
	 * @return Note Message displayed on Workflow Distribution page
	 */
	public String getNoteMsgText() {
		return noteMsg.getText().trim();
	}

	/**
	 * clicking on All radio button
	 */
	public void clickselectAllRadioBtn() {
		evaluateJavascript("arguments[0].click();", selectAllRadioBtn);
	}

	/**
	 * @return list of facility Header Text
	 */
	public List<String> getListOfFacilityInventoryHeaders() {
		List<String> listOfFacilityHeaderText = new ArrayList<>();
		for (WebElementFacade facilityInventoryHeader : listOfFacilityInventoryHeaders) {
			listOfFacilityHeaderText.add(facilityInventoryHeader.getText().replace("\n", " ").trim());
		}
		return listOfFacilityHeaderText;
	}

	/**
	 * clicking on first facility group
	 */
	public void clickFirstFacilityGroup() {
		evaluateJavascript("arguments[0].click();", firstFacilityGroup);
	}

	/**
	 * @return list of text value of facility grp sections
	 */
	public List<String> getGroupSections() {
		List<String> listOfTxtValOfFacilityGrpSections = new ArrayList<>();
		for (WebElementFacade groupSections : listOfGroupSections) {
			withAction().moveToElement(groupSections).build().perform();
			listOfTxtValOfFacilityGrpSections.add(groupSections.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSections;
	}

	/**
	 * @return list of text value of sub Sections for unassigned
	 */
	public List<String> getListOfGroupSubSectionsForUnassigned() {
		List<String> listOfTxtValOfGrpSubSectionsForUnassigned = new ArrayList<>();
		for (WebElementFacade groupSubSectionsForUnassigned : listOfGroupSubSectionsForUnassigned) {
			listOfTxtValOfGrpSubSectionsForUnassigned.add(groupSubSectionsForUnassigned.getText().trim());
		}
		return listOfTxtValOfGrpSubSectionsForUnassigned;
	}

	/**
	 * @return list of text value of sub Sections for assigned
	 */
	public List<String> getListOfGroupSubSectionsForAssigned() {
		List<String> listOfTxtValOfFacilityGrpSubSectionsForAssigned = new ArrayList<>();
		for (WebElementFacade groupSubSectionsForAssigned : listOfGroupSubSectionsForAssigned) {
			withAction().moveToElement(groupSubSectionsForAssigned).build().perform();
			listOfTxtValOfFacilityGrpSubSectionsForAssigned.add(groupSubSectionsForAssigned.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSubSectionsForAssigned;
	}

	/**
	 * clicking on patient radio button
	 */
	public void clickPatientRadioBtn() {
		evaluateJavascript("arguments[0].click();", patientRadioBtn);
	}

	/**
	 * clicking on payer radio button
	 */
	public void clickPayerRadioBtn() {
		evaluateJavascript("arguments[0].click();", payerRadioBtn);
	}

	/**
	 * @return boolean value by checking first facility
	 */
	public boolean isFirstFacilityGroupVisible() {
		return firstFacilityGroup.isVisible();
	}

	/**
	 * @return boolean value by checking All radio button
	 */
	public boolean isAllRadioBtnVisible() {
		return allRadioBtn.isVisible();
	}

	/**
	 * @return boolean value by checking professional radio button
	 */
	public boolean isProfessionalRadioBtnVisible() {
		return professionalRadioBtn.isVisible();
	}

	/**
	 * @return boolean value by checking technical radio button
	 */
	public boolean isTechnicalRadioBtnVisible() {
		return technicalRadioBtn.isVisible();
	}

	/**
	 * clicking on facility inventory tab
	 */
	public void clickOnFacilityInvtryTab() {
		waitForAngularRequestsToFinish();
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	/**
	 * @return list of filters under Account Inventory Tab
	 */
	public List<String> getFiltersValueUnderAccInventory() {
		List<String> filtersUnderAccInventory = new ArrayList<>();
		for (WebElementFacade filter : listOfFiltersUnderAccInvtryTab) {
			filtersUnderAccInventory.add(filter.getText());
		}
		return filtersUnderAccInventory;
	}

	/**
	 * @return boolean value by checking hide link on facility Inventory Tab
	 */
	public boolean isHideLinkVisibleInFacilityInvtryTab() {
		return hideLinkOnFacilityInvtryTab.isVisible();
	}

	/**
	 * clicking on hide link on facility Inventory Tab
	 */
	public void clickOnHideLinkOnFacilityInvtryTab() {
		hideLinkOnFacilityInvtryTab.click();
	}

	/**
	 * @return boolean value by checking show link on facility inventory Tab
	 */
	public boolean isShowLinkVisibleInFacilityInvtryTab() {
		return showLinkOnFacilityInvtryTab.isVisible();
	}

	/**
	 * clicking on show link on facility Inventory Tab
	 */
	public void clickOnShowLinkOnFacilityInvtryTab() {
		showLinkOnFacilityInvtryTab.click();
	}

	/**
	 * @return boolean value by checking professional radio button selected
	 */
	public String isProfessionalRadioBtnSelected() {
		return evaluateJavascript("return document.querySelector('#inventoryFiltersProfessional').checked").toString();
	}

	/**
	 * @return boolean value by checking filter under account inventory tab
	 */
	public boolean isFiltersUnderAccInvtryVisible() {
		return showLinkOnFacilityInvtryTab.getAttribute("class").equals("collapsed");
	}

	/**
	 * @param facilityGrpName coming from feature file
	 */
	public void selectFacilityGroup(String facilityGrpName) {
		facilityGrpDrpDwn.selectByVisibleText(facilityGrpName);
	}

	/**
	 * @return boolean value by checking list of filters names under reps tab
	 */
	public boolean isListOfFiltersNameUnderRepsTabEmpty() {
		return !listOfFiltersUnderRepsTab.isEmpty();
	}

	/**
	 * @return boolean value by checking hide link on reps tab
	 */
	public boolean isHideLinkVisibleInRepsTab() {
		return hideLinkOnRepsTab.isVisible();
	}

	/**
	 * @return boolean value by checking search label on reps tab
	 */
	public boolean isSearchLabelOnRepsTabVisible() {
		return searchLabelOnRepsTab.isVisible();
	}

	/**
	 * @return Watermark text of search box
	 */
	public String getSearchBoxWaterMarkTextOnRepsTab() {
		return searchTxtBoxOnRepsTab.getAttribute("placeholder");
	}

	/**
	 * @return list of buttons value on Reps Tab
	 */
	public List<String> getButtonsValueUnderRepsTab() {
		List<String> buttonsUnderRepsTab = new ArrayList<>();
		for (WebElementFacade button : listOfButtonsOnRepsTab) {
			buttonsUnderRepsTab.add(button.getAttribute("value"));
		}
		return buttonsUnderRepsTab;
	}

	/**
	 * clicking on hide link on reps tab
	 */
	public void clickOnHideLinkOnRepsTab() {
		hideLinkOnRepsTab.click();
	}

	/**
	 * @return boolean value by checking show link on reps tab
	 */
	public boolean isShowLinkVisibleInRepsTab() {
		return showLinkOnRepsTab.isVisible();
	}

	/**
	 * clicking on show link on reps tab
	 */
	public void clickOnShowLinkOnRepsTab() {
		showLinkOnRepsTab.click();
	}

	/**
	 * @return boolean value by checking list of filters visible
	 */
	public boolean isListOfFiltersOnRepsTabVisible() {
		return showLinkOnRepsTab.getAttribute("class").equals("collapsed");
	}

	/**
	 * @param wrkflwDistributionTabs coming from feature file clicking on
	 *                               wrkflwDistributionTabs
	 */
	public void clickOnWrkfloDistribitionTabs(String wrkflwDistributionTabs) {
		int size = workflowDistributionTabs.size();
		for (int index = 0; index < size; index++) {
			if (workflowDistributionTabs.get(index).getText().equals(wrkflwDistributionTabs)) {
				workflowDistributionTabs.get(index).click();
			}
		}
	}

	/**
	 * 
	 * @return boolean value based on the presence of PRCM filter
	 */
	public boolean isFilterSectionPresent() {
		return filterSection.isVisible();
	}

	/**
	 * clicking on professional radio button
	 */
	public void clickProfessionaltRadioBtnOnPayerInvtryTab() {
		professionalRadioBtn.click();
	}

	/**
	 * clicking on first payer on payer inventory tab
	 */
	public void clickOnFirstPayerOnPayerInvtryTab() {
		evaluateJavascript("arguments[0].click();", firstDrildwnBtnOnPayerInvtryTab);
	}

	/**
	 * 
	 * @return the boolean value based on the visibility of Title on the Workflow
	 *         Distribution page
	 */
	public boolean isWorkflowDistributionPageVisible() {
		return workflowDistributionTitle.isVisible();
	}

	/**
	 * @param facilityGrpName is passed from the feature file
	 * @return the boolean value based on the presence of expected faciltiy group
	 *         name from the dropdown
	 */
	public boolean isFacilityGrpNamePresentInTheDropdown(String facilityGrpName) {
		List<String> facilityGrpList;
		facilityGrpList = facilityGrpDrpDwn.getSelectOptions();
		return facilityGrpList.contains(facilityGrpName);
	}

	/**
	 * clicking on technical radio button on payer inventory tab
	 */
	public void clickTechnicalRadioBtnOnPayerInvtryTab() {
		technicalRadioBtnOnPayorInvtryTab.click();
	}

	/**
	 * @return boolean value by checking group sections on technical filter
	 */
	public boolean getGroupSectionsOnTechnicalFilter() {
		if (!listOfGroupSections.isEmpty()) {
			List<String> listOfTxtValOfFacilityGrpSections = new ArrayList<>();
			for (WebElementFacade groupSections : listOfGroupSections) {
				withAction().moveToElement(groupSections).build().perform();
				listOfTxtValOfFacilityGrpSections.add(groupSections.getText().trim());
			}
		}
		isNotPresent = true;
		return isNotPresent;
	}

	/**
	 * @return boolean value by checking group subsections for unassigned on
	 *         technical filter
	 */
	public Boolean getListOfGroupSubSectionsForUnassignedOnTechnicalFilter() {
		if (!listOfGroupSections.isEmpty()) {
			List<String> listOfTxtValOfGrpSubSectionsForUnassigned = new ArrayList<>();
			for (WebElementFacade groupSubSectionsForUnassigned : listOfGroupSubSectionsForUnassigned) {
				listOfTxtValOfGrpSubSectionsForUnassigned.add(groupSubSectionsForUnassigned.getText().trim());
			}
		}
		isNotPresent = true;
		return isNotPresent;
	}

	/**
	 * @return boolean value by checking group subsections for assigned on technical
	 *         filter
	 */
	public Boolean getListOfGroupSubSectionsForAssignedOnTechnicalFilter() {
		if (!listOfGroupSections.isEmpty()) {
			List<String> listOfTxtValOfFacilityGrpSubSectionsForAssigned = new ArrayList<>();
			for (WebElementFacade groupSubSectionsForAssigned : listOfGroupSubSectionsForAssigned) {
				withAction().moveToElement(groupSubSectionsForAssigned).build().perform();
				listOfTxtValOfFacilityGrpSubSectionsForAssigned.add(groupSubSectionsForAssigned.getText().trim());
			}
		}
		isNotPresent = true;
		return isNotPresent;
	}
}