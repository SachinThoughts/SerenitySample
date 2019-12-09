package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.EparsProHandoffPage;

public class EparsProHandoffSteps {
	EparsProHandoffPage eparsProHandoffPage;

	@Step
	public void enterOperatorAndSearchByTextBox(String operatorValue, String textBoxValue) {
		switch (eparsProHandoffPage.getSearchByDropdownValue()) {
		case "Visit Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterVisitNumberTextBox(textBoxValue);
			break;

		case "Invoice Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterInvoiceNumberTextBox(textBoxValue);
			break;

		case "Social Security Number":
			eparsProHandoffPage.enterSSNTextBox(textBoxValue);
			break;

		case "Last/First Name":
			eparsProHandoffPage.enterLastNameTextBox(textBoxValue);
			break;

		case "Medical Records Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterMRNTextBox(textBoxValue);
			break;

		case "Claim Number":
			eparsProHandoffPage.selectOperator(operatorValue);
			eparsProHandoffPage.enterClaimNumberTextBox(textBoxValue);
			break;

		case "DOB":
			eparsProHandoffPage.enterDateOfBirthTextBox(textBoxValue);
			break;

		case "Date of Service":
			eparsProHandoffPage.enterDateOfServiceTextBox(textBoxValue);
			break;
		}
	}

	@Step
	public boolean verifyValidationMessages(List<String> listOfValidationMsgs, String expValidationMsg) {
		for (String validationMsg : listOfValidationMsgs) {
			if (!validationMsg.equals(expValidationMsg))
				return false;
		}
		return true;
	}
}