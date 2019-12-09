package r1.prcmbe.steps.definitions;

import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.AccountActionHistoryPage;
import r1.prcmbe.pages.AccountInformationPage;

public class AccountActionHistoryStepDef {
	AccountInformationPage accInfoPage;
	AccountActionHistoryPage accActionHistoryPage;
	String invoiceNumber;

	@Given("^user is on Account Information Page$")
	public void user_is_on_Account_Information_Page() {
		accInfoPage.verifyPatientDetailsSectionVisible();
	}
	
	@When("^user fetch invoice number from database$")
	public void user_fetch_invoice_number_from_database() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			invoiceNumber = DatabaseConn.resultSet.getString("invoicenumber");
		}
	}

	@When("^user clicks on back and forth arrows$")
	public void user_clicks_on_back_and_forth_arrows() {
		accActionHistoryPage.clickPreviousArrow();
	}

	@Then("^user should be able to view all defect subcategory previously associated with that invoice$")
	public void user_should_be_able_to_view_all_defect_subcategory_previously_associated_with_that_invoice() {
	    Assert.assertTrue("Defect subcategory is not visible", accActionHistoryPage.isPreviousDefectSubCategoryVisible());
	}

	@When("^user selects any checkbox in Verify All Steps Taken Section$")
	public void user_selects_any_checkbox_in_Verify_All_Steps_Taken_Section() {
	    
	}

	@When("^user selects any checkbox in steps Taken Section$")
	public void user_selects_any_checkbox_in_steps_Taken_Section() {
	   
	}

	@Then("^user should be able to view the Blue bubble code display as D on horizontal timeline$")
	public void user_should_be_able_to_view_the_Blue_bubble_code_display_as_D_on_horizontal_timeline() {
	    
	}

	@When("^user hovers the activity bubbles$")
	public void user_hovers_the_activity_bubbles() {
	    
	}

	@Then("^user should be able to view all fields of that action$")
	public void user_should_be_able_to_view_all_fields_of_that_action(DataTable arg1) {
	    
	}

	@Then("^user is able to view Account History Action Notes with following fields$")
	public void user_is_able_to_view_Account_History_Action_Notes_with_following_fields(DataTable arg1) {
	    
	}

}
