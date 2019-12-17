package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.AccountInformationPage;

public class PRCMSaveSteps {

	AccountInformationPage acctInformationPage;

	@Step
	public boolean verifyAttributeValueFromDbAsUrl(List<String> listOfAttributeVal) {
		for (String attributeVal : listOfAttributeVal) {
			if (acctInformationPage.getCurrentApplicationUrl().contains(attributeVal)) {
				return true;
			}
		}
		return false;
	}
}