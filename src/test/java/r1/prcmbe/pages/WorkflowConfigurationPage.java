package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class WorkflowConfigurationPage extends PageObject {

	int index;
	String enterWorkflowName, workflowDescriptionText, getSelectedValueFromNextDropdown, getDispositionStatus;

	@FindBy(xpath = "//h3[contains(text(),'Workflow Configuration')]")
	private WebElementFacade workflowTitle;

	@FindBy(id = "HandoffLink")
	private WebElementFacade handoffTab;

	@FindBy(id = "ActionTypeLink")
	private WebElementFacade actionTypeTab;

	@FindBy(xpath = "//div[@class = 'navbar']//li/a")
	private List<WebElementFacade> workflowTabs;

	@FindBy(xpath = "//div[@class = 'summary']//li[1]")
	private WebElementFacade workflowSummaryLabel;

	@FindBy(xpath = "(//div[@id = 'dvHandOff']//li[1]//span)[2]")
	private WebElementFacade defaultHandoffName;

	@FindBy(id = "btnAddHandoff")
	private WebElementFacade addHandoffButton;

	@FindBy(xpath = "//div[@id = 'step1']//button[contains(@class,'btn btnSuccess')]")
	private WebElementFacade stepFirstContinueBtn;

	@FindBy(xpath = "//ul[@class = 'sop-header workflowConfiguration']//li")
	private List<WebElementFacade> workflowGridHeaders;

	@FindBy(xpath = "(//a[contains(@href,'editHandoff')])[1]")
	private WebElementFacade handoffEditButton;

	@FindBy(xpath = "//label[@for = 'workflowChoiceID-0']")
	private WebElementFacade defaultRadioBtn;

	@FindBy(xpath = "//a[@data-target='#addHandoff']")
	private List<WebElementFacade> listOfEditIcons;

	@FindBy(xpath = "//label[@for='addWorkflowName']")
	private WebElementFacade workflowName;

	@FindBy(xpath = "//*[@id='addHandoff']/div/div/div[2]/div/label")
	private List<WebElementFacade> listOfLabelsOnEditPopup;

	@FindBy(xpath = "//*[@id='addHandoff']/div/div/div[3]/button")
	private List<WebElementFacade> listOfControlsOnEditPopup;

	@FindBy(id = "msg_success")
	private WebElementFacade successMsg;

	@FindBy(xpath = "//h4[text()='Edit HandOff']")
	private WebElementFacade editHandoffText;

	@FindBy(xpath = "//*[@id='addWorkflowDescription']")
	private WebElementFacade workflowDescriptionTextbox;

	@FindBy(xpath = "//*[@id='dvHandOff']/ul/li/div[3]/span")
	private List<WebElementFacade> listOfDescriptions;

	@FindBy(id = "addHandoffLabel")
	private WebElementFacade addHandOffLabel;

	@FindBy(xpath = "//div//span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> listOfAddedHandOffs;

	@FindBy(xpath = "//*[@id='dvHandOff']/ul/li/div[2]/span")
	private List<WebElementFacade> handoffTypeList;

	@FindBy(xpath = "//*[@id='dvHandOff']/ul/li/div[1]/div/div/label")
	private List<WebElementFacade> handoffTypeRadioBtnList;

	@FindBy(xpath = "//div[@class='container']//div[@class='row']/div[2]/button")
	private WebElementFacade continueBtnOnHandoff;

	@FindBy(xpath = "//*[@id='step2']//h2[text()='Choose Recipient']")
	private WebElementFacade recipientPage;

	@FindBy(id = "RecipientLink")
	private WebElementFacade recipientTab;

	@FindBy(id = "sName0")
	private WebElementFacade defaultRecipientName;

	@FindBy(xpath = "//button[contains(@data-target,'#addRecipient')]")
	private WebElementFacade addRecipientBtn;

	@FindBy(xpath = "//*[@id='step2']/div[1]/div/div[2]/button")
	private WebElementFacade recipientContinueBtn;

	@FindBy(xpath = "//*[@id='main']/div/div/div/div[2]/div[2]/ol/li[1]")
	private WebElementFacade handOffOnBreadcrumb;

	@FindBy(xpath = "//*[@id='main']/div/div/div/div[2]/div[2]/ol/li[2]")
	private WebElementFacade recipientOnBreadcrumb;

	@FindBy(xpath = "//*[@id='step2']/div/h2")
	private WebElementFacade chooseRecipientLabel;

	@FindBy(xpath = "//ul[@class='sop-header']/li")
	private List<WebElementFacade> listOfSopHeader;

	@FindBy(xpath = "(//a[contains(@data-target,'#editRecipient')])[1]")
	private WebElementFacade editIconOnRecipientTab;

	@FindBy(xpath = " (//*[@id='dvRecipientDetails']//ul)[2]/preceding-sibling::div//a[2]/i")
	private WebElementFacade detailsBtnOnRecipientTab;

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']/li/span[1]")
	private List<WebElementFacade> listOfDetailColumnsRecipientTab;

	@FindBy(id = "txtDispositionsNotes")
	private WebElementFacade dispositionNotes;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtn;

	@FindBy(id = "addNewDispositionLabel")
	private WebElementFacade dispositionPopupHeader;

	@FindBy(xpath = "//*[@id='addNewDisposition']//i[@class='fa fa-close']")
	private WebElementFacade dispositionPopupClose;

	@FindBy(xpath = "//div[@id='step3']//div[@class='row']/div[2]/button")
	private WebElementFacade continueBtnOnActionTypeTab;

	@FindBy(xpath = "(//div[@class='container']//div[@class='row']/div[2]/button)[2]")
	private WebElementFacade continueBtnOnRecipientTab;

	@FindBy(xpath = "//div[not(contains(@class,'hidden')) and @id='addNewDisposition']/div/div/div[2]/div/label")
	private List<WebElementFacade> labelsOnDispositionPopup;

	@FindBy(id = "txtdispositionCode")
	private WebElementFacade dispositionCodeTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//div[@class='alert alert-info']/span")
	private WebElementFacade errorMsgOnEmptyFields;

	@FindBy(id = "txtdispositionDescription")
	private WebElementFacade dispositionNameTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowDisposition_ddldispositionTarget']")
	private WebElementFacade nextDispositionByDropdown;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']/li[last()]/div/div/a[3]/i")
	private WebElementFacade newDetailsLinkOnDisposition;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade createdDateField;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade createdByField;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[@class='close']")
	private WebElementFacade closeBtnOnDispositionPopup;

	@FindBy(xpath = "//*[@id='addNewDisposition']//div[@class='alert alert-danger']/span")
	private WebElementFacade errorMsgOnduplicateDispositionCode;

	@FindBy(id = "txtdispositionFUD")
	private WebElementFacade followUpDaysTxtBoxOnDispositionPopup;

	@FindBy(id = "txtdispositionTimeLimit")
	private WebElementFacade respondDeadLineTxtBoxOnDispositionPopup;

	@FindBy(xpath = "//label[text()='Disposition Status']/..//select")
	private WebElementFacade dispositionStatusDrpDwn;

	@FindBy(xpath = "(//*[@id='WorkflowTypeDispositionSorttable']//a[@data-target='#addNewDisposition'])[last()]")
	private WebElementFacade editLinkOnDispositionGrid;

	@FindBy(id = "ActionTypeLink")
	private WebElementFacade actionTypeLink;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//div[@class='config-tools appeals']/div/label")
	private List<WebElementFacade> listOfActionTypeRadioBtns;

	@FindBy(xpath = "//button[contains(text(),' Add Recipient')]")
	private WebElementFacade addRecipientButton;

	@FindBy(xpath = "//*[@id='addRecipient']//div[@class ='form-group']/label")
	private List<WebElementFacade> listOfAddRecipientLabels;

	@FindBy(xpath = "//*[@id = 'addRecipient']//div[@class = 'modal-footer']/button")
	private List<WebElementFacade> listOfAddRecipientButtons;

	@FindBy(xpath = "//*[@id='addRecipient']//button/span[1]/i")
	private WebElementFacade closeButtonOnAddRecipient;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//li//div[2]/span")
	private List<WebElementFacade> listOfRecipientNames;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//li//div[3]/span")
	private List<WebElementFacade> listOfRecipientDesc;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']/ul//div[2]/span")
	private List<WebElementFacade> recipientsList;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']/ul/li/div[1]/div/div/label")
	private List<WebElementFacade> recipientsRadioBtnList;

	@FindBy(xpath = "//a[@class = 'toggle-info']//i")
	private List<WebElementFacade> listOfDetailsOnRecipientTab;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade createdByRecipient;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade createdDateRecipient;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[@class='radio']//label")
	private List<WebElementFacade> listOfRecipientsRadioBtn;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[contains(@id,'sName')]")
	private List<WebElementFacade> listOfRecipientsName;

	@FindBy(xpath = "//div[@id='addNewAction']//div[not(contains(@class,'hidden'))]/label[contains(@class,'control-label')]")
	private List<WebElementFacade> actionPopupControls;

	@FindBy(xpath = "//*[@id='addNewAction']//button[text()='Close']")
	private WebElementFacade closeBtnOnActionPopup;

	@FindBy(xpath = "//*[@id='addNewAction']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtnOnActionPopup;

	@FindBy(xpath = "//*[@id='addNewAction']//*[@id='sopActionRequired']/..")
	private WebElementFacade requiredCheckBoxActionPopup;

	@FindBy(id = "addEditNewActionLabel")
	private WebElementFacade addActionPopup;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//i[@class='fa fa-chevron-right fa-1-5x']")
	private List<WebElementFacade> listOfDetailsLinkOnActionTab;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//span[contains(@id,'NA')]")
	private List<WebElementFacade> listOfActionNames;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade actionCreatedDate;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade actionCreatedBy;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated Date']/following-sibling::span")
	private WebElementFacade actionUpdatedDate;

	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated By']/following-sibling::span")
	private WebElementFacade actionUpdatedBy;

	@FindBy(xpath = "//div[@id='addNewAction']//div[@class='alert alert-info']/span")
	private WebElementFacade errorMsgForEmptyFieldsOnActionPopup;

	@FindBy(id = "DispositionTypeLink")
	private WebElementFacade dispositionTab;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']//span[@id='Disp0']")
	private WebElementFacade firstDispositionName;

	@FindBy(xpath = "//*[@class='breadcrumb defect-summary']//li[@class='step3']")
	private WebElementFacade dispositionBreadCrumb;

	@FindBy(xpath = "//*[@id='step4']//*[contains(@class,'workflow')]//*[@class='col-lg-3']//button[@type='button']")
	private List<WebElementFacade> dispositionChooseGridButtons;

	@FindBy(xpath = "//*[@class='btn btnSuccess btn-block next-step last-step step4 saveconf btnPrimary']")
	private WebElementFacade dispositionSaveConfigBtn;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//div[3]//span[contains(@id,'Disp')]")
	private List<WebElementFacade> dispositionNameList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-edit fa-1-5x']")
	private List<WebElementFacade> dispositionEditLinkList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-chevron-right fa-1-5x']")
	private List<WebElementFacade> dispositionDetailsLinkList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-arrows fa-1-5x']")
	private List<WebElementFacade> dispositionReorderLinksList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-arrows fa-1-5x']")
	private List<WebElementFacade> dispositionRadioBtnList;

	@FindBy(xpath = "(//*[@id='dvWorkflowDispositions']//li//i[contains(@class,'fa fa-chevron-right fa-1-5x')])[1]")
	private WebElementFacade firstDispositionDetailsLink;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//li/span[1]")
	private List<WebElementFacade> dispositionDetailsColumnList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-1-5x fa-chevron-down']")
	private WebElementFacade expandedDispositionDetailsLink;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']")
	private WebElementFacade dispositionDetailsSection;

	@FindBy(xpath = "//*[@class='sop-header dispositions']/li")
	private List<WebElementFacade> listOfDispositionHeader;

	@FindBy(xpath = "//*[@id='dvWorkflowTypeActions']/ul/li/div[1]/div/div/label")
	private List<WebElementFacade> actionsRadioBtnList;

	@FindBy(xpath = "//*[@id='editRecipient']//div[@class ='form-group']/label")
	private List<WebElementFacade> listOfEditRecipientLabels;

	@FindBy(xpath = "//button[contains(text(),'Save Recipient')]")
	private WebElementFacade saveRecipientButton;

	@FindBy(id = "txteditRecipientName")
	private WebElementFacade recipientNameTextbox;

	@FindBy(id = "txteditRecipientDescription")
	private WebElementFacade recipientDescriptionTextbox;

	@FindBy(xpath = "(//*[@id = 'dvRecipientDetails']//div[3]//span)[1]")
	private WebElementFacade firstRecipientDesc;

	@FindBy(xpath = "(//a[@class = 'toggle-info']//i)[1]")
	private WebElementFacade firstDetailsLinkOnRecipient;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated By']/following-sibling::span")
	private WebElementFacade recipientUpdatedByField;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated Date']/following-sibling::span")
	private WebElementFacade recipientUpdatedDateField;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']/li/div/div/a[3]/i")
	private List<WebElementFacade> listOfDetailsLinkOnDispositionTab;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']/li[last()]/div[5]/span")
	private WebElementFacade mappedTimeLimitValueOnDispositionTypeGrid;

	@FindBy(xpath = "//*[@id='step3']//h2[text()='Choose Action Type']")
	private WebElementFacade actionTab;

	@FindBy(xpath = "//ul[@class='sop-header wf-action-type']/li")
	private List<WebElementFacade> headersOnActionType;

	@FindBy(xpath = "(//*[@id='WorkflowTypeActionsSorttable']//ul)[1]/preceding-sibling::div//a[3]/i")
	private WebElementFacade detailsLinkBtnOnActionTypeTab;

	@FindBy(xpath = "(//*[@id='WorkflowTypeActionsSorttable']//a[@data-target='#addNewAction'])[1]")
	private WebElementFacade firstEditLinkOnActionTypeTab;

	@FindBy(xpath = "//a[@class='reorder']")
	private WebElementFacade reorderLinkOnActionType;

	@FindBy(xpath = "//div[@class='alert alert-danger']/span")
	private WebElementFacade duplicateActionNameErrMsg;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']/li/div[3]/span[@id='NA0']")
	private WebElementFacade actionTypeName;

	@FindBy(xpath = "//ol[@class='breadcrumb defect-summary']")
	private WebElementFacade actionTypeBreadcrumb;

	@FindBy(id = "ddlHandOffType")
	private WebElementFacade handOffTypeDrpDwn;

	@FindBy(id = "ddlHandoffDirection")
	private WebElementFacade createDrpDwn;

	@FindBy(id = "ddlAction")
	private WebElementFacade whyDrpDwn;

	@FindBy(id = "ddlDisposition")
	private WebElementFacade dispositionDrpDwn;

	@FindBy(xpath = "//div[@id='dvHandOff']//li//div[2]/span")
	private List<WebElementFacade> listOfHandoffNameOnHandoffTab;

	@FindBy(xpath = "//div[@id='dvHandOff']//li//div[1]/input[@name='workflowName']/..//label")
	private List<WebElementFacade> listOfRadioLabelOnHandoffTab;

	@FindBy(xpath = "//div[@id='dvWorkflowTypeActions']//li//div[4]/span")
	private WebElementFacade followDaysOnActionTypeTab;

	@FindBy(xpath = "//div[@id='dvWorkflowTypeActions']//li//div[5]/span")
	private WebElementFacade timeLimitOnActionTypeTab;

	@FindBy(xpath = "//div[@id='dvWorkflowTypeActions']//li//div[3]/span")
	private WebElementFacade actionNameOnActionTypeTab;

	@FindBy(xpath = "//ul[@class='sop-types workflowConfiguration']//span[text()='AR Supervisor']//ancestor::li//child::label[contains(@for,'work')]")
	private WebElementFacade arSupervisorRadioBtn;

	@FindBy(xpath = "//*[@id='dvWorkflowTypeActions']//span[contains(@id,'NA')]")
	private List<WebElementFacade> actionTypeList;

	@FindBy(xpath = "//*[@id='dvWorkflowTypeActions']//div[@class='radio']//label")
	private List<WebElementFacade> actionTypeRadioBtnList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//div[3]/child::*[contains(@id,'Disp')]")
	private List<WebElementFacade> dispositionTypeList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//i[@class='fa fa-edit fa-1-5x']")
	private List<WebElementFacade> dispositionTypeEditBtnList;

	@FindBy(id = "addNewDispositionLabel")
	private WebElementFacade editDispositionPopupHeader;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtnDispositionPopUp;

	@FindBy(id = "msg_success")
	private WebElementFacade successMessage;
	
	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//ul/preceding-sibling::div//a[3]/i")
	private List<WebElementFacade> listOfDetailsLinkActionTypeTab;
	
	@FindBy(xpath = "//*[@id='WorkflowTypeActionsSorttable']//li/div[5]/span")
	private List<WebElementFacade> listOfTimeLimitInActionTypeGrid;
	
	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//span[text()='Edit']")
	private List<WebElementFacade> listOfEditLinkOnActionTypeGrid;
	
	@FindBy(xpath = "//*[@id='addNewAction']//*[@class='form-group']/label")
	private List<WebElementFacade> listOfEditActionTypePopUpFields;
	
	@FindBy(xpath = "//*[@id='addNewAction']//*[@class='modal-footer']/button[1]")
	private WebElementFacade closeBtnOnPopUp;
	
	@FindBy(xpath = "//*[@id='addNewAction']//*[@class='modal-footer']/button[2]")
	private WebElementFacade saveChangesBtnOnPopUp;
	
	@FindBy(id = "txtActionName")
	private WebElementFacade actionNameOnEditActionPopUp;
	
	@FindBy(id = "txtActionDescription")
	private WebElementFacade actionDescOnEditActionPopUp;

	@FindBy(id = "dnn_ctr1590_TaskPanel_taskBase_WorkflowActionType_ddlNextActionBy")
	private WebElementFacade nextActionByOnEditActionPopUp;

	@FindBy(id = "txtActionTimeLimit")
	private WebElementFacade respondDeadlineOnEditActionPopUp;

	@FindBy(id = "txtActionFUD")
	private WebElementFacade followUpDaysOnEditActionPopUp;

	@FindBy(id = "dnn_ctr1590_TaskPanel_taskBase_WorkflowActionType_ddlActionStatus")
	private WebElementFacade actionStatusOnEditActionPopUp;
	
	@FindBy(id = "addEditNewActionLabel")
	private WebElementFacade editActionPopUpHeader;
	
	public String getActionTextBreadcrumb() {
		return actionTypeBreadcrumb.getText().trim();
	}

	public String getSelectedActionTypeName() {
		return actionTypeName.getText().trim();
	}

	public String getErrMsgOnDuplicateActionName() {
		return duplicateActionNameErrMsg.getText().trim();
	}

	public void clickFirstEditLinkOnActionTypeTab() {
		firstEditLinkOnActionTypeTab.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().click();
	}

	public boolean isActionTabVisible() {
		return actionTab.isVisible();
	}

	public boolean isContinueBtnOnActionTypeVisible() {
		return continueBtnOnActionTypeTab.isVisible();
	}

	public boolean isDetailLinkOnActionTypeVisible() {
		return detailsLinkBtnOnActionTypeTab.isVisible();
	}

	public boolean isRadioBtnOnActionTypeVisible() {
		return listOfActionTypeRadioBtns.get(0).isVisible();
	}

	public boolean isEditLinkOnActionTypeVisible() {
		return firstEditLinkOnActionTypeTab.isVisible();
	}

	public boolean isReorderOnActionTypeVisible() {
		return reorderLinkOnActionType.isVisible();
	}

	public void clickOnDetailsBtnOnActionTypeTab() {
		detailsLinkBtnOnActionTypeTab.click();
	}

	public void clickHandoffTab() {
		handoffTab.click();
	}

	public List<String> getActionTypeHeaders() {
		List<String> listOfLabels = new ArrayList<String>();
		for (WebElementFacade headers : headersOnActionType) {
			listOfLabels.add(headers.getText().trim());
		}
		return listOfLabels;
	}

	public void clickOnDetailsLinkOnDispositionTab() {
		withAction().moveToElement(newDetailsLinkOnDisposition).build().perform();
		evaluateJavascript("arguments[0].click();", newDetailsLinkOnDisposition);
	}

	public String getDispositionErrorMsgOnDuplicateCode() {
		return errorMsgOnduplicateDispositionCode.getText().trim();
	}

	public void clickOnAnyActionTypeRadioBtn() {
		evaluateJavascript("arguments[0].click();", listOfActionTypeRadioBtns.get(1));
	}

	public void clickOnActionType() {
		actionTypeLink.click();
	}

	public void clickOnRandomHandoffType() {
		int randomHandoff = CommonMethods.getRandom(handoffTypeRadioBtnList.size() - 1);
		evaluateJavascript("arguments[0].click();", handoffTypeRadioBtnList.get(randomHandoff));
	}

	public String getDispositionCodeFromTextBox() {
		return evaluateJavascript("return arguments[0].value;", dispositionCodeTextBox).toString();
	}

	public void clickOnCloseBtnOnDispositionPopup() {
		closeBtnOnDispositionPopup.click();
	}

	public String getCreatedByFieldValue() {
		return createdByField.getText();
	}

	public String getCreatedDateFieldValue() {
		return createdDateField.getText();
	}

	public void clickOnNewlyDispositionDetailsLink() {
		withAction().moveToElement(newDetailsLinkOnDisposition).click().build().perform();
	}

	public boolean isAddNewDispositionPopupVisible() {
		return dispositionPopupHeader.isVisible();
	}

	public void selectNextDispositionFromDropdown(String nextDrpDownValue) {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDropdown);
		nextDispositionByDropdown.selectByVisibleText(nextDrpDownValue);
	}

	public boolean isSelectedValueInNextDispositionByVisible(String expectedDrpDownValue) {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDropdown);
		getSelectedValueFromNextDropdown = nextDispositionByDropdown.getSelectedVisibleTextValue();
		if (getSelectedValueFromNextDropdown.trim().equals(expectedDrpDownValue)) {
			return true;
		}
		return false;
	}

	public void enterDispositionName() {
		dispositionNameTextBox.type(RandomStringUtils.randomAlphabetic(6));
	}

	public String getErrorMsgOnDispositionPopup() {
		withAction().moveToElement(errorMsgOnEmptyFields).build().perform();
		String expectedMEssage = errorMsgOnEmptyFields.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible()
				.getText().trim();
		return expectedMEssage;

	}

	public void enterTextInDispositionCodeTextBox() {
		dispositionCodeTextBox.type(RandomStringUtils.randomAlphanumeric(6));
	}

	public void enterPreviousDispositionCode(String copiedCode) {
		withAction().moveToElement(dispositionCodeTextBox).build().perform();
		dispositionCodeTextBox.clear();
		waitForAngularRequestsToFinish();
		dispositionCodeTextBox.type(copiedCode);
	}

	public boolean isSaveBtnOnDispositionPopupVisible() {
		withAction().moveToElement(saveChangesBtn).build().perform();
		return saveChangesBtn.isVisible();
	}

	public List<String> getListOfLabelsOnDispositionPopup() {
		List<String> listOfLabels = new ArrayList<String>();
		for (WebElementFacade labels : labelsOnDispositionPopup) {
			listOfLabels.add(labels.getText().trim());
		}
		return listOfLabels;
	}

	public String isFirstRadioBtnActionTabSelected() {
		return evaluateJavascript("return document.querySelector('#wfActionBSOCP-0').checked").toString();
	}

	public void clickOnContinueBtnOnRecipientTab() {
		continueBtnOnRecipientTab.click();
	}

	public String enterAndGetDispositionNotes() {
		dispositionNotes.type(RandomStringUtils.randomAlphabetic(6));
		return dispositionNotes.getText();
	}

	public void clickSaveChangesBtn() {
		saveChangesBtn.click();
	}

	public void clickDispositionPopupClose() {
		dispositionPopupClose.click();
	}

	public void clickContinueBtnOnActionTypeTab() {
		continueBtnOnActionTypeTab.click();
	}

	public List<String> getSopHeaderList() {
		List<String> headerList = new ArrayList<>();
		for (WebElementFacade sopHeaderEle : listOfSopHeader) {
			headerList.add(sopHeaderEle.getText().trim());
		}
		return headerList;
	}

	public String isFirstRecipientBtnSelected() {
		return evaluateJavascript("return document.querySelector('#workflowSubTypeID-0').checked").toString();
	}

	public boolean isDetailsIconOnRecipientVisible() {
		return detailsBtnOnRecipientTab.isVisible();
	}

	public List<String> getDetailColumnHeadersRecipientTab() {
		List<String> columnList = new ArrayList<>();
		for (WebElementFacade columnEle : listOfDetailColumnsRecipientTab) {
			columnList.add(columnEle.getText().trim());
		}
		return columnList;
	}

	public boolean isEditIconOnRecipientTabVisible() {
		return editIconOnRecipientTab.isVisible();
	}

	public void clickOnDetailsOnRecipientTab() {
		detailsBtnOnRecipientTab.click();
	}

	public boolean isChooseRecipientVisible() {
		return chooseRecipientLabel.isVisible();
	}

	public boolean isRecipientAppendInBreadcrumbInRecipientTab(String handoffName, String recipientName) {
		return handOffOnBreadcrumb.getText().contains(handoffName)
				&& recipientOnBreadcrumb.getText().contains(recipientName);
	}

	public String getDefaultSelectedRecipientName() {
		return defaultRecipientName.getText().trim();
	}

	public boolean isContinueAndAddRecipientOnRecipientTabVisible() {
		return recipientContinueBtn.isVisible() && addRecipientBtn.isVisible();
	}

	public String getRecipientTabColour() {
		withAction().moveToElement(recipientTab).build().perform();
		return recipientTab.getCssValue("background-color");
	}

	public void clickOnContinueBtnOnHandoffTab() {
		continueBtnOnHandoff.click();
	}

	public boolean isRecipientPageVisible() {
		return recipientPage.isVisible();
	}

	public void clickOnRadioBtnAgnstFetchedHandOff(String expectedHandOff) {
		int size = handoffTypeList.size();
		for (int i = 0; i < size; i++) {
			if (handoffTypeList.get(i).getText().equals(expectedHandOff)) {
				withAction().moveToElement(handoffTypeRadioBtnList.get(i)).build().perform();
				evaluateJavascript("arguments[0].click();", handoffTypeRadioBtnList.get(i));
			}
		}
	}

	public boolean isNewlyAddedHandOffVisible(String handOffName) {
		int size = listOfAddedHandOffs.size();
		for (index = 0; index < size; index++) {
			if (listOfAddedHandOffs.get(index).getText().equals(handOffName))
				return true;
		}
		return false;
	}

	public boolean isAddHandOffLabelVisible() {
		return addHandOffLabel.isVisible();
	}

	public void clickOnCloseBtnOnEditPopup() {
		listOfControlsOnEditPopup.get(0).click();
	}

	public boolean isEditPopupVisible() {
		return editHandoffText.isVisible();
	}

	public boolean isSuccessMsgVisible() {
		return successMsg.isVisible();
	}

	public String getSuccessMsgOnSave() {
		return successMsg.withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible().getText();
	}

	public boolean isNewlyEditHandoffVisible() {
		withAction().moveToElement(listOfDescriptions.get(index)).build().perform();
		listOfDescriptions.get(index).withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible();
		if (enterWorkflowName.equals(listOfDescriptions.get(index).getText().trim())) {
			return true;
		}
		return false;
	}

	public void clickOnRandomEditLink() {
		int editLinkSize = listOfEditIcons.size();
		index = CommonMethods.getRandom(editLinkSize);
		while (index == editLinkSize) {
			index = CommonMethods.getRandom(editLinkSize);
		}
		evaluateJavascript("arguments[0].click();", listOfEditIcons.get(index));
	}

	public List<String> getLabelsOnEditPopup() {
		List<String> listOfLabels = new ArrayList<>();
		for (WebElementFacade element : listOfLabelsOnEditPopup) {
			listOfLabels.add(element.getText().trim());
		}
		return listOfLabels;
	}

	public List<String> getControlsOnEditPopup() {
		List<String> listOfControls = new ArrayList<>();
		for (WebElementFacade element : listOfControlsOnEditPopup) {
			listOfControls.add(element.getText().trim());
		}
		return listOfControls;
	}

	public String getWorkflowDescriptionText() {
		workflowDescriptionText = evaluateJavascript("return document.querySelector('#addWorkflowDescription').value;")
				.toString();
		return workflowDescriptionText;
	}

	public boolean areValuesPopulatedInTheControls() {
		String workflowNameText = evaluateJavascript("return document.querySelector('#addWorkflowName').value;")
				.toString();
		if (!(workflowNameText == null && workflowDescriptionText == null)) {
			return true;
		}
		return false;
	}

	public String editWorkflowDescription() {
		int randomNumber = listOfEditIcons.size();
		enterWorkflowName = getWorkflowDescriptionText().concat("_" + CommonMethods.getRandom(randomNumber));
		workflowDescriptionTextbox.type(enterWorkflowName);
		return enterWorkflowName;
	}

	public boolean isWorkflowTitleVisible() {
		return workflowTitle.isVisible();
	}

	public boolean isHandoffTabVisible() {
		return handoffTab.isVisible();
	}

	public String getHandoffTabColor() {
		return handoffTab.getCssValue("background-color");
	}

	public String getActionTypeTabColor() {
		return actionTypeTab.getCssValue("background-color");
	}

	public List<String> getWorkflowTabs() {
		List<String> workflowTabValues = new ArrayList<>();
		for (WebElementFacade tabs : workflowTabs) {
			workflowTabValues.add(tabs.getText().trim());
		}
		return workflowTabValues;
	}

	public String getWorkflowSummaryLabel() {
		return workflowSummaryLabel.getText().trim();
	}

	public String getDefaultHandoffName() {
		return defaultHandoffName.getText();
	}

	public boolean isAddHandoffBtnVisible() {
		return addHandoffButton.isVisible();
	}

	public boolean isStepFirstContinueBtnVisible() {
		return stepFirstContinueBtn.isVisible();
	}

	public List<String> getWorkflowGridHeaders() {
		List<String> workflowHeaders = new ArrayList<>();
		int size = workflowGridHeaders.size();
		for (int i = 1; i < size; i++) {
			workflowHeaders.add(workflowGridHeaders.get(i).getText());
		}
		return workflowHeaders;
	}

	public boolean isHandoffEditButtonVisible() {
		return handoffEditButton.isVisible();
	}

	public String isDefaultRadioBtnSelected() {
		return evaluateJavascript("return document.querySelector('#workflowChoiceID-0').checked").toString();
	}

	public List<Object> verifyEditDispositionPopupPrePopulated() {
		List<Object> listOfVal = new ArrayList<>();
		int count = 0;
		if (dispositionCodeTextBox.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Disposition Code");
		}
		if (dispositionNameTextBox.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Disposition Name");
		}
		if (!nextDispositionByDropdown.getSelectedVisibleTextValue().equals("--Select One--")) {
			count = count + 1;
		} else {
			listOfVal.add("Next Disposition by");
		}
		if (respondDeadLineTxtBoxOnDispositionPopup.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Response deadline");
		}
		if (followUpDaysTxtBoxOnDispositionPopup.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Follow up days");
		}
		if (!dispositionStatusDrpDwn.getSelectedVisibleTextValue().equals("--Select One--")) {
			count = count + 1;
		} else {
			listOfVal.add("Disposition status");
		}
		if (count == 6) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}
		return listOfVal;
	}

	public void clickOnEditLinkOnDispositionGrid() {
		evaluateJavascript("arguments[0].click();", editLinkOnDispositionGrid);
	}

	public void clickAddRecipientButton() {
		withAction().moveToElement(addRecipientButton).build().perform();
		evaluateJavascript("arguments[0].click();", addRecipientButton);
	}

	public List<String> getListOfAddRecipientLabels() {
		List<String> listOfRecipientLabels = new ArrayList<String>();
		for (WebElementFacade element : listOfAddRecipientLabels) {
			listOfRecipientLabels.add(element.getText().trim());
		}
		return listOfRecipientLabels;
	}

	public List<String> getListOfAddRecipientButtons() {
		List<String> listOfRecipientButtons = new ArrayList<String>();
		for (WebElementFacade element : listOfAddRecipientButtons) {
			listOfRecipientButtons.add(element.getText().trim());
		}
		return listOfRecipientButtons;
	}

	public void clickCloseButtonOnAddRecipient() {
		closeButtonOnAddRecipient.click();
	}

	public boolean isAddedRecipientNameVisible(String recipientName) {
		int size = listOfRecipientNames.size();
		for (index = 0; index < size; index++) {
			if (listOfRecipientNames.get(index).getText().equals(recipientName)) {
				withAction().moveToElement(listOfRecipientNames.get(index)).build().perform();
				return true;
			}
		}
		return false;
	}

	public boolean isAddedRecipientDescVisible(String recipientDesc) {
		if (listOfRecipientDesc.get(index).getText().equals(recipientDesc)) {
			return true;
		}
		return false;
	}

	public void clickOnRadioBtnAgnstFetchedRecipient(String expectedRecipientName) {
		int size = recipientsList.size();
		for (int i = 0; i < size; i++) {
			if (recipientsList.get(i).getText().equals(expectedRecipientName)) {
				evaluateJavascript("arguments[0].click();", recipientsRadioBtnList.get(i));
			}
		}
	}

	public void clickOnDetailsOfSpecificRecipient(String recipientName) {
		int size = listOfRecipientNames.size();
		for (index = 0; index < size; index++) {
			if (listOfRecipientNames.get(index).getText().equals(recipientName)) {
				evaluateJavascript("arguments[0].click();", listOfDetailsOnRecipientTab.get(index));
				break;
			}
		}
	}

	public String getCreatedByRecipientText() {
		return createdByRecipient.getText();
	}

	public String getCreatedDateRecipientText() {
		return createdDateRecipient.getText();
	}

	public void clickSpecificRecipientRadioBtn(String recipientName) {
		int size = listOfRecipientsName.size();
		for (int i = 0; i < size; i++) {
			if (listOfRecipientsName.get(i).getText().equals(recipientName)) {
				withAction().moveToElement(listOfRecipientsRadioBtn.get(i)).build().perform();
				evaluateJavascript("arguments[0].click();", listOfRecipientsRadioBtn.get(i));
			}
		}
	}

	public List<Object> verifyAddActionPopupControlsVisible(List<String> listOfFields) {
		List<Object> listOfVal = new ArrayList<>();
		int count = 0;
		int size = actionPopupControls.size();
		for (int i = 0; i < size; i++) {
			if (actionPopupControls.get(i).getText().equals(listOfFields.get(i))) {
				count = count + 1;
			} else {
				listOfVal.add(listOfFields.get(i));
			}
		}
		if (closeBtnOnActionPopup.getText().equals(listOfFields.get(8))) {
			count = count + 1;
		} else {
			listOfVal.add("Close");
		}
		if (saveChangesBtnOnActionPopup.getText().equals(listOfFields.get(9))) {
			count = count + 1;
		} else {
			listOfVal.add("Save changes");
		}
		if (count == 10) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}
		return listOfVal;
	}

	public void clickRequiredCheckBoxOnActionPopup() {
		requiredCheckBoxActionPopup.click();
	}

	public boolean isAddActionPopupVisible() {
		return addActionPopup.isVisible();
	}

	public void clickSpecificDetailsLinkOnActionTab(String actionName) {
		int size = listOfActionNames.size();
		for (int i = 0; i < size; i++) {
			if (listOfActionNames.get(i).getText().equals(actionName)) {
				withAction().moveToElement(listOfDetailsLinkOnActionTab.get(i)).build().perform();
				evaluateJavascript("arguments[0].click();", listOfDetailsLinkOnActionTab.get(i));
			}
		}
	}

	public String getActionCreatedDate() {
		return actionCreatedDate.getText();
	}

	public String getActionCreatedBy() {
		return actionCreatedBy.getText();
	}

	public String getActionUpdatedDate() {
		return actionUpdatedDate.getText();
	}

	public String getActionUpdatedBy() {
		if (actionUpdatedBy.isVisible()) {
			return actionUpdatedBy.getText();
		} else {
			return "";
		}
	}

	public String getErrorMsgOnActionPopup() {
		withAction().moveToElement(errorMsgForEmptyFieldsOnActionPopup).build().perform();
		String expectedMEssage = errorMsgForEmptyFieldsOnActionPopup.withTimeoutOf(Duration.ofSeconds(20))
				.waitUntilVisible().getText().trim();
		return expectedMEssage;

	}

	public boolean isNewlyAddedActionVisibleInGrid(String actionName) {
		int size = listOfActionNames.size();
		for (int i = 0; i < size; i++) {
			if (listOfActionNames.get(i).getText().equals(actionName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDispositionTabVisible() {
		return dispositionTab.isVisible();
	}

	public String getDispositionTabColor() {
		return dispositionTab.getCssValue("background-color");
	}

	public String getFirstDispositionName() {
		return firstDispositionName.getText();
	}

	public String getDispositionBreadCrumbValue() {
		return dispositionBreadCrumb.getText();
	}

	public List<String> getDispositionButtonText() {
		List<String> dispositionBtnText = new ArrayList<>();
		for (WebElementFacade dispositionBtn : dispositionChooseGridButtons) {
			dispositionBtnText.add(dispositionBtn.getText());
		}
		return dispositionBtnText;
	}

	public boolean isSaveConfigBtnOnDispositionTabDisabled() {
		return dispositionSaveConfigBtn.isDisabled();
	}

	public int getDispositionNameCount() {
		return dispositionNameList.size();
	}

	public int getDispositionEditLinksCount() {
		return dispositionEditLinkList.size();
	}

	public int getDispositionDetailsLinkCount() {
		return dispositionDetailsLinkList.size();
	}

	public int getDispositionReorderLinksCount() {
		return dispositionReorderLinksList.size();
	}

	public void clickFirstDispositionDetailsLink() {
		evaluateJavascript("arguments[0].click();", firstDispositionDetailsLink);
	}

	public List<String> getDispositionDetailsColumnNamesList() {
		List<String> columnNames = new ArrayList<>();
		for (WebElementFacade columnName : dispositionDetailsColumnList) {
			columnNames.add(columnName.getText());
		}
		return columnNames;
	}

	public void clickExpandedDetailsLinkOnDispositionTab() {
		expandedDispositionDetailsLink.click();
	}

	public boolean isDispositionDetailsCollapsed() {
		return firstDispositionDetailsLink.isVisible();
	}

	public boolean isDispositionDetailsSectionVisible() {
		return dispositionDetailsSection.isVisible();
	}

	public List<String> getDispositionGridHeaderList() {
		List<String> headerList = new ArrayList<String>();
		for (WebElementFacade dispositionHeader : listOfDispositionHeader) {
			headerList.add(dispositionHeader.getText().trim());
		}
		return headerList;
	}

	public void clickFirstEditIconOnRecipientTab() {
		editIconOnRecipientTab.click();
	}

	public List<String> getListOfEditRecipientLabels() {
		List<String> listOfRecipientLabels = new ArrayList<String>();
		for (WebElementFacade element : listOfEditRecipientLabels) {
			listOfRecipientLabels.add(element.getText().trim());
		}
		return listOfRecipientLabels;
	}

	public String getSaveRecipientButtonText() {
		return saveRecipientButton.getText();
	}

	public boolean verifyEditRecipientPrePopulatedFields() {
		if (recipientNameTextbox.getText() != null && recipientDescriptionTextbox.getText() != null)
			return true;
		else
			return false;
	}

	public String enterAndGetRandomRecipientDescText() {
		recipientDescriptionTextbox.type(RandomStringUtils.randomAlphanumeric(6));
		return recipientDescriptionTextbox.getTextValue();
	}

	public void clickSaveRecipientButton() {
		saveRecipientButton.click();
	}

	public String getFirstRecipientDesc() {
		return firstRecipientDesc.getText();
	}

	public void clickFirstRecipientDetailsLink() {
		firstDetailsLinkOnRecipient.click();
	}

	public String getUpdatedByFieldValue() {
		return recipientUpdatedByField.getText();
	}

	public String getUpdatedDateFieldValue() {
		return recipientUpdatedDateField.getText();
	}

	public void clickRespondDeadlineOnEditDispositionTypePopup() {
		respondDeadLineTxtBoxOnDispositionPopup.click();
	}

	public String enterAndGetRandomValueRespondDeadlineForEditDispositionTypePopup() {
		respondDeadLineTxtBoxOnDispositionPopup.type(RandomStringUtils.randomNumeric(1));
		return respondDeadLineTxtBoxOnDispositionPopup.getTextValue();
	}

	public String getMappedDispositionTimeLimitValueOnDispositionTypeGrid() {
		return mappedTimeLimitValueOnDispositionTypeGrid.getText();
	}

	public void clickOnCloseBtnOnActionPopup() {
		closeBtnOnActionPopup.click();
	}

	public int getActionNamesCount() {
		return listOfActionNames.size();
	}

	public List<String> getListOfActionNames() {
		List<String> actionNamesList = new ArrayList<>();
		for (WebElementFacade actionName : listOfActionNames) {
			actionNamesList.add(actionName.getText());
		}
		return actionNamesList;
	}

	public void clickSpecificRadioBtnOnActionTab(String actionName) {
		int size = listOfActionNames.size();
		for (int i = 0; i < size; i++) {
			if (listOfActionNames.get(i).getText().equals(actionName)) {
				withAction().moveToElement(actionsRadioBtnList.get(i)).build().perform();
				evaluateJavascript("arguments[0].click();", actionsRadioBtnList.get(i));
			}
		}
	}

	public List<String> getListOfDispositionNames() {
		List<String> listOfDispositionNames = new ArrayList<>();
		for (WebElementFacade dispositionName : dispositionNameList) {
			listOfDispositionNames.add(dispositionName.getText());
		}
		return listOfDispositionNames;
	}

	public void selectNewHandOffType(String newlyAddedHandOff) {
		handOffTypeDrpDwn.selectByVisibleText(newlyAddedHandOff);
	}

	public void selectCreateDrpDwn(String recipientDesc) {
		createDrpDwn.selectByVisibleText(recipientDesc);
	}

	public void selectWhyDrpDwn(String actionName) {
		whyDrpDwn.selectByVisibleText(actionName);
	}

	public void selectDispostionDrpDwn(String dispositionName) {
		dispositionDrpDwn.selectByVisibleText(dispositionName);
	}

	public List<String> getNewHandOffValuesAdded() {
		List<String> handOffValues = new ArrayList<>();
		handOffValues.add(handOffTypeDrpDwn.getSelectedVisibleTextValue());
		handOffValues.add(createDrpDwn.getSelectedVisibleTextValue());
		handOffValues.add(whyDrpDwn.getSelectedVisibleTextValue());
		handOffValues.add(dispositionDrpDwn.getSelectedVisibleTextValue());
		return handOffValues;
	}

	public int getPositionOfHandoffType(String handoffNameVal) {
		int size = listOfHandoffNameOnHandoffTab.size();
		int position = 0;
		for (int i = 0; i < size; i++) {
			if (listOfHandoffNameOnHandoffTab.get(i).getText().trim().equals(handoffNameVal)) {
				position = i;
			}
		}
		return position;
	}

	public void clickOnHandoffTypeRadioBtn(String handoffName) {
		evaluateJavascript("arguments[0].click();",
				listOfRadioLabelOnHandoffTab.get(getPositionOfHandoffType(handoffName)));
	}

	public int getPositionOfRecipient(String recipientNameVal) {
		int size = listOfRecipientNames.size();
		int position = 0;
		for (int i = 0; i < size; i++) {
			if (listOfRecipientNames.get(i).getText().trim().equals(recipientNameVal)) {
				position = i;
			}
		}
		return position;
	}

	public void clickOnRecipientRadioBtn(String recipientNameVal) {
		listOfRecipientsRadioBtn.get(getPositionOfRecipient(recipientNameVal)).click();
	}

	public String getFollowUpDayOnActionTypeTab() {
		return followDaysOnActionTypeTab.getText().trim();
	}

	public String getTimeLimitOnActionTypeTab() {
		return timeLimitOnActionTypeTab.getText().trim();
	}

	public String getActionNameOnActionTypeTab() {
		return actionNameOnActionTypeTab.getText().trim();
	}

	public void clickArSupervisorRadioBtn() {
		withAction().moveToElement(arSupervisorRadioBtn).build().perform();
		arSupervisorRadioBtn.click();
	}

	public void clickSpecificActionTypeRadioBtn(String actionType) {
		int size = actionTypeList.size();
		for (index = 0; index < size; index++) {
			if (actionTypeList.get(index).getText().equals(actionType)) {
				evaluateJavascript("arguments[0].click();", actionTypeRadioBtnList.get(index));
				break;
			}
		}
	}

	public void clickSpecificEditDispositionTypeBtn(String dispositionType) {
		successMsg.withTimeoutOf(Duration.ofSeconds(20)).waitUntilNotVisible();
		int size = dispositionTypeList.size();
		for (index = 0; index < size; index++) {
			if (dispositionTypeList.get(index).getText().equals(dispositionType)) {
				evaluateJavascript("arguments[0].click();", dispositionTypeEditBtnList.get(index));
				break;
			}
		}
	}

	public boolean isEditDispositionPopupVisible() {
		return editDispositionPopupHeader.isVisible();
	}

	public void clickSaveChangesBtnOnDispositionPopUp() {
		saveChangesBtnDispositionPopUp.click();
	}

	public String getDispositionNotes() {
		dispositionNotes.type(RandomStringUtils.randomAlphabetic(6));
		return dispositionNotes.getText();
	}

	public String getSuccessMessage() {
		return successMessage.getText();
	}

	public void clickSpecificActionTypeEditLink(String actionType) {
		int size = actionTypeList.size();
		for (index = 0; index < size; index++) {
			if (actionTypeList.get(index).getText().equals(actionType)) {
				evaluateJavascript("arguments[0].click();", listOfEditLinkOnActionTypeGrid.get(index));
				break;
			}
		}
		
	}

	public List<Object> verifyEditActionPopupControlsVisible(List<String> listOfFields) {
		List<Object> listOfVal = new ArrayList<>();
		int count = 0;
		int size = listOfEditActionTypePopUpFields.size();
		for (int i = 0; i < size; i++) {
			if (listOfEditActionTypePopUpFields.get(i).getText().equals(listOfFields.get(i))) {
				count = count + 1;
			} else {
				listOfVal.add(listOfFields.get(i));
			}
		}
		if (closeBtnOnPopUp.getText().equals(listOfFields.get(8))) {
			count = count + 1;
		} else {
			listOfVal.add("Close");
		}
		if (saveChangesBtnOnPopUp.getText().equals(listOfFields.get(9))) {
			count = count + 1;
		} else {
			listOfVal.add("Save changes");
		}
		if (count == 10) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}
		return listOfVal;
	}

	public List<Object> verifyEditActionPopupPrePopulated() {
		List<Object> listOfVal = new ArrayList<>();
		int count = 0;
		if (actionNameOnEditActionPopUp.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Action name");
		}
		if (actionDescOnEditActionPopUp.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Action description");
		}
		if (!nextActionByOnEditActionPopUp.getSelectedVisibleTextValue().equals("--Select One--")) {
			count = count + 1;
		} else {
			listOfVal.add("Next action by");
		}
		if (respondDeadlineOnEditActionPopUp.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Response deadline");
		}
		if (followUpDaysOnEditActionPopUp.getText() != null) {
			count = count + 1;
		} else {
			listOfVal.add("Follow up days");
		}
		if (!actionStatusOnEditActionPopUp.getSelectedVisibleTextValue().equals("--Select One--")) {
			count = count + 1;
		} else {
			listOfVal.add("Action status");
		}
		if (count == 6) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}
		return listOfVal;
	}

	public void clickRespondDeadline() {
		respondDeadlineOnEditActionPopUp.click();
	}

	public String enterAndGetRandomValueRespondDeadline() {
		respondDeadlineOnEditActionPopUp.type(RandomStringUtils.randomNumeric(1));
		return respondDeadlineOnEditActionPopUp.getTextValue();
	}

	public void clickSaveChangesBtnEditActionPopup() {
		saveChangesBtnOnPopUp.click();
		
	}

	public boolean isEditActionPopupVisible() {
		return editActionPopUpHeader.isVisible();
	}

	public String getSpecificTimeLimitValueInActionTypeGrid(String actionType) {
		int size = actionTypeList.size();
		for (index = 0; index < size; index++) {
			if (actionTypeList.get(index).getText().equals(actionType)) {
				break;
			}
		}
		return listOfTimeLimitInActionTypeGrid.get(index).getText();
	}

	public void clickSpecificActionTypeDetailsLink(String actionType) {
		int size = actionTypeList.size();
		for (index = 0; index < size; index++) {
			if (actionTypeList.get(index).getText().equals(actionType)) {
				evaluateJavascript("arguments[0].click();", listOfDetailsLinkActionTypeTab.get(index));
				break;
			}
		}
		
	}
}