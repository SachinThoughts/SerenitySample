package r1.prcmbe.serenity.steps;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.TaggingPage;
import r1.prcmbe.steps.definitions.TaggingStepDef;

public class TaggingSteps {
	private String categoryName, newTagName, editedTagCategory, editedTagName;
	TaggingPage taggingPage;
	CommonMethods commonMethods;
	TaggingStepDef taggingStepDef;
	List<String> getAddedTagHistory = new ArrayList<>();
	List<String> editedTagDetails = new ArrayList<>();
	private static String dbFileName = "Tagging";

	@Step
	public String getNewCategoryName(String value, List<String> categoryNameList) {
		categoryName = value.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
		while (categoryNameList.contains(categoryName)) {
			categoryName = value.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
		}
		return categoryName;
	}

	@Step
	public String getNewTagName(String tagName) {
		if (!taggingPage.getlistOfTagName().isEmpty()) {
			newTagName = tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
			while (taggingPage.getlistOfTagName().contains(newTagName)) {
				newTagName = tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
			}
			return newTagName;
		}
		return tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
	}

	@Step
	public List<String> getAddedTagDetailsFromDatabase(String queryName, String dbInvoiceId) throws Exception {
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName,
				String.format(commonMethods.loadQuery(queryName, dbFileName), dbInvoiceId));
		try {
			while (DatabaseConn.resultSet.next()) {
				getAddedTagHistory.add(DatabaseConn.resultSet.getString("CategoryName"));
				getAddedTagHistory.add(DatabaseConn.resultSet.getString("TagName"));
				String dbCreatedDate = (DatabaseConn.resultSet.getString("CreatedDateTime").substring(0, 10));
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dbCreatedDate);
				getAddedTagHistory.add(new SimpleDateFormat("d MMMM yyyy").format(date));
				getAddedTagHistory.add(DatabaseConn.resultSet.getString("CreatedUserName"));
				Object dbUpdatedDateTime = (DatabaseConn.resultSet.getObject("UpdatedDateTime"));
				if (dbUpdatedDateTime == null) {
					getAddedTagHistory.add("-");
				}
				Object dbUpdatedUserName = (DatabaseConn.resultSet.getObject("UpdatedUserName"));
				if (dbUpdatedUserName == null) {
					getAddedTagHistory.add("-");
				}
			}
		} catch (SQLException sQLException) {
			Assert.assertTrue(
					"All the added tag data is not fetched from DB.\nThe Technical Error is:\n" + sQLException, false);
		}
		return getAddedTagHistory;
	}

	public List<String> editAndSveTagOnAccInfoPge(String editTagNote) {
		editedTagCategory = taggingPage.selectAndGetAnyTagCategory();
		editedTagName = taggingPage.selectAndGetAnyTagName();
		taggingPage.enterTagNote(editTagNote);
		editedTagDetails.add(editedTagCategory);
		editedTagDetails.add(editedTagName);
		taggingPage.clickOnSaveTagBtn();
		if (taggingPage.isAlertMsgOnDuplicateTagVisible()) {
			while (editedTagCategory.equalsIgnoreCase(taggingPage.selectAndGetAnyTagCategory())) {
				editedTagCategory = taggingPage.selectAndGetAnyTagCategory();
			}
			editedTagName = taggingPage.selectAndGetAnyTagName();
			editedTagDetails.add(editedTagCategory);
			editedTagDetails.add(editedTagName);
			taggingPage.enterTagNote(editTagNote);
			taggingPage.clickOnSaveTagBtn();
			return editedTagDetails;
		}
		return editedTagDetails;
	}
}
