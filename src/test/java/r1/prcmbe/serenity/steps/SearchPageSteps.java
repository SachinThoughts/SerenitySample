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
	public boolean verifyInvoiceNumberWithEqualOperator(String dbInvoiceNum) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceNum : searchPage.getlistOfInvNum()) {
				if (!invoiceNum.equalsIgnoreCase(dbInvoiceNum)) {
					loginSteps.log("The incorrect searched Invoice Number is " + invoiceNum);
					return false;
				}
			}
			searchPage.clickSearchInvoiceNumber();
		}
		if (searchPage.isErrorMsgVisible()) {
			searchPage.clickErrorMsg();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbInvoiceNum.equalsIgnoreCase(searchPage.getAccountNumber());
	}

}
