package r1.prcmbe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class R1ConfigurationPage extends PageObject {

	@FindBy(id = "dnn_ctr1531_dnnTITLE_titleLabel")
	private WebElementFacade r1ConfigurationTitle;

	@FindBy(id = "dllApplicationSearch")
	private WebElementFacade searchDrpdwn;

	@FindBy(id = "txtAppSearchValue")
	private WebElementFacade searchTextbox;

	@FindBy(id = "BtnAppSearch")
	private WebElementFacade searchBtn;

	@FindBy(xpath = "//*[@id='ApplicationGridData']/div[1]/div[@text='Update AppSetting']")
	private WebElementFacade editBtn;

	@FindBy(id = "txtAppSettingValue")
	private WebElementFacade settingValueTextbox;

	@FindBy(id = "BtnADDApplication")
	private WebElementFacade updateAppSettingBtn;

	@FindBy(xpath = "//span[text()='Application Setting List']")
	private WebElementFacade applicationSettingsList;

	@FindBy(xpath = "//*[@id='ApplicationGridData']/div[1]/div[1]")
	private WebElementFacade settingName;

	@FindBy(xpath = "//*[@id='ApplicationGridData']/div[1]/div[2]")
	private WebElementFacade settingValue;

	public void isR1ConfigurationTitleVisible() {
		r1ConfigurationTitle.shouldBeVisible();
	}

	public void selectValueFromSearchDrpdwn(String value) {
		searchDrpdwn.selectByVisibleText(value);
	}

	public void enterValueInSearchTextbox(String input) {
		searchTextbox.type(input);
	}

	public void clickSearchBtn() {
		evaluateJavascript("arguments[0].click();", searchBtn);
	}

	public void clickEditBtn() {
		evaluateJavascript("arguments[0].click();", editBtn);
	}

	public void enterValueInSettingValueTextbox(String input) {
		settingValueTextbox.clear();
		settingValueTextbox.type(input);
	}

	public void clickUpdateAppSettingBtn() {
		updateAppSettingBtn.click();
	}

	public boolean isApplicationSettingsListVisible() {
		return applicationSettingsList.isVisible();
	}

	public String getSettingNameValue() {
		return settingName.getText().trim();
	}

	public String getSettingValue() {
		return settingValue.getText().trim();
	}
}
