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

	/**
	 * @return visibility of Action target configuration page title
	 */
	public boolean isActionTargetConfigPageVisible() {
		return actionTargetConfigPageTitle.isVisible();
	}

	/**
	 * verify Action target configuration title displayed
	 */
	public void verifyActionTargetConfigTitleVisible() {
		actionTargetConfigPageTitle.shouldBeVisible();
	}

	/**
	 * @return visibility of Search dropdown
	 */
	public boolean isSearchDropdwnVisible() {
		return searchByDropdwn.isVisible();
	}

	/**
	 * @return visibility of Search textbox
	 */
	public boolean isActionTargetSearchTxtboxVisible() {
		return actionTargetSearchTxtbox.isVisible();
	}

	/**
	 * @return whether Apply button is disabled
	 */
	public boolean isApplyBtnDisabled() {
		return applyButton.isDisabled();
	}

	/**
	 * @return visibility of Clear All button
	 */
	public boolean isClearAllBtnVisible() {
		return clearAllButton.isVisible();
	}

	/**
	 * @return visibility of Add new Action target button
	 */
	public boolean isAddNewActionTargetBtnVisible() {
		return addNewActionTargetBtn.isVisible();
	}
	
	/**
	 * clicks on Search dropdown
	 */
	public void clickSearchByDrpdwn() {
		evaluateJavascript("arguments[0].click();",searchByDropdwn);
	}
	
	/**
	 * @return list of Search dropdown values
	 */
	public List<String> getSearchByDrpdwnValues(){
		return searchByDropdwn.getSelectOptions();
	}
	
	/**
	 * @return the default value selected in Search dropdown
	 */
	public String getSearchByDrpdwnDefaultSelectedValue() {
		return searchByDropdwn.getSelectedVisibleTextValue();
	}
	
	/**
	 * enters the parameter value in the Action target name textbox
	 * @param searchText
	 */
	public void enterTextInActionTargetNameTxtBox(String searchText) {
		loadingSpinner.withTimeoutOf(Duration.ofSeconds(30)).waitUntilNotVisible();
		actionTargetSearchTxtbox.type(searchText);
	}
	
	/**
	 * @return search suggestion text
	 */
	public String getSearchSuggestionText() {
		return searchSuggestion.getText();
	}
	
	/**
	 * @return searched text from search textbox
	 */
	public String getSearchedText() {
		return actionTargetSearchTxtbox.getTextValue();
	}
	
	/**
	 * clicks on the search suggestion
	 */
	public void clickSuggestion() {
		searchSuggestion.click();
	}
	
	/**
	 * @return whether Apply button is enabled
	 */
	public boolean isApplyBtnEnabled() {
		return applyButton.isEnabled();
	}
	
	/**
	 * clicks on Apply button
	 */
	public void clickApplyBtn() {
		applyButton.click();
	}
	
	/**
	 * @return list of Action Names
	 */
	public List<String> getActionNameList(){
		List<String> actionNames=new ArrayList<>();
		for(WebElementFacade actionName: actionNamesList) {
			actionNames.add(actionName.getText());
		}
		return actionNames;
	}
	
	/**
	 * @return no results message
	 */
	public String getNoResultsMessage() {
		return noResultsMessage.getText();
	}
}
