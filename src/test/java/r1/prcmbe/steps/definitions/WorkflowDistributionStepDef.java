package r1.prcmbe.steps.definitions;

import org.junit.Assert;
import java.util.List;
import cucumber.api.DataTable;
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
		workflowDistributionPage.isWorkflowDistributionTitleVisible();
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

	@Given("^User clicks on All radio button under the Account Inventory Filter in Filter Section$")
	public void user_clicks_on_All_radio_button_under_the_Account_Inventory_Filter_in_Filter_Section() {
		workflowDistributionPage.clickselectAllRadioBtn();
	}

	@Then("^user should be able to view the grid$")
	public void user_should_be_able_to_view_the_grid(DataTable subheaders) {
		List<String> listOfSubheaders = subheaders.asList(String.class);
		Assert.assertTrue(
				"The expected subheaders are not present. Actual subheaders: "
						+ workflowDistributionPage.getListOfFacilityInventoryHeaders() + " Expected subheaders: "
						+ listOfSubheaders,
				workflowDistributionPage.getListOfFacilityInventoryHeaders().containsAll(listOfSubheaders));
	}

	@When("^user clicks on any Facility Group from the grid$")
	public void user_clicks_on_any_Facility_Group_from_the_grid() {
		workflowDistributionPage.clickFirstFacilityGroup();
	}

	@Then("^user should be able to view the below sections$")
	public void user_should_be_able_to_view_the_below_sections(DataTable expSections) {
		List<String> expListOfSections = expSections.asList(String.class);
		Assert.assertTrue("The expected sections are not present. Actual sections: "
				+ workflowDistributionPage.getFacilityGrpSections() + " Expected sections: " + expListOfSections,
				workflowDistributionPage.getFacilityGrpSections().equals(expListOfSections));
	}

	@Then("^user should be able to view following sub sections under Unassigned \\(Due for Work \\) section$")
	public void user_should_be_able_to_view_following_sub_sections_under_Unassigned_Due_for_Work_section(
			DataTable expSubsectionHeaders) {
		List<String> expListOfSubsectionHeaders = expSubsectionHeaders.asList(String.class);
		Assert.assertTrue(
				"The expected Subsections are not present for Unassigned. Actual Subsections: "
						+ workflowDistributionPage.getListOfFacilityGroupSubSectionsForUnassigned()
						+ " Expected sections: " + expListOfSubsectionHeaders,
				workflowDistributionPage.getListOfFacilityGroupSubSectionsForUnassigned()
						.equals(expListOfSubsectionHeaders));
	}

	@Then("^user should be able to view following sub sections under Assigned \\(Due for Work \\) section$")
	public void user_should_be_able_to_view_following_sub_sections_under_Assigned_Due_for_Work_section(
			DataTable expSubsectionHeaders) {
		List<String> expListOfSubsectionHeaders = expSubsectionHeaders.asList(String.class);
		Assert.assertTrue(
				"The expected Subsections are not present for Assigned. Actual Subsections: "
						+ workflowDistributionPage.getListOfFacilityGroupSubSectionsForAssigned()
						+ " Expected sections: " + expListOfSubsectionHeaders,
				workflowDistributionPage.getListOfFacilityGroupSubSectionsForAssigned()
						.equals(expListOfSubsectionHeaders));
	}

	@Given("^User clicks on Payer radio button under the Account Inventory Filter in Filter Section$")
	public void user_clicks_on_Payer_radio_button_under_the_Account_Inventory_Filter_in_Filter_Section() {
		workflowDistributionPage.clickPayerRadioBtn();
	}
}