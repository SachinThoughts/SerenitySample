package r1.prcmbe.serenity.steps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.PatientInformationPage;

public class PatientInformationSteps {
	 PatientInformationPage patientInfoPage;
	List<String> getDBFacilityDetails = new ArrayList<>();
	String facilityTaxId1,facilityPTan1;
	
	/**
	 * Below method verifies value fetched from DB and UI are Equal or not
	 * @return List of values which are not matched with UI and DB
	 * @throws SQLException
	 */
	@Step
	public  List<Object> verifyFacilityDetailsWithDb() throws SQLException {
		int count = 0;
		List<Object> listOfVal = new ArrayList<>();
		while (DatabaseConn.resultSet.next()) {
			String facilityCode = DatabaseConn.resultSet.getString("Facility Code").trim();
			String facilityName = DatabaseConn.resultSet.getString("Facility Name").trim();
			String locationCode = DatabaseConn.resultSet.getString("Location code").trim();
			String locationName = DatabaseConn.resultSet.getString("Location Name").trim();
			String locationAddress = DatabaseConn.resultSet.getString("Location Address").trim();
			String facilityCity = DatabaseConn.resultSet.getString("City").trim();
			String facilityState = DatabaseConn.resultSet.getString("State").trim();
			String facilityZipCode = DatabaseConn.resultSet.getString("Zip Code").trim();
			String Npi = DatabaseConn.resultSet.getString("NPI").trim();
			String facilityTaxId = DatabaseConn.resultSet.getString("Tax ID #");
			String facilityPTan = DatabaseConn.resultSet.getString("PTAN");
			
			if (facilityTaxId.isEmpty()) {
				facilityTaxId = "-";
			}
			if (facilityPTan.isEmpty()) {
				facilityPTan = "-";
			}
			if (facilityCode.equalsIgnoreCase(patientInfoPage.getFacilityCode())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility code");
			}
			if (facilityName.equalsIgnoreCase(patientInfoPage.getFacilityName())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility name");
			}
			if (locationCode.equalsIgnoreCase(patientInfoPage.getLocationCode())) {
				count = count + 1;
			} else {
				listOfVal.add("Location code");
			}
			if (locationName.equalsIgnoreCase(patientInfoPage.getLocationName())) {
				count = count + 1;
			} else {
				listOfVal.add("Location Name");
			}
			if (locationAddress.equalsIgnoreCase(patientInfoPage.getFacilityAddress())) {
				count = count + 1;
			} else {
				listOfVal.add("Location address");
			}

			if (facilityCity.equalsIgnoreCase(patientInfoPage.getFacilityCity())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility city");
			}
			if (facilityState.equalsIgnoreCase(patientInfoPage.getFacilityState())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility state");
			}
			if (facilityZipCode.equalsIgnoreCase(patientInfoPage.getFacilityZipCode())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility zip code");
			}
			if (Npi.equalsIgnoreCase(patientInfoPage.getFacilityNpi())) {
				count = count + 1;
			} else {
				listOfVal.add("NPI");
			}
			if (facilityTaxId.equalsIgnoreCase(patientInfoPage.getFacilityTaxId())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility TaxID");
			}
			if (facilityPTan.equalsIgnoreCase(patientInfoPage.getFacilityPTan())) {
				count = count + 1;
			} else {
				listOfVal.add("Facility PTan");
			}
			if (count == 11) {
				listOfVal.add(true);
			} else {
				listOfVal.add(false);
			}
		}
		return listOfVal;
	}
}