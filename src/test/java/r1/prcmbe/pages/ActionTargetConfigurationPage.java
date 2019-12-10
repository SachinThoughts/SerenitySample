package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class ActionTargetConfigurationPage extends PageObject {

	@FindBy(xpath = "//div[@class='panel-heading']/h2[text()='Action Target Configuration']")
	private WebElementFacade actionTargetConfigPageTitle;

	@FindBy(id = "actionFilterCriteria")
	private WebElementFacade searchByDropdwn;

	@FindBy(id = "searchActionTarget")
	private WebElementFacade actionTargetSearchTxtbox;

	@FindBy(id = "applyFilters")
	private WebElementFacade applyButton;

	@FindBy(id = "clearFilters")
	private WebElementFacade clearAllButton;

	@FindBy(id = "btnAddTeam")
	private WebElementFacade addNewActionTargetBtn;
	
	@FindBy(xpath="//li[contains(@id,'typeahead')]/a")
	private WebElementFacade searchSuggestion;
	
	@FindBy(xpath="//*[@id='all-team-configs']//span[@data-ng-bind='response.Name']")
	private List<WebElementFacade> actionNamesList;
	
	@FindBy(id = "actionAlertMsg")
	private WebElementFacade noResultsMessage;
	
	@FindBy(xpath="//*[@id='Actionloader']//i[@class='fa fa-refresh fa-spin fa-3x text-center']")
	private WebElementFacade loadingSpinner;

	public boolean isActionTargetConfigPageVisible() {
		return actionTargetConfigPageTitle.isVisible();
	}

	public void verifyActionTargetConfigTitleVisible() {
		actionTargetConfigPageTitle.shouldBeVisible();
	}

	public boolean isSearchDropdwnVisible() {
		return searchByDropdwn.isVisible();
	}

	public boolean isActionTargetSearchTxtboxVisible() {
		return actionTargetSearchTxtbox.isVisible();
	}

	public boolean isApplyBtnDisabled() {
		return applyButton.isDisabled();
	}

	public boolean isClearAllBtnVisible() {
		return clearAllButton.isVisible();
	}

	public boolean isAddNewActionTargetBtnVisible() {
		return addNewActionTargetBtn.isVisible();
	}
	
	public void clickSearchByDrpdwn() {
		evaluateJavascript("arguments[0].click();",searchByDropdwn);
	}
	
	public List<String> getSearchByDrpdwnValues(){
		return searchByDropdwn.getSelectOptions();
	}
	
	public String getSearchByDrpdwnDefaultSelectedValue() {
		return searchByDropdwn.getSelectedVisibleTextValue();
	}
	
	public void enterTextInActionTargetNameTxtBox(String searchText) {
		loadingSpinner.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
		actionTargetSearchTxtbox.type(searchText);
	}
	
	public String getSearchSuggestionText() {
		return searchSuggestion.getText();
	}
	
	public String getSearchedText() {
		return actionTargetSearchTxtbox.getTextValue();
	}
	
	public void clickSuggestion() {
		searchSuggestion.click();
	}
	
	public boolean isApplyBtnEnabled() {
		return applyButton.isEnabled();
	}
	
	public void clickApplyBtn() {
		applyButton.click();
	}
	
	public List<String> getActionNameList(){
		List<String> actionNames=new ArrayList<>();
		for(WebElementFacade actionName: actionNamesList) {
			actionNames.add(actionName.getText());
		}
		return actionNames;
	}
	
	public String getNoResultsMessage() {
		return noResultsMessage.getText();
	}
}
