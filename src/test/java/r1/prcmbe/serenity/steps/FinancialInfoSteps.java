package r1.prcmbe.serenity.steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.FinancialInfoPage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

public class FinancialInfoSteps extends PageObject {

	FinancialInfoPage financialInfoPage;
	SearchPage searchPage;
	DateFormat outputFormat, inputFormat;

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
	public String formatDbDateFieldWithDateTime(String dateFromDB) throws ParseException {
		outputFormat = new SimpleDateFormat("M/dd/yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(dateFromDB);
		return outputFormat.format(date);
	}

	@Step
	public String deleteLastTwoDecPlaces(String amount) {
		return amount.substring(0, amount.length() - 2);
	}
}