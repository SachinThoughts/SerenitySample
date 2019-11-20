package r1.prcmbe.steps.definitions;

import org.junit.Assert;
import java.util.List;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.WorkflowDistributionPage;

public class WorkflowDistributionStepDef extends PageObject {
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

	@Then("^user should be able view following tabs under Workflow Distribution$")
	public void user_should_be_able_view_following_tabs_under_Workflow_Distribution(DataTable distributionTabs) {
		List<String> wrkflwDistributionTabs = distributionTabs.asList(String.class);
		Assert.assertTrue("User cannot view the tabs under Workflow Distribution",
				workflowDistributionPage.isWorkflowDistributionTabsVisible(wrkflwDistributionTabs));
	}

	@Then("^user should able to view \"([^\"]*)\" opened by default$")
	public void user_should_able_to_view_opened_by_default(String expectedTab) {
		Assert.assertTrue("Payer Inventory is not open by default",
				workflowDistributionPage.getBreadCrumbText().contains(expectedTab));
	}

	@When("^user clicks on Patient Inventory Tab$")
	public void user_clicks_on_Patient_Inventory_Tab() {
		workflowDistributionPage.clickPatientInventoryTab();
	}

	@Then("^user should be able to view \"([^\"]*)\" tab$")
	public void user_should_be_able_to_view_tab(String expectedTab) {
		Assert.assertTrue("User is not able to view" + expectedTab + "tab",
				workflowDistributionPage.getBreadCrumbText().contains(expectedTab));
	}

	@When("^user clicks on Facility Inventory Tab$")
	public void user_clicks_on_Facility_Inventory_Tab() {
		workflowDistributionPage.clickFacilityInventoryTab();
	}

	@When("^user clicks on Team Tab$")
	public void user_clicks_on_Team_Tab() {
		workflowDistributionPage.clickTeamsTab();
	}

	@When("^user clicks on Reps tab$")
	public void user_clicks_on_Reps_tab() {
		workflowDistributionPage.clickRepsTab();
	}

	@Then("^user should be able to view Notes section between  Filter Result by section and Bread Crumb$")
	public void user_should_be_able_to_view_Notes_section_between_Filter_Result_by_section_and_Bread_Crumb() {
		Assert.assertTrue("Notes section is not visible on Workflow Distribution tabs",
				workflowDistributionPage.isNoteMsgVisible());
	}

	@Then("^user should able to view Note message as \"([^\"]*)\"$")
	public void user_should_able_to_view_Note_message_as(String expectedNoteMsg) {
		Assert.assertTrue("User is not able to view the Notes message displayed on Workflow Distribution tabs",
				workflowDistributionPage.getNoteMsgText().equals(expectedNoteMsg));
	}
}
