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

	private String cancelBtnJS = "$('#editPhysicianNPIPayors > div > div > div.modal-footer > button.btn.btn-default')";

	private String saveBtnJS = "$('#btnSave')";

	public void pRCMNPIConfigTitleShouldBeVisible() {
		pRCMNPIConfigTitle.shouldBeVisible();
	}

	public void clickFirstPhysicianConfigEditLink() {
		firstPhysicianConfigEditLink.withTimeoutOf(Duration.ofSeconds(180)).waitUntilClickable().click();
	}

	public void editPhysicianPopUpShouldBeVisible() {
		editPhysicianPopUp.shouldBeVisible();
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

	public void addAllPayorsBtnShouldBeVisible() {
		addAllPayorsBtn.shouldBeVisible();
	}

	public void removeAllPayorsBtnShouldBeVisible() {
		removeAllPayorsBtn.shouldBeVisible();
	}

	public void searchEligibleTxtBoxShouldBeVisible() {
		searchEligibleTxtBox.shouldBeVisible();
	}

	public void searchDisabledTxtBoxShouldBeVisible() {
		searchDisabledTxtBox.shouldBeVisible();
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
}
