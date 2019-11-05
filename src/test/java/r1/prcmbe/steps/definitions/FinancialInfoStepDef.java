package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
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
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.serenity.steps.LoginSteps;

public class FinancialInfoStepDef {
	NavigationPage navigationPage;
	FinancialInfoPage financialInfoPage;
	EnvironmentVariables environmentVariables;
	CommonMethods commonMethods;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	LoginSteps loginSteps;
	String invoiceNumber, transactionTypeSearchOne = "A%", transactionTypeSearchTwo = "C%", expTotalBalance,
			financialInfoElementVal;
	List<String> listOfDBValuesOfAdjustmentTableData = new ArrayList<>();
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
		Assert.assertTrue("Headers do not match", financialInfoPage.isFinanceInfoHeadersVisible(financeInfoHeaders));
	}

	@When("^user runs Financial_Information_Section_SQL5(.+)$")
	public void user_runs_Financial_Information_Section_SQL1(String queryName) throws SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), transactionTypeSearchOne,
						transactionTypeSearchTwo));
	}

	@When("^user hover on R1_Decision$")
	public void user_hover_on_R__Decision() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@Then("^user should be able to fetch Invoice Number$")
	public void user_should_be_able_to_fetch_Invoice_Number() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			invoiceNumber = DatabaseConn.resultSet.getString("InvoiceNumber");
			loginSteps.log("The fetched invoice number is: " + invoiceNumber);
		}
	}

	@When("^user enters fetched Invoice Number in the Invoice Number textbox$")
	public void user_enters_fetched_Invoice_Number_in_the_Invoice_Number_textbox() {
		searchPage.enterInvoiceNumber(invoiceNumber);
	}

	@When("^user clicks on Submit button on R1D page$")
	public void user_clicks_on_Submit_button_on_R_D_page() {
		financialInfoPage.clickSubmitBtn();
	}

	@When("^User Clicks on drill down icon of Adjustment drilldown$")
	public void user_Clicks_on_drill_down_icon_of_Adjustment_drilldown() {
		financialInfoPage.clickAdjustmentScrollArrow();
	}

	@Then("^User should be able to view following fields:$")
	public void user_should_be_able_to_view_following_fields(DataTable datatable) {
		List<String> expectedAdjustmentTableHeaders = datatable.asList(String.class);
		Assert.assertTrue("Adjustment Headers donot match",
				financialInfoPage.getAdjustmentTableHeaders().equals(expectedAdjustmentTableHeaders));
	}

	@Then("^User should be able to view same data in drilldown section of Adjustment as SQL result$")
	public void user_should_be_able_to_view_same_data_in_drilldown_section_of_Adjustment_as_SQL_result() {
		Assert.assertTrue(
				"The values of Adjustment table donot match with database. Values from UI: "
						+ financialInfoPage.getAdjustmentTableData() + "and from Database: "
						+ listOfDBValuesOfAdjustmentTableData,
				financialInfoPage.getAdjustmentTableData().containsAll(listOfDBValuesOfAdjustmentTableData));
	}

	@When("^user runs Financial_Information_Section_SQL9(.+)$")
	public void user_runs_Financial_Information_Section_SQL2(String queryName) throws SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), invoiceNumber,
						transactionTypeSearchOne, transactionTypeSearchTwo));
		while (DatabaseConn.resultSet.next()) {
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("PaymentCode"));
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("Description"));
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("PayorPlanCode"));
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("PayorPlanName"));
			listOfDBValuesOfAdjustmentTableData.add(financialInfoStep
					.formatDbDateFieldWithDateTime(DatabaseConn.resultSet.getString("DateOfTransaction")));
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("TypeOfTransaction"));
			listOfDBValuesOfAdjustmentTableData
					.add(financialInfoStep.deleteLastTwoDecPlaces(DatabaseConn.resultSet.getString("Amount")));
			listOfDBValuesOfAdjustmentTableData.add(DatabaseConn.resultSet.getString("GLCode"));
		}
	}

	@When("^user moves to Total Balance column$")
	public void user_moves_to_Total_Balance_column() {
		financialInfoPage.scrollToTotalBalance();
	}

	@Then("^User should be able to view the same amount of Total Balance as SQL result$")
	public void user_should_be_able_to_view_the_same_amount_of_Total_Balance_as_SQL_result() {
		Assert.assertTrue(
				"The Balances donot match. Value from database is:" + expTotalBalance + "and on UI is: "
						+ financialInfoPage.getTotalBalanceData(),
				expTotalBalance.equals(financialInfoPage.getTotalBalanceData()));
	}

	@When("^user runs Financial_Information_Section_SQL3(.+)$")
	public void user_runs_Financial_Information_Section_SQL3(String queryName) throws SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@Then("^user should be able to fetch Invoice Number and Total Balance$")
	public void user_should_be_able_to_fetch_Invoice_Number_and_Total_Balance() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			invoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			loginSteps.log("The fetched invoice number is: " + invoiceNumber);
			expTotalBalance = DatabaseConn.resultSet.getString("TotalOpenBalance");
		}
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
		Assert.assertTrue(
				"Same amount not displayed in Adjustment column as SQL result \n Expected adjustment from DB"
						+ financialInfoStep.formatCurrency(financialInfoElementVal) + "Actual adjustment on UI"
						+ financialInfoPage.getTotalAdjustments(),
				financialInfoPage.getTotalAdjustments()
						.equals(financialInfoStep.formatCurrency(financialInfoElementVal)));
	}
	
	@When("^user Clicks on drill down icon of total charges$")
	public void user_Clicks_on_drill_down_icon_of_total_charges() {
		financialInfoPage.expandTotalCharges();
	}
	
	@Then("^User should be able to view following total charges fields:$")
	public void user_should_be_able_to_view_following_total_charges_fields(DataTable datatable) {
		List<String> expectedTotalChargesTableHeaders = datatable.asList(String.class);
		Assert.assertTrue("Total charges Headers do not match",
				financialInfoPage.getTotalChargesTableHeaders().equals(expectedTotalChargesTableHeaders));
	}
	
	@When("^user executes the query to fetch total charges details (.*)$")
	public void user_runs_the_query_to_fetch_total_charges_details(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilename), invoiceNumber));
	}
	
	@Then("^user should be able to view same data in drilldown section of Total Charges as SQL result$")
	public void user_should_be_able_to_view_same_data_in_drilldown_section_of_Total_Charges_as_SQL_result() throws Exception {
		List<Object> listOfVal = financialInfoStep.verifyTotalChargesDbValuesWithUI();
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Following values does not match\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}
}
