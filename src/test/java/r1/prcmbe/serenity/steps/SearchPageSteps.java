package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;

public class SearchPageSteps {

	SearchPage pRCMBESearchPage;

	@Step
	public boolean verifyInvoiceIDWithLikeOperator(String invoiceIDDB) {
		if (pRCMBESearchPage.isSearchAccTableVisible()) {
			for (String invoiceID : pRCMBESearchPage.getlistOfInvoiceID()) {
				if (!invoiceID.toLowerCase().contains(invoiceIDDB.toLowerCase())) {
					return false;
				}
			}
			pRCMBESearchPage.clickSearchInvoiceID();
		}
		return pRCMBESearchPage.isPatientAndVisitHeaderVisible()
				&& invoiceIDDB.contains(pRCMBESearchPage.getInvoiceID().toLowerCase());
	}
}
