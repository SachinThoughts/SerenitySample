package r1.prcmbe.steps.definitions;

import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountActionHistoryPage;
import r1.prcmbe.pages.SearchPage;

public class AccountActionHistoryStepDef {
	AccountActionHistoryPage accActionHistoryPage;
	CommonMethods commonMethods;
	String invoiceNumber;
	SearchPage searchPage;
	static String dbFileName = "AccountActionHistory";
		
	@When("^user scrolls down to reach Account Action History Section$")
	public void user_scrolls_down_to_reach_Account_Action_History_Section() {
		accActionHistoryPage.scrollToAccActionHistorySection();
	}

	@Then("^user should be able to view the message \"([^\"]*)\" at Account Action History Section$")
	public void user_should_be_able_to_view_the_message_at_Account_Action_History_Section(String noActionHistoryMsg) {
		Assert.assertTrue("No Account Action history message is not displayed", accActionHistoryPage.getNoAccActionHistoryMsg().equals(noActionHistoryMsg));
	}
	
	@When("^user runs the account action history query \"([^\"]*)\"$")
	public void user_runs_the_account_action_history_query(String queryName) throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber=DatabaseConn.resultSet.getString("InvoiceNumber");
			}
	}
	
	@When("^user enters the result in Invoice Number search textbox$")
	public void user_enters_the_result_in_Invoice_Number_search_textbox() {
		searchPage.enterInvoiceNumber(invoiceNumber);
	}
}
