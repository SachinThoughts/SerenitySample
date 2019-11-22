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

	public void verifyTagConfigPageIsVisible() {
		taggingPageTitle.shouldBeVisible();
	}

	public void searchCategoryNameTxtBoxShouldBeVisible() {
		searchCategoryNameTxtBox.shouldBeVisible();
	}

	public void tagCategoryTabShouldBeVisible() {
		tagCategoryTab.shouldBeVisible();
	}

	public void tagNameTabShouldBeVisible() {
		tagNameTab.shouldBeVisible();
	}

	public void searchCategoryLabelShouldBeVisible() {
		searchCategoryLabel.shouldBeVisible();
	}

	public void addNewCategoryBtnShouldBeVisible() {
		addNewCategoryBtn.shouldBeVisible();
	}

	public void continueBtnShouldBeVisible() {
		continueBtn.shouldBeVisible();
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
}
