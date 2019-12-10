package r1.prcmbe.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FinancialInfoPage extends PageObject {

	@FindBy(xpath = "//*[@id='financialInfoPanel']/div[1]/h3/i")
	private WebElementFacade financialInfoSection;

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

	@FindBy(xpath = "//i[@class='fa toggle fa-chevron-right' and @id='patFI']")
	private WebElementFacade financialInfoExpandIcon;

	@FindBy(id = "financialInfoPanel")
	private WebElementFacade financialInfoPanel;

	@FindBy(id = "onloadsearchBtn")
	private WebElementFacade submitBtn;

	@FindBy(xpath = "//*[@id='accordion']//a[@onclick='callServiceTotalAdjustmentsPayments();return false;']//i")
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
	private WebElementFacade patientPaymentsValue;

	@FindBy(xpath = "(//*[@id = 'divTotalInsurancePaymentsNA']//h5)[2]")
	private WebElementFacade insurancePaymentAmount;

	@FindBy(xpath = "(//*[@id = 'divTotalPatientPaymentsNA']//h5)[2]")
	private WebElementFacade patientPaymentsAmount;

	@FindBy(xpath = "//*[@id='lblTotalAdjustment']/ancestor::a//i")
	private WebElementFacade expandIconAdjustments;

	@FindBy(id = "spmTotalPayments")
	private WebElementFacade adjustmentMessage;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[1]")
	private List<WebElementFacade> paymentCodeList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[2]")
	private List<WebElementFacade> paymentDescList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[3]")
	private List<WebElementFacade> payorPlanCodeList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[4]")
	private List<WebElementFacade> payorPlanNameList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[6]")
	private List<WebElementFacade> dateTransactionList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[7]")
	private List<WebElementFacade> typeTransactionList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[8]")
	private List<WebElementFacade> amountList;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//tr//td[9]")
	private List<WebElementFacade> glCodeList;

	@FindBy(xpath = "//*[@id='divTotalInsurancePayments']//a/i")
	private WebElementFacade expandIconInsurancePayments;

	@FindBy(xpath = "//*[@id='divTotalInsurance']//th")
	private List<WebElementFacade> insurancePaymentHeadersList;

	@FindBy(id = "lblUnbilledBalance")
	private WebElementFacade unbilledBalanceAmount;

	public boolean isFinanceInfoHeadersVisible(List<String> expectedHeaders) {
		return getFinInfoHeaderAttributes().containsAll(expectedHeaders);
	}

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

	public String getPatientPaymentText() {
		withAction().moveToElement(patientPaymentsValue).build().perform();
		return patientPaymentsValue.getText().trim();
	}

	public String getInsurancePaymentAmount() {
		withAction().moveToElement(insurancePaymentAmount).build().perform();
		return insurancePaymentAmount.getText();
	}

	public String getPatientPaymentAmount() {
		withAction().moveToElement(patientPaymentsAmount).build().perform();
		return patientPaymentsAmount.getText().trim();
	}

	public void clickExpandIconAdjustments() {
		withAction().moveToElement(expandIconAdjustments).build().perform();
		evaluateJavascript("arguments[0].click();", expandIconAdjustments);
	}

	public String getAdjustmentMessage() {
		withAction().moveToElement(adjustmentMessage).build().perform();
		return adjustmentMessage.getText();
	}

	public List<String> getPaymentCodeList() {
		List<String> paymentCodes = new ArrayList<String>();
		for (WebElementFacade element : paymentCodeList) {
			paymentCodes.add(element.getText());
		}
		return paymentCodes;
	}

	public List<String> getPaymentDescList() {
		List<String> descPayment = new ArrayList<>();
		int size = paymentDescList.size();
		for (int i = 1; i <= size; i++) {
			String jsPathValue = "#divTotalInsurance > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2)";
			String content = evaluateJavascript("return document.querySelector(\"" + jsPathValue + "\").textContent")
					.toString();
			descPayment.add(content);
		}
		return descPayment;
	}

	public List<String> getPayorPlanCodeList() {
		List<String> payorPlanCodes = new ArrayList<String>();
		for (WebElementFacade element : payorPlanCodeList) {
			payorPlanCodes.add(element.getText());
		}
		return payorPlanCodes;
	}

	public List<String> getPayorPlanNameList() {
		List<String> payorPlanNames = new ArrayList<String>();
		for (WebElementFacade element : payorPlanNameList) {
			payorPlanNames.add(element.getText());
		}
		return payorPlanNames;
	}

	public List<String> getDateTransactionList() {
		List<String> transactionDates = new ArrayList<String>();
		for (WebElementFacade element : dateTransactionList) {
			transactionDates.add(element.getText());
		}
		return transactionDates;
	}

	public List<String> getTypeTransactionList() {
		List<String> transactionTypes = new ArrayList<String>();
		for (WebElementFacade element : typeTransactionList) {
			transactionTypes.add(element.getText());
		}
		return transactionTypes;
	}

	public List<String> getAmountList() {
		List<String> amounts = new ArrayList<String>();
		for (WebElementFacade element : amountList) {
			amounts.add(element.getText());
		}
		return amounts;
	}

	public List<String> getGlCodeList() {
		List<String> glCodes = new ArrayList<String>();
		for (WebElementFacade element : glCodeList) {
			glCodes.add(element.getText());
		}
		return glCodes;
	}

	public void clickExpandIconInsurancePayments() {
		expandIconInsurancePayments.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
		withAction().moveToElement(expandIconInsurancePayments).build().perform();
		evaluateJavascript("arguments[0].click();", expandIconInsurancePayments);
	}

	public List<String> getInsurancePaymentHeaders() {
		List<String> headers = new ArrayList<String>();
		for (WebElementFacade element : insurancePaymentHeadersList) {
			withAction().moveToElement(element).build().perform();
			headers.add(element.getText());
		}
		return headers;
	}

	public String getUnbilledBalance() {
		withAction().moveToElement(unbilledBalanceAmount).build().perform();
		return unbilledBalanceAmount.getText();
	}
}
