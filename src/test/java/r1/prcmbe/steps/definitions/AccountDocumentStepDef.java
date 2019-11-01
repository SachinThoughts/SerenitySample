package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;

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
import r1.prcmbe.pages.PRCMBESearchPage;
import r1.prcmbe.serenity.steps.AccountDocumentSteps;

public class AccountDocumentStepDef {

	BillingAndFollowUpPage billingAndFollowUpPage;
	AccountDocumentPage accntDocumentPage;
	AccountInformationPage accntInformationPage;
	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	NavigationPage navigationPage;
	PRCMBESearchPage pRCMBESearchPage;

	Random random = new Random();

	@Steps
	AccountDocumentSteps accntDocumentSteps;

	String selectedDocumentType, enteredDocumentTitle, fileName, visitNo, facilityCode, invoiceNumber;
	static String dbFileName = "AccountDocument";

	@Given("^user login to SQL server and connect to database$")
	public void user_login_to_SQL_server_and_connect_to_database() throws IOException, ClassNotFoundException, SQLException {
		String webdriverURL = EnvironmentSpecificConfiguration.from(environmentVariables)
				.getProperty("webdriver.base.url");
		String facility = CommonMethods.loadProperties("facility");
		facility = facility.substring(0, 4);
		DatabaseConn.getServerDBName(webdriverURL, facility);
	}

	@When("^user clicks on submit button$")
	public void user_clicks_on_Submit_Button() {
		pRCMBESearchPage.clickSubmitBtn();
	}

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user hovers on R1_Decision link$")
	public void user_hovers_on_R1_Decision_link() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
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
				accntInformationPage.getInvoiceNumber().equals(invoiceNumber));
	}

	@When("^user selects \"([^\"]*)\" from Search By dropdown$")
	public void user_selects_from_Search_By_dropdown(String dropdownValue) {
		pRCMBESearchPage.searchBySelectText(dropdownValue);
	}

	@When("^user runs the \"([^\"]*)\" query to fetch invoice number$")
	public void user_runs_the_query_to_fetch_invoice_number(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				invoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("InvoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
	}

	@When("^user enters the query result in Invoice Number search textbox$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox() {
		pRCMBESearchPage.enterInvoiceNumber(invoiceNumber);
	}
}
