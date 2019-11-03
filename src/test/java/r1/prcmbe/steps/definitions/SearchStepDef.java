package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class SearchStepDef {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	EnvironmentVariables environmentVariables;
	CommonMethods commonMethods;

	@Steps
	SearchPageSteps searchPageSteps;

	String dbQueryFilename = "Search", dbMRN, lastName, firstName, dbClaimNo, dbResult;
	List<String> listOfGridColumnsOnUI = new ArrayList<>();
	List<String> dbListOfColumns = new ArrayList<>();
	List<String> listOfNamesOnUI = new ArrayList<>();
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

	@When("^user hover on R1_Decision link$")
	public void user_hover_on_R1_Decision_link() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
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

	@Then("^user should be able to view the grid with following columns$")
	public void user_should_be_able_to_view_the_grid_with_following_columns(DataTable resultColumns) {
		List<String> expectedListOfGridColumns = resultColumns.asList(String.class);
		listOfGridColumnsOnUI = searchPage.getListOfSrchAccTblHeaders();
		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI) && !listOfGridColumnsOnUI.isEmpty());
	}

	@Then("^user should be able to view the same result in grid as SQL result for Last Name/First Name$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_Last_Name_First_Name() {
		listOfNamesOnUI = searchPage.getListOfSearchedNames();
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
				dbListOfNames.containsAll(listOfNamesOnUI));
	}

	@When("^user selects (.*) from Operator dropdown$")
	public void user_selects_from_Operator_dropdown(String operator) {
		searchPage.selectOperatorValue(operator);
	}

	@When("^user runs the (.*) query for search$")
	public void user_runs_the_query_for_search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@When("^user enters the query result in Claim Number textbox$")
	public void user_enters_the_query_result_in_Claim_Number_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbClaimNo = DatabaseConn.resultSet.getString("ClaimNo");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("claim number is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		searchPage.enterClaimNumber(dbClaimNo);
	}

	@When("^user runs the (.*) query for claim search$")
	public void user_runs_the_query_for_claim_search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), dbClaimNo));
	}

	@Then("^user should be able to view the same result in grid as SQL result for Claim Number$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result_for_Claim_Number() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbResult = DatabaseConn.resultSet.getString(1);
			}
			Assert.assertTrue("grid columns from Database does not match with UI",
					listOfGridColumnsOnUI.containsAll(searchPageSteps.fetchColumnNamesFromDatabaseResult()));
		} catch (SQLException sQLException) {
			Assert.assertTrue("Names are not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
	}
}
