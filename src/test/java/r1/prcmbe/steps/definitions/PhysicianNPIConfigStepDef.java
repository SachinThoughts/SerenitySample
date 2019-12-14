package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.PhysicianNPIConfigPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.serenity.steps.PhysicianNPIConfigSteps;

public class PhysicianNPIConfigStepDef {
	SettingsPage settingsPage;
	PhysicianNPIConfigPage physicianNPIConfigPage;
	CommonMethods commonMethods;

	@Steps
	PhysicianNPIConfigSteps physicianNPIConfigSteps;

	String physicianName, physicianNPI, payor, physicianFirstName;
	List<String> listOfPayorNames = new ArrayList<>();
	int payorSize = 0, existingEntryCount = 0, newEntryCount = 0, cntOfTotalPayorsDisabled = 0;
	private static String dbQueryFilename = "PhysicianNPIConfig";

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
		Assert.assertTrue("Edit Physician PopUp is not visible", physicianNPIConfigPage.isEditPhysicianPopUpVisible());
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
		Assert.assertTrue("Add All Payors button is not visible", physicianNPIConfigPage.isAddAllPayorsBtnVisible());
		Assert.assertTrue("Remove All Payors button is not visible",
				physicianNPIConfigPage.isRemoveAllPayorsBtnVisible());
	}

	@Then("^user should be able to view the Search Disabled Payors section$")
	public void user_should_be_able_to_view_the_Search_Disabled_Payors_section() {
		Assert.assertTrue("Search Disabled Payors section is not visible",
				physicianNPIConfigPage.isSearchDisabledTxtBoxVisible());
	}

	@Then("^user should be able to view Search Eligible Payors section$")
	public void user_should_be_able_to_view_Search_Selected_Payors_section() {
		Assert.assertTrue("Search Eligible Payors section is not visible",
				physicianNPIConfigPage.isSearchEligibleTxtBoxVisible());
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
		payorSize = physicianNPIConfigPage.getListOfDisabledPayorsName().size();
		payor = physicianNPIConfigSteps.getAnyDisabledPayor();
	}

	@When("^user enters a search text Payor in Search Disabled Payors textbox$")
	public void user_enters_a_search_text_Payor_in_Search_Disabled_Payors_textbox() {
		physicianNPIConfigPage.enterSearchDisabledTxtBox(payor);
	}

	@Then("^user should be able to view the filtered list of payors in Total Payors Disabled$")
	public void user_should_be_able_to_view_the_filtered_list_of_payors_in_Total_Payors_Disabled() {
		Assert.assertTrue("filtered list of payors in Total Payors Disabled is not visible",
				physicianNPIConfigPage.getListOfDisabledPayorsName().size() < payorSize
						&& physicianNPIConfigPage.getListOfDisabledPayorsName().contains(payor));
	}

	@Then("^user should be able to view updated count in header Total Payors disabled: Count$")
	public void user_should_be_able_to_view_updated_count_in_header_Total_Payors_disabled_Count() {
		Assert.assertTrue("Correct count is not updated", physicianNPIConfigPage
				.getCountOfTotalPayorsDisabled() == physicianNPIConfigPage.getListOfDisabledPayorsName().size());
	}

	@When("^user copies payor of any eligible payor$")
	public void user_copies_payor_of_any_eligible_payor() {
		payorSize = physicianNPIConfigPage.getListOfEligiblePayorsName().size();
		payor = physicianNPIConfigSteps.getAnyEligiblePayor();
	}

	@When("^user enters a search text Payor in Search Eligible Payors textbox$")
	public void user_enters_a_search_text_Payor_in_Search_Eligible_Payors_textbox() {
		physicianNPIConfigPage.enterSearchEligibleTxtBox(payor);
	}

	@Then("^user should be able to view the filtered list of payors in Total Eligible Payors$")
	public void user_should_be_able_to_view_the_filtered_list_of_payors_in_Total_Eligible_Payors() {
		Assert.assertTrue("filtered list of payors in Total Eligible Payors is not visible",
				physicianNPIConfigPage.getListOfEligiblePayorsName().size() < payorSize
						&& physicianNPIConfigPage.getListOfEligiblePayorsName().contains(payor));
	}

	@Then("^user should be able to view the updated count in header Total Eligible Payors: Count$")
	public void user_should_be_able_to_view_updated_count_in_header_Total_Eligible_Payors_Count() {
		Assert.assertTrue("Correct count is not updated", physicianNPIConfigPage
				.getCountOfTotalEligiblePayors() == physicianNPIConfigPage.getListOfEligiblePayorsName().size());
	}

	@When("^the user clicks on '\\*' sign for a Payor record under Total Payors Disabled section$")
	public void the_user_clicks_on_sign_for_a_Payor_record_under_Total_Payors_Disabled_section() {
		physicianNPIConfigPage.clickSearchedDisabledPayorsName(payor);
	}

	@Then("^user should be able to view the displayed payor name in Total Eligible Payors section$")
	public void user_should_be_able_to_view_the_displayed_payor_name_in_Total_Selected_Payors_section() {
		Assert.assertTrue("selected payor not present in the Total Eligible Payors section",
				physicianNPIConfigPage.getListOfEligiblePayorsName().contains(payor));
	}

	@Then("^user should be able to view the removed Payor name from Total Payors Disabled section$")
	public void user_should_be_able_to_view_the_removed_Payor_name_from_Total_Payors_Disabled_section() {
		Assert.assertTrue("selected payor is not removed from the Total Payors Disabled section",
				!physicianNPIConfigPage.getListOfDisabledPayorsName().contains(payor));
	}

	@When("^the user clicks on '\\+' sign for a Payor record under Total Eligible Payors section$|^user clicks on '\\+' button to disabled some of the payer$")
	public void the_user_clicks_on_sign_for_a_Payor_record_under_Total_Eligible_Payors__section() {
		physicianNPIConfigPage.clickSearchedEligiblePayorsName(payor);
	}

	@Then("^user should be able to view the display payor name in Total Payors Disabled section$")
	public void user_should_be_able_to_view_the_display_payor_name_in_Total_Payors_Disabled_section() {
		Assert.assertTrue("selected payor not present in the Total Payors Disabled section",
				physicianNPIConfigPage.getListOfDisabledPayorsName().contains(payor));
	}

	@Then("^user should be able to view the removed Payor name from Total Eligible Payors section$")
	public void user_should_be_able_to_view_the_removed_Payor_name_from_Total_Eligible_Payors_section() {
		Assert.assertTrue("selected payor is not removed from the Total Payors Disabled section",
				!physicianNPIConfigPage.getListOfEligiblePayorsName().contains(payor));
	}

	@When("^user clicks on <<Add All Payors button$")
	public void user_clicks_on_Add_All_Payors_button() {
		listOfPayorNames = physicianNPIConfigPage.getListOfEligiblePayorsName();
		physicianNPIConfigPage.clickAddAllPayorsBtn();
	}

	@Then("^user should be able to view the display all Payors in Total Payors Disabled section$")
	public void user_should_be_able_to_view_the_display_all_Payors_in_Total_Payors_Disabled_section() {
		Assert.assertTrue("All Payors in Total Payors Disabled section are not visible",
				physicianNPIConfigPage.getListOfDisabledPayorsName().containsAll(listOfPayorNames));
	}

	@Then("^user should be able to view removed all Payors from Total Eligible Payors section$")
	public void user_should_be_able_to_view_removed_all_Payors_from_Total_Eligible_Payors_section() {
		Assert.assertTrue("All payors are not removed from Total Eligible Payors section",
				physicianNPIConfigPage.getListOfEligiblePayorsName().isEmpty());
	}

	@When("^user clicks on Remove All Payors>> button$")
	public void user_clicks_on_Remove_All_Payors_button() {
		listOfPayorNames = physicianNPIConfigPage.getListOfDisabledPayorsName();
		physicianNPIConfigPage.clickRemoveAllPayorsBtn();
	}

	@Then("^user should be able to view the display all Payors in Total Eligible Payors section$")
	public void user_should_be_able_to_view_the_display_all_Payors_in_Total_Eligible_Payors_section() {
		Assert.assertTrue("All Payors in Total Eligible Payors section are not visible",
				physicianNPIConfigPage.getListOfEligiblePayorsName().containsAll(listOfPayorNames));
	}

	@Then("^user should be able to view removed all Payors from Total Disabled Payors section$")
	public void user_should_be_able_to_view_removed_all_Payors_from_Total_Disabled_Payors_section() {
		Assert.assertTrue("All payors are not removed from Total Payors Disabled section",
				physicianNPIConfigPage.getListOfDisabledPayorsName().isEmpty());
	}

	@When("^user runs the \"([^\"]*)\" query for Physician NPI$")
	public void user_runs_the_something_query(String queryName) throws Exception {
		physicianFirstName = physicianNPIConfigPage.getFirstPhysiciansFirstName();
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), physicianFirstName));
	}

	@When("^user view existing entry in physician in EligibilityNPIDisabled column$")
	public void user_view_existing_entry_in_physician_in_EligibilityNPIDisabled_column() {
		try {
			while (DatabaseConn.resultSet.next()) {
				existingEntryCount++;
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("results are not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
	}

	@When("^user clicks on save button$")
	public void user_clicks_on_save_button() {
		physicianNPIConfigPage.clickSaveBtn();
	}

	@Then("^user should be able to view the success message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_success_message(String message) {
		Assert.assertTrue("'" + message + "' message is not visible",
				physicianNPIConfigPage.getSaveConfigMsg().equals(message.toUpperCase()));
	}

	@When("^user gets count of the Total Payors Disabled$")
	public void user_gets_count_of_the_Total_Payors_Disabled() {
		cntOfTotalPayorsDisabled = physicianNPIConfigPage.getCountOfTotalPayorsDisabled();
	}

	@Then("^user is able to see the number of payer which are disabled in total disabled payer column$")
	public void user_is_able_to_see_the_number_of_payer_which_are_disabled_in_total_disabled_payer_column() {
		Assert.assertTrue("Number of payors which are disabled are not visible in total disabled payer column",
				cntOfTotalPayorsDisabled == physicianNPIConfigPage.firstPhyTotalDisabledCount());
	}

	@Then("^user should be able to view the entry in physician in EligibilityNPIDisabled column$")
	public void user_should_be_able_to_view_the_entry_in_physician_in_EligibilityNPIDisabled_column() {
		try {
			while (DatabaseConn.resultSet.next()) {
				newEntryCount++;
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("results are not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("New entry not added in EligibilityNPIDisabled",
				newEntryCount > existingEntryCount && newEntryCount == cntOfTotalPayorsDisabled);
	}
	
	@Then("^physician list should be displayed with the columns$")
	public void physician_list_should_be_displayed_with_the_columns(DataTable expectedColumnNames) {
		List<String> listOfColumnnNames = expectedColumnNames.asList(String.class);
		Assert.assertTrue("Failed to verify Phiysician column names",
				physicianNPIConfigPage.getListOfPhysicianColumnNames().containsAll(listOfColumnnNames));
	}

	@Then("^user should be able to view pagination should be displayed$")
	public void user_shoule_be_able_to_view_pagination_should_be_displayed() {
		Assert.assertTrue("Failed to view pagination Control", physicianNPIConfigPage.isPaginationCtrlVisible());
	}

	@Then("^user should be able to view header and Footer should be displayed like$")
	public void user_shoule_be_able_to_view_header_and_Footer_should_be_displayed_like(
			DataTable expectedHeaderAndFooterValues) {
		List<String> listOfHeaderAndFooterValues = expectedHeaderAndFooterValues.asList(String.class);
		Assert.assertTrue("failed to verify Header and Footer",
				physicianNPIConfigPage.getHeaderOfPagination().containsAll(listOfHeaderAndFooterValues));
		Assert.assertTrue("failed to verify Header and Footer",
				physicianNPIConfigPage.getFooterOfPagination().containsAll(listOfHeaderAndFooterValues));
	}

	@Then("^user shoule be able to view page Header \"([^\"]*)\" should be displayed$")
	public void user_shoule_be_able_to_view_page_Header_should_be_displayed(String expectedHeader) {
		Assert.assertTrue("Failed to verify" + expectedHeader,
				physicianNPIConfigPage.getFacilityPhysicianHeader().equals(expectedHeader));
	}

	@Then("^user shoule be able to view the physicians records should be sorted based on Total Payors Disabled desc$")
	public void user_shoule_be_able_to_view_the_physicians_records_should_be_sorted_based_on_Total_Payors_Disabled_desc() {
		List<String> listOfTotalPayorDisabledDesc = new ArrayList<String>(
				physicianNPIConfigPage.getCountOfTotalDisabledPayors());
		Collections.sort(listOfTotalPayorDisabledDesc, Collections.reverseOrder());
		Assert.assertTrue("failed to verify physicians records should be sorted based on Total Payors Disabled desc",
				listOfTotalPayorDisabledDesc.equals(physicianNPIConfigPage.getCountOfTotalDisabledPayors()));
	}

	@Then("^user should be able to view Edit Links$")
	public void user_should_be_able_to_view_Edit_Links() {
		Assert.assertFalse("Failed to view Edit links on Physician Search Page",
				physicianNPIConfigPage.isListOfEditBtnLinkEmpty());
	}

	@Then("^user should be able to view the title Physician Search is displayed\\.$")
	public void user_should_be_able_to_view_the_title_Physician_Search_is_displayed() {
		Assert.assertTrue("failed to view physician Search title",
				physicianNPIConfigPage.isphysicianSearchTitleVisible());
	}

	@When("^user clicks on search text field on  PRCM NPI configuration page$")
	public void user_clicks_on_search_text_field_on_PRCM_NPI_configuration_page() {
		physicianNPIConfigPage.clickOnPhysicianSearchTxtField();
	}

	@Then("^user search on the basis of Physician's and user should be able to search successfully on  PRCM NPI configuration page$")
	public void user_search_on_the_basis_of_Physician_s(DataTable expectedphysicianInfo) {
		List<String> listOfphysicianInfo = expectedphysicianInfo.asList(String.class);
		physicianNPIConfigPage.enterPhysicianSearchTxtBox(listOfphysicianInfo.get(0));
		Assert.assertTrue("failed to verify Physician's Name",
				physicianNPIConfigPage.getListofSearchedPhisicianInfo().contains(listOfphysicianInfo.get(0)));
		physicianNPIConfigPage.enterPhysicianSearchTxtBox(listOfphysicianInfo.get(1));
		Assert.assertTrue("Failed to verify NPI",
				physicianNPIConfigPage.getListofSearchedPhisicianInfo().contains(listOfphysicianInfo.get(1)));
		physicianNPIConfigPage.enterPhysicianSearchTxtBox(listOfphysicianInfo.get(2));
		Assert.assertTrue("Failed to verify Facility physician Id",
				physicianNPIConfigPage.getListofSearchedPhisicianInfo().contains(listOfphysicianInfo.get(2)));
	}
}