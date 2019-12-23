package r1.prcmbe.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import net.serenitybdd.core.annotations.findby.FindBy;

public class DefaultHandoffPage extends PageObject {

	String workflowName, getDispositionStatus;

	@FindBy(xpath = "//h3[@class='panel-title']")
	private WebElementFacade defaultHandOffPageTitle;

	@FindBy(id = "btnAddHandoff")
	private WebElementFacade addHandOffButton;

	@FindBy(xpath = "//label[@for='addWorkflowName']")
	private WebElementFacade addHandOffWorkflowNameLabel;

	@FindBy(xpath = "//label[@for='addWorkflowDescription']")
	private WebElementFacade addHandOffWorkflowDescriptionLabel;

	@FindBy(xpath = "//label[contains(text(),'Worklist')]")
	private WebElementFacade addHandOffWorkList;

	@FindBy(xpath = "//label[contains(text(),'AH Module Code')]")
	private WebElementFacade addHandOffAHModuleCode;

	@FindBy(xpath = "//label[contains(text(),'Visible to Group')]")
	private WebElementFacade addHandOffVisibleToGroupLabel;

	@FindBy(xpath = "//label[contains(text(),'Activate Handoff')]")
	private WebElementFacade addHandOffActivateHandOffLabel;

	@FindBy(xpath = "//*[@id='addHandoff']//button[@class='btn btn-default']")
	private WebElementFacade addHandOffCloseButton;

	@FindBy(xpath = "//*[@id='addHandoff']//button[@class='btn btnPrimary']")
	private WebElementFacade addHandOffSaveChangesButton;

	@FindBy(id = "addWorkflowName")
	private WebElementFacade workflowNameTextBox;

	@FindBy(id = "addWorkflowDescription")
	private WebElementFacade workflowDescriptionTextBox;

	@FindBy(id = "dnn_ctr1590_TaskPanel_taskBase_ddlWorkList")
	private WebElementFacade workListDD;

	@FindBy(id = "addAHModuleCode")
	private WebElementFacade ahModuleCodeTextBox;

	@FindBy(xpath = "//div[contains(text(),'Select some options')]")
	private WebElementFacade visibleToGroupDD;

	@FindBy(xpath = "//*[@id='addHandoff']//span//strong[text()='Info']")
	private WebElementFacade addHandOffInfoMessage;

	@FindBy(xpath = "(//label[@for='addHandoffActive'])[2]")
	private WebElementFacade activateHandOffCheckBox;

	@FindBy(xpath = "(//div//span[@class='fs-checkbox'])[1]")
	private WebElementFacade visibleToGroupCheckBox;

	@FindBy(id = "msg_success")
	private WebElementFacade successMessage;

	@FindBy(xpath = "//div//span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> listOfAddedHandOffs;

	@FindBy(xpath = "//label[@data-bind='attr: {for: ']")
	private List<WebElementFacade> listOfAddedHandOffRadioButton;

	@FindBy(xpath = "//button[@class='btn btnSuccess btn-block addAction next-step step1 btnPrimary']")
	private WebElementFacade stepOneContinueBtn;

	@FindBy(xpath = "//div[@id='dvRecipientDetails' and text()='No Recipient exists for the selected Handoff']")
	private WebElementFacade recipientDetailsMessage;

	@FindBy(xpath = "//button[contains(text(),' Add Recipient')]")
	private WebElementFacade addRecipientButton;

	@FindBy(id = "txtRecipientName")
	private WebElementFacade recipientNameTextBox;

	@FindBy(id = "txtRecipientDescription")
	private WebElementFacade recipientDescriptionTextBox;

	@FindBy(xpath = "//*[@id='addRecipient']//div//button[contains(text(),'Add Recipient')]")
	private WebElementFacade saveRecipientButton;

	@FindBy(xpath = "//ol[@class='breadcrumb defect-summary']")
	private WebElementFacade breadcrumb;

	@FindBy(id = "sName0")
	private WebElementFacade savedRecipientName;

	@FindBy(id = "sDescription0")
	private WebElementFacade savedRecipientDescription;

	@FindBy(id = "NA0")
	private WebElementFacade savedActionName;

	@FindBy(id = "FD0")
	private WebElementFacade savedFollowUpDays;

	@FindBy(id = "TL0")
	private WebElementFacade savedTimeLineDays;

	@FindBy(xpath = "//button[@class='btn btnSuccess btn-block addAction next-step step2 btnPrimary']")
	private WebElementFacade stepTwoContinueBtn;

	@FindBy(xpath = "//div[@id='dvWorkflowTypeActions' and text()='No Action exists for the selected Recipient']")
	private WebElementFacade workflowTypeActionsMessage;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions' and text()='No Disposition exists for the selected action type']")
	private WebElementFacade workflowTypeDispositionMessage;

	@FindBy(xpath = "//button[contains(text(),' Add New Action')]")
	private WebElementFacade addNewActionButton;

	@FindBy(xpath = "//*[@id='addNewAction']//input[@id='txtActionName']")
	private WebElementFacade actionNameTextBox;

	@FindBy(xpath = "//*[@id='addNewAction']//input[@id='txtActionDescription']")
	private WebElementFacade actionDescriptionTextBox;

	@FindBy(xpath = "//*[@id='addNewAction']//div//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowActionType_ddlNextActionBy']")
	private WebElementFacade nextActionByDD;

	@FindBy(xpath = "//*[@id='addNewAction']//div//input[@id='txtActionFUD']")
	private WebElementFacade followUpDaysTextBox;

	@FindBy(xpath = "//*[@id='addNewAction']//div//input[@id='txtActionTimeLimit']")
	private WebElementFacade respondDeadlineTextBox;

	@FindBy(xpath = "//*[@id='addNewAction']//div//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowActionType_ddlActionStatus']")
	private WebElementFacade actionStatusDD;

	@FindBy(xpath = "//*[@id='addNewAction']//button[@class='btn btnPrimary']")
	private WebElementFacade addNewActionSaveChangesButton;

	@FindBy(xpath = "//button[@class='btn btnSuccess btn-block addAction next-step step3 btnPrimary']")
	private WebElementFacade stepThreeContinueBtn;

	@FindBy(xpath = "//button[contains(text(),' Add New Disposition')]")
	private WebElementFacade addNewDispositionButton;

	@FindBy(xpath = "//*[@id='addNewDisposition']//input[@id='txtdispositionCode']")
	private WebElementFacade dispositionCodeTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//input[@id='txtdispositionDescription']")
	private WebElementFacade dispositionNameTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowDisposition_ddldispositionTarget']")
	private WebElementFacade nextDispositionByDD;

	@FindBy(xpath = "//*[@id='addNewDisposition']//input[@id='txtdispositionFUD']")
	private WebElementFacade dispositionFollowUpDaysTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//input[@id='txtdispositionTimeLimit']")
	private WebElementFacade dispositionRespondDeadlineTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowDisposition_ddldispositionStatus']")
	private WebElementFacade dispositionStatusDD;

	@FindBy(xpath = "//*[@id='addNewDisposition']//textarea[@id='txtDispositionsNotes']")
	private WebElementFacade preDefinedNotes;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[@class='btn btnPrimary']")
	private WebElementFacade addNewDispositionSaveChangesButton;

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable'])[last()]/li[last()]/div[4]/span")
	private WebElementFacade savedDispositionFollowUpDays;

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable'])/li[last()]/div[5]/span")
	private WebElementFacade savedDispositionTimeLimit;

	@FindBy(id = "ASName0")
	private WebElementFacade savedDispositionStatus;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//div[3]//span[contains(@id,'Disp')]")
	private List<WebElementFacade> dispositionNameList;

	@FindBy(xpath = "//div[@class='fs-option-label' and text()='AHtoDecision Admin']/preceding-sibling::span")
	private WebElementFacade visibleToGrpAHtoDecisionChkBox;

	@FindBy(id = "dnn_ctr1534_dnnTITLE_titleLabel")
	private WebElementFacade facilitySettingConfigTitle;

	@FindBy(id = "dllLocationSearch")
	private WebElementFacade searchDrpdwn;

	@FindBy(id = "txtLocSearchValue")
	private WebElementFacade searchTxtBox;

	@FindBy(id = "BtnLocSearch")
	private WebElementFacade searchBtn;

	@FindBy(xpath = "//*[@id='LocationGridData']/div[@class='Rows']/div[@class='col'][1]")
	private WebElementFacade searchFacilityCode;

	@FindBy(xpath = "//*[@id='LocationGridData']/div[@class='Rows']/div[@class='col'][10]/a")
	private WebElementFacade searchFacilityViewLink;

	@FindBy(id = "pnlFacilitySettings")
	private WebElementFacade searchFacilitySettingGrid;

	@FindBy(id = "dllFSSearch")
	private WebElementFacade facilitySettingSearchDrpdwn;

	@FindBy(id = "txtFSSearchValue")
	private WebElementFacade facilitySettingSearchTxtBox;

	@FindBy(id = "BtnFSSearch")
	private WebElementFacade facilitySettingSearchBtn;

	@FindBy(xpath = "//*[@id='FacilitySettingsGridData']/div[@class='Rows']/div[@class='col'][1]")
	private WebElementFacade searchFacilitySettingText;

	@FindBy(xpath = "//*[@id='FacilitySettingsGridData']/div[@class='Rows']/div[@title='Update Facility Setting']")
	private WebElementFacade searchFSEditIcon;

	@FindBy(id = "FacilitySettingsModelpopup")
	private WebElementFacade facilitySettingDetailsPopup;

	@FindBy(id = "txtSettingValue")
	private WebElementFacade settingValueTxtArea;

	@FindBy(xpath = "//input[@type='button' and @value='Update Facility Setting']")
	private WebElementFacade updateSettingValueBtn;

	@FindBy(xpath = "//span[contains(text(),'AHtoDecision Admin')]/ancestor::li/div[2]/span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> listOfAHtoDecisionAdminWorkflows;

	@FindBy(id = "ddlHandOffType")
	private WebElementFacade handoffTypeDrpdwn;

	@FindBy(xpath = "//*[@id='ddlHandoffDirection']/option")
	private List<WebElementFacade> createDrpdwnOption;

	@FindBy(id = "ddlHandoffDirection")
	private WebElementFacade createDrpdwn;

	@FindBy(xpath = "//*[@id='ddlAction']/option")
	private List<WebElementFacade> whyDrpdwnOption;

	@FindBy(id = "ddlAction")
	private WebElementFacade whyDrpdwn;

	@FindBy(id = "ddlDisposition")
	private WebElementFacade dispositionDrpdwn;

	@FindBy(xpath = "//*[@id='ddlDisposition']/option")
	private List<WebElementFacade> dispositionDrpdwnOptions;

	@FindBy(id = "txtHandOffNotes")
	private WebElementFacade noteTextBox;

	@FindBy(xpath = "//h3[ text()=' Other Account Defects ']")
	private WebElementFacade showHistoryBtn;

	@FindBy(xpath = "//*[@id='carousel']//li[last()]/div[last()]//a[@class='trigger']/..")
	private WebElementFacade addedBubble;

	@FindBy(id = "btnSaveHandsOff")
	private WebElementFacade handoffSaveBtn;

	@FindBy(xpath = "//div[@class='popover-content']//span//span[1]")
	private List<WebElementFacade> listOfEventCircleColumns;

	@FindBy(xpath = "//div[@class='popover-content']//span//span[2]")
	private List<WebElementFacade> popupValues;

	@FindBy(xpath = "//div[@class='popover-content']//div[@class='timeline-details']//span[1]/child::span[2]")
	private WebElementFacade createdDateOnPopup;

	@FindBy(xpath = "//div[@class='popover-content']//div[@class='timeline-details']//span[3]/child::span[2]")
	private WebElementFacade followupDateOnPopup;

	@FindBy(xpath = "//*[@id='btnShowHistory']/span")
	private WebElementFacade accountActionHistoryNotesBtn;

	@FindBy(xpath = "//*[@id='notesHistory']/li[1]/div/div/div/span/span[1]")
	private List<WebElementFacade> accountActionHistoryColumns;

	@FindBy(xpath = "//*[@id='notesHistory']/li[1]/div/div/div/span/span[2]")
	private List<WebElementFacade> accountActionHistoryValues;

	@FindBy(xpath = "//*[@id='notesHistory']/li[1]/div/div/div[3]/span[1]/span[2]")
	private WebElementFacade accountActionHistoryAddedDate;

	@FindBy(xpath = "//*[@id='notesHistory']/li[1]/div/div/div[3]/span[3]/span[2]")
	private WebElementFacade accountActionHistoryFollowupDate;

	@FindBy(id = "patAAH")
	private WebElementFacade accountActionHistoryExpandButton;

	@FindBy(xpath = "//*[@id='carousel']/ul/li[2]/a")
	private WebElementFacade accountActionHistoryTimelineArrow;

	public boolean isAddNewActionBtnVisisble() {
		return addNewActionButton.isVisible();
	}

	public String getTextDefaultHandOffPageTitle() {
		return defaultHandOffPageTitle.getText().trim();
	}

	public boolean verifyVisibilityOfAddHandOffButton() {
		return addHandOffButton.isVisible();
	}

	public void clickAddHandOffButton() {
		addHandOffButton.click();
	}

	public String getTextAddHandOffWorkFlowNameLabel() {
		return addHandOffWorkflowNameLabel.getText().trim();
	}

	public String getTextAddHandOffWorkFlowDescriptionLabel() {
		return addHandOffWorkflowDescriptionLabel.getText().trim();
	}

	public String getTextAddHandOffWorkList() {
		return addHandOffWorkList.getText().trim();
	}

	public String getTextAddHandOffAHModuleCode() {
		return addHandOffAHModuleCode.getText().trim();
	}

	public String getTextAddHandOffVisibleToGroupLabel() {
		return addHandOffVisibleToGroupLabel.getText().trim();
	}

	public String getTextAddHandOffActivateHandOffLabel() {
		return addHandOffActivateHandOffLabel.getText().trim();
	}

	public String getTextAddHandOffCloseButton() {
		return addHandOffCloseButton.getText().trim();
	}

	public String getTextAddHandOffSaveChangesButton() {
		return addHandOffSaveChangesButton.getText().trim();
	}

	public boolean checkVisibilityOfWorkFlowNameTextBox() {
		return workflowNameTextBox.isVisible();
	}

	public boolean checkVisibilityOfWorkFlowDescriptionTextBox() {
		return workflowDescriptionTextBox.isVisible();
	}

	public boolean checkVisibilityOfWorkListDD() {
		return workListDD.isVisible();
	}

	public boolean checkVisibilityOfAHModuleCodeTextBox() {
		return ahModuleCodeTextBox.isVisible();
	}

	public boolean checkVisibilityOfVisibleToGroupDD() {
		return visibleToGroupDD.isVisible();
	}

	public boolean checkVisibilityOfActivateHandOffCheckBox() {
		return activateHandOffCheckBox.isVisible();
	}

	public boolean checkVisibilityOfAddHandOffCloseButton() {
		return addHandOffCloseButton.isVisible();
	}

	public boolean checkVisibilityOfAddHandOffSaveChangesButton() {
		return addHandOffSaveChangesButton.isVisible();
	}

	public String getTextSuccessMessage() {
		return successMessage.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText().trim();
	}

	public String enterWorkFlowName() {
		workflowName = RandomStringUtils.randomAlphabetic(15);
		workflowNameTextBox.type(workflowName);
		return workflowName;
	}

	public void enterWorkFlowDescription(String description) {
		workflowDescriptionTextBox.type(description);
	}

	public void selectWorkListDD() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", workListDD);
		List<String> listOfValuesOfWorkListDD = workListDD.getSelectOptions();
		int index = CommonMethods.getRandom(listOfValuesOfWorkListDD.size() - 1);
		workListDD.selectByIndex(index);
	}

	public void enterAHModuleCode(String ahModuleCode) {
		ahModuleCodeTextBox.type(ahModuleCode);
	}

	public void selectVisibleToGroup() {
		visibleToGroupDD.click();
		withAction().moveToElement(visibleToGroupCheckBox).click().build().perform();
	}

	public void clickAddHandOffSaveChangesButton() {
		addHandOffSaveChangesButton.click();
	}

	public void selectNewlyAddedHandOff() {
		List<String> listOfAddedHandOffNames = new ArrayList<>();
		for (int i = 0; i < listOfAddedHandOffs.size(); i++) {
			if (i % 2 == 0)
				listOfAddedHandOffNames.add(listOfAddedHandOffs.get(i).getText().trim());
		}
		for (int i = 0; i < listOfAddedHandOffNames.size(); i++) {
			if (listOfAddedHandOffNames.get(i).equals(workflowName)) {
				withAction().moveToElement(listOfAddedHandOffRadioButton.get(i)).build().perform();
				listOfAddedHandOffRadioButton.get(i).click();
				break;
			}
		}
	}

	public void clickStepOneContinueBtn() {
		stepOneContinueBtn.click();
	}

	public String getTextNoRecipientMsg() {
		return recipientDetailsMessage.getText().trim();
	}

	public void clickAddRecipientButton() {
		addRecipientButton.click();
	}

	public String enterRecipientNameTextBox() {
		String recipientName = RandomStringUtils.randomAlphabetic(14);
		recipientNameTextBox.type(recipientName);
		return recipientName;
	}

	public String enterRecipientDescriptionTextBox(String recipientDescription) {
		recipientDescriptionTextBox.type(recipientDescription);
		return recipientDescription;
	}

	public void clickSaveRecipientButton() {
		saveRecipientButton.click();
	}

	public String getTextSavedRecipientName() {
		return savedRecipientName.getText().trim();
	}

	public String getTextSavedRecipientDescription() {
		return savedRecipientDescription.getText().trim();
	}

	public String getTextBreadcrumb() {
		return breadcrumb.getText().trim();
	}

	public void clickStepTwoContinueBtn() {
		stepTwoContinueBtn.click();
	}

	public String getTextworkFlowTypeActionsMessage() {
		return workflowTypeActionsMessage.getText().trim();
	}

	public void clickAddNewActionButton() {
		addNewActionButton.click();
	}

	public String enterActionNameTextBox() {
		String actionName = RandomStringUtils.randomAlphabetic(13);
		actionNameTextBox.type(actionName);
		return actionName;
	}

	public String getActionNameFromTextBox() {
		return evaluateJavascript("return arguments[0].value;", actionNameTextBox).toString();
	}

	public void enterCopiedActionName(String previousActionName) {
		actionNameTextBox.type(previousActionName);
	}

	public String enterActionDescriptionTextBox(String actionDescription) {
		actionDescriptionTextBox.type(actionDescription);
		return actionDescription;
	}

	public void selectNextActionByDD(String nextActionValue) {
		nextActionByDD.selectByVisibleText(nextActionValue);
	}

	public void enterFollowUpDaysTextBox(String followUpDaysValue) {
		followUpDaysTextBox.type(followUpDaysValue);
	}

	public void enterRespondDeadlineTextBox(String respondDeadlineValue) {
		respondDeadlineTextBox.type(respondDeadlineValue);
	}

	public void selectActionStatusDD(String actionStatusValue) {
		actionStatusDD.selectByVisibleText(actionStatusValue);
	}

	public void clickAddNewActionSaveChangesButton() {
		addNewActionSaveChangesButton.click();
	}

	public String getTextSavedActionName() {
		return savedActionName.getText().trim();
	}

	public String getTextSavedFollowUpDays() {
		return savedFollowUpDays.getText().trim();
	}

	public String getTextSavedTimelineDays() {
		return savedTimeLineDays.getText().trim();
	}

	public void clickStepThreeContinueBtn() {
		stepThreeContinueBtn.click();
	}

	public String getTextWorkFlowTypeDispositionMessage() {
		return workflowTypeDispositionMessage.getText().trim();
	}

	public void clickAddNewDispositionButton() {
		addNewDispositionButton.click();
	}

	public String enterDispositionCode() {
		String enterDispositionCode = RandomStringUtils.randomAlphabetic(13);
		dispositionCodeTextBox.type(enterDispositionCode);
		return enterDispositionCode;
	}

	public String enterDispositionNameTextBox(String dispositionDescription) {
		dispositionNameTextBox.type(dispositionDescription);
		return dispositionDescription;
	}
	
	public String getEnterDispositionName() {
		return evaluateJavascript("return arguments[0].value;", dispositionNameTextBox).toString();	
	}

	public void selectNextDispositionByDD() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDD);
		List<String> listOfValuesOfNextDispositionDD = nextDispositionByDD.getSelectOptions();
		nextDispositionByDD.selectByIndex(CommonMethods.getRandom(listOfValuesOfNextDispositionDD.size() - 1) + 1);
	}

	public void enterDispositionFollowUpDaysTextBox(String dispositionFollowUpDaysValue) {
		dispositionFollowUpDaysTextBox.type(dispositionFollowUpDaysValue);
	}

	public void enterDispositionRespondDeadlineTextBox(String dispositionRespondDeadlineValue) {
		dispositionRespondDeadlineTextBox.type(dispositionRespondDeadlineValue);
	}

	public void selectDispositionStatusDropdown(String dispositionStatusValue) {
		dispositionStatusDD.selectByVisibleText(dispositionStatusValue);
		getDispositionStatus = dispositionStatusDD.getSelectedVisibleTextValue();

	}

	public void selectDispositionStatusFromDropdown(String dispositionStatusValue) {
		dispositionStatusDD.selectByVisibleText(dispositionStatusValue);
	}

	public boolean isSelectedValueInDispositionStatusVisible(String expectedDrpDownValue) {
		evaluateJavascript("arguments[0].scrollIntoView(true);", dispositionStatusDD);
		getDispositionStatus = dispositionStatusDD.getSelectedVisibleTextValue();
		if (getDispositionStatus.trim().equals(expectedDrpDownValue)) {
			return true;
		}
		return false;
	}

	public void enterPreDefinedNotes(String preDefinedNotesValue) {
		preDefinedNotes.type(preDefinedNotesValue);
	}

	public void clickAddNewDispositionSaveChangesButton() {
		addNewDispositionSaveChangesButton.click();
	}

	public String getTextSavedDispositionName(String dispositionName) {
		int i;
		int size = dispositionNameList.size();
		int flag = 0;
		for (i = 0; i < size; i++) {
			if (dispositionNameList.get(i).getText().equals(dispositionName)) {
				withAction().moveToElement(dispositionNameList.get(i)).build().perform();
			}
			flag = 1;
			if (flag == 1) {
				break;
			}
		}
		return dispositionNameList.get(i).getText().trim();
	}

	public String getTextSavedDispositionFollowUpDays() {
		return savedDispositionFollowUpDays.getText().trim();
	}

	public String getTextSavedDispositionTimeLimit() {
		return savedDispositionTimeLimit.getText().trim();
	}

	public String getTextSavedDispositionStatus() {
		return savedDispositionStatus.getText().trim();
	}

	public void clickVisibleToGrpAHtoDecisionChkBox() {
		visibleToGrpAHtoDecisionChkBox.click();
	}

	public String getFacilitySettingConfigTitle() {
		return facilitySettingConfigTitle.getText();
	}

	public void selectTextSearchDrpdwn(String searchValue) {
		searchDrpdwn.selectByVisibleText(searchValue);
	}

	public void enterSearchTxtBox(String value) {
		searchTxtBox.type(value);
	}

	public void clickSearchBtn() {
		searchBtn.click();
	}

	public String getSearchFacilityCode() {
		return searchFacilityCode.getText();
	}

	public void clickSearchFacilityViewLink() {
		searchFacilityViewLink.click();
	}

	public boolean isSearchFacilitySettingGridVisible() {
		return searchFacilitySettingGrid.isVisible();
	}

	public void selectTextFSSearchDrpdwn(String searchValue) {
		facilitySettingSearchDrpdwn.selectByVisibleText(searchValue);
	}

	public void enterFSSearchTxtBox(String value) {
		facilitySettingSearchTxtBox.type(value);
	}

	public void clickFSSearchBtn() {
		facilitySettingSearchBtn.click();
	}

	public void clickSearchFSEditIcon() {
		searchFSEditIcon.click();
	}

	public String getSearchFacilitySettingText() {
		return searchFacilitySettingText.getText();
	}

	public boolean isFSDetailsPopupVisible() {
		return facilitySettingDetailsPopup.isVisible();
	}

	public String getSettingValueTxtArea() {
		return evaluateJavascript("return document.querySelector('#txtSettingValue').value").toString();
	}

	public void updateSettingValueTxtArea(String settingValue) {
		settingValueTxtArea.type(getSettingValueTxtArea() + settingValue);
	}

	public void clickUpdateSettingValueBtn() {
		updateSettingValueBtn.click();
	}

	public String getSecondLastAHtoDecisionAdminWorkflow() {
		return listOfAHtoDecisionAdminWorkflows.get(listOfAHtoDecisionAdminWorkflows.size() - 2).getText();
	}

	public String selectHandoffType(String dropdown) {
		waitForAngularRequestsToFinish();
		return handoffTypeDrpdwn.selectByVisibleText(dropdown).getSelectedVisibleTextValue();
	}

	public String selectAnyOptionFromCreateDrpdwn() {
		waitForAngularRequestsToFinish();
		int index = CommonMethods.getRandom(createDrpdwnOption.size() - 1) + 1;
		return createDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}

	public String selectAnyOptionFromWhyDrpdwn() {
		waitForAngularRequestsToFinish();
		int index = CommonMethods.getRandom(whyDrpdwnOption.size() - 1) + 1;
		return whyDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}

	public String selectAnyOptionFromDispositionDrpdwn() {
		if (dispositionDrpdwn.isVisible()) {
			waitForAngularRequestsToFinish();
			int index = CommonMethods.getRandom(dispositionDrpdwnOptions.size() - 1) + 1;
			return dispositionDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
		}
		return "";
	}

	public void enterNote(String note) {
		noteTextBox.type(note);
	}

	public String getAddedBubbleText() {
		withAction().moveToElement(addedBubble).build().perform();
		return addedBubble.getText();
	}

	public String getHandoffBubbleColor() {
		addedBubble.withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible();
		withAction().moveToElement(addedBubble).build().perform();
		return addedBubble.getCssValue("background-color");
	}

	public void hoverOnAddedBubble() {
		withAction().moveToElement(addedBubble).build().perform();
	}

	public void clickHandoffSaveBtn() {
		handoffSaveBtn.click();
	}

	public List<String> getListOfEventCircleColumns() {
		List<String> listOfEventCircleCols = new ArrayList<>();
		for (WebElementFacade eventCircleCol : listOfEventCircleColumns) {
			listOfEventCircleCols.add(eventCircleCol.getText().trim());
		}
		return listOfEventCircleCols;
	}

	public List<String> getListOfPopupValues() {
		List<String> popupValList = new ArrayList<>();
		for (WebElementFacade popupValue : popupValues) {
			popupValList.add(popupValue.getText());
		}
		return popupValList;
	}

	public String getCreatedDate() {
		return createdDateOnPopup.getText();
	}

	public String getFollowupDate() {
		return followupDateOnPopup.getText();
	}

	public void clickAccountActionHistoryNotesBtn() {
		withAction().moveToElement(accountActionHistoryNotesBtn).build().perform();
		evaluateJavascript("arguments[0].click();", accountActionHistoryNotesBtn);
	}

	public List<String> getAccountActionHistoryColumns() {
		List<String> listOfAccountActionHistoryColumns = new ArrayList<>();
		for (WebElementFacade accountActionHistoryColumn : accountActionHistoryColumns) {
			withAction().moveToElement(accountActionHistoryColumn).build().perform();
			listOfAccountActionHistoryColumns.add(accountActionHistoryColumn.getText());
		}
		return listOfAccountActionHistoryColumns;
	}

	public List<String> getAccountActionHistoryValues() {
		List<String> listOfAccountActionHistoryValues = new ArrayList<>();
		for (WebElementFacade accountActionHistoryValue : accountActionHistoryValues) {
			listOfAccountActionHistoryValues.add(accountActionHistoryValue.getText());
		}
		return listOfAccountActionHistoryValues;
	}

	public String getAccountActionHistoryAddedDate() {
		return accountActionHistoryAddedDate.getText();
	}

	public String getAccountActionHistoryFollowupDate() {
		return accountActionHistoryFollowupDate.getText();
	}

	public String getTextHandoffSuccessMessage() {
		waitForAngularRequestsToFinish();
		return successMessage.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible().getText().trim();
	}

	public void expandAccountActionHistory() {
		if (accountActionHistoryExpandButton.getAttribute("class").equals("fa toggle fa-chevron-right"))
			evaluateJavascript("arguments[0].click();", accountActionHistoryExpandButton);
		while (accountActionHistoryTimelineArrow.getAttribute("class").equals("flex-next")) {
			evaluateJavascript("arguments[0].click();", accountActionHistoryTimelineArrow);
		}
	}
}