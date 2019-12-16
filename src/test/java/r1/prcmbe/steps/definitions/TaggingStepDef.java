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
import net.thucydides.core.annotations.Steps;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.SettingsPage;
import r1.prcmbe.pages.TaggingPage;
import r1.prcmbe.serenity.steps.FinancialInfoSteps;
import r1.prcmbe.serenity.steps.SearchPageSteps;
import r1.prcmbe.serenity.steps.TaggingSteps;

public class TaggingStepDef extends PageObject {
	SettingsPage settingsPage;
	TaggingPage taggingPage;
	SearchPage searchPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	final String ALERTBGCOLOR = "rgba(76, 144, 221, 1)";
	List<String> categoryNameList = new ArrayList<>();
	List<String> listOfGridColumnsOnUI = new ArrayList<>();
	List<String> listOfTagHeadersOnUI = new ArrayList<>();
	List<String> listOfTagDetailsInDB = new ArrayList<>();
	List<String> listOfEditedTagDetailsOnUi = new ArrayList<>();

	String enteredCategoryName, dbCategoryName, dbCategoryDecs, successMsg, editedCategoryName, radioBtnTxt, newTagName,
			tagAddedSuccessMsg, dbTagName, selectedTagName, selectedTagCategory, dbInvoiceNumber, dbInvoiceId;
	CommonMethods commonMethods;
	private static String dbFileName = "Tagging";
	@Steps
	TaggingSteps taggingSteps;
	@Steps
	FinancialInfoSteps financialInfoSteps;
	@Steps
	SearchPageSteps searchPageSteps;

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
		taggingPage.enterTxtInCategoryNameTxtBox(enteredCategoryName);
	}

	@When("^User enters upto 500 alphabets in Tag Description (.*) textbox$")
	public void user_enters_upto_alphabets_in_Tag_Description_AutomationTest_textbox(String tagDescrp) {
		taggingPage.enterTxtInTagDescTxtBox(tagDescrp.concat((" " + RandomStringUtils.randomAlphabetic(3))));
	}

	@When("^user runs the tag category (.*) query to verify newly added tag category$")
	public void user_runs_the_tag_category_Tagging___SQL_query_to_verify_newly_added_tag_category(String queryName)
			throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), enteredCategoryName));
	}

	@Then("^User should be able to view the newly added Tag Category in SQL result$")
	public void user_should_be_able_to_view_the_newly_added_Tag_Category_in_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbCategoryName = DatabaseConn.resultSet.getString("CategoryName");
				dbCategoryDecs = DatabaseConn.resultSet.getString("CategoryDescription");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Category Name is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Category Name on UI does not match with database",
				taggingPage.getlistOfCategoryName().contains(dbCategoryName)
						&& taggingPage.getlistOfCategoryDesc().contains(dbCategoryDecs));
	}

	@Then("^User should be able to view the Success message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_Success_message(String expectedMsg) {
		Assert.assertTrue("User is not able to view the success message", successMsg.trim().contains(expectedMsg));
	}

	@When("^User clicks on Edit button of added category$")
	public void user_clicks_on_Edit_button_of_added_category() {
		categoryNameList = taggingPage.getlistOfCategoryName();
		taggingPage.clickEditLink();
	}

	@When("^User edits Category Name (.*) in textbox$")
	public void user_edits_Category_Name_AutomationTest_in_textbox(String updatedCategoryName) {
		editedCategoryName = taggingSteps.getNewCategoryName(updatedCategoryName, categoryNameList);
		taggingPage.enterTxtInCategoryNameTxtBox(editedCategoryName);
	}

	@When("^User edits Tag Description (.*) in textbox$")
	public void user_edits_Tag_Description_TestDesciption_in_textbox(String tagDescrp) {
		taggingPage.enterTxtInTagDescTxtBox(tagDescrp.concat((" " + RandomStringUtils.randomAlphabetic(3))));
	}

	@Then("^User should be able to view the Updated added Tag Category in SQL result$")
	public void user_should_be_able_to_view_the_Updated_added_Tag_Category_in_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbCategoryName = DatabaseConn.resultSet.getString("CategoryName");
				dbCategoryDecs = DatabaseConn.resultSet.getString("CategoryDescription");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Category Name is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Updated Category Name and Decription on UI does not match with database",
				taggingPage.getlistOfCategoryName().contains(dbCategoryName)
						&& taggingPage.getlistOfCategoryDesc().contains(dbCategoryDecs));
	}

	@When("^User clicks on Active switch slide bar for Edit$")
	public void user_clicks_on_Active_switch_slide_bar_for_Edit() {
		taggingPage.clickOnActiveSlideBarEdit();
	}

	@When("^user runs the tag category query to verify newly updated tag category(.*)$")
	public void user_runs_the_tag_category_query_to_verify_newly_updated_tag_category(String queryName)
			throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), editedCategoryName));
	}

	@When("^User clicks on Save button and captures success message$")
	public void user_clicks_on_Save_button_and_captures_success_message() {
		successMsg = taggingPage.clickOnSaveAndGetSuccessMsg();
	}

	@When("^User clicks on Radio button of any category$")
	public void user_clicks_on_Radio_button_of_any_category() {
		radioBtnTxt = taggingPage.clickAndGetAnyRadioBtnTxt();
	}

	@When("^User clicks on Continue button$")
	public void user_clicks_on_Continue_button() {
		taggingPage.clickOnContinueBtn();
	}

	@Then("^User should be able to navigate to TAG NAME tab$")
	public void user_should_be_able_to_navigate_to_TAG_NAME_tab() {
		Assert.assertTrue("User is not able to navigate to  Tag Name Tab", taggingPage.isAddNewTagBtnVisible());
	}

	@Then("^User should be able to view Selected Category Label$")
	public void user_should_be_able_to_view_Selected_Category_Label() {
		Assert.assertTrue("User is not able to view selected category lable",
				taggingPage.isSelectedCategoryLblVisible());
	}

	@Then("^User should be able to view Selected Category Name label$")
	public void user_should_be_able_to_view_Selected_Category_Name_label() {
		Assert.assertTrue("User is not able to to view selecred category name label on UI",
				taggingPage.getCategoryNameFromBreadCrumb().equalsIgnoreCase(radioBtnTxt));
	}

	@When("^User clicks on Add New Tag button$")
	public void user_clicks_on_Add_New_Tag_button() {
		taggingPage.clickOnAddNewTagBtn();
	}

	@When("^User enters the value for Tag Name (.*) in Enter Tag Name textbox$")
	public void user_enters_the_value_for_AutomationTest_in_Enter_Tag_Name_textbox(String tagName) {
		newTagName = taggingSteps.getNewTagName(tagName);
		taggingPage.enterTxtInTagNameTxtBox(newTagName);
	}

	@When("^User enters the value for Tag Description (.*) in Enter Tag Description textbox$")
	public void user_enters_the_value_for_TestTagDesciption_in_Enter_Tag_Description_textbox(String tagDescription) {
		taggingPage.enterTxtInTagDescTxtBox(tagDescription.concat((" " + RandomStringUtils.randomAlphabetic(3))));
	}

	@When("^user selects multiple Facilities (.*) and (.*) from Facilities drop down$")
	public void user_selects_multiple_Facilities_WPWI_and_ABIL_from_Facilities_drop_down(String facility1,
			String facility2) {
		taggingPage.clickOnFacilityDrpdwn();
		taggingPage.selectMultipleFacility(facility1);
		taggingPage.selectMultipleFacility(facility2);
	}

	@When("^user runs query two the query to get newly added tag (.*)$")
	public void user_runs_query_two_the_query_to_get_newly_added_tag(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), newTagName));
	}

	@Then("^User should be able to view the newly added Tag in SQL result\\.$")
	public void user_should_be_able_to_view_the_newly_added_Tag_in_SQL_result() {
		try {
			while (DatabaseConn.resultSet.next()) {
				dbTagName = DatabaseConn.resultSet.getString("TagName");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Tag Name is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		Assert.assertTrue("Updated Tag Name on UI does not match with database",
				taggingPage.getlistOfTagName().contains(dbTagName));
	}

	@When("^User Clicks on Add Tag link$")
	public void user_Clicks_on_Add_Tag_link() {
		taggingPage.clickTagNameLinkAtAccInfoPge();
	}

	@When("^User selects any value from Tag Category dropdown$")
	public void user_selects_any_value_from_Tag_Category_dropdown() {
		selectedTagCategory = taggingPage.selectAndGetAnyTagCategory();
	}

	@When("^User selects any value from Tag Name dropdown$")
	public void user_selects_any_value_from_Tag_Name_dropdown() {
		selectedTagName = taggingPage.selectAndGetAnyTagName();
	}

	@When("^User enters some text \"([^\"]*)\" in Notes textbox$")
	public void user_enters_some_text_in_Notes_textbox(String tagNote) {
		taggingPage.enterTagNote(tagNote);
	}

	@When("^User clicks on Save Changes button$")
	public void user_clicks_on_Save_Changes_button() {
		tagAddedSuccessMsg = taggingPage.saveTagAndGetTagAddedSuccessMsg();
	}

	@Then("^User should be able to view message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message(String successMsg) {
		Assert.assertTrue("Tag has been added successfully message is not displayed on screen",
				tagAddedSuccessMsg.equalsIgnoreCase(successMsg));
	}

	@Then("^User should be able to view Tag Name under the title Tag$")
	public void user_should_be_able_to_view_Tag_Name_under_the_title_Tag() {
		Assert.assertTrue("Added Tag name is not displayed under the title tag",
				taggingPage.getAddedTagNameOnAccInfo().equalsIgnoreCase(selectedTagName));
	}

	@When("^User scrolls till Tag History section$")
	public void user_scrolls_till_Tag_History_section() {
		taggingPage.scrollTillTagHistorySection();
	}

	@Then("^user should be able to view following grid columns under tag history section$")
	public void user_should_be_able_to_view_following_grid_columns_under_tag_history_section(DataTable gridHeaders) {
		List<String> expectedListOfGridColumns = gridHeaders.asList(String.class);
		listOfTagHeadersOnUI = taggingPage.getlistOfTagHeadersUnderHistorySection();
		Assert.assertTrue("All the grid columns are not visible",
				expectedListOfGridColumns.containsAll(listOfTagHeadersOnUI) && !listOfTagHeadersOnUI.isEmpty());
	}

	@Then("^user should be able to view added Tag Details in the grid$")
	public void user_should_be_able_to_view_added_Tag_Details_in_the_grid() {
		Assert.assertTrue("User is not able to view added tag Category in the grid",
				taggingPage.getlistOfAddedTagDetailsUnderHistorySection().contains(selectedTagCategory));
		Assert.assertTrue("User is not able to view added tag Name in the grid",
				taggingPage.getlistOfAddedTagDetailsUnderHistorySection().contains(selectedTagName));
	}

	@When("^user run the query and fetch the Invoice Number to add tag\"([^\"]*)\"$")
	public void user_run_the_query_and_fetch_the_Invoice_Number_to_add_tag(String queryName) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				commonMethods.loadQuery(queryName, dbFileName));
		try {
			while (DatabaseConn.resultSet.next()) {
				dbInvoiceNumber = DatabaseConn.resultSet.getString("InvoiceNumber");
				dbInvoiceId = DatabaseConn.resultSet.getString("InvoiceId");
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue("Invoice number is not fetched from DB.\nThe Technical Error is:\n" + sQLException,
					false);
		}
		financialInfoSteps.log("Fetched Invoice Number from Database is " + dbInvoiceNumber);
	}

	@When("^user enters the query result in Invoice Number search textbox to add tag on account$")
	public void user_enters_the_query_result_in_Invoice_Number_search_textbox_to_add_tag_on_account() {
		searchPage.enterInvoiceNumber(dbInvoiceNumber);
		searchPage.clickSubmitBtn();
		searchPageSteps.verifyInvoiceNumberWithEqualOperator(dbInvoiceNumber);
	}

	@When("^user run the query to fetch newly added tag in histroy section (.*) query$")
	public void user_run_the_query_to_fetch_newly_added_tag_in_histroy_section(String queryName) throws Exception {
		listOfTagDetailsInDB = taggingSteps.getAddedTagDetailsFromDatabase(queryName, dbInvoiceId);
	}

	@Then("^User should be able to view the newly added Tag in history section in SQL result\\.$")
	public void user_should_be_able_to_view_the_newly_added_Tag_in_history_section_in_SQL_result() {
		Assert.assertTrue("Newly added tag is not updated in the DB",
				taggingPage.getlistOfAddedTagDetailsUnderHistorySection().containsAll(listOfTagDetailsInDB));
	}

	@When("^User Clicks on Edit Tag link$")
	public void user_Clicks_on_Edit_Tag_link() {
		taggingPage.clickEditTagLinkAtAccInfoPge();
	}

	@When("^User edits Tag Category , Tag Name and \"([^\"]*)\" text in notes textbox and click on save changes button$")
	public void user_edits_Tag_Category_Tag_Name_and_text_in_notes_textbox_and_click_on_save_changes_button(
			String editTagNote) {
		listOfEditedTagDetailsOnUi = taggingSteps.editAndSveTagOnAccInfoPge(editTagNote);
		tagAddedSuccessMsg = taggingPage.getaccAddedAlrtMsgAfterEdit();
	}

	@Then("^User should be able to view edited Tag Name under the title Tag$")
	public void user_should_be_able_to_view_edited_Tag_Name_under_the_title_Tag() {
		Assert.assertTrue("Edited Tag name is not displayed under the title tag",
				listOfEditedTagDetailsOnUi.contains(taggingPage.getAddedTagNameOnAccInfo()));
	}

	@Then("^user should be able to view edited Tag Details in the grid$")
	public void user_should_be_able_to_view_edited_Tag_Details_in_the_grid() {
		Assert.assertTrue("User is not able to view Edited tag details in the grid",
				taggingPage.getlistOfAddedTagDetailsUnderHistorySection().containsAll(listOfEditedTagDetailsOnUi));
	}

	@Then("^User should be able to view message as \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message_as(String expectedMsg) {
		Assert.assertTrue("User is not able to view " + expectedMsg,
				taggingPage.getAlertMsgOnTagPopupForUnclassifiedAcc().contains(expectedMsg));
	}

	@Then("^User should be able to view Save Changes button as disabled$")
	public void user_should_be_able_to_view_Save_Changes_button_as_disabled() {
		Assert.assertTrue("Save Changes Button is not disables on add account popup",
				taggingPage.isSaveChangesBtnDisabled());
	}

	@When("^User clicks on Mass Update link$")
	public void user_clicks_on_Mass_Update_link() {
		billingAndFollowUpPage.clickMassUpdateLink();
	}

	@Given("^User is on Mass Update screen$")
	public void user_is_on_Mass_Update_screen() {
		taggingPage.massUpdateScreenShouldBeVisible();
	}

	@When("^user clicks on Radio button Professional under Invoice Account Type$")
	public void user_clicks_on_Radio_button_Professional_under_Invoice_Account_Type() {
		taggingPage.selectProfessionalRadioBtn();
	}

	@When("^user clicks on Radio button Manual Entry under Manual Entry or Upload Document\\?$")
	public void user_clicks_on_Radio_button_Manual_Entry_under_Manual_Entry_or_Upload_Document() {
		taggingPage.selectManualEntryRadioBtn();
	}

	@When("^user enters query result with FacilityCode prefixed under Enter Values textbox$")
	public void user_enters_query_result_with_FacilityCode_prefixed_under_Enter_Values_textbox() {
		taggingPage.enterInvcNumInManualEntryTxtbxOnMUScrn(dbInvoiceNumber);
	}

	@When("^user clicks on Radio button Account Tagging under Mass Tag Update Type$")
	public void user_clicks_on_Radio_button_Account_Tagging_under_Mass_Tag_Update_Type() {
		taggingPage.selectMUReqAccountTaggingRadioBtn();

	}

	@When("^user clicks on Add radio button under Account Tag Update Type$")
	public void user_clicks_on_Add_radio_button_under_Account_Tag_Update_Type() {
		taggingPage.selectAddRadioBtnOnMU();
	}

	@Then("^User should be able to view Account Tag Category dropdown $")
	public void user_should_be_able_to_view_Account_Tag_Category_dropdown() {
		taggingPage.isAddCategoryDrpdwnVisibleOnMU();

	}

	@Then("^User should be able to view Account Tag Name dropdown$")
	public void user_should_be_able_to_view_Account_Tag_Name_dropdown() {
		taggingPage.isAddTagDrpdwnVisibleOnMU();

	}

	@When("^User selects Account Tag Category from Account Tag Category drop down$")
	public void user_selects_Account_Tag_Category_from_Account_Tag_Category_drop_down() {
		taggingPage.selectAccTagCategoryOnMU();
	}

	@When("^Users selects Account Tag Name from Account Tag Name drop down$")
	public void users_selects_Account_Tag_Name_from_Account_Tag_Name_drop_down() {
		taggingPage.selectTagNameOnMU();
	}

	@When("^User enters the Notes \"([^\"]*)\" in notes textbox$")
	public void user_enters_the_Notes_in_notes_textbox(String notes) {
		taggingPage.enterNotesOnMU(notes);
	}

	@When("^User clicks on Submit button$")
	public void user_clicks_on_Submit_button() {
		taggingPage.clickOnSaveBtnOnMU();
	}

	@Then("^User should be able to view the message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_message(String expectedMsg) {
		Assert.assertTrue("User is not able to view success message on screeen",
				taggingPage.getSuccessMsgOnMU().contains(expectedMsg));
	}

	@When("^user clicks on Remove radio button  under Account Tag Update Type$")
	public void user_clicks_on_Remove_radio_button_under_Account_Tag_Update_Type() {
		taggingPage.selectRemoveRadioBtnOnMU();
	}
}