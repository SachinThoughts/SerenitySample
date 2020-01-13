package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FacilityGroupConfigurationPage extends PageObject {

	List<String> ListOfPageControls = new ArrayList<>();
	int index;
	String facilityGroupName;

	@FindBy(xpath = "//*[@id='lstFacilityGroup']/li/div[1]/span")
	private List<WebElementFacade> facilityGroupList;

	@FindBy(xpath = "//h3[text()='Facility Group Configuration']")
	private WebElementFacade facilityGroupConfigPageHeader;

	@FindBy(xpath = "//ul[@class='list-table-header FacilityGroup-admin no-counter']/li/preceding-sibling::li")
	private List<WebElementFacade> listOfTableHeader;

	@FindBy(xpath = "//button[text()='Add New Facility Group']")
	private List<WebElementFacade> listOfAddFacilityBtn;

	@FindBy(xpath = "//a[@class='btn btn-link lnkEditFacilityGroup']")
	private List<WebElementFacade> listOfEditBtns;

	@FindBy(xpath = "//li[text()='Facilities']/parent::ul/parent::div//li/div[count(//li[text()='Facilities'])+1]")
	private List<WebElementFacade> listOfFacilities;

	@FindBy(id = "lblModalName")
	private WebElementFacade facilityGrpModalWindow;

	@FindBy(id = "btnAddFacility")
	private WebElementFacade addBtnOnAddNewPopup;

	@FindBy(id = "txtAssignFacility")
	private WebElementFacade facilityCodeTxtBox;

	@FindBy(xpath = "//label[@class='control-label']")
	private List<WebElementFacade> listOfLabels;

	@FindBy(xpath = "//*[@id='addFacilityGroup']//b")
	private WebElementFacade physicianCheckboxText;

	@FindBy(id = "btnSave")
	private WebElementFacade saveBtn;

	@FindBy(xpath = "(//button[@onclick='ShowConfirmYesNo();']/span)[1]")
	private WebElementFacade closeBtn;

	@FindBy(id = "chkIsPRCMEnabled")
	private WebElementFacade physicianCheckbox;

	@FindBy(id = "txtFacilityGroupName")
	private WebElementFacade facilityGrpNameOnPopup;

	@FindBy(xpath = "//*[@class='tt-suggestions']/div")
	private List<WebElementFacade> facilitySearchSuggestion;

	/**
	 * Method clicks on the facility code from suggestive dropdown
	 * 
	 * @param code
	 *            is passed from feature file
	 */
	public void clickOnFacilityCodeFromSearchDropdown(String code) {
		int size = facilitySearchSuggestion.size();
		for (int i = 0; i < size; i++) {
			if (facilitySearchSuggestion.get(i).getText().contains(code)) {
				evaluateJavascript("arguments[0].click();", facilitySearchSuggestion.get(i));
				break;
			}
		}
	}

	/**
	 * Method enters facility group name in Add/Edit popup
	 * 
	 * @param facilityGrpName
	 *            is passed from feature file
	 */
	public void enterFacilityGrpNameInTxtBox(String facilityGrpName) {
		facilityGrpNameOnPopup.type(facilityGrpName.concat(RandomStringUtils.randomAlphabetic(3)));
	}

	/**
	 * 
	 * @return the text values of all the controls present on the Add/edit Facility
	 *         Group Config page
	 */
	public List<String> getAllPageControls() {
		for (WebElementFacade pageControls : listOfLabels) {
			ListOfPageControls.add(pageControls.getText().trim());
		}
		ListOfPageControls.add(physicianCheckboxText.getText().trim());
		ListOfPageControls.add(closeBtn.getText().trim());
		ListOfPageControls.add(saveBtn.getText().trim());
		ListOfPageControls.add(addBtnOnAddNewPopup.getText().trim());
		return ListOfPageControls;
	}

	public String getPRCMFacility() {
		String pRCMFacility = null;
		for (WebElementFacade facility : facilityGroupList) {
			if (facility.getText().trim().contains("PRCM")) {
				pRCMFacility = facility.getText().trim();
			}
		}
		return pRCMFacility;
	}

	/**
	 * Method types the Facility code in the Facility code textbox
	 * 
	 * @param code
	 *            is passed from the feature file
	 */
	public void enterFacilityCodeInTxtBox(String code) {
		facilityCodeTxtBox.type(code);
	}

	/**
	 * 
	 * @return boolean value based on whether Add New Btn is enabled
	 */
	public boolean isAddBtnOnAddNewPopupEnabled() {
		if (addBtnOnAddNewPopup.getAttribute("disabled") == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return boolean value based on the visibility of AddNew Facility Config popup
	 */
	public boolean isAddNewFacilityGroupPopupVisible() {
		return facilityGrpModalWindow.isVisible();
	}

	/**
	 * Method clicks on Add Facility Btn
	 */
	public void clickOnAddFacilityBtn() {
		listOfAddFacilityBtn.get(0).click();
	}

	/**
	 * Method checks that Facility Group Head is present
	 */
	public void isFacilityGrpConfigHeaderVisible() {
		facilityGroupConfigPageHeader.shouldBeVisible();
	}

	/**
	 * 
	 * @return the text value of headers present on the facility group config page
	 */
	public List<String> getTableGridHeaders() {
		List<String> getHeaders = new ArrayList<>();
		for (WebElementFacade headerElement : listOfTableHeader) {
			getHeaders.add(headerElement.getText().trim());
		}
		return getHeaders;
	}

	/**
	 * Method checks the presence of Facility Group Name
	 * 
	 * @param expectedFacilityGroupName
	 *            passed from featuref lile
	 * @returns the boolean based on the presence of facility group name
	 */
	public boolean isFacilityGroupNamePresent(String expectedFacilityGroupName) {
		List<String> getListOfFacilityGroup = new ArrayList<>();
		for (WebElementFacade facilityGrp : facilityGroupList) {
			getListOfFacilityGroup.add(facilityGrp.getText());
		}
		return getListOfFacilityGroup.contains(expectedFacilityGroupName);
	}

	/**
	 * Method checks the presence of facility code
	 * 
	 * @param expectedFacility
	 *            is passed from feature
	 * @return the boolean value based on the presence of fcilitycode
	 */
	public boolean isExpectedFacilityPresent(String expectedFacility) {
		List<String> getFacilities = new ArrayList<>();
		for (WebElementFacade facility : listOfFacilities) {
			getFacilities.add(facility.getText());
		}
		return getFacilities.contains(expectedFacility);
	}

	/**
	 * 
	 * @return the boolean based on the presence of Add facility button
	 */
	public boolean areAddFAcilityBtnPresents() {
		int size = listOfAddFacilityBtn.size();
		for (int i = 0; i < size; i++) {
			if (listOfAddFacilityBtn.get(i).isVisible()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return the boolean value based on the presence of edit button on the page
	 */
	public boolean isEditBtnPresent() {
		return listOfEditBtns.get(0).isVisible();
	}

	/**
	 * Method checks if Header is present on the Facility Group Page
	 * 
	 * @return boolean based on the visibility of the header
	 */
	public boolean isHeaderNameOnFacilityGrpConfigVisble() {
		return facilityGroupConfigPageHeader.isVisible();
	}

	/**
	 * 
	 * @return boolean value when edit btn are displayed against each facility group
	 */
	public boolean isEditBtnsDisplayedAgainstEachFacilityGrp() {
		int editBtnSize = listOfEditBtns.size();
		int facilityGrpSize = facilityGroupList.size();
		return editBtnSize == facilityGrpSize;
	}

	/**
	 * Method clicks on the edit btn available on the page
	 */
	public void clickOnEditBtn() {
		listOfEditBtns.get(0).click();
	}

	/**
	 * 
	 * @return boolean value based on the visibility of Edit Facility popup
	 */
	public boolean isEditFacilityModalWindowVisisble() {
		return facilityGrpModalWindow.isVisible();
	}

	/**
	 * 
	 * @return boolean value based on the visibility of Physician checkbox
	 */
	public boolean isPhysicianCheckboxVisisble() {
		return physicianCheckbox.isVisible();
	}

	/**
	 * Method clicks on the physician checkbox if it is not checked else, checks the
	 * checkbox
	 */
	public void clickOnPhysicianCheckbox() {
		if (!physicianCheckbox.isSelected()) {
			physicianCheckbox.click();
		} else
			physicianCheckbox.click();

	}

	/**
	 * Method clicks on the edit btn of the facility group who's physician checkbox
	 * is not checked
	 */
	public void clickOnEditBtnWithNoPhysicianChkboxChecked() {
		int check = 0;
		int size = listOfEditBtns.size();
		for (index = 0; index < size; index++) {
			evaluateJavascript("arguments[0].click();", listOfEditBtns.get(index));
			while (physicianCheckbox.isSelected()) {
				physicianCheckbox.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
				closeBtn.click();
				check = 0;
				break;
			}
			if (!physicianCheckbox.isSelected()) {
				physicianCheckbox.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
				physicianCheckbox.click();
				saveBtn.click();
				check = 1;
				break;
			}
			if (check == 1) {
				break;
			}
		}
	}

	/**
	 * 
	 * @return the text value of facility group who's physician checkbox is checked.
	 */
	public String getFacilityGrpNameWithPhysicianChecked() {
		return facilityGroupList.get(index).getText();
	}

	/**
	 * @return the text value facility group name entered in the textbox
	 */
	public String getEnteredFacilityGroupName() {
		return evaluateJavascript("return arguments[0].value;", facilityGrpNameOnPopup).toString();

	}

	/**
	 * 
	 * @return the boolean value based on physician checkbox is checked
	 */
	public boolean isPhysicianCheckboxEnabled() {
		withAction().moveToElement(physicianCheckbox).build().perform();
		return physicianCheckbox.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible().isSelected();
	}

	/**
	 * Method clicks on the edit button of facility group name
	 * 
	 * @param facilityGrpName
	 *            is passed from feature file
	 */
	public void clickOnFacilityGrpEditBtn(String facilityGrpName) {
		int size = listOfEditBtns.size();
		for (index = 0; index < size; index++) {
			if (facilityGroupList.get(index).getText().equals(facilityGrpName)) {
				withAction().moveToElement(facilityGroupList.get(index)).build().perform();
				evaluateJavascript("arguments[0].click();", listOfEditBtns.get(index));
				break;
			}
		}
	}

	/**
	 * Method clicks on Save button
	 */
	public void clickOnSaveBtn() {
		saveBtn.click();
	}

	/**
	 * 
	 * @return text values of all the facilitycodes which are PRCM enabled
	 */
	public List<String> getPrcmEnabledFacilityCodes() {
		List<String> listOfPrcmEnabledFacilityGrp = new ArrayList<>();
		int size = facilityGroupList.size();
		for (index = 0; index < size; index++) {
			if (facilityGroupList.get(index).getText().contains("PRCM")) {
				listOfPrcmEnabledFacilityGrp.add(listOfFacilities.get(index).getText());
			}
		}
		return listOfPrcmEnabledFacilityGrp;
	}

	/**
	 * Method clicks on Add btn on the Add/Edit popup
	 */
	public void clickOnAddBtnOnPopup() {
		addBtnOnAddNewPopup.click();
	}

	/**
	 * Method clicks on close btn
	 */
	public void clickOnCloseBtn() {
		closeBtn.click();
	}

	/**
	 * 
	 * @param facilityGrpName
	 *            is passed from feature file
	 * @param commonFacilityGrpCode
	 *            is passed from feature file
	 * @return the text value of facility group name which contains the facility
	 *         code
	 */
	public boolean checkFacilityGrpContainsCommonFacilityCode(String facilityGrpName, String commonFacilityGrpCode) {
		int size = facilityGroupList.size();
		int i;
		for (i = 0; i < size; i++) {
			if (facilityGroupList.get(i).getText().equals(facilityGrpName)) {
				withAction().moveToElement(facilityGroupList.get(i)).build().perform();
				break;
			}
		}
		return listOfFacilities.get(i).getText().contains(commonFacilityGrpCode);
	}
}
