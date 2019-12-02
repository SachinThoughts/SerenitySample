package r1.prcmbe.serenity.steps;

import java.text.DecimalFormat;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.SearchPage;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.FinancialInfoPage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

public class FinancialInfoSteps extends PageObject {

	FinancialInfoPage financialInfoPage;
	SearchPage searchPage;
	DateFormat outputFormat, inputFormat;
	int count = 0;
	List<String> paymentCode = new ArrayList<String>();
	List<String> paymentDesc = new ArrayList<String>();
	List<String> payorPlanCode = new ArrayList<String>();
	List<String> payorPlanName = new ArrayList<String>();	
	List<String> dateTransaction = new ArrayList<String>();
	List<String> typeTransaction = new ArrayList<String>();
	List<String> amount = new ArrayList<String>();
	List<Object> glCode = new ArrayList<>();
	List<Object> datePostedFormatted = new ArrayList<>();
	List<Object> dateTransactionFormatted = new ArrayList<>();
	List<Object> glCodeFormatted = new ArrayList<>();
	List<Object> amtFormatted = new ArrayList<>();
	List<Object> listOfVal = new ArrayList<>();
	
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
		outputFormat = new SimpleDateFormat("M/d/yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(dateFromDB);
		return outputFormat.format(date);
	}

	@Step
	public String deleteLastTwoDecPlaces(String amount) {
		return amount.substring(0, amount.length() - 2);
	}

	@Step
	public String formatCurrency(String value) {
		final String PATTERN = "$#,##0.00;($#,##0.00)";
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		decimalFormat.applyPattern(PATTERN);
		return decimalFormat.format(Float.parseFloat(value)).toString();
	}

	@Step
	public List<Object> verifyTotalChargesDbValuesWithUI() throws SQLException, ParseException {
		List<Object> listOfVal = new ArrayList<>();
		List<String> serviceDt = new ArrayList<>();
		List<String> chargePostingDt = new ArrayList<>();
		List<String> ubRevCode = new ArrayList<>();
		List<String> revenueCenterCode = new ArrayList<>();
		List<String> cptCode = new ArrayList<>();
		List<String> noOfUnits = new ArrayList<>();
		List<String> totalCharges = new ArrayList<>();
		List<String> chargeItemCode = new ArrayList<>();
		List<Object> chargeDesc = new ArrayList<>();
		List<String> serviceDateFormatted = new ArrayList<>();
		List<String> chargePostingDateFormatted = new ArrayList<>();
		List<String> totalChargesFormatted = new ArrayList<>();
		List<String> chargeDescFormatted = new ArrayList<>();
		while (DatabaseConn.resultSet.next()) {
			serviceDt.add(DatabaseConn.resultSet.getString("DateOfService").trim());
			chargePostingDt.add(DatabaseConn.resultSet.getString("ChargePostingDate").trim());
			ubRevCode.add(DatabaseConn.resultSet.getString("UB92RevCode").trim());
			revenueCenterCode.add(DatabaseConn.resultSet.getString("RevenueCenterCode").trim());
			cptCode.add(DatabaseConn.resultSet.getString("CPT4HCPCSCode"));
			noOfUnits.add(DatabaseConn.resultSet.getString("NumberOfUnits").trim());
			totalCharges.add(DatabaseConn.resultSet.getString("TotalCharges").trim());
			chargeItemCode.add(DatabaseConn.resultSet.getString("ChargeItemCode").trim());
			chargeDesc.add(DatabaseConn.resultSet.getObject("ChargeDescription"));
			for (String serviceDate : serviceDt) {
				serviceDateFormatted.add(formatDbDateFieldWithDateTime(serviceDate));
			}
			for (String postingDate : chargePostingDt) {
				chargePostingDateFormatted.add(formatDbDateFieldWithDateTime(postingDate));
			}
			for (String charges : totalCharges) {
				totalChargesFormatted.add(formatCurrency(charges));
			}
			for (Object desc : chargeDesc) {
				if (desc == null) {
					chargeDescFormatted.add("");
				} else {
					chargeDescFormatted.add(desc.toString());
				}
			}
			if (serviceDateFormatted.equals(financialInfoPage.getServiceDateList())) {
				count = count + 1;
			} else {
				listOfVal.add("ServiceDate");
			}
			if (chargePostingDateFormatted.equals(financialInfoPage.getPostingDateList())) {
				count = count + 1;
			} else {
				listOfVal.add("ChargePostingdate");
			}
			if (ubRevCode.equals(financialInfoPage.getUBRevCodeList())) {
				count = count + 1;
			} else {
				listOfVal.add("UBRevCode");
			}
			if (revenueCenterCode.equals(financialInfoPage.getRevenueCodeList())) {
				count = count + 1;
			} else {
				listOfVal.add("RevenueCenterCode");
			}
			if (cptCode.equals(financialInfoPage.getCptCodeList())) {
				count = count + 1;
			} else {
				listOfVal.add("CPTCode");
			}
			if (noOfUnits.equals(financialInfoPage.getNoOfUnitsList())) {
				count = count + 1;
			} else {
				listOfVal.add("NoOfUnits");
			}
			if (totalChargesFormatted.equals(financialInfoPage.getTotalChargesInDetailsList())) {
				count = count + 1;
			} else {
				listOfVal.add("TotalCharge");
			}
			if (chargeItemCode.equals(financialInfoPage.getChargeItemCodeList())) {
				count = count + 1;
			} else {
				listOfVal.add("ChargeItemCode");
			}
			if (chargeDescFormatted.equals(financialInfoPage.getChargeDescList())) {
				count = count + 1;
			} else {
				listOfVal.add("ChargeDesc");
			}
			if (count == 9) {
				listOfVal.add(true);
			} else {
				listOfVal.add(false);
			}
		}
		return listOfVal;
	}
	
	@Step
	public List<Object> verifyInsurancePaymentsDbValuesWithUI() throws SQLException, ParseException {
		while (DatabaseConn.resultSet.next()) {
			paymentCode.add(DatabaseConn.resultSet.getString("PaymentCode").trim());
			paymentDesc.add(DatabaseConn.resultSet.getString("Description").trim());
			payorPlanCode.add(DatabaseConn.resultSet.getString("PayorPlanCode").trim());
			payorPlanName.add(DatabaseConn.resultSet.getString("PayorPlanName").trim());			
			dateTransaction.add(DatabaseConn.resultSet.getString("DateOfTransaction").trim());
			typeTransaction.add(DatabaseConn.resultSet.getString("TypeOfTransaction").trim());
			amount.add(DatabaseConn.resultSet.getString("Amount").trim());
			glCode.add(DatabaseConn.resultSet.getObject("GLCode"));
		}
	
		for (String transactionDate : dateTransaction) {			
			dateTransactionFormatted.add(formatDbDateFieldWithDateTime(transactionDate));		
		}

		for (String amt : amount) {
			amtFormatted.add(formatCurrency(amt));
		}

		for (Object code : glCode) {
			if (code == null) {
				glCodeFormatted.add("");
			} else {
				glCodeFormatted.add(code.toString());
			}
		}

		if (financialInfoPage.getPaymentCodeList().equals(paymentCode)) {
			count = count + 1;
		} else {
			listOfVal.add("Payment Code");
		}		
		if (financialInfoPage.getPaymentDescList().equals(paymentDesc)) {
			count = count + 1;
		} else {
			listOfVal.add("Payment desc");
		}

		if (financialInfoPage.getPayorPlanCodeList().equals(payorPlanCode)) {
			count = count + 1;
		} else {
			listOfVal.add("Payor plan code");
		}

		if (financialInfoPage.getPayorPlanNameList().equals(payorPlanName)) {
			count = count + 1;
		} else {
			listOfVal.add("Payor plan name");
		}

		if (financialInfoPage.getDateTransactionList().equals(dateTransactionFormatted)) {
			count = count + 1;
		} else {
			listOfVal.add("date of transaction");
		}

		if (financialInfoPage.getTypeTransactionList().equals(typeTransaction)) {
			count = count + 1;
		} else {
			listOfVal.add("transaction type");
		}

		if (financialInfoPage.getAmountList().equals(amtFormatted)) {
			count = count + 1;
		} else {
			listOfVal.add("Amount");
		}

		if (financialInfoPage.getGlCodeList().equals(glCodeFormatted)) {
			count = count + 1;
		} else {
			listOfVal.add("GL Code");
		}

		if (count == 8) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}
		return listOfVal;
	}
	
}
