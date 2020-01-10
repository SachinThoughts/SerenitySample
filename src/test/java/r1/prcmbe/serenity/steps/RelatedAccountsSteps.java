package r1.prcmbe.serenity.steps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.RelatedAccountsPage;

public class RelatedAccountsSteps {
	RelatedAccountsPage relatedAcctPage;
	List<String> visitNumbersDb = new ArrayList<>();
	List<String> invoiceNumbersDb = new ArrayList<>();
	List<String> admitDatesDb = new ArrayList<>();
	List<String> dischargeDatesDb = new ArrayList<>();
	List<Object> patientTypesDb = new ArrayList<>();
	List<String> facilityCodesDb = new ArrayList<>();
	List<String> payorPlanCodesDb = new ArrayList<>();
	List<String> insuranceBalanceDb = new ArrayList<>();
	List<String> patientBalanceDb = new ArrayList<>();
	List<String> defectTypesDb = new ArrayList<>();
	List<String> defectSubTypesDb = new ArrayList<>();
	List<String> formattedPatientTypesDb = new ArrayList<>();

	/**
	 * This methods verifies the Related Account popup grid data on UI with DB
	 * @return list of values
	 * @throws SQLException
	 */
	@Step
	public List<Object> verifyGridDataWithDb() throws SQLException {
		int count = 0;
		List<Object> assertValues = new ArrayList<>();
		while (DatabaseConn.resultSet.next()) {
			visitNumbersDb.add(DatabaseConn.resultSet.getString("AccountNumber").trim());
			invoiceNumbersDb.add(DatabaseConn.resultSet.getString("InvoiceNumber").trim());
			admitDatesDb.add(DatabaseConn.resultSet.getString("AdmitDate").trim());
			dischargeDatesDb.add(DatabaseConn.resultSet.getString("DischargeDate").trim());
			patientTypesDb.add(DatabaseConn.resultSet.getObject("PatientType"));
			facilityCodesDb.add(DatabaseConn.resultSet.getString("FacilityCode").trim());
			payorPlanCodesDb.add(DatabaseConn.resultSet.getString("CurrentPayorPlanCode").trim());
			insuranceBalanceDb.add(DatabaseConn.resultSet.getString("InsuranceBalance").trim());
			patientBalanceDb.add(DatabaseConn.resultSet.getString("PatientBalance").trim());
			defectTypesDb.add(DatabaseConn.resultSet.getString("DefectTypeDesc").trim());
			defectSubTypesDb.add(DatabaseConn.resultSet.getString("DefectSubCategoryDesc").trim());
		}
		for (Object patientType : patientTypesDb) {
			if (patientType == null) {
				formattedPatientTypesDb.add("");
			} else {
				formattedPatientTypesDb.add(patientType.toString());
			}
		}
		if (visitNumbersDb.containsAll(relatedAcctPage.getVisitsListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("VisitNumbers");
		}
		if (invoiceNumbersDb.containsAll(relatedAcctPage.getInvoicesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("InvoiceNumbers");
		}
		if (admitDatesDb.containsAll(relatedAcctPage.getAdmitDatesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("AdmitDate");
		}
		if (dischargeDatesDb.containsAll(relatedAcctPage.getDischargeDatesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("DischargeDate");
		}
		if (formattedPatientTypesDb.containsAll(relatedAcctPage.getPatientTypesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("PatientType");
		}
		if (facilityCodesDb.containsAll(relatedAcctPage.getFacilityCodesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("FacilityCode");
		}
		if (payorPlanCodesDb.containsAll(relatedAcctPage.getPayorPlanCodesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("PayorPlanCode");
		}
		if (insuranceBalanceDb.containsAll(relatedAcctPage.getInsuranceBalanceListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("InsuranceBalance");
		}
		if (patientBalanceDb.containsAll(relatedAcctPage.getPatientBalanceListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("PatientBalance");
		}
		if (defectTypesDb.containsAll(relatedAcctPage.getDefectTypesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("DefectType");
		}
		if (defectSubTypesDb.containsAll(relatedAcctPage.getDefectSubTypesListCurrentPage())) {
			count = count + 1;
		} else {
			assertValues.add("DefectSubType");
		}
		if (count == 11) {
			assertValues.add(true);
		} else {
			assertValues.add(false);
		}
		return assertValues;
	}
}
