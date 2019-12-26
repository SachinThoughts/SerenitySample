package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PhysicianNPIConfigPage extends PageObject {

	@FindBy(xpath = "//span[@class='Title' and text()='PRCM NPI CONFIGURATION']")
	private WebElementFacade pRCMNPIConfigTitle;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/descendant::a[1]")
	private WebElementFacade firstPhysicianConfigEditLink;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div[1]")
	private WebElementFacade firstPhysicianName;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div[2]")
	private WebElementFacade firstPhysicianNPI;

	@FindBy(xpath = "//*[@id='editPhysicianNPIPayors']/descendant::h3[contains(text(),'Edit Physician Payor List')]")
	private WebElementFacade editPhysicianPopUp;

	@FindBy(xpath = "//*[@id='editPayorSearch']/descendant::p[1]")
	private WebElementFacade totalPayorsDisabled;

	@FindBy(xpath = "//*[@id='editPayorSearch']/descendant::p[2]")
	private WebElementFacade totalEligiblePayors;

	@FindBy(xpath = "//*[@id='editPayorSearch']/descendant::ul[@class='list-table-body list-table-body-scroll payor-list remove-from-list']//descendant::div[2]")
	private List<WebElementFacade> listOfDisabledPayorsName;

	@FindBy(xpath = "//*[@id='editPayorSearch']/descendant::ul[@class='list-table-body list-table-body-scroll payor-list add-to-list']//descendant::div[2]")
	private List<WebElementFacade> listOfEligiblePayorsName;

	@FindBy(xpath = "//span[text()='Add All Payors']")
	private WebElementFacade addAllPayorsBtn;

	@FindBy(xpath = "//span[text()='Remove All Payors']")
	private WebElementFacade removeAllPayorsBtn;

	@FindBy(id = "txtSearchEligible")
	private WebElementFacade searchEligibleTxtBox;

	@FindBy(id = "txtSearchdisabled")
	private WebElementFacade searchDisabledTxtBox;

	@FindBy(xpath = "//*[@id='editPhysicianNPIPayors']/descendant::h3")
	private WebElementFacade popUpMsgAndPhysicianNameNPI;

	@FindBy(xpath = "//div[@id='generalPopUpMsg']/descendant::h3")
	private WebElementFacade saveConfigMsg;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div[4]")
	private WebElementFacade firstPhyTotalDisabledCount;

	@FindBy(xpath = "//ul[@class='list-table-header physician-list']/li")
	private List<WebElementFacade> listOfPhysicianColumnNames;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/descendant::a")
	private List<WebElementFacade> listOfEditBtnLink;

	@FindBy(xpath = "//ul[@class='pagination ng-scope']")
	private WebElementFacade paginationControl;

	@FindBy(xpath = "//div[contains(@class,'detail-header')]/child::p[@class='range-label top-pagination ng-binding'][1]")
	private WebElementFacade listOfPaginationHeader;

	@FindBy(xpath = "//div[contains(@class,'detail-header')]/child::p[@class='range-label top-pagination ng-binding'][2]")
	private WebElementFacade listOfPaginationFooter;

	@FindBy(xpath = "//div[contains(@class,'detail-header')]/h3")
	private WebElementFacade facilityPhysicianHeader;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']//div[4]")
	private List<WebElementFacade> listOfTotalPayorsDisabled;

	@FindBy(xpath = "//div[contains(@class,'search-header')]/h3[text()='Physician Search']")
	private WebElementFacade physicianSearchTitle;

	@FindBy(xpath = "//input[@placeholder='Search Physicians Name, NPI, etc.']")
	private WebElementFacade physicianSearchTxtField;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div")
	private List<WebElementFacade> listOfSearchedPhysicianInfo;

	private String cancelBtnJS = "$('#editPhysicianNPIPayors button.btn.btn-default')";

	private String saveBtnJS = "$('#btnSave')";

	String[] paginationValue, paginationValueWithFirstTextValue, paginationValueWithSecondTextValue;
	List<String> listOfPaginationValue = new ArrayList<>();

	/**
	 * This method is used to check whether 'PRCM NPI CONFIGURATION' text is visible
	 * This will throw an AssertionError if the element is not visible
	 */
	public void pRCMNPIConfigTitleShouldBeVisible() {
		pRCMNPIConfigTitle.shouldBeVisible();
	}

	/**
	 * This method is used to click on the Edit link of the first Physician. Before
	 * clicking it will explicitly wait for given time until the element is visible
	 */
	public void clickFirstPhysicianConfigEditLink() {
		firstPhysicianConfigEditLink.withTimeoutOf(Duration.ofSeconds(180)).waitUntilClickable().click();
	}

	/**
	 * This method checks visibility of the edit physician pop up
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isEditPhysicianPopUpVisible() {
		return editPhysicianPopUp.isVisible();
	}

	/**
	 * This method is used to get the text with count of Total payor disabled
	 * 
	 * @return the text of Total Payor Disabled
	 */
	public String getTotalPayorsDisabled() {
		return totalPayorsDisabled.getText();
	}

	/**
	 * This method is used to get the text with count of Total eligible payor
	 * 
	 * @return the text of Total Eligible Payor
	 */
	public String getTotalEligiblePayors() {
		return totalEligiblePayors.getText();
	}

	/**
	 * This method is used to get all the disabled payor names dispalyed on UI
	 * 
	 * @return the List of String values of disabled payor names
	 */
	public List<String> getListOfDisabledPayorsName() {
		List<String> listOfPayorNames = new ArrayList<>();
		for (WebElementFacade payorName : listOfDisabledPayorsName)
			listOfPayorNames.add(payorName.getText());

		return listOfPayorNames;
	}

	/**
	 * This method is used to get all the eligible payor names dispalyed on UI
	 * 
	 * @return the List of String values of eligible payor names
	 */
	public List<String> getListOfEligiblePayorsName() {
		List<String> listOfPayorNames = new ArrayList<>();
		for (WebElementFacade payorName : listOfEligiblePayorsName)
			listOfPayorNames.add(payorName.getText());

		return listOfPayorNames;
	}

	/**
	 * This method checks visibility of the Add All Payors button
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isAddAllPayorsBtnVisible() {
		return addAllPayorsBtn.isVisible();
	}

	/**
	 * This method checks visibility of the Remove All Payors button
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isRemoveAllPayorsBtnVisible() {
		return removeAllPayorsBtn.isVisible();
	}

	/**
	 * This method checks visibility of the Search Eligible Payors textbox
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isSearchEligibleTxtBoxVisible() {
		return searchEligibleTxtBox.isVisible();
	}

	/**
	 * This method checks visibility of the Search Disabled Payors textbox
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isSearchDisabledTxtBoxVisible() {
		return searchDisabledTxtBox.isVisible();
	}

	/**
	 * This method checks visibility of the Cancel button on the Edit PopUp using
	 * JavaScript
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isCancelBtnVisible() {
		return evaluateJavascript("return " + cancelBtnJS + ".text()").equals("Cancel");
	}

	/**
	 * This method checks visibility of the Save button on the Edit PopUp using
	 * JavaScript
	 * 
	 * @return the value true if the element is visible else return false
	 */
	public boolean isSaveBtnVisible() {
		return evaluateJavascript("return " + saveBtnJS + ".text()").equals("Save");
	}

	/**
	 * This method is used to get the name of the first Physician displayed on UI.
	 * Before getting the name it will explicitly wait for given time until the
	 * element is visible
	 * 
	 * @return the name of the physician
	 */
	public String getFirstPhysicianName() {
		return firstPhysicianName.withTimeoutOf(Duration.ofSeconds(180)).waitUntilVisible().getText();
	}

	/**
	 * This method is used to get the NPI of the first Physician displayed on UI.
	 * Before getting the NPI it will explicitly wait for given time until the
	 * element is visible
	 * 
	 * @return the NPI of the physician
	 */
	public String getFirstPhysicianNPI() {
		return firstPhysicianNPI.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	/**
	 * This method is used to get he message displayed on the edit pop up along with
	 * the Physician Name
	 * 
	 * @return the text displayed on pop up screen
	 */
	public String getPopUpMsgAndPhysicianNameNPI() {
		return popUpMsgAndPhysicianNameNPI.getText();
	}

	/**
	 * This method is used to type values in the search disabled payor text box
	 * 
	 * @param disabledPayor
	 *            the value to be typed in the searchDisabled text box
	 */
	public void enterSearchDisabledTxtBox(String disabledPayor) {
		searchDisabledTxtBox.type(disabledPayor);
	}

	/**
	 * This method is used get count of total payors disabled from the edit pop up
	 * The text visible on the UI consist of count with other text, hence count is
	 * extracted using split method
	 * 
	 * @return the total number of payors disabled
	 */
	public int getCountOfTotalPayorsDisabled() {
		String[] totalPayorDisabled = getTotalPayorsDisabled().split(": ", 0);
		return Integer.parseInt(totalPayorDisabled[1]);
	}

	/**
	 * This method is used to type values in the search eligible payor text box
	 * 
	 * @param eligiblePayor
	 *            the value to be typed in the searchEligible text box
	 */
	public void enterSearchEligibleTxtBox(String eligiblePayor) {
		searchEligibleTxtBox.type(eligiblePayor);
	}

	/**
	 * This method is used get count of total eligible payors from the edit pop up
	 * The text visible on the UI consist of count with other text, hence count is
	 * extracted using split method
	 * 
	 * @return the total number of eligible payors
	 */
	public int getCountOfTotalEligiblePayors() {
		String[] totalPayorEligible = getTotalEligiblePayors().split(": ", 0);
		return Integer.parseInt(totalPayorEligible[1]);
	}

	/**
	 * This method is used to click on the disabled payor name which is searched by
	 * user, after clicking the disabled payor it will move to the eligible payor
	 * section
	 * 
	 * @param searchedPayorName
	 *            the payor value to be searched on UI
	 */
	public void clickSearchedDisabledPayorsName(String searchedPayorName) {
		int index = 0;
		for (String payorName : getListOfDisabledPayorsName()) {
			if (payorName.equals(searchedPayorName)) {
				listOfDisabledPayorsName.get(index).click();
				break;
			}
			index++;
		}
	}

	/**
	 * This method is used to click on the eligible payor name which is searched by
	 * user, after clicking the eligible payor it will move to the disabled payor
	 * section
	 * 
	 * @param searchedPayorName
	 *            the payor value to be searched on UI
	 */
	public void clickSearchedEligiblePayorsName(String searchedPayorName) {
		int index = 0;
		for (String payorName : getListOfEligiblePayorsName()) {
			if (payorName.equals(searchedPayorName)) {
				listOfEligiblePayorsName.get(index).click();
				break;
			}
			index++;
		}
	}

	/**
	 * This method is used to click on the Add All Payor button which is displayed
	 * on the edit pop up
	 */
	public void clickAddAllPayorsBtn() {
		addAllPayorsBtn.click();
	}

	/**
	 * This method is used to click on the Remove All Payor button which is
	 * displayed on the edit pop up
	 */
	public void clickRemoveAllPayorsBtn() {
		removeAllPayorsBtn.click();
	}

	/**
	 * This method is used to get the message displayed after saving the physician's
	 * configuration
	 * 
	 * @return the text displayed on the alert
	 */
	public String getSaveConfigMsg() {
		return saveConfigMsg.getText();
	}

	/**
	 * This method is used to click on the Save Button on the edit pop up. For
	 * clicking JavaScript is used since the button position changes as per the
	 * screen resolution
	 */
	public void clickSaveBtn() {
		evaluateJavascript(saveBtnJS + ".click()");
	}

	/**
	 * This method is used to get the count of total payor disabled for the first
	 * physician displayed on the UI This method will wait for the angular requests
	 * to finish if any, later will explicitly wait for the given time until the
	 * counts are display on UI
	 * 
	 * @return the count of total payor disabled
	 */
	public int firstPhyTotalDisabledCount() {
		waitForAngularRequestsToFinish();
		return Integer.parseInt(
				firstPhyTotalDisabledCount.withTimeoutOf(Duration.ofSeconds(180)).waitUntilVisible().getText());
	}

	/**
	 * This method is used to get the first name of the first physician displayed on
	 * the UI. The first name and Last name both are displayed together hence to get
	 * only the first name split method is used.
	 * 
	 * @return the first name of the physician
	 */
	public String getFirstPhysiciansFirstName() {
		String[] physicianName = getFirstPhysicianName().split(", ", 0);
		return physicianName[1].trim();
	}

	/**
	 * This method is used to get all the column names displayed on the physician
	 * NPI configuration screen
	 * 
	 * @return the list of column names
	 */
	public List<String> getListOfPhysicianColumnNames() {
		List<String> listOfColumnNames = new ArrayList<>();
		for (WebElementFacade columnName : listOfPhysicianColumnNames)
			listOfColumnNames.add(columnName.getText());

		return listOfColumnNames;
	}

	/**
	 * This method is used to check the visibility of edit buttons against the
	 * physician name
	 * 
	 * @return the value true if the edit button is visible else return false
	 */
	public boolean isListOfEditBtnLinkEmpty() {
		firstPhysicianConfigEditLink.withTimeoutOf(Duration.ofSeconds(180)).waitUntilVisible();
		return listOfEditBtnLink.isEmpty();
	}

	/**
	 * This method is used to check the visibility of the pagination control
	 * 
	 * @return the value true if the pagination control is visible else return false
	 */
	public boolean isPaginationCtrlVisible() {
		return paginationControl.isVisible();
	}

	/**
	 * This method is used to get the text values 'Total Physicians' and 'Displaying
	 * Page' from the pagination header. Split method is used so that the required
	 * two text are extracted from the displayed text
	 * 
	 * @return two text values
	 */
	public List<String> getHeaderOfPagination() {
		paginationValue = listOfPaginationHeader.getText().split("\\|");
		paginationValueWithFirstTextValue = paginationValue[0].trim().split("\\s+");
		paginationValueWithSecondTextValue = paginationValue[1].trim().split("\\s+");
		listOfPaginationValue
				.add(paginationValueWithFirstTextValue[0].trim() + " " + paginationValueWithFirstTextValue[1].trim());
		listOfPaginationValue
				.add(paginationValueWithSecondTextValue[0].trim() + " " + paginationValueWithSecondTextValue[1].trim());
		return listOfPaginationValue;
	}

	/**
	 * This method is used to get the text values 'Total Physicians' and 'Displaying
	 * Page' from the pagination footer. Split method is used so that the required
	 * two text are extracted from the displayed text
	 * 
	 * @return two text values
	 */
	public List<String> getFooterOfPagination() {
		paginationValue = listOfPaginationFooter.getText().split("\\|");
		paginationValueWithFirstTextValue = paginationValue[0].trim().split("\\s+");
		paginationValueWithSecondTextValue = paginationValue[1].trim().split("\\s+");
		listOfPaginationValue
				.add(paginationValueWithFirstTextValue[0].trim() + " " + paginationValueWithFirstTextValue[1].trim());
		listOfPaginationValue
				.add(paginationValueWithSecondTextValue[0].trim() + " " + paginationValueWithSecondTextValue[1].trim());
		return listOfPaginationValue;
	}

	/**
	 * This method is used to get the text visible on the facility physician header
	 * 
	 * @return the text 'Facility Physician' header
	 */
	public String getFacilityPhysicianHeader() {
		return facilityPhysicianHeader.getText();
	}

	/**
	 * This method is used to get count of disabled payor for each physician
	 * 
	 * @return the list of all the disabled payor count
	 */
	public List<String> getCountOfTotalDisabledPayors() {
		List<String> listOfTotalPayors = new ArrayList<>();
		for (WebElementFacade totalPayors : listOfTotalPayorsDisabled)
			listOfTotalPayors.add(totalPayors.getText());
		return listOfTotalPayors;
	}

	/**
	 * This method checks visibility of the 'Physician Search' title
	 * 
	 * @return the value true if title is visible else return false
	 */
	public boolean isPhysicianSearchTitleVisible() {
		return physicianSearchTitle.isVisible();
	}

	/**
	 * This method is used to click on the Physician Search text box
	 */
	public void clickOnPhysicianSearchTxtField() {
		physicianSearchTxtField.click();
	}

	/**
	 * This method is used to type value in the Physician Search text box
	 * 
	 * @param physiciansName
	 *            the name of physician to be typed in the Physician Search text box
	 */
	public void enterPhysicianSearchTxtBox(String physiciansName) {
		physicianSearchTxtField.type(physiciansName);
	}

	/**
	 * This method is used to get the details of searched Physician like Name, NPI,
	 * Facility ID, count of payor disabled
	 * 
	 * @return details of the searched physician
	 */
	public List<String> getListofSearchedPhisicianInfo() {
		List<String> listOfphysicianInfo = new ArrayList<>();
		for (WebElementFacade physicianInfo : listOfSearchedPhysicianInfo)
			listOfphysicianInfo.add(evaluateJavascript("return arguments[0].innerText;", physicianInfo).toString());
		return listOfphysicianInfo;
	}

	/**
	 * This method is used to click on the Cancel Button on the edit pop up. For
	 * clicking JavaScript is used since the button position changes as per the
	 * screen resolution
	 */
	public void clickCancelBtn() {
		evaluateJavascript(cancelBtnJS + ".click()");
	}
}