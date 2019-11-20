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

	public boolean isWorkflowDistributionTitleVisible() {
		return workflowDistributionTitle.isVisible();
	}

	public List<String> getWorkflowDistributionTabsHeaders() {
		List<String> wrkflwTabs = new ArrayList<String>();
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
}
