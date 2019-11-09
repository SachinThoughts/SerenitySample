package r1.prcmbe.steps.definitions;

import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class SearchStepDef extends PageObject {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;
	CommonMethods commonMethods;
	FinancialInfoSteps financialInfoSteps;
	private static String dbQueryFilename = "PrcmSearch";
	String dbInvoiceNumber;

	@Steps
	SearchPageSteps searchPageSteps;
	
	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user clicks on R1_Decision link$")
	public void user_clicks_on_R1_Decision_link() {
		billingAndFollowUpPage.clickOnR1DecisionLink();
	}

	@Then("^user should be able to view R1D Search page $")
	public void user_should_be_able_to_view_R1_D_Search_page() {
		Assert.assertTrue("User is not navigated on R1 D Search Page",
				searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision - Search"));
	}

	@Then("^user should be able to view message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message(String expectedMessage) {
		Assert.assertTrue("'" + expectedMessage + "' message not visible",
				searchPage.getNoAccountsMessage().replace("\n", "").equals(expectedMessage));
	}

	@When("^user hovers on R1_Decision link$")
	public void user_hovers_on_R1_Decision_link() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
	}

	@Given("^user is on \"([^\"]*)\" page$")
	public void user_is_on_page(String searchPageTitle) {
		Assert.assertTrue(searchPageTitle + " page is not displayed",
				searchPage.getSearchPageTitle().contains(searchPageTitle));
	}

	@Then("^user should be able to view Invoice Number selected by default in Search By drop down$")
	public void user_should_be_able_to_view_Invoice_Number_selected_by_default_in_Search_By_drop_down() {
		Assert.assertTrue("Invoice number is not selected by default",
				searchPage.getDefaultSelectedVal().equals("Invoice Number"));
	}

	@When("^user selects (.*) from Search By drop down$")
	public void user_selects_Visit_Number_from_Search_By_drop_down(String dropdown) {
		searchPage.searchBySelectText(dropdown);
	}

	@When("^user enters invalid value in (.*) textbox $")
	public void user_enters_invalid_value_in_$_textbox(String invalidVal) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(invalidVal);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(invalidVal);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(invalidVal);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(invalidVal);
		} else if (searchPage.isFirstNameTxtFieldVisible() && searchPage.isLastNameTxtFieldVisible()) {
			searchPage.enterLastNameTxtBox(invalidVal);
			searchPage.enterFirstName(invalidVal);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(invalidVal);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@When("^user clicks on Submit Button$")
	public void user_clicks_on_Submit_Button() {
		searchPage.clickSubmitBtn();
	}

	@Then("^user should be able to view error message (.*)$")
	public void user_should_be_able_to_view_error_message(String errorMsg) {
		Assert.assertTrue("'" + errorMsg + "' message is not visible",
				searchPage.getErrorMsg().equalsIgnoreCase(errorMsg));

	}

	@When("^user selects \"([^\"]*)\" operator from operator dropdown$")
	public void user_selects_operator_from_operator_dropdown(String operator) {
		searchPage.selectOperatorValue(operator);
	}

	@When("^user enters less than 5 characters in (.*) textbox$")
	public void user_enters_less_than_characters_in_textbox(String value) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(value);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(value);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(value);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(value);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(value);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@Then("^user should able to view tool-tip message (.*)$")
	public void user_should_able_to_view_tool_tip_message_Please_add_five_or_more_characters(String toolTipMessage) {
		Assert.assertTrue("Tooltip message '" + toolTipMessage + "' is not visible",
				searchPage.getToolTipText().equals(toolTipMessage));
	}

	@Then("^user should be able to view Submit Button in disabled state$")
	public void user_should_be_able_to_view_Submit_Button_in_disabled_state() {
		Assert.assertFalse("Submit button is enabled", searchPage.isSubmitBtnEnabled());
	}

	@When("^user enters more than or equal to 5 characters (.*) in textbox$")
	public void user_enters_more_than_or_equal_to_characters_in_textbox(String value) {
		if (searchPage.isVisitTxtFieldVisible()) {
			searchPage.enterVisitNumber(value);
		} else if (searchPage.isMRNTxtFieldVisible()) {
			searchPage.enterMRN(value);
		} else if (searchPage.isClaimNumberTxtFieldVisible()) {
			searchPage.enterClaimNumber(value);
		} else if (searchPage.isInvoiceNumberTxtFieldVisible()) {
			searchPage.enterInvoiceNumber(value);
		} else if (searchPage.isSSNTxtFieldVisible()) {
			searchPage.enterSSN(value);
		} else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@Then("^user should be able to view Submit Button in enabled state$")
	public void user_should_be_able_to_view_Submit_Button_in_enabled_state() {
		Assert.assertTrue("Submit Button is disabled", searchPage.isSubmitBtnEnabled());
	}

	@Then("^user should be able to view message \"([^\"]*)\" (.*)$")
	public void user_should_be_able_to_view_message_Visit_Number(String expectedMsg, String fieldName) {
		Assert.assertTrue("Validation message " + expectedMsg + " " + fieldName + "is not visible",
				searchPage.getErrorMsg().equals(expectedMsg + " " + fieldName));
	}

	@Then("^user should not able to view tool-tip message$")
	public void user_should_not_able_to_view_tool_tip_message() {
		Assert.assertFalse("Tooltip is visible", searchPage.isToolTipVisible());
	}

	@When("^user run the query and fetch the Invoice Number \"([^\"]*)\"$")
	public void user_run_the_query_and_fetch_the_Invoice_Number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString(1);
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("EncounterID is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		financialInfoSteps.log("Fetched EncounterID from Database is " + dbInvoiceNumber);
	}

	@When("^user select \"([^\"]*)\" from Search By dropdown$")
	public void user_select_from_Search_By_dropdown(String drpdwnVal) {
		searchPage.searchBySelectText(drpdwnVal);
	}

	@When("^user enters the query result in Invoice Number search textbox and can view the same invoice number of selected facility or different facility$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_and_can_view_the_same_invoice_number_of_selected_facility_or_different_facility() {
		if (searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision - Search")) {
			searchPage.enterInvoiceNumber(dbInvoiceNumber);
			searchPage.clickSubmitBtn();
			searchPageSteps.verifyInvoiceIDWithEqualOperator(dbInvoiceNumber);
		}
	}

	@Then("^user navigates on internal search page$")
	public void user_navigates_on_internal_search_page() {
		Assert.assertTrue("User is not navigated on R1 Internal Search Page",
				searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision"));
	}
}
