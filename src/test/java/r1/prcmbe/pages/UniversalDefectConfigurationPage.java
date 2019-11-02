package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class UniversalDefectConfigurationPage extends PageObject {

	private int index, randomNumber, radioBtnListSize;

	@FindBy(xpath = "//h3[contains(text(),'Universal Defect Configuration')]")
	private WebElementFacade uDCTitle;

	@FindBy(xpath = "//*[@id='anchorahtodecisiontab']/a")
	private WebElementFacade decisionConfigTab;

	@FindBy(xpath = "//*[@id='anchorahtodecisiontab']/a")
	private WebElementFacade pRCMDecisionConfig;

	@FindBy(id = "R1Dh2id")
	private WebElementFacade innerNavConfigHeader;

	@FindBy(id = "dnn_dnnLOGIN_loginLink")
	private WebElementFacade logout;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']//li//div[2]/span[starts-with(text(),'Automation')]")
	private List<WebElementFacade> listOfAutomationDefectTypes;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']//li//div[2]/span[starts-with(text(),'Automation')]/ancestor::li//input[@type='radio']/following-sibling::label")
	private List<WebElementFacade> listOfAutomationRadioBtn;

	@FindBy(xpath = "//h2[text()='Choose Defect Type']/..//button[text()='Continue ']")
	private WebElementFacade continueBtnOnDefectType;

	@FindBy(xpath = "//div[text()='Defect SubCategory not exists']")
	private WebElementFacade defectSubCategoryNotExistsTxt;

	@FindBy(xpath = "//button[text()='Add Defect SubCategory']")
	private WebElementFacade addDefectSubCategoryButton;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_txtDefectSubCategory")
	private WebElementFacade defectSubCategoryName;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_btnSaveDefectSubCategory")
	private WebElementFacade saveDefectSubCategoryBtn;

	@FindBy(xpath = "//*[@id='step3']//button[@class='btn btnSuccess btn-block addAction next-step step1 btnPrimary']")
	private WebElementFacade continueBtnDefectSubCategoryPage;

	@FindBy(xpath = "//*[@class='sop-types']//li//div[2]")
	private List<WebElementFacade> listOfSOPName;

	@FindBy(xpath = "//div[@id='dvDefectSubCategory']/ul/li/div[1]/div/div/label")
	private List<WebElementFacade> listOfDefectSubcategoryRadioBtn;

	@FindBy(xpath = "//*[@class='breadcrumb defect-summary']//li")
	private List<WebElementFacade> listOfBreadCrumbDefectSummary;

	@FindBy(xpath = "//h2[text()='Choose a SOP']")
	private WebElementFacade chooseSOPHeader;

	@FindBy(xpath = "//h2[text()='Choose a SOP']/..//button[contains(text(),'Continue')]")
	private WebElementFacade continueBtnOnSOP;

	@FindBy(xpath = "//button[text()='Add New SOP Actions']")
	private WebElementFacade addNewSOPActionBtn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_txtActionName")
	private WebElementFacade actionNameTxtBox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_txtActionDescription")
	private WebElementFacade actionDescriptionTxtBox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_ddlNextActionBy")
	private WebElementFacade nextActionByDrpDwn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_txtFollowUpDays")
	private WebElementFacade followUpDaysTxtBox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_txtTimelimitDays")
	private WebElementFacade respondDeadlineTxtBox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCActionControl_ddlActionStatus")
	private WebElementFacade actionStatusDrpDwn;

	@FindBy(xpath = "//h4[@id='addSOPActionLabel']/ancestor::div[@class='modal-content']/descendant::button[text()='Save changes']")
	private WebElementFacade saveSOPActionBtn;

	@FindBy(id = "msg_success")
	private WebElementFacade sOPSuccessMessage;

	@FindBy(xpath = "//div[@id='dvSOPAction']/descendant::div[@class='wrap-text']/span[starts-with(@id,'sopAN')]")
	private List<WebElementFacade> listOfSOPActions;

	@FindBy(id = "addSOPActionLabel")
	private WebElementFacade sOPActionPopup;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']/li/div[2]/span")
	private List<WebElementFacade> listOfDefectTypes;

	@FindBy(xpath = "//button[contains(text(),'Add Defect Type')]")
	private WebElementFacade addDefectTypeBtn;

	@FindBy(id = "addDefectTypeLabel")
	private WebElementFacade defectTypeModalPopUp;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_txtDefectType")
	private WebElementFacade defectTypeNameTextbox;

	@FindBy(xpath = "//div[text()='Defect Type Added Successfully.']")
	private WebElementFacade defectTypeSuccessMsg;

	@FindBy(xpath = "//ul[@id='sortableDefectSubCategory']/li/div[2]/span")
	private List<WebElementFacade> listOfDefectSubCategories;

	@FindBy(xpath = "//*[@id='step3']/div[1]/div/div[1]/button")
	private WebElementFacade addDefectSubCategoryBtn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_chkDefectType")
	private WebElementFacade activeCheckbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_btnSaveDefectType")
	private WebElementFacade modalAddDefectTypeBtn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_chkDefectSubCategory")
	private WebElementFacade defectSubCategoryActiveCheckbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_btnSaveDefectSubCategory")
	private WebElementFacade addDefectSubCategoryPopUpBtn;

	public boolean checkUDCTitleVisibility() {
		return uDCTitle.isVisible();
	}

	public boolean decisionConfigTabIsVisible() {
		return decisionConfigTab.isVisible();
	}

	public boolean pRCMDecisionConfigIsVisible() {
		return pRCMDecisionConfig.isVisible();
	}

	public void clickOnDecisionConfigTab() {
		decisionConfigTab.click();
	}

	public boolean innerNavConfigHeaderIsVisible() {
		return innerNavConfigHeader.isVisible();
	}

	public void clickOnPRCMDecisionConfig() {
		pRCMDecisionConfig.click();
	}

	public void clickOnLogout() {
		logout.click();
	}

	public String selectAndGetRandomDefectType() {
		randomNumber = CommonMethods.getRandom(listOfAutomationDefectTypes.size());
		while (listOfAutomationDefectTypes.size() == randomNumber) {
			randomNumber = CommonMethods.getRandom(listOfAutomationDefectTypes.size());
		}
		evaluateJavascript("arguments[0].click();", listOfAutomationRadioBtn.get(randomNumber));
		return listOfAutomationDefectTypes.get(randomNumber).getText();
	}

	public void clickContinueBtnOnDefectType() {
		continueBtnOnDefectType.click();
	}

	public void addDefectSubCategoryIfNotExist() {
		if (defectSubCategoryNotExistsTxt.isVisible()) {
			clickOnAddDefectSubCategory();
			enterDefectSubCategoryName("AutomationTesting_SubCategory_" + CommonMethods.getRandom(20));
			clickOnSaveDefectSubCategoryBtn();
			waitForAngularRequestsToFinish();
		}
	}

	public void clickOnAddDefectSubCategory() {
		addDefectSubCategoryButton.click();
	}

	public void enterDefectSubCategoryName(String value) {
		defectSubCategoryName.type(value);
	}

	public void clickOnSaveDefectSubCategoryBtn() {
		saveDefectSubCategoryBtn.click();
	}

	public void clickContinueBtnDefectSubCategory() {
		continueBtnDefectSubCategoryPage.click();
	}

	public String selectAndGetRandomSOPType() {
		radioBtnListSize = (getListOfSOPTypes().size()) - 1;
		randomNumber = CommonMethods.getRandom(radioBtnListSize);
		evaluateJavascript("return document.querySelector('#sopSteps-0" + randomNumber + "').click()");
		return evaluateJavascript("return document.querySelector('#sopSteps-0" + randomNumber + "').value").toString();
	}

	public List<String> getListOfSOPTypes() {
		waitForAngularRequestsToFinish();
		List<String> sOPTypeList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfSOPName) {
			sOPTypeList.add(listOfNames.getText());
		}
		return sOPTypeList;
	}

	public String selectAndGetRandomDefectSubcategory() {
		randomNumber = CommonMethods.getRandom(listOfDefectSubcategoryRadioBtn.size());
		while (listOfDefectSubcategoryRadioBtn.size() == randomNumber) {
			randomNumber = CommonMethods.getRandom(listOfDefectSubcategoryRadioBtn.size());
		}
		evaluateJavascript("arguments[0].click();", listOfDefectSubcategoryRadioBtn.get(randomNumber));
		return listOfDefectSubcategoryRadioBtn.get(randomNumber).getAttribute("value");
	}

	public List<String> getListOfBreadCrumbVal() {
		List<String> getListOfBreadCrumbVal = new ArrayList<>();
		for (WebElementFacade listOfBreadCrumbDfctSum : listOfBreadCrumbDefectSummary) {
			getListOfBreadCrumbVal.add(listOfBreadCrumbDfctSum.getText());
		}
		return getListOfBreadCrumbVal;
	}

	public boolean isChooseSOPGridVisible() {
		return chooseSOPHeader.isVisible();
	}

	public void clickContinueBtnOnSOP() {
		continueBtnOnSOP.click();
	}

	public void clickAddNewSOPActionBtn() {
		addNewSOPActionBtn.click();
	}

	public void enterActionName(String actionName) {
		actionNameTxtBox.type(actionName);
	}

	public void enterActionDescription(String actionDescription) {
		actionDescriptionTxtBox.type(actionDescription);
	}

	public void selectNextActionByText(String text) {
		nextActionByDrpDwn.selectByVisibleText(text);
	}

	public void enterFollowUpDays(String followUpDays) {
		followUpDaysTxtBox.type(followUpDays);
	}

	public void enterRespondDeadline(String respondDeadline) {
		respondDeadlineTxtBox.type(respondDeadline);
	}

	public void selectActionStatusByText(String text) {
		actionStatusDrpDwn.selectByVisibleText(text);
	}

	public void clickSaveChangesSOPActionBtn() {
		saveSOPActionBtn.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().click();
	}

	public String getSOPSuccessMessage() {
		return sOPSuccessMessage.getText();
	}

	public boolean checkSOPActionPopupDisappeared() {
		return sOPActionPopup.isVisible();
	}

	public List<String> getListOfSOPActions() {
		List<String> listOfSOPActionValues = new ArrayList<>();
		for (WebElementFacade sOPActionElement : listOfSOPActions) {
			listOfSOPActionValues.add(sOPActionElement.getText());
		}
		return listOfSOPActionValues;
	}

	public List<String> getListOfDefectTypes() {
		waitForAngularRequestsToFinish();
		List<String> defectTypeList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfDefectTypes) {
			defectTypeList.add(listOfNames.getText().trim());
		}
		return defectTypeList;
	}

	public void clickAddDefectTypeBtn() {
		addDefectTypeBtn.click();
	}

	public boolean checkDefectTypeModalPopUpVisibility() {
		return defectTypeModalPopUp.isVisible();
	}

	public void enterDefectName(String defectType) {
		defectTypeNameTextbox.type(defectType);
	}

	public String getDefectTypeSuccessMsg() {
		return defectTypeSuccessMsg.getText().trim();
	}

	public String getNewlyAddedDefectType() {
		index = listOfDefectTypes.size() - 1;
		return listOfDefectTypes.get(index).getText().trim();
	}

	public List<String> getListOfDefectSubCategory() {
		waitForAngularRequestsToFinish();
		List<String> defectSubCategoryList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfDefectSubCategories) {
			defectSubCategoryList.add(listOfNames.getText().trim());
		}
		return defectSubCategoryList;
	}

	public void clickAddDefectSubCategoryBtn() {
		addDefectSubCategoryBtn.click();
	}

	public void clickActiveCheckbox() {
		activeCheckbox.click();
	}

	public void clickModalAddDefectTypeBtn() {
		modalAddDefectTypeBtn.click();
	}

	public void clickDefectSubCategoryActiveCheckbox() {
		defectSubCategoryActiveCheckbox.click();
	}

	public void clickAddDefectSubCategoryPopUpBtn() {
		addDefectSubCategoryPopUpBtn.click();
	}
}