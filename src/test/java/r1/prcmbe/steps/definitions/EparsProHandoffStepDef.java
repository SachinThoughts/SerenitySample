package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.ResultSetMetaData;
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
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.EparsProHandoffPage;
import r1.prcmbe.serenity.steps.EparsProHandoffSteps;

public class EparsProHandoffStepDef {

	BillingAndFollowUpPage billingAndFollowupPage;
	EparsProHandoffPage eparsProHandoffPage;
	CommonMethods commonMethods;
	private static String dbQueryFilename = "EparsProHandoff";
	List<String> listOfDBSearchColumnHeaders = new ArrayList<>();
	List<Object> listOfAllSearchHeaders = new ArrayList<>();
	List<String> listOfSearchByDrpDwnValues = new ArrayList<>();

	@Steps
	EparsProHandoffSteps eparsProHandoffSteps;

	String selectedSearchByValue;
	List<String> listOfActualValidationMessages = new ArrayList<>();

	@When("^User clicks on ePARS-Pro link$")
	public void user_clicks_on_ePARS_Pro_link() {
		billingAndFollowupPage.clickEparsProLink();
	}

	@Given("^user is on ePARS Pro screen$")
	public void user_is_on_ePARS_Pro_screen() {
		eparsProHandoffPage.isEparsPageTitleVisible();
	}

	@Then("^user should be able to view following value selected by default in Search By dropdown on ePARS Pro Page : \"([^\"]*)\"$")
	public void user_should_be_able_to_view_following_value_selected_by_default_in_Search_By_dropdown_on_ePARS_Pro_Page(
			String expDefaultSearchByDrpDwnValue) {
		Assert.assertTrue(
				"The default expected value of Search by is: " + expDefaultSearchByDrpDwnValue
						+ " but actual value is: " + eparsProHandoffPage.getSearchByDropdownValue(),
				eparsProHandoffPage.getSearchByDropdownValue().equals(expDefaultSearchByDrpDwnValue));
	}

	@When("^user selects following values from Search By drop down on Epars Page followed by click on Submit Button$")
	public void user_selects_following_values_from_Search_By_drop_down_on_Epars_Page_followed_by_click_on_Submit_Button(
			DataTable searchByDrpDwnValues) {
		listOfSearchByDrpDwnValues.clear();
		listOfSearchByDrpDwnValues = searchByDrpDwnValues.asList(String.class);
		listOfActualValidationMessages.clear();
		for (String searchByDrpDwnValue : listOfSearchByDrpDwnValues) {
			eparsProHandoffPage.selectSearchByDropdownValue(searchByDrpDwnValue);
			eparsProHandoffPage.clickSubmitBtn();
			listOfActualValidationMessages.add(eparsProHandoffPage.getValidationMessage());
		}
	}

	@Then("^user should be able to view following validation messages on Epars page$")
	public void user_should_be_able_to_view_following_validation_messages_on_Epars_page(
			DataTable expValidationMessages) {
		List<String> listOfExpValidationMessages = expValidationMessages.asList(String.class);
		Assert.assertTrue(
				"The validations messages are not as expected. Expected: " + listOfExpValidationMessages + " Actual: "
						+ listOfActualValidationMessages,
				listOfActualValidationMessages.equals(listOfExpValidationMessages));
	}

	@When("^user selects following values from Search By drop down on Epars Page, with operator \"([^\"]*)\" enters \"([^\"]*)\" in Search textbox followed by click on Submit Button$")
	public void user_selects_following_values_from_Search_By_drop_down_on_Epars_Page_with_operator_enters_followed_by_click_on_Submit_Button(
			String operatorValue, String textBoxValue, DataTable searchByDrpDwnValues) {
		listOfSearchByDrpDwnValues.clear();
		listOfSearchByDrpDwnValues = searchByDrpDwnValues.asList(String.class);
		listOfActualValidationMessages.clear();
		for (String searchByDrpDwnValue : listOfSearchByDrpDwnValues) {
			eparsProHandoffPage.selectSearchByDropdownValue(searchByDrpDwnValue);
			eparsProHandoffSteps.enterOperatorAndSearchByTextBox(operatorValue, textBoxValue);
			eparsProHandoffPage.clickSubmitBtn();
			listOfActualValidationMessages.add(eparsProHandoffPage.getValidationMessage());
		}
	}

	@Then("^user should be able to view following message on Epars page \"([^\"]*)\"$")
	public void user_should_be_able_to_view_following_message_on_Epars_page(String expMessage) {
		Assert.assertTrue(
				"The error messages are not as expected. Expected: All the error messages displayed should be: "
						+ expMessage
						+ "Actual list of error messages displayed for each SearchBy Value respectively selected:"
						+ listOfActualValidationMessages,
				eparsProHandoffSteps.verifyValidationMessages(listOfActualValidationMessages, expMessage));
	}

	@Then("^user should be able to view the grid with following columns:$")
	public void user_should_be_able_to_view_the_grid_with_following_columns(DataTable searchResultsColumnHeaders) {
		List<String> listOfExpValuesforSearchResultsColumnNames = searchResultsColumnHeaders.asList(String.class);
		for (int i = 0; i < listOfAllSearchHeaders.size(); i++) {
			Assert.assertTrue(
					"There is a mismatch in the headers of the Search results grid for the following SearchBy value: "
							+ listOfSearchByDrpDwnValues.get(i) + " . Expected headers should be: "
							+ listOfExpValuesforSearchResultsColumnNames + " .Actual headers are: "
							+ listOfAllSearchHeaders.get(i),
					listOfAllSearchHeaders.get(i).equals(listOfExpValuesforSearchResultsColumnNames));
		}
	}

	@Then("^user should be able to view the same result in grid as SQL result$")
	public void user_should_be_able_to_view_the_same_result_in_grid_as_SQL_result() {
		for (int i = 0; i < listOfAllSearchHeaders.size(); i++) {
			Assert.assertTrue(
					"There is a mismatch in the headers of the Search results grid for the following SearchBy value: "
							+ listOfSearchByDrpDwnValues.get(i) + " . Expected headers from database: "
							+ listOfDBSearchColumnHeaders + " .Actual headers are: " + listOfAllSearchHeaders.get(i),
					listOfAllSearchHeaders.get(i).equals(listOfDBSearchColumnHeaders));
		}
	}

	@When("^EparsPro user runs the query to get the expected columns in the grid: \"([^\"]*)\" $")
	public void eparspro_user_runs_the_query_to_get_the_expected_columns_in_the_grid(String query)
			throws ClassNotFoundException, SQLException, IOException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(query, dbQueryFilename)));
		ResultSetMetaData rsmd = DatabaseConn.resultSet.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			listOfDBSearchColumnHeaders.add(rsmd.getColumnName(i));
		}
	}

	@When("^user selects following values from Search By drop down on Epars Page, with operator \"([^\"]*)\" enters following data \"([^\"]*)\" in Search textbox followed by click on Submit Button$")
	public void user_selects_following_values_from_Search_By_drop_down_on_Epars_Page_with_operator_enters_following_data_in_Search_textbox_followed_by_click_on_Submit_Button(
			String operatorValue, String textBoxValue, DataTable searchByDrpDwnValues) {
		listOfSearchByDrpDwnValues.clear();
		listOfSearchByDrpDwnValues = searchByDrpDwnValues.asList(String.class);
		for (String searchByDrpDwnValue : listOfSearchByDrpDwnValues) {
			eparsProHandoffPage.selectSearchByDropdownValue(searchByDrpDwnValue);
			eparsProHandoffSteps.enterOperatorAndSearchByTextBox(operatorValue, textBoxValue);
			eparsProHandoffPage.clickSubmitBtn();
			listOfAllSearchHeaders.add(eparsProHandoffPage.getSearchResultsTableHeaders());
		}
	}
}