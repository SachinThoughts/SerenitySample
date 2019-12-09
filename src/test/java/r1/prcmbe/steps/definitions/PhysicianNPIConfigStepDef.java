package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.prcmbe.pages.PhysicianNPIConfigPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.PhysicianNPIConfigSteps;

public class PhysicianNPIConfigStepDef {
	SettingsPage settingsPage;
	PhysicianNPIConfigPage physicianNPIConfigPage;

	@Steps
	PhysicianNPIConfigSteps physicianNPIConfigSteps;

	String physicianName, physicianNPI, payor;

	@When("^user hovers Payor and Plan Config$")
	public void user_hovers_Payor_and_Plan_Config() {
		settingsPage.hoverPayorAndPlanConfigLink();
	}

	@When("^user clicks on PRCM Eligibility NPI Configuration$")
	public void user_clicks_on_PRCM_Eligibility_NPI_Configuration() {
		settingsPage.clickEligibilityNPIConfigLink();
	}

	@Given("^user is on PRCM Eligibility NPI Configuration page$")
	public void user_is_on_PRCM_Eligibility_NPI_Configuration_page() {
		physicianNPIConfigPage.pRCMNPIConfigTitleShouldBeVisible();
	}

	@When("^user clicks on to Edit link corresponding to the Physicians Name$")
	public void user_clicks_on_to_Edit_link_corresponding_to_the_Physicians_Name() {
		physicianNPIConfigPage.clickFirstPhysicianConfigEditLink();
	}

	@Then("^user should be able to view edit pop up$")
	public void user_should_be_able_to_view_edit_pop_up() {
		physicianNPIConfigPage.editPhysicianPopUpShouldBeVisible();
	}

	@Then("^user should be able to view the section \"([^\"]*)\" with the payors disabled for the Physician$")
	public void user_should_be_able_to_view_the_section_with_the_payors_disabled_for_the_Physician(String sectionName) {
		Assert.assertTrue("Section " + sectionName + " is not visible",
				physicianNPIConfigPage.getTotalPayorsDisabled().contains(sectionName));
		Assert.assertTrue("Payors disabled for the physician are not visible",
				!physicianNPIConfigPage.getListOfDisabledPayorsName().isEmpty());
	}

	@Then("^user should be able to view the section \"([^\"]*)\" with the payors chosen to configure$")
	public void user_should_be_able_to_view_the_section_with_the_payors_chosen_to_configure(String sectionName) {
		Assert.assertTrue("Section " + sectionName + " is not visible",
				physicianNPIConfigPage.getTotalEligiblePayors().contains(sectionName));
		Assert.assertTrue("Payors choosen to configure for the physician are not visible",
				!physicianNPIConfigPage.getListOfEligiblePayorsName().isEmpty());
	}

	@Then("^user should be able to view provide Add All Payors and Remove All Payors buttons$")
	public void user_should_be_able_to_view_provide_Add_All_Payors_and_Remove_All_Payors_buttons() {
		physicianNPIConfigPage.addAllPayorsBtnShouldBeVisible();
		physicianNPIConfigPage.removeAllPayorsBtnShouldBeVisible();
	}

	@Then("^user should be able to view the Search Disabled Payors section$")
	public void user_should_be_able_to_view_the_Search_Disabled_Payors_section() {
		physicianNPIConfigPage.searchDisabledTxtBoxShouldBeVisible();
	}

	@Then("^user should be able to view Search Eligible Payors section$")
	public void user_should_be_able_to_view_Search_Selected_Payors_section() {
		physicianNPIConfigPage.searchEligibleTxtBoxShouldBeVisible();
	}

	@Then("^user should be able to view the cancel and Save buttons$")
	public void user_should_be_able_to_view_the_cancel_and_Save_buttons() {
		Assert.assertTrue("Cancel button is not visible", physicianNPIConfigPage.isCancelBtnVisible());
		Assert.assertTrue("Save button is not visible", physicianNPIConfigPage.isSaveBtnVisible());
	}

	@When("^user copies Physician's Name and NPI$")
	public void user_copies_Physicians_Name_and_NPI() {
		physicianName = physicianNPIConfigPage.getFirstPhysicianName();
		physicianNPI = physicianNPIConfigPage.getFirstPhysicianNPI();
	}

	@Then("^user should be able to view \"([^\"]*)\" Physician's Name NPI message on edit pop up$")
	public void user_should_be_able_to_view_Physicians_Name_NPI_message_on_edit_pop_up(String message) {
		Assert.assertTrue("correct message, name or NPI is not visible", physicianNPIConfigPage
				.getPopUpMsgAndPhysicianNameNPI().equalsIgnoreCase(message + physicianName + " " + physicianNPI));
	}

	@When("^user copies payor of any disabled payor$")
	public void user_copies_payor_of_any_disabled_payor() {
		payor = physicianNPIConfigSteps.getAnyDisabledPayor();
	}

	@When("^user enters a search text Payor in Search Disabled Payors textbox$")
	public void user_enters_a_search_text_Payor_in_Search_Disabled_Payors_textbox() {
		physicianNPIConfigPage.enterSearchDisabledTxtBox(payor);
	}

	@Then("^user should be able to view the filtered list of payors in Total Payors Disabled$")
	public void user_should_be_able_to_view_the_filtered_list_of_payors_in_Total_Payors_Disabled() {
		Assert.assertTrue("filtered list of payors in Total Payors Disabled is not visible",
				physicianNPIConfigPage.getListOfDisabledPayorsName().size() == 1
						&& physicianNPIConfigPage.getListOfDisabledPayorsName().contains(payor));
	}

	@Then("^user should be able to view updated count in header Total Payors disabled: Count$")
	public void user_should_be_able_to_view_updated_count_in_header_Total_Payors_disabled_Count() {
		System.out.println("count:"+physicianNPIConfigPage.getCountOfTotalPayorsDisabled());
		Assert.assertTrue("Correct count is not updated", physicianNPIConfigPage
				.getCountOfTotalPayorsDisabled() == physicianNPIConfigPage.getListOfDisabledPayorsName().size());
	}
}
