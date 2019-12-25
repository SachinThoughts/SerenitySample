package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FinancialInfoPage extends PageObject {

	@FindBy(id = "patFI")
	private WebElementFacade financialInfoSection;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[1]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> totalBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[2]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> insuranceBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[3]/h3/span/span[1]/h5/span")
	private List<WebElementFacade> patientBalance;

	@FindBy(xpath = "//div[@id='financialAccordNoDetail']/div[position()=4]/div//span[position()=1]/h5/span")
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

	@FindBy(xpath = "//i[@class='fa toggle fa-chevron-right' and @id='patFI']")
	private WebElementFacade financialInfoExpandIcon;

	@FindBy(id = "financialInfoPanel")
	private WebElementFacade financialInfoPanel;

	@FindBy(id = "onloadsearchBtn")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//a/descendant::span[text()='Adjustments']/ancestor::a/i")
	private WebElementFacade adjustmentScrollArrow;

	@FindBy(xpath = "//*[@id='divTotalAdjustments']/table/thead/tr/th")
	private List<WebElementFacade> listOfAdjustmentTableHeaders;

	@FindBy(xpath = "//*[@id='divTotalAdjustments']/table/tbody/tr/td")
	private List<WebElementFacade> listOfAdjustmentTableData;

	@FindBy(xpath = "//*[@id='divTotalAdjustments']/table/tbody/tr[1]")
	private WebElementFacade firstRowOfAdjustmentTable;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div/h3/span/span/h5/span[contains(text(),'Total')]")
	private WebElementFacade totalBalanceColumn;

	@FindBy(id = "lblTotalBalance")
	private WebElementFacade totalBalanceData;

	@FindBy(id = "lblTotalAdjustment")
	private WebElementFacade totalAdjustments;

	@FindBy(xpath = "//*[@id='accordion']//a[@onclick='callChargeDetailsService();return false;']//i")
	private WebElementFacade totalChargesScrollArrow;

	@FindBy(xpath = "//*[@id='divTotalCharges']/table/thead/tr/th")
	private List<WebElementFacade> listOfTotalChargesTableHeaders;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[1]")
	private List<WebElementFacade> serviceDate;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[2]")
	private List<WebElementFacade> chargePostingDate;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[3]")
	private List<WebElementFacade> ubRevCode;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[4]")
	private List<WebElementFacade> revenueCenterCode;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[5]")
	private List<WebElementFacade> cptCode;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[6]")
	private List<WebElementFacade> noOfUnits;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[7]")
	private List<WebElementFacade> totalCharge;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[8]")
	private List<WebElementFacade> chargeItemCode;

	@FindBy(xpath = "//*[@id='divTotalCharges']//tr/td[9]")
	private List<WebElementFacade> chargeDesc;

	@FindBy(id = "lblTotalCharges")
	private WebElementFacade totalChargesValue;

	@FindBy(id = "lblExpectedPaymentInvoice")
	private WebElementFacade expectedPaymentValue;

	@FindBy(id = "lblTotalPatientPayments")
	private WebElementFacade patientPaymentsAmount;

	@FindBy(xpath = "(//*[@id = 'divTotalPatientPaymentsNA']//h5)[2]")
	private WebElementFacade patientPaymentsValue;

	@FindBy(id = "spmTotalPayments")
	private WebElementFacade adjustmentMessage;

	@FindBy(id = "lblUnbilledBalance")
	private WebElementFacade unbilledBalanceAmount;

	public boolean isFinanceInfoHeadersVisible(List<String> expectedHeaders) {
		return getFinInfoHeaderAttributes().containsAll(expectedHeaders);
	}

	/**
	 * Expands financial section if not already expanded
	 */
	public void expandFinancialInfoSectn() {
		if (financialInfoExpandIcon.isVisible()) {
			financialInfoExpandIcon.click();
		}
	}

	public List<String> getFinInfoHeaderAttributes() {
		List<String> financeInfoHeadersVisibleAttributes = new ArrayList<>();
		financeInfoHeadersVisibleAttributes
				.add(totalBalance.get(0).getText().trim().concat(" " + totalBalance.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(insuranceBalance.get(0).getText().trim().concat(" " + insuranceBalance.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(patientBalance.get(0).getText().trim().concat(" " + patientBalance.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(unbilledBalance.get(0).getText().trim().concat(" " + unbilledBalance.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(totalCharges.get(0).getText().trim().concat(" " + totalCharges.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(expectedPayment.get(0).getText().trim().concat(" " + expectedPayment.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(insurancePayments.get(0).getText().trim().concat(" " + insurancePayments.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes
				.add(patientPayments.get(0).getText().trim().concat(" " + patientPayments.get(1).getText().trim()));
		financeInfoHeadersVisibleAttributes.add(adjustments.get(1).getText().trim());

		return financeInfoHeadersVisibleAttributes;
	}

	/**
	 * Scrolls into Financial information section
	 */
	public void scrollIntoFinancialInfoPanel() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(financialInfoSection).build().perform();
	}

	public boolean isFinancialInfoSectionVisible() {
		return financialInfoSection.isVisible();
	}

	public void clickExpandFinancialInfo() {
		if (financialInfoExpandIcon.isVisible()) {
			financialInfoExpandIcon.click();
		}
	}

	public void clickSubmitBtn() {
		submitBtn.click();
	}

	/**
	 * Clicks and expands Adjustments field
	 */
	public void clickAdjustmentScrollArrow() {
		evaluateJavascript("arguments[0].click();", adjustmentScrollArrow);
	}

	public List<String> getAdjustmentTableHeaders() {
		withAction().moveToElement(firstRowOfAdjustmentTable).build().perform();
		List<String> listOfTextValuesOfAdjustmentTableHeaders = new ArrayList<>();
		for (WebElementFacade adjustmentTableHeader : listOfAdjustmentTableHeaders) {
			withAction().moveToElement(adjustmentTableHeader).build().perform();
			listOfTextValuesOfAdjustmentTableHeaders.add(adjustmentTableHeader.getText().trim());
		}
		return listOfTextValuesOfAdjustmentTableHeaders;
	}

	public List<String> getAdjustmentTableData() {
		List<String> listOfTextValuesOfAdjustmentTableData = new ArrayList<>();
		for (WebElementFacade adjustmentTableData : listOfAdjustmentTableData) {
			withAction().moveToElement(adjustmentTableData).build().perform();
			String adjustmentData = adjustmentTableData.getText().trim();
			if (adjustmentData.contains("($")) {
				adjustmentData = adjustmentData.replace("($", "-").replace(")", "").replace(",", "");
			} else if (adjustmentData.contains("$")) {
				adjustmentData = adjustmentData.replace("$", "").replace(",", "");
			}
			if (!adjustmentData.equals("null"))
				listOfTextValuesOfAdjustmentTableData.add(adjustmentData);
		}
		return listOfTextValuesOfAdjustmentTableData;
	}

	public void scrollToTotalBalance() {
		withAction().moveToElement(totalBalanceColumn).build().perform();
	}

	public String getTotalBalanceData() {
		return totalBalanceData.getText().trim().replace("$", "").replace(",", "").replace("(", "-").replace(")", "");
	}

	/**
	 * @return Total Adjustments value
	 */
	public String getTotalAdjustments() {
		return totalAdjustments.getText();
	}

	public void expandTotalCharges() {
		withAction().moveToElement(totalChargesScrollArrow).build().perform();
		evaluateJavascript("arguments[0].click();", totalChargesScrollArrow);
	}

	public List<String> getTotalChargesTableHeaders() {
		List<String> listOfTextValuesOfTotalChargesTableHeaders = new ArrayList<>();
		for (WebElementFacade totalChargesTableHeader : listOfTotalChargesTableHeaders) {
			withAction().moveToElement(totalChargesTableHeader).build().perform();
			listOfTextValuesOfTotalChargesTableHeaders.add(totalChargesTableHeader.getText().trim());
		}
		return listOfTextValuesOfTotalChargesTableHeaders;
	}

	public List<String> getServiceDateList() {
		List<String> dateService = new ArrayList<>();
		for (WebElementFacade element : serviceDate) {
			dateService.add(element.getText());
		}
		return dateService;
	}

	public List<String> getPostingDateList() {
		List<String> datePosting = new ArrayList<>();
		for (WebElementFacade element : chargePostingDate) {
			datePosting.add(element.getText());
		}
		return datePosting;
	}

	public List<String> getUBRevCodeList() {
		List<String> code = new ArrayList<>();
		for (WebElementFacade element : ubRevCode) {
			code.add(element.getText());
		}
		return code;
	}

	public List<String> getRevenueCodeList() {
		List<String> revenueCodes = new ArrayList<>();
		for (WebElementFacade element : revenueCenterCode) {
			revenueCodes.add(element.getText());
		}
		return revenueCodes;
	}

	public List<String> getCptCodeList() {
		List<String> cptCodes = new ArrayList<>();
		for (WebElementFacade element : cptCode) {
			cptCodes.add(element.getText());
		}
		return cptCodes;
	}

	public List<String> getNoOfUnitsList() {
		List<String> units = new ArrayList<>();
		for (WebElementFacade element : noOfUnits) {
			units.add(element.getText());
		}
		return units;
	}

	public List<String> getTotalChargesInDetailsList() {
		List<String> charges = new ArrayList<>();
		for (WebElementFacade element : totalCharge) {
			charges.add(element.getText());
		}
		return charges;
	}

	public List<String> getChargeItemCodeList() {
		List<String> itemCodes = new ArrayList<>();
		for (WebElementFacade element : chargeItemCode) {
			itemCodes.add(element.getText());
		}
		return itemCodes;
	}

	public List<String> getChargeDescList() {
		List<String> descCharge = new ArrayList<>();
		int size = chargeDesc.size();
		for (int i = 1; i <= size; i++) {
			String jsPathValue = "#divTotalCharges > table > tbody > tr:nth-child(" + i + ") > td:nth-child(9)";
			String content = evaluateJavascript("return document.querySelector(\"" + jsPathValue + "\").textContent")
					.toString();
			descCharge.add(content);
		}
		return descCharge;
	}

	public String getTotalCharges() {
		return totalChargesValue.getText();
	}

	public Object getExpectedPayment() {
		return expectedPaymentValue.getText();
	}

	/**
	 * @return Patient payment amount is fetched
	 */
	public String getPatientPaymentAmount() {
		withAction().moveToElement(patientPaymentsAmount).build().perform();
		return patientPaymentsAmount.getText().trim();
	}

	/**
	 * @return Patient payment NA value is fetched
	 */
	public String getPatientPaymentValue() {
		withAction().moveToElement(patientPaymentsValue).build().perform();
		return patientPaymentsValue.getText().trim();
	}

	/**
	 * @return Adjustment message under Adjustments field
	 */
	public String getAdjustmentMessage() {
		withAction().moveToElement(adjustmentMessage).build().perform();
		return adjustmentMessage.getText();
	}

	/**
	 * @return Unbilled balance amount is fetched
	 */
	public String getUnbilledBalance() {
		withAction().moveToElement(unbilledBalanceAmount).build().perform();
		return unbilledBalanceAmount.getText();
	}
}
