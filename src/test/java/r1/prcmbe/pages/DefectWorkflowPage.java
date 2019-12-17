package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class DefectWorkflowPage extends PageObject {

	CommonMethods commonMethods;

	@FindBy(xpath = "//*[@id='divDefectWorkflow']/h3[contains(text(),'Defect Workflow')]")
	private WebElementFacade defectWorkflowSecHeader;

	@FindBy(xpath = "//*[@id='msform']/ul/li")
	private List<WebElementFacade> listOfProgressBars;

	@FindBy(xpath = "//*[@id='msform']/ul/li[1]")
	private WebElementFacade confirmStepOnProgressBar;

	@FindBy(xpath = "//h2[contains(text(),'Current Defect')]")
	private WebElementFacade defectSubCategrySec;

	@FindBy(xpath = "//*[@id='rdoAssignCat']//td/label")
	private List<WebElementFacade> listOfRadioBtnOnOverrideSubCat;

	@FindBy(xpath = "//*[@id='ddlDefType']")
	private WebElementFacade defectTypeValuesDrpdwn;

	@FindBy(xpath = "//*[@id='ddlSubCate']")
	private WebElementFacade defectSubCategoryValuesDrpdwn;

	@FindBy(xpath = "//*[@id='btnResetSave']")
	private WebElementFacade saveButton;

	@FindBy(id = "lblCurrentDefectSubCategory")
	private WebElementFacade defectSubCatUnderCurrentDefect;

	@FindBy(xpath = "//p[@class='dc-text']/strong[contains(text(),'Defect Classification')]")
	private WebElementFacade defectClassificationSection;

	@FindBy(id = "lblBreadcrumb")
	private WebElementFacade bredCrumUnderDefectClassification;

	@FindBy(xpath = "//a[@class='btn btnPrimary next action-button']")
	private WebElementFacade nextButton;

	@FindBy(xpath = "//*[@id='verify']/h2")
	private WebElementFacade triageSectionHeader;

	@FindBy(xpath = "//a[starts-with(@id,'btnVerify')]")
	private List<WebElementFacade> listOfBtnOnDefectWorkflow;

	@FindBy(id = "btnVerifyPreviousStep")
	private WebElementFacade prevButton;

	@FindBy(id = "overrideStep")
	private WebElementFacade defectOverrideSec;

	@FindBy(xpath = "//*[@id='cblActionsOptional']/label")
	private List<WebElementFacade> listOfSOPActionsOnTriagePage;

	@FindBy(xpath = "//*[@id='cblActionsRequired']/label")
	private List<WebElementFacade> listOfSOPActionsOnActionPage;

	@FindBy(xpath = "//*[@id='required']/h2")
	private WebElementFacade actionSectionHeader;

	@FindBy(id = "btnVerifyNextStep")
	private WebElementFacade nextBtnOnTriagePage;

	@FindBy(xpath = "//*[@id='rdoAssignCat']//td/label[contains(text(),'No')]")
	private WebElementFacade noRadioBtn;

	@FindBy(xpath = "//*[@id='rdoAssignCat']//td/label[contains(text(),'Yes')]")
	private WebElementFacade yesRadioBtn;

	@FindBy(xpath = "//*[@id='cblActionsOptional']/label")
	private List<WebElementFacade> verifyAllStepsCheckbox;

	@FindBy(id = "msg_success")
	private WebElementFacade successMsg;
	
	@FindBy(id="btnA2DSave")
	private WebElementFacade a2DSaveButton;
	
	@FindBy(id = "btnRequiredStep")
	private WebElementFacade prevButtonOnActionSection;

	public boolean isDefectWorkFlowSecVisible() {
		return defectWorkflowSecHeader.isVisible();
	}

	public void moveToDefectWorkflowSec() {
		withAction().moveToElement(defectWorkflowSecHeader).build().perform();
	}

	public List<String> getProgressBarSteps() {
		List<String> listOfProgressBarsValues = new ArrayList<>();
		for (WebElementFacade progressBarValue : listOfProgressBars) {
			listOfProgressBarsValues.add(progressBarValue.getText());
		}
		return listOfProgressBarsValues;
	}

	public boolean isAssignedSubCategryVisible() {
		return defectSubCategrySec.isVisible();
	}

	public boolean verifyDefaultSelectedStepOnProgressBar(String stepName) {
		return confirmStepOnProgressBar.getText().equals(stepName);
	}

	public void selectNoRadioBtnOnOverrideSubCat() {
		if (!defectTypeValuesDrpdwn.isVisible()) {
			noRadioBtn.click();
		}
	}

	public List<String> getDefectTypeValues() {
		List<String> listOfDefectTypeValue;
		listOfDefectTypeValue = defectTypeValuesDrpdwn.getSelectOptions();
		return listOfDefectTypeValue;
	}

	public String selectAndGetTextDefectType(String defaultValueDefectTypeDrpDwn) {
		List<String> listOfDefectTypeDropDwnValues = getDefectTypeValues();
		int randomNumber;
		int size = listOfDefectTypeDropDwnValues.size();
		randomNumber = CommonMethods.getRandom(size);
		defectTypeValuesDrpdwn.selectByIndex(randomNumber);
		while (defectTypeValuesDrpdwn.getSelectedVisibleTextValue().equals(defaultValueDefectTypeDrpDwn)) {
			randomNumber = CommonMethods.getRandom(size);
			defectTypeValuesDrpdwn.selectByIndex(randomNumber);
		}
		return defectTypeValuesDrpdwn.getSelectedVisibleTextValue();
	}

	public List<String> getDefectSubCategoryValues() {
		List<String> listOfDefectSubCategory;
		listOfDefectSubCategory = defectSubCategoryValuesDrpdwn.getSelectOptions();
		return listOfDefectSubCategory;
	}

	public String selectAndGetTextDefectSubCategory(String defaultValueDefectSubCatTypeDrpDwn) {
		List<String> listOfDefectSubCatDropDwnValues = getDefectSubCategoryValues();
		int randomNumber;
		int size = listOfDefectSubCatDropDwnValues.size();
		randomNumber = CommonMethods.getRandom(size);
		defectSubCategoryValuesDrpdwn.selectByIndex(randomNumber);
		while (defectSubCategoryValuesDrpdwn.getSelectedVisibleTextValue().equals(defaultValueDefectSubCatTypeDrpDwn)) {
			randomNumber = CommonMethods.getRandom(size);
			defectSubCategoryValuesDrpdwn.selectByIndex(randomNumber);
		}
		return defectSubCategoryValuesDrpdwn.getSelectedVisibleTextValue();
	}

	public void clickOnSaveBtn() {
		saveButton.click();
	}

	public void refreshesAPage() {
		getDriver().navigate().refresh();
	}

	public String getDefectSubCategoryUnderCurrentDefect() {
		successMsg.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible();
		return defectSubCatUnderCurrentDefect.getText();
	}

	public void moveToDefectClassificationSec() {
		withAction().moveToElement(defectClassificationSection).build().perform();
	}

	public String getDefectCategoryUnderDefectClassSec() {
		return bredCrumUnderDefectClassification.getText();
	}

	public void clickOnNextButton() {
		nextButton.click();
	}

	public String getTriageSectionHeaderText() {
		return triageSectionHeader.getText();
	}

	public List<String> getButtonValuesOnSection() {
		List<String> listOfBtnValues = new ArrayList<>();
		for (WebElementFacade btnValue : listOfBtnOnDefectWorkflow) {
			listOfBtnValues.add(btnValue.getText());
		}
		return listOfBtnValues;
	}

	public void clickOnPrevBtn() {
		prevButton.click();
	}

	public boolean checkOverrideDefectCatSec() {
		return defectOverrideSec.isVisible();
	}

	public List<String> getSOPActionsOnTriagePage() {
		List<String> listOfSOPActions = new ArrayList<>();
		for (WebElementFacade SOPActionValue : listOfSOPActionsOnTriagePage) {
			listOfSOPActions.add(SOPActionValue.getText());
		}
		return listOfSOPActions;
	}

	public String getActionSectionHeaderText() {
		return actionSectionHeader.getText();
	}

	public List<String> getSOPActionsOnActionPage() {
		List<String> listOfSOPActions = new ArrayList<>();
		for (WebElementFacade SOPActionValue : listOfSOPActionsOnActionPage) {
			listOfSOPActions.add(SOPActionValue.getText());
		}
		return listOfSOPActions;
	}

	public void clickOnNextButtonOnTriagePage() {
		nextBtnOnTriagePage.click();
	}

	public boolean isDefectTypeDrpdwnVisible() {
		return defectTypeValuesDrpdwn.isVisible();
	}

	public boolean isDefectSubCategoryDrpdwnVisible() {
		return defectSubCategoryValuesDrpdwn.isVisible();
	}

	public boolean isSaveBtnVisible() {
		return saveButton.isVisible();
	}

	public void selectYesRadioBtn() {
		if (!defectTypeValuesDrpdwn.isVisible()) {
			yesRadioBtn.click();
		}
	}

	public void selectRandomVerifyAllStepsCheckbox() {
		int size = verifyAllStepsCheckbox.size();
		int index = CommonMethods.getRandom(size);
		while (index == size) {
			index = CommonMethods.getRandom(size);
		}
		verifyAllStepsCheckbox.get(index).click();
	}
	
	public String getActionTabColourVal() {
		return actionSectionHeader.getCssValue("color");
	}
	
	public void clickSOPActionOnTriagePage() {
		int randomVal=CommonMethods.getRandom(listOfSOPActionsOnTriagePage.size());
		listOfSOPActionsOnTriagePage.get(randomVal).click();
	}
	
	public void clickSOPActionOnActionPage() {
		int randomVal=CommonMethods.getRandom(listOfSOPActionsOnActionPage.size());
		listOfSOPActionsOnActionPage.get(randomVal).click();
	}
	
	public String getSuccessMessage() {
		successMsg.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
		return successMsg.getText();
	}
	
	public void clickOnA2DSaveButton() {
		evaluateJavascript("arguments[0].click();",a2DSaveButton);
	}
	
	public int getSizeOfSOPActionOnActionPage() {
		return listOfSOPActionsOnActionPage.size();
	}
	
	public void clickOnPreviousButtonOnActionSection() {
		evaluateJavascript("arguments[0].click();", prevButtonOnActionSection);
	}
	
	public List<String> getListOfSOPFromUI(){
		List<String> listOfSOP=new ArrayList<>();
		clickOnNextButton();
		listOfSOP.addAll(getSOPActionsOnTriagePage());
		clickOnNextButtonOnTriagePage();
		listOfSOP.addAll(getSOPActionsOnActionPage());
		return listOfSOP;
	}
}