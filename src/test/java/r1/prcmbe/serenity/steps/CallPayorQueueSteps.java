package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.CallPayorQueuePage;
import r1.prcmbe.pages.SearchPage;

public class CallPayorQueueSteps {

	CallPayorQueuePage callPayorQueuePage;
	SearchPage searchPage;

	@Step
	public boolean isRemovedAccountFromCallQueueVisible(String removedInvoice) {
		searchPage.enterInvoiceNumber(removedInvoice);
		searchPage.clickSubmitBtn();
		if (removedInvoice.equals(searchPage.getInvoiceID())) {
			return true;
		} else {
			return false;
		}
	}

}
