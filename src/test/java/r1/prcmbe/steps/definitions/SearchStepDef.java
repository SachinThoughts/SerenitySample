package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import net.serenitybdd.core.pages.PageObject;
import cucumber.api.DataTable;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class SearchStepDef extends PageObject {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	EnvironmentVariables environmentVariables;
	CommonMethods commonMethods;

	@Steps
	SearchPageSteps searchPageSteps;

	@Steps
	FinancialInfoSteps financialInfoSteps;

	String dbQueryFilename = "Search", dbMRN, lastName, firstName, dbClaimNo, dbResult;
	List<String> listOfGridColumnsOnUI = new ArrayList<>();
	List<String> dbListOfColumns = new ArrayList<>();
	List<String> dbListOfNames = new ArrayList<>();

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user clicks on R1_Decision link$")
	public void user_clicks_on_R1_Decision_link() {
		billingAndFollowUpPage.clickOnR1DecisionLink();
	}

	@Then("^user should be able to view R1D Search page$")
	public void user_should_be_able_to_view_R1_D_Search_page() {
		Assert.assertTrue("User is not navigated on R1 D Search Page",
				searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision - Search"));
	}

	@Then("^user should be able to view message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message(String expectedMessage) {
		Assert.assertTrue("'" + expectedMessage + "' message not visible",
				searchPage.getNoAccountsMessage().replace("\n", "").equals(expectedMessage));
	}

	@When("^user hovers on R1_Decision link$|^user hover on R1_Decision link$")
	public void user_hovers_on_R1_Decision_link() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
	}

	@Given("^user is on \"([^\"]*)\" page$")
	public void user_is_on_page(String searchPageTitle) {
		Assert.assertTrue(searchPageTitle + " page is not displayed",
				searchPage.getSearchPageTitle().contains(searchPageTitle));
	}

	@Then("^user should be able to view Invoice Number selected by default in Search By drop down$")
	public void user_should_be_able_to_view_Invoice_Number_selected_by_default_in_Search_By_drop_down() {
		Assert.assertTrue("Invoice number is not selected by default",
				searchPage.getDefaultSelectedVal().equals("Invoice Number"));
	}

	@When("^user selects (.*) from Search By drop down$")
	public void user_selects_Visit_Number_from_Search_By_drop_down(String dropdown) {
		searchPage.searchBySelectText(dropdown);
	}

	@When("^user enters invalid value in (.*) textbox $")
	public void user_enters_invalid_value_in_$_textbox(String invalidVal) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(invalidVal);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(invalidVal);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(invalidVal);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(invalidVal);
		} else if (searchPage.isFirstNameTxtFieldVisible() && searchPage.isLastNameTxtFieldVisible()) {
			searchPage.enterLastNameTxtBox(invalidVal);
			searchPage.enterFirstName(invalidVal);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(invalidVal);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@When("^user clicks on Submit Button$")
	public void user_clicks_on_Submit_Button() {
		searchPage.clickSubmitBtn();
	}

	@Then("^user should be able to view error message (.*)$")
	public void user_should_be_able_to_view_error_message(String errorMsg) {
		Assert.assertTrue("'" + errorMsg + "' message is not visible",
				searchPage.getErrorMsg().equalsIgnoreCase(errorMsg));

	}

	@When("^user selects \"([^\"]*)\" operator from operator dropdown$")
	public void user_selects_operator_from_operator_dropdown(String operator) {
		if (!searchPage.isSSNTxtFieldVisible())
			searchPage.selectOperatorValue(operator);
	}

	@When("^user enters less than 5 characters in (.*) textbox$")
	public void user_enters_less_than_characters_in_textbox(String value) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(value);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(value);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(value);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(value);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(value);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@Then("^user should able to view tool-tip message (.*)$")
	public void user_should_able_to_view_tool_tip_message_Please_add_five_or_more_characters(String toolTipMessage) {
		Assert.assertTrue("Tooltip message '" + toolTipMessage + "' is not visible",
				searchPage.getToolTipText().equals(toolTipMessage));
	}

	@Then("^user should be able to view Submit Button in disabled state$")
	public void user_should_be_able_to_view_Submit_Button_in_disabled_state() {
		Assert.assertFalse("Submit button is enabled", searchPage.isSubmitBtnEnabled());
	}

	@When("^user enters more than or equal to 5 characters (.*) in textbox$")
	public void user_enters_more_than_or_equal_to_characters_in_textbox(String value) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(value);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(value);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(value);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(value);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(value);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@Then("^user should be able to view Submit Button in enabled state$")
	public void user_should_be_able_to_view_Submit_Button_in_enabled_state() {
		Assert.assertTrue("Submit Button is disabled", searchPage.isSubmitBtnEnabled());
	}

	@Then("^user should be able to view message \"([^\"]*)\" (.*)$")
	public void user_should_be_able_to_view_message_Visit_Number(String expectedMsg, String fieldName) {
		Assert.assertTrue("Validation message " + expectedMsg + " " + fieldName + "is not visible",
				searchPage.getErrorMsg().equals(expectedMsg + " " + fieldName));
	}

	@Then("^user should not able to view tool-tip message$")
	public void user_should_not_able_to_view_tool_tip_message() {
		Assert.assertFalse("Tooltip is visible", searchPage.isToolTipVisible());
	}

	@Given("^user is on R1 Decision search page$")
	public void user_is_on_R1_Decision_search_page() {
		Assert.assertTrue("User is not navigated on R1 D Search Page",
				searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision - Search"));
	}

	@When("^user selects \"([^\"]*)\" from Search By dropdown$")
	public void user_selects_option_from_Search_By_dropdown(String dropdownVal) {
		searchPage.searchBySelectText(dropdownVal);
	}

	@When("^user runs the (.*) query to fetch name$")
	public void user_runs_the_query_to_fetch_name(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), lastName + "%", firstName + "%"));
	}

	@When("^user enters (.*) text in Last Name textbox$")
	public void user_enters_text_in_Last_Name_textbox(String lastName) {
		this.lastName = lastName;
		searchPage.enterLastName(this.lastName);
	}

	@When("^user enters (.*) text in First Name textbox$")
	public void user_enters_text_in_First_Name_textbox(String firstName) {
		this.firstName = firstName;
		searchPage.enterFirstName(this.firstName);
	}

	@Then("^user should be able to view the grid with following columns for Last Name/First Name search$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_for_LastName_FirstName_search(
			DataTable resultColumns) {
		List<String> expectedListOfGridColumns = resultColumns.asList(String.class);
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();

		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());

		Assert.assertTrue("Last name or first name does not match with the searched character",
				searchPageSteps.verifyOnlyLastName(lastName) && searchPageSteps.verifyOnlyFirstName(firstName));
	}

	@Then("^user should be able to view the same result in grid as SQL result for Last Name/First Name$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_Last_Name_First_Name() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbListOfNames.add(DatabaseConn.resultSet.getString("Name"));
			}
			Assert.assertTrue("grid columns from Database does not match with UI",
					listOfGridColumnsOnUI.containsAll(searchPageSteps.fetchColumnNamesFromDatabaseResult()));
		} catch (SQLException sQLException) {
			Assert.assertTrue("Names are not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		financialInfoSteps.log("List of names from DB:\n" + dbListOfNames);
		Assert.assertTrue("Names displayed on UI does not match with database",
				searchPageSteps.verifyNameOnUIWithDatabaseResult(dbListOfNames));
	}

	@When("^user selects (.*) from Operator dropdown$")
	public void user_selects_from_Operator_dropdown(String operator) {
		searchPage.selectOperatorValue(operator);
	}

	@When("^user runs the (.*) query for search$")
	public void user_runs_the_query_for_search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilename), CommonMethods.loadProperties("prcmBeUsername")));
	}

	@When("^user enters (.*) in (.*) textbox$")
	public void user_enters_in_textbox(String textValue, String searchByOption) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(textValue);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(textValue);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(textValue);
		} else {
			Assert.assertTrue(searchByOption + " text box is not visible", false);
		}
	}

	@Then("^user should be able to view the grid with following columns$")
	public void user_should_be_able_to_view_the_grid_with_following_columns(DataTable resultColumns) {
		List<String> expectedListOfGridColumns = resultColumns.asList(String.class);
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
	}

	@Then("^user should be able to view only those facilities in Facility Code column which are coming in SQL result$")
	public void user_should_be_able_to_view_only_those_facilities_in_Facility_Code_column_which_are_coming_in_SQL_result() {
		List<String> dbListOfFacility = new ArrayList<>();
		try {
			while (DatabaseConn.resultSet.next()) {
				dbListOfFacility.add(DatabaseConn.resultSet.getString("Code"));
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Facility codes are not fetched from DB.\nThe Technical Error is:\n" + sQLException,
					false);
		}
		Assert.assertTrue("Facility code from database and UI does not match",
				dbListOfFacility.containsAll(searchPage.getlistOfSearchedFacility()));
	}
}
