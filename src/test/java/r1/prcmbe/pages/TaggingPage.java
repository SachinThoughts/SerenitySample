package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class TaggingPage extends PageObject {

	@FindBy(xpath = "//h2[contains(text(),'Account Tags Configuration')]")
	private WebElementFacade taggingPageTitle;

	@FindBy(xpath = "//input[@placeholder='Search Category Name']")
	private WebElementFacade searchCategoryNameTxtBox;

	@FindBy(xpath = "//a[text()='Tag Category']//parent::li[@class='active']")
	private WebElementFacade tagCategoryTab;

	@FindBy(xpath = "//*[@id='main-content']//a[text()='Tag Name']")
	private WebElementFacade tagNameTab;

	@FindBy(xpath = "//label[text()='Search Categories']")
	private WebElementFacade searchCategoryLabel;

	@FindBy(xpath = "//*[@id='main-content']//button[text()=' Add New Category']")
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

	@FindBy(xpath = "//*[@id='select0']")
	private WebElementFacade applicationDrpdwn;

	@FindBy(xpath = "//span[@class='slider round']")
	private WebElementFacade applicationSlideBar;

	@FindBy(xpath = "//button[@class='btn btn-link editRow']//i")
	private WebElementFacade saveBtn;

	@FindBy(xpath = "//span[text()='Category Name should not more than 100 characters' or text()='Category Description should not more than 500 characters']")
	private WebElementFacade invalidCategoryNameValidationMsg;

	public boolean isTagConfigPageVisible() {
		return taggingPageTitle.isVisible();
	}

	public boolean isSearchCategoryNameTxtBoxVisible() {
		return searchCategoryNameTxtBox.isVisible();
	}

	public boolean isTagCategoryTabVisible() {
		return tagCategoryTab.isVisible();
	}

	public boolean isTagNameTabVisible() {
		return tagNameTab.isVisible();
	}

	public boolean isSearchCategoryLabelVisible() {
		return searchCategoryLabel.isVisible();
	}

	public boolean isAddNewCategoryBtnVisible() {
		return addNewCategoryBtn.isVisible();
	}

	public boolean isContinueBtnVisible() {
		return continueBtn.isVisible();
	}

	public List<String> getListOfColHeaders() {
		waitForAngularRequestsToFinish();
		List<String> colHeaderList = new ArrayList<>();
		for (WebElementFacade listOfNames : listOfColHeaders) {
			colHeaderList.add(listOfNames.getText().trim());
			colHeaderList.removeAll(Arrays.asList(null, ""));
		}
		return colHeaderList;
	}

	public void enterRandomTxtInSearchCategoryNameTxtbox() {
		searchCategoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(6));
	}

	public String getAlertMsg() {
		return alertMsg.getText();
	}

	public String getAlertSectionColor() {
		return alertSection.getCssValue("background-color");
	}

	public void clickAddNewCategoryBtn() {
		waitForAngularRequestsToFinish();
		addNewCategoryBtn.click();
	}

	public void enterMoreThnHundredCategoryName() {
		categoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(105));
	}

	public void enterUptoFiveHundredTagDisc() {
		tagDescriptionTxtBox.type(RandomStringUtils.randomAlphabetic(500));
	}

	public void selectApplication(String applicationName) {
		applicationDrpdwn.selectByVisibleText(applicationName);
	}

	public void clickActiveSlider() {
		applicationSlideBar.click();
	}

	public void clickOnSaveBtn() {
		saveBtn.click();
	}

	public String getCategoryNameValidationMsg() {
		return invalidCategoryNameValidationMsg.getText();
	}
	
	public void enterUptoHundredCategoryName() {
		categoryNameTxtBox.type(RandomStringUtils.randomAlphabetic(100));
	}

	public void enterMoreThnFiveHundredTagDisc() {
		tagDescriptionTxtBox.type(RandomStringUtils.randomAlphabetic(505));
	}

}
