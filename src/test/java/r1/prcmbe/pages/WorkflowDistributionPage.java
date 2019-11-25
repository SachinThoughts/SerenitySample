package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class WorkflowDistributionPage extends PageObject {

	@FindBy(xpath = "//h3[contains(text(),'Defect Management Inventory ')]")
	private WebElementFacade workflowDistributionTitle;

	@FindBy(className = "FacilityInventoryTab")
	private WebElementFacade facilityInventoryTab;

	@FindBy(xpath = "//label[@for='inventoryFiltersAll']")
	private WebElementFacade selectAllRadioBtn;

	@FindBy(xpath = "//ul[@id='inventoryHeaderView']/li")
	private List<WebElementFacade> listOfFacilityInventoryHeaders;
	
	@FindBy(xpath = "(//a[@class='toggle-inventory'])[1]")
	private WebElementFacade firstFacilityGroup;
	
	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/h4[not (contains(@class,'ng-hide'))]")
	private List<WebElementFacade> listOfFacilityGroupSections;
	
	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[1]/li[not (contains(@class,'ng-hide'))]           ")
	private List<WebElementFacade> listOfFacilityGroupSubSectionsForUnassigned;
	
	@FindBy(xpath = "//*[@id='inventoryDetail0']/li/ul[4]/li[not (contains(@class,'ng-hide'))]           ")
	private List<WebElementFacade> listOfFacilityGroupSubSectionsForAssigned;
	
	@FindBy(xpath = "//label[@for='inventoryFiltersPatient']")
	private WebElementFacade patientRadioBtn;
	
	@FindBy(xpath = "//label[@for='inventoryFiltersPayer']")
	private WebElementFacade payerRadioBtn;
	
	public boolean isWorkflowDistributionTitleVisible() {
		return workflowDistributionTitle.isVisible();
	}

	public void clickFacilityInventoryTab() {
		evaluateJavascript("arguments[0].click();", facilityInventoryTab);
	}

	public void clickselectAllRadioBtn() {
		evaluateJavascript("arguments[0].click();", selectAllRadioBtn);
	}

	public List<String> getListOfFacilityInventoryHeaders() {
		List<String> listOfFacilityHeaderText = new ArrayList<String>();
		for (WebElementFacade facilityInventoryHeader : listOfFacilityInventoryHeaders) {
			listOfFacilityHeaderText.add(facilityInventoryHeader.getText().replace("\n", " ").trim());
		}
		return listOfFacilityHeaderText;
	}
	
	public void clickFirstFacilityGroup()
	{		evaluateJavascript("arguments[0].click();", firstFacilityGroup);
	}
	
	public List<String> getFacilityGrpSections() {
		List<String> listOfTxtValOfFacilityGrpSections = new ArrayList<String>();
		for (WebElementFacade facilityGroupSections : listOfFacilityGroupSections) {
			withAction().moveToElement(facilityGroupSections).build().perform();
			listOfTxtValOfFacilityGrpSections.add(facilityGroupSections.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSections;
	}
	
	public List<String> getListOfFacilityGroupSubSectionsForUnassigned() {
		List<String> listOfTxtValOfFacilityGrpSubSectionsForUnassigned = new ArrayList<String>();
		for (WebElementFacade facilityGroupSubSectionsForUnassigned : listOfFacilityGroupSubSectionsForUnassigned) {
			listOfTxtValOfFacilityGrpSubSectionsForUnassigned.add(facilityGroupSubSectionsForUnassigned.getText().trim());
		}
		return listOfTxtValOfFacilityGrpSubSectionsForUnassigned;
	}
	
	public List<String> getListOfFacilityGroupSubSectionsForAssigned() {
		List<String> listOfTxtValOfFacilityGrpSubSectionsForAssigned = new ArrayList<String>();
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
}