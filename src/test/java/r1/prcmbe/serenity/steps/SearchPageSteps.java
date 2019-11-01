package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;

public class SearchPageSteps {

	SearchPage searchPage;

	@Step
	public boolean verifyInvoiceIDWithLikeOperator(String dBInvoiceId) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceID : searchPage.getlistOfInvoiceID()) {
				if (!invoiceID.toLowerCase().contains(dBInvoiceId.toLowerCase())) {
					return false;
				}
			}
			searchPage.clickSearchInvoiceID();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dBInvoiceId.contains(searchPage.getInvoiceID().toLowerCase());
	}
}
