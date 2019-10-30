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

	 String workFlowName;

	@FindBy(xpath = "//h3[@class='panel-title']")
	private WebElementFacade defaultHandOffPageTitle;

	@FindBy(id = "btnAddHandoff")
	private WebElementFacade addHandOffButton;

	@FindBy(xpath = "//label[@for='addWorkflowName']")
	private WebElementFacade addHandOffWorkFlowNameLabel;

	@FindBy(xpath = "//label[@for='addWorkflowDescription']")
	private WebElementFacade addHandOffWorkFlowDescriptionLabel;

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
	private WebElementFacade workFlowNameTextBox;

	@FindBy(id = "addWorkflowDescription")
	private WebElementFacade workFlowDescriptionTextBox;

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
	private WebElementFacade workFlowTypeActionsMessage;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions' and text()='No Disposition exists for the selected action type']")
	private WebElementFacade workFlowTypeDispositionMessage;

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

	@FindBy(id = "Disp0")
	private WebElementFacade savedDispositionName;

	@FindBy(id = "FUP0")
	private WebElementFacade savedDispositionFollowUpDays;

	@FindBy(id = "TLMT0")
	private WebElementFacade savedDispositionTimeLimit;

	@FindBy(id = "ASName0")
	private WebElementFacade savedDispositionStatus;

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
		return addHandOffWorkFlowNameLabel.getText().trim();
	}

	public String getTextAddHandOffWorkFlowDescriptionLabel() {
		return addHandOffWorkFlowDescriptionLabel.getText().trim();
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
		return workFlowNameTextBox.isVisible();
	}

	public boolean checkVisibilityOfWorkFlowDescriptionTextBox() {
		return workFlowDescriptionTextBox.isVisible();
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
		workFlowName = RandomStringUtils.randomAlphabetic(15);
		workFlowNameTextBox.type(workFlowName);
		return workFlowName;
	}

	public void enterWorkFlowDescription(String description) {
		workFlowDescriptionTextBox.type(description);
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
			if (listOfAddedHandOffNames.get(i).equals(workFlowName)) {
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
		return workFlowTypeActionsMessage.getText().trim();
	}

	public void clickAddNewActionButton() {
		addNewActionButton.click();
	}

	public String enterActionNameTextBox() {
		String actionName = RandomStringUtils.randomAlphabetic(13);
		actionNameTextBox.type(actionName);
		return actionName;
	}

	public String enterActionDescriptionTextBox(String actionDescription) {
		actionDescriptionTextBox.type(actionDescription);
		return actionDescription;
	}

	public void selectNextActionByDD(String value) {
		nextActionByDD.selectByVisibleText(value);
	}

	public void enterFollowUpDaysTextBox(String value) {
		followUpDaysTextBox.type(value);
	}

	public void enterRespondDeadlineTextBox(String value) {
		respondDeadlineTextBox.type(value);
	}

	public void selectActionStatusDD(String value) {
		actionStatusDD.selectByVisibleText(value);
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
		return workFlowTypeDispositionMessage.getText().trim();
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

	public void selectNextDispositionByDD() {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDD);
		List<String> listOfValuesOfNextDispositionDD = nextDispositionByDD.getSelectOptions();
		nextDispositionByDD.selectByIndex(CommonMethods.getRandom(listOfValuesOfNextDispositionDD.size() - 1) + 1);
	}

	public void enterDispositionFollowUpDaysTextBox(String value) {
		dispositionFollowUpDaysTextBox.type(value);
	}

	public void enterDispositionRespondDeadlineTextBox(String value) {
		dispositionRespondDeadlineTextBox.type(value);
	}

	public void selectDispositionStatusDD(String value) {
		dispositionStatusDD.selectByVisibleText(value);
	}

	public void enterPreDefinedNotes(String value) {
		preDefinedNotes.type(value);
	}

	public void clickAddNewDispositionSaveChangesButton() {
		addNewDispositionSaveChangesButton.click();
	}

	public String getTextSavedDispositionName() {
		return savedDispositionName.getText().trim();
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
}