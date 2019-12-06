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

	@Given("^User clicks on Patient radio button under the Account Inventory Filter in Filter Section$")
	public void user_clicks_on_Patient_radio_button_under_the_Account_Inventory_Filter_in_Filter_Section() {
		workflowDistributionPage.clickPatientRadioBtn();
	}

	@Then("^user should not be able to view any data in side the grid$")
	public void user_should_not_be_able_to_view_any_data_in_side_the_grid() {
		Assert.assertFalse("user should not be able to view any data in side the grid",
				workflowDistributionPage.isFirstFacilityGroupVisible());
	}

	@Then("^user should be able to view All Radio button, Professional Radio button, Technical Radio button under Payer Inventory section under Filter section$")
	public void user_should_be_able_to_view_All_Radio_button_Professional_Radio_button_Technical_Radio_button_under_Payer_Inventory_section_under_Filter_section() {
		Assert.assertTrue("All radio button is not visible", workflowDistributionPage.isAllRadioBtnVisible());
		Assert.assertTrue("Professional radio button is not visible",
				workflowDistributionPage.isProfessionalRadioBtnVisible());
		Assert.assertTrue("Technical radio button is not visible",
				workflowDistributionPage.isTechnicalRadioBtnVisible());
	}

	@Given("^user clicks on Facility Inventory tab$")
	public void user_clicks_on_Facility_Inventory_tab() {
		workflowDistributionPage.clickOnFacilityInvtryTab();
	}

	@Then("^user should be able to view Hide link on facility Inventory Tab")
	public void user_should_be_able_to_view_Hide_link_on_facility_Inventory_Tab() {
		Assert.assertTrue("User is not able to view Hide Link",
				workflowDistributionPage.isHideLinkVisibleInFacilityInvtryTab());
	}

	@Then("^user should be able to view Professional Radio button selected by default under Payer section$")
	public void user_should_be_able_to_view_Professional_Radio_button_selected_by_default_under_Payer_section() {
		Assert.assertTrue("Professional radio button is not selected",
				workflowDistributionPage.isProfessionalRadioBtnSelected().equals("true"));
	}

	@When("^User clicks on Hide link on facility Inventory Tab$")
	public void user_clicks_on_Hide_link_on_facility_Inventory_Tab() {
		workflowDistributionPage.clickOnHideLinkOnFacilityInvtryTab();
	}

	@Then("^user should be able to view Show link label on facility Inventory Tab$")
	public void user_should_be_able_to_view_Show_link_label_on_facility_Inventory_Tab() {
		Assert.assertTrue("User is not able to view Show Link",
				workflowDistributionPage.isShowLinkVisibleInFacilityInvtryTab());
	}

	@Then("^user should not be able to view the following filters under Account Inventory under Filters section$")
	public void user_should_not_be_able_to_view_the_following_filters_under_Account_Inventory_under_Filters_section() {
		Assert.assertTrue("User able to view the filters under Account Inventory Tab",
				workflowDistributionPage.isFiltersUnderAccInvtryVisible());
	}

	@When("^User clicks on Show link on facility Inventory Tab$")
	public void user_clicks_on_Show_link_on_facility_Inventory_Tab() {
		workflowDistributionPage.clickOnShowLinkOnFacilityInvtryTab();
	}

	@Then("^user should be able to view the following filters under Account Inventory under Filters section$")
	public void user_should_be_able_to_view_the_following_filters_under_Account_Inventory_under_Filters_section(
			DataTable listOfFilters) {
		List<String> listOfFiltersUnderAccInvtryTab = listOfFilters.asList(String.class);
		Assert.assertTrue("User cannot view the filters under Account Inventory Tab", workflowDistributionPage
				.getFiltersValueUnderAccInventory().containsAll(listOfFiltersUnderAccInvtryTab));
	}

	@Given("^user selects PRCM enabled \"([^\"]*)\" facility group from Facility Group dropdown$")
	public void user_selects_PRCM_enabled_facility_group_from_Facility_Group_dropdown(String facilityGrpName) {
		workflowDistributionPage.selectFacilityGroup(facilityGrpName);
	}

	@When("^user selects Non-PRCM facility group \"([^\"]*)\" from Facility Group dropdown$")
	public void user_selects_Non_PRCM_facility_group_from_Facility_Group_dropdown(String facilityGrpName) {
		workflowDistributionPage.selectFacilityGroup(facilityGrpName);
	}

	@Then("^user should be able to view Team Filter under Filters section on Reps Tab$")
	public void user_should_be_able_to_view_Team_Filter_under_Filters_section_on_Reps_Tab(DataTable filtersName) {
		List<String> listOfFiltersName = filtersName.asList(String.class);
		Assert.assertTrue("failed to verify filters under Reps Tab",
				listOfFiltersName.equals(workflowDistributionPage.getFiltersNameUnderRepsTab()));
	}

	@Then("^user should be able to view Hide link besides Team on Reps Tab$")
	public void user_should_be_able_to_view_Hide_link_besides_Team_on_Reps_Tab() {
		Assert.assertTrue("Hide link is not besides Team on Reps Tab",
				workflowDistributionPage.isHideLinkVisibleInRepsTab());
	}

	@Then("^user should be able to view Search label on Reps Tab$")
	public void user_should_be_able_to_view_Search_label_on_Reps_Tab() {
		workflowDistributionPage.isSearchLabelOnRepsTabVisible();
	}

	@Then("^user should be able to view \"([^\"]*)\" search box on Reps Tab$")
	public void user_should_be_able_to_view_search_box_on_Reps_Tab(String expectedWaterMarkText) {
		workflowDistributionPage.getSearchBoxWaterMarkTextOnRepsTab();
	}

	@Then("^user should be able to view buttons on Reps Tab$")
	public void user_should_be_able_to_view_buttons_on_Reps_Tab(DataTable buttons) {
		List<String> listOfButtons = buttons.asList(String.class);
		Assert.assertTrue("failed to verify buttons under Reps Tab",
				listOfButtons.equals(workflowDistributionPage.getButtonsValueUnderRepsTab()));
	}

	@When("^User clicks on Hide link on Reps Tab$")
	public void user_clicks_on_Hide_link_on_Reps_Tab() {
		workflowDistributionPage.clickOnHideLinkOnRepsTab();
	}

	@Then("^user should be able to view Show link label on Reps Tab$")
	public void user_should_be_able_to_view_Show_link_label_on_Reps_Tab() {
		Assert.assertTrue("failed to view Show link label on Reps Tab",
				workflowDistributionPage.isShowLinkVisibleInRepsTab());
	}

	@Then("^user should not be able to view Filters on Reps Tab$")
	public void user_should_not_be_able_to_view_Filters_on_Rep_Tab() {
	//	Assert.assertTrue("Failed to view Filters on Reps", workflowDistributionPage.isShowLinkVisibleInRepsTab());
		Assert.assertTrue("Failed to view Filters on Reps",workflowDistributionPage.isListOfFiltersOnRepsTabVisible());
	}

	@When("^User clicks on Show link on Reps Tab$")
	public void user_clicks_on_Show_link_on_Reps_Tab() {
		workflowDistributionPage.clickOnShowLinkOnRepsTab();
	}
}
