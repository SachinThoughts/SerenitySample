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

	@FindBy(xpath = "//div[@id='divDefectWorkflow']/h3")
	private WebElementFacade defectWorkflowSecHeader;

	@FindBy(xpath = "//ul[contains(@class,'DefectWorkflowProgress')]//li")
	private List<WebElementFacade> listOfProgressBars;

	@FindBy(xpath = "//div[@id='msform']//li[text()='Confirm']")
	private WebElementFacade confirmStepOnProgressBar;

	@FindBy(xpath = "//h2[contains(text(),'Current Defect')]")
	private WebElementFacade defectSubCategrySec;

	@FindBy(xpath = "//table[@id='rdoAssignCat']//label")
	private List<WebElementFacade> listOfRadioBtnOnOverrideSubCat;

	@FindBy(id = "ddlDefType")
	private WebElementFacade defectTypeValuesDrpdwn;

	@FindBy(id = "ddlSubCate")
	private WebElementFacade defectSubCategoryValuesDrpdwn;

	@FindBy(id = "btnResetSave")
	private WebElementFacade saveButton;

	@FindBy(id = "lblCurrentDefectSubCategory")
	private WebElementFacade defectSubCatUnderCurrentDefect;

	@FindBy(xpath = "//strong[text()='Defect Classification']")
	private WebElementFacade defectClassificationSection;

	@FindBy(id = "lblBreadcrumb")
	private WebElementFacade bredCrumUnderDefectClassification;
	
	@FindBy(id = "btnOverrideNextStep")
	private WebElementFacade nextButton;

	@FindBy(xpath = "//fieldset[@id='verify']/h2")
	private WebElementFacade triageSectionHeader;

	@FindBy(xpath = "//a[starts-with(@id,'btnVerify')]")
	private List<WebElementFacade> listOfBtnOnDefectWorkflow;

	@FindBy(id = "btnVerifyPreviousStep")
	private WebElementFacade prevButton;

	@FindBy(id = "overrideStep")
	private WebElementFacade defectOverrideSec;

	@FindBy(xpath = "//span[@id='cblActionsRequired']/label")
	private List<WebElementFacade> listOfSOPActionsOnActionPage;

	@FindBy(xpath = "//fieldset[@id='required']/h2")
	private WebElementFacade actionSectionHeader;

	@FindBy(id = "btnVerifyNextStep")
	private WebElementFacade nextBtnOnTriagePage;

	@FindBy(xpath = "//table[@id='rdoAssignCat']//td/label[contains(text(),'No')]")
	private WebElementFacade noRadioBtn;

	@FindBy(xpath = "//table[@id='rdoAssignCat']//td/label[contains(text(),'Yes')]")
	private WebElementFacade yesRadioBtn;

	@FindBy(xpath = "//span[@id='cblActionsOptional']/label")
	private List<WebElementFacade> verifyAllStepsCheckbox;

	@FindBy(id = "msg_success")
	private WebElementFacade successMsg;

	@FindBy(id = "btnA2DSave")
	private WebElementFacade a2DSaveButton;

	@FindBy(id = "btnRequiredStep")
	private WebElementFacade prevButtonOnActionSection;

	@FindBy(id = "verify")
	private WebElementFacade verifyAllStepsTakenSection;

	/**
	 * This method checks whether the Defect WorkFlow Section is Visible or not
	 * @return boolean value based on visibility
	 */
	public boolean isDefectWorkFlowSecVisible() {
		return defectWorkflowSecHeader.isVisible();
	}

	/**
	 * This method moves the control to Defect WorkFlow Section
	 */
	public void moveToDefectWorkflowSec() {
		withAction().moveToElement(defectWorkflowSecHeader).build().perform();
	}

	/**
	 * This method stores the Progress Bar Steps value in List
	 * @return listOfProgressBarsValues
	 */
	public List<String> getProgressBarSteps() {
		List<String> listOfProgressBarsValues = new ArrayList<>();
		for (WebElementFacade progressBarValue : listOfProgressBars) {
			listOfProgressBarsValues.add(progressBarValue.getText());
		}
		return listOfProgressBarsValues;
	}

	/**
	 * This method checks whether the Defect SubCategory Section is Visible or not
	 * @return boolean value based on visibility
	 */
	public boolean isAssignedSubCategryVisible() {
		return defectSubCategrySec.isVisible();
	}

	/**
	 * This method fetches Confirm Step On ProgressBar Value
	 * @return Confirm Step On ProgressBar Value
	 */
	public String getConfirmStepOnProgressBarValue() {
		return confirmStepOnProgressBar.getText();
	}

	/**
	 *This method selects No Radio Button On OverrideSub Category
	 */
	public void selectNoRadioBtnOnOverrideSubCat() {
		if (!defectTypeValuesDrpdwn.isVisible()) {
			noRadioBtn.click();
		}
	}

	/**
	 * This method stores the Defect Type DropDown Values
	 * @return listOfDefectTypeValue
	 */
	public List<String> getDefectTypeValues() {
		List<String> listOfDefectTypeValue;
		listOfDefectTypeValue = defectTypeValuesDrpdwn.getSelectOptions();
		return listOfDefectTypeValue;
	}


	/**
	 *This method select Defect Type From Dropdown and fetches the value selected
	 *@return SelectedVisibleText Value
	 */
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

	/**
	 * This method stores the Defect SubCategory DropDown Values
	 * @return listOfDefectTypeValue
	 */
	public List<String> getDefectSubCategoryValues() {
		List<String> listOfDefectSubCategory;
		listOfDefectSubCategory = defectSubCategoryValuesDrpdwn.getSelectOptions();
		return listOfDefectSubCategory;
	}

	/**
	 *This method select Defect SubCategory From Dropdown and fetches the value selected
	 *@return SelectedVisibleText Value
	 */
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

	/**
	 * This method clicks on Save Button Below Defect SubCategory Drop Down
	 */
	public void clickOnSaveBtn() {
		evaluateJavascript("arguments[0].click();",saveButton);
	}

	/**
	 * This method refreshes the Page
	 */
	public void refreshesAPage() {
		getDriver().navigate().refresh();
	}

	/**
	 * This method fetches Defect SubCategory Under Current Defect Value
	 * @return Defect SubCategory Under Current Defect Value
	 */
	public String getDefectSubCategoryUnderCurrentDefect() {
		successMsg.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible();
		return defectSubCatUnderCurrentDefect.getText();
	}

	/**
	 * This method moves the control to Defect Classification Section
	 */
	public void moveToDefectClassificationSec() {
		withAction().moveToElement(defectClassificationSection).build().perform();
	}

	/**
	 * This method fetches Defect Classification BreadCrumb Value
	 * @return Defect Classification BreadCrumb Value
	 */
	public String getDefectCategoryUnderDefectClassSec() {
		return bredCrumUnderDefectClassification.getText();
	}

	/**
	 * This method clicks on Next Button On Defect Override Section
	 */
	public void clickOnNextButton() {
		nextButton.click();
	}

	/**
	 * This method fetches the Triage Section Header Value
	 * @return triage Section Header Value
	 */
	public String getTriageSectionHeaderText() {
		return triageSectionHeader.getText();
	}

	/**
	 * This method stores the Button Values on Section in List
	 * @return listOfBtnValues 
	 */
	public List<String> getButtonValuesOnSection() {
		List<String> listOfBtnValues = new ArrayList<>();
		for (WebElementFacade btnValue : listOfBtnOnDefectWorkflow) {
			listOfBtnValues.add(btnValue.getText());
		}
		return listOfBtnValues;
	}

	/**
	 * This method clicks on Previous Button On Triage Section
	 */
	public void clickOnPrevBtn() {
		evaluateJavascript("arguments[0].click();",prevButton);
	}

	/**
	 * This method checks the visibility of Override Defect Category Section
	 * @return boolean value based on visibility
	 */
	public boolean checkOverrideDefectCatSec() {
		return defectOverrideSec.isVisible();
	}

	/**
	 * This method stores the SOP On Triage Page in List
	 * @return listOfSOPActions
	 */
	public List<String> getSOPActionsOnTriagePage() {
		List<String> listOfSOPActions = new ArrayList<>();
		for (WebElementFacade SOPActionValue : verifyAllStepsCheckbox) {
			listOfSOPActions.add(SOPActionValue.getText());
		}
		return listOfSOPActions;
	}

	/**
	 * This method fetches the Action Section Header Text Value
	 * @return Action Section Header Text Value
	 */
	public String getActionSectionHeaderText() {
		return actionSectionHeader.getText();
	}

	/**
	 * This method stores the SOP On Action Page in List
	 * @return listOfSOPActions
	 */
	public List<String> getSOPActionsOnActionPage() {
		List<String> listOfSOPActions = new ArrayList<>();
		for (WebElementFacade SOPActionValue : listOfSOPActionsOnActionPage) {
			listOfSOPActions.add(SOPActionValue.getText());
		}
		return listOfSOPActions;
	}

	/**
	 * This method clicks on Next Button On Traige Page
	 */
	public void clickOnNextButtonOnTriagePage() {
		if(nextBtnOnTriagePage.withTimeoutOf(Duration.ofSeconds(30)).isVisible()) {
			evaluateJavascript("arguments[0].scrollIntoView();",nextBtnOnTriagePage);
			withAction().moveToElement(nextBtnOnTriagePage).click().build().perform();
		}
	}

	/**
	 * This method checks whether DefectType DropDown is visible or not
	 * @return boolean value based on visibility
	 */
	public boolean isDefectTypeDrpdwnVisible() {
		return defectTypeValuesDrpdwn.isVisible();
	}

	/**
	 * This method checks whether Defect SubCategory DropDown is visible or not
	 * @return boolean value based on visibility
	 */
	public boolean isDefectSubCategoryDrpdwnVisible() {
		return defectSubCategoryValuesDrpdwn.isVisible();
	}

	/**
	 * This method checks whether Save Button is visible or not
	 * @return boolean value based on visibility
	 */
	public boolean isSaveBtnVisible() {
		return saveButton.isVisible();
	}

	/**
	 * This method clicks on Yes RadioButton
	 */
	public void selectYesRadioBtn() {
		if (!defectTypeValuesDrpdwn.isVisible()) {
			yesRadioBtn.click();
		}
	}

	/**
	 * This method randomly selects Verify All Steps CheckBox
	 */
	public void selectRandomVerifyAllStepsCheckbox() {
		int size = verifyAllStepsCheckbox.size();
		int index = CommonMethods.getRandom(size);
		while (index == size) {
			index = CommonMethods.getRandom(size);
		}
		verifyAllStepsCheckbox.get(index).click();
	}

	/**
	 * This method fetches Action Tab Color Value
	 * @return Action Tab Color Value
	 */
	public String getActionTabColourVal() {
		return actionSectionHeader.getCssValue("color");
	}

	/**
	 * This method clicks on any SOP Action on Triage Section
	 */
	public void clickSOPActionOnTriageSection() {
		int randomVal = CommonMethods.getRandom(verifyAllStepsCheckbox.size());
		verifyAllStepsCheckbox.get(randomVal).click();
	}

	/**
	 * This method clicks on any SOP on Action Section
	 */
	public void clickSOPActionOnActionSection() {
		int randomVal = CommonMethods.getRandom(listOfSOPActionsOnActionPage.size());
		listOfSOPActionsOnActionPage.get(randomVal).click();
	}

	/**
	 * This method fetches Success Message on saving the Defect
	 * @return successMsg
	 */
	public String getSuccessMessage() {
		successMsg.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
		return successMsg.getText();
	}

	/**
	 * This method clicks on Save Button under Defect WorkFlow Section
	 */
	public void clickOnA2DSaveButton() {
		evaluateJavascript("arguments[0].scrollIntoView();",a2DSaveButton);
		withAction().moveToElement(a2DSaveButton).click().build().perform();
	}

	/**
	 * This method fetches Size of SOPActions List
	 * @return size of SOPActions List
	 */
	public int getSizeOfSOPActionOnActionPage() {
		return listOfSOPActionsOnActionPage.size();
	}

	/**
	 * This method clicks on Previous Button On Action Section
	 */
	public void clickOnPreviousButtonOnActionSection() {
		evaluateJavascript("arguments[0].click();", prevButtonOnActionSection);
	}

	/**
	 * This method stores the SOP On Defect Workflow Section in List
	 * @return listOfSOP
	 */
	public List<String> getListOfSOPFromUI() {
		List<String> listOfSOP = new ArrayList<>();
		clickOnNextButton();
		waitForAngularRequestsToFinish();
		listOfSOP.addAll(getSOPActionsOnTriagePage());
		clickOnNextButtonOnTriagePage();
		listOfSOP.addAll(getSOPActionsOnActionPage());
		return listOfSOP;
	}
}