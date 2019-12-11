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
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.RelatedAccountsPage;
import r1.prcmbe.serenity.steps.RelatedAccountsSteps;

public class RelatedAccountsStepDef {
	RelatedAccountsPage relatedAccntsPage;
	CommonMethods commonMethods;
	AccountInformationPage accInfoPage;
	@Steps
	RelatedAccountsSteps relatedAcctSteps;
	static String dbFileName = "RelatedAccounts";
	List<String> visitNumbersDb = new ArrayList<>();
	String invoiceNumber, mrnNumber;

	@Given("^user is on R1 Decision Account information page$")
	public void user_is_on_R1_Decision_Account_information_page() {
		accInfoPage.verifyPatientDetailsSectionVisible();
		invoiceNumber = accInfoPage.getInvoiceNumber();
		mrnNumber = accInfoPage.getMRNNumber();
	}

	@When("^user clicks on Related Accounts under Patient & Facility Info Section$")
	public void user_clicks_on_Related_Accounts_under_Patient_Facility_Info_Section() {
		relatedAccntsPage.clickRelatedAccountsBtn();
	}

	@Then("^user should able to view the pop up title as \"([^\"]*)\"$")
	public void user_should_able_to_view_the_pop_up_title_as(String popupTitle) {
		Assert.assertTrue("Popup title is not displayed",
				relatedAccntsPage.getRelatedAccPopupLabelTxt().equals(popupTitle));
	}

	@Then("^user should be able to view Search button$")
	public void user_should_be_able_to_view_Search_button() {
		Assert.assertTrue("Search button is not visible", relatedAccntsPage.isSearchBtnVisible());
	}

	@Then("^user should be able to view First button$")
	public void user_should_be_able_to_view_First_button() {
		Assert.assertTrue("First button is not visible", relatedAccntsPage.isFirstBtnVisible());
	}

	@Then("^user should be able to view Previous button$")
	public void user_should_be_able_to_view_Previous_button() {
		Assert.assertTrue("Previous button is not visible", relatedAccntsPage.isPreviousBtnVisible());
	}

	@Then("^user should be able to view Next Button$")
	public void user_should_be_able_to_view_Next_Button() {
		Assert.assertTrue("Next button is not visible", relatedAccntsPage.isNextBtnVisible());
	}

	@Then("^user should be able to view Last Button$")
	public void user_should_be_able_to_view_Last_Button() {
		Assert.assertTrue("Next button is not visible", relatedAccntsPage.isLastBtnVisible());
	}

	@Then("^user should be able to view (\\d+) button$")
	public void user_should_be_able_to_view_button(int pageNumber) {
		Assert.assertTrue(pageNumber + "page is not visible",
				relatedAccntsPage.getDefaultSelectedPage().equals(Integer.toString(pageNumber)));
	}

	@Then("^user should be able to view following grid columns$")
	public void user_should_be_able_to_view_following_grid_columns(DataTable columnNames) {
		List<String> expectedGridColumns = columnNames.asList(String.class);
		Assert.assertTrue(
				"The expected grid columns not visible \t Actual columns"
						+ relatedAccntsPage.getRelatedAcctPopUpHeaderList(),
				relatedAccntsPage.getRelatedAcctPopUpHeaderList().equals(expectedGridColumns));
	}

	@Then("^user should be able to view maximum (\\d+) Accounts under Related Accounts grid$")
	public void user_should_be_able_to_view_maximum_Accounts_under_Related_Accounts_grid(int count) {
		Assert.assertTrue("Expected count not displayed in the Related Accounts grid",
				relatedAccntsPage.getRelatedAccountCount() == count);
	}

	@When("^user runs query to fetch Related Accounts(.*)$")
	public void user_runs_query_to_fetch_Related_Accounts(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), mrnNumber, invoiceNumber, invoiceNumber));
		while (DatabaseConn.resultSet.next()) {
			visitNumbersDb.add(DatabaseConn.resultSet.getString("AccountNumber").trim());
		}
	}

	@Then("^user should be able to view same list of accounts in grid as in SQL result$")
	public void user_should_be_able_to_view_same_list_of_accounts_in_grid_as_in_SQL_result() {
		List<String> visitNumbersUi = relatedAccntsPage.getAllVisitNumbers();
		Assert.assertTrue("Accounts list not same in DB and Popup", visitNumbersDb.containsAll(visitNumbersUi));
	}
	
	@Then("^user should be able to view InvoiceNumber as hyperlink$")
	public void user_should_be_able_to_view_InvoiceNumber_as_hyperlink() {
		Assert.assertTrue("Invoice number/ VisitNumber is not a hyperlink",relatedAccntsPage.isInvoiceNumberClickable());
	}

	@Then("^user should be able to view Visit Number as hyperlink for records having InvoiceNumber as NA$")
	public void user_should_be_able_to_view_Visit_Number_as_hyperlink_for_records_having_InvoiceNumber_as_NA() {
		Assert.assertTrue("Visit Number is not displayed as hyperlink for NA invoice number",relatedAccntsPage.isVisitNumberHyperLinkedForNAInvoiceNumber());
	}

	@When("^user runs query to fetch all details of Related Accounts (.*)$")
	public void user_runs_query_to_fetch_all_details_of_Related_Accounts(String queryName) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), mrnNumber, invoiceNumber, invoiceNumber));
	}

	@Then("^user should be able to view the same grid data as in SQL result$")
	public void user_should_be_able_to_view_the_same_grid_data_as_in_SQL_result() throws SQLException {
		List<Object> listOfVal = relatedAcctSteps.verifyGridDataWithDb();
		boolean val = ((Boolean) listOfVal.get(listOfVal.size() - 1)).booleanValue();
		Assert.assertTrue("Following values does not match\n" + listOfVal.subList(0, listOfVal.size() - 1), val);
	}	
	
}
