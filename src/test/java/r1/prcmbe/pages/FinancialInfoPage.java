package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FinancialInfoPage extends PageObject {

	/** Page Locators **/

	@FindBy(xpath = "//*[@id='financialInfoPanel']/div[1]/h3/i")
	public WebElementFacade financialInfoSection;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[1]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> totalBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[2]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> insuranceBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[3]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> patientBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[4]/div/h3/span/span[1]/h5/span")
	private List<WebElementFacade> unbilledBalance;

	@FindBy(xpath = "//*[@id='accordion']/div[1]/h3/a/span[1]/h5/span")
	private List<WebElementFacade> totalCharges;

	@FindBy(xpath = "//*[@id='divInvoice']/h3/a/span[1]/h5/span")
	private List<WebElementFacade> expectedPayment;

	@FindBy(xpath = "//*[@id='divTotalInsurancePaymentsNA']/h3/span/span[1]/h5/span")
	private List<WebElementFacade> insurancePayments;

	@FindBy(xpath = "//*[@id='divTotalPatientPaymentsNA']/h3/span/span[1]/h5/span")
	private List<WebElementFacade> patientPayments;

	@FindBy(xpath = "//*[@id='accordion']/div[5]/h3/a/span[1]/h5/span")
	private List<WebElementFacade> adjustments;

	@FindBy(xpath = "//*[text()='Submit']")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//i[@class='fa toggle fa-chevron-right' and @id='patFI']")
	private WebElementFacade financialInfoExpandIcon;

	public WebElementFacade getFinancialInfoSection() {
		return financialInfoSection.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
	}

	public void expandFinancialInfoSectn() {
		if (financialInfoExpandIcon.isVisible()) {
			financialInfoExpandIcon.click();
		}
	}

	public List<String> getFinInfoHeaderAttributes() {
		List<String> actualFinInfoHeadersAtrributes = new ArrayList<String>();
		actualFinInfoHeadersAtrributes
				.add(totalBalance.get(0).getText().trim().concat(" " + totalBalance.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(insuranceBalance.get(0).getText().trim().concat(" " + insuranceBalance.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(patientBalance.get(0).getText().trim().concat(" " + patientBalance.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(unbilledBalance.get(0).getText().trim().concat(" " + unbilledBalance.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(totalCharges.get(0).getText().trim().concat(" " + totalCharges.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(expectedPayment.get(0).getText().trim().concat(" " + expectedPayment.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(insurancePayments.get(0).getText().trim().concat(" " + insurancePayments.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes
				.add(patientPayments.get(0).getText().trim().concat(" " + patientPayments.get(1).getText().trim()));
		actualFinInfoHeadersAtrributes.add(adjustments.get(1).getText().trim());

		return actualFinInfoHeadersAtrributes;
	}

	public void scrollIntoFinancialInfoPanel() {
		withAction().moveToElement(financialInfoSection.waitUntilVisible()).build().perform();

	}

}
