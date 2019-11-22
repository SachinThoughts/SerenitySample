package r1.prcmbe.steps.definitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import org.junit.Assert;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.WorkflowDistributionPage;

public class WorkflowDistributionStepDef {

	NavigationPage navigationPage;
	WorkflowDistributionPage workflowDistributionPage;

	@When("^user clicks on Workflow Distribution link$")
	public void user_clicks_on_Workflow_Distribution_link() {
		navigationPage.clickWorkflowDistribution();
	}

	@Given("^user is on Workflow Distribution screen$")
	public void user_is_on_Workflow_Distribution_screen() {
		Assert.assertTrue("User is not on Workflow Distribution screen",
				workflowDistributionPage.isWorkflowDistributionTitleVisible());
	}

	@Given("^user clicks on Facility Inventory tab$")
	public void user_clicks_on_Facility_Inventory_tab() {
		workflowDistributionPage.clickOnFacilityInvtryTab();
	}

	@Then("^user should be able to view Hide link$")
	public void user_should_be_able_to_view_Hide_link() {
		Assert.assertTrue("User is not able to view Hide Link",
				workflowDistributionPage.isHideLinkVisibleInFacilityInvtryTab());
	}

	@Then("^user should be able to view Professional Radio button selected by default under Payer section$")
	public void user_should_be_able_to_view_Professional_Radio_button_selected_by_default_under_Payer_section() {
		Assert.assertTrue("Professional radio button is not selected",
				workflowDistributionPage.isProfessionalRadioBtnSelected().equals("true"));
	}

	@When("^User clicks on Hide link$")
	public void user_clicks_on_Hide_link() {
		workflowDistributionPage.clickOnHideLinkOnFacilityInvtryTab();
	}

	@Then("^user should be able to view Show link label$")
	public void user_should_be_able_to_view_Show_link_label() {
		Assert.assertTrue("User is not able to view Show Link",
				workflowDistributionPage.isShowLinkVisibleInFacilityInvtryTab());
	}

	@Then("^user should not be able to view the following filters under Account Inventory under Filters section $")
	public void user_should_not_be_able_to_view_the_following_filters_under_Account_Inventory_under_Filters_section(
			DataTable listOfFilters) {
		List<String> listOfFiltersUnderAccInvtryTab = listOfFilters.asList(String.class);
		Assert.assertTrue("User able to view the filters under Account Inventory Tab",
				workflowDistributionPage.isFiltersUnderAccInvtryVisible());
	}

	@When("^User clicks on Show link$")
	public void user_clicks_on_Show_link() {
		workflowDistributionPage.clickOnShowLinkOnFacilityInvtryTab();
	}

	@Then("^user should be able to view the following filters under Account Inventory under Filters section$")
	public void user_should_be_able_to_view_the_following_filters_under_Account_Inventory_under_Filters_section(
			DataTable listOfFilters) {
		List<String> listOfFiltersUnderAccInvtryTab = listOfFilters.asList(String.class);
		Assert.assertTrue("User cannot view the filters under Account Inventory Tab", workflowDistributionPage
				.getFiltersValueUnderAccInventory().containsAll(listOfFiltersUnderAccInvtryTab));
	}
}
