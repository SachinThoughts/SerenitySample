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
	private static final String backgroundColour = "background-color";

	@FindBy(xpath = "//h3[text()='AHtoDecision Workflow Configuration']")
	private WebElementFacade workflowTitle;

	@FindBy(id = "HandoffLink")
	private WebElementFacade handoffTab;

	@FindBy(id = "ActionTypeLink")
	private WebElementFacade actionTypeTab;

	@FindBy(xpath = "//div[@class = 'navbar']//li/a")
	private List<WebElementFacade> workflowTabs;

	@FindBy(xpath = "//div[@class = 'summary']//li[1]")
	private WebElementFacade workflowSummaryLabel;

	@FindBy(xpath = "(//span[@data-bind='text: workflowDescriptionName'])[1]")
	private WebElementFacade defaultHandoffName;

	@FindBy(id = "btnAddHandoff")
	private WebElementFacade addHandoffButton;

	@FindBy(xpath = "//div[@id = 'step1']//button[contains(@class,'btn btnSuccess')]")
	private WebElementFacade handOffContinueBtn;

	@FindBy(xpath = "//ul[@class = 'sop-header workflowConfiguration']//li")
	private List<WebElementFacade> workflowGridHeaders;

	@FindBy(xpath = "(//a[@href='#editHandoff'])[1]")
	private WebElementFacade handoffEditButton;

	@FindBy(xpath = "//label[@for = 'workflowChoiceID-0']")
	private WebElementFacade defaultRadioBtn;

	@FindBy(xpath = "//a[@data-target='#addHandoff']")
	private List<WebElementFacade> listOfEditIcons;

	@FindBy(xpath = "//label[@for='addWorkflowName']")
	private WebElementFacade workflowName;

	@FindBy(xpath = "//div[@id='addHandoff']//label[@class='col-lg-4 control-label']")
	private List<WebElementFacade> listOfLabelsOnEditPopup;

	@FindBy(xpath = "//div[@id='addHandoff']//div[@class='modal-footer']/button")
	private List<WebElementFacade> listOfControlsOnEditPopup;

	@FindBy(id = "msg_success")
	private WebElementFacade successMsg;

	@FindBy(xpath = "//h4[text()='Edit HandOff']")
	private WebElementFacade editHandoffText;

	@FindBy(xpath = "//input[@id='addWorkflowDescription']")
	private WebElementFacade workflowDescriptionTextbox;

	@FindBy(xpath = "//div[@id='dvHandOff']//div[3]//span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> listOfDescriptions;

	@FindBy(id = "addHandoffLabel")
	private WebElementFacade addHandOffLabel;

	@FindBy(xpath = "//div[@id='dvHandOff']//div[2]//span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> listOfAddedHandOffs;

	@FindBy(xpath = "//div[@id='dvHandOff']//div[2]//span[@data-bind='text: workflowDescriptionName']")
	private List<WebElementFacade> handoffTypeList;

	@FindBy(xpath = "//div[@id='dvHandOff']//div[@class='radio']/label")
	private List<WebElementFacade> handoffTypeRadioBtnList;

	@FindBy(xpath = "//div[@class='row']//button[contains(@class,'next-step step1')]")
	private WebElementFacade continueBtnOnHandoff;

	@FindBy(xpath = "//div[@id='step2']//h2[text()='Choose Recipient']")
	private WebElementFacade recipientPage;

	@FindBy(id = "RecipientLink")
	private WebElementFacade recipientTab;

	@FindBy(id = "sName0")
	private WebElementFacade defaultRecipientName;

	@FindBy(xpath = "//button[contains(@data-target,'#addRecipient')]")
	private WebElementFacade addRecipientBtn;

	@FindBy(xpath = "//div[@id='step2']//button[contains(@class,'next-step step2')]")
	private WebElementFacade recipientContinueBtn;

	@FindBy(xpath = "//ol[@class='breadcrumb defect-summary']/li[contains(@class,'step1')]")
	private WebElementFacade handOffOnBreadcrumb;

	@FindBy(xpath = "//ol[@class='breadcrumb defect-summary']/li[contains(@class,'step2')]")
	private WebElementFacade recipientOnBreadcrumb;

	@FindBy(xpath = "//div[@id='step2']/div/h2")
	private WebElementFacade chooseRecipientLabel;

	@FindBy(xpath = "//ul[@class='sop-header']/li")
	private List<WebElementFacade> listOfSopHeader;

	@FindBy(xpath = "(//a[contains(@data-target,'#editRecipient')])[1]")
	private WebElementFacade editIconOnRecipientTab;

	@FindBy(xpath = " (//*[@id='dvRecipientDetails']//ul)[2]/preceding-sibling::div//a[2]/i")
	private WebElementFacade detailsBtnOnRecipientTab;

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']/li/span[1]")
	private List<WebElementFacade> listOfDetailColumnsRecipientTab;

	@FindBy(xpath = "//div[@id='step3']//div[@class='row']/div[2]/button")
	private WebElementFacade continueBtnOnActionTypeTab;

	@FindBy(id = "ActionTypeLink")
	private WebElementFacade actionTypeLink;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//div[@class='config-tools appeals']/div/label")
	private List<WebElementFacade> listOfActionTypeRadioBtns;

	@FindBy(xpath = "//button[contains(text(),' Add Recipient')]")
	private WebElementFacade addRecipientButton;

	@FindBy(xpath = "//div[@id='addRecipient']//div[@class ='form-group']/label")
	private List<WebElementFacade> listOfAddRecipientLabels;

	@FindBy(xpath = "//div[@id='addRecipient']//div[@class = 'modal-footer']/button")
	private List<WebElementFacade> listOfAddRecipientButtons;

	@FindBy(xpath = "//div[@id='addRecipient']//button/span[1]/i")
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

	@FindBy(xpath = "//div[@id='editRecipient']//div[@class ='form-group']/label")
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

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated By']/following-sibling::span")
	private WebElementFacade recipientUpdatedByField;

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated Date']/following-sibling::span")
	private WebElementFacade recipientUpdatedDateField;

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade createdDateRecipient;

	@FindBy(xpath = "//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade createdByRecipient;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[@class='radio']//label")
	private List<WebElementFacade> listOfRecipientsRadioBtn;

	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[contains(@id,'sName')]")
	private List<WebElementFacade> listOfRecipientsName;

	@FindBy(xpath = "//div[@id='addNewAction']//div[not(contains(@class,'hidden'))]/label[contains(@class,'control-label')]")
	private List<WebElementFacade> actionPopupControls;

	@FindBy(xpath = "//div[@id='addNewAction']//button[text()='Close']")
	private WebElementFacade closeBtnOnActionPopup;

	@FindBy(xpath = "//div[@id='addNewAction']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtnOnActionPopup;

	@FindBy(xpath = "//div[@id='addNewAction']//*[@id='sopActionRequired']/..")
	private WebElementFacade requiredCheckBoxActionPopup;

	@FindBy(id = "addEditNewActionLabel")
	private WebElementFacade addActionPopup;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//i[@class='fa fa-chevron-right fa-1-5x']")
	private List<WebElementFacade> listOfDetailsLinkOnActionTab;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//span[contains(@id,'NA')]")
	private List<WebElementFacade> listOfActionNames;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade actionCreatedDate;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade actionCreatedBy;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated Date']/following-sibling::span")
	private WebElementFacade actionUpdatedDate;

	@FindBy(xpath = "//ul[@id='WorkflowTypeActionsSorttable']//ul[@class='more-info workflowConfigdetailsInfo']//span[text()='Updated By']/following-sibling::span")
	private WebElementFacade actionUpdatedBy;

	@FindBy(xpath = "//div[@id='addNewAction']//div[@class='alert alert-info']/span")
	private WebElementFacade errorMsgForEmptyFieldsOnActionPopup;

	@FindBy(id = "DispositionTypeLink")
	private WebElementFacade dispositionTab;

	@FindBy(xpath = "//ul[@id='WorkflowTypeDispositionSorttable']//span[@id='Disp0']")
	private WebElementFacade firstDispositionName;

	@FindBy(xpath = "//ol[@class='breadcrumb defect-summary']//li[@class='step3']")
	private WebElementFacade dispositionBreadCrumb;

	@FindBy(xpath = "//*[@id='step4']//*[contains(@class,'workflow')]//*[@class='col-lg-3']//button[@type='button']")
	private List<WebElementFacade> dispositionChooseGridButtons;

	@FindBy(xpath = "//*[@class='btn btnSuccess btn-block next-step last-step step4 saveconf btnPrimary']")
	private WebElementFacade dispositionSaveConfigBtn;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions']//li//div[3]//span[contains(@id,'Disp')]")
	private List<WebElementFacade> dispositionNameList;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions']//li//i[@class='fa fa-edit fa-1-5x']")
	private List<WebElementFacade> dispositionEditLinkList;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions']//li//i[@class='fa fa-chevron-right fa-1-5x']")
	private List<WebElementFacade> dispositionDetailsLinkList;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions']//li//i[@class='fa fa-arrows fa-1-5x']")
	private List<WebElementFacade> dispositionReorderLinksList;

	@FindBy(xpath = "//div[@id='dvWorkflowDispositions']//li//i[@class='fa fa-arrows fa-1-5x']")
	private List<WebElementFacade> dispositionRadioBtnList;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//li/span[1]")
	private List<WebElementFacade> dispositionDetailsColumnList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//li//i[@class='fa fa-1-5x fa-chevron-down']")
	private WebElementFacade expandedDispositionDetailsLink;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']")
	private WebElementFacade dispositionDetailsSection;

	@FindBy(xpath = "//*[@class='sop-header dispositions']/li")
	private List<WebElementFacade> listOfDispositionHeader;

	@FindBy(id = "txtDispositionsNotes")
	private WebElementFacade dispositionNotes;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtn;

	@FindBy(id = "addNewDispositionLabel")
	private WebElementFacade dispositionPopupHeader;

	@FindBy(xpath = "//*[@id='addNewDisposition']//i[@class='fa fa-close']")
	private WebElementFacade dispositionPopupClose;

	@FindBy(xpath = "//*[@id='dvWorkflowTypeActions']/ul/li/div[1]/div/div/label")
	private List<WebElementFacade> actionsRadioBtnList;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']/li/div/div/a[3]/i")
	private List<WebElementFacade> listOfDetailsLinkOnDispositionTab;

	@FindBy(xpath = "//*[@id='WorkflowTypeDispositionSorttable']/li/div[5]/span")
	private List<WebElementFacade> mappedTimeLimitValueOnDispositionTypeGrid;

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

	@FindBy(xpath = "//ul[@class='sop-types workflowConfiguration']//span[text()='Writeoff']//ancestor::li//child::label[contains(@for,'work')]")
	private WebElementFacade writeOffRadioBtn;

	@FindBy(xpath = "//span[text()='Identified']")
	private List<WebElementFacade> actionTypeStatusList;

	@FindBy(xpath = "//div[not(contains(@class,'hidden')) and @id='addNewDisposition']//label[@class='col-lg-4 control-label']")
	private List<WebElementFacade> labelsOnDispositionPopup;

	@FindBy(id = "txtdispositionCode")
	private WebElementFacade dispositionCodeTextBox;

	@FindBy(xpath = "//*[@id='addNewDisposition']//div[@class='alert alert-info']/span")
	private WebElementFacade errorMsgOnEmptyFields;

	@FindBy(id = "txtdispositionDescription")
	private WebElementFacade dispositionNameTextBox;

	@FindBy(xpath = "//select[@id='dnn_ctr1590_TaskPanel_taskBase_WorkflowDisposition_ddldispositionTarget']")
	private WebElementFacade nextDispositionByDropdown;

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

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//div[3]/child::*[contains(@id,'Disp')]")
	private List<WebElementFacade> dispositionTypeList;

	@FindBy(xpath = "//*[@id='dvWorkflowDispositions']//i[@class='fa fa-edit fa-1-5x']")
	private List<WebElementFacade> dispositionTypeEditBtnList;

	@FindBy(id = "addNewDispositionLabel")
	private WebElementFacade editDispositionPopupHeader;

	@FindBy(xpath = "//*[@id='addNewDisposition']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtnDispositionPopUp;

	/**
	 * This method fetch ActionName from the Breadcrumb
	 * 
	 * @return breadcrumb test value
	 */
	public String getActionTextBreadcrumb() {
		return actionTypeBreadcrumb.getText().trim();
	}

	/**
	 * This method fetch selected ActionName
	 * 
	 * @return breadcrumb test value
	 */
	public String getSelectedActionTypeName() {
		return actionTypeName.getText().trim();
	}

	/**
	 * This method fetch error msg on duplicate ActionName
	 * 
	 * @return duplicate error message
	 */
	public String getErrMsgOnDuplicateActionName() {
		return duplicateActionNameErrMsg.getText().trim();
	}

	/**
	 * This method clicks edit of first Action
	 */
	public void clickFirstEditLinkOnActionTypeTab() {
		firstEditLinkOnActionTypeTab.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().click();
	}

	/**
	 * This method checks the visibility of Action tab
	 */
	public boolean isActionTabVisible() {
		return actionTab.isVisible();
	}

	/**
	 * This method checks the visibility of Continuebtn on Action tab
	 */
	public boolean isContinueBtnOnActionTypeVisible() {
		return continueBtnOnActionTypeTab.isVisible();
	}

	/**
	 * This method checks the visibility of Details link on Action tab
	 */
	public boolean isDetailLinkOnActionTypeVisible() {
		return detailsLinkBtnOnActionTypeTab.isVisible();
	}

	/**
	 * This method checks the visibility of Radio btn on Action tab
	 */
	public boolean isRadioBtnOnActionTypeVisible() {
		return listOfActionTypeRadioBtns.get(0).isVisible();
	}

	/**
	 * This method checks the visibility of first editlink on Action tab
	 * 
	 */
	public boolean isEditLinkOnActionTypeVisible() {
		return firstEditLinkOnActionTypeTab.isVisible();
	}

	/**
	 * This Method checks visibility of Reorder link On Action Tab
	 * 
	 */
	public boolean isReorderOnActionTypeVisible() {
		return reorderLinkOnActionType.isVisible();
	}

	/**
	 * This Method click on Details btn on Action Tab
	 */
	public void clickOnDetailsBtnOnActionTypeTab() {
		detailsLinkBtnOnActionTypeTab.click();
	}

	/**
	 * Method clicks on Handoff tab
	 */
	public void clickHandoffTab() {
		handoffTab.click();
	}

	/**
	 * This method fetches the labels on HandOff popup
	 * 
	 * @return text value of the labels
	 */
	public List<String> getActionTypeHeaders() {
		List<String> listOfLabels = new ArrayList<>();
		for (WebElementFacade headers : headersOnActionType) {
			listOfLabels.add(headers.getText().trim());
		}
		return listOfLabels;
	}

	/**
	 * Method clicks on details link on the Disposition tab
	 * 
	 */
	public void clickOnDetailsLinkOnDispositionTab() {
		evaluateJavascript("arguments[0].click();", dispositionDetailsLinkList.get(0));
	}

	/**
	 * @return error message text on adding duplicate Disposition Name
	 */
	public String getDispositionErrorMsgOnDuplicateCode() {
		return errorMsgOnduplicateDispositionCode.getText().trim();
	}

	/**
	 * Method clicks on the first Action type
	 */
	public void clickOnAnyActionTypeRadioBtn() {
		evaluateJavascript("arguments[0].click();", listOfActionTypeRadioBtns.get(1));
	}

	/**
	 * Method clicks on the Action Tab button
	 */
	public void clickOnActionType() {
		actionTypeLink.click();
	}

	/**
	 * Method clicks on any Handoff on the Handoff tab
	 */
	public void clickOnRandomHandoffType() {
		int randomHandoff = CommonMethods.getRandom(handoffTypeRadioBtnList.size() - 1);
		evaluateJavascript("arguments[0].click();", handoffTypeRadioBtnList.get(randomHandoff));
	}

	/**
	 * @return the Disposition Code value from the textbox
	 */
	public String getDispositionCodeFromTextBox() {
		return evaluateJavascript("return arguments[0].value;", dispositionCodeTextBox).toString();
	}

	/**
	 * Method clicks on the close btn on Disposition Page
	 */
	public void clickOnCloseBtnOnDispositionPopup() {
		closeBtnOnDispositionPopup.click();
	}

	/**
	 * @return the text value from created By field
	 */
	public String getCreatedByFieldValue() {
		return createdByField.getText();
	}

	/**
	 * @return the text value from created date field
	 */
	public String getCreatedDateFieldValue() {
		return createdDateField.getText();
	}

	/**
	 * Method clicks on the newly created Disposition from the list of Dispositions
	 * 
	 * @param dispositionName
	 *            is the created disposition name
	 */
	public void clickOnNewlyDispositionDetailsLink(String dispositionName) {
		int size = dispositionNameList.size();
		int flag = 0;
		for (int i = 0; i < size; i++) {
			if (dispositionNameList.get(i).getText().equals(dispositionName)) {
				withAction().moveToElement(dispositionNameList.get(i)).build().perform();
			}
			evaluateJavascript("arguments[0].click();", dispositionDetailsLinkList.get(i));
			flag = 1;
			if (flag == 1) {
				break;
			}
		}
	}

	/**
	 * Method checks the visibility of popup
	 */
	public boolean isAddNewDispositionPopupVisible() {
		return dispositionPopupHeader.isVisible();
	}

	/**
	 * selects the value from the Next Disposition By dropdown
	 * 
	 * @param nextDrpDownValue
	 *            value to be selected from the dropdown
	 */
	public void selectNextDispositionFromDropdown(String nextDrpDownValue) {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDropdown);
		nextDispositionByDropdown.selectByVisibleText(nextDrpDownValue);
	}

	/**
	 * Method checks visibility of newly added value in Next Disposition By dropdown
	 */
	public boolean isSelectedValueInNextDispositionByVisible(String expectedDrpDownValue) {
		evaluateJavascript("arguments[0].scrollIntoView(true);", nextDispositionByDropdown);
		getSelectedValueFromNextDropdown = nextDispositionByDropdown.getSelectedVisibleTextValue();
		if (getSelectedValueFromNextDropdown.trim().equals(expectedDrpDownValue)) {
			return true;
		}
		return false;
	}

	/**
	 * Enter random letters in Disposition Name
	 */
	public void enterDispositionName() {
		dispositionNameTextBox.type(RandomStringUtils.randomAlphabetic(6));
	}

	/**
	 * @return error message on not entering values in Disposition pop up
	 */
	public String getErrorMsgOnDispositionPopup() {
		withAction().moveToElement(errorMsgOnEmptyFields).build().perform();
		String expectedMEssage = errorMsgOnEmptyFields.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible()
				.getText().trim();
		return expectedMEssage;
	}

	/**
	 * Method enters random value in Disposition Code testbox
	 */
	public void enterTextInDispositionCodeTextBox() {
		dispositionCodeTextBox.type(RandomStringUtils.randomAlphanumeric(6));
	}

	/**
	 * Methods enters the previously copied Disposition code into new Disposition
	 * 
	 * @param copiedCode
	 *            is the code that is copied from existing Disposition code
	 */
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
		List<String> listOfLabels = new ArrayList<>();
		for (WebElementFacade labels : labelsOnDispositionPopup) {
			listOfLabels.add(labels.getText().trim());
		}
		return listOfLabels;
	}

	/**
	 * @return the boolean value if first radio btn of Action name is selected
	 */
	public String isFirstRadioBtnActionTabSelected() {
		return evaluateJavascript("return document.querySelector('#wfActionBSOCP-0').checked").toString();
	}

	/**
	 * Method checks if the Action name is present against the recipient name, if
	 * not, checks the next recipient, goes on until action name is found
	 */
	public void clickOnContinueRecipientTabHavingActionNames() {
		int recipientSize = recipientsRadioBtnList.size();
		recipientContinueBtn.click();
		int size = listOfActionTypeRadioBtns.size();
		while (size < 2) {
			evaluateJavascript("arguments[0].click();", recipientTab);
			for (int i = 1; i < recipientSize; i++) {
				evaluateJavascript("arguments[0].click();", listOfRecipientsRadioBtn.get(i));
				recipientContinueBtn.click();
				size = listOfActionTypeRadioBtns.size();
				if (size >= 2) {
					break;
				}
				evaluateJavascript("arguments[0].click();", recipientTab);
			}
		}
	}

	/**
	 * Checks if atleast one action name is present against the recipient
	 */
	public void clickOnContinueBtnOnRecipientTab() {
		int recipientSize = recipientsRadioBtnList.size();
		recipientContinueBtn.click();
		int size = listOfActionTypeRadioBtns.size();
		while (size < 1) {
			evaluateJavascript("arguments[0].click();", recipientTab);
			for (int i = 1; i < recipientSize; i++) {
				evaluateJavascript("arguments[0].click();", listOfRecipientsRadioBtn.get(i));
				recipientContinueBtn.click();
				size = listOfActionTypeRadioBtns.size();
				if (size >= 1) {
					break;
				}
				evaluateJavascript("arguments[0].click();", recipientTab);
			}
		}

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

	/**
	 * @param exiting
	 *            handoffName
	 * @param added
	 *            recipientName
	 * @returns the text value of the bread crumb present having handoff and
	 *          recipient name
	 */
	public boolean isRecipientAppendInBreadcrumbInRecipientTab(String handoffName, String recipientName) {
		return handOffOnBreadcrumb.getText().contains(handoffName)
				&& recipientOnBreadcrumb.getText().contains(recipientName);
	}

	public String getDefaultSelectedRecipientName() {
		return defaultRecipientName.getText().trim();
	}

	/**
	 * @return the visibility of continue and add recipient btn on the Recipient
	 *         Page
	 */
	public boolean isContinueAndAddRecipientOnRecipientTabVisible() {
		return recipientContinueBtn.isVisible() && addRecipientBtn.isVisible();
	}

	/**
	 * @returns the tab colour
	 */
	public String getRecipientTabColour() {
		withAction().moveToElement(recipientTab).build().perform();
		return recipientTab.getCssValue(backgroundColour);
	}

	/*
	 * Method clicks Continue btn on the Handoff page
	 */
	public void clickOnContinueBtnOnHandoffTab() {
		continueBtnOnHandoff.click();
	}

	/**
	 * @returns the visibility of recipient page
	 */
	public boolean isRecipientPageVisible() {
		return recipientPage.isVisible();
	}

	/**
	 * Method clicks on the radio button of handoff
	 * 
	 * @param expectedHandoff
	 */
	public void clickOnRadioBtnAgnstFetchedHandOff(String expectedHandOff) {
		int size = handoffTypeList.size();
		for (int i = 0; i < size; i++) {
			if (handoffTypeList.get(i).getText().equals(expectedHandOff)) {
				withAction().moveToElement(handoffTypeRadioBtnList.get(i)).build().perform();
				evaluateJavascript("arguments[0].click();", handoffTypeRadioBtnList.get(i));
			}
		}
	}

	/**
	 * Checks the visibility of the newly added handoff
	 * 
	 * @param handOffName
	 *            is the new handoff added
	 * @return
	 */
	public boolean isNewlyAddedHandOffVisible(String handOffName) {
		int size = listOfAddedHandOffs.size();
		for (index = 0; index < size; index++) {
			if (listOfAddedHandOffs.get(index).getText().equals(handOffName))
				return true;
		}
		return false;
	}

	/**
	 * @return the visibility of handoff
	 */
	public boolean isAddHandOffLabelVisible() {
		return addHandOffLabel.isVisible();
	}

	/**
	 * Method clicks on the close btn on the handoff popup
	 */
	public void clickOnCloseBtnOnEditPopup() {
		listOfControlsOnEditPopup.get(0).click();
	}

	/**
	 * @return visibility of edit hadn off popup
	 */
	public boolean isEditPopupVisible() {
		return editHandoffText.isVisible();
	}

	/**
	 * @return visibility of success message on saving handoff
	 */
	public boolean isSuccessMsgVisible() {
		return successMsg.isVisible();
	}

	/**
	 * 
	 * @return text value of success message of hand off save
	 */
	public String getSuccessMsgOnSave() {
		return successMsg.withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible().getText();
	}

	/**
	 * 
	 * @return the visibility of newly added handoff
	 */
	public boolean isNewlyEditHandoffVisible() {
		withAction().moveToElement(listOfDescriptions.get(index)).build().perform();
		listOfDescriptions.get(index).withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible();
		if (enterWorkflowName.equals(listOfDescriptions.get(index).getText().trim())) {
			return true;
		}
		return false;
	}

	/**
	 * Method clicks on the random edit links on Handoff page
	 */
	public void clickOnRandomEditLink() {
		int editLinkSize = listOfEditIcons.size();
		index = CommonMethods.getRandom(editLinkSize);
		while (index == editLinkSize) {
			index = CommonMethods.getRandom(editLinkSize);
		}
		evaluateJavascript("arguments[0].click();", listOfEditIcons.get(index));
	}

	/**
	 * returns list of text values of the label present on the edit pop on Handoff
	 * Page
	 */
	public List<String> getLabelsOnEditPopup() {
		List<String> listOfLabels = new ArrayList<>();
		for (WebElementFacade element : listOfLabelsOnEditPopup) {
			listOfLabels.add(element.getText().trim());
		}
		return listOfLabels;
	}

	/**
	 * 
	 * @return list of text values of the controls present on HandoffPage
	 */
	public List<String> getControlsOnEditPopup() {
		List<String> listOfControls = new ArrayList<>();
		for (WebElementFacade element : listOfControlsOnEditPopup) {
			listOfControls.add(element.getText().trim());
		}
		return listOfControls;
	}

	/**
	 * 
	 * @returns the text value of Workflow Description on the Edit/Add handoff popup
	 */
	public String getWorkflowDescriptionText() {
		workflowDescriptionText = evaluateJavascript("return document.querySelector('#addWorkflowDescription').value;")
				.toString();
		return workflowDescriptionText;
	}

	/**
	 * 
	 * @returns boolean value based on the values present on the Add/Edit handoff
	 *          popup
	 */
	public boolean areValuesPopulatedInTheControls() {
		String workflowNameText = evaluateJavascript("return document.querySelector('#addWorkflowName').value;")
				.toString();
		if (!(workflowNameText == null && workflowDescriptionText == null)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @returns the entered text value of Workflow Description on edit/Add handoff
	 *          popup
	 */
	public String editWorkflowDescription() {
		int randomNumber = listOfEditIcons.size();
		enterWorkflowName = getWorkflowDescriptionText().concat("_" + CommonMethods.getRandom(randomNumber));
		workflowDescriptionTextbox.type(enterWorkflowName);
		return enterWorkflowName;
	}

	/**
	 * 
	 * @return boolean value based on the visibility of worklfow title on handpff
	 *         page
	 */
	public boolean isWorkflowTitleVisible() {
		return workflowTitle.isVisible();
	}

	/**
	 * 
	 * @return boolean value based on the visibilty of hand off tab
	 */
	public boolean isHandoffTabVisible() {
		return handoffTab.isVisible();
	}

	/**
	 * 
	 * @return handoff tab colour in text
	 */
	public String getHandoffTabColor() {
		return handoffTab.getCssValue(backgroundColour);
	}

	/**
	 * 
	 * @return Action type tab colour in text
	 */
	public String getActionTypeTabColor() {
		return actionTypeTab.getCssValue(backgroundColour);
	}

	/**
	 * 
	 * @returns the list of text values of workflow tabs present on the Handoff page
	 */
	public List<String> getWorkflowTabs() {
		List<String> workflowTabValues = new ArrayList<>();
		for (WebElementFacade tabs : workflowTabs) {
			workflowTabValues.add(tabs.getText().trim());
		}
		return workflowTabValues;
	}

	/**
	 * 
	 * @returns the text present under Workflow Summary on Handoff Page
	 */
	public String getWorkflowSummaryLabel() {
		return workflowSummaryLabel.getText().trim();
	}

	/**
	 * 
	 * @returns the default handoff text value
	 */

	public String getDefaultHandoffName() {
		return defaultHandoffName.getText();
	}

	/**
	 * 
	 * @return the boolean value based on the visibility of Add Handoff btn
	 */
	public boolean isAddHandoffBtnVisible() {
		return addHandoffButton.isVisible();
	}

	/**
	 * 
	 * @return the boolean value based on the visibility of continue btn on Handoff
	 *         Page
	 */
	public boolean isStepFirstContinueBtnVisible() {
		return handOffContinueBtn.isVisible();
	}

	/**
	 * 
	 * @return the list of workflow header text value on HandoffPage
	 */
	public List<String> getWorkflowGridHeaders() {
		List<String> workflowHeaders = new ArrayList<>();
		int size = workflowGridHeaders.size();
		for (int i = 1; i < size; i++) {
			workflowHeaders.add(workflowGridHeaders.get(i).getText());
		}
		return workflowHeaders;
	}

	/**
	 * 
	 * @return the boolean value based on the visibility of edit btn on Handoff page
	 */
	public boolean isHandoffEditButtonVisible() {
		return handoffEditButton.isVisible();
	}

	/**
	 * 
	 * @return boolean value to check if the default radio btn on Handoff Page is
	 *         selected
	 */
	public String isDefaultRadioBtnSelected() {
		return evaluateJavascript("return document.querySelector('#workflowChoiceID-0').checked").toString();
	}

	/**
	 * 
	 * @return the boolean value based on the values present on the fields on Edit
	 *         Disposition popup
	 */
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

	/**
	 * Method click on the first Dispostion name
	 */
	public void clickOnEditLinkOnDispositionGrid() {
		evaluateJavascript("arguments[0].click();", dispositionEditLinkList.get(0));
	}

	/**
	 * Method clicks on the add recipient
	 */
	public void clickAddRecipientButton() {
		withAction().moveToElement(addRecipientButton).build().perform();
		evaluateJavascript("arguments[0].click();", addRecipientButton);
	}

	/**
	 * 
	 * @return the list of text values from Add/Edit Recipient popup
	 */
	public List<String> getListOfAddRecipientLabels() {
		List<String> listOfRecipientLabels = new ArrayList<String>();
		for (WebElementFacade element : listOfAddRecipientLabels) {
			listOfRecipientLabels.add(element.getText().trim());
		}
		return listOfRecipientLabels;
	}

	/**
	 * 
	 * @return the list of Recipient btn text values
	 */
	public List<String> getListOfAddRecipientButtons() {
		List<String> listOfRecipientButtons = new ArrayList<String>();
		for (WebElementFacade element : listOfAddRecipientButtons) {
			listOfRecipientButtons.add(element.getText().trim());
		}
		return listOfRecipientButtons;
	}

	/**
	 * Method clicks on close btn on the Add Recipient popup
	 */
	public void clickCloseButtonOnAddRecipient() {
		closeButtonOnAddRecipient.click();
	}

	/**
	 * 
	 * @param recipientName
	 *            is the newly added recipient name
	 * @return the boolean value based on the visibility of Recipient name present
	 */
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

	/**
	 * 
	 * @param listOfFields
	 * @return list of Controls text values on Add Action Popup on Action Page
	 */
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
		return dispositionTab.getCssValue(backgroundColour);
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
		evaluateJavascript("arguments[0].click();", dispositionDetailsLinkList.get(0));
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
		return expandedDispositionDetailsLink.isVisible();
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

	/**
	 * 
	 * @return Text value of updated By field on Recipient Page
	 */
	public String getUpdatedByFieldValue() {
		return recipientUpdatedByField.getText();
	}

	/**
	 * 
	 * @return Text value of Updated Date field on Recipient Page
	 */
	public String getUpdatedDateFieldValue() {
		return recipientUpdatedDateField.getText();
	}

	/**
	 * Method clicks on Respond Deadline on Add Disposition popup
	 */
	public void clickRespondDeadlineOnEditDispositionTypePopup() {
		respondDeadLineTxtBoxOnDispositionPopup.click();
	}

	/**
	 * 
	 * @return the text value of Respond Deadline text box
	 */
	public String enterAndGetRandomValueRespondDeadlineForEditDispositionTypePopup() {
		respondDeadLineTxtBoxOnDispositionPopup.type(RandomStringUtils.randomNumeric(1));
		return respondDeadLineTxtBoxOnDispositionPopup.getTextValue();
	}

	/**
	 * 
	 * @return the text value of Time Limit on Disposition page
	 */
	public String getMappedDispositionTimeLimitValueOnDispositionTypeGrid() {
		return mappedTimeLimitValueOnDispositionTypeGrid.get(0).getText();
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
				evaluateJavascript("arguments[0].click();", listOfActionTypeRadioBtns.get(index));
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

	public List<String> getActionTypeStatusListText() {
		List<String> statusList = new ArrayList<>();
		for (WebElementFacade element : actionTypeStatusList) {
			statusList.add(element.getText().trim());
		}
		return statusList;
	}

	public void clickWriteOffRadioBtn() {
		withAction().moveToElement(writeOffRadioBtn).build().perform();
		writeOffRadioBtn.click();
	}
}