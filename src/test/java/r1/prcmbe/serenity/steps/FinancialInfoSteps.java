package r1.prcmbe.serenity.steps;

import java.text.DecimalFormat;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.FinancialInfoPage;


public class FinancialInfoSteps extends PageObject {

	FinancialInfoPage financialInfoPage;
	SearchPage searchPage;

	@Step
	public void log(String message) {
	}

	@Step
	public void searchInvoiceNumber(String invoiceNumber) {
		searchPage.enterInvoiceNumber(invoiceNumber);
		searchPage.clickSubmitBtn();
		waitForAngularRequestsToFinish();
	}
	
	@Step
	public String formatCurrency(String value) {
		final String PATTERN = "$#,##0.00;($#,##0.00)";
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		decimalFormat.applyPattern(PATTERN);
		return decimalFormat.format(Float.parseFloat(value)).toString();
	}
}
