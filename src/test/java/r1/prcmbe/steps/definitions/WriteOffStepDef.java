package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.WorkflowConfigurationPage;

public class WriteOffStepDef extends PageObject {

	CommonMethods commonMethods;
	WorkflowConfigurationPage workflowConfigPage;
	CallPayerQueuePage callPayerQueuePage;

	private static String dbQueryFilename = "WriteOff";

	@Given("^user having level 1 Approver role is on R1 Hub page$")
	public void user_having_level_1_Approver_role_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies 2.0 - 15 Home"));
	}

	@When("^user runs the \"([^\"]*)\" query to fetch the invoice number$")
	public void user_runs_the_query_to_fetch_the_invoice_number(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbQueryFilename));
	}

	@Then("^user should be able to view the Approval Request  popup$")
	public void user_should_be_able_to_view_the_Approval_Request_popup() {
		Assert.assertTrue("User not able to view Approval request popup", callPayerQueuePage.isApprovalPopupVisisble());
	}

	@Then("^user should be able to view Category drop down$")
	public void user_should_be_able_to_view_Category_drop_down() {
		Assert.assertTrue("User not able to view category dropdown", callPayerQueuePage.isCategoryVisible());
	}

	@Then("^user should be able to view Write Off Type dropdown$")
	public void user_should_be_able_to_view_Write_Off_Type_dropdown() {
		Assert.assertTrue("User not able to view Write Off Type dropdown", callPayerQueuePage.isWriteOffTypeVisible());
	}

	@Then("^user should be able to view Write Off Amount dropdown$")
	public void user_should_be_able_to_view_Write_Off_Amount_dropdown() {
		Assert.assertTrue("User not able to view Write Off Amount dropdown",
				callPayerQueuePage.isWriteOffAmountVisible());
	}

	@Then("^user should be able to view Notes text area$")
	public void user_should_be_able_to_view_Notes_text_area() {
		Assert.assertTrue("User not able to view Notes text area", callPayerQueuePage.isWriteOffNotesVisible());
	}

	@Then("^user should be able to view Close button$")
	public void user_should_be_able_to_view_Close_button() {
		Assert.assertTrue("User not able to view close button", callPayerQueuePage.isApprovalPopupCloseBtnVisible());
	}

	@Then("^user should be able to view Save button$")
	public void user_should_be_able_to_view_Save_button() {
		Assert.assertTrue("User not able to view save button", callPayerQueuePage.isSaveWriteOffBtnVisible());
	}

	@Then("^user should be able to view T-Code to Use drodown$")
	public void user_should_be_able_to_view_T_Code_to_Use_drodown() {
		Assert.assertTrue("User not able to view T-Code to Use drodown", callPayerQueuePage.isTCodeToUseTypeVisible());
	}
}
