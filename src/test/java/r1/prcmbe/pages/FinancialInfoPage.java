package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FinancialInfoPage extends PageObject {

	@FindBy(xpath = "//*[@id='financialInfoPanel']/div[1]/h3/i")
	private WebElementFacade financialInfoSection;

	@FindBy(xpath = "//div[@id='financialAccordNoDetail']/div[1]//span[1]/h5/span")
	private List<WebElementFacade> totalBalance;

	@FindBy(xpath = "//div[@id='financialAccordNoDetail']/div[2]//span[1]/h5/span")
	private List<WebElementFacade> insuranceBalance;

	@FindBy(xpath = "//*[@id='financialAccordNoDetail']/div[3]//span[1]/h5/span")
	private List<WebElementFacade> patientBalance;

	@FindBy(xpath = "//div[@id='financialAccordNoDetail']/div[4]//span[1]/h5/span")
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

	@FindBy(xpath = "//a/descendant::span[text()='Total']/ancestor::a/i")
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

	@FindBy(xpath = "(//*[@id = 'divTotalPatientPaymentsNA']//h5)[2]")
	private WebElementFacade patientPaymentsAmount;

	@FindBy(xpath = "//*[@id='lblTotalAdjustment']/ancestor::a//i")
	private WebElementFacade expandIconAdjustments;

	@FindBy(id = "spmTotalPayments")
	private WebElementFacade adjustmentMessage;

	@FindBy(id = "lblUnbilledBalance")
	private WebElementFacade unbilledBalanceAmount;

	/**
	 * Checks Financial information headers are visible
	 * @param expectedHeaders of financial information
	 * @return true or false depending on header visibility
	 */
	public boolean isFinanceInfoHeadersVisible(List<String> expectedHeaders) {
		return getFinancialInfoHeaders().containsAll(expectedHeaders);
	}

	/**
	 * checks and expands financial information section if not visible
	 */
	public void expandFinancialInfoSectn() {
		if (financialInfoExpandIcon.isVisible()) {
			financialInfoExpandIcon.click();
		}
	}

	/**
	 * Fetches list of Financial Info Headers
	 * @return list of headers
	 */
	public List<String> getFinancialInfoHeaders() {
		List<String> financialInfoHeaders = new ArrayList<>();
		financialInfoHeaders
				.add(totalBalance.get(0).getText().trim().concat(" " + totalBalance.get(1).getText().trim()));
		financialInfoHeaders
				.add(insuranceBalance.get(0).getText().trim().concat(" " + insuranceBalance.get(1).getText().trim()));
		financialInfoHeaders
				.add(patientBalance.get(0).getText().trim().concat(" " + patientBalance.get(1).getText().trim()));
		financialInfoHeaders
				.add(unbilledBalance.get(0).getText().trim().concat(" " + unbilledBalance.get(1).getText().trim()));
		financialInfoHeaders
				.add(totalCharges.get(0).getText().trim().concat(" " + totalCharges.get(1).getText().trim()));
		financialInfoHeaders
				.add(expectedPayment.get(0).getText().trim().concat(" " + expectedPayment.get(1).getText().trim()));
		financialInfoHeaders
				.add(insurancePayments.get(0).getText().trim().concat(" " + insurancePayments.get(1).getText().trim()));
		financialInfoHeaders
				.add(patientPayments.get(0).getText().trim().concat(" " + patientPayments.get(1).getText().trim()));
		financialInfoHeaders.add(adjustments.get(1).getText().trim());

		return financialInfoHeaders;
	}

	/**
	 * Scrolls into financial information section
	 */
	public void scrollIntoFinancialInfoPanel() {
		waitForAngularRequestsToFinish();
		withAction().moveToElement(financialInfoSection).build().perform();
	}

	/**
	 * @return true or false if financial info section is visible
	 */
	public boolean isFinancialInfoSectionVisible() {
		return financialInfoSection.isVisible();
	}

	/**
	 * Clicks and expand Adjustments field
	 */
	public void clickSubmitBtn() {
		submitBtn.click();
	}

	/**
	 * Clicks and expand adjustment section
	 */
	public void clickAdjustmentScrollArrow() {
		evaluateJavascript("arguments[0].click();", adjustmentScrollArrow);
	}

	/**
	 * Fetches Adjustments table headers
	 * @return list of Adjustments table header values
	 */
	public List<String> getAdjustmentTableHeaders() {
		withAction().moveToElement(firstRowOfAdjustmentTable).build().perform();
		List<String> listOfTextValuesOfAdjustmentTableHeaders = new ArrayList<>();
		for (WebElementFacade adjustmentTableHeader : listOfAdjustmentTableHeaders) {
			withAction().moveToElement(adjustmentTableHeader).build().perform();
			listOfTextValuesOfAdjustmentTableHeaders.add(adjustmentTableHeader.getText().trim());
		}
		return listOfTextValuesOfAdjustmentTableHeaders;
	}

	/**
	 * Fetches Adjustments table data in a list
	 * @return list of adjustments table data
	 */
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

	/**
	 * Scroll to total balance section
	 */
	public void scrollToTotalBalance() {
		withAction().moveToElement(totalBalanceColumn).build().perform();
	}

	/**
	 * @return total balance value
	 */
	public String getTotalBalanceData() {
		return totalBalanceData.getText().trim().replace("$", "").replace(",", "").replace("(", "-").replace(")", "");
	}

	/**
	 * @return total adjustments value
	 */
	public String getTotalAdjustments() {
		return totalAdjustments.getText();
	}

	/**
	 * Expands Total charges by clicking on expand icon
	 */
	public void expandTotalCharges() {
		withAction().moveToElement(totalChargesScrollArrow).build().perform();
		evaluateJavascript("arguments[0].click();", totalChargesScrollArrow);
	}

	/**
	 * @return list of total charges table header values
	 */
	public List<String> getTotalChargesTableHeaders() {
		List<String> listOfTextValuesOfTotalChargesTableHeaders = new ArrayList<>();
		for (WebElementFacade totalChargesTableHeader : listOfTotalChargesTableHeaders) {
			withAction().moveToElement(totalChargesTableHeader).build().perform();
			listOfTextValuesOfTotalChargesTableHeaders.add(totalChargesTableHeader.getText().trim());
		}
		return listOfTextValuesOfTotalChargesTableHeaders;
	}

	/**
	 * @return list of Service date under total charges
	 */
	public List<String> getServiceDateList() {
		List<String> dateService = new ArrayList<>();
		for (WebElementFacade element : serviceDate) {
			dateService.add(element.getText());
		}
		return dateService;
	}

	/**
	 * @return list of Posting date under total charges
	 */
	public List<String> getPostingDateList() {
		List<String> datePosting = new ArrayList<>();
		for (WebElementFacade element : chargePostingDate) {
			datePosting.add(element.getText());
		}
		return datePosting;
	}

	/**
	 * @return list of UB Rev code values under total charges
	 */
	public List<String> getUBRevCodeList() {
		List<String> code = new ArrayList<>();
		for (WebElementFacade element : ubRevCode) {
			code.add(element.getText());
		}
		return code;
	}

	/**
	 * @return list of Revenue code values under total charges
	 */
	public List<String> getRevenueCodeList() {
		List<String> revenueCodes = new ArrayList<>();
		for (WebElementFacade element : revenueCenterCode) {
			revenueCodes.add(element.getText());
		}
		return revenueCodes;
	}

	/**
	 * @return list of Cpt code values under total charges
	 */
	public List<String> getCptCodeList() {
		List<String> cptCodes = new ArrayList<>();
		for (WebElementFacade element : cptCode) {
			cptCodes.add(element.getText());
		}
		return cptCodes;
	}

	/**
	 * @return list of Number of units under total charges
	 */
	public List<String> getNoOfUnitsList() {
		List<String> units = new ArrayList<>();
		for (WebElementFacade element : noOfUnits) {
			units.add(element.getText());
		}
		return units;
	}

	/**
	 * @return list of Total charges values
	 */
	public List<String> getTotalChargesInDetailsList() {
		List<String> charges = new ArrayList<>();
		for (WebElementFacade element : totalCharge) {
			charges.add(element.getText());
		}
		return charges;
	}

	/**
	 * @return list of Charge item code values under total charges
	 */
	public List<String> getChargeItemCodeList() {
		List<String> itemCodes = new ArrayList<>();
		for (WebElementFacade element : chargeItemCode) {
			itemCodes.add(element.getText());
		}
		return itemCodes;
	}

	/**
	 * @return list of Charge description under total charges
	 */
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

	/**
	 * @return Total charges value 
	 */
	public String getTotalCharges() {
		return totalChargesValue.getText();
	}

	/**
	 * @return Expected payment value
	 */
	public Object getExpectedPayment() {
		return expectedPaymentValue.getText();
	}

	/**
	 * @return patient payment text
	 */
	public String getPatientPaymentText() {
		withAction().moveToElement(patientPaymentsValue).build().perform();
		return patientPaymentsValue.getText().trim();
	}

	/**
	 * @return patient payment amount value
	 */
	public String getPatientPaymentAmount() {
		withAction().moveToElement(patientPaymentsAmount).build().perform();
		return patientPaymentsAmount.getText().trim();
	}

	/**
	 * expands the Adjustment section
	 */
	public void clickExpandIconAdjustments() {
		withAction().moveToElement(expandIconAdjustments).build().perform();
		evaluateJavascript("arguments[0].click();", expandIconAdjustments);
	}

	/**
	 * @return adjustment message text
	 */
	public String getAdjustmentMessage() {
		withAction().moveToElement(adjustmentMessage).build().perform();
		return adjustmentMessage.getText();
	}

	/**
	 * @return unbilled balance amount
	 */
	public String getUnbilledBalance() {
		withAction().moveToElement(unbilledBalanceAmount).build().perform();
		return unbilledBalanceAmount.getText();
	}
}
