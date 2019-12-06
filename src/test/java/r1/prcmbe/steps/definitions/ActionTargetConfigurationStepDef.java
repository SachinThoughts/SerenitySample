package r1.prcmbe.steps.definitions;

import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.prcmbe.pages.ActionTargetConfigurationPage;
import r1.prcmbe.pages.SettingsPage;

public class ActionTargetConfigurationStepDef extends PageObject {
	SettingsPage settingsPage;
	ActionTargetConfigurationPage actionTargetConfigPage;

	@When("^user clicks on Action Target Configuration link$")
	public void user_clicks_on_Action_Target_Configuration_link() {
		settingsPage.clickActionTargetConfigLink();
	}

	@Then("^user should be able to view Action Target Configuration screen$")
	public void user_should_be_able_to_view_Action_Target_Configuration_screen() {
		Assert.assertTrue("Action Target Config Screen is not visible",
				actionTargetConfigPage.isActionTargetConfigPageVisible());
	}

	@Given("^user having AHtoDecision Admin role is on Action Target Configuration page$")
	public void user_having_AHtoDecision_Admin_role_is_on_Action_Target_Configuration_page() {
		actionTargetConfigPage.verifyActionTargetConfigTitleVisible();
	}

	@Then("^user should be able to view Search By dropdown$")
	public void user_should_be_able_to_view_Search_By_dropdown() {
		Assert.assertTrue("Search dropdown is not visible", actionTargetConfigPage.isSearchDropdwnVisible());
	}

	@Then("^user should be able to view Enter Action Target Name textbox$")
	public void user_should_be_able_to_view_Enter_Action_Target_Name_textbox() {
		Assert.assertTrue("Enter Action Target Name textbox is not visible",
				actionTargetConfigPage.isActionTargetSearchTxtboxVisible());
	}

	@Then("^user should be able to view Apply button disabled by default$")
	public void user_should_be_able_to_view_Apply_button_disabled_by_default() {
		Assert.assertTrue("Apply button is not disabled by default", actionTargetConfigPage.isApplyBtnDisabled());
	}

	@Then("^user should be able to view Clear All button$")
	public void user_should_be_able_to_view_Clear_All_button() {
		Assert.assertTrue("Clear all button is not visible", actionTargetConfigPage.isClearAllBtnVisible());
	}

	@Then("^user should be able to view Add New Action Target button$")
	public void user_should_be_able_to_view_Add_New_Action_Target_button() {
		Assert.assertTrue("Add new Action target button is not visible",
				actionTargetConfigPage.isAddNewActionTargetBtnVisible());
	}

	@When("^user clicks on Search By dropdown$")
	public void user_clicks_on_Search_By_dropdown() {
		actionTargetConfigPage.clickSearchByDrpdwn();
	}

	@Then("^user should be able to view the following values in search by dropdown$")
	public void user_should_be_able_to_view_the_following_values_in_search_by_dropdown(DataTable searchByValues) {
		List<String> searchByDrpdwnValues = searchByValues.asList(String.class);
		Assert.assertTrue(
				"The expected values not displayed in search dropdown \t Actual values"
						+ actionTargetConfigPage.getSearchByDrpdwnValues(),
				searchByDrpdwnValues.equals(actionTargetConfigPage.getSearchByDrpdwnValues()));
	}

	@Then("^user should be able to view \"([^\"]*)\" as selected value by default in search by dropdown$")
	public void user_should_be_able_to_view_as_selected_value_by_default_in_search_by_dropdown(String defaultSelectedValue) {
		Assert.assertTrue("The expected value"+defaultSelectedValue+"is not displayed",defaultSelectedValue.equals(actionTargetConfigPage.getSearchByDrpdwnDefaultSelectedValue()));
	}
}
