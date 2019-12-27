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
	int i;
	@FindBy(xpath = "//h3[@class='panel-title']")
	private WebElementFacade defaultHandOffPageTitle;

	@FindBy(id = "btnAddHandoff")
	private WebElementFacade addHandOffButton;

	@FindBy(xpath = "//label[@for='addWorkflowName']")
	private WebElementFacade addHandOffWorkflowNameLabel;

	@FindBy(xpath = "//label[@for='addWorkflowDescription']")
	private WebElementFacade addHandOffWorkflowDescriptionLabel;

	@FindBy(xpath = "//label[text()='Worklist']")
	private WebElementFacade addHandOffWorkList;

	@FindBy(xpath = "//label[text()='AH Module Code']")
	private WebElementFacade addHandOffAHModuleCode;

	@FindBy(xpath = "//label[text()='Visible to Group']")
	private WebElementFacade addHandOffVisibleToGroupLabel;

	@FindBy(xpath = "//label[text()='Activate Handoff']")
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

	@FindBy(xpath = "//div[@class='fs-label']")
	private WebElementFacade visibleToGroupDD;

	@FindBy(xpath = "//*[@id='addHandoff']//span//strong[text()='Info']")
	private WebElementFacade addHandOffInfoMessage;

	@FindBy(xpath = "//label[text()='Activate Handoff']/following::label[@for='addHandoffActive']")
	private WebElementFacade activateHandOffCheckBox;

	@FindBy(xpath = "(//div//span[@class='fs-checkbox'])[position()=1]")
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

	@FindBy(xpath = "//button[text()=' Add Recipient']")
	private WebElementFacade addRecipientButton;

	@FindBy(id = "txtRecipientName")
	private WebElementFacade recipientNameTextBox;

	@FindBy(id = "txtRecipientDescription")
	private WebElementFacade recipientDescriptionTextBox;

	@FindBy(xpath = "//*[@id='addRecipient']//div//button[text()='Add Recipient']")
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

	@FindBy(xpath = "//button[text()=' Add New Action']")
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

	@FindBy(xpath = "//button[text()=' Add New Disposition']")
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

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable'])[last()]/li[last()]/div[position()=3]/span")
	private WebElementFacade savedDispositionName;

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable'])[last()]/li[last()]/div[position()=4]/span")

	private WebElementFacade savedDispositionFollowUpDays;

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable'])/li[last()]/div[position()=5]/span")
	private WebElementFacade savedDispositionTimeLimit;

	@FindBy(id = "ASName0")
	private WebElementFacade savedDispositionStatus;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']//li//div[3]//span[contains(@id,'Disp')]")
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

	@FindBy(xpath = "//*[@id='LocationGridData']/div[@class='Rows']/div[@class='col'][position()=1]")
	private WebElementFacade searchFacilityCode;

	@FindBy(xpath = "//*[@id='LocationGridData']/div[@class='Rows']/div[@class='col'][position()=10]/a")
	private WebElementFacade searchFacilityViewLink;

	@FindBy(id = "pnlFacilitySettings")
	private WebElementFacade searchFacilitySettingGrid;

	@FindBy(id = "dllFSSearch")
	private WebElementFacade facilitySettingSearchDrpdwn;

	@FindBy(id = "txtFSSearchValue")
	private WebElementFacade facilitySettingSearchTxtBox;

	@FindBy(id = "BtnFSSearch")
	private WebElementFacade facilitySettingSearchBtn;

	@FindBy(xpath = "//*[@id='FacilitySettingsGridData']/div[@class='Rows']/div[@class='col'][position()=1]")
	private WebElementFacade searchFacilitySettingText;

	@FindBy(xpath = "//*[@id='FacilitySettingsGridData']/div[@class='Rows']/div[@title='Update Facility Setting']")
	private WebElementFacade searchFSEditIcon;

	@FindBy(id = "FacilitySettingsModelpopup")
	private WebElementFacade facilitySettingDetailsPopup;

	@FindBy(id = "txtSettingValue")
	private WebElementFacade settingValueTxtArea;

	@FindBy(xpath = "//input[@type='button' and @value='Update Facility Setting']")
	private WebElementFacade updateSettingValueBtn;

	@FindBy(xpath = "//span[contains(text(),'AHtoDecision Admin')]/ancestor::li/div[position()=2]/span[@data-bind='text: workflowDescriptionName']")
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

	@FindBy(xpath = "//div[@class='popover-content']//span//span[position()=1]")
	private List<WebElementFacade> listOfEventCircleColumns;

	@FindBy(xpath = "//div[@class='popover-content']//span//span[position()=2]")
	private List<WebElementFacade> popupValues;

	@FindBy(xpath = "//div[@class='popover-content']//div[@class='timeline-details']//span[position()=1]/child::span[position()=2]")
	private WebElementFacade createdDateOnPopup;

	@FindBy(xpath = "//div[@class='popover-content']//div[@class='timeline-details']//span[position()=3]/child::span[position()=2]")
	private WebElementFacade followupDateOnPopup;

	@FindBy(xpath = "//*[@id='btnShowHistory']/span")
	private WebElementFacade accountActionHistoryNotesBtn;

	@FindBy(xpath = "//*[@id='notesHistory']/li[position()=1]/div/div/div/span/span[position()=1]")
	private List<WebElementFacade> accountActionHistoryColumns;

	@FindBy(xpath = "//*[@id='notesHistory']/li[position()=1]/div/div/div/span/span[position()=2]")
	private List<WebElementFacade> accountActionHistoryValues;

	@FindBy(xpath = "//*[@id='notesHistory']/li[position()=1]/div/div/div[position()=3]/span[position()=1]/span[position()=2]")
	private WebElementFacade accountActionHistoryAddedDate;

	@FindBy(xpath = "//*[@id='notesHistory']/li[position()=1]/div/div/div[position()=3]/span[position()=3]/span[position()=2]")
	private WebElementFacade accountActionHistoryFollowupDate;

	@FindBy(id = "patAAH")
	private WebElementFacade accountActionHistoryExpandButton;

	@FindBy(xpath = "//*[@id='carousel']/ul/li[position()=2]/a")
	private WebElementFacade accountActionHistoryTimelineArrow;

	/**
	 * Method checks the visibility of Add New Action Btn
	 */
	public boolean isAddNewActionBtnVisisble() {
		return addNewActionButton.isVisible();
	}

	/**
	 * @return the DefaultHandoff Page title
	 */
	public String getTextDefaultHandOffPageTitle() {
		return defaultHandOffPageTitle.getText().trim();
	}

	/**
	 * @return true if the add handoff button is visible else false
	 */
	public boolean verifyVisibilityOfAddHandOffButton() {
		return addHandOffButton.isVisible();
	}

	/**
	 * click on add handoff button
	 */
	public void clickAddHandOffButton() {
		addHandOffButton.click();
	}

	/**
	 * @return text value for Workflow Name label
	 */
	public String getTextAddHandOffWorkFlowNameLabel() {
		return addHandOffWorkflowNameLabel.getText().trim();
	}

	/**
	 * @return text value for Workflow description label
	 */
	public String getTextAddHandOffWorkFlowDescriptionLabel() {
		return addHandOffWorkflowDescriptionLabel.getText().trim();
	}

	/**
	 * @return text value for WorkList label
	 */
	public String getTextAddHandOffWorkList() {
		return addHandOffWorkList.getText().trim();
	}

	/**
	 * @return text value for AH Module
	 */
	public String getTextAddHandOffAHModuleCode() {
		return addHandOffAHModuleCode.getText().trim();
	}

	/**
	 * @return text value of Visible to Group Label
	 */
	public String getTextAddHandOffVisibleToGroupLabel() {
		return addHandOffVisibleToGroupLabel.getText().trim();
	}

	/**
	 * @return text value of Activate Handoff label
	 */
	public String getTextAddHandOffActivateHandOffLabel() {
		return addHandOffActivateHandOffLabel.getText().trim();
	}

	/**
	 * @return text value of Close button label
	 */
	public String getTextAddHandOffCloseButton() {
		return addHandOffCloseButton.getText().trim();
	}

	/**
	 * @return text value of Save Changes button
	 */
	public String getTextAddHandOffSaveChangesButton() {
		return addHandOffSaveChangesButton.getText().trim();
	}

	/**
	 * @return true if WorkflowName textbox visible else false
	 */
	public boolean checkVisibilityOfWorkFlowNameTextBox() {
		return workflowNameTextBox.isVisible();
	}

	/**
	 * @return true if Workflow description textbox is visible else false
	 */
	public boolean checkVisibilityOfWorkFlowDescriptionTextBox() {
		return workflowDescriptionTextBox.isVisible();
	}

	/**
	 * @return true if WorkList DD is visible else false
	 */
	public boolean checkVisibilityOfWorkListDD() {
		return workListDD.isVisible();
	}

	/**
	 * @return true if AH Module Code textbox is visible else false
	 */
	public boolean checkVisibilityOfAHModuleCodeTextBox() {
		return ahModuleCodeTextBox.isVisible();
	}

	/**
	 * @return true if Visible to Group is visible else false
	 */
	public boolean checkVisibilityOfVisibleToGroupDD() {
		return visibleToGroupDD.isVisible();
	}

	/**
	 * @return true if Activate Handoff is visible else false
	 */
	public boolean checkVisibilityOfActivateHandOffCheckBox() {
		return activateHandOffCheckBox.isVisible();
	}

	/**
	 * @return true if close button is visible else false
	 */
	public boolean checkVisibilityOfAddHandOffCloseButton() {
		return addHandOffCloseButton.isVisible();
	}

	/**
	 * @return true if Save Changes button is visible else false
	 */
	public boolean checkVisibilityOfAddHandOffSaveChangesButton() {
		return addHandOffSaveChangesButton.isVisible();
	}

	/**
	 * @return the success message thrown when the handoff is saved successfully
	 */
	public String getTextSuccessMessage() {
		return successMessage.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText().trim();
	}

	/**
	 * @return enter a random and unique value for Workflow Name
	 */
	public String enterWorkFlowName() {
		workflowName = RandomStringUtils.randomAlphabetic(15);
		workflowNameTextBox.type(workflowName);
		return workflowName;
	}

	/**
	 * @param description : Description value to be entered for WorkFlow Description
	 */
	public void enterWorkFlowDescription(String description) {
		workflowDescriptionTextBox.type(description);
	}

	/**
	 * Select a random value for WorkList
	 */
	public void selectWorkListDD() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", workListDD);
		List<String> listOfValuesOfWorkListDD = workListDD.getSelectOptions();
		int index = CommonMethods.getRandom(listOfValuesOfWorkListDD.size() - 1);
		workListDD.selectByIndex(index);
	}

	/**
	 * @param ahModuleCode :AH Module code to be entered in AH Code Module textbox
	 */
	public void enterAHModuleCode(String ahModuleCode) {
		ahModuleCodeTextBox.type(ahModuleCode);
	}

	public void selectVisibleToGroup() {
		visibleToGroupDD.click();
		withAction().moveToElement(visibleToGroupCheckBox).click().build().perform();
	}

	/**
	 * click on Save Changes button
	 */
	public void clickAddHandOffSaveChangesButton() {
		addHandOffSaveChangesButton.click();
	}

	/**
	 * this method selects the handoff that is newly added
	 */
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

	/**
	 * Click on the continue button to move to the next tab
	 */
	public void clickStepOneContinueBtn() {
		stepOneContinueBtn.click();
	}

	/**
	 * @return the text message when user moves to the Recipient tab and no
	 *         recipient is added
	 */
	public String getTextNoRecipientMsg() {
		return recipientDetailsMessage.getText().trim();
	}

	/**
	 * click on Add Recipient Button
	 */
	public void clickAddRecipientButton() {
		addRecipientButton.click();
	}

	/**
	 * @return enter random string for recipient Name
	 */
	public String enterRecipientNameTextBox() {
		String recipientName = RandomStringUtils.randomAlphabetic(14);
		recipientNameTextBox.type(recipientName);
		return recipientName;
	}

	/**
	 * @param recipientDescription : the value to be entered in Recipient
	 *                             description textbox
	 * @return the value entered in recipient description textbox
	 */
	public String enterRecipientDescriptionTextBox(String recipientDescription) {
		recipientDescriptionTextBox.type(recipientDescription);
		return recipientDescription;
	}

	/**
	 * click on Save recipient button
	 */
	public void clickSaveRecipientButton() {
		saveRecipientButton.click();
	}

	/**
	 * @return the name of the saved recipient
	 */
	public String getTextSavedRecipientName() {
		return savedRecipientName.getText().trim();
	}

	/**
	 * @return the saved description text
	 */
	public String getTextSavedRecipientDescription() {
		return savedRecipientDescription.getText().trim();
	}

	/**
	 * @return the value of bread crumb
	 */
	public String getTextBreadcrumb() {
		return breadcrumb.getText().trim();
	}

	/**
	 * Click on the Continue button to move to the next tab
	 */
	public void clickStepTwoContinueBtn() {
		stepTwoContinueBtn.click();
	}

	/**
	 * @return the text value for validation message when there is no action present
	 */
	public String getTextworkFlowTypeActionsMessage() {
		return workflowTypeActionsMessage.getText().trim();
	}

	/**
	 * click on Add New Action Button
	 */
	public void clickAddNewActionButton() {
		addNewActionButton.click();
	}

	/**
	 * @return enter the action name and return its value
	 */
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

	/**
	 * @param actionDescription enter the action description in the textbox
	 * @return the action description value
	 */
	public String enterActionDescriptionTextBox(String actionDescription) {
		actionDescriptionTextBox.type(actionDescription);
		return actionDescription;
	}

	/**
	 * @param nextActionValue: select the next action value from the DD
	 */
	public void selectNextActionByDD(String nextActionValue) {
		nextActionByDD.selectByVisibleText(nextActionValue);
	}

	/**
	 * @param followUpDaysValue: enter followup days in the textbox
	 */
	public void enterFollowUpDaysTextBox(String followUpDaysValue) {
		followUpDaysTextBox.type(followUpDaysValue);
	}

	/**
	 * @param respondDeadlineValue: enter respond Deadline in the textbox
	 */
	public void enterRespondDeadlineTextBox(String respondDeadlineValue) {
		respondDeadlineTextBox.type(respondDeadlineValue);
	}

	/**
	 * @param actionStatusValue: select action status value
	 */
	public void selectActionStatusDD(String actionStatusValue) {
		actionStatusDD.selectByVisibleText(actionStatusValue);
	}

	/**
	 * click on Add new Action Save button
	 */
	public void clickAddNewActionSaveChangesButton() {
		addNewActionSaveChangesButton.click();
	}

	/**
	 * @return return the saved action name displayed in the table after adding it
	 */
	public String getTextSavedActionName() {
		return savedActionName.getText().trim();
	}

	/**
	 * @return return the Saved followupdays displayed in the table after adding it
	 */
	public String getTextSavedFollowUpDays() {
		return savedFollowUpDays.getText().trim();
	}

	/**
	 * @return return the Saved timeline days displayed in the table after adding it
	 */
	public String getTextSavedTimelineDays() {
		return savedTimeLineDays.getText().trim();
	}

	/**
	 * click on the continue button to move to next tab
	 */
	public void clickStepThreeContinueBtn() {
		stepThreeContinueBtn.click();
	}

	public String getTextWorkFlowTypeDispositionMessage() {
		return workflowTypeDispositionMessage.getText().trim();
	}

	/**
	 * click on add new disposition button
	 */
	public void clickAddNewDispositionButton() {
		addNewDispositionButton.click();
	}

	/**
	 * @return enter random disposition code and return it
	 */
	public String enterDispositionCode() {
		String enterDispositionCode = RandomStringUtils.randomAlphabetic(13);
		dispositionCodeTextBox.type(enterDispositionCode);
		return enterDispositionCode;
	}

	/**
	 * @param dispositionDescription: the disposition Name to be entered
	 * @return the disposition Name
	 */
	public String enterDispositionNameTextBox(String dispositionDescription) {
		dispositionNameTextBox.type(dispositionDescription);
		return dispositionDescription;
	}

	/**
	 * select next Disposition by DD	
	 * @return the text value entered in the Disposition text box
	 */
	public String getEnterDispositionName() {
		return evaluateJavascript("return arguments[0].value;", dispositionNameTextBox).toString();
	}

	public void selectNextDispositionByDD() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDD);
		List<String> listOfValuesOfNextDispositionDD = nextDispositionByDD.getSelectOptions();
		nextDispositionByDD.selectByIndex(CommonMethods.getRandom(listOfValuesOfNextDispositionDD.size() - 1) + 1);
	}

	/**
	 * @param dispositionFollowUpDaysValue : enter disposition followup days
	 */
	public void enterDispositionFollowUpDaysTextBox(String dispositionFollowUpDaysValue) {
		dispositionFollowUpDaysTextBox.type(dispositionFollowUpDaysValue);
	}

	/**
	 * @param dispositionRespondDeadlineValue: enter disposition respond deadline value
	 */
	public void enterDispositionRespondDeadlineTextBox(String dispositionRespondDeadlineValue) {
		dispositionRespondDeadlineTextBox.type(dispositionRespondDeadlineValue);
	}

	/**
	 * @param dispositionStatusValue: enter disposition status from the dropdown
	 */
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

	/**
	 * @param preDefinedNotesValue: enter notes in the Notes textbox
	 */
	public void enterPreDefinedNotes(String preDefinedNotesValue) {
		preDefinedNotes.type(preDefinedNotesValue);
	}

	/**
	 * click on save changes button of the Add new Disposition
	 */
	public void clickAddNewDispositionSaveChangesButton() {
		addNewDispositionSaveChangesButton.click();
	}

	/**
	 * 
	 * @param dispositionName
	 *            is the name of Disposition to be passed from feature file
	 * @return the Disposition Name text of the newy created Disposition
	 */
	public String getTextSavedDispositionName(String dispositionName) {
		int size = dispositionNameList.size();
		int flag = 0;
		for (i = 0; i < size; i++) {
			if (dispositionNameList.get(i).getText().equals(dispositionName)) {
				withAction().moveToElement(dispositionNameList.get(i)).build().perform();
				flag = 1;
			}
			if (flag == 1) {
				break;
			}
		}
		return dispositionNameList.get(i).getText().trim();
	}

	/**
	 * @return the value of the Saved Disposition Followup days in the table below
	 */
	public String getTextSavedDispositionFollowUpDays() {
		return savedDispositionFollowUpDays.getText().trim();
	}

	/**
	 * @return the value of the Saved Disposition TimeLimit in the table below
	 */
	public String getTextSavedDispositionTimeLimit() {
		return savedDispositionTimeLimit.getText().trim();
	}

	/**
	 * @return the value of the Saved Disposition Status in the table below
	 */
	public String getTextSavedDispositionStatus() {
		return savedDispositionStatus.getText().trim();
	}

	public void clickVisibleToGrpAHtoDecisionChkBox() {
		visibleToGrpAHtoDecisionChkBox.click();
	}

	/**
	 * @return get the Title text for Facility Setting Configuration
	 */
	public String getFacilitySettingConfigTitle() {
		return facilitySettingConfigTitle.getText();
	}

	/**
	 * @param searchValue: Select value for Search
	 */
	public void selectTextSearchDrpdwn(String searchValue) {
		searchDrpdwn.selectByVisibleText(searchValue);
	}

	/**
	 * @param value : Enter value for Search
	 */
	public void enterSearchTxtBox(String value) {
		searchTxtBox.type(value);
	}

	/**
	 * click Search button
	 */
	public void clickSearchBtn() {
		searchBtn.click();
	}

	/**
	 * @return Search Facility Code
	 */
	public String getSearchFacilityCode() {
		return searchFacilityCode.getText();
	}

	/**
	 * click on Search Facility View link
	 */
	public void clickSearchFacilityViewLink() {
		searchFacilityViewLink.click();
	}

	/**
	 * @return true if Search Facility Setting Grid is visible else false
	 */
	public boolean isSearchFacilitySettingGridVisible() {
		return searchFacilitySettingGrid.isVisible();
	}

	/**
	 * @param searchValue: For FacilitySettingSearch dropdown
	 */
	public void selectTextFSSearchDrpdwn(String searchValue) {
		facilitySettingSearchDrpdwn.selectByVisibleText(searchValue);
	}

	/**
	 * @param value : For FacilitySettingSearch dropdown
	 */
	public void enterFSSearchTxtBox(String value) {
		facilitySettingSearchTxtBox.type(value);
	}

	/**
	 * click facility Setting Search Button
	 */
	public void clickFSSearchBtn() {
		facilitySettingSearchBtn.click();
	}

	/**
	 * click Facility Setting Edit button
	 */
	public void clickSearchFSEditIcon() {
		searchFSEditIcon.click();
	}

	/**
	 * @return text value for search Facility
	 */
	public String getSearchFacilitySettingText() {
		return searchFacilitySettingText.getText();
	}

	/**
	 * @return true if Facility Setting Details popup is visible else false
	 */
	public boolean isFSDetailsPopupVisible() {
		return facilitySettingDetailsPopup.isVisible();
	}

	/**
	 * @return Setting Value Text data
	 */
	public String getSettingValueTxtArea() {
		return evaluateJavascript("return document.querySelector('#txtSettingValue').value").toString();
	}

	/**
	 * @param settingValue: append value at the end of the data values in the textbox
	 */
	public void updateSettingValueTxtArea(String settingValue) {
		settingValueTxtArea.type(getSettingValueTxtArea() + settingValue);
	}

	/**
	 * click Update Setting Button
	 */
	public void clickUpdateSettingValueBtn() {
		updateSettingValueBtn.click();
	}

	/**
	 * @return second Last value in AHtoDecision Admin Workflow
	 */
	public String getSecondLastAHtoDecisionAdminWorkflow() {
		return listOfAHtoDecisionAdminWorkflows.get(listOfAHtoDecisionAdminWorkflows.size() - 2).getText();
	}

	/**
	 * @param dropdown select the handoff type from the dropdown
	 * @return
	 */
	public String selectHandoffType(String dropdown) {
		waitForAngularRequestsToFinish();
		return handoffTypeDrpdwn.selectByVisibleText(dropdown).getSelectedVisibleTextValue();
	}

	/**
	 * @return select any value from create dropdown and return it
	 */
	public String selectAnyOptionFromCreateDrpdwn() {
		waitForAngularRequestsToFinish();
		int index = CommonMethods.getRandom(createDrpdwnOption.size() - 1) + 1;
		return createDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}

	/**
	 * @return select any value from Why dropdown and return it
	 */
	public String selectAnyOptionFromWhyDrpdwn() {
		waitForAngularRequestsToFinish();
		int index = CommonMethods.getRandom(whyDrpdwnOption.size() - 1) + 1;
		return whyDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}

	/**
	 * @return select any value from disposition dropdown and return it
	 */
	public String selectAnyOptionFromDispositionDrpdwn() {
		if (dispositionDrpdwn.isVisible()) {
			waitForAngularRequestsToFinish();
			int index = CommonMethods.getRandom(dispositionDrpdwnOptions.size() - 1) + 1;
			return dispositionDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
		}
		return "";
	}

	/**
	 * @param note Enter Note in the Note textbox
	 */
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

	/**
<<<<<<< HEAD
	 * hover on the added handoff bubble
=======
	 *This method perform move to recently Added Bubble element
>>>>>>> fbe12705709a548f8b1d551822cd968b28231848
	 */
	public void hoverOnAddedBubble() {
		withAction().moveToElement(addedBubble).build().perform();
	}

	/**
	 * click on handoff Save button
	 */
	public void clickHandoffSaveBtn() {
		handoffSaveBtn.click();
	}

	/**
<<<<<<< HEAD
	 * @return the list of the column headers that appear in the box after hovering over the bubble
=======
	 *This method stores Event Circle Columns Value in List
	 *@return listOfEventCircleCols
>>>>>>> fbe12705709a548f8b1d551822cd968b28231848
	 */
	public List<String> getListOfEventCircleColumns() {
		List<String> listOfEventCircleCols = new ArrayList<>();
		for (WebElementFacade eventCircleCol : listOfEventCircleColumns) {
			listOfEventCircleCols.add(eventCircleCol.getText().trim());
		}
		return listOfEventCircleCols;
	}

	/**
	 * @return the list of the values that appear in the box after hovering over the bubble
	 */
	public List<String> getListOfPopupValues() {
		List<String> popupValList = new ArrayList<>();
		for (WebElementFacade popupValue : popupValues) {
			popupValList.add(popupValue.getText());
		}
		return popupValList;
	}

	/**
	 * @return Created Date for Handoff
	 */
	public String getCreatedDate() {
		return createdDateOnPopup.getText();
	}

	/**
	 * @return Followup Date for Handoff
	 */
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

	/**
	 * @return the Account Action History Values
	 */
	public List<String> getAccountActionHistoryValues() {
		List<String> listOfAccountActionHistoryValues = new ArrayList<>();
		for (WebElementFacade accountActionHistoryValue : accountActionHistoryValues) {
			listOfAccountActionHistoryValues.add(accountActionHistoryValue.getText());
		}
		return listOfAccountActionHistoryValues;
	}

	/**
	 * @return Account Action History Added Date
	 */
	public String getAccountActionHistoryAddedDate() {
		return accountActionHistoryAddedDate.getText();
	}

	/**
	 * @return Account Action history followup date
	 */
	public String getAccountActionHistoryFollowupDate() {
		return accountActionHistoryFollowupDate.getText();
	}

	public String getTextHandoffSuccessMessage() {
		waitForAngularRequestsToFinish();
		return successMessage.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible().getText().trim();
	}

	/**
	 *  expand the account action history section in case it is collapsed
	 */
	public void expandAccountActionHistory() {
		if (accountActionHistoryExpandButton.getAttribute("class").equals("fa toggle fa-chevron-right"))
			evaluateJavascript("arguments[0].click();", accountActionHistoryExpandButton);
		while (accountActionHistoryTimelineArrow.getAttribute("class").equals("flex-next")) {
			evaluateJavascript("arguments[0].click();", accountActionHistoryTimelineArrow);
		}
	}
}
