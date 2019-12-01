package r1.prcmbe.steps.definitions;

import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;

public class CallPayerQueueStepDef {
	CommonMethods commonMethods;
	CallPayerQueuePage callPayerQueuePage;
	SearchPage searchPage;
	AccountInformationPage accInformationPage;

	@Steps
	FinancialInfoSteps financialInfoStep;

	String accountNo, noOfAccountsInQueueBefore, dbInvoiceNumber, category, amount, tCode;
	private static String dbQueryFilename = "CallPayorQueue";

	@Given("^user is on account page having no payer : \"([^\"]*)\"$")
	public void user_is_on_account_page_having_no_payer(String expDefectClassification) {
		Assert.assertTrue("The defect Classification is not as expected",
				callPayerQueuePage.getDefectClassification().equals(expDefectClassification));
		accountNo = callPayerQueuePage.getAccountNo();
		noOfAccountsInQueueBefore = callPayerQueuePage.getCountOfAccountsInCallPayorQueue();
	}

	@When("^user clicks on Add to Call queue icon$")
	public void user_clicks_on_Add_to_Call_queue_icon() {
		callPayerQueuePage.clickAddtoCallPayorQueueBtn();
	}

	@Then("^The popup to add call to queue should open with title as \"([^\"]*)\"$")
	public void the_popup_to_add_call_to_queue_should_open_with_title_as(String expPopupTitle) {
		Assert.assertTrue("Add Call To Queue popup should open",
				callPayerQueuePage.getCallToQueuePopupTitle().equals(expPopupTitle));
	}

	@When("^user clicks Add without note$")
	public void user_clicks_Add_without_note() {
		callPayerQueuePage.clickAddPayerQueueWithoutNoteBtn();
	}

	@Then("^user should be able to view message for Call Payor queue as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_for_Call_Payor_queue_as(String expMessage) {
		Assert.assertTrue("Expected Info Message not displayed",
				callPayerQueuePage.getInfoMessageText().equals(expMessage));
	}

	@Then("^user should not be able to view account to Call Queue$")
	public void user_should_not_be_able_to_view_account_to_Call_Queue() {
		callPayerQueuePage.closeMsgButton();
		Assert.assertTrue("The count of Accounts in the Call Payor Queue should remain unchanged",
				callPayerQueuePage.getCountOfAccountsInCallPayorQueue().equals(noOfAccountsInQueueBefore));
		callPayerQueuePage.clickExpandArrowCallPayorQueue();
		Assert.assertFalse("User should not be able to view account in Call Payor Queue",
				callPayerQueuePage.getListOfAccountsInCallPayorQueue().contains(accountNo));
	}

	@When("^Call Payor Queue user run the query to fetch an Unclassified account \"([^\"]*)\"$")
	public void call_Payor_Queue_user_run_the_query_to_fetch_an_Unclassified_account(String query) {
		try {
			String finalQuery = commonMethods.loadQuery(query, dbQueryFilename);
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			Assert.assertTrue("unable to execute query" + e, false);
		}
	}

	@Given("^user is on account page$")
	public void user_is_on_account_page() {
		Assert.assertTrue("Searched account page is not displayed",
				accInformationPage.getInvoiceNumber().equals(dbInvoiceNumber));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch account for writeoff$")
	public void user_runs_the_query_to_fetch_account_for_writeoff(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@When("^user fetch the InvoiceNumber from database$")
	public void user_fetch_the_InvoiceNumber_from_DB() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			}
			financialInfoStep.log("invoiceNumber in DB" + dbInvoiceNumber);
		} catch (SQLException exception) {
			Assert.assertTrue("invoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters Invoice number$")
	public void user_enter_Invoice_Number() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@When("^user clicks on Approvals link$")
	public void user_clicks_on_Approvals_link() {
		accInformationPage.clickApprovalWriteOffLink();
	}

	@When("^user selects \"([^\"]*)\" option from Category dropdown$")
	public void user_selects_option_from_Category_dropdown(String drpdwnVal) {
		category = drpdwnVal;
		callPayerQueuePage.categorySelectByText(category);
	}

	@When("^user selects \"([^\"]*)\" from Write Off Type dropdown$")
	public void user_selects_from_Write_Off_Type_dropdown(String drpdwnVal) {
		callPayerQueuePage.writeOffTypeSelectByText(drpdwnVal);
	}

	@When("^user selects \"([^\"]*)\" from T-Code to Use dropdown$")
	public void user_selects_from_tcode_to_Use_dropdown(String drpdwnVal) {
		tCode = drpdwnVal;
		callPayerQueuePage.tCodeSelectByText(tCode);
	}

	@When("^user enters amount \"([^\"]*)\" in Write off Amount textbox$")
	public void user_enters_amount_in_Write_Off_Amount_textbox(String amount) {
		this.amount = amount;
		callPayerQueuePage.enterWriteOffAmount(this.amount);
	}

	@When("^user enters \"([^\"]*)\" in Notes textbox$")
	public void user_enters_in_Notes_textbox(String notes) {
		callPayerQueuePage.enterWriteOffNotes(notes);
	}

	@When("^user clicks on write off Save button$")
	public void user_clicks_on_write_off_Save_button() {
		callPayerQueuePage.clickSaveWriteOffBtn();
	}

	@Then("^user should be able to view write-off request on account$")
	public void user_should_be_able_to_view_write_off_request_on_account() {
		Assert.assertTrue("incorrect or no write off request present on the account",
				amount.equals(callPayerQueuePage.getCreatedWriteOffAmount())
						&& tCode.equals(callPayerQueuePage.getCreatedTCode())
						&& category.equals(callPayerQueuePage.getCreatedWriteOffCategory()));
	}
}