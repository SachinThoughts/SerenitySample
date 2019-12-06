package r1.prcmbe.pages;

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
		searchByDropdwn.click();
	}
	
	public List<String> getSearchByDrpdwnValues(){
		return searchByDropdwn.getSelectOptions();
	}
	
	public String getSearchByDrpdwnDefaultSelectedValue() {
		return searchByDropdwn.getSelectedVisibleTextValue();
	}
}
