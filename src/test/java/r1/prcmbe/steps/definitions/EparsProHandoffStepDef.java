package r1.prcmbe.steps.definitions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.EparsProHandoffPage;
import r1.prcmbe.serenity.steps.EparsProHandoffSteps;

public class EparsProHandoffStepDef {

	BillingAndFollowUpPage billingAndFollowupPage;
	EparsProHandoffPage eparsProHandoffPage;

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
		List<String> listOfSearchByDrpDwnValues = searchByDrpDwnValues.asList(String.class);
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
			String operatorValue,String textBoxValue, DataTable searchByDrpDwnValues) {
		List<String> listOfSearchByDrpDwnValues = searchByDrpDwnValues.asList(String.class);
		listOfActualValidationMessages.clear();
		for (String searchByDrpDwnValue : listOfSearchByDrpDwnValues) {
			eparsProHandoffPage.selectSearchByDropdownValue(searchByDrpDwnValue);
			eparsProHandoffSteps.enterOperatorAndSearchByTextBox(operatorValue,textBoxValue);
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
}