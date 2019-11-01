package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class DefectOverridePage extends PageObject {

	@FindBy(xpath = "//*[@id='divDefectWorkflow']/h3[contains(text(),'Defect Workflow')]")
	private WebElementFacade defectWorkflowSecHeader;

	@FindBy(xpath = "//*[@id='msform']/ul/li")
	private List<WebElementFacade> listOfProgressBars;

	@FindBy(xpath = "//*[@id='msform']/ul/li[1]")
	private WebElementFacade confirmStepOnProgressBar;

	@FindBy(xpath = "//h2[contains(text(),'Current Defect')]")
	private WebElementFacade defectSubCategrySec;

	public boolean isDefectWorkFlowSecPresent() {
		return defectWorkflowSecHeader.isVisible();
	}

	public void moveToDefectWorkflowSec() {
		withAction().moveToElement(defectWorkflowSecHeader).build().perform();
	}

	public List<String> getProgressBarSteps() {
		List<String> listOfPrgressBarsValues = new ArrayList<>();
		for (WebElementFacade ele : listOfProgressBars) {
			listOfPrgressBarsValues.add(ele.getText());
		}
		return listOfPrgressBarsValues;
	}

	public boolean isAssignedSubCategryPresent() {
		return defectSubCategrySec.isVisible();

	}

	public boolean defaultSelectedStepOnPrgsBar(String stepName) {
		return confirmStepOnProgressBar.getText().equals(stepName);
	}
}
