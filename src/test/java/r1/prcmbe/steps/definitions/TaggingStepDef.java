package r1.prcmbe.steps.definitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.TaggingPage;

public class TaggingStepDef extends PageObject {
	SettingsPage settingsPage;
	TaggingPage taggingPage;
	final String ALERTBGCOLOR = "rgba(76, 144, 221, 1)";
	List<String> listOfGridColumnsOnUI = new ArrayList<>();
	String enteredCategoryName, dbCategoryName;
	CommonMethods commonMethods;
	private static String dbFileName = "Tagging";

	@When("^User clicks on Tag Configuration link$")
	public void user_clicks_on_Tag_Configuration_link() {
		settingsPage.clickTagConfigLink();
	}

	@Given("^User is on Account Tags Configuration screen$")
	public void user_is_on_Account_Tags_Configuration_screen() {
		taggingPage.isTagConfigPageVisible();
	}

	@Then("^User should be able to view Account Tags Configuration as page title$")
	public void user_should_be_able_to_view_Account_Tags_Configuration_as_page_title() {
		Assert.assertTrue("Account tag configuration page is not displyed", taggingPage.isTagConfigPageVisible());
	}

	@Then("^User should able to view Search Category Name textbox$")
	public void user_should_able_to_view_Search_Category_Name_textbox() {
		Assert.assertTrue("Search category name textbox is not displayed",
				taggingPage.isSearchCategoryNameTxtBoxVisible());
	}

	@Then("^User should be able to view TAG CATEGORY tab as selected$")
	public void user_should_be_able_to_view_TAG_CATEGORY_tab_as_selected() {
		Assert.assertTrue("Tag Category tab is not visible by default selected", taggingPage.isTagCategoryTabVisible());
	}

	@Then("^User should be able to view TAG NAME tab$")
	public void user_should_be_able_to_view_TAG_NAME_tab() {
		Assert.assertTrue("User is not able to view Tag Name tab", taggingPage.isTagNameTabVisible());
	}

	@Then("^User should be able to view Search Categories label$")
	public void user_should_be_able_to_view_Search_Categories_label() {
		Assert.assertTrue("User is not able to view search categories label",
				taggingPage.isSearchCategoryLabelVisible());
	}

	@Then("^User should be able to view \\+Add New Category button$")
	public void user_should_be_able_to_view_Add_New_Category_button() {
		Assert.assertTrue("User is not able to view Add New Category Button", taggingPage.isAddNewCategoryBtnVisible());
	}

	@Then("^User should be able to view Continue button$")
	public void user_should_be_able_to_view_Continue_button() {
		Assert.assertTrue("User is not able to view Continue button on screen", taggingPage.isContinueBtnVisible());
	}

	@Then("^User should be able to view following Grid headers$")
	public void user_should_be_able_to_view_following_Grid_headers(DataTable expectedColumns) {
		List<String> expectedListOfGridColumns = expectedColumns.asList(String.class);
		listOfGridColumnsOnUI = taggingPage.getListOfColHeaders();
		Assert.assertTrue("All the grid columns values are not visible",
				expectedListOfGridColumns.containsAll(listOfGridColumnsOnUI));
	}

	@When("^user enters some random text in Search Category Name Textbox$")
	public void user_enters_some_random_text_in_Search_Category_Name_Textbox() {
		taggingPage.enterRandomTxtInSearchCategoryNameTxtbox();
	}

	@Then("^User should be able to view the validation message \"([^\"]*)\" with blue background\\.$")
	public void user_should_be_able_to_view_the_validation_message_with_blue_background(String expectedMsg) {
		Assert.assertTrue("Validation message is not displayed on UI", taggingPage.getAlertMsg().equals(expectedMsg));
		Assert.assertTrue("Background colour of alert in not blue",
				taggingPage.getAlertSectionColor().equals(ALERTBGCOLOR));
	}

	@When("^User clicks on Add New Category button$")
	public void user_clicks_on_Add_New_Category_button() {
		taggingPage.clickAddNewCategoryBtn();
	}

	@When("^User enters more than 100 alphabets in Category name$")
	public void user_enters_more_than_alphabets_in_Category_name() {
		taggingPage.enterMreThnHundredCharsInCategoryNameTxtBox();
	}

	@When("^User enters upto 500 alphabets in Category description$")
	public void user_enters_upto_alphabets_in_Category_description() {
		taggingPage.enterUptoFiveHundredCharsInTagDiscTxtBox();
	}

	@When("^User selects \"([^\"]*)\" value from the Application drop down$")
	public void user_selects_value_from_the_Application_drop_down(String drpdwnVal) {
		taggingPage.selectApplication(drpdwnVal);
	}

	@When("^User clicks on Active switch$")
	public void user_clicks_on_Active_switch() {
		taggingPage.clickActiveSlider();
	}

	@When("^User clicks on Save button$")
	public void user_clicks_on_Save_button() {
		taggingPage.clickOnSaveBtn();
	}

	@Then("^User should be able to view the validation message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_validation_message(String expectedValidationMsg) {
		Assert.assertTrue("User is not able to veiw validation message like" + expectedValidationMsg,
				taggingPage.getCategoryNameValidationMsg().equalsIgnoreCase(expectedValidationMsg));
	}

	@When("^User enters upto 100 alphabets in Category name$")
	public void user_enters_upto_100_alphabets_in_Category_name() {
		taggingPage.enterUptoHundredCharsInCategoryNameTxtBox();
	}

	@When("^User enters more than 500 alphabets in Category description$")
	public void user_enters_more_500_than_alphabets_in_Category_description() {
		taggingPage.enterMoreThnFiveHundredCharsInTagDiscTxtBox();
	}

	@When("^User enters upto 100 alphabets in Category Name (.*)  textbox$")
	public void user_enters_upto_alphabets_in_Category_Name_AutomationTest_textbox(String categoryName) {
		enteredCategoryName = categoryName.concat((" " + RandomStringUtils.randomAlphabetic(5)));
		System.out.println(enteredCategoryName);
		taggingPage.enterTxtInCategoryNameTxtBox(enteredCategoryName);
	}

	@When("^User enters upto 500 alphabets in Tag Description (.*) textbox$")
	public void user_enters_upto_alphabets_in_Tag_Description_AutomationTest_textbox(String tagDescrp) {
		System.out.println("Category Decription : " + tagDescrp.concat((" " + RandomStringUtils.randomAlphabetic(3))));
		taggingPage.enterTxtInTagDescTxtBox(tagDescrp.concat((" " + RandomStringUtils.randomAlphabetic(3))));
	}

	@When("^user runs the query to verify newly added tag category$")
	public void user_runs_the_query_to_verify_newly_added_tag_category(String queryName) throws Exception {
		String query = commonMethods.loadQuery(queryName, dbFileName);
		query = String.format(query, enteredCategoryName);
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, query);
	}

	@Then("^User should be able to view the newly added Tag Category in SQL result$")
	public void user_should_be_able_to_view_the_newly_added_Tag_Category_in_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbCategoryName = DatabaseConn.resultSet.getString("CategoryName");
				System.out.println(dbCategoryName);
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Category Name is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Category Name on UI does not match with database",
				taggingPage.getlistOfCategoryName().contains(dbCategoryName));
	}

	@Then("^User should be able to view the Success message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_Success_message(String arg1) {

	}
}