package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;

public class BSODayNightFollowUpStepDef {
	AccountInformationPage accInfoPage;
	CommonMethods commonMethods;
	private static String dbQueryFilename = "BSODayNightFollowUp";
	
	@When("^user executes the query to fetch InvoiceNumber (.*)$")
	public void user_executes_the_query_to_fetch_InvoiceNumber(String query) {
		try {
			String finalQuery = commonMethods.loadQuery(query, dbQueryFilename);
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, finalQuery);
		} catch (Exception e) {
			Assert.assertTrue("unable to execute query" + e, false);
		}
	}

	@When("^user clicks on handOff button$")
	public void user_clicks_on_handOff_button() {
		accInfoPage.clickHandOffBtn();
	}

	@Then("^user should be able to view Hand-off pop-up window$")
	public void user_should_be_able_to_view_Hand_off_pop_up_window() {
		Assert.assertTrue("HandOff Popup is not visible",accInfoPage.isHandOffPopUpVisible());
	}

	@When("^user selects \"([^\"]*)\" from hand off type dropdown$")
	public void user_selects_from_hand_off_type_dropdown(String value) {
		accInfoPage.selectHandOffTypeValue(value);
	}

	@Then("^user should be able to select Hand Off Type as \"([^\"]*)\"$")
	public void user_should_be_able_to_select_Hand_Off_Type_as(String handOffType) {
		Assert.assertTrue("Expected HandOff type is not selected",accInfoPage.getSelectedHandOffTypeValue().equals(handOffType));
	}
}
