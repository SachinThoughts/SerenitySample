package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import r1.prcmbe.pages.RelatedAccountsPage;

public class RelatedAccountsStepDef {
	RelatedAccountsPage relatedAccntsPage;
	
	@When("^user clicks on Related Accounts under Patient & Facility Info Section$")
	public void user_clicks_on_Related_Accounts_under_Patient_Facility_Info_Section() {
		relatedAccntsPage.clickRelatedAccountsBtn();
	}

	@Then("^user should able to view the pop up title as \"([^\"]*)\"$")
	public void user_should_able_to_view_the_pop_up_title_as(String popupTitle) {
	    Assert.assertTrue("Popup title is not displayed",relatedAccntsPage.getRelatedAccPopupLabelTxt().equals(popupTitle));
	}

	@Then("^user should be able to view Search button$")
	public void user_should_be_able_to_view_Search_button() {
	    Assert.assertTrue("Search button is not visible",relatedAccntsPage.isSearchBtnVisible());
	}

	@Then("^user should be able to view First button$")
	public void user_should_be_able_to_view_First_button() {
		Assert.assertTrue("First button is not visible",relatedAccntsPage.isFirstBtnVisible());
	}

	@Then("^user should be able to view Previous button$")
	public void user_should_be_able_to_view_Previous_button() {
		Assert.assertTrue("Previous button is not visible",relatedAccntsPage.isPreviousBtnVisible());
	}

	@Then("^user should be able to view Next Button$")
	public void user_should_be_able_to_view_Next_Button() {
		Assert.assertTrue("Next button is not visible",relatedAccntsPage.isNextBtnVisible());
	}

	@Then("^user should be able to view Last Button$")
	public void user_should_be_able_to_view_Last_Button() {
		Assert.assertTrue("Next button is not visible",relatedAccntsPage.isLastBtnVisible());
	}

	@Then("^user should be able to view (\\d+) button$")
	public void user_should_be_able_to_view_button(int pageNumber) {
		Assert.assertTrue(pageNumber+"page is not visible",relatedAccntsPage.getDefaultSelectedPage().equals(Integer.toString(pageNumber)));
	}

	@Then("^user should be able to view following grid columns$")
	public void user_should_be_able_to_view_following_grid_columns(DataTable arg1) {
	    
	}

	@Then("^user should be able to view maximum (\\d+) Accounts under Related Accounts grid$")
	public void user_should_be_able_to_view_maximum_Accounts_under_Related_Accounts_grid(int arg1) {
	    
	}

	@When("^user runs query to fetch Related Accounts RelatedInvoices_(\\d+)_SQL(\\d+)$")
	public void user_runs_query_to_fetch_Related_Accounts_RelatedInvoices___SQL(int arg1, int arg2) {
	    
	}

	@Then("^user should be able to view same list of accounts in grid as in SQL result$")
	public void user_should_be_able_to_view_same_list_of_accounts_in_grid_as_in_SQL_result() {
	    
	}

}
