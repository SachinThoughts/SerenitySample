package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.SearchPage;

public class CallPayerQueueSteps {

	CallPayerQueuePage callPayorQueuePage;
	SearchPage searchPage;

	@Step
	public boolean isRemovedAccountFromCallQueueVisible(String removedInvoice) {
		searchPage.enterInvoiceNumber(removedInvoice);
		searchPage.clickSubmitBtn();
		return removedInvoice.equals(searchPage.getInvoiceID());
	}

}
