package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.CallPayerQueuePage;
import r1.prcmbe.pages.SearchPage;

public class CallPayerQueueSteps {

	CallPayerQueuePage callPayerQueuePage;
	SearchPage searchPage;
	AccountInformationPage accInfoPage;

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
		return removedInvoice.equals(searchPage.getInvoiceID());
	}

	/**
	 * Checks if invoice count is increased by 1
	 * 
	 * @param existingCount
	 *            This parameter is used to pass current invoice count
	 * @param newCount
	 *            This parameter is used to pass expected invoice count
	 * @return boolean This returns true or false depending on element visibility
	 */
	@Step
	public boolean isCountIncrementedByOne(String existingCount, String newCount) {
		return Integer.parseInt(newCount) == Integer.parseInt(existingCount) + 1;
	}

	/**
	 * Checks if invoice number is visible more than once in Call payer queue
	 * 
	 * @param invoiceNumber
	 *            This parameter is used to pass invoice number
	 * @return boolean This returns true or false depending on element visibility
	 */
	@Step
	public boolean isAccountVisibleMoreThanOnceInCallPayerQueue(String invoiceNumber) {
		int visiblityCount = 0;
		List<String> cPQInvoiceNumberList = callPayerQueuePage.getCallPayerQueueInvoiceList();
		for (String invoiceNumFromList : cPQInvoiceNumberList) {
			if (invoiceNumFromList.contains(invoiceNumber))
				visiblityCount++;
		}
		return visiblityCount > 1;
	}

	/**
	 * This method is used to check invoice number present in the call payer queue
	 * 
	 * @param invoiceNumber
	 *            the invoice number to be search in the List
	 * @return value 'true' if the invoice number is present in the list else return
	 *         'false'
	 */
	@Step
	public boolean isAccountVisibleInCallPayerQueue(String invoiceNumber) {
		for (String invoiceNum : callPayerQueuePage.getListOfInvoiceNumberCPQ()) {
			if (invoiceNum.contains(invoiceNumber))
				return true;
		}
		return false;
	}

	/**
	 * Remove search invoice from CPQ
	 * 
	 * @param dbInvoiceNumber
	 *            This parameter is used to pass invoice number
	 */
	@Step
	public void removeInvoiceFromCPQ(String dbInvoiceNumber) {
		accInfoPage.closeInfoMessage();
		callPayerQueuePage.clickToggleCallQueueBtn();
		List<String> invoiceNumber = callPayerQueuePage.getListOfAccountsInCallPayorQueue();
		int size = invoiceNumber.size();
		int index = 0;
		for (index = 0; index < size; index++) {
			if (invoiceNumber.get(index).equals(dbInvoiceNumber)) {
				callPayerQueuePage.removeInvoiceFromCPQ(index);
				break;
			}
		}
	}
}
