package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.FinancialInfoPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;

public class FinancialInfoStepDef extends FinancialInfoPage {
	NavigationPage navigationPage;
	SearchPage prcmbeSearchPage;
	String invoiceNumber;

	FinancialInfoPage financialInfoPage;
	CommonMethods commonMethods = new CommonMethods();

	@Steps
	FinancialInfoSteps financialInfoStep;
	LoginPage userLoginPage;
	private static String dbQueryFilename = "FinancialInformation";
	EnvironmentVariables environmentVariables;

	@When("^user scrolls down till Financial Information Section$")
	public void user_scrolls_down_till_Financial_Information_Section() {
		financialInfoPage.scrollIntoFinancialInfoPanel();
		financialInfoPage.expandFinancialInfoSectn();
		waitForAngularRequestsToFinish();

	}

	@Then("^Financial Information should be displayed$")
	public void financial_Information_should_be_displayed() {

		Assert.assertTrue("Financial Information section is not displayed",
				financialInfoPage.financialInfoSection.isDisplayed());

	}

	@When("^user executes the query for InvoiceNumber (.*)$")
	public void user_executes_the_query_for_InvoiceNumber(String query) throws Exception {
		try {
			String finalQuery = String.format(commonMethods.loadQuery(query, dbQueryFilename));
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			System.out.println("unable to execute query");
		}
	}

	@When("^user fetch the InvoiceNumber from DB$")
	public void user_fetch_the_InvoiceNumber_from_DB() throws SQLException {
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			}
			financialInfoStep.log("invoiceNumber in DB" + invoiceNumber);
		} catch (Exception e) {
			Assert.assertTrue("invoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + e, false);
		}
	}

	@When("^user enters InvoiceNumber in the InvoiceNumber field and click on submit button$")
	public void user_enters_InvoiceNumber_in_the_InvoiceNumber_field_and_click_on_submit_button() {
		financialInfoStep.searchInvoiceNumber(invoiceNumber);
	}

	@Given("^user login to SQL server and connect to database$")
	public void user_login_to_SQL_server_and_connect_to_database() throws IOException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@Then("^User should be able to view following fields$")
	public void User_should_be_able_to_view_following_fields(DataTable expectedHeaders) {
		List<String> expectedFinInfoHeaders = expectedHeaders.asList(String.class);
		Assert.assertTrue("Headers do not match",
				financialInfoStep.isFinInfoHeaderAttributesVisible(expectedFinInfoHeaders));
	}

}
