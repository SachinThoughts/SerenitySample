package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.SearchPage;

public class CallPayerQueueSteps {

	CallPayerQueuePage callPayorQueuePage;
	SearchPage searchPage;

	/**
	 * Checks if removed invoice number from call queue is visible
	 * 
	 * @param removedInvoice
	 * @return boolean This returns true or false depending on element visibility
	 */
	@Step
	public boolean isRemovedAccountFromCallQueueVisible(String removedInvoice) {
		searchPage.enterInvoiceNumber(removedInvoice);
		searchPage.clickSubmitBtn();
		return removedInvoice.equals(searchPage.getInvoiceNumber());
	}

	/**
	 * Checks if invoice count is increased by 1
	 * 
	 * @param existingCount This parameter is used to pass current invoice count
	 * @param newCount      This parameter is used to pass expected invoice count
	 * @return boolean This returns true or false depending on element visibility
	 */
	@Step
	public boolean isCountIncrementedByOne(String existingCount, String newCount) {
		return Integer.parseInt(newCount) == Integer.parseInt(existingCount) + 1;
	}

	/**
	 * Checks if invoice number is visible more than once in Call payer queue
	 * 
	 * @param invoiceNumber This parameter is used to pass invoice number
	 * @return boolean This returns true or false depending on element visibility
	 */
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
