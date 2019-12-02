package r1.prcmbe.serenity.steps;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import net.thucydides.core.annotations.Step;

public class TaggingSteps {
	String categoryName;

	@Step
	public String getEditedCategoryName(String value, List<String> categoryNameList) {
		categoryName = value.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
		while (categoryNameList.contains(categoryName)) {
			categoryName = value.concat((" " + RandomStringUtils.randomAlphabetic(3).trim()));
		}
		return categoryName;
	}

}
