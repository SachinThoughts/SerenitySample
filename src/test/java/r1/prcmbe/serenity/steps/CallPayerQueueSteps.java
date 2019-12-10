package r1.prcmbe.serenity.steps;

import java.util.List;

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

	@Step
	public boolean isCountIncrementedByOne(String existingCount, String newCount) {
		return Integer.parseInt(newCount) == Integer.parseInt(existingCount) + 1;
	}

	@Step
	public boolean isAccountVisibleMoreThanOnceInCallPayerQueue(String invoiceNumber) {
		int visiblityCount = 0;
		List<String> cPQInvoiceNumberList = callPayorQueuePage.getCallPayerQueueInvoiceList();
		for (String invoiceNumFromList : cPQInvoiceNumberList) {
			if (invoiceNumFromList.contains(invoiceNumber))
				visiblityCount++;
		}
		return visiblityCount > 1;
	}
}
