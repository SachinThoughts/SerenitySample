package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.prcmbe.pages.FinancialInfoPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;

public class FinancialInfoStepDef {
	NavigationPage navigationPage;
	FinancialInfoPage financialInfoPage;
	EnvironmentVariables environmentVariables;
	CommonMethods commonMethods = new CommonMethods();

	String invoiceNumber, financialInfoElementVal;
	private static String dbQueryFilename = "FinancialInformation";	
	
	@Steps
	FinancialInfoSteps financialInfoStep;
	LoginPage userLoginPage;


	@When("^user scrolls down till Financial Information Section$")
	public void user_scrolls_down_till_Financial_Information_Section() {
		financialInfoPage.scrollIntoFinancialInfoPanel();
		financialInfoPage.expandFinancialInfoSectn();
		financialInfoPage.waitForAngularRequestsToFinish();
	}

	@Then("^Financial Information should be displayed$")
	public void financial_Information_should_be_displayed() {
		Assert.assertTrue("Financial Information section is not displayed",
		financialInfoPage.isFinancialInfoSectionVisible());
	}

	@When("^user executes the query for InvoiceNumber (.*)$")
	public void user_executes_the_query_for_InvoiceNumber(String query) {
		try {
			String finalQuery = commonMethods.loadQuery(query, dbQueryFilename);
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			Assert.assertTrue("unable to execute query" + e, false);
		}
	}

	@When("^user fetch the InvoiceNumber from DB$")
	public void user_fetch_the_InvoiceNumber_from_DB() {
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

	@Then("^User should be able to view following fields$")
	public void User_should_be_able_to_view_following_fields(DataTable expectedHeaders) {
		List<String> financeInfoHeaders = expectedHeaders.asList(String.class);
		Assert.assertTrue("Headers do not match",
		financialInfoPage.isFinanceInfoHeadersVisible(financeInfoHeaders));
	}
	
	@When("^user fetch the InvoiceNumber and \"([^\"]*)\" from DB$")
	public void user_fetch_the_InvoiceNumber_and_from_DB(String financialInfoElement) throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			invoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			financialInfoElementVal = DatabaseConn.resultSet.getString(financialInfoElement);
		}
	}
	
	@Then("^user should be able to view the same amount in Adjustment column as SQL result$")
	public void user_should_be_able_to_view_the_same_amount_in_Adjustment_column_as_SQL_result() {
	    Assert.assertTrue("Same amount not displayed in Adjustment column as SQL result", financialInfoPage.getTotalAdjustments().equals(financialInfoStep.formatCurrency(financialInfoElementVal)));
	}
}
