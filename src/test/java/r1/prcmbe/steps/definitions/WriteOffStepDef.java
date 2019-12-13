package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.WorkflowConfigurationPage;

public class WriteOffStepDef extends PageObject {

	CommonMethods commonMethods;
	WorkflowConfigurationPage workflowConfigPage;

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

}
