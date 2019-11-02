package r1.prcmbe.serenity.steps;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;
import r1.prcmbe.pages.FinancialInfoPage;
import r1.commons.databaseconnection.DatabaseConn;

public class FinancialInfoSteps extends PageObject {

	FinancialInfoPage financialInfoPage;
	SearchPage searchPage;

	@Step
	public void log(String message) {
	}

	public boolean isFinInfoHeaderAttributesVisible(List<String> expectedHeaders) {
		if (financialInfoPage.getFinInfoHeaderAttributes().containsAll(expectedHeaders)) {
			return true;
		}
		return false;
	}

	@Step
	public void searchInvoiceNumber(String invoiceNumber) {

		searchPage.enterInvoiceNumber(invoiceNumber);
		searchPage.clickSubmitBtn();
		waitForAngularRequestsToFinish();
	}

}
