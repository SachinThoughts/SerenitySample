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

	@FindBy(id = "R1Dh2id")
	private WebElementFacade innerNavConfigHeader;

	@FindBy(id = "dnn_dnnLOGIN_loginLink")
	private WebElementFacade logout;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']//li//div[position()=2]/span[starts-with(text(),'Automation')]")
	private List<WebElementFacade> listOfAutomationDefectTypes;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']//li//div[position()=2]/span[starts-with(text(),'Automation')]/ancestor::li//input[@type='radio']/following-sibling::label")
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

	@FindBy(xpath = "//*[@class='sop-types']//li//div[position()=2]")
	private List<WebElementFacade> listOfSOPName;

	@FindBy(xpath = "//div[@id='dvDefectSubCategory']/ul/li/div[position()=1]/div/div/label")
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

	@FindBy(xpath = "//*[@class='sop-types sortable defects']/li/div[position()=2]/span")
	private List<WebElementFacade> listOfDefectTypes;

	@FindBy(xpath = "//button[contains(text(),'Add Defect Type')]")
	private WebElementFacade addDefectTypeBtn;

	@FindBy(id = "addDefectTypeLabel")
	private WebElementFacade defectTypeModalPopUp;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_txtDefectType")
	private WebElementFacade defectTypeNameTextbox;

	@FindBy(xpath = "//div[text()='Defect Type Added Successfully.']")
	private WebElementFacade defectTypeSuccessMsg;

	@FindBy(xpath = "//ul[@id='sortableDefectSubCategory']/li/div[position()=2]/span")
	private List<WebElementFacade> listOfDefectSubCategories;

	@FindBy(xpath = "//*[@id='step3']/div[position()=1]/div/div[position()=1]/button")
	private WebElementFacade addDefectSubCategoryBtn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_chkDefectType")
	private WebElementFacade activeCheckbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_btnSaveDefectType")
	private WebElementFacade modalAddDefectTypeBtn;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_chkDefectSubCategory")
	private WebElementFacade defectSubCategoryActiveCheckbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_UDCDefectSubCategory_btnSaveDefectSubCategory")
	private WebElementFacade addDefectSubCategoryPopUpBtn;

	@FindBy(id = "addSOPLabel")
	private WebElementFacade addSOPModalPopUP;

	@FindBy(xpath = "//div[@class='alert alert-info']/span")
	private WebElementFacade alertMsgOnPopup;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_SOPControl_txtSOPName")
	private WebElementFacade sopNameTxtBox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_SOPControl_txtSOPDescription")
	private WebElementFacade sopDescriptionTxtBox;

	@FindBy(xpath = "//button[@data-target='#addSOP']")
	private WebElementFacade addNewSopBtn;

	@FindBy(xpath = "//*[@id='addSOP']//button[position()=2]")
	private WebElementFacade saveSopBtn;

	@FindBy(id = "msg_success")
	private WebElementFacade successMsg;

	@FindBy(id = "dTypeAhtodec")
	private WebElementFacade defectTypeTab;

	@FindBy(xpath = "//*[@class='sop-types sortable defects']//li//div[position()=2]/span[starts-with(text(),'Automation')]/ancestor::li//span[text()='Edit']")
	private List<WebElementFacade> editLinksList;

	@FindBy(xpath = "//h4[text()='Edit Defect Type']")
	private WebElementFacade editModalPopUp;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_txtUpdateDefectType")
	private WebElementFacade editDefectTypeNameTextbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_chkUpdateDefectType")
	private WebElementFacade editActiveCheckbox;

	@FindBy(id = "dnn_ctr1588_TaskPanel_taskBase_DefectTypeControl_btnUpdateDefectType")
	private WebElementFacade saveDefectTypeBtn;

	@FindBy(xpath = "//*[@id='PRCMlinktab']/a")
	private WebElementFacade pRCMDecisionConfigTab;

	@FindBy(id = "chkSOPActive")
	private WebElementFacade activeSOPCheckBox;

	@FindBy(xpath = "//*[@class='sop-types']//li//div[position()=2]")
	private List<WebElementFacade> listOfSopName;

	/**
	 * This method checks the UDC title visibility
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean checkUDCTitleVisibility() {
		return uDCTitle.isVisible();
	}

	/**
	 * This method checks if R1 decision config tab is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean decisionConfigTabIsVisible() {
		return decisionConfigTab.isVisible();
	}

	/**
	 * This method checks if R1 PRCM decision config tab is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean pRCMDecisionConfigTabIsVisible() {
		return pRCMDecisionConfigTab.isVisible();
	}

	/**
	 * This method performs click operation on R1 Decision Config tab
	 */
	public void clickOnDecisionConfigTab() {
		decisionConfigTab.click();
	}

	/**
	 * This method checks if navigation config header bar is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean innerNavConfigHeaderIsVisible() {
		return innerNavConfigHeader.isVisible();
	}

	/**
	 * This method performs click operation on R1 PRCM Decision Config tab
	 */
	public void clickOnPRCMDecisionConfig() {
		pRCMDecisionConfigTab.click();
	}

	/**
	 * This method performs click operation on logout link
	 */
	public void clickOnLogout() {
		logout.click();
	}

	/**
	 * This method is used to select random defect type
	 * 
	 * @return String This returns random defect type
	 */
	public String selectAndGetRandomDefectType() {
		randomNumber = CommonMethods.getRandom(listOfAutomationDefectTypes.size());
		while (listOfAutomationDefectTypes.size() == randomNumber) {
			randomNumber = CommonMethods.getRandom(listOfAutomationDefectTypes.size());
		}
		evaluateJavascript("arguments[0].click();", listOfAutomationRadioBtn.get(randomNumber));
		return listOfAutomationDefectTypes.get(randomNumber).getText();
	}

	/**
	 * This method performs click operation on continue button Defect Type page in
	 * UDC
	 */
	public void clickContinueBtnOnDefectType() {
		continueBtnOnDefectType.click();
	}

	/**
	 * This method adds defect sub-category if there is no defect category already
	 * created
	 */
	public void addDefectSubCategoryIfNotExist() {
		if (defectSubCategoryNotExistsTxt.isVisible()) {
			clickOnAddDefectSubCategory();
			enterDefectSubCategoryName("AutomationTesting_SubCategory_" + CommonMethods.getRandom(20));
			clickOnSaveDefectSubCategoryBtn();
			waitForAngularRequestsToFinish();
		}
	}

	/**
	 * This method performs click operation on Add Defect Sub Category button
	 */
	public void clickOnAddDefectSubCategory() {
		addDefectSubCategoryButton.click();
	}

	/**
	 * This method enters value in Defect sub category textbox
	 * 
	 * @param value This parameter is used to pass the defect sub category name to
	 *              type method
	 */
	public void enterDefectSubCategoryName(String value) {
		defectSubCategoryName.type(value);
	}

	/**
	 * This method performs click operation on Save defect sub category button
	 */
	public void clickOnSaveDefectSubCategoryBtn() {
		saveDefectSubCategoryBtn.click();
	}

	/**
	 * This method performs click operation on Continue button on defect sub
	 * category page
	 */
	public void clickContinueBtnDefectSubCategory() {
		continueBtnDefectSubCategoryPage.click();
	}

	/**
	 * This method is used to select random SOP type
	 * 
	 * @return String This returns random SOP type
	 */
	public String selectAndGetRandomSOPType() {
		radioBtnListSize = (getListOfSOPTypes().size()) - 1;
		randomNumber = CommonMethods.getRandom(radioBtnListSize);
		evaluateJavascript("return document.querySelector('#sopSteps-0" + randomNumber + "').click()");
		return evaluateJavascript("return document.querySelector('#sopSteps-0" + randomNumber + "').value").toString();
	}

	/**
	 * This method is used to capture all SOP types
	 * 
	 * @return List This returns list of SOP types
	 */
	public List<String> getListOfSOPTypes() {
		waitForAngularRequestsToFinish();
		List<String> sOPTypeList = new ArrayList<>();
		for (WebElementFacade sOPNames : listOfSOPName) {
			sOPTypeList.add(sOPNames.getText());
		}
		return sOPTypeList;
	}

	/**
	 * This method is used to select random defect sub category
	 * 
	 * @return String This returns random defect sub category
	 */
	public String selectAndGetRandomDefectSubcategory() {
		randomNumber = CommonMethods.getRandom(listOfDefectSubcategoryRadioBtn.size());
		while (listOfDefectSubcategoryRadioBtn.size() == randomNumber) {
			randomNumber = CommonMethods.getRandom(listOfDefectSubcategoryRadioBtn.size());
		}
		evaluateJavascript("arguments[0].click();", listOfDefectSubcategoryRadioBtn.get(randomNumber));
		return listOfDefectSubcategoryRadioBtn.get(randomNumber).getAttribute("value");
	}

	/**
	 * This method is used capture bread crumb value on UDC page
	 * 
	 * @return List This returns list of bread crumb
	 */
	public List<String> getListOfBreadCrumbVal() {
		List<String> getListOfBreadCrumbVal = new ArrayList<>();
		for (WebElementFacade breadCrumbDfctSum : listOfBreadCrumbDefectSummary) {
			getListOfBreadCrumbVal.add(breadCrumbDfctSum.getText());
		}
		return getListOfBreadCrumbVal;
	}

	/**
	 * This method checks if Choose SOP grid is visible
	 * 
	 * @return boolean This returns true or false depeding on element visibility
	 */
	public boolean isChooseSOPGridVisible() {
		return chooseSOPHeader.isVisible();
	}

	/**
	 * This method performs click operation on Continue button on SOP page
	 */
	public void clickContinueBtnOnSOP() {
		continueBtnOnSOP.click();
	}

	/**
	 * This method performs click operation on Add New SOP Action button
	 */
	public void clickAddNewSOPActionBtn() {
		addNewSOPActionBtn.click();
	}

	/**
	 * This method is used to enter value in SOP Action textbox
	 * 
	 * @param actionName This parameters is used to pass SOP Action Name to type
	 *                   method
	 */
	public void enterActionName(String actionName) {
		actionNameTxtBox.type(actionName);
	}

	/**
	 * This method is used to enter value in SOP Action description textbox
	 * 
	 * @param actionDescription This parameter is used to pass SOP Action
	 *                          description to type method
	 */
	public void enterActionDescription(String actionDescription) {
		actionDescriptionTxtBox.type(actionDescription);
	}

	/**
	 * This method is used to select value from Next Action drop down
	 * 
	 * @param text This parameter contains text to be selected from drop down
	 */
	public void selectNextActionByText(String text) {
		nextActionByDrpDwn.selectByVisibleText(text);
	}

	/**
	 * This method is used to enter value in follow up days textbox
	 * 
	 * @param followUpDays This parameters is used to pass follow up days value to
	 *                     type method
	 */
	public void enterFollowUpDays(String followUpDays) {
		followUpDaysTxtBox.type(followUpDays);
	}

	/**
	 * This method is used to enter value in response deadline days textbox
	 * 
	 * @param respondDeadline This parameters is used to pass response deadline
	 *                        value to type method
	 */
	public void enterRespondDeadline(String respondDeadline) {
		respondDeadlineTxtBox.type(respondDeadline);
	}

	/**
	 * This method is used to select value from Action status drop down
	 * 
	 * @param text This parameter contains text to be selected from drop down
	 */
	public void selectActionStatusByText(String text) {
		actionStatusDrpDwn.selectByVisibleText(text);
	}

	/**
	 * This method performs click operation on Save Changes button on SOP Action
	 * page
	 */
	public void clickSaveChangesSOPActionBtn() {
		saveSOPActionBtn.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().click();
	}

	/**
	 * This method is used to capture success message
	 * 
	 * @return String This returns success message
	 */
	public String getSOPSuccessMessage() {
		return sOPSuccessMessage.getText();
	}

	/**
	 * This method checks if SOP Action Pop up is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean checkSOPActionPopupDisappeared() {
		return sOPActionPopup.isVisible();
	}

	/**
	 * This method captures all the SOP actions
	 * 
	 * @return List This returns List of SOP actions
	 */
	public List<String> getListOfSOPActions() {
		List<String> listOfSOPActionValues = new ArrayList<>();
		for (WebElementFacade sOPActionElement : listOfSOPActions) {
			listOfSOPActionValues.add(sOPActionElement.getText());
		}
		return listOfSOPActionValues;
	}

	/**
	 * This method captures all the Defect types
	 * 
	 * @return List This return list of defect types
	 */
	public List<String> getListOfDefectTypes() {
		waitForAngularRequestsToFinish();
		List<String> defectTypeList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfDefectTypes) {
			defectTypeList.add(listOfNames.getText().trim());
		}
		return defectTypeList;
	}

	/**
	 * This method performs click operation on Add Defect Type button
	 */
	public void clickAddDefectTypeBtn() {
		addDefectTypeBtn.click();
	}

	/**
	 * This method checks if Defect Type Modal Pop up is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean checkDefectTypeModalPopUpVisibility() {
		return defectTypeModalPopUp.isVisible();
	}

	/**
	 * This method is used to enter value in defect name textbox
	 * 
	 * @param defectType This parameter is used pass defect name to type method
	 */
	public void enterDefectName(String defectType) {
		defectTypeNameTextbox.type(defectType);
	}

	/**
	 * This method captures success message
	 * 
	 * @return String This returns success message
	 */
	public String getDefectTypeSuccessMsg() {
		return successMsg.getText().trim();
	}

	/**
	 * This method captures newly created defect type
	 * 
	 * @return String This returns newly created defect type
	 */
	public String getNewlyAddedDefectType() {
		index = listOfDefectTypes.size() - 1;
		return listOfDefectTypes.get(index).getText().trim();
	}

	/**
	 * This method captures all defect sub category
	 * 
	 * @return List This returns list of defect sub category
	 */
	public List<String> getListOfDefectSubCategory() {
		waitForAngularRequestsToFinish();
		List<String> defectSubCategoryList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfDefectSubCategories) {
			defectSubCategoryList.add(listOfNames.getText().trim());
		}
		return defectSubCategoryList;
	}

	/**
	 * This method performs click operation on Add defect sub category button
	 */
	public void clickAddDefectSubCategoryBtn() {
		addDefectSubCategoryBtn.click();
	}

	/**
	 * This method performs click operation on Active checkbox
	 */
	public void clickActiveCheckbox() {
		activeCheckbox.click();
	}

	/**
	 * This method performs click operation on Add defect type button on Modal pop
	 * up
	 */
	public void clickModalAddDefectTypeBtn() {
		modalAddDefectTypeBtn.click();
	}

	/**
	 * This method performs click operation on Active checkbox on defect sub
	 * category pop up
	 */
	public void clickDefectSubCategoryActiveCheckbox() {
		defectSubCategoryActiveCheckbox.click();
	}

	/**
	 * This method performs click operation Add defect sub category pop up button
	 */
	public void clickAddDefectSubCategoryPopUpBtn() {
		addDefectSubCategoryPopUpBtn.click();
	}

	/**
	 * This method checks if the add SOP modal pop up is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean isAddSOPModalPopUpVisible() {
		waitForAngularRequestsToFinish();
		return addSOPModalPopUP.isVisible();
	}

	/**
	 * This method captures alert message
	 * 
	 * @return String This return alert message
	 */
	public String getValidationMsg() {
		return alertMsgOnPopup.getText();
	}

	/**
	 * This method is used to enter value in SOP name textbox
	 * 
	 * @param sopName This parameter is used to pass SOP name to type method
	 */
	public void enterTextInSopNameTxtBox(String sopName) {
		sopNameTxtBox.type(sopName);
	}

	/**
	 * This method is used to enter value in SOP description textbox
	 * 
	 * @param sopDescription This parameter is used to pass SOP description value to
	 *                       type method
	 */
	public void enterTextInSopDescriptionTxtBox(String sopDescription) {
		sopDescriptionTxtBox.type(sopDescription);
	}

	/**
	 * This method performs click operation on Add new SOP button
	 */
	public void clickAddNewSopBtn() {
		addNewSopBtn.click();
	}

	/**
	 * This method performs click operation on Save changes button
	 */
	public void clickSaveChangesSopBtn() {
		saveSopBtn.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().click();
	}

	/**
	 * This method checks if Success message pop up is visible
	 * 
	 * @return boolean This return true or false depending on the element visibility
	 */
	public boolean checkSuccessMsgPopupVisibility() {
		return successMsg.withTimeoutOf(Duration.ofSeconds(10)).waitUntilNotVisible().isVisible();
	}

	/**
	 * This method performs click operation on Defect type tab
	 */
	public void clickOnDefectTypeTab() {
		defectTypeTab.click();
	}

	/**
	 * This method performs click operation on edit link
	 */
	public void clickEditLink() {
		waitForAngularRequestsToFinish();
		index = listOfAutomationRadioBtn.size() - 1;
		evaluateJavascript("arguments[0].click();", listOfAutomationRadioBtn.get(index));
		evaluateJavascript("arguments[0].scrollIntoView(true);", editLinksList.get(index));
		evaluateJavascript("arguments[0].click();", editLinksList.get(index));
	}

	/**
	 * This method checks if edit modal pop up is visible
	 * 
	 * @return boolean This returns true or false depending on element visibility
	 */
	public boolean getEditModalPopUpVisibility() {
		return editModalPopUp.isVisible();
	}

	/**
	 * This method is used to enter value in edit defect type textbox
	 * 
	 * @param defectType This parameter is used pass defect type name to type method
	 */
	public void editDefectTypeName(String defectType) {
		editDefectTypeNameTextbox.type(defectType);
	}

	/**
	 * The method performs click operation on edit active checkbox
	 */
	public void selectEditActiveCheckbox() {
		editActiveCheckbox.click();
	}

	/**
	 * This method perform click operation on save button on defect type page
	 */
	public void clickSaveBtn() {
		saveDefectTypeBtn.click();
	}

	/**
	 * This method performs click operation on SOP active checkbox
	 */
	public void clickSOPActiveChckBox() {
		activeSOPCheckBox.click();
	}

	/**
	 * This method captures all the SOP types
	 * 
	 * @return List This returns list of SOP types
	 */
	public List<String> getListOfSopTypes() {
		waitForAngularRequestsToFinish();
		List<String> sopTypeList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfSopName) {
			sopTypeList.add(listOfNames.getText());
		}
		return sopTypeList;
	}
}