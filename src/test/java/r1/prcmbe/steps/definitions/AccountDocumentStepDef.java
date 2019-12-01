package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountDocumentPage;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.AccountDocumentSteps;

public class AccountDocumentStepDef {

	BillingAndFollowUpPage billingAndFollowUpPage;
	AccountDocumentPage accntDocumentPage;
	AccountInformationPage accntInformationPage;
	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	NavigationPage navigationPage;
	SearchPage searchPage;

	Random random = new Random();

	@Steps
	AccountDocumentSteps accntDocumentSteps;

	String selectedDocumentType, enteredDocumentTitle, fileName, invoiceNo, facilityCode, invoiceNumber,
			chargeTransactionId;
	List<String> listOfInvoiceNumber;
	static String dbFileName = "AccountDocument";

	@Given("^user login to SQL server and connect to database$")
	public void user_login_to_SQL_server_and_connect_to_database()
			throws IOException, ClassNotFoundException, SQLException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@When("^user scrolls down till Account Documents section$")
	public void user_scrolls_down_till_Account_Documents_section() throws Exception {
		accntDocumentPage.clickOnDocumentLink();
	}

	@When("^user selects any document type from Document Type dropdown$")
	public void user_selects_any_document_type_from_Document_Type_dropdown() {
		selectedDocumentType = accntDocumentPage.selectRandomDocumentTypeAndGetSelectedVal();
	}

	@Then("^user should be able to view the selected document type in Document type drop down$")
	public void user_should_be_able_to_view_the_selected_document_type_in_Document_type_drop_down() {
		Assert.assertTrue("Actual DocumentType DropDowwn Val doesnot match with Selected DocumentType DropDown Val",
				accntDocumentPage.getSelectedDocumentTypeVal().equals(selectedDocumentType));
	}

	@When("^user enters document title \"([^\"]*)\" in Document Title field$")
	public void user_enters_document_title_in_Document_Title_field(String value) {
		enteredDocumentTitle = value.concat("_" + random.nextInt());
		accntDocumentPage.enterTextDocTitle(enteredDocumentTitle);
	}

	@Then("^user should be able to view the entered text in Document Title field$")
	public void user_should_be_able_to_view_the_entered_text_in_Document_Title_field() {
		Assert.assertTrue("Actual DocumentTitle doesnot match with Entered Document Title",
				enteredDocumentTitle.equals(accntDocumentPage.getDocumentTitleVal()));
	}

	@When("^user selects file (.*) using ChooseFile Option$")
	public void user_selects_file_using_ChooseFile_Option(String fileType) {
		fileName = accntDocumentPage.getFileName(fileType, "LessThan20MB");
		accntDocumentPage.uploadFile(fileName);
	}

	@Then("^user should be able to view the selected file path/name under File Name text box$")
	public void user_should_be_able_to_view_the_selected_file_path_name_under_File_Name_text_box() {
		Assert.assertTrue("Actual File Name doesnot match with Entered File Name",
				accntDocumentPage.getFileNameVal().contains(fileName));
	}

	@When("^user clicks on Upload Document button$|^user clicks on Upload Document button without entering value in any field$|^user clicks on Upload Document button without entering Document Title$|^user clicks on Upload Document button without selecting any file in the File Name field$")
	public void user_clicks_on_Upload_Document_button() {
		accntDocumentPage.clickOnUploadFileBtn();
	}

	@Then("^user should be able to view message \"([^\"]*)\" (.*) \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message(String arg1, String arg2, String arg3) {
		Assert.assertTrue(
				"Actual Error Message doesnot match with Expected Error Message : Actual Error Msg :"
						+ accntDocumentPage.getUploadErrorMsg(),
				accntDocumentPage.getUploadErrorMsg().equals(arg1 + arg2 + arg3));
	}

	@When("^user selects file (.*) with size of more than (?:\\d+) MB$")
	public void user_selects_file_with_size_of_more_than_MB(String fileType) {
		fileName = accntDocumentPage.getFileName(fileType, "Morethan20MB");
		accntDocumentPage.uploadFile(fileName);
	}

	@Then("^user should be able to view the error message on uploading the document \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_error_message_on_uploading_the_document(String expectAlertMsg) {
		Assert.assertTrue(
				"Actual Error Message doesnot match with Expected Error Message : Actual Error Msg :"
						+ accntDocumentPage.getUploadErrorMsg(),
				accntDocumentPage.getUploadErrorMsg().equals(expectAlertMsg));
	}

	@Then("^user should be able to view the error message without entering any Value \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_error_message_without_entering_any_Value(String expectAlertMsg) {
		Assert.assertTrue(
				"Actual Error Message doesnot match with Expected Error Message : Actual Error Msg :"
						+ accntDocumentPage.getUploadErrorMsg(),
				accntDocumentPage.getUploadErrorMsg().equals(expectAlertMsg));
	}

	@Then("^user should be able to view the message \"([^\"]*)\" below File Name field in maroon color text$")
	public void user_should_be_able_to_view_the_message_below_File_Name_field_in_maroon_color_text(
			String expectAlertMsg) {
		Assert.assertTrue(
				"Actual Error Message doesnot match with Expected Error Message : Actual Error Msg :"
						+ accntDocumentPage.getMsgBelowFileName(),
				accntDocumentPage.getMsgBelowFileName().equals(expectAlertMsg));
		Assert.assertTrue("Colour of Text does not match with Maroon Color",
				accntDocumentSteps.verifyColourCodeForTextMsg());
	}

	@When("^user click on cancel to clear the field under Document Section$")
	public void user_click_on_cancel_to_clear_the_field_under_Document_Section() {
		accntDocumentPage.clickOnCancelBtn();
	}

	@Then("^user should get the information message \"([^\"]*)\" on screen$")
	public void user_should_get_the_information_message_on_screen(String successMsg) {
		Assert.assertTrue(
				"Actual Displayed doesn't match with expected Message : Actual Msg"
						+ accntDocumentPage.getDocumentUploadSuccessMsg(),
				successMsg.equals(accntDocumentPage.getDocumentUploadSuccessMsg()));
	}

	@Given("^user is on R1 Decision Account page$")
	public void user_is_on_R1_Decision_Account_page() {
		Assert.assertTrue("Searched account page is not displayed",
				accntInformationPage.getInvoiceNumber().equals(listOfInvoiceNumber.get(0)));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch invoice number$")
	public void user_runs_the_query_to_fetch_invoice_number(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		listOfInvoiceNumber = new ArrayList<>();
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfInvoiceNumber.add(DatabaseConn.resultSet.getString("invoicenumber"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("InvoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters the query result in Invoice Number search textbox$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox() {
		searchPage.enterInvoiceNumber(listOfInvoiceNumber.get(0));
	}

	@When("^user checks the Show All Documents check box$")
	public void user_checks_the_Show_All_Documents_check_box() {
		accntDocumentPage.clickOnShowAllDocumentCheckBox();
	}

	@Then("^user should be able to view the Show All Documents checkbox checked$")
	public void user_should_be_able_to_view_the_Show_All_Documents_checkbox_checked() {
		Assert.assertTrue("Show All Document CheckBox not Checked",
				accntDocumentPage.getShowAllDocumentCheckBoxSelectedStatus().equals("true"));
	}

	@Then("^user should be able to view the validation message \"([^\"]*)\" below Upload Document button$")
	public void user_should_be_able_to_view_the_validation_message_below_Upload_Document_button(
			String expectedMessage) {
		Assert.assertTrue(
				"Actual Validation Message doesn't match with Expected Message : Actual Message"
						+ accntDocumentPage.getNoDocumentUploadTxtValue(),
				expectedMessage.equals(accntDocumentPage.getNoDocumentUploadTxtValue()));
	}

	@Then("^user should able to view the documents grid containing a list of all uploaded documents with their information$")
	public void user_should_able_to_view_the_documents_grid_containing_a_list_of_all_uploaded_documents_with_their_information() {
		Assert.assertTrue("Uploaded Document not available in Documents Grid",
				accntDocumentSteps.verifyUploadedDocsTitle(enteredDocumentTitle));
	}

	@When("^user clicks on the Document Title from the grid to open the corresponding document$")
	public void user_clicks_on_the_Document_Title_from_the_grid_to_open_the_corresponding_document() {
		accntDocumentSteps.deleteFileFromSystem(fileName);
		accntDocumentPage.clickOnUploadedDocument(enteredDocumentTitle);
	}

	@Then("^user should be able to view downloaded document on the system$")
	public void user_should_be_able_to_view_downloaded_document_on_the_system() throws InterruptedException {
		Assert.assertTrue("Downloaded Document not available on System",
				accntDocumentSteps.isDocumentDownloadedOnSystem(fileName, 20));
	}

	@When("^user checks Associated to MRN checkbox$")
	public void user_checks_Associated_to_MRN_checkbox() {
		accntDocumentPage.clickOnMRNCheckBox();
	}

	@When("^user runs the \"([^\"]*)\" query to fetch invoice number having no document$")
	public void user_runs_the_query_to_fetch_invoice_number_having_no_document(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfInvoiceNumber.add(DatabaseConn.resultSet.getString("invoicenumber"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("InvoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters the query result in Invoice Number search textbox having no document$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_having_no_document() {
		searchPage.enterInvoiceNumber(listOfInvoiceNumber.get(1));
	}

	@Then("^user should be able to view Associated to MRN checkbox checked$")
	public void user_should_be_able_to_view_Associated_to_MRN_checkbox_checked() {
		Assert.assertTrue("MRN checkBox is not checked", accntDocumentPage.isMRNCheckBoxSelected());
	}

	@When("^user clicks on Related Accounts button under Patient & Facility Info section$")
	public void user_clicks_on_Related_Accounts_button_under_Patient_Facility_Info_section() {
		facilityCode = accntInformationPage.getFacilityCodeValue();
		accntInformationPage.clicksRelatedAccountButton();
	}

	@Then("^user should be able to view the Related Accounts pop-up with list of all related accounts$")
	public void user_should_be_able_to_view_the_Related_Accounts_pop_up_with_list_of_all_related_accounts() {
		Assert.assertTrue("Related Account PopUp doesn't appear", accntInformationPage.isRelatedAccntPopUpVisible());
		Assert.assertTrue("List of Related Account not available for particular Visit No",
				accntInformationPage.getSizeOfRelatedAccntInvoiceNo() > 0);
	}

	@When("^user clicks on any account of same facility from Related account list$")
	public void user_clicks_on_any_account_of_same_facility_from_Related_account_list() {
		invoiceNo = accntInformationPage.clickRelatedAccountBasedOnFacilityCodeAndFetchInvoiceNo(facilityCode);
	}

	@Then("^user should be able to navigate to selected account$")
	public void user_should_be_able_to_navigate_to_selected_account() {
		Assert.assertTrue("Opened Related Account doesn't match with facility Code selected",
				invoiceNo.equals(accntInformationPage.getInvoiceNumber().trim()));
	}

	@Then("^user should be able to view all the uploaded documents in list which were associated with MRN$")
	public void user_should_be_able_to_view_all_the_uploaded_documents_in_list_which_were_associated_with_MRN() {
		Assert.assertTrue("Uploaded Document not available in Documents Grid which were associated with MRN",
				accntDocumentSteps.verifyUploadedDocsTitle(enteredDocumentTitle));
	}

	@When("^user go to Account Documents section$")
	public void user_go_to_Account_Documents_section$() {
		accntDocumentPage.clickOnDocumentLink();
	}

	@Then("user should not be able to view the uploaded documents in the list which were uploaded in previous account")
	public void user_should_not_be_able_to_view_the_uploaded_documents_in_the_list_which_were_uploaded_in_previous_account() {
		Assert.assertFalse("Uploaded Document available in Documents Grid which were uploaded in previous account",
				accntDocumentSteps.verifyUploadedDocsTitle(enteredDocumentTitle));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch invoice number and ChargeTransactionID$")
	public void user_runs_the_query_to_fetch_invoice_number_and_ChargeTransactionID(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber = DatabaseConn.resultSet.getString("invoiceNumber");
				chargeTransactionId = DatabaseConn.resultSet.getString("chargetransactionid");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("InvoiceNumber and ChargeTransactionID is not fetched from DB.\nThe Technical Error is:\n"
					+ exception, false);
		}
	}

	@Then("^user should be able to fetch InvoiceNumber and ChargeTransactionID$")
	public void user_should_be_able_to_fetch_InvoiceNumber_and_ChargeTransactionID() {
		Assert.assertTrue("User is not able to fetch InvoiceNumber", invoiceNumber != null);
		Assert.assertTrue("User is not able to fetch ChargeTransactionID", chargeTransactionId != null);
	}

	@Then("^user should be able to view same document in attachment as uploaded in previous Invoice number$")
	public void user_should_be_able_to_view_same_document_in_attachment_as_uploaded_in_previous_Invoice_number() {
		Assert.assertTrue(
				"Same Document not available in Documents Grid which were uploaded in previous Invoice Number",
				accntDocumentSteps.verifyUploadedDocsTitle(enteredDocumentTitle));
	}

	@When("^user runs the query \"([^\"]*)\" query to fetch invoice number based on result of above query$")
	public void user_runs_the_query_query_to_fetch_invoice_number_based_on_result_of_above_query(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(String.format(queryName, invoiceNumber, chargeTransactionId), dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber = DatabaseConn.resultSet.getString("invoiceNumber");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("InvoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters the query result in Invoice Number search textbox fetched above$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_fetched_above() {
		searchPage.enterInvoiceNumber(invoiceNumber);
	}
}