package r1.prcmbe.steps.definitions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.TaggingPage;

public class TaggingStepDef extends PageObject {
	SettingsPage settingsPage;
	TaggingPage taggingPage;
	final String ALERTBGCOLOR = "rgba(76, 144, 221, 1)";
	List<String> listOfGridColumnsOnUI = new ArrayList<>();

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
}
