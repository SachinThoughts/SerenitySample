package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;

public class SearchPageSteps {

	SearchPage searchPage;
	LoginSteps loginSteps;

	@Step
	public boolean verifyInvoiceIDWithLikeOperator(String dbInvoiceId) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceID : searchPage.getlistOfInvoiceID()) {
				if (!invoiceID.toLowerCase().contains(dbInvoiceId.toLowerCase())) {
					return false;
				}
			}
			searchPage.clickSearchInvoiceID();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbInvoiceId.contains(searchPage.getInvoiceID().toLowerCase());
	}
	
	@Step
	public boolean verifyInvoiceNumberWithEqualOperator(String dbEncounterID) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String encounterID : searchPage.getlistOfAccNum()) {
				if (!encounterID.equalsIgnoreCase(dbEncounterID)) {
					loginSteps.log("The incorrect searched Encounter id is " + encounterID);
					return false;
				}
			}
			searchPage.clickSearchAccountNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbEncounterID.equalsIgnoreCase(searchPage.getAccountNumber());
	}

}
