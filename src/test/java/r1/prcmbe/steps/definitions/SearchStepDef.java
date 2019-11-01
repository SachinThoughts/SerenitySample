package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;

public class SearchStepDef {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	EnvironmentVariables environmentVariables;
	CommonMethods commonMethods;

	String dbQueryFilename, dbMRN;

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

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
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

	@Given("^user login to SQL Server and connect to facility database$")
	public void user_login_to_SQL_server_and_connect_to_facility_database() throws IOException {
		DatabaseConn.getServerDBName(
				EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.base.url"), "WPWI");
	}

	@When("^user runs the (.*) query for search$")
	public void user_run_the_query_for_Search(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@When("^user selects (.*) from Operator dropdown$")
	public void user_selects_from_Operator_dropdown(String operator) {
		searchPage.operatorSelectText(operator);
	}

	@When("^user enters the query result in Medical Record Number textbox$")
	public void user_enters_the_query_result_in_Medical_Record_Number_textbox() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbMRN = DatabaseConn.resultSet.getString(1);
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Medical Record Number is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		searchPage.enterMRN(dbMRN);
	}
}
