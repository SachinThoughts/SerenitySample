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

	@FindBy(xpath = "//h3[contains(text(),'Facility Group Configuration')]")
	private WebElementFacade facilityGroupConfigPageHeader;

	@FindBy(xpath = "//ul[@class='list-table-header FacilityGroup-admin no-counter']/li/preceding-sibling::li")
	private List<WebElementFacade> listOfTableHeader;

	@FindBy(xpath = "//button[text()='Add New Facility Group']")
	private List<WebElementFacade> listOfAddFacilityBtn;

	@FindBy(xpath = "//a[@class='btn btn-link lnkEditFacilityGroup']")
	private List<WebElementFacade> listOfEditBtns;

	@FindBy(xpath = "//*[@id='lstFacilityGroup']/li/div[2]/span")
	private List<WebElementFacade> listOfFacilities;

	@FindBy(id = "lblModalName")
	private WebElementFacade facilityGrpModalWindow;

	@FindBy(id = "btnAddFacility")
	private WebElementFacade addBtnOnAddNewPopup;
	//
	@FindBy(id = "txtAssignFacility")
	private WebElementFacade facilityCodeTxtBox;

	@FindBy(xpath = "//*[@id='addFacilityGroup']//label[@class='control-label']")
	private List<WebElementFacade> listOfLabels;

	@FindBy(xpath = "//*[@id='addFacilityGroup']//div[4]/span/b")
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

	public void clickOnFacilityCodeFromSearchDropdown(String code) {
		int size = facilitySearchSuggestion.size();
		for (int i = 0; i < size; i++) {
			if (facilitySearchSuggestion.get(i).getText().contains(code)) {
				evaluateJavascript("arguments[0].click();", facilitySearchSuggestion.get(i));
			}
		}
	}

	public void enterFacilityGrpNameInTxtBox(String facilityGrpName) {
		facilityGrpNameOnPopup.type(facilityGrpName.concat(RandomStringUtils.randomAlphabetic(3)));
	}

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

	public void enterFacilityCodeInTxtBox(String code) {
		facilityCodeTxtBox.type(code);
	}

	public boolean isAddBtnOnAddNewPopupEnabled() {
		if (addBtnOnAddNewPopup.getAttribute("disabled") == null) {
			return true;
		}
		return false;
	}

	public boolean isAddNewFacilityGroupPopupVisible() {
		return facilityGrpModalWindow.isVisible();
	}

	public void clickOnAddFacilityBtn() {
		listOfAddFacilityBtn.get(0).click();
	}

	public void isFacilityGrpConfigHeaderVisible() {
		facilityGroupConfigPageHeader.shouldBeVisible();
	}

	public List<String> getTableGridHeaders() {
		List<String> getHeaders = new ArrayList<>();
		for (WebElementFacade headerElement : listOfTableHeader) {
			getHeaders.add(headerElement.getText().trim());
		}
		return getHeaders;
	}

	public boolean isFacilityGroupNamePresent(String expectedFacilityGroupName) {
		List<String> getListOfFacilityGroup = new ArrayList<>();
		for (WebElementFacade facilityGrp : facilityGroupList) {
			getListOfFacilityGroup.add(facilityGrp.getText());
		}
		return getListOfFacilityGroup.contains(expectedFacilityGroupName);
	}

	public boolean isExpectedFacilityPresent(String expectedFacility) {
		List<String> getFacilities = new ArrayList<>();
		for (WebElementFacade facility : listOfFacilities) {
			getFacilities.add(facility.getText());
		}
		return getFacilities.contains(expectedFacility);
	}

	public boolean areAddFAcilityBtnPresents() {
		int size = listOfAddFacilityBtn.size();
		for (int i = 0; i < size; i++) {
			if (listOfAddFacilityBtn.get(i).isVisible()) {
				return true;
			}
		}
		return false;
	}

	public boolean isEditBtnPresent() {
		return listOfEditBtns.get(0).isVisible();
	}

	public boolean isHeaderNameOnFacilityGrpConfigVisble() {
		return facilityGroupConfigPageHeader.isVisible();
	}

	public boolean isEditBtnsDisplayedAgainstEachFacilityGrp() {
		int editBtnSize = listOfEditBtns.size();
		int facilityGrpSize = facilityGroupList.size();
		return editBtnSize == facilityGrpSize;
	}

	public void clickOnEditBtn() {
		listOfEditBtns.get(0).click();
	}

	public boolean isEditFacilityModalWindowVisisble() {
		return facilityGrpModalWindow.isVisible();
	}

	public boolean isPhysicianCheckboxVisisble() {
		return physicianCheckbox.isVisible();
	}

	public void clickOnPhysicianCheckbox() {
		if (!physicianCheckbox.isSelected()) {
			physicianCheckbox.click();
		} else {
			physicianCheckbox.click();
		}
	}

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

	public String getFacilityGrpNameWithPhysicianChecked() {
		return facilityGroupList.get(index).getText();
	}

	public String getEnteredFacilityGroupName() {
		return evaluateJavascript("return arguments[0].value;", facilityGrpNameOnPopup).toString();

	}

	public boolean isPhysicianCheckboxEnabled() {
		withAction().moveToElement(physicianCheckbox).build().perform();
		return physicianCheckbox.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible().isSelected();
	}

	public void clickOnFacilityGrpEditBtn(String facilityGrpName) {
		int size = listOfEditBtns.size();
		for (index = 0; index < size; index++) {
			if (facilityGroupList.get(index).getText().equals(facilityGrpName)) {
				withAction().moveToElement(facilityGroupList.get(index)).build().perform();
				evaluateJavascript("arguments[0].click();", listOfEditBtns.get(index));
			}
		}
	}

	public void clickOnSaveBtn() {
		saveBtn.click();
	}

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

	public void clickOnAddBtnOnPopup() {
		addBtnOnAddNewPopup.click();
	}
}
