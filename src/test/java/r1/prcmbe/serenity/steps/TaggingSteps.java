package r1.prcmbe.serenity.steps;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.TaggingPage;

public class TaggingSteps {
	String categoryName;
	TaggingPage taggingPage;
	

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
		if(!taggingPage.getlistOfTagName().isEmpty()) {
			//List<String> tagNameOnUi=taggingPage.getlistOfTagName();
			String newTagName = tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
			while(taggingPage.getlistOfTagName().contains(newTagName)) {
				newTagName = tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
			}
			System.out.println("Inside If " +newTagName);
			return newTagName;
		}
		System.out.println("Outside If " +tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim())));
			return  tagName.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
	}
}
