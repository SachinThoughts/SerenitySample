package r1.prcmbe.steps.definitions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.DefectWorkflowPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.PRCMSaveSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;

public class PRCMSaveStepDef {

	DefectWorkflowPage defectWorkflowPage;
	CommonMethods commonMethods;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;

	private String tabColorOnDefectWrkflwSection = "rgba(61, 100, 168, 1)";

	@Steps
	SearchPageSteps searchPageSteps;
	@Steps
	FinancialInfoSteps financialInfoStep;
	@Steps
	PRCMSaveSteps prcmSaveSteps;

	String dbInvoiceNumber, dbDefectAccountKey, dbInvoiceId, dbUserId, dbDefectTypeId, dbDefectSubCategoryId, dbName;
	List<String> listOfDefectAttributeTypeIdFromDb = new ArrayList<>(), listOfAttributeValueFromDb = new ArrayList<>(),
			listOfSOPHavingIsRequired0FromDb = new ArrayList<>(), listOfSOPHavingIsRequired1FromDb = new ArrayList<>(),
			listOfApplicationNameFromDb = new ArrayList<>(), listOfApplicationIdFromDb = new ArrayList<>(),
			listOfSOPActionFromDb = new ArrayList<>(), listOfSOPFromUI = new ArrayList<>();
	private static String dbQueryFilenameForAccntActnHistory = "AccountActionHistory",
			dbQueryFilenameForPRCMSave = "PRCMSave";

	@When("^user clicks on Next button under Defect workflow section$")
	public void user_clicks_on_Next_button_under_Defect_workflow_section() {
		defectWorkflowPage.clickOnNextButton();
	}

	@When("^user clicks on Next button after selecting any step$")
	public void user_clicks_on_Next_button_after_selecting_any_step() {
		defectWorkflowPage.clickSOPActionOnTriagePage();
		defectWorkflowPage.clickOnNextButtonOnTriagePage();
	}

	@Then("^user should be able to view \"([^\"]*)\" section$")
	public void user_should_be_able_to_view_Step_s_taken_section(String expectedSectionValue) {
		Assert.assertTrue("failed to navigate taken Section",
				defectWorkflowPage.getActionSectionHeaderText().contains(expectedSectionValue));
	}

	@Then("^user should be able to view the bar move to Action$")
	public void user_should_be_able_to_view_the_bar_move_to_Action() {
		Assert.assertTrue("User is not able to view the bar moved to Action",
				defectWorkflowPage.getActionTabColourVal().trim().equals(tabColorOnDefectWrkflwSection));
	}

	@When("^user selects checkbox from Verify All Steps Taken checkboxes$")
	public void user_selects_checkbox_from_Verify_All_Steps_Taken_checkboxes() {
		defectWorkflowPage.clickSOPActionOnTriagePage();
	}

	@When("^user selects checkbox from Steps Taken checkboxes$")
	public void user_selects_checkbox_from_Steps_Taken_checkboxes() {
		defectWorkflowPage.clickSOPActionOnActionPage();
	}

	@Then("^user should be able to view the message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_message(String expectedMsg) {
		String actualSuccessMsg = defectWorkflowPage.getSuccessMessage();
		Assert.assertTrue("User is not able to view the expected message . Actual Message :" + actualSuccessMsg,
				actualSuccessMsg.equals(expectedMsg));
	}

	@When("^user clicks on A2D Save button$")
	public void user_clicks_on_A2D_Save_button() {
		defectWorkflowPage.clickOnA2DSaveButton();
	}

	@When("^user enters Invoice Number fetched from database in invoice number textbox$")
	public void user_enters_Invoice_Number_fetched_from_database_in_invoice_number_textbox() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
	}

	@Then("^user is able to view the Account Page$|^user is on Account Page$")
	public void user_is_able_to_view_the_Account_Page() {
		Assert.assertTrue("User is not navigated to the R1D account page for Searched Invoice Number",
				searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber));
	}

	@When("^user moves to the Account Action History section$")
	public void user_moves_to_account_action_history_section() {
		accInfoPage.moveToAccountActionHistory();
	}

	@Then("^user should be able to view the entries in these tables$")
	public void user_should_be_able_to_view_the_entries_in_these_tables() throws SQLException {
		Assert.assertTrue("User is not able to view the entries in provide sql query ", DatabaseConn.resultSet.next());
	}

	@Then("^user should be able to view the defect Attributetypeid is (\\d+) and attributevalue is URL$")
	public void user_should_be_able_to_view_the_defect_Attributetypeid_is_and_attributevalue_is_URL(int expectedId) {
		Assert.assertTrue("User is not able to view the expected Defect AttributeTypeId ",
				listOfDefectAttributeTypeIdFromDb.contains(Integer.toString(expectedId)));
		Assert.assertTrue("User is not able to view the attribute as URL",
				prcmSaveSteps.verifyAttributeValueFromDbAsUrl(listOfAttributeValueFromDb));
	}

	@When("^user runs the query \"([^\"]*)\" to fetch \"([^\"]*)\"$")
	public void user_runs_the_query_to_fetch_Invoice_Id(String queryName, String valueToFetch)
			throws ClassNotFoundException, SQLException, Exception {
		switch (valueToFetch) {
		case "Invoice Id":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					String.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbInvoiceNumber));
			try {
				while (DatabaseConn.resultSet.next()) {
					dbInvoiceId = DatabaseConn.resultSet.getString("id");
				}
				financialInfoStep.log("InvoiceId in DB" + dbInvoiceId);
			} catch (SQLException exception) {
				Assert.assertTrue("InvoiceId is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
			}
			break;
		case "Invoice Number":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					commonMethods.loadQuery(queryName, dbQueryFilenameForAccntActnHistory));
			try {
				while (DatabaseConn.resultSet.next()) {
					dbInvoiceNumber = DatabaseConn.resultSet.getString("invoiceNumber");
				}
				financialInfoStep.log("invoiceNumber in DB" + dbInvoiceNumber);
			} catch (SQLException exception) {
				Assert.assertTrue("invoiceNumber is not fetched from DB.\nThe Technical Error is:\n" + exception,
						false);
			}
			break;
		case "DefectAccountKey":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					String.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbInvoiceId));
			try {
				while (DatabaseConn.resultSet.next()) {
					dbDefectAccountKey = DatabaseConn.resultSet.getString("DefectAccountKey");
				}
				financialInfoStep.log("DefectAccountKey in DB" + dbInvoiceNumber);
			} catch (SQLException exception) {
				Assert.assertTrue("DefectAccountKey is not fetched from DB.\nThe Technical Error is:\n" + exception,
						false);
			}
			break;
		case "Result Set For Created User":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					String.format(commonMethods.loadQuery("getUserId", dbQueryFilenameForPRCMSave),
							CommonMethods.loadProperties("prcmBeUsername")));
			while (DatabaseConn.resultSet.next()) {
				dbUserId = DatabaseConn.resultSet.getString("UserID");
			}
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
					commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbDefectAccountKey, dbUserId));
			break;
		case "DefectTypeAttributeId and AttributeVal":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
					commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbDefectAccountKey, dbUserId));
			try {
				while (DatabaseConn.resultSet.next()) {
					listOfDefectAttributeTypeIdFromDb.add(DatabaseConn.resultSet.getString("DefectAttributeTypeID"));
					listOfAttributeValueFromDb.add(DatabaseConn.resultSet.getString("AttributeValue"));
				}
			} catch (SQLException exception) {
				Assert.assertTrue(
						"DefectAttributeTypeId and AttributeValue is not fetched from DB.\nThe Technical Error is:\n"
								+ exception,
						false);
			}
			break;
		case "Result Set":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					String.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbInvoiceId));
			break;

		case "Application name and Application ID":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
					commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave));
			try {
				while (DatabaseConn.resultSet.next()) {
					listOfApplicationNameFromDb.add(DatabaseConn.resultSet.getString("ApplicationName"));
					listOfApplicationIdFromDb.add(DatabaseConn.resultSet.getString("ApplicationID"));
				}
			} catch (SQLException exception) {
				Assert.assertTrue(
						"DefectAttributeTypeId and AttributeValue is not fetched from DB.\nThe Technical Error is:\n"
								+ exception,
						false);
			}
			break;
		case "DefectCategoryID":
			String defectSubCategoryDesc = accInfoPage.getDefectSubcategoryBreadcrumb().trim();
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String
					.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), defectSubCategoryDesc));
			break;
		case "SOP List":
			DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String
					.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbDefectSubCategoryId));
			try {
				while (DatabaseConn.resultSet.next()) {
					listOfSOPActionFromDb.add(DatabaseConn.resultSet.getString("ActionName"));
				}
			} catch (SQLException exception) {
				Assert.assertTrue("SOP Action is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
			}
			break;
		}
	}

	@When("^user fetch Defect typeid$")
	public void user_fetch_Defect_typeid() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			dbDefectTypeId = DatabaseConn.resultSet.getString("DefectTypeID");
		}
	}

	@When("^user fetch Defectsubcategoryid$")
	public void user_fetch_Defectsubcategoryid() throws SQLException {
		while (DatabaseConn.resultSet.next()) {
			dbDefectSubCategoryId = DatabaseConn.resultSet.getString("DefectSubCategoryID");
		}
	}

	@Then("^user should be able to view Defect SubCategory id for which SOP loaded$")
	public void user_should_be_able_to_view_Defect_SubCategory_id_for_which_SOP_loaded() {
		Assert.assertTrue(
				"User is not able to view the Defect Subcategory Id for which SOP Loaded. Defect Category Id from ID:"
						+ dbDefectSubCategoryId,
				dbDefectSubCategoryId != null);
	}

	@Then("^user should be able to view optional and mandatory actions$")
	public void user_should_be_able_to_view_optional_and_mandatory_actions() {
		Assert.assertTrue(
				"User is not able to view optional and mandatory actions. List Of Actions are : "
						+ defectWorkflowPage.getSOPActionsOnActionPage(),
				defectWorkflowPage.getSizeOfSOPActionOnActionPage() > 0);
	}

	@Then("^user should be able to view SOP actions having IsRequired=0$")
	public void user_should_be_able_to_view_SOP_actions_having_IsRequired_0() {
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfSOPHavingIsRequired0FromDb.add(DatabaseConn.resultSet.getString("ActionName"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("ActionName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		Assert.assertTrue("User is not able to view SOP Actions having IsRequired 0",
				listOfSOPHavingIsRequired0FromDb.size() > 0);
	}

	@Then("^user should be able to view SOP actions having IsRequired=1$")
	public void user_should_be_able_to_view_SOP_actions_having_IsRequired_1() {
		try {
			while (DatabaseConn.resultSet.next()) {
				listOfSOPHavingIsRequired1FromDb.add(DatabaseConn.resultSet.getString("ActionName"));
			}
		} catch (SQLException exception) {
			Assert.assertTrue("ActionName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		Assert.assertTrue("User is not able to view SOP Actions having IsRequired 0",
				listOfSOPHavingIsRequired1FromDb.size() > 0);
	}

	@Then("^user should be able to view the optional Sop actions in verify All Steps Taken section$")
	public void user_should_be_able_to_view_the_optional_Sop_actions_in_verify_All_Steps_Taken_section() {
		List<String> listOfSOPOnTriageSection = defectWorkflowPage.getSOPActionsOnTriagePage();
		Assert.assertTrue("User is not able to view the SOP Actions in Verify All Steps Taken Section",
				listOfSOPOnTriageSection.containsAll(listOfSOPHavingIsRequired0FromDb));
	}

	@Then("^user should be able to view the optional Sop actions in Steps Taken section$")
	public void user_should_be_able_to_view_the_optional_Sop_actions_in_Steps_Taken_section() {
		List<String> listOfSOPOnActionSection = defectWorkflowPage.getSOPActionsOnActionPage();
		Assert.assertTrue("User is not able to view the SOP Actions in Verify All Steps Taken Section",
				listOfSOPOnActionSection.containsAll(listOfSOPHavingIsRequired1FromDb));
	}

	@When("^user runs the query \"([^\"]*)\" for fetching DefectTypeId$")
	public void user_runs_the_query_for_fetching_DefectTypeId(String queryName)
			throws ClassNotFoundException, SQLException, IOException, Exception {
		String defectType = accInfoPage.getDefectTypeBreadcrumb().trim();
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), defectType));
	}

	@When("^user runs the query \"([^\"]*)\" with passing by defecttypeid$")
	public void user_runs_the_query_with_passing_by_defecttypeid(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		String defectSubCategoryDesc = accInfoPage.getDefectSubcategoryBreadcrumb().trim();
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbDefectTypeId, defectSubCategoryDesc));
	}

	@When("^user runs the query \"([^\"]*)\" for fetching SOP having IsRequired=(\\d+)$")
	public void user_runs_the_query_for_fetching_SOP_having_IsRequired(String queryName, int isrequired)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, String.format(
				commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), isrequired, dbDefectSubCategoryId));
	}

	@Then("^user should be able to view some invoice id fetched from DB$")
	public void user_should_be_able_to_view_some_invoice_id() {
		Assert.assertTrue("User is not able to view invoice id fetched from DB", dbInvoiceNumber != null);
	}

	@When("^user clicks on Previous button on Action Section$")
	public void user_clicks_on_Next_button_on_TriagePage() {
		defectWorkflowPage.clickOnPreviousButtonOnActionSection();
	}

	@Then("^user should be able to view selected database as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_selected_database_as(String expectedDbName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery("getDBName", dbQueryFilenameForPRCMSave));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbName = DatabaseConn.resultSet.getString("DBName");
			}
		} catch (SQLException exception) {
			Assert.assertTrue("DBName is not fetched from DB.\nThe Technical Error is:\n" + exception, false);
		}
		Assert.assertTrue("User is not able to view selected database . Fetched Database Name :" + dbName,
				dbName.equals(expectedDbName));
	}

	@Then("^user should be able to view the Application name and Application ID$")
	public void user_should_be_able_to_view_the_Application_name_and_Application_ID() {
		Assert.assertTrue("User is not able to view the Application Name .", listOfApplicationNameFromDb.size() > 0);
		Assert.assertTrue("User is not able to view the Application Id .", listOfApplicationIdFromDb.size() > 0);
	}

	@When("^user runs the query \"([^\"]*)\" with passing by defectsubcategoryid$")
	public void user_runs_the_query_with_passing_by_defectsubcategoryid(String queryName)
			throws ClassNotFoundException, SQLException, Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbQueryFilenameForPRCMSave), dbDefectSubCategoryId));
	}

	@Then("^user should be able to view the Skillid for all Major payer for defectsubcategoryid$")
	public void user_should_be_able_to_view_Skillid_for_all_Major_payer_for_defectsubcategoryid() throws SQLException {
		List<Integer> skillsId = new ArrayList<>();
		while (DatabaseConn.resultSet.next()) {
			skillsId.add(DatabaseConn.resultSet.getInt("SkillID"));
		}
		Assert.assertTrue("Skill Id's not fetched from database", !skillsId.isEmpty());
	}

	@Then("^user should be able to view the SOP list for the passed defect sub category$")
	public void user_should_be_able_to_view_the_SOP_list_for_the_passed_defect_sub_category() {
		Assert.assertTrue("User is not able to view the SOP list for passed defect sub category ",
				!listOfSOPActionFromDb.isEmpty());
	}

	@When("^user checks the SOP steps from DB in UI$")
	public void user_checks_the_SOP_steps_from_DB_in_UI() {
		listOfSOPFromUI = defectWorkflowPage.getListOfSOPFromUI();
	}

	@Then("^user should be able to view same SOPs Step from DB in UI$")
	public void user_should_be_able_to_view_same_SOPs_Step_from_DB_in_UI() {
		Assert.assertTrue("User is not able to view same SOP Step from DB in UI .SOP Step from DB :"
				+ listOfSOPActionFromDb + " SOP Step from UI :" + listOfSOPFromUI,
				listOfSOPFromUI.containsAll(listOfSOPActionFromDb));
	}
}