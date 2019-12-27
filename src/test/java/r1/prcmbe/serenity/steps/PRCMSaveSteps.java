package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.AccountInformationPage;

public class PRCMSaveSteps {

	AccountInformationPage acctInformationPage;

	/**
	 * This method verify whether Attribute Value from Database represents URL
	 * @return boolean value based on verification
	 */
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