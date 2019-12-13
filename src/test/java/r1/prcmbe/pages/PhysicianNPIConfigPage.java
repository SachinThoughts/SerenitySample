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

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]//a[@class='btn btn-link']")
	private WebElementFacade firstPhysicianConfigEditLink;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div[1]")
	private WebElementFacade firstPhysicianName;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div[2]")
	private WebElementFacade firstPhysicianNPI;

	@FindBy(xpath = "//*[@id='editPhysicianNPIPayors']/descendant::h3[contains(text(),'Edit Physician Payor List')]")
	private WebElementFacade editPhysicianPopUp;

	@FindBy(xpath = "//*[@id='editPayorSearch']/div[1]/p")
	private WebElementFacade totalPayorsDisabled;

	@FindBy(xpath = "//*[@id='editPayorSearch']/div[3]/p")
	private WebElementFacade totalEligiblePayors;

	@FindBy(xpath = "//*[@id='editPayorSearch']/div[1]/ul[2]/li/div[2]")
	private List<WebElementFacade> listOfDisabledPayorsName;

	@FindBy(xpath = "//*[@id='editPayorSearch']/div[3]/ul[2]/li/div[2]")
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

	@FindBy(xpath = "//*[@id='dnn_ctr2647_TaskPanel_spanTask']/div/div/div[2]/ul[2]/li/div[5]/a")
	private List<WebElementFacade> listOfEditBtnLink;

	@FindBy(xpath = "//div[@class='container-fluid ng-scope']//dir-pagination-controls[1]")
	private WebElementFacade paginationControl;

	@FindBy(xpath = "//*[@id='dnn_ctr2647_TaskPanel_spanTask']/div/div/div[2]/p[1]")
	private WebElementFacade listOfPaginationHeader;

	@FindBy(xpath = "//*[@id='dnn_ctr2647_TaskPanel_spanTask']/div/div/div[2]/p[2]")
	private WebElementFacade listOfPaginationFooter;

	@FindBy(xpath = "//*[@id='dnn_ctr2647_TaskPanel_spanTask']/div/div/div[2]/h3")
	private WebElementFacade facilityPhysicianHeader;

	@FindBy(xpath = "//*[@class='list-table-body physician-list']//div[4]")
	private List<WebElementFacade> listOfTotalPayorsDisabled;

	@FindBy(xpath = "//div[@class='container-fluid ng-scope']/div/h3[text()='Physician Search']")
	private WebElementFacade physicianSearchTitle;

	@FindBy(xpath = "//input[@placeholder='Search Physicians Name, NPI, etc.']")
	private WebElementFacade physicianSearchTxtField;

	@FindBy(xpath = "//ul[@class='list-table-body physician-list']/li[1]/div")
	private List<WebElementFacade> listOfSearchedPhysicianInfo;

	private String cancelBtnJS = "$('#editPhysicianNPIPayors > div > div > div.modal-footer > button.btn.btn-default')";

	private String saveBtnJS = "$('#btnSave')";
	
	String[] paginationValue, paginationValueWithFirstTextValue, paginationValueWithSecondTextValue;
	List<String> listOfPaginationValue = new ArrayList<>();

	public void pRCMNPIConfigTitleShouldBeVisible() {
		pRCMNPIConfigTitle.shouldBeVisible();
	}

	public void clickFirstPhysicianConfigEditLink() {
		firstPhysicianConfigEditLink.withTimeoutOf(Duration.ofSeconds(180)).waitUntilClickable().click();
	}

	public boolean isEditPhysicianPopUpVisible() {
		return editPhysicianPopUp.isVisible();
	}

	public String getTotalPayorsDisabled() {
		return totalPayorsDisabled.getText();
	}

	public String getTotalEligiblePayors() {
		return totalEligiblePayors.getText();
	}

	public List<String> getListOfDisabledPayorsName() {
		List<String> listOfPayorNames = new ArrayList<>();
		for (WebElementFacade payorName : listOfDisabledPayorsName)
			listOfPayorNames.add(payorName.getText());

		return listOfPayorNames;
	}

	public List<String> getListOfEligiblePayorsName() {
		List<String> listOfPayorNames = new ArrayList<>();
		for (WebElementFacade payorName : listOfEligiblePayorsName)
			listOfPayorNames.add(payorName.getText());

		return listOfPayorNames;
	}

	public boolean isAddAllPayorsBtnVisible() {
		return addAllPayorsBtn.isVisible();
	}

	public boolean isRemoveAllPayorsBtnVisible() {
		return removeAllPayorsBtn.isVisible();
	}

	public boolean isSearchEligibleTxtBoxVisible() {
		return searchEligibleTxtBox.isVisible();
	}

	public boolean isSearchDisabledTxtBoxVisible() {
		return searchDisabledTxtBox.isVisible();
	}

	public boolean isCancelBtnVisible() {
		return evaluateJavascript("return " + cancelBtnJS + ".text()").equals("Cancel");
	}

	public boolean isSaveBtnVisible() {
		return evaluateJavascript("return " + saveBtnJS + ".text()").equals("Save");
	}

	public String getFirstPhysicianName() {
		return firstPhysicianName.withTimeoutOf(Duration.ofSeconds(180)).waitUntilVisible().getText();
	}

	public String getFirstPhysicianNPI() {
		return firstPhysicianNPI.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().getText();
	}

	public String getPopUpMsgAndPhysicianNameNPI() {
		return popUpMsgAndPhysicianNameNPI.getText();
	}

	public void enterSearchDisabledTxtBox(String disabledPayor) {
		searchDisabledTxtBox.type(disabledPayor);
	}

	public int getCountOfTotalPayorsDisabled() {
		String[] totalPayorDisabled = getTotalPayorsDisabled().split(": ", 0);
		return Integer.parseInt(totalPayorDisabled[1]);
	}

	public void enterSearchEligibleTxtBox(String eligiblePayor) {
		searchEligibleTxtBox.type(eligiblePayor);
	}

	public int getCountOfTotalEligiblePayors() {
		String[] totalPayorEligible = getTotalEligiblePayors().split(": ", 0);
		return Integer.parseInt(totalPayorEligible[1]);
	}

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

	public void clickAddAllPayorsBtn() {
		addAllPayorsBtn.click();
	}

	public void clickRemoveAllPayorsBtn() {
		removeAllPayorsBtn.click();
	}

	public String getSaveConfigMsg() {
		return saveConfigMsg.getText();
	}

	public void clickSaveBtn() {
		evaluateJavascript(saveBtnJS + ".click()");
	}

	public int firstPhyTotalDisabledCount() {
		waitForAngularRequestsToFinish();
		return Integer.parseInt(firstPhyTotalDisabledCount.getText());
	}

	public String getFirstPhysiciansFirstName() {
		String[] physicianName = getFirstPhysicianName().split(", ", 0);
		return physicianName[1].trim();
	}
	
	public List<String> getListOfPhysicianColumnNames() {
		List<String> listOfColumnNames = new ArrayList<>();
		for (WebElementFacade columnName : listOfPhysicianColumnNames)
			listOfColumnNames.add(columnName.getText());

		return listOfColumnNames;
	}

	public boolean isListOfEditBtnLinkEmpty() {
		firstPhysicianConfigEditLink.withTimeoutOf(Duration.ofSeconds(180)).waitUntilVisible();
		return listOfEditBtnLink.isEmpty();
	}

	public boolean isPaginationCtrlVisible() {
		return paginationControl.isVisible();
	}

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

	public String getFacilityPhysicianHeader() {
		return facilityPhysicianHeader.getText();
	}

	public List<String> getCountOfTotalDisabledPayors() {
		List<String> listOfTotalPayors = new ArrayList<>();
		for (WebElementFacade totalPayors : listOfTotalPayorsDisabled)
			listOfTotalPayors.add(totalPayors.getText());
		return listOfTotalPayors;
	}

	public boolean isphysicianSearchTitleVisible() {
		return physicianSearchTitle.isVisible();
	}

	public void clickOnPhysicianSearchTxtField() {
		physicianSearchTxtField.click();
	}

	public void enterPhysicianSearchTxtBox(String physiciansName) {
		physicianSearchTxtField.type(physiciansName);
	}

	public List<String> getListofSearchedPhisicianInfo() {
		List<String> listOfphysicianInfo = new ArrayList<>();
		for (WebElementFacade physicianInfo : listOfSearchedPhysicianInfo)
			listOfphysicianInfo.add(evaluateJavascript("return arguments[0].innerText;", physicianInfo).toString());
		return listOfphysicianInfo;
	}
}