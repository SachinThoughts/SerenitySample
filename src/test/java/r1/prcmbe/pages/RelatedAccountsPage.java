package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class RelatedAccountsPage extends PageObject {

	@FindBy(id = "relatedInstances")
	private WebElementFacade relatedAccountsBtn;

	@FindBy(id = "relatedLabel")
	private WebElementFacade relatedAccountsPopupLbl;

	/*@FindBy(xpath = "//button[text()='Search']")
	private WebElementFacade searchBtn;*/

	@FindBy(xpath = "//*[@id='AccountFirst']/a")
	private List<WebElementFacade> firstBtnList;

	@FindBy(xpath = "//*[@id='AccountPrevious']/a")
	private List<WebElementFacade> previousBtnList;

	@FindBy(xpath = "//*[@id='AccountPageNext']/a")
	private List<WebElementFacade> nextBtnList;

	@FindBy(xpath = "//*[@id='AccountPageLast']/a")
	private List<WebElementFacade> lastBtnList;

	@FindBy(xpath = "//*[@class='jp-current']")
	private WebElementFacade defaultPage;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//th")
	private List<WebElementFacade> relatedAccountPopupHeaderList;

	@FindBy(xpath = "//th[.=' Visit #']/ancestor::tbody/tr/td[count(//tr/th[.=' Visit #']/preceding-sibling::th)+1]")
	private List<WebElementFacade> relatedVisitsList;

	@FindBy(xpath = "//*[@id='AccountDiv']//*[@id='AccountPageNext' and @class='disabled']/a")
	private WebElementFacade nextBtnDisabled;

	@FindBy(xpath = "//*[@id='AccountDiv']//*[@id='AccountPageNext']/a")
	private WebElementFacade nextBtn;

	@FindBy(xpath = "//*[@id='loadingDiv']/i")
	private WebElementFacade paginationSpinner;

	@FindBy(xpath = "//th[.='Invoice #']/ancestor::tbody/tr/td[count(//tr/th[.='Invoice #']/preceding-sibling::th)+1]")
	private List<WebElementFacade> relatedInvoicesList;

	@FindBy(xpath = "//th[.='Admit Date']/ancestor::tbody/tr/td[count(//tr/th[.='Admit Date']/preceding-sibling::th)+1]")
	private List<WebElementFacade> admitDatesList;

	@FindBy(xpath = "//th[.='Discharge Date']/ancestor::tbody/tr/td[count(//tr/th[.='Discharge Date']/preceding-sibling::th)+1]")
	private List<WebElementFacade> dischargeDatesList;

	@FindBy(xpath = "//th[.='Patient Type']/ancestor::tbody/tr/td[count(//tr/th[.='Patient Type']/preceding-sibling::th)+1]")
	private List<WebElementFacade> patientTypesList;

	@FindBy(xpath = "//th[.='Facility Code']/ancestor::tbody/tr/td[count(//tr/th[.='Facility Code']/preceding-sibling::th)+1]")
	private List<WebElementFacade> facilityCodesList;

	@FindBy(xpath = "//th[.='PayerPlan Code']/ancestor::tbody/tr/td[count(//tr/th[.='PayerPlan Code']/preceding-sibling::th)+1]")
	private List<WebElementFacade> payorPlanCodesList;

	@FindBy(xpath = "//th[.='Insurance Balance']/ancestor::tbody/tr/td[count(//tr/th[.='Insurance Balance']/preceding-sibling::th)+1]")
	private List<WebElementFacade> insuranceBalanceList;

	@FindBy(xpath = "//th[.='Patient Balance']/ancestor::tbody/tr/td[count(//tr/th[.='Patient Balance']/preceding-sibling::th)+1]")
	private List<WebElementFacade> patientBalanceList;

	@FindBy(xpath = "//th[.='Defect Type']/ancestor::tbody/tr/td[count(//tr/th[.='Defect Type']/preceding-sibling::th)+1]")
	private List<WebElementFacade> defectTypesList;

	@FindBy(xpath = "//th[.='Defect SubCategory']/ancestor::tbody/tr/td[count(//tr/th[.='Defect SubCategory']/preceding-sibling::th)+1]")
	private List<WebElementFacade> defectSubTypesList;

	/**
	 * clicks on Related Account button
	 */
	public void clickRelatedAccountsBtn() {
		relatedAccountsBtn.click();
	}

	/**
	 * @return the Related Account popup label text
	 */
	public String getRelatedAccPopupLabelTxt() {
		return relatedAccountsPopupLbl.getText();
	}

	/**
	 * @return visibility of Search button on Related Account popup
	 */
/*	public boolean isSearchBtnVisible() {
		return searchBtn.isVisible();
	}*/

	/**
	 * @return visibility of first pagination button on Related Account popup
	 */
	public boolean isFirstBtnVisible() {
		return firstBtnList.size() == 2;
	}

	/**
	 * @return visibility of Previous button on Related Account popup
	 */
	public boolean isPreviousBtnVisible() {
		return previousBtnList.size() == 2;
	}

	/**
	 * @return visibility of Next button on Related Account popup
	 */
	public boolean isNextBtnVisible() {
		return nextBtnList.size() == 2;
	}

	/**
	 * @return visibility of Last button on Related Account popup
	 */
	public boolean isLastBtnVisible() {
		return lastBtnList.size() == 2;
	}

	/**
	 * @return default selected page text
	 */
	public String getDefaultSelectedPage() {
		return defaultPage.getText();
	}

	/**
	 * @return Related Account popup Header list
	 */
	public List<String> getRelatedAcctPopUpHeaderList() {
		List<String> headerList = new ArrayList<>();
		for (WebElementFacade header : relatedAccountPopupHeaderList) {
			headerList.add(header.getText());
		}
		return headerList;
	}

	/**
	 * @return Related accounts count in the Related Account popup
	 */
	public int getRelatedAccountCount() {
		return relatedVisitsList.size();
	}

	/**
	 * @return List of visit numbers
	 */
	public List<String> getAllVisitNumbers() {
		List<String> visitNumbers = new ArrayList<>();
		boolean flag = false;
		do {
			for (WebElementFacade visit : relatedVisitsList) {
				visitNumbers.add(visit.getText().trim());
			}
			flag = nextBtnDisabled.isVisible();
			if (flag == false) {
				nextBtn.click();
				paginationSpinner.waitUntilNotVisible();
			}
		} while (flag == false);
		return visitNumbers;
	}

	/**
	 * @return whether Invoice number is clickable
	 */
	public boolean isInvoiceNumberClickable() {
		if (!relatedInvoicesList.get(1).isClickable()) {
			return relatedVisitsList.get(1).isClickable();
		}
		return relatedInvoicesList.get(1).isClickable();
	}

	/**
	 * @return whether Visit number is hyperlinked for NA Invoice number
	 */
	public boolean isVisitNumberHyperLinkedForNAInvoiceNumber() {
		boolean flag = false;
		do {
			int count = 0;
			for (WebElementFacade invoice : relatedInvoicesList) {
				if (invoice.getText().equals("N/A")) {
					return relatedVisitsList.get(count).isClickable();
				}
				count++;
			}
			flag = nextBtnDisabled.isVisible();
			if (flag == false) {
				nextBtn.click();
				paginationSpinner.waitUntilNotVisible();
			}
		} while (flag == false);
		return false;
	}

	/**
	 * @return List of Visit numbers in current page
	 */
	public List<String> getVisitsListCurrentPage() {
		List<String> visitNumbers = new ArrayList<>();
		for (WebElementFacade visit : relatedVisitsList) {
			visitNumbers.add(visit.getText().trim());
		}
		return visitNumbers;
	}

	/**
	 * @return List of Invoice numbers in Current page
	 */
	public List<String> getInvoicesListCurrentPage() {
		List<String> invoiceNumbers = new ArrayList<>();
		for (WebElementFacade invoice : relatedInvoicesList) {
			invoiceNumbers.add(invoice.getText().trim());
		}
		return invoiceNumbers;
	}

	/**
	 * @return List of Admit dates in Current page
	 */
	public List<String> getAdmitDatesListCurrentPage() {
		List<String> admitDates = new ArrayList<>();
		for (WebElementFacade date : admitDatesList) {
			admitDates.add(date.getText().trim());
		}
		return admitDates;
	}

	/**
	 * @return List of Discharge Dates in Current page
	 */
	public List<String> getDischargeDatesListCurrentPage() {
		List<String> dischargeDates = new ArrayList<>();
		for (WebElementFacade date : dischargeDatesList) {
			dischargeDates.add(date.getText().trim());
		}
		return dischargeDates;
	}

	/**
	 * @return List of Patient types in current page
	 */
	public List<String> getPatientTypesListCurrentPage() {
		List<String> patientTypes = new ArrayList<>();
		for (WebElementFacade patientType : patientTypesList) {
			patientTypes.add(patientType.getText().trim());
		}
		return patientTypes;
	}

	/**
	 * @return List of Facility Codes in Current page
	 */
	public List<String> getFacilityCodesListCurrentPage() {
		List<String> facilityCodes = new ArrayList<>();
		for (WebElementFacade facilityCode : facilityCodesList) {
			facilityCodes.add(facilityCode.getText().trim());
		}
		return facilityCodes;
	}

	/**
	 * @return List of Payor plan codes in Current page
	 */
	public List<String> getPayorPlanCodesListCurrentPage() {
		List<String> payorPlanCodes = new ArrayList<>();
		for (WebElementFacade planCode : payorPlanCodesList) {
			payorPlanCodes.add(planCode.getText().trim());
		}
		return payorPlanCodes;
	}

	/**
	 * @return List of Insurance balance in Current page
	 */
	public List<String> getInsuranceBalanceListCurrentPage() {
		List<String> insuranceBalances = new ArrayList<>();
		for (WebElementFacade insuranceBalance : insuranceBalanceList) {
			insuranceBalances.add(insuranceBalance.getText().trim());
		}
		return insuranceBalances;
	}

	/**
	 * @return List of Patient balance in Current page
	 */
	public List<String> getPatientBalanceListCurrentPage() {
		List<String> patientBalances = new ArrayList<>();
		for (WebElementFacade patientBalance : patientBalanceList) {
			patientBalances.add(patientBalance.getText().trim());
		}
		return patientBalances;
	}

	/**
	 * @return List of Defect types in Current page
	 */
	public List<String> getDefectTypesListCurrentPage() {
		List<String> defectTypes = new ArrayList<>();
		for (WebElementFacade defectType : defectTypesList) {
			defectTypes.add(defectType.getText().trim());
		}
		return defectTypes;
	}

	/**
	 * @return List of defect subtypes in current page               
	 */
	public List<String> getDefectSubTypesListCurrentPage() {
		List<String> defectSubTypes = new ArrayList<>();
		for (WebElementFacade defectSubType : defectSubTypesList) {
			defectSubTypes.add(defectSubType.getText().trim());
		}
		return defectSubTypes;
	}

	/**
	 * Clicks on first Invoice number
	 * @return Invoice number which is clicked
	 */
	public String clickAndGetInvoiceNumber() {
		String invoiceNumber = relatedInvoicesList.get(1).getText();
		relatedInvoicesList.get(1).click();
		return invoiceNumber;
	}

	/**
	 * @return list of Invoice numbers in Related Account popup
	 */
	public List<String> getAllInvoiceNumbers() {
		List<String> invoiceNumbers = new ArrayList<>();
		boolean flag = false;
		do {
			for (WebElementFacade invoice : relatedInvoicesList) {
				invoiceNumbers.add(invoice.getText().trim());
			}
			flag = nextBtnDisabled.isVisible();
			if (flag == false) {
				nextBtn.click();
				paginationSpinner.waitUntilNotVisible();
			}
		} while (flag == false);
		return invoiceNumbers;
	}

	/**
	 * @return Visit number where Invoice Number is NA on Related Account popup
	 */
	public String clickAndGetVisitNumberHyperLinkedForNAInvoiceNumber() {
		waitForAngularRequestsToFinish();
		boolean flag = false;
		String clickedVisitNumber = "";
		do {
			int count = 0;
			for (WebElementFacade invoice : relatedInvoicesList) {
				if (invoice.getText().equals("N/A")) {
					clickedVisitNumber = relatedVisitsList.get(count).getText();
					relatedVisitsList.get(count).click();
					return clickedVisitNumber;
				}
				count++;
			}
			flag = nextBtnDisabled.isVisible();
			if (flag == false) {
				nextBtn.click();
				paginationSpinner.waitUntilNotVisible();
			}
		} while (flag == false);
		return null;
	}
	
	/**
	 * @return Page Count corresponding to the number of records in Related Account popup
	 */
	public boolean isPageCountCorrespondingToRecords() {
		List<String> visitNumbersUi = getAllVisitNumbers();
		int pageCount=1;
		double visitCount=visitNumbersUi.size();
		if (visitNumbersUi.size() > 20) {
			pageCount=(int) ((visitCount+20-1)/20);
		} 
		return pageCount==Integer.parseInt(defaultPage.getText());
	}
}