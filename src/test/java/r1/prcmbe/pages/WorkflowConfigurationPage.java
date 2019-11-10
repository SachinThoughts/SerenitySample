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

	@FindBy(xpath = "//a[contains(@data-target,'#editRecipient')]")
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
	private WebElementFacade addDispositionPopupHeader;

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
	private WebElementFacade detailsLinkOnDisposition;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created Date']/following-sibling::span")
	private WebElementFacade createdDateField;

	@FindBy(xpath = "//*[@class='more-info workflowConfigdetailsInfo']//span[text()='Created By']/following-sibling::span")
	private WebElementFacade createdByField;
	
	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[@class='radio']//label")
	private List<WebElementFacade> listOfRecipientsRadioBtn;
	
	@FindBy(xpath = "//*[@id='dvRecipientDetails']//*[contains(@id,'sName')]")
	private List<WebElementFacade> listOfRecipientsName;
	
	@FindBy(xpath = "//div[@id='addNewAction']//div[not(contains(@class,'hidden'))]/label[contains(@class,'control-label')]")
	private List<WebElementFacade> actionPopUpControls;
	
	@FindBy(xpath = "//*[@id='addNewAction']//button[text()='Close']")
	private WebElementFacade closeBtnOnActionPopUp;
	
	@FindBy(xpath = "//*[@id='addNewAction']//button[text()='Save changes']")
	private WebElementFacade saveChangesBtnOnActionPopUp;
	
	@FindBy(xpath = "//*[@id='addNewAction']//*[@id='sopActionRequired']/..")
	private WebElementFacade requiredCheckBoxActionPopUp;
	
	@FindBy(id="addEditNewActionLabel")
	private WebElementFacade addActionPopUp;
	
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
	private WebElementFacade errorMsgForEmptyFieldsOnActionPopUp;

	public String getCreatedByFieldValue() {
		return createdByField.getText();
	}

	public String getCreatedDateFieldValue() {
		return createdDateField.getText();
	}

	public void clickOnDispositionDetailsLink() {
		withAction().moveToElement(detailsLinkOnDisposition).click().build().perform();
	}

	public boolean isAddNewDispositionPopupVisible() {
		return addDispositionPopupHeader.isVisible();
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
		List<String> headerList = new ArrayList<String>();
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
		List<String> columnList = new ArrayList<String>();
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

	public void clickOnSaveBtn() {
		listOfControlsOnEditPopup.get(1).click();
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
		List<String> listOfLabels = new ArrayList<String>();
		for (WebElementFacade element : listOfLabelsOnEditPopup) {
			listOfLabels.add(element.getText().trim());
		}
		return listOfLabels;
	}

	public List<String> getControlsOnEditPopup() {
		List<String> listOfControls = new ArrayList<String>();
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

	public List<String> getWorkflowTabs() {
		List<String> workflowTabValues = new ArrayList<String>();
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
		int size = actionPopUpControls.size();
		for (int i = 0; i < size; i++) {
			System.out.println(actionPopUpControls.get(i).getText());
			System.out.println(listOfFields.get(i));
			if (actionPopUpControls.get(i).getText().equals(listOfFields.get(i))) {
				count = count + 1;
			} else {
				listOfVal.add(listOfFields.get(i));
			}
		}
		if (closeBtnOnActionPopUp.getText().equals(listOfFields.get(8))) {
			count = count + 1;
		} else {
			listOfVal.add("Close");
		}
		if (saveChangesBtnOnActionPopUp.getText().equals(listOfFields.get(9))) {
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
	
	public void clickRequiredCheckBoxOnActionPopUp() {
		requiredCheckBoxActionPopUp.click();
	}

	public boolean isAddActionPopUpVisible() {
		return addActionPopUp.isVisible();
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
	
	public  String getActionCreatedDate() {
		return actionCreatedDate.getText();
	}
	
	public String getActionCreatedBy() {
		return actionCreatedBy.getText();
	}
	
	public String getActionUpdatedDate() {
		return actionUpdatedDate.getText();
	}
	
	public String getActionUpdatedBy() {
		//actionUpdatedBy.withTimeoutOf(Duration.ofSeconds(40)).waitUntilVisible();
		return actionUpdatedBy.getTextContent();
	}
	
	public String getErrorMsgOnActionPopup() {
		withAction().moveToElement(errorMsgForEmptyFieldsOnActionPopUp).build().perform();
		String expectedMEssage = errorMsgForEmptyFieldsOnActionPopUp.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible()
				.getText().trim();
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
}