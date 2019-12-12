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

	@FindBy(xpath = "//button[text()='Search']")
	private WebElementFacade searchBtn;

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

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[1]")
	private List<WebElementFacade> relatedVisitsList;

	@FindBy(xpath = "(//*[@id='AccountPageNext' and @class='disabled']/a)[1]")
	private WebElementFacade nextBtnDisabled;

	@FindBy(xpath = "(//*[@id='AccountPageNext']/a)[1]")
	private WebElementFacade nextBtn;

	@FindBy(xpath = "//*[@id='loadingDiv']/i")
	private WebElementFacade paginationSpinner;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']/tbody/tr[2]/td[2]")
	private WebElementFacade firstInvoiceNumber;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']/tbody/tr[2]/td[1]")
	private WebElementFacade firstVisitNumber;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[2]")
	private List<WebElementFacade> relatedInvoicesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[4]")
	private List<WebElementFacade> admitDatesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[5]")
	private List<WebElementFacade> dischargeDatesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[6]")
	private List<WebElementFacade> patientTypesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[3]")
	private List<WebElementFacade> facilityCodesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[7]")
	private List<WebElementFacade> payorPlanCodesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[8]")
	private List<WebElementFacade> insuranceBalanceList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[9]")
	private List<WebElementFacade> patientBalanceList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[10]")
	private List<WebElementFacade> defectTypesList;

	@FindBy(xpath = "//*[@id='tbRelatedAccount']//tr/td[11]")
	private List<WebElementFacade> defectSubTypesList;

	public void clickRelatedAccountsBtn() {
		relatedAccountsBtn.click();
	}

	public String getRelatedAccPopupLabelTxt() {
		return relatedAccountsPopupLbl.getText();
	}

	public boolean isSearchBtnVisible() {
		return searchBtn.isVisible();
	}

	public boolean isFirstBtnVisible() {
		return firstBtnList.size() == 2;
	}

	public boolean isPreviousBtnVisible() {
		return previousBtnList.size() == 2;
	}

	public boolean isNextBtnVisible() {
		return nextBtnList.size() == 2;
	}

	public boolean isLastBtnVisible() {
		return lastBtnList.size() == 2;
	}

	public String getDefaultSelectedPage() {
		return defaultPage.getText();
	}

	public List<String> getRelatedAcctPopUpHeaderList() {
		List<String> headerList = new ArrayList<>();
		for (WebElementFacade header : relatedAccountPopupHeaderList) {
			headerList.add(header.getText());
		}
		return headerList;
	}

	public int getRelatedAccountCount() {
		return relatedVisitsList.size();
	}

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

	public boolean isInvoiceNumberClickable() {
		if (!firstInvoiceNumber.isClickable()) {
			return firstVisitNumber.isClickable();
		}
		return firstInvoiceNumber.isClickable();
	}

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

	public List<String> getVisitsListCurrentPage() {
		List<String> visitNumbers = new ArrayList<>();
		for (WebElementFacade visit : relatedVisitsList) {
			visitNumbers.add(visit.getText().trim());
		}
		return visitNumbers;
	}

	public List<String> getInvoicesListCurrentPage() {
		List<String> invoiceNumbers = new ArrayList<>();
		for (WebElementFacade invoice : relatedInvoicesList) {
			invoiceNumbers.add(invoice.getText().trim());
		}
		return invoiceNumbers;
	}

	public List<String> getAdmitDatesListCurrentPage() {
		List<String> admitDates = new ArrayList<>();
		for (WebElementFacade date : admitDatesList) {
			admitDates.add(date.getText().trim());
		}
		return admitDates;
	}

	public List<String> getDischargeDatesListCurrentPage() {
		List<String> dischargeDates = new ArrayList<>();
		for (WebElementFacade date : dischargeDatesList) {
			dischargeDates.add(date.getText().trim());
		}
		return dischargeDates;
	}

	public List<String> getPatientTypesListCurrentPage() {
		List<String> patientTypes = new ArrayList<>();
		for (WebElementFacade patientType : patientTypesList) {
			patientTypes.add(patientType.getText().trim());
		}
		return patientTypes;
	}

	public List<String> getFacilityCodesListCurrentPage() {
		List<String> facilityCodes = new ArrayList<>();
		for (WebElementFacade facilityCode : facilityCodesList) {
			facilityCodes.add(facilityCode.getText().trim());
		}
		return facilityCodes;
	}

	public List<String> getPayorPlanCodesListCurrentPage() {
		List<String> payorPlanCodes = new ArrayList<>();
		for (WebElementFacade planCode : payorPlanCodesList) {
			payorPlanCodes.add(planCode.getText().trim());
		}
		return payorPlanCodes;
	}

	public List<String> getInsuranceBalanceListCurrentPage() {
		List<String> insuranceBalances = new ArrayList<>();
		for (WebElementFacade insuranceBalance : insuranceBalanceList) {
			insuranceBalances.add(insuranceBalance.getText().trim());
		}
		return insuranceBalances;
	}

	public List<String> getPatientBalanceListCurrentPage() {
		List<String> patientBalances = new ArrayList<>();
		for (WebElementFacade patientBalance : patientBalanceList) {
			patientBalances.add(patientBalance.getText().trim());
		}
		return patientBalances;
	}

	public List<String> getDefectTypesListCurrentPage() {
		List<String> defectTypes = new ArrayList<>();
		for (WebElementFacade defectType : defectTypesList) {
			defectTypes.add(defectType.getText().trim());
		}
		return defectTypes;
	}

	public List<String> getDefectSubTypesListCurrentPage() {
		List<String> defectSubTypes = new ArrayList<>();
		for (WebElementFacade defectSubType : defectSubTypesList) {
			defectSubTypes.add(defectSubType.getText().trim());
		}
		return defectSubTypes;
	}
	
	public String clickAndGetInvoiceNumber() {
		String invoiceNumber=firstInvoiceNumber.getText();
		firstInvoiceNumber.click();
		return invoiceNumber;
	}
	
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
	
	public String clickAndGetVisitNumberHyperLinkedForNAInvoiceNumber() {
		waitForAngularRequestsToFinish();
		boolean flag = false;
		String clickedVisitNumber="";
		do {
			int count = 0;
			for (WebElementFacade invoice : relatedInvoicesList) {
				if (invoice.getText().equals("N/A")) {
					clickedVisitNumber=relatedVisitsList.get(count).getText();
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
}