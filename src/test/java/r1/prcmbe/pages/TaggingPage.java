package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.Keys;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import r1.commons.utilities.CommonMethods;

public class TaggingPage extends PageObject {

	@FindBy(xpath = "//h2[text()='Account Tags Configuration']")
	private WebElementFacade taggingPageTitle;

	@FindBy(xpath = "//input[@placeholder='Search Category Name']")
	private WebElementFacade searchCategoryNameTxtBox;

	@FindBy(xpath = "//a[text()='Tag Category']//parent::li[@class='active']")
	private WebElementFacade tagCategoryTab;

	@FindBy(xpath = "//a[text()='Tag Name']")
	private WebElementFacade tagNameTab;

	@FindBy(xpath = "//label[text()='Search Categories']")
	private WebElementFacade searchCategoryLabel;

	@FindBy(xpath = "//button[text()=' Add New Category']")
	private WebElementFacade addNewCategoryBtn;

	@FindBy(xpath = "//button[text()='Continue ']")
	private WebElementFacade continueBtn;

	@FindBy(xpath = "//ul[@class='list-table-header patient-categories no-counter']//li")
	private List<WebElementFacade> listOfColHeaders;

	@FindBy(xpath = "//strong[text()='No Tag Category Found']")
	private WebElementFacade alertMsg;

	@FindBy(xpath = "//div[@class='alert alert-lg alert-info']")
	private WebElementFacade alertSection;

	@FindBy(xpath = "//input[@placeholder='Enter Category Name']")
	private WebElementFacade categoryNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='Enter Tag Description']")
	private WebElementFacade tagDescriptionTxtBox;

	@FindBy(xpath = "//select[@class='form-control']")
	private WebElementFacade applicationDrpdwn;

	@FindBy(xpath = "//span[@class='slider round']")
	private List<WebElementFacade> applicationSlideBar;

	@FindBy(xpath = "//button[@class='btn btn-link editRow']//i[@class='fa fa-save fa-1-5x']")
	private WebElementFacade saveBtn;

	@FindBy(xpath = "//span[text()='Category Name should not more than 100 characters' or text()='Category Description should not more than 500 characters']")
	private WebElementFacade invalidCategoryNameValidationMsg;

	@FindBy(xpath = "//*[@id='cmHandoffHIMGroup']//div[@class='radio']//label")
	// *[@id='cmHandoffHIMGroup']/li//div[1]//label
	private List<WebElementFacade> categoryNameList;

	@FindBy(xpath = "//*[@id='cmHandoffHIMGroup']/li//div[2]//span")
	private List<WebElementFacade> categoryDescList;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElementFacade successMsg;

	@FindBy(xpath = "//*[@id='cmHandoffHIMGroup']/li//div[@class='radio']//label[starts-with(text(),'Automation')]//ancestor::li//input[@type='radio']/following-sibling::label")
	private List<WebElementFacade> automationRadioBtn;

	@FindBy(xpath = "//*[@id='cmHandoffHIMGroup']/li//label[starts-with(text(),'Automation')]//ancestor::li//span[text()='Edit']")
	private List<WebElementFacade> automationEditLinks;

	@FindBy(xpath = "//button[text()=' Add New Tag']")
	private WebElementFacade addNewTagBtn;

	@FindBy(xpath = "//strong[text()='Selected Category']")
	private WebElementFacade selectedCategoryLbl;

	@FindBy(xpath = "//*[@class='breadcrumb defect-summary']//li//h3")
	private WebElementFacade selectedCategoryOnBreadCrumb;

	@FindBy(xpath = "//strong[text()='No Tag Found']")
	private WebElementFacade noTagAlertMsg;

	@FindBy(xpath = "//*[@id='cmHandoffHIMGroup']//span[starts-with(text(),'Automation')]")
	private List<WebElementFacade> listOfAddedTagName;

	@FindBy(xpath = "//input[@placeholder='Enter Tag Name']")
	private WebElementFacade tagNameTxtBox;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElementFacade searchFacility;

	@FindBy(xpath = "//*[@id='divfacilityCode0']//div[@class='fs-dropdown']//div[@class='fs-option g0']")
	private WebElementFacade searchedFacilityCode;

	@FindBy(xpath = "//*[@id='divfacilityCode0']//div[text()='Select some options']")
	private WebElementFacade facilityDrpdwn;

	@FindBy(xpath = "//*[@data-target='#Tag']//span[text()='Add Tag']")
	private WebElementFacade tagNameLinkAtAccInfoPge;

	@FindBy(xpath = "//a[@data-target='#Tag']//span[text()='Edit Tag']")
	private WebElementFacade editTagLink;

	@FindBy(xpath = "//span[@class='tag-name tag label label-info']//span")
	private WebElementFacade removeTagBtn;

	@FindBy(xpath = "//*[@id='TagCategory']//option")
	private List<WebElementFacade> tagCategoryDrpdwnOptions;

	@FindBy(id= "TagCategory")
	private WebElementFacade tagCategoryDrpdwn;

	@FindBy(xpath = "//*[@id='TagName']//option")
	private List<WebElementFacade> tagNameDrpdwnOptions;

	@FindBy(id = "TagName")
	private WebElementFacade tagNameDrpdwn;

	@FindBy(id = "TagNotes")
	private WebElementFacade tagNotesTxtBox;

	@FindBy(id = "tagSave")
	private WebElementFacade saveTagBtn;

	@FindBy(xpath = "//div[@class='modal-body text-center alert alert-success']/h3[text()='Account Tag removed successfully.']")
	private WebElementFacade accRmvdAlrtMsg;

	@FindBy(xpath = "//h3[text()='Account Tag saved successfully.']")
	private WebElementFacade accAddedAlrtMsg;

	@FindBy(xpath = "//span[@class='tag-name tag label label-info' or @class='tag-name  tag label label-info']")
	private WebElementFacade tagNameOnAccInfo;

	@FindBy(xpath = "//h3[text()='Claims & Remittances ']")
	private WebElementFacade claimAndRemittanceSection;

	@FindBy(xpath = "//*[@id='generalTags']//li[position()=1]//table[@class='table']//child::th")
	private List<WebElementFacade> listOfTagHeadersUnderHistorySection;

	@FindBy(xpath = "//*[@id='generalTags']//li[position()=1]//table[@class='table']//child::td")
	private List<WebElementFacade> listOfAddedTagDetails;

	@FindBy(xpath = "//*[@class='alert alert-info']//span")
	private WebElementFacade alertMsgOnAddAcTagPopup;

	@FindBy(xpath = "//*[@class='alert alert-info']//span[text()='  Adding Tag on unclassified account is not allowed']")
	private WebElementFacade alertMsgOnTagPopupForUnclassifiedAcc;

	@FindBy(xpath = "//*[@id='main']//h3[text()='Mass Update']")
	private WebElementFacade massUpdatePageTitle;

	@FindBy(xpath = "//label[@for='accountOption01a']")
	private WebElementFacade professionalRadioBtn;

	@FindBy(xpath = "//label[@for='muOption01a']")
	private WebElementFacade manualEntryRadioBtn;

	@FindBy(xpath = "(//div[@class='form-group']//textarea)[1]")
	private WebElementFacade manualEntryTextBox;

	@FindBy(xpath = "//label[@for='massUpdateRequestAccountTagging']")
	private WebElementFacade massUpdateReqAccTaggingRadioBtn;

	@FindBy(xpath = "//label[text()='Add']")
	private WebElementFacade massUpdateAddTagRadioBtn;

	@FindBy(id = "tagCategory")
	private WebElementFacade tagCategoryDrpdwnOnMassUpdateScrn;

	@FindBy(xpath = "//*[@id='tagCategory']/option")
	private List<WebElementFacade> tagCategoryDrpdwnOptnsOnMUScrn;

	@FindBy(id = "tagName")
	private WebElementFacade tagNameDrpdwnOnMassUpdateScrn;

	@FindBy(xpath = "//*[@id='tagName']/option")
	private List<WebElementFacade> tagNameDrpdwnOptnsOnMassUpdateScrn;

	@FindBy(id = "massUpdateNotes")
	private WebElementFacade massUpdateNotes;

	@FindBy(id = "msg_info")
	private WebElementFacade alertMsgOnMassUpdateScrn;

	@FindBy(id = "dnn_ctr1025_LocationChooser_ddlLocation")
	private WebElementFacade facilityDrpdwnOnMUScreen;

	@FindBy(xpath = "//*[@id='msg_info']")
	private WebElementFacade successMsgOnMU;

	@FindBy(xpath = "//label[text()='Remove']")
	private WebElementFacade removeRadioBtn;

	int index, randomNumber;
	String removeTagBtnJs = "document.querySelector('#dnn_ctr1552_TaskPanel_spanTask > div.container > div.row > div.col-lg-9 > div:nth-child(3) > div.row > div.col-lg-3.hidden-print > ol > li > span > span').click()";
	private String scrollToElementJs = "arguments[0].scrollIntoView(true);";
	String tagCategory, tagName;

	/**
	 * @return Tag Configuration Page is Visible
	 */
	public boolean isTagConfigPageVisible() {
		return taggingPageTitle.isVisible();
	}

	/**
	 * @return True if Search category name textbox visible on UI
	 */
	public boolean isSearchCategoryNameTxtBoxVisible() {
		return searchCategoryNameTxtBox.isVisible();
	}

	/**
	 * @return Visibility of Tag category Tab
	 */
	public boolean isTagCategoryTabVisible() {
		return tagCategoryTab.isVisible();
	}

	/**
	 * @return Visibility of Tag Name Tab visibility
	 */
	public boolean isTagNameTabVisible() {
		return tagNameTab.isVisible();
	}

	/**
	 * @return Visibility of Search Category Label visibility
	 */
	public boolean isSearchCategoryLabelVisible() {
		return searchCategoryLabel.isVisible();
	}

	/**
	 * @return AddNew Category Button visibility
	 */
	public boolean isAddNewCategoryBtnVisible() {
		return addNewCategoryBtn.isVisible();
	}

	/**
	 * @return Visibility of continue button
	 */
	public boolean isContinueBtnVisible() {
		return continueBtn.isVisible();
	}

	/**
	 * @return List of column headers of tag Category tab
	 */
	public List<String> getListOfColHeaders() {
		waitForAngularRequestsToFinish();
		List<String> colHeaderList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfColHeaders) {
			colHeaderList.add(listOfNames.getText().trim());
			colHeaderList.removeAll(Arrays.asList(null, ""));
		}
		return colHeaderList;
	}

	/**
	 * Entering Random alphabets in search category name text box
	 */
	public void enterRandomTxtInSearchCategoryNameTxtbox() {
		searchCategoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(6));
	}

	/**
	 * @return returns alert message text when searching invalid search category
	 *         name
	 */
	public String getAlertMsg() {
		return alertMsg.getText();
	}

	/**
	 * @return returns background color of alert section
	 */
	public String getAlertSectionColor() {
		return alertSection.getCssValue("background-color");
	}

	/**
	 * Clicking on Add new Category Button
	 */
	public void clickAddNewCategoryBtn() {
		waitForAngularRequestsToFinish();
		addNewCategoryBtn.click();
	}

	/**
	 * Entering More than 100 alphabets in category name text box
	 */
	public void enterMreThnHundredCharsInCategoryNameTxtBox() {
		categoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(105));
	}

	/**
	 * Entering up to 500 characters in tag description text box
	 */
	public void enterUptoFiveHundredCharsInTagDiscTxtBox() {
		tagDescriptionTxtBox.type(RandomStringUtils.randomAlphabetic(500));
	}

	/**
	 * selecting application name from drop down
	 * @param Passing parameter from feature file
	 */
	public void selectApplication(String applicationName) {
		applicationDrpdwn.selectByVisibleText(applicationName);
	}

	/**
	 * Clicking on Active slide bar through java script
	 */
	public void clickActiveSlider() {
		evaluateJavascript("arguments[0].click();", applicationSlideBar.get(0));
	}

	/**
	 * clicking on save button
	 */
	public void clickOnSaveBtn() {
		waitForAngularRequestsToFinish();
		saveBtn.click();
	}

	/**
	 * @return Text of validation message when user entering invalid category name
	 */
	public String getCategoryNameValidationMsg() {
		return invalidCategoryNameValidationMsg.getText();
	}

	/**
	 * Entering random up to 100 characters in category name text field
	 */
	public void enterUptoHundredCharsInCategoryNameTxtBox() {
		categoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(100));
	}

	/**
	 * Entering random more than 500 characters in category description text field
	 */
	public void enterMoreThnFiveHundredCharsInTagDiscTxtBox() {
		tagDescriptionTxtBox.type(RandomStringUtils.randomAlphabetic(505));
	}

	/**
	 * Entering valid category name
	 * @param Passing through feature file
	 */
	public void enterTxtInCategoryNameTxtBox(String categoryName) {
		categoryNameTxtBox.type(categoryName);
	}

	/**
	 * Entering valid category description
	 * @param Passing through feature file
	 */
	public void enterTxtInTagDescTxtBox(String tagDesc) {
		tagDescriptionTxtBox.type(tagDesc);
	}

	/**
	 * @return List of category name present on UI
	 */
	public List<String> getlistOfCategoryName() {
		List<String> listOfCategories = new ArrayList<>();
		for (WebElementFacade categoryName : categoryNameList) {
			listOfCategories.add(categoryName.getText());
		}
		return listOfCategories;
	}

	/**
	 * @return List of category Description present on UI
	 */
	public List<String> getlistOfCategoryDesc() {
		List<String> listOfCategoriesDesc = new ArrayList<>();
		for (WebElementFacade categoryDesc : categoryDescList) {
			listOfCategoriesDesc.add(categoryDesc.getText());
		}
		return listOfCategoriesDesc;
	}

	/**
	 * Clicking on save button
	 * @return Text of Success Message from UI
	 */
	public String clickOnSaveAndGetSuccessMsg() {
		saveBtn.click();
		return successMsg.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	/**
	 * Taking list of radio button which have label as automation(Which is added by
	 * automation script) Clicking on Radio button which label has started with Automation
	 *  Clicking on corresponding edit link of selected radio button
	 */
	public void clickEditLink() {
		waitForAngularRequestsToFinish();
		index = automationRadioBtn.size() - 1;
		evaluateJavascript("arguments[0].click();", automationRadioBtn.get(index));
		evaluateJavascript("arguments[0].scrollIntoView(true);", automationEditLinks.get(index));
		evaluateJavascript("arguments[0].click();", automationEditLinks.get(index));
	}

	/**
	 * Clicking on Active Slide bar while editing tag
	 */
	public void clickOnActiveSlideBarEdit() {
		evaluateJavascript("arguments[0].click();", applicationSlideBar.get(index));
	}

	/**
	 * Clicking on any radio button which has label as automation
	 * @return Category name of selected radio button
	 */
	public String clickAndGetAnyRadioBtnTxt() {
		index = (automationRadioBtn.size()) - 1;
		randomNumber = CommonMethods.getRandom(index);
		evaluateJavascript("arguments[0].click();", automationRadioBtn.get(randomNumber));
		return categoryNameList.get(randomNumber).getText();
	}

	/**
	 * Clicking on continue button
	 */
	public void clickOnContinueBtn() {
		continueBtn.click();
	}

	/**
	 * @return Add new tag button visibility
	 */
	public boolean isAddNewTagBtnVisible() {
		return addNewTagBtn.isVisible();
	}

	/**
	 * @return Selected category label visibility
	 */
	public boolean isSelectedCategoryLblVisible() {
		return selectedCategoryLbl.isVisible();
	}

	/**
	 * @return Category name text from bread crumb
	 */
	public String getCategoryNameFromBreadCrumb() {
		return selectedCategoryOnBreadCrumb.getText();
	}

	/**
	 * Clicking on Add new tag button
	 */
	public void clickOnAddNewTagBtn() {
		addNewTagBtn.click();
	}

	/**
	 * @return Visibility alert message
	 */
	public boolean isNoTagAlertMsgVisible() {
		return noTagAlertMsg.isVisible();
	}

	/**
	 * @return List of added tag name from UI
	 */
	public List<String> getlistOfTagName() {
		List<String> listOfTagName = new ArrayList<>();
		for (WebElementFacade tagName : listOfAddedTagName) {
			listOfTagName.add(tagName.getText());
		}
		return listOfTagName;
	}

	/**
	 * Entering Valid Tag name 
	 * @param tagName coming from feature file
	 */
	public void enterTxtInTagNameTxtBox(String tagName) {
		tagNameTxtBox.type(tagName);
	}

	/**
	 * Clicking on facility drop down
	 */
	public void clickOnFacilityDrpdwn() {
		facilityDrpdwn.click();
	}

	/**
	 * Clear search facility text field Entering facility name
	 * @param searchValue from feature file
	 * clicking on facility code
	 */
	public void selectMultipleFacility(String searchValue) {
		searchFacility.clear();
		searchFacility.type(searchValue);
		searchedFacilityCode.click();
	}

	/**
	 *Clicking on Add tag name link at Account info page if edit tag link is visible on page first will remove tag than will add
 		tags */
	public void clickOnAddTagNameLinkAtAccInfoPge() {
		if (editTagLink.isVisible()) {
			evaluateJavascript(removeTagBtnJs);
			accRmvdAlrtMsg.withTimeoutOf(Duration.ofSeconds(20)).waitUntilNotVisible().getText();
		}
		tagNameLinkAtAccInfoPge.click();
	}

	/**
	 * selecting any random value from tag category drop down
	 * @return selected drop down option value
	 */
	public String selectAndGetAnyTagCategory() {
		index = CommonMethods.getRandom(tagCategoryDrpdwnOptions.size() - 1) + 1;
		return tagCategoryDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}
	/**
	 * selecting any random value from tag name drop down
	 * @return selected drop down option value
	 */
	public String selectAndGetAnyTagName() {
		index = CommonMethods.getRandom(tagNameDrpdwnOptions.size() - 1) + 1;
		return tagNameDrpdwn.selectByIndex(index).getSelectedVisibleTextValue();
	}

	/**
	 * Entering Tag notes
	 * @param tagNote passing from feature file
	 */
	public void enterTagNote(String tagNote) {
		tagNotesTxtBox.type(tagNote);
	}

	/**
	 * Clicking on Save button
	 * @return Text of Alert message after adding Tag on Acc info page
	 */
	public String saveTagAndGetTagAddedSuccessMsg() {
		saveTagBtn.click();
		return accAddedAlrtMsg.getText();
	}

	/**
	 * Clicking on save button after editing
	 */
	public void clickOnSaveTagBtn() {
		saveTagBtn.click();
	}

	/**
	 * @return Text of added tag name under title tag
	 */
	public String getAddedTagNameOnAccInfo() {
		return tagNameOnAccInfo.getText();
	}

	/**
	 * Scrolling till claim and remittance section to view tag history view section
	 */
	public void scrollToViewTagHistorySection() {
		evaluateJavascript(scrollToElementJs, claimAndRemittanceSection);
	}

	/**
	 * @return List of newly added tag headers under tag history section
	 */
	public List<String> getlistOfTagHeadersUnderHistorySection() {
		List<String> listOfTagHeaders = new ArrayList<>();
		for (WebElementFacade tagHeaders : listOfTagHeadersUnderHistorySection) {
			listOfTagHeaders.add(tagHeaders.getText());
		}
		return listOfTagHeaders;
	}

	/**
	 * @return List of added tag details under tag history section
	 */
	public List<String> getlistOfAddedTagDetailsUnderHistorySection() {
		List<String> listOfTagDetails = new ArrayList<>();
		for (int i = 1; i <= listOfAddedTagDetails.size(); i++) {
			listOfTagDetails.add(listOfAddedTagDetails.get(0).getText());
			listOfTagDetails.add(listOfAddedTagDetails.get(1).getText());
			listOfTagDetails.add(listOfAddedTagDetails.get(2).getText());
			listOfTagDetails.add(listOfAddedTagDetails.get(3).getText().substring(13, 18).trim());
			listOfTagDetails.add(listOfAddedTagDetails.get(4).getText());
			listOfTagDetails.add(listOfAddedTagDetails.get(5).getText());
		}
		return listOfTagDetails;
	}

	/**
	 * Clicking on Edit tag link if account doesn't have already added tag so first method will add tag than method will click on edit
	 */
	public void clickEditTagLinkAtAccInfoPge() {
		if (tagNameLinkAtAccInfoPge.isVisible()) {
			tagNameLinkAtAccInfoPge.click();
			tagCategory = selectAndGetAnyTagCategory();
			tagName = selectAndGetAnyTagName();
			saveTagBtn.click();
			accAddedAlrtMsg.withTimeoutOf(Duration.ofSeconds(20)).waitUntilNotVisible().getText();
		}
		editTagLink.click();
	}

	public boolean isAlertMsgOnDuplicateTagVisible() {
		return alertMsgOnAddAcTagPopup.isVisible();
	}

	/**
	 * @return text of Account add alert message after editing
	 */
	public String getaccAddedAlrtMsgAfterEdit() {
		return accAddedAlrtMsg.getText();
	}

	public String getAlertMsgOnTagPopupForUnclassifiedAcc() {
		return alertMsgOnTagPopupForUnclassifiedAcc.getText();
	}

	public boolean isSaveChangesBtnDisabled() {
		return saveTagBtn.isDisabled();
	}

	public void massUpdateScreenShouldBeVisible() {
		massUpdatePageTitle.shouldBeVisible();
	}

	public void selectProfessionalRadioBtn() {
		professionalRadioBtn.click();
	}

	public void selectManualEntryRadioBtn() {
		manualEntryRadioBtn.click();
	}

	public void enterInvcNumInManualEntryTxtbxOnMUScrn(String invoiceNumber) {
		String selectedFacility = facilityDrpdwnOnMUScreen.getSelectedVisibleTextValue().substring(0, 4)
				+ invoiceNumber;
		evaluateJavascript("document.querySelector('#muManualEntry').value='" + selectedFacility + "';",
				manualEntryTextBox);
	}

	public void selectMUReqAccountTaggingRadioBtn() {
		massUpdateReqAccTaggingRadioBtn.click();
	}

	public void selectAddRadioBtnOnMU() {
		massUpdateAddTagRadioBtn.click();
		tagCategoryDrpdwnOnMassUpdateScrn.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
	}

	public List<String> getTagCategoryDrpDwnValues() {
		List<String> listOfTagCategoryDropDwnValues;
		listOfTagCategoryDropDwnValues = tagCategoryDrpdwnOnMassUpdateScrn.getSelectOptions();
		return listOfTagCategoryDropDwnValues;
	}

	public void selectAccTagCategoryOnMU() {
		int index1 = CommonMethods.getRandom(tagCategoryDrpdwnOptnsOnMUScrn.size());
		CommonMethods.getRandom(tagCategoryDrpdwnOptnsOnMUScrn.size());
		while (index1 == 0) {
			index1 = CommonMethods.getRandom(tagCategoryDrpdwnOptnsOnMUScrn.size());
		}
		evaluateJavascript(scrollToElementJs, tagCategoryDrpdwnOnMassUpdateScrn);

		for (int i = 0; i < index1; i++) {
			withAction().moveToElement(tagCategoryDrpdwnOnMassUpdateScrn).click().sendKeys(Keys.DOWN).sendKeys(Keys.TAB)
					.build().perform();
		}
	}

	public List<String> getTagNameDrpDwnValues() {
		List<String> listOfTagNameDropDwnValues;
		listOfTagNameDropDwnValues = tagNameDrpdwnOnMassUpdateScrn.getSelectOptions();
		return listOfTagNameDropDwnValues;
	}

	public void selectTagNameOnMU() {
		tagNameDrpdwnOnMassUpdateScrn.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
		withAction().moveToElement(tagNameDrpdwnOnMassUpdateScrn).click().build().perform();

		int index1 = CommonMethods.getRandom(tagNameDrpdwnOptnsOnMassUpdateScrn.size());
		while (index1 == 0) {
			index1 = CommonMethods.getRandom(tagNameDrpdwnOptnsOnMassUpdateScrn.size());
		}
		evaluateJavascript("document.querySelector(\"#tagName > option:nth-child(" + index1 + ")\").selected=true");
	}

	public boolean isAddCategoryDrpdwnVisibleOnMU() {
		return tagCategoryDrpdwnOnMassUpdateScrn.isVisible();
	}

	public boolean isAddTagDrpdwnVisibleOnMU() {
		return tagNameDrpdwnOnMassUpdateScrn.isVisible();
	}

	public void clickOnSaveBtnOnMU() {
		evaluateJavascript("document.querySelector('#muSumbit').click()");
	}

	public void enterNotesOnMU(String notes) {
		evaluateJavascript("document.querySelector('#massUpdateNotes').value='" + notes + "';", manualEntryTextBox);
	}

	public String getSuccessMsgOnMU() {
		return successMsgOnMU.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public void selectRemoveRadioBtnOnMU() {
		removeRadioBtn.click();
	}
}