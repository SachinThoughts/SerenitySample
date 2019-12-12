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

	@Step
	public void enterSearchByValue(List<String> searchByValues) {
		switch (eparsProHandoffPage.getSearchByDropdownValue()) {
		case "Visit Number":
			eparsProHandoffPage.enterVisitNumberTextBox(searchByValues.get(0));
			break;

		case "Invoice Number":
			eparsProHandoffPage.enterInvoiceNumberTextBox(searchByValues.get(0));
			break;

		case "Social Security Number":
			eparsProHandoffPage.enterSSNTextBox(searchByValues.get(0));
			break;

		case "Last/First Name":
			eparsProHandoffPage.enterLastNameTextBox(searchByValues.get(0));
			eparsProHandoffPage.enterFirstNameTextBox(searchByValues.get(1));
			break;

		case "Medical Records Number":
			eparsProHandoffPage.enterMRNTextBox(searchByValues.get(0));
			break;

		case "Claim Number":
			eparsProHandoffPage.enterClaimNumberTextBox(searchByValues.get(0));
			break;

		case "DOB":
			eparsProHandoffPage.enterDateOfBirthTextBox(searchByValues.get(0));
			break;

		case "Date of Service":
			eparsProHandoffPage.enterDateOfServiceTextBox(searchByValues.get(0));
			break;
		}
	}
}