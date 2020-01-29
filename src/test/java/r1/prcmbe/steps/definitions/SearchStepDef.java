package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
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
	AccountInformationPage accInfoPage;

	@Steps
	SearchPageSteps searchPageSteps;

	@Steps
	FinancialInfoSteps financialInfoSteps;

	String dbQueryFilename = "Search", dbMRN, lastName, firstName, dbClaimNo, dbResult, dbInvoiceNumber, dbEncounterId,
			dbLastName, dbFirstName, dbSSN, visitNumber, invoiceNumber, sSN, mRN;
	List<String> listOfGridColumnsOnUI = new ArrayList<>();
	List<String> dbListOfColumns = new ArrayList<>();
	List<String> dbListOfNames = new ArrayList<>();
	List<String> expectedListOfGridColumns = new ArrayList<>();
	List<String> dbListOfEncounterID = new ArrayList<>();
	List<String> dbListOfInvoiceNumber = new ArrayList<>();
	List<String> dbListOfMRN = new ArrayList<>();

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user clicks on R1_Decision link$")
	public void user_clicks_on_R1_Decision_link() {
		billingAndFollowUpPage.clickOnR1DecisionLink();
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

	@Then("^user should be able to view Invoice Number selected by default in Search By drop down$")
	public void user_should_be_able_to_view_Invoice_Number_selected_by_default_in_Search_By_drop_down() {
		Assert.assertTrue("Invoice number is not selected by default",
				searchPage.getDefaultSelectedVal().equals("Invoice Number"));
	}

	/*
	 * @When("^user selects (.*) from Search By drop down$") public void
	 * user_selects_Visit_Number_from_Search_By_drop_down(String dropdown) {
	 * searchPage.searchBySelectText(dropdown); }
	 */

	/*
	 * @When("^user enters invalid value in (.*) textbox $") public void
	 * user_enters_invalid_value_in_$_textbox(String invalidVal) { if
	 * (searchPage.isVisitTxtFieldVisible()) {
	 * searchPage.enterVisitNumber(invalidVal); } else if
	 * (searchPage.isMRNTxtFieldVisible()) { searchPage.enterMRN(invalidVal); } else
	 * if (searchPage.isClaimNumberTxtFieldVisible()) {
	 * searchPage.enterClaimNumber(invalidVal); } else if
	 * (searchPage.isInvoiceNumberTxtFieldVisible()) {
	 * searchPage.enterInvoiceNumber(invalidVal); } else if
	 * (searchPage.isFirstNameTxtFieldVisible() &&
	 * searchPage.isLastNameTxtFieldVisible()) {
	 * searchPage.enterLastNameTxtBox(invalidVal);
	 * searchPage.enterFirstName(invalidVal); } else if
	 * (searchPage.isSSNTxtFieldVisible()) { searchPage.enterSSN(invalidVal); } else
	 * { Assert.assertTrue("Search text box not visible", false); } }
	 */
	@When("^user clicks on Submit button$")
	public void user_clicks_on_Submit_button() {
		searchPage.clickSubmitBtn();
	}

	/*
	 * @Then("^user should be able to view error message (.*)$")/ public void
	 * user_should_be_able_to_view_error_message(String errorMsg) {
	 * Assert.assertTrue("'" + errorMsg + "' message is not visible",
	 * searchPage.getErrorMsg().equalsIgnoreCase(errorMsg));
	 * 
	 * }
	 */

	@When("^user selects \"([^\"]*)\" from operator dropdown$")
	public void user_selects_operator_from_operator_dropdown(String operator) {
		if (!searchPage.isSSNTxtFieldVisible())
			searchPage.selectOperatorValue(operator);
	}

	/*
	 * @When("^user enters less than 5 characters in (.*) textbox$") public void
	 * user_enters_less_than_characters_in_textbox(String value) { if
	 * (searchPage.isVisitTxtFieldVisible()) { searchPage.enterVisitNumber(value); }
	 * else if (searchPage.isMRNTxtFieldVisible()) { searchPage.enterMRN(value); }
	 * else if (searchPage.isClaimNumberTxtFieldVisible()) {
	 * searchPage.enterClaimNumber(value); } else if
	 * (searchPage.isInvoiceNumberTxtFieldVisible()) {
	 * searchPage.enterInvoiceNumber(value); } else if
	 * (searchPage.isSSNTxtFieldVisible()) { searchPage.enterSSN(value); } else {
	 * Assert.assertTrue("Search text box not visible", false); } }
	 */

	@Then("^user should able to view tool-tip message (.*)$")
	public void user_should_able_to_view_tool_tip_message_Please_add_five_or_more_characters(String toolTipMessage) {
		Assert.assertTrue("Tooltip message '" + toolTipMessage + "' is not visible",
				searchPage.getToolTipText().equals(toolTipMessage));
	}

	@Then("^user should be able to view Submit Button in disabled state$")
	public void user_should_be_able_to_view_Submit_Button_in_disabled_state() {
		Assert.assertFalse("Submit button is enabled", searchPage.isSubmitBtnEnabled());
	}

	/*
	 * @When("^user enters less than 5 characters in (.*) textbox$")
	 * 
	 * @And("^user enters more than or equal to 5 characters (.*) in textbox$")
	 * public void user_enters_more_than_or_equal_to_characters_in_textbox(String
	 * value) { if (searchPage.isVisitTxtFieldVisible()) {
	 * searchPage.enterVisitNumber(value); } else if
	 * (searchPage.isMRNTxtFieldVisible()) { searchPage.enterMRN(value); } else if
	 * (searchPage.isClaimNumberTxtFieldVisible()) {
	 * searchPage.enterClaimNumber(value); } else if
	 * (searchPage.isInvoiceNumberTxtFieldVisible()) {
	 * searchPage.enterInvoiceNumber(value); } else if
	 * (searchPage.isSSNTxtFieldVisible()) { searchPage.enterSSN(value); } else {
	 * Assert.assertTrue("Search text box not visible", false); } }
	 */

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

	@When("^user run the query and fetch the Invoice Number \"([^\"]*)\"$")
	public void user_run_the_query_and_fetch_the_Invoice_Number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("Invoicenumber");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Invoice number is not fetched from DB.\nThe Technical Error is:\n" + sQLException,
					false);
		}
		financialInfoSteps.log("Fetched Invoice Number from Database is " + dbInvoiceNumber);
	}

	@When("^user select \"([^\"]*)\" from Search By dropdown$")
	public void user_select_from_Search_By_dropdown(String drpdwnVal) {
		searchPage.searchBySelectText(drpdwnVal);
	}

	@When("^user enters the query result in Invoice Number search textbox and can view the same invoice number of selected facility or different facility$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_and_can_view_the_same_invoice_number_of_selected_facility_or_different_facility() {
		if (searchPage.noAccInQueueMsgIsVisbile()) {
			searchPage.searchBySelectText("Invoice Number");
			searchPage.enterInvoiceNumber(dbInvoiceNumber);
			searchPage.clickSubmitBtn();
			searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber);
		}
	}

	@Given("^User is on Internal search page$|^User is on R1 Account information page$")
	public void user_is_on_Internal_search_page() {
		if (searchPage.noAccInQueueMsgIsVisbile()) {
			try {
				user_run_the_query_and_fetch_the_Invoice_Number("SearchInternal_391031_SQL1");
			} catch (Exception e) {
				 Assert.assertTrue("Invoice number is not fetched from DB.\nThe Technical Error is:\n" + e, false);
			}
			searchPage.searchBySelectText("Invoice Number");
			searchPage.enterInvoiceNumber(dbInvoiceNumber);
			searchPage.clickSubmitBtn();
			searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber);
		}
		accInfoPage.verifyPatientDetailsSectionVisible();
		 searchPage.searchByTextShouldBeVisible();
	}

	@Given("^user is on R1 Decision search page$|^user should be able to view R1D Search page$")
	public void user_is_on_R1_Decision_search_page() {
		Assert.assertTrue("User is not navigated on R1 D Search Page",
				searchPage.getSearchPageTitle().contains("R1_Decision - Search"));
		searchPage.patientDetailsSectionShouldNotVisible();
	}

	/*
	 * @When("^user selects \"([^\"]*)\" from Search By dropdown$") public void
	 * user_selects_option_from_Search_By_dropdown(String dropdownVal) {
	 * searchPage.searchBySelectText(dropdownVal); }
	 */

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
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();

		Assert.assertTrue("All the grid columns are not visible",
				expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());

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
		Assert.assertTrue("Names displayed on UI does not match with database",
				searchPageSteps.verifyNameOnUIWithDatabaseResult(dbListOfNames));
	}

	/*
	 * @When("^user selects (.*) from Operator dropdown$") public void
	 * user_selects_from_Operator_dropdown(String operator) {
	 * searchPage.selectOperatorValue(operator); }
	 */
	@When("^user runs the (.*) query for search$")
	public void user_runs_the_query_for_search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilename), CommonMethods.loadProperties("prcmBeUsername")));
	}

	/*
	 * @When("^user enters value (.*) in (.*) textbox$") public void
	 * user_enters_in_textbox(String textValue, String searchByOption) { if
	 * (searchPage.isVisitTxtFieldVisible()) { this.visitNumber = textValue;
	 * searchPage.enterVisitNumber(this.visitNumber); } else if
	 * (searchPage.isInvoiceNumberTxtFieldVisible()) { this.invoiceNumber =
	 * textValue; searchPage.enterInvoiceNumber(this.invoiceNumber); } else if
	 * (searchPage.isSSNTxtFieldVisible()) { this.sSN = textValue;
	 * searchPage.enterSSN(this.sSN); } else if (searchPage.isMRNTxtFieldVisible())
	 * { this.mRN = textValue; searchPage.enterMRN(this.mRN); } else {
	 * Assert.assertTrue(searchByOption + " text box is not visible", false); } }
	 */

	@Then("^user should be able to view the grid with following columns$")
	public void user_should_be_able_to_view_the_grid_with_following_columns(DataTable resultColumns) {
		expectedListOfGridColumns = resultColumns.asList(String.class);
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

	@When("^user enter the query result of in Invoice Number search textbox$")
	public void user_enter_the_query_result_of_in_Invoice_Number_search_textbox() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@Then("^user should be able to navigate to the R1D account page for searched Invoice Number$")
	public void user_should_be_able_to_navigate_to_the_R1_D_account_page_for_searched_Invoice_Number() {
		Assert.assertTrue("User is not navigated to the R1D account page for Searched Invoice Number",
				searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber));
	}

	@When("^user runs the (.*) query to fetch account data$")
	public void user_runs_the_query_to_fetch_account_data(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@When("^user enters the query result in Visit Number search textbox$")
	public void user_enters_the_query_result_in_Visit_Number_search_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbEncounterId = DatabaseConn.resultSet.getString("encounterid");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Visit number is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		searchPage.enterVisitNumber(dbEncounterId);
	}

	@Then("^user should be able to view the grid with following columns if they are visible else verify the searched account$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_and_verify_the_searched_account(
			DataTable resultColumns) {
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		if (searchPage.isSearchAccTableVisible()) {
			listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
			Assert.assertTrue("All the grid columns are not visible",
					expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		} else {
			financialInfoSteps
					.log("searched table is not visible with grid, single account present for the search result");
		}
		Assert.assertTrue("Incorrect account is searched",
				dbEncounterId.equalsIgnoreCase(searchPage.getPatientAccountNo()));
	}

	@When("^user runs the (.*) query to search Visit number$")
	public void user_runs_the_query_to_search_Visit_number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbEncounterId));
	}

	@Then("^user should be able to view the same result in grid as SQL result for searched Visit number$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_searched_Visit_number() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbEncounterId = DatabaseConn.resultSet.getString("encounterid");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("encounterid is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Visit number or Invoice number on UI does not match with database",
				searchPage.getPatientAccountNo().contains(dbEncounterId));
	}
	/*
	 * @When("^user runs the (.*) query to fetch name for search$") public void
	 * user_runs_the_query_to_fetch_name_for_search(String queryName) throws
	 * Exception { DatabaseConn.serverConn(DatabaseConn.serverName,
	 * DatabaseConn.databaseName, String.format(commonMethods.loadQuery(queryName,
	 * dbQueryFilename))); }
	 */

	@Then("^user runs the (.*) query to fetch name using dbfirstname and dblastname$")
	public void user_runs_the_query_to_fetch_name_using_dbfirstname_and_dblastname(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String
				.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbLastName + "%", dbFirstName + "%"));
	}

	@Then("^user should be able to view the grid with following columns for Last Name/First Name search for database firstname lastname values$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_for_LastName_FirstName_search_for_database_firstname_lastname_values(
			DataTable resultColumns) {
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();

		Assert.assertTrue("All the grid columns are not visible",
				expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());

		Assert.assertTrue("Last name or first name does not match with the searched character",
				searchPageSteps.verifyOnlyLastName(dbLastName) && searchPageSteps.verifyOnlyFirstName(dbFirstName));
	}

	@Then("^user should be able to fetch Firstname and Lastname from the query$")
	public void user_should_be_able_to_fetch_Firstname_and_Lastname_from_the_query() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbLastName = DatabaseConn.resultSet.getString("lastname");
				dbFirstName = DatabaseConn.resultSet.getString("firstname");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue(
					"First name or Last name is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("First name or Last name is not fetched from DB",
				!dbLastName.isEmpty() && !dbFirstName.isEmpty());
	}

	@When("^user enters the fetched Lastname in Last Name textbox$")
	public void user_enters_the_fetched_Lastname_in_Last_Name_textbox() {
		lastName = dbLastName;
		searchPage.enterLastName(dbLastName);
	}

	@When("^user enters the fetched Firstname in First Name textbox$")
	public void user_enters_the_fetched_Firstname_in_First_Name_textbox() {
		firstName = dbFirstName;
		searchPage.enterFirstName(dbFirstName);
	}

	@Then("^user should be able to view the grid with following columns and verify searched name$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_and_verify_searched_name(
			DataTable resultColumns) {
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		if (searchPage.isSearchAccTableVisible()) {
			listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
			Assert.assertTrue("All the grid columns are not visible",
					expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
			Assert.assertTrue("Last name or first name does not match with the searched character",
					searchPageSteps.verifyOnlyLastName(lastName) && searchPageSteps.verifyOnlyFirstName(firstName));
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		} else {
			financialInfoSteps
					.log("searched table is not visible with grid, single account present for the search result");
		}
		Assert.assertTrue("Incorrect account is searched",
				searchPageSteps.verifyPatientFirstAndLastName(lastName, firstName));
	}

	@Then("^user should be able to view the same names in grid as SQL result$")
	public void user_should_be_able_to_view_the_same_names_in_grid_as_SQL_result() throws SQLException {
		Assert.assertTrue("Same searched names are not visible in the SQL result",
				searchPageSteps.verifySearchedNamesWithDatabase(lastName, firstName));
	}

	@When("^user enters the query result in SSN textbox$")
	public void user_enters_the_query_result_in_SSN_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbSSN = DatabaseConn.resultSet.getString("SSN");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("SSN is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		searchPage.enterSSN(dbSSN);
	}

	@Then("^user should be able to view the grid with following columns and verify searched SSN$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_and_verify_searched_SSN(
			DataTable resultColumns) {
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		if (searchPage.isSearchAccTableVisible()) {
			listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
			Assert.assertTrue("All the grid columns are not visible",
					expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		} else {
			financialInfoSteps
					.log("searched table is not visible with grid, single account present for the search result");
		}
		Assert.assertTrue("Incorrect account is searched", searchPage.getPatientSSN().contains(dbSSN));
	}

	@When("^user runs the (.*) query to fetch SSN result$")
	public void user_runs_the_query_to_fetch_SSN_result(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbSSN));
	}

	@Then("^user should be able to view the same SSN in grid as SQL result$")
	public void user_should_be_able_to_view_the_same_SSN_in_grid_as_SQL_result() throws SQLException {
		Assert.assertTrue("Same searched SSN are not visible in the SQL result",
				searchPageSteps.verifySearchedSSNWithDatabase(dbSSN));
	}

	@When("^user enters the query result in Medical Record Number textbox$")
	public void user_enters_the_query_result_in_Medical_Record_Number_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbMRN = DatabaseConn.resultSet.getString("FacilityPatientID");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("MRN is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		searchPage.enterMRN(dbMRN);
	}

	@Then("^user should be able to view the grid with following columns if they are visible else verify the searched account with MRN$")
	public void user_should_be_able_to_view_the_grid_with_following_columns_and_verify_the_searched_account_with_MRN(
			DataTable resultColumns) {
		List<String> expListOfGridColumns = resultColumns.asList(String.class);
		if (searchPage.isSearchAccTableVisible()) {
			listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
			Assert.assertTrue("All the grid columns are not visible",
					expListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
			searchPage.clickSearchInvoiceIdOrVisitNumber();
		} else {
			financialInfoSteps
					.log("searched table is not visible with grid, single account present for the search result");
		}
		Assert.assertTrue("Account with Incorrect MRN number is searched", searchPage.getMRNText().contains(dbMRN));
	}

	@When("^user runs the (.*) query for MRN search$")
	public void user_runs_the_query_for_MRN_search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbMRN));
	}

	@Then("^user should be able to view the same MRN in grid as SQL result$")
	public void user_should_be_able_to_view_the_same_MRN_in_grid_as_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbMRN = DatabaseConn.resultSet.getString("facilitypatientid");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("MRN is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("MRN on UI does not match with database", searchPage.getMRNText().contains(dbMRN));
	}

	@Then("^user should be able to view the \"([^\"]*)\" error message$")
	public void user_should_be_able_to_view_the_error_message(String errorMsg) {
		Assert.assertTrue("'" + errorMsg + "' message is not visible",
				searchPage.getErrorMsg().equalsIgnoreCase(errorMsg));
	}

	@When("^user runs the (.*) query to fetch firstname and lastname$")
	public void user_runs_the_query_to_fetch_firstname_and_lastname(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String
				.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbLastName + "%", dbFirstName + "%"));
	}

	@Then("^user should be able to navigate to the R1D account page for searched visit Number$")
	public void user_should_be_able_to_navigate_to_the_R1D_account_page_for_searched_visit_Number() {
		Assert.assertTrue("User is not navigated on R1D account page for searched visit number",
				searchPageSteps.verifyEncounterId(dbEncounterId));
	}

	@Then("^user should be able to navigate to the R1D account page for searched Visit Number and verify invoice number should not be visible$")
	public void user_should_be_able_to_navigate_to_the_R1D_account_page_for_searched_Visit_Number_and_verify_invoice_number_should_not_be_visible() {
		Assert.assertTrue("User is not navigated on R1D account page for searched visit number",
				searchPageSteps.verifyEncounterId(dbEncounterId)&&!searchPage.isInvoiceNumberVisible());
	}

	@When("^user runs query and fetch visit number(.*)$")
	public void user_runs_query_and_fetch_visit_number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), "%" + visitNumber));
	}

	@Then("^user should be able to view the same result in grid as SQL result for visit number$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_visit_number() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbListOfEncounterID.add(DatabaseConn.resultSet.getString("Visit #"));
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("encounterid is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Fetched list of Visit number doesnt contains entered Visit number",
				searchPageSteps.verifyEncounterIdOnUIWithDatabaseResult(dbListOfEncounterID));
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
	}

	@When("^user runs query and fetch invoice number(.*)$")
	public void user_runs_query_and_fetch_invoice_number_(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), "%" + invoiceNumber));
	}

	@Then("^user should be able to view the same result in grid as SQL result for invoice number$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_invoice_number() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbListOfInvoiceNumber.add(DatabaseConn.resultSet.getString("Invoice #"));
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("encounterid is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Fetched list of visit number doesnt contains entered visit number",
				searchPageSteps.verifyInvoiceNumberOnUIWithDatabaseResult(dbListOfInvoiceNumber));

		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();

		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
	}

	@When("^user runs query and fetch MRN number(.*)$")
	public void user_runs_query_and_fetch_MRN_number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), "%" + mRN));
	}

	@Then("^user should be able to view the same result in grid as SQL result for MRN number$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_MRN_number() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbListOfMRN.add(DatabaseConn.resultSet.getString("MRN"));
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("encounterid is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
		Assert.assertTrue("Fetched list of MRN number doesnt contains entered MRN",
				searchPageSteps.verifyMRNOnUIWithDatabaseResult(dbListOfMRN));
		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
	}

	@When("^user enters (.*) in search textbox$")
	public void user_enters_text_in_search_textbox(String textValue) {
		if (searchPage.isVisitTxtFieldVisible()) {
			this.visitNumber = textValue;
			searchPage.enterVisitNumber(this.visitNumber);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			this.invoiceNumber = textValue;
			searchPage.enterInvoiceNumber(this.invoiceNumber);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			this.sSN = textValue;
			searchPage.enterSSN(this.sSN);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			this.mRN = textValue;
			searchPage.enterMRN(this.mRN);
		} else if (searchPage.isFirstNameTxtFieldVisible() && searchPage.isLastNameTxtFieldVisible()) {
			searchPage.enterLastNameTxtBox(textValue);
			searchPage.enterFirstName(textValue);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}
}